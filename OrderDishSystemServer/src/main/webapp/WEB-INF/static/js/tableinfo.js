$("body").onload(function(){
    $.post("url",{
        "name":"table",
        "type":"dish"
    },function(data){
        showDataTable(data);
    },"json");
});

function showDataTable(data){
    var table_info=JSON.parse(data);
    var record;
    var i = 1;
    for(record in table_info){
        var tbody=$("tbody");
        tbody.append("<tr id=\"dish_line"+i.toString()+"\">");
        tbody.append("<td><span  class=\"datamanage_content_text\">"+record.catagory+"</span></td>");
        tbody.append("<td><span  class=\"datamanage_content_text\">"+record.dishName+"</span></td>");
        tbody.append("<td><span  class=\"datamanage_content_text\">"+record.price+"</span></td>");
        tbody.append("<td><span  class=\"datamanage_content_text\">"+record.salesNum+"</span></td>");
        tbody.append("<td><img id=\"detail_button"+i.toString()+"\"  class=\"small_button\" src=\"image/detail.jpg\">" +
            "<img id=\"edit_button"+i.toString()+"\"  class=\"small_button\" src=\"image/edit.jpg\">"+
            "<img id=\"delete_button"+i.toString()+"\"  clsss=\"small_button\" src=\"image/delete.jpg\">");
        $("detail_button"+i.toString()).onclick(function(){

        });
        $("edit_button"+i.toString()).onclick(function () {

        });
        $("delete_button"+i.toString()).onclick(function () {
            con=confirm("确定要删除该菜品的信息？");
            if(con===true){
                $("dish_line"+i.toString()).remove();
                $.post("url",{
                    "name":"deleteRecord",
                    "value":i.toString()
                },function(){},"json");
            }
        });

        i++;
    }
}

$("#flash_data").onclick(function(){
    var pkey=$("primary_key").find("option:selected").val();
    var order=$("order_base").find("option:selected").val();
    for(var i=0;i<table_line_num;i++){
        $("#dish_table_body").empty();
    }
    $.post("url",{
        "primary_key":pkey,
        "order":order
    },function(data){
        showDataTable(data);
    },"json");
});

$("#calculate_sales").onclick(function () {
    $.post("url",{
        "name":"value",
        "type":"dish"
    },function (data) {
        var sale_num=JSON.parse(data);
        $("#show_sales_data").attr("value","当前餐厅菜品总销量为"+sale_num.toString()+"份");
    })
})