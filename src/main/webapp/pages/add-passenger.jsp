<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Passenger Page</title>
</head>
<body>
<form method="post" action="/savePassenger">
    <input name="flightId" type="hidden" value="${param.flightId}">
    <input name="fio" placeholder="FIO"><br>
    <input name="place" placeholder="Place"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>