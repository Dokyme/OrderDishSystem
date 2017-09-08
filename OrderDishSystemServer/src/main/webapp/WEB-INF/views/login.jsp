<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/login.css"/>
    <script src="static/js/jquery-3.2.1.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <title>登陆页面</title>
    <script>
        $(document).ready(function () {
            $("#verify_pic").bind("click", function () {
                $("#verify_pic").attr("src", "http://localhost:8080/captcha?" + Math.random());//刷新验证码
            });

            $("#submit").bind("click", function () {
                var a=$("#username").attr("value");
                var b=document.getElementById("username").value;
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/login",
                    dataType: "json",
                    data: {
                        username: $("#username").attr("value"),
                        password: $("#password").attr("value"),
                        captcha: $("#verify").attr("value")
                    },
                    success: function (data, state) {
                        if (state == 200) {
                            alert("登陆成功");
                            var response = JSON.parse(data);
                            if (response["flag"] === "1") {
                                var usertype = response["type"];
                                if (usertype === "1") {

                                }
                                else if (usertype === "2") {

                                }
                                else {

                                }
                            }
                        }
                        else {
                            alert("登陆失败");
                            $("error_message").attr("value", state);
                            clearAll();
                        }
                    }
                });
            });
        });

        function clearAll() {
            $("#username").attr("value", "");
            $("#password").attr("value", "");
            $("#verify").attr("value", "");
            $("#verify_pic").click();
        }
    </script>
</head>


<body>
<div class="background_pic">
    <img src="static/image/login_background.jpg">
</div>
<headline id="headline">
    餐厅点餐系统
</headline>
<div id="login_box">
    <label id="error_message"></label>
    <p class="login_text">用户名</p>
    <input id="username" type="text" placeholder="  Username" autofocus required>
    <p class="login_text">密码</p>
    <input id="password" type="password" placeholder="  Password" required>
    <p class="login_text">验证码</p>
    <input id="verify" type="text">
    <img id="verify_pic" src="http://localhost:8080/captcha">
    <input id="submit" type="submit" value="登  陆">
</div>
</body>

</html>