<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="/student/showCourse">所有课程<span class="badge pull-right"></span></a></li>
        <li><a href="/student/selectedCourse">已选课程<span class="badge pull-right"></span></a></li>
        <li><a href="/student/overCourse">已修课程<span class="badge pull-right"></span></a></li>        <!-- 新增的通知列表按钮 -->
        <li><a href="/student/showNotice">通知列表<span class="glyphicon glyphicon-bell pull-right"></span></a></li>
        <li><a href="/student/passwordRest">修改密码<span class="glyphicon glyphicon-pencil pull-right"></span></a></li>
        <li><a onclick="logoutConfirmd()">退出系统<span class="glyphicon glyphicon-log-out pull-right"></span></a></li>

    </ul>

    <script>
        function logoutConfirmd() {
            var msg = "您确定要退出吗？！";
            if (confirm(msg) == true) {
                location.href = '/logout';
                return true;
            } else {
                return false;
            }
        };
    </script>
</div>