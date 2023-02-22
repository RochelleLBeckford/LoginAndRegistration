package com.rochelle.login_reg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
// import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rochelle.login_reg.models.LoginUser;
import com.rochelle.login_reg.models.User;
import com.rochelle.login_reg.services.UserService;

// designate this as a controller
@Controller
public class MainController {
    
    // @GetMapping("/")
    // public String index() {
    //     return "index.jsp";
    // }

    /* 
    ~ Need 2 models 
        -> one for User -> use this to store user in our DB
        -> one for LoginUser -> temp one we need long enough to do the comparison
    ~ When registering a you are creating a new user in my DB
        -> making a user object and putting it in our database 
        -> then they can login
    ~ Login -> you are checking if they already exists
        -> validating them 
        -> make sure email provided matches what is in the database
        -> password user types in comparing them to -> depends if the password is encrypted 
            -> the password must be encrypted -> we never store the passwords
            -> want to temporaroly hold it then throw it away
            -> always want to hash passwords and then throw it away 
                -> compare the password that they entered to the hash that is in our DB
                -> the only place the password should exist is in the users mind
            -> hold the password long enough to see if it matches the hash in our DB when they login and then throw it away 
    ~ There is no id or entity or table name 
    
    */
    // Add once service is implemented:
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        // need these attributes because of the data binding 
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(
        @Valid 
        @ModelAttribute("newUser") User newUser, 
        BindingResult result, 
        Model model, 
        HttpSession session) {
        //need to make a user 
        // -> take in the new user I am making and pass in the result object 
        // controller talks to the service
        User user = userService.register(newUser, result);
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        // logging them in means just putting them in session 
                                // want to set the user id from register method to this user id
                                // -> use the getId to retrieve it 
        session.setAttribute("userId", user.getId());
        // just to see setting the password to session
        // session.setAttribute("password", user.getPassword());
        return "redirect:/welcome";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // -> gonna pass in the new login user from the data binding and pass in result
        User user = userService.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        // -> to log a user in use session -> set the attribute
        // set userId to the user we get back from the DB -> set it to their id
        session.setAttribute("userId", user.getId());
        // return where ever want to send them
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome.jsp";
    }

    // Loging out woould be clearing session
    @GetMapping("/logout")
    // we need session
    public String logout(HttpSession session) {
        // now to clear session
        // 1 way -> session.setAttribute("userId", null);
        // 22nd way -> this clears out everything 
        session.invalidate();
        return "redirect:/";
    }

    /* 
    
    ~ DB will get kind of clutered if you keep adding models or changing your attributes and the like.
    -> best to drop the schema and then remake it
    -> if you are having issues do not be afraid to drop the schema and remake it -> it can happen so just do this when it does occur 
    
    ~ refresh DB now see in MySQL then will see the table but it is empty
    ~ test and register a new user and the refresh DB to see the new user
    -> registration is just adding user to the database 
    */
}
