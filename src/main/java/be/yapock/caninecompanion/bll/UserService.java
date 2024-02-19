package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.user.AuthDTO;
import be.yapock.caninecompanion.pl.models.user.CreateForm;
import be.yapock.caninecompanion.pl.models.user.LoginForm;
import jakarta.mail.MessagingException;

public interface UserService {
    void sendCreateInvitation(long id) throws MessagingException;
    void create(String token, CreateForm form) throws IllegalAccessException;
    AuthDTO login(LoginForm form);
}
