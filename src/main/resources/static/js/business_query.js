function getAllData() {
    var detials = "<tr>\n" +
        "        <td>公司名称</td>\n" +
        "        <td>具体日期</td>\n" +
        "        <td>浇筑部位</td>\n" +
        "        <td>浇筑方式</td>\n" +
        "        <td>工程量</td>\n" +
        "        <td>车数</td>\n" +
        "        <td>运费</td>\n" +
        "        <td>强度等级</td>\n" +
        "        <td>备注</td>\n" +
        "    </tr>";
    var comp_name = document.getElementsByName("comp_name")[0].value;
    var add_date = document.getElementsByName("add_date")[0].value;
    var pagenumber = document.getElementsByName("pagenumber")[0].value;
    pagenumber
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: '/GetAllData',  //请求的路径
        data: {
            comp_name: comp_name,
            add_date: add_date,
            pagenumber: pagenumber,
        },
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                detials += "<tr><td>" + data[i].business_name + "</td><td>" + data[i].business_date + "</td><td>" + data[i].pouring_position + "</td><td>" + data[i].pouring_method + "</td>";
                detials += "<td>" + data[i].quantities + "</td><td>" + data[i].number_of_vehicles + "</td><td>" + data[i].freight + "</td><td>" + data[i].strength_grade + "</td>" + "<td>" + data[i].remarks + "</td></tr>"
            }
        }
    })
    $("#details").html(detials);
}

function pageup() {
    var pagenumber = document.getElementsByName("pagenumber")[0].value;
    if (pagenumber <= 1) {
        pagenumber = 1;
    } else {
        pagenumber = Number(pagenumber) - Number(1);
    }
    document.getElementsByName("pagenumber")[0].value = pagenumber;
    getAllData();
}

function pagedown() {
    var pagenumber = document.getElementsByName("pagenumber")[0].value;
    pagenumber = Number(pagenumber) + Number(1);
    document.getElementsByName("pagenumber")[0].value = pagenumber;
    getAllData();
}