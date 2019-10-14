layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#submit-code-list',
        url: servletContent + 'submit_code_list',
        method: 'post',
        page: true,
        limit: 20,
        id: 'table',
        cols: [[ //表头
            {field: 'userName', title: '用户', templet: function (d) {
                if (d.userName == null) {
                    return "-----";
                } else {
                    return d.userName;
                }
            }},
            {field: 'problemId', title: 'ID'},
            {field: 'problemName', title: '题目名称'},
            {field: 'codeType', title: '语言类型'},
            {field: 'submitTime', title: '提交时间', templet: function (d) {
                date = new Date(d.submitTime);
                var res = date.getFullYear() + "-" +
                          date.getMonth() + "-" +
                          date.getDate() + " " +
                          date.getHours() + ":" +
                          date.getMinutes();
                return res;
            }},
            {field: 'judgeStatus', title: '评测状态'}
        ]],
        done: function (res) {
            //do something after getted data

        }
    });
    table.on('row(submit-code-list)', function(obj){
        window.location.href = "#";
    });
    setInterval(function () {
        table.reload('table',{});
    }, 3000)
})