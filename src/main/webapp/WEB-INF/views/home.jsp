<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${not empty articles}">
    <c:choose>
        <c:when test="${not empty categories}">
            <h2>Recently added articles</h2>
        </c:when>
        <c:when test="${not empty flag}">
            <h2>All Articles</h2>
        </c:when>
        <c:otherwise>
        <h2>Articles from ${categoryName} category</h2>
        </c:otherwise>
    </c:choose>

    <c:forEach items="${articles}" var="article">
        <ul>
            <li>Tytuł: ${article.getTitle()}, Data dodania: ${article.getCreatedOn()},
                Content: ${article.getContent()},
                Data aktualizacji: ${article.getUpdatedOn()}</li>
        </ul>
    </c:forEach>
</c:if>
<form method="POST" action="/home/cat">

    <c:if test="${not empty categories}">

    <h2>Category List</h2>

    <select name="category" id="dropdown">
        <c:forEach items="${categories}" var="category">

            <option value="${category.getName()}">${category.getName()}</option>

        </c:forEach>
    </select>
        <input type="submit" value="Submit Form"/>
    </c:if>
</body>
</html>

