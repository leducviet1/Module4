 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sinh viên</title>
</head>
<body>
<h3>Danh sách sinh viên</h3>
<a href="${pageContext.request.contextPath}/students/create">Thêm sinh viên</a>
<table border="1">
    <tr>
        <th>Mã sinh viên</th>
        <th>Tên sinh viên</th>
        <th>GPA</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.code}</td>
            <td>${student.name}</td>
            <td>${student.gpa}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
