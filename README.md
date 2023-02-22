# Login and Registration
<!-- can have readme preview open as well to see how it will appear -->
<!-- - this is a bullet -->

<!-- ## this is a sub heading -->
### Build a Spring application that focuses on Login and Reg 
- Build an application that requires both user authentication and validations
- Add server-side validations in addition to model-level validations
- Implement authentication logic
- Use 'Optionals' to check if a user exists
- Import and use 'BCrypt' to create hashes and compare hashed strings against the database
- Use and manipulate transient member variables and non-entity classes
- Handle user logout and active session status
- Use session data to pull the current user's information

1. Create User and LoginUser models including all model-level validation annotations

2. Add server-level authentication

3. Thoroughly test validations and that validation messages show to the user, including on a second submission.

4. Add a logout route to the controller and test to be sure a user cannot access the success page after having logged out.

<!-- 
Test: Show how to add a web browser
[website](https://www.google.com) -->


 - ## Checklist
- [ ] update [application.properties](/src/main/resources/application.properties)
<!-- how to put in code blocks us ```-->
```
# Where are jsp files? HERE!
spring.mvc.view.prefix=/WEB-INF/
# Data Persistence
#<!-- after the '/' is <<WHATEVER_YOUR_SCHEMA_NAME>> that you created in MySQL Workbench -->
spring.datasource.url=jdbc:mysql://localhost:3306/login_reg
spring.datasource.username=root
spring.datasource.password=rootroot
spring.jpa.hibernate.ddl-auto=update
# For Update and Delete method hidden inputs
spring.mvc.hiddenmethod.filter.enabled=true
```


- ### After adding this to my pom fie it will ask if you want to update -> always say yes
- [ ] [pom.xml](pom.xml)
```
		<!-- 
		& add the two dependency files here 
		-> after save click yes so Maven can update the dependencies that we can use
		--> 

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
		<dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>jstl</artifactId>
        </dependency>

		<!-- & Bootstrap -->
	    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>webjars-locator</artifactId>
        <version>0.30</version>
		</dependency>
		
		<!-- BOOTSTRAP DEPENDENCIES -->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.0.1</version>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.6.0</version>
		</dependency>

        <!-- & DEPENDENCIES FOR DISPLAYING JSPS AND USING JSTL TAGS -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <!-- & DEPENDENCY FOR USING VALIDATION ANNOTATIONS -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- & Bootswatch -->
		<dependency>
			<groupId>org.webjars.npm</groupId>
			<artifactId>bootswatch</artifactId>
			<version>5.2.3</version>
		</dependency>

        <!-- & DEPENDENCY FOR USING BCRYPT & -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
```


- [ ] add [index.jsp](src/main/webapp/WEB-INF/index.jsp)
<!-- 
when you make this 
    -> can click it and vs code will say the file is not there 
    -> can create file
-> make sure in the right place -->
```
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
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>

</body>
</html>

```
- [ ] add a schema in MySQL Workbench


<!-- 
-> Always generate the MainController by hand 
-> packages will differ for each directory 
-> this will make sure the file is in the right place
-->
- [ ] add controller [MainController.java](src/main/java/com/rochelle/login_reg_demo/controllers/MainController.java)


- add this to the MainController.java
```
//MainController.java
    @Autowired UserService userService;
```

- [ ] add model [Whatever.java](src/main/java/com/rochelle/login_reg_demo/models/User.java)
    - To connect service with controller
        - [ ] add `@Entity` and `@Table` `@Id` `@GeneratedValue` to the model

- [ ] add a 2nd model [Whatever.java](src/main/java/com/rochelle/login_reg_demo/models/LoginUser.java)

<!-- need to add a service folder and file with whatever name you want -->
- [ ] add a [WhateverService.java](src/main/java/com/rochelle/login_reg_demo/services/UserService.java)

<!-- add service annotation -->
- add 
```
//ExpenseService.java
@Autowired UserRepository userRepository;
```

<!-- need to add a repository folder and file with whatever name you want -->
- To connect service with repository
- [ ] add a [WhateverRepository.java](src/main/java/com/rochelle/login_reg_demo/repositories/UserRepository.java)

# LoginAndRegistration
