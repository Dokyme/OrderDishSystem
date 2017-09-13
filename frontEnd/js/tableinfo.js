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

function payBillTableOutput(count,data){
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td><img src=\"image/detail.png\" style=\"width:20px;height:20px;\"</td>";
    strHtml+="<td>"+data["table"]+"</td>";
    strHtml+="<td>"+data["time"]+"</td>";
    strHtml+="<td>¥"+data["total"]+"</td>";
    strHtml+="<td> <div> <img src=\"image/confirm.png\" class=\"content_icon\" id=\"confirm_"+count.toString()+"\"> <img src=\"image/cancel.png\" class=\"content_icon\" id=\"cancel_"+count.toString()+"\"> </div> </td>";
    strHtml+="</tr>"
    return strHtml;
}
<<<<<<< HEAD

function staffManageTableOutput(count,data) {
    var career=["管理员","厨师","服务员"];
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td>"+career[parseInt(data["type"])]+"</td>";
    strHtml+="<td>"+data["name"]+"</td>";
    strHtml+="<td>"+data["account"]+"</td>";
    strHtml+="<td> <div> <img src=\"image/edit.png\" class=\"content_icon\" id=\"edit_"+count.toString()+"\"> <img src=\"image/cancel.png\" class=\"content_icon\" id=\"cancel_"+count.toString()+"\"> </div> </td>";
    strHtml+="</tr>"
=======

function staffManageTableOutput(count,data) {
    var career=["管理员","厨师","服务员"];
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td>"+career[parseInt(data["type"])]+"</td>";
    strHtml+="<td>"+data["name"]+"</td>";
    strHtml+="<td>"+data["account"]+"</td>";
    strHtml+="<td> <div> <img src=\"image/edit.png\" class=\"content_icon\" id=\"edit_"+count.toString()+"\"> <img src=\"image/cancel.png\" class=\"content_icon\" id=\"cancel_"+count.toString()+"\"> </div> </td>";
    strHtml+="</tr>"
    return strHtml;
}

function dishManageTableOutput(count,data) {
    var career=["管理员","厨师","服务员"];
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td>"+data["type"]+"</td>";
    strHtml+="<td>"+data["name"]+"</td>";
    strHtml+="<td>"+data["price"]+"</td>";
    strHtml+="<td>"+data["sales"]+"</td>";
    strHtml+="<td> <div> <img src=\"image/edit.png\" class=\"content_icon\" id=\"edit_"+count.toString()+"\"> <img src=\"image/cancel.png\" class=\"content_icon\" id=\"cancel_"+count.toString()+"\"> </div> </td>";
    strHtml+="</tr>";
    return strHtml;
}

function moneyManageTableOutput(count,data){
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td>"+data["time"]+"</td>";
    strHtml+="<td>"+data["income"]+"</td>";
    strHtml+="<td>"+data["sellNum"]+"</td>";
    strHtml+="<td>"+data["fantai"]+"</td>";
    strHtml+="</tr>";
    return strHtml;
}

function billManageTableOutput(count,data) {
    var strHtml="";
    strHtml+="<tr id=\"row_"+count.toString()+"\">";
    strHtml+="<td>"+data["time"]+"</td>";
    var starNum=[parseInt(data["dishquality"]),parseInt(data["cookspeed"]),parseInt(data["serveattitude"])];
    for(var k=0;k<3;k++){
        strHtml+="<td>";
        for(var i=0;i<starNum[k];i++)
            strHtml+="<img src=\"image/star.png\" class=\"star_icon\">";
        for(var j=starNum[k];j<5;j++)
            strHtml+="<img src=\"image/nostar.png\" class=\"star_icon\">";
        strHtml+="</td>";
    }
    strHtml+="</tr>";
>>>>>>> c0e5c0ee7acac5af42d6fadcf1e1b778c689cfc5
    return strHtml;
}