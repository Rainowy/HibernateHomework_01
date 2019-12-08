<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<c:if test="${not empty addSuccess}">
   <h2 style="background-color:green">${addSuccess}</h2>
</c:if>

<h2>List of categories</h2>
<c:choose>
    <c:when test="${not empty category}">
        <c:set var="formAction" value="/category/postAdd"/>
        <h2>Add new Category</h2>
        <form:form method="post" action="${formAction}" modelAttribute="category">
            <%--<form:hidden path="id" value="${article.getId()}"/>--%>
            <p>Name<form:input path="name"></form:input></p>
            <p><form:errors path="name" cssClass="error" /></p>
            <p>Description<form:input path="description"></form:input></p>
            <p><input type="submit" value="Register"></p>
        </form:form>
    </c:when>
    <c:otherwise>
        <c:forEach items="${categories}" var="category">
            <ul>
                <li><h3>${category.get('name')} Category</h3>
                    <a href="<c:url value="/category/${category.get('name')}" />"> Articles </a>,
                    <a href="<c:url value="/category/add/${book.getId()}" />"> Add Category </a>
                    <a href="<c:url value="/category/delete/${category.get('id')}" />">Kasuj</a>
                </li>
            </ul>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
