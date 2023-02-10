<%-- 
    Document   : login
    Created on : Feb 6, 2023, 12:17:01 PM
    Author     : Majid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <form action="home" method="get">
            <table>               
                <tr>
                    <td>Username</td>
                    <td><input type="username" name="username" value="${username}"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="${password}"></td>
                </tr>
            </table>      
            <input type="submit" value="Log in" >
        </form>

        <c:if test="${nullInvalid == true}">
            <p>Invalid Entry. Please enter both username and password.</p>
        </c:if> 
        <c:if test="${uORpInvalid == true}">
            <p>Invalid username and/or password.</p>
        </c:if> 

    </body>
</html>
