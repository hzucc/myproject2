layui.use('table', function () {
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#submit-code-list',
        url: '/submit_code_list',
        method: 'post',
        page: true,
        limit: 20,
        cols: [[ //表头
            {field: 'problemId', title: 'ID'},
            {field: 'problemName', title: '题目名称'},
            {field: 'codeType', title: '语言类型'},
            {field: 'userName', title: '用户'},
            {field: 'judgeStatus', title: '评测状态'}
        ]],
        done: function (res) {
            //do something after getted data
        }
    });
    table.on('row(submit-code-list)', function(obj){
        window.location.href = "#";
    });
})