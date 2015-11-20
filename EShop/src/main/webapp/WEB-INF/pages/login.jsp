<%@ include file="import.jsp" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<h1>Register form</h1>
<%--domain/register--%>
<form action="login" method="post">
    Email: <input name="email" type="text"><br>
    Pass: <input name="pass" type="password"><br>
    <input value="Login" type="submit">
</form>

</body>
</html>
