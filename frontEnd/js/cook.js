<<<<<<< HEAD
//把订单传来的菜都放在一个数组里，初始设前四个元素显示，
//若按向右，则初始四个元素隐藏，后四个显示
//若按向左，则初始四个隐藏，前四个显示

var array=document.getElementsByClassName("order");
var now=0;

function changeImageleft()
{
    var count=1;
    if(now<=0){
        alert("已经是第一页了");
        return;
    }
    for(;now>=0&&now<array.length;now--)
    {
        if(count<=4) {
            array[now].style.display = "none";
            count++;

            continue;
        }
        if(count>4&&count<=8){
            if(array[now].name!="notorder")
                array[now].style.display = "block";
            count++;
            if(count>8){
                break;
            }
            continue;
        }

    }

}

//        var tishi=0;
function changeImageright()
{
    var count=1;
//            tishi++;
//            if(count==1&&tishi==2){
//                alert("后面没有订单了");
//                tishi=0;
//            }
    if(now>=7){
        alert("后面没有订单了");
        return;
    }

    for(;now<array.length;now++)
    {
        if(count<=4) {
            array[now].style.display = "none";
//                        console.log(count);
            count++;

            continue;
        }

        if(count>4&&count<=8){
            if(array[now].name!="notorder")
                array[now].style.display = "block";
//                        console.log(count);
            count++;
            if(count>8){
//                            console.log(count);
                break;
            }
            continue;
        }

    }

}

//刷新
function  flash() {
    array=document.getElementsByName("order");
}
//撤销菜品
function canceldish(){
    var check=document.getElementsByName("Xuan");
    for(var i=0;i<check.length;i++){
        if(check[i].checked==true){
//            console.log(check[i].parentNode.name);
            check[i].parentNode.name="notorder";
            check[i].parentNode.style.display="none";
//            console.log(check[i].parentNode.name);

        }
    }
    flash();
}

//取消全选
function cancelall() {
    var checks = document.getElementsByName("Xuan");
    console.log(checks[0].checked);
    for (var i = 0; i < checks.length; i++) {
        if (checks[i].checked == true) {
            checks[i].checked = false;
        }
    }
    console.log(checks[0].checked);
}


//接单确认
function accept() {
    $.get("",function(){
        alert("发送信息成功");
}
//菜品通知
function finish() {

}
=======
//把订单传来的菜都放在一个数组里，初始设前四个元素显示，
//若按向右，则初始四个元素隐藏，后四个显示
//若按向左，则初始四个隐藏，前四个显示

var array=document.getElementsByClassName("order");
var now=0;

function changeImageleft()
{
    var count=1;
    if(now<=0){
        alert("已经是第一页了");
        return;
    }
    for(;now>=0&&now<array.length;now--)
    {
        if(count<=4) {
            array[now].style.display = "none";
            count++;

            continue;
        }
        if(count>4&&count<=8){
            if(array[now].name!="notorder")
                array[now].style.display = "block";
            count++;
            if(count>8){
                break;
            }
            continue;
        }

    }

}

//        var tishi=0;
function changeImageright()
{
    var count=1;
//            tishi++;
//            if(count==1&&tishi==2){
//                alert("后面没有订单了");
//                tishi=0;
//            }
    if(now>=7){
        alert("后面没有订单了");
        return;
    }

    for(;now<array.length;now++)
    {
        if(count<=4) {
            array[now].style.display = "none";
//                        console.log(count);
            count++;

            continue;
        }

        if(count>4&&count<=8){
            if(array[now].name!="notorder")
                array[now].style.display = "block";
//                        console.log(count);
            count++;
            if(count>8){
//                            console.log(count);
                break;
            }
            continue;
        }

    }

}

//刷新
function  flash() {
    array=document.getElementsByName("order");
}
//撤销菜品
function canceldish(){
    var check=document.getElementsByName("Xuan");
    for(var i=0;i<check.length;i++){
        if(check[i].checked==true){
//            console.log(check[i].parentNode.name);
            check[i].parentNode.name="notorder";
            check[i].parentNode.style.display="none";
//            console.log(check[i].parentNode.name);

        }
    }
    flash();
}

//取消全选
function cancelall() {
    var checks = document.getElementsByName("Xuan");
    console.log(checks[0].checked);
    for (var i = 0; i < checks.length; i++) {
        if (checks[i].checked == true) {
            checks[i].checked = false;
        }
    }
    console.log(checks[0].checked);
}


//接单确认
function accept() {
    $.get("",function(){
        alert("发送信息成功");
}
//菜品通知
function finish() {

}
>>>>>>> c0e5c0ee7acac5af42d6fadcf1e1b778c689cfc5
