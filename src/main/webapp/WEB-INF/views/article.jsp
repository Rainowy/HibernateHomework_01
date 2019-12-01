<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <li>Tytuł: ${article.getTitle()}, Data dodania: ${article.getCreatedOn()},
                Content: ${article.getContent()},
                Data aktualizacji: ${article.getUpdatedOn()}</li>
        </ul>
    </c:forEach>
</c:if>
<%--<c:if test="${not empty categories}">--%>
    <%--<h2>Category List</h2>--%>
    <select id="select">
        <c:forEach items="${categories}" var="category">

            <option value="${category.get('name')}">${category.get('name')}</option>

        </c:forEach>
    </select>
    <button type="button" id="submit" style="font-size: 16px;">Select Category</button>
    </div>
    <script>
        document.getElementById('submit').addEventListener('click', () =>
            location.href = "/article/" + (document.getElementById('select').value))
    </script>
<button type="button" id="submit1" style="font-size: 16px;">Add Article</button>
<button type="button" id="submit2" style="font-size: 16px;">Delete Article</button>
<button type="button" id="submit3" style="font-size: 16px;">Edit Article</button>
<script>
    document.getElementById('submit1').addEventListener('click', () => location.href = "/article/")
    document.getElementById('submit2').addEventListener('click', () => location.href = "/category/")
    document.getElementById('submit3').addEventListener('click', () => location.href = "/category/")
</script>
</body>
</html>
