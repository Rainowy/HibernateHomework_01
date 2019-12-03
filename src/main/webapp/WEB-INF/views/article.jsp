<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Article</title>
</head>
<body>
<c:if test="${not empty articles}">


    <c:choose>
        <c:when test="${not empty categoryName}">
            <h2>Articles from ${categoryName} category</h2>
        </c:when>
        <%--<c:when test="${not empty flag}">--%>
        <%--<h2>All Articles</h2>--%>
        <%--</c:when>--%>
        <c:otherwise>
            <%--<h2>Articles from ${categoryName} category</h2>--%>
            <h2>Recently added articles</h2>
        </c:otherwise>
    </c:choose>

    <c:forEach items="${articles}" var="article">
        <ul>
            <li>Id: ${article.get('id')}, Tytuł: ${article.get('title')}, Data dodania: ${article.get('created')},
                Content: ${article.get('content')},
                Data aktualizacji: ${article.get('updated')}
                <a href="<c:url value="/article/edit/${article.get('id')}" />">Edytuj</a>,
                <a href="<c:url value="/article/delete/${article.get('id')}" />">Kasuj</a>
            </li>
        </ul>
    </c:forEach>
</c:if>
<select id="select">
    <c:forEach items="${categoriesTuple}" var="category">

        <option value="${category.get('name')}">${category.get('name')}</option>

    </c:forEach>
</select>
<button type="button" id="submit" style="font-size: 16px;">Select Category</button>

<button type="button" id="submit1" style="font-size: 16px;">Add Article</button>

<script>
    document.getElementById('submit1').addEventListener('click', () => location.href = "/article/add")
    document.getElementById('submit').addEventListener('click', () =>
        location.href = "/article/" + (document.getElementById('select').value))

</script>

<c:if test="${not empty article}">

    <c:choose>
        <c:when test="${not empty edit}">
            <c:set var="formAction" value="/article/postEdit"/>
            <h2>Update article</h2>
        </c:when>
        <c:otherwise>
            <c:set var="formAction" value="/article/postAdd"/>
            <h2>Add article</h2>
        </c:otherwise>
    </c:choose>
    <form:form method="post" action="${formAction}" modelAttribute="article">
        <form:hidden path="id" value="${article.getId()}"/>
        <p>Title<form:input path="title"></form:input></p>
        <p>Description<form:input path="content"></form:input></p>

        <c:choose>
            <c:when test="${not empty edit}">
                <h3>If names stays blank, author won't be updated</h3>
                <p>Author FirstName<input type="text" name="firstName" value="${article.getAuthor().getFirstName()}">
                </p>
                <p>Author LastName<input type="text" name="lastName" value="${article.getAuthor().getLastName()}"></p>
            </c:when>
            <c:otherwise>
                <h3>If names stays blank, author won't be created</h3>
                <p>Author FirstName<input type="text" name="firstName"></p>
                <p>Author LastName<input type="text" name="lastName"></p>
            </c:otherwise>
        </c:choose>
        <form:select path="category">
            <c:choose>
                <c:when test="${not empty edit}">
                    <form:option value="0" label="Current (${article.getCategory().getName()})" selected="true"
                                 disabled="true"/>
                </c:when>
                <c:otherwise>
                    <form:option value="0" label="Choose category" selected="true" disabled="true"/>
                </c:otherwise>
            </c:choose>
            <form:options items="${categoriesEntity}" itemValue="id" itemLabel="name"/>
        </form:select>
        <p><input type="submit" value="Register"></p>
    </form:form>
</c:if>
</body>
</html>
