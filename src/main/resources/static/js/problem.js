$.get("/problem/data/" + $("#problemId").val(), function (res) {
    $("#problem-data").html(res);
});

function problemEditor() {
    window.location.href = "/aceEditor/" + $("#problemId").val();
}