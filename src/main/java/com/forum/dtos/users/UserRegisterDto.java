package com.forum.dtos.users;

import com.forum.constants.Constants;
import com.forum.dtos.validations.IsEmailRegistered;
import com.forum.dtos.validations.IsUsernameTaken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @Size(min = 4,max = 20,message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;

    @Size(min = 4,max = 30,message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    @IsEmailRegistered
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
