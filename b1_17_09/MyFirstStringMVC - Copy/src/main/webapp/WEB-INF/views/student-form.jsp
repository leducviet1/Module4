<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Oct-25
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form thêm sinh viên</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="post">
    Mã sv<input type="text" name="code" > <br>
    Tên:<input type="text" name="name" > <br>
    GPA:<input type="text" name="gpa" > <br>
    <button type="submit">Add</button>
</form>
</body>
</html>
