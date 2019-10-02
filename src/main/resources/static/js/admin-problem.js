//var result = myEditor.getData();
//myEditor.setData(content);
/*获取题目信息*/
if ($("#problemId").val() != null) {
    $.getJSON("/problem/data/" + $("#problemId").val(), function (res) {
        $("#problemName").val(res.problemName);
        myEditor.setData(res.problemContent);
        $("#c_cppTimeLimit").val(res.timeLimit.c_cppTimeLimit);
        $("#javaTimeLimit").val(res.timeLimit.javaTimeLimit);
        $("#c_cppMemoryLimit").val(res.memoryLimit.c_cppMemoryLimit);
        $("#javaMemoryLimit").val(res.memoryLimit.javaMemoryLimit);
    })
    /*获取题目样例数据*/
    $.getJSON("/getTestDataMessage/" + $("#problemId").val(), function (res) {
        if(res.testDataStatus == "已上传") {
            var fileSize = Number(res.fileSize);
            if (fileSize == 0) {
                fileSize = 1;
            }
            $("#testDataFile").text(fileSize);
        } else {
            $("#testDataFile").text(res.testDataStatus);
        }
    })
}

layui.use('upload', function(){
    var upload = layui.upload;

    //执行实例
    var uploadInst = upload.render({
        elem: '#updateTestData' //绑定元素
        ,url: '/updateTestData' //上传接口
        ,accept: 'file'
        ,exts: 'zip'
        ,auto: true
        ,method: 'POST'
        ,multiple: false
        ,size: 20480
        ,data: {
            problemId: function () {
                return $("#problemId").val();
            }
        }
        ,done: function(res, index, upload){
            alert("文件上传成功")
        }
        ,error: function(){
            alert("文件上传出错...")
        }
    });
});

function updateProblem() {
    $("#problemContent").val(myEditor.getData());
    $("#form").submit();
}

function downloadTestData() {
    window.location.href = "/downloadTestData/" + $("#problemId").val();
}
