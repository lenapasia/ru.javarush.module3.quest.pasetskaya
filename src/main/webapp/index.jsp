<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quest Portal</title>
</head>

<body>
<h1>Welcome to Quest portal</h1>

<div>
<c:if test='${sessionScope.user == null}'>
    <form action="/user" method="post">
        <p>Please enter your name:</p>
        <input class="form-check-input" type="text" name="name">
        <div  class="d-grid gap-2 d-md-flex justify-content-md-start">
            <button class="btn btn-primary" type="submit">Submit</button>
        </div>
    </form>
</c:if>

<c:if test='${sessionScope.user!= null}'>
    <form action="/start-quest" method="post">
        <div  class="d-grid gap-2 d-md-flex justify-content-md-start">
            <button class="btn btn-primary" type="submit">Start</button>
        </div>
    </form>
</c:if>
</div>

</body>
</html>