<%@ include file="import.jsp"%>
<html>
<head>
    <title>User Info</title>
</head>
<body>

<%@include file="header.jsp"%>

<c:set var="user" value="${requestScope.myUser}"/>

<div>
    <h1>User Info</h1>

    <ul>
        <li>
            Id : ${user.id}
        </li>

        <li>
            Email : ${user.email}
        </li>
        <li>
            Phone : ${user.phone}
        </li>
        <li>
            Fullname: ${user.fullname}
        </li>
    </ul>

</div>


</body>
</html>
