<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quest Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

</head>

<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


<div class="container col-xxl-8 px-4 py-5">
    <h1>Welcome to Quest portal</h1>
<c:if test='${sessionScope.user == null}'>
    <form action="/user" method="post">
        <div class="d-grid gap-2">
            <p>Please enter your name:</p>
            <input class="form-control" type="text" name="name">
            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <button class="btn btn-outline-success" type="submit">Submit</button>
            </div>
        </div>
    </form>
</c:if>

<c:if test='${sessionScope.user != null}'>
    <form action="/start-quest" method="post">
        <div class="d-grid gap-2 mt-5">
            <h3 class="text-primary">${requestScope.availableQuest.name}</h3>
            <label>
                    ${requestScope.availableQuest.description}
            </label>
            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <button class="btn btn-outline-success" type="submit">Start</button>
            </div>
        </div>
    </form>
</c:if>
</div>

</body>
</html>