/**获取供查询的下拉列表框选项值 */
function getQueryCrashLog() {
    var pouring_position = "";
    var add_materials = "";
    var strength_grade = "";
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: '/SelectQuery',  //请求的路径
        success: function (data) {
            var pouring_positionarr = data.pouring_position//controller层传过来的map
            for (var i = 0; i < pouring_positionarr.length; i++) {
                pouring_position += "<option value=\'" + pouring_positionarr[i] + "'\>" + pouring_positionarr[i] + "</option>";
            }
            var addMaterialsarr = data.add_materials//controller层传过来的map
            for (var i = 0; i < addMaterialsarr.length; i++) {
                add_materials += "<input type='checkbox' name='addmaterials' value = '" + addMaterialsarr[i] + "'>" + addMaterialsarr[i] + "</input>";
            }
            var strength_gradearr = data.strength_grade//controller层传过来的map
            for (var i = 0; i < strength_gradearr.length; i++) {
                strength_grade += "<option value=\'" + strength_gradearr[i] + "'\>" + strength_gradearr[i] + "</option>";
            }
        }
    });
    $("#pouring_position").html(pouring_position);
    $("#strength_grade").html(strength_grade);
    $("#add_materials").html(add_materials);

}

function submitform() {
    var check = document.getElementsByName("addmaterials");
    var addmaterialsvalue = "";
    for (let i = 0; i < check.length; i++) {
        if (check[i].checked == true) {
            addmaterialsvalue += check[i].value + "~";
        }
    }
    var form = document.getElementById("business_add_fm");
    form.addmaterialsvalue.value = addmaterialsvalue;
    form.submit();
}
