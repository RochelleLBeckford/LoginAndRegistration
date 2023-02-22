package com.rochelle.login_reg.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.rochelle.login_reg.models.LoginUser;
import com.rochelle.login_reg.models.User;
import com.rochelle.login_reg.repositories.UserRepository;
    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // sevice talks to the repo
        // TO-DO: Additional validations!
        // check to see if the user trying to add already exists in DB 
        // looking for something that may or may not be there us Optional
        Optional<User> user = userRepository.findByEmail(newUser.getEmail());
        // checks to see if user is present already in DB
        // -> do not want to register a user that already exists 
        if (user.isPresent()) {
            // if the user is present -> return a message that the Email is already in use 
            result.rejectValue("email", "Email", "Email already registered.");
        }
        // if the user is not present will go to the next if statement 
        // checking to see if the password the user typed in matchs the confirm password they typed in
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            // if these are not equal we want to take the result and throw another error
            result.rejectValue("confirm", "Confirm", "Passwords must match");
        }
        if (result.hasErrors()) {
            // return null because we do not want to create a user 
            // -> do not want user in our main controller to be our user
            return null;
        }
        // ~ if get to this point than everything is ok 
        // -> the user is not present -> the passwords match -> no worries

        // we do not want to store any form of the password -> want to hash the password
                    // where we use the bcrypt -> takes the password and uses un-hackable algorithm to generate a hash
                    // different from encryption -> no key when it is hashed -> long string of jibberish
                    // -> hashpw takes the password we want to hash by taking the new user and get the password
                    // salt -> the longer the salt the more secure the hash -> salt too long algo is less effecient
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        // now want to modify the password before call the password -> sreplacing what they typed in to a hash
        newUser.setPassword(hashed);
        // save the user and returns the user onject
        return userRepository.save(newUser);
    }

    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
        // now going to use my login method inside of my service 
        // to log a user in -> compare the email & password that the user has given you to what is already inside their DB
        // -> if they are in DB we will pull them from the DB 

        // ~ use the method created below in login 
        // if do not find it -> then want to add to my result object
        if (!this.checkEmail(newLoginObject.getEmail())) {
            result.rejectValue("email", "noEmail", "Invalid Credentials");
        }
        // if the email does not exists I am done logging in
        if (result.hasErrors()) {
            return null;
        }

        //~ if get to this point -> we found the email 
        // -> now to check the password
        // -> query tthe DB for that email
        User user = userRepository.findByEmail(newLoginObject.getEmail()).orElse(null);
        // now want to validate their password
        // Take whatever user typed into the form 
        // -> compare login objects password to user password(hash password from the DB)
        if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
            // if they do not match
            result.rejectValue("password", "Password", "Invalid Credentials");
        }
        // if got here and the passwords do not match
        if (result.hasErrors()) {
            return null;
        }
        //~ if get to this point -> we found the email 
        // just going to return the user that i got from my DB
        return user;
    }

    //~ helper function to see if the user exists in the DB already 
    public boolean checkEmail(String email) {
        // querying a DB and giving it something that may or may not -> use optional object
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // means that we were able to retrieve the user from the DB
            return true;
        } else {
            // the user was not present and we return false
            return false;
        }
    }
}

