<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 21.11.19
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${not empty articles}">
    <h2>Animals List</h2>
    <c:forEach items="${articles}" var="article">
        <ul>
            <li>Tytuł: ${article.getTitle()}, Data dodania: ${article.getCreatedOn()},
            Content: ${article.getContent()},
            Data aktualizacji: ${article.getUpdatedOn()}</li>
        </ul>
    </c:forEach>
</c:if>
</body>
</html>
