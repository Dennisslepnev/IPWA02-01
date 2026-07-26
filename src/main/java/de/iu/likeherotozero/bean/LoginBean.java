package de.iu.likeherotozero.bean;

import java.io.Serializable;

import de.iu.likeherotozero.entity.User;
import de.iu.likeherotozero.enums.UserRole;
import de.iu.likeherotozero.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    private String username;
    private String password;
    private User loggedInUser;
    private String errorMessage;

    public String login() {

        loggedInUser = userService.authenticate(username, password);

        if (loggedInUser == null) {
            errorMessage = "Benutzername oder Passwort ist falsch.";
            return null;
        }

        errorMessage = null;
        password = null;

        if (loggedInUser.getRole() == UserRole.EDITOR) {
            return "approval?faces-redirect=true";
        }

        return "admin?faces-redirect=true";
    }

    public String logout() {

        loggedInUser = null;
        username = null;
        password = null;
        errorMessage = null;

        return "login?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public boolean isEditor() {
        return loggedInUser != null
                && loggedInUser.getRole() == UserRole.EDITOR;
    }

    public boolean isScientist() {
        return loggedInUser != null
                && loggedInUser.getRole() == UserRole.SCIENTIST;
    }

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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public String checkScientistAccess() {

        if (!isScientist()) {
            return "login?faces-redirect=true";
        }

        return null;
    }

    public String checkEditorAccess() {

        if (!isEditor()) {
            return "login?faces-redirect=true";
        }

        return null;
    }
}