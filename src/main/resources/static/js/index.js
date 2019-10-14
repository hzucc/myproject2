
layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#problemList',
        url: servletContent + 'problemList',
        page: true,
        method: 'post',
        cols: [[
            {field: 'problemId',  title: 'ID', sort: true},
            {field: 'problemName',  title: '题目名'},
            {field: 'acceptNumber/submitNumber',  title: '通过数/总提交数',
                templet: '<div>{{d.acceptNumber}}/{{d.submitNumber}} ({{d.submitNumber? Math.round(d.acceptNumber / d.submitNumber * 10000) / 100.00: 100 }}%)</div>'
            },
            {field: 'status',  title: '状态'}
        ]]
    })
    table.on('row(table-row-event)', function(obj){
        var href = null;
        var isAdmin = false;
        var problemId = obj.data.problemId;
        $.getJSON(servletContent + "isAdmin", function (isAdmin) {
            var href = null;
            if (isAdmin) {
                href = servletContent + "admin/problem/" + problemId;
            } else {
                href = servletContent + "problem/" + problemId;
            }
            window.location.href = href;
        });
    });
});
