package Practice.InterviewQuestion.Random.AtmProject;

import java.io.*;
import java.util.Objects;

public class CreditCardObjects {
    public static String fetchTheUser;

    public static String getFetchTheUser() {
        return fetchTheUser;
    }

    public void setFetchTheUser(String fetchTheUser) {
        CreditCardObjects.fetchTheUser = fetchTheUser;
    }

    String userName;
    long creditCardNumber;
    int cardPin;
    int depositedAmount;
    String cardType;
    String emailId;
    String password;
    static File fileLocation = new File("src/main/java/Practice/InterviewQuestion/Random/AtmProject");

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getEmailId(){
        return emailId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    public int getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(int depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    @Override
    public String toString() {
        return "userName : " + userName + '\'' +
                ", creditCardNumber : " + creditCardNumber +
                ", cardPin : " + cardPin +
                ", depositedAmount : " + depositedAmount;
    }
}
