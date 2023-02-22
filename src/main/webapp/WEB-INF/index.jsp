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
    <title>Login and Registration</title>
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
    ${userId}
    <div class="container">
        <h1 class="text-center" >Login and Registration</h1>
        <div class="row d-flex gap-4">
            <div class="col card border-dark">
                <h2>Register</h2>
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <div>
                        <div class="form-group">
                        <form:label path="userName">userName</form:label>
                        <form:input  class="form-control" path="userName"/>
                        <form:errors class="text-danger" path="userName"/>
                        </div>
                        <div class="form-group">
                        <form:label path="email">email</form:label>
                        <form:input  class="form-control" path="email"/>
                        <form:errors class="text-danger" path="email"/>
                        </div>
                        <div>
                        <form:label path="password">password</form:label>
                        <form:input  class="form-control" path="password"/>
                        <form:errors class="text-danger" path="password"/>
                        </div>
                        <div>
                        <form:label path="confirm">confirm password</form:label>
                        <form:input  class="form-control" path="confirm"/>
                        <form:errors class="text-danger" path="confirm"/>
                        </div>
                    </div>
                    <input class="mt-4 btn btn-dark btn-sm" type="submit" value="register">
                </form:form>
            </div>
            <div class="col card border-dark">
                <h2>Login</h2>
                <div class="form-group">
                    <form:form action="/login" method="post" modelAttribute="newLogin">
                        <div class="form-group">
                            <form:label path="email">email</form:label>
                            <form:input  class="form-control" path="email"/>
                            <form:errors class="text-danger" path="email"/>
                        </div>
                        <div>
                            <form:label path="password">password</form:label>
                            <form:input  class="form-control" path="password"/>
                            <form:errors class="text-danger" path="password"/>
                        </div>
                    <input class="mt-4 btn btn-dark btn-sm" type="submit" value="login">
                    </form:form>
                </div>
            </div>
    
        </div>
    </div>

</body>
</html>
