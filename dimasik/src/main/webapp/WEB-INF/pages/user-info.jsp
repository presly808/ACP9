<%@ include file="import.jsp" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>

<%-- will be replced by jstl--%>
<c:set var="user" value="${requestScope.currentUser}"/>

<div>
    <h1>User Info</h1>

    <ul>
        <li>
            Id : ${user.id}
        </li>

        <li>
            Username : ${user.username}
        </li>
        <li>
            Requirements : ${user.rentalRequirements}
        </li>
    </ul>

</div>


</body>
</html>
