<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
    <!-- Bootstrap CDN -->
    <link 
    rel="stylesheet" 
    href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/lux/bootstrap.min.css" 
    integrity="sha384-9+PGKSqjRdkeAU7Eu4nkJU8RFaH8ace8HGXnkiKMP9I9Te0GJ4/km3L1Z8tXigpG" 
    crossorigin="anonymous"
    >
        
</head>
<body>
    <!-- to make sure this works -->
    <!-- ${userId} -->
    <div class="container text-center">
        <div class="card border-dark">
            <h1 class="text-center" >
                Welcome, <c:out value="${user.userName}"></c:out> ! 
            </h1>
            <h2>Join our growing community.</h2>
            <p>This is your dashboard. Nothing to see here yet.</p>
            <!--
                -> to make sure that the Id is here
                -> Know that the user is logged in because the id is there
                -> essentially registering the user and as soon as register -> log them in at the same time
                -> know the password is there because it shows the hashed password
                -> would never display this -> just a test
            -->
            ${userId}
            <!-- ${password} -->
            <a class="btn btn-dark btn-sm" href="/logout">LogOut</a>
        </div>
    </div>

</body>
</html>
