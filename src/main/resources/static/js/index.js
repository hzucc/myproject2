layui.use('element', function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function (elem) {
        //console.log(elem)
        layer.msg(elem.text());
    });
});
layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#problemList'
        , url: '/problemList'
        , page: true
        , cols: [[
            {field: 'problemId',  title: 'ID', sort: true}
            , {field: 'problemName',  title: '题目名'}
            , {field: 'acceptNumber/submitNumber',  title: '通过数/总提交数',
                templet: '<div>{{d.acceptNumber}}/{{d.submitNumber}} ({{d.submitNumber? Math.round(d.acceptNumber / d.submitNumber * 10000) / 100.00: 100 }}%)</div>'
            }
            , {field: 'status',  title: '状态'}
        ]]
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "msg": "", //解析提示文本
                "count": 1000, //解析数据长度
                "data": res //解析数据列表
            };
        }
    })
    table.on('row(table-row-event)', function(obj){
        var href = null;
        var isAdmin = false;
        var problemId = obj.data.problemId;
        $.getJSON("/isAdmin", function (isAdmin) {
            var href = null;
            if (isAdmin) {
                href = "/admin/problem/" + problemId;
            } else {
                href = "/problem/" + problemId;
            }
            window.location.href = href;
        });
    });
});
