
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Form thêm sinh viên</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/create" method="post">
    Mã sv<input type="text" name="code" > <br>
    Tên:<input type="text" name="name" > <br>
    GPA:<input type="text" name="gpa" > <br>
    <button type="submit">Add</button>
</form:form>
</body>
</html>
