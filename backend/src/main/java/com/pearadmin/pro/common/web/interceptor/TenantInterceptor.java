package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.context.BeanContext;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.web.interceptor.annotation.TenantIgnore;
import lombok.Data;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.BinaryExpression;
import com.pearadmin.pro.common.constant.TenantConstant;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 多 租 户 拦 截 器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class TenantInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(getAnnotation(InvocationHandler.getMappedStatement(invocation)) == null) {
            String sql = InvocationHandler.getSql(invocation);
            sql = addWhere(sql);
            InvocationHandler.setSql(invocation, sql);
        }
        return invocation.proceed();
    }

    public String addWhere(String sql) throws JSQLParserException {
        UserContext userContext = BeanContext.getBean(UserContext.class);
        Statement stmt = CCJSqlParserUtil.parse(sql);

        // edit insert sql ...
        if (stmt instanceof Insert) {
            Insert insert = (Insert) stmt;
            // TODO 涉及租户的表, 才修改
            Table table = insert.getTable();

            List<Column> columns = insert.getColumns();

            boolean flag = true;

            for (Column column : columns) {
                if(column.getName(false).equals(TenantConstant.TENANT_COLUMN)) {
                    flag = false;
                }
            }

            if(Arrays.asList(TenantConstant.IGNORE_TABLE).indexOf(table.getName()) < 0 && flag) {
                insert.getColumns().add(new Column(TenantConstant.TENANT_COLUMN));
                ((ExpressionList) insert.getItemsList()).getExpressions().add(new StringValue(userContext.getPrincipal().getTenantId()));
            }
            return insert.toString();
        }

        // edit update sql ...
        if (stmt instanceof Update) {
            Update updateStatement = (Update) stmt;
            // TODO 涉及租户的表, 才修改
            Table table = updateStatement.getTable();
            if(Arrays.asList(TenantConstant.IGNORE_TABLE).indexOf(table.getName()) < 0) {
                Expression where = updateStatement.getWhere();
                if (where instanceof BinaryExpression) {
                    EqualsTo equalsTo = new EqualsTo();
                    equalsTo.setLeftExpression(new Column(TenantConstant.TENANT_COLUMN));
                    equalsTo.setRightExpression(new StringValue(userContext.getPrincipal().getTenantId()));
                    AndExpression andExpression = new AndExpression(equalsTo, where);
                    updateStatement.setWhere(andExpression);
                }
            }
            return updateStatement.toString();
        }

        // edit select sql ...
        if (stmt instanceof Select) {
            Select select = (Select) stmt;
            PlainSelect ps = (PlainSelect) select.getSelectBody();

            Table mainTable = (Table) ps.getFromItem();
            TableAlias mainTableAlias = new TableAlias();
            mainTableAlias.setAlias(mainTable.getAlias()==null?null:mainTable.getAlias().getName());
            mainTableAlias.setTable(mainTable.getName());

            // TODO 涉及租户的表, 才修改
            if(Arrays.asList(TenantConstant.IGNORE_TABLE).indexOf(mainTableAlias.getTable()) < 0) {
                EqualsTo equalsTo = new EqualsTo();
                equalsTo.setLeftExpression(new Column((mainTableAlias.getAlias()==null?mainTableAlias.getTable():mainTableAlias.getAlias()) + '.' + TenantConstant.TENANT_COLUMN));
                equalsTo.setRightExpression(new StringValue(userContext.getPrincipal().getTenantId()));
                if(ps.getWhere() == null) {
                    EqualsTo replaceWhere = new EqualsTo();
                    replaceWhere.setLeftExpression(new Column("1"));
                    replaceWhere.setRightExpression(new LongValue(1));
                    AndExpression andExpression = new AndExpression(equalsTo, replaceWhere);
                    ps.setWhere(andExpression);
                } else {
                    AndExpression andExpression = new AndExpression(equalsTo, ps.getWhere());
                    ps.setWhere(andExpression);
                }
            }
            return select.toString();
        }
        return sql;
    }

    private TenantIgnore getAnnotation(MappedStatement mappedStatement) {
        TenantIgnore tenantIgnore = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
            final Class<?> cls = Class.forName(className);
            final Method[] method = cls.getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName)) {
                    tenantIgnore = me.getAnnotation(TenantIgnore.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenantIgnore;
    }

    @Data
    class TableAlias {

        private String table;

        private String alias;
    }
}
