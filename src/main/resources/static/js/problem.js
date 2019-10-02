new Vue({
    el: '#problem-data',
    data: {
        problemContent: null
    },
    mounted() {
        axios.get('/problem/data/' + $("#problemId").val()).then(function (res) {
            $("#problem-data").html(res.data.problemContent);
        }).catch(function () {
            console.log(error);
        });
    }
})

