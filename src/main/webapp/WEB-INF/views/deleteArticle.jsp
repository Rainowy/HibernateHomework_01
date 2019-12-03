<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete Article</title>
</head>
<body>
<h2> Do you want to delete Article: ${article.getTitle()}, Created: ${article.getCreatedOn()} </h2>
<form method="post" action="/article/delete">
    <input type="hidden" name="id" value="${article.getId()}">
    <input type="radio" name="del" value="yes">YES
    <input type="radio" name="del" value="no">NO
    <input type="submit" value="Send">
</form>
</body>
</html>
