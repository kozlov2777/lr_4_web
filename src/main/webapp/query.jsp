<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список преподавателів</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Список преподавателів</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Повне ім'я</th>
        <th>Вік</th>
        <th>Місце роботи</th>
        <th>Досвід</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${teachers}" var="teacher">
        <tr>
            <td><c:out value="${teacher.id}" /></td>
            <td><c:out value="${teacher.fullName}" /></td>
            <td><c:out value="${teacher.age}" /></td>
            <td><c:out value="${teacher.workplace}" /></td>
            <td><c:out value="${teacher.experience}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

