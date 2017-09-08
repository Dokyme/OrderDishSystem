function showTime(){
    var date = new Date();
    var week = date.getDay();
    var weekday;
    switch(week){
        case 0: weekday = '星期天';break;
        case 1: weekday = '星期一';break;
        case 2: weekday = '星期二';break;
        case 3: weekday = '星期三';break;
        case 4: weekday = '星期四';break;
        case 5: weekday = '星期五';break;
        case 6: weekday = '星期六';break;
    }
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    if(hour < 10)
        hour="0"+hour.toString();
    var minute = date.getMinutes();
    if(minute < 10)
        minute="0"+minute.toString();
    var second = date.getSeconds();
    if(second < 10)
        second="0"+second.toString();
    $("#headtext_3").html(year + "年" + month + "月" + day + '日'+' ' + weekday + ' ' + hour + ':' + minute + ':' + second);
    setTimeout(showTime,1000);
}