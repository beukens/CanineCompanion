package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.UserService;
import be.yapock.caninecompanion.pl.models.user.AuthDTO;
import be.yapock.caninecompanion.pl.models.user.CreateForm;
import be.yapock.caninecompanion.pl.models.user.LoginForm;
import be.yapock.caninecompanion.pl.models.user.PasswordResetRequestForm;
import jakarta.mail.MessagingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sends a create invitation to a user identified by their ID.
     *
     * @param id The ID of the user to send the invitation to.
     * @throws MessagingException If an error occurs while sending the invitation email.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public void sendCreateInvitation(@PathVariable long id) throws MessagingException {
        userService.sendCreateInvitation(id);
    }

    /**
     * Creates a new user using the provided token and form.
     *
     * @param form  The form containing the user creation details.
     * @param token The token used for authorization.
     * @throws IllegalAccessException if an error occurs while creating the user.
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping()
    public void create(@RequestBody CreateForm form, @RequestParam String token) throws IllegalAccessException {
        userService.create(token, form);
    }

    /**
     * Logs in the user using the provided login form.
     *
     * @param form The login form containing the username and password.
     * @return The authentication details of the logged-in user.
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form) {
        return userService.login(form);
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }

    /**
     * Resets the password for a user by sending a password reset request form.
     *
     * @param form The password reset request form containing the login and email of the user.
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/reset-password")
    public void resetPasswordRequest(@RequestBody PasswordResetRequestForm form) {
        userService.resetPasswordRequest(form);
    }
}
