var indexService = {
    init: function () {
        this.initParam()
    },
    initParam: function () {
        alert("初始化参数");
    }
}

$(function() {
    indexService.init();
});