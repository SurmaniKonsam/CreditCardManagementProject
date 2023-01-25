package Practice.InterviewQuestion.Random.AtmProject;

import java.io.IOException;

public interface ParentOperations {
    void customerOperations(String userName,String emailId,String password) throws IOException;
    void administratorOperations() throws IOException;
    void applyForNewCard(String useName,String emailId,String password) throws IOException;
    void viewBalanceBasedOnUserNameAndCreditCardNumber(String name,String emailId,String password) throws IOException;
    void closeBlockCard(String userName,String emailId,String password) throws IOException;
    void logOut() throws IOException;
    void viewIssuedCards(String emailId,String password) throws IOException;
    void viewBlockedCard() throws IOException;
    void purchaseItems(String emailId,String passsword) throws IOException;
}
