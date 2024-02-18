package be.yapock.caninecompanion.bll;

import jakarta.mail.MessagingException;

public interface UserService {
    void sendCreateInvitation(long id) throws MessagingException;
    void create();
}
