<%@ include file="import.jsp"%>
<html>

    <head>
        <title>Register page</title>
    </head>

    <body>

        <div>

            <h1>Register form</h1>
            <%--domain/register--%>
            <form action="register" method="post">
                Email:      <input name="email" type="text"><br>
                Fullname:   <input name="fullname" type="text"><br>
                Phone:      <input name="phone" type="text"><br>
                Pass:       <input name="pass" type="password"><br>
                <input value="Register" type="submit">
            </form>

        </div>

    </body>

</html>
