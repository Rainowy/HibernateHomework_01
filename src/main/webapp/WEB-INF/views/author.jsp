<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author</title>
</head>
<body>
<c:if test="${not empty authors}">
<h2>Authors List</h2>
    <c:forEach items="${authors}" var="author">
        <ul>
            <li><p>Author FirstName: ${author.get("firstName")}, Author LastName: ${author.get("lastName")}</p></li>
        </ul>
    </c:forEach>
</c:if>
<c:if test="${not empty author}">
    <c:set var="formAction" value="/author/postAdd"/>
    <form:form method="post" action="${formAction}" modelAttribute="author">
            Author FirstName<form:input path="firstName"></form:input>
        <p><form:errors path="firstName" cssClass="error" /></p>
            Author LastName<form:input path="lastName"></form:input>
        <p><form:errors path="lastName" cssClass="error" /></p>
    <p><input type="submit" value="Register"></p>
    </form:form>
</c:if>


<button type="button" id="submit" style="font-size: 16px;">Add Article</button>

<script>
    document.getElementById('submit').addEventListener('click', () => location.href = "/author/add");
</script>
</body>
</html>

