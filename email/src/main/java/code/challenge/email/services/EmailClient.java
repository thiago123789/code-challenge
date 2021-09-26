package code.challenge.email.services;

public interface EmailClient {
    void prepareEndSend(String recipient, String message, String subject);
}
