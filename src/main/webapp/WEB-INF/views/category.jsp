<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<h2>List of categories</h2>
<c:forEach items="${categories}" var="category">
    <ul>
    <li><h3>${category.get('name')} Category</h3>
        <a href="<c:url value="/category/${category.get('name')}" />"> Articles </a>,
        <a href="<c:url value="/category/add/${book.getId()}" />"> Add Category </a>
    </li>
    </ul>
</c:forEach>

</body>
</html>
