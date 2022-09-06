function getAllData() {
    var detials = "";
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: '/GetAllData',  //请求的路径
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                detials += "<tr><td>"+ data[i].business_name +"</td><td>"+data[i].pouring_position+"</td>;
            }
        }
    })

}