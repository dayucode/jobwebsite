package com.pearadmin.pro.common.tools.core;

import java.util.List;
import java.util.regex.Pattern;

public class PatternUtil {

    public static boolean matches(String pattern,String content){
        return Pattern.matches(getRegPath(pattern), content);
    }

    public static boolean matches(String pattern, List<String> content){
        for ( String item :content) {
            if(matches(getRegPath(pattern),item)){
                return true;
            }
        }
        return false;
    }

    public static boolean matches(List<String> pattern, String content){
        for ( String item : pattern) {
            if(matches(getRegPath(item),content)){
                return true;
            }
        }
        return false;
    }

    private static String getRegPath(String path) {
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for(int i=0;i<len;i++){
            if (chars[i] == '*'){//遇到*字符
                if (preX){//如果是第二次遇到*，则将**替换成.*
                    sb.append(".*");
                    preX = false;
                }else if(i+1 == len){//如果是遇到单星，且单星是最后一个字符，则直接将*转成[^/]*
                    sb.append("[^/]*");
                }else{//否则单星后面还有字符，则不做任何动作，下一把再做动作
                    preX = true;
                    continue;
                }
            }else{//遇到非*字符
                if (preX){//如果上一把是*，则先把上一把的*对应的[^/]*添进来
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?'){//接着判断当前字符是不是?，是的话替换成.
                    sb.append('.');
                }else{//不是?的话，则就是普通字符，直接添进来
                    sb.append(chars[i]);
                }
            }
        }
        return sb.toString();
    }
}
