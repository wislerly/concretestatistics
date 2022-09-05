/**获取供查询的下拉列表框选项值 */
function getQueryCrashLog(){
    console.log("进来了");
    var optionsDev="<option value=''>全部</option>";
/*    var optionsNet="<option value='all'>全部</option>";        //该字段含有null值
    var optionsApp="<option value=''>全部</option>";*/
    $.ajax({
        async : false,
        cache:false,
        type: 'POST',
        dataType : "json",
        url: '/SelectQuery',  //请求的路径
        success:function(data){												//controller层传过来的map
            for(var i=0;i<data.length;i++){
                var deviceSubList = data[i].deviceSubList;
                var networkSubList=data[i].networkSubList;
                var appSubList=data[i].appSubList;
                if(data[i].deviceSubList==deviceSubList){
                    for(var j=0;j<deviceSubList.length;j++){
                        optionsDev+="<option value=\'"+deviceSubList[j]+"'\>"+deviceSubList[j]+"</option>";
                    }
                }
                if(data[i].networkSubList==networkSubList){
                    for(var j=0;j<networkSubList.length;j++){
                        optionsNet+="<option value=\'"+networkSubList[j]+"'\>"+networkSubList[j]+"</option>";
                    }
                }
                if(data[i].appSubList==appSubList){
                    for(var j=0;j<appSubList.length;j++){
                        optionsApp+="<option value=\'"+appSubList[j]+"'\>"+appSubList[j]+"</option>";
                    }
                }
            }
        }
    });
    $("#pouring_position").html(optionsDev);
/*    $("#networkOperatorName").html(optionsNet);
    $("#appVersion").html(optionsApp);*/
}