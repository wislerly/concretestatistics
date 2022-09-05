/**获取供查询的下拉列表框选项值 */
function getQueryCrashLog() {
    console.log("进来了");
    var optionsDev = "<option value=''>全部</option>";
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: '/SelectQuery',  //请求的路径
        success: function (data) {
            var arr = data.pouring_position//controller层传过来的map
            for (var i = 0; i < arr.length; i++) {
                optionsDev += "<option value=\'" + arr[i] + "'\>" + arr[i] + "</option>";
            }
        }
    });
    $("#pouring_position").html(optionsDev);

}