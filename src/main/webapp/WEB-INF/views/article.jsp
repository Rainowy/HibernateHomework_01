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
                <a href="<c:url value="/books/delete/${book.get('id')}" />">Kasuj</a>

                    <%--<button type="button" id="submit2" value="${article.getTitle()}" style="font-size: 16px;">Edit Article</button>--%>
                    <%--<button type="button" id="submit3" style="font-size: 16px;">Delete Article</button>--%>
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
    // document.getElementById('submit2').addEventListener('click', () => location.href = "/article/edit/" +(document.getElementById('submit2').value) )
    //
    // document.getElementById('submit3').addEventListener('click', () => location.href = "/category/")
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
        <p>Title<form:input path="title"></form:input></p>
        <p>Description<form:input path="content"></form:input></p>
        <p>Author FirstName<input type="text" name="firstName"></p>
        <p>Author LastName<input type="text" name="lastName"></p>
        <form:select path="category">
            <form:option value="0" label="Choose category" selected="true" disabled="true"/>
            <form:options items="${categoriesEntity}" itemValue="id" itemLabel="name"/>
        </form:select>
        <p><input type="submit" value="Register"></p>
    </form:form>
</c:if>

</body>
</html>
