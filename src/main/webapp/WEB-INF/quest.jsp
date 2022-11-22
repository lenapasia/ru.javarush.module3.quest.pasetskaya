<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quest</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<div class="col-lg-6 px-4">
    <h1 class="display-5 fw-bold lh-1 mb-3">${requestScope.quest.name}</h1>
    <p class="lead">${requestScope.question.text}</p>

    <c:if test='${requestScope.quest.completed}'>
    <form action="/start-quest" method="post">
        <div  class="d-grid gap-2 d-md-flex justify-content-md-start">
            <button class="btn btn-outline-success" type="submit">Restart</button>
        </div>
    </form>
    </c:if>

    <c:if test='${!requestScope.quest.completed}'>
    <form action="/apply-answer" method="post">
        <c:forEach var="answer" items="${requestScope.question.answers}">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="id" id="flexRadioDefault1"
                       value="${answer.id}">
                <label class="form-check-label" for="flexRadioDefault1">
                        ${answer.text}
                </label>
            </div>
        </c:forEach>
        <div class="d-grid gap-2 d-md-flex justify-content-md-start">
            <button id="btnSubmitAnswer" class="btn btn-outline-success" type="submit" disabled="true">Submit</button>
        </div>
    </form>
    </c:if>

    <%@ include file="statistics.jsp" %>
</div>

<script>
    const radioButtons = document.querySelectorAll('input[name="id"]');
    for (const radioButton of radioButtons) {
        radioButton.addEventListener("change", onRadioButtonChange);
    }

    function onRadioButtonChange() {
        var submitBtn = document.getElementById("btnSubmitAnswer");
        submitBtn.disabled = false;

        for (const radioButton of radioButtons) {
            radioButton.removeEventListener("change", onRadioButtonChange);
        }
    };
</script>

</body>
</html>
