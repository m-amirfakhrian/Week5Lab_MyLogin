<%-- 
    Document   : home
    Created on : Feb 6, 2023, 12:28:30 PM
    Author     : Majid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <h3>Hello ${user.username}</h3>
        
        <form action="login" method="get">              
            <input type="submit" value="Logout" >
            <input type="hidden" name="action" value="logout">
        </form>
    </body>
</html>
