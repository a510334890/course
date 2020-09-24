Loading = {
    show: function () {
        $.blockUI({
            message: '<img src="/static/img/loading.gif"/>',

        });
    },
    hide: function () {
        // 本地查询速度太快，loading显示一瞬间，故意做个延迟
        setTimeout(function () {
            $.unblockUI();
        }, 500)
    }
};