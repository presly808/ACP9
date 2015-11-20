<link rel="stylesheet" type="text/css" href="css/main.css">
<div class="nav">
    <ul>

        <c:if test="${currentUser != null}">
            <li>${currentUser.email}</li><br>
        </c:if>

        <li><a href="register">Register</a></li>
        <li><a href="login">Login</a></li>
        <li><a href="#">Action</a></li>
    </ul>
</div>
