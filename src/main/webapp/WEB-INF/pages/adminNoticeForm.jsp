<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>发布通知</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入 JQuery 和 Bootstrap.js -->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="../pages/admin/top.jsp"></jsp:include>

<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <!-- 侧边栏 -->
        <jsp:include page="../pages/admin/menu.jsp"></jsp:include>

        <!-- 主体内容 -->
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">发布通知</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/admin/adminNotice" method="post">
                        <div class="form-group">
                            <label for="notice" class="col-sm-2 control-label">通知内容</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="notice" name="notice" rows="5" placeholder="请输入通知内容" required></textarea>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 底部 -->
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>

<script type="text/javascript">
    $("#nav li:nth-child(2)").addClass("active");
</script>
</body>
</html>