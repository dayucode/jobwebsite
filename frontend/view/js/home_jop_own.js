layui.use(['admin', 'jquery', 'popup','dropdown','util','carousel','card','restClient','form'], function () {
    var $ = layui.jquery;
    window.enterAdmin = function (){
        var res = layui.restClient.getSync(applicationUtil.URL + '/api/sys/user/enterAdmin')
        console.log(res.data)
        if (res.code === 200){
            location.href = res.msg+'?index=' + encodeURIComponent(JSON.stringify('view/console/console1.html'));
        }else {
            location.href = 'login.html';
        }
    }
    window.enterPerson = function (){
        var res = layui.restClient.getSync(applicationUtil.URL + '/api/sys/user/enterPerson')
        console.log(res)
        if (res.code === 200){
            location.href = res.msg;
        }else {
            location.href = 'login.html';
        }
    }
    window.cardTableCheckedCard = function (elem, obj) {
        $(obj).addClass('layui-table-click').siblings().removeClass('layui-table-click');
        window.location.href = 'positionDetails.html?id=' + encodeURIComponent(JSON.stringify(obj.id));
    }

})