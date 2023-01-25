package Practice.InterviewQuestion.Random.AtmProject;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankXYZ extends Operations {
    static Scanner enter = new Scanner(System.in).useDelimiter("\n");
    public static void main(String[] args) throws IOException {
        try {
            BankXYZ bankXYZ = new BankXYZ();
            System.out.println("Select the user type!!!");
            System.out.print("1.\tBank Administrator.\n2.\tCustomer\n");
            int userType = enter.nextInt();
            switch (userType) {
                case 1 -> {
                    boolean adminLoop = false;
                    int adminAttempts = 0;
                    do {
                        adminAttempts++;
                        System.out.println("Please enter your user_Id");
                        String adminId = enter.next();
                        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                        boolean emailIdIsValid = adminId.matches(emailRegex);
                        if (emailIdIsValid) {
                            int passwordAttempts = 0;
                            boolean outOfLoopPassword = false;
                            do {
                                passwordAttempts++;
                                System.out.println("Please enter your password");
                                String adminPassword = enter.next();
                                String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
                                boolean passwordIsValid = adminPassword.matches(passwordRegex);
                                if (passwordIsValid) {
                                    if (validateAdminCredentials(adminId, adminPassword)) {
                                        outOfLoopPassword = true;
                                        adminLoop = true;
                                        System.out.println("Hello bank Administrator!!!");
                                        bankXYZ.administratorOperations();
                                    }
                                } else {
                                    System.out.println("Your password is invalid!!!");
                                }
                                if (passwordAttempts == 3 && !outOfLoopPassword) {
                                    System.out.println("You have exhausted your limit!!!");
                                    adminLoop = true;
                                    break;
                                }
                            } while (!outOfLoopPassword);
                        }
                        if (adminAttempts == 3 && !adminLoop) {
                            System.out.println("Sorry you have exhausted your limit!!!");
                            break;
                        }
                    } while (!adminLoop);
                }
                case 2 -> {
                    System.out.println("Are you our existing customer.");
                    System.out.print("1.\tYes.\n2.\tNo.\n");
                    int yesOrNo = enter.nextInt();
                    switch (yesOrNo) {
                        case 1 -> {
                            String emailId;
                            int emailIdAttempts = 0;
                            boolean validEmailId;
                            boolean outOfLoop = false;
                            do {
                                System.out.println("Please enter your email_Id");
                                emailId = enter.next();
                                emailIdAttempts++;
                                String emailIdRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                                validEmailId = emailId.matches(emailIdRegex);
                                if (validEmailId) {
                                    outOfLoop = true;
                                    boolean passwordOutOfLoop = false;
                                    boolean validPassword;
                                    int passwordAttempts = 0;
                                    do {
                                        passwordAttempts++;
                                        System.out.println("Please enter your password!!!");
                                        String password = enter.next();
                                        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
                                        validPassword = password.matches(passwordRegex);
                                        if (validPassword) {
                                            passwordOutOfLoop = true;
                                            if (validateUserNameEmailAddressAndPassword(emailId, password)) {
                                                System.out.println("Welcome back " + getFetchTheUser() + " !!!!");
                                                bankXYZ.customerOperations(getFetchTheUser(), emailId, password);
                                            } else {
                                                System.out.println("Please enter your credentials again" +
                                                        " if you are our existing customer");
                                                outOfLoop = false;
                                            }
                                        } else {
                                            System.out.println("You password is incorrect!!!");
                                        }
                                        if (passwordAttempts == 3) {
                                            System.out.println("You have exhausted your limit!!!");
                                            break;
                                        }
                                    } while (!passwordOutOfLoop);

                                } else {
                                    System.out.println("Your email-id is invalid!!!");
                                }
                                if (emailIdAttempts == 3) {
                                    System.out.println("You have exhausted your limit");
                                    break;
                                }
                            } while (!outOfLoop);
                        }

                        case 2 -> {
                            boolean newUserEnteredCorrectly = false;
                            int attempts = 0;
                            do {
                                attempts++;
                                System.out.println("Please Create your userName");
                                String userName = enter.next();
                                boolean outOfLoop = false;
                                int emailIdAttempts = 0;
                                do {
                                    System.out.println("Please create your email-Id");
                                    String emailId = enter.next();
                                    emailIdAttempts++;
                                    String regexForEmail = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                                    boolean emailIdMatchesWithTheRegex = emailId.matches(regexForEmail);
                                    if (emailIdMatchesWithTheRegex) {
                                        boolean outOfLoopPassword = false;
                                        int passwordAttempts = 0;
                                        do {
                                            System.out.println("Please create your own unique password!!!");
                                            String password = enter.next();
                                            passwordAttempts++;
                                            String regexForPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
                                            boolean passwordMatchesWithRegex = password.matches(regexForPassword);
                                            if (passwordMatchesWithRegex) {
                                                if (checkIfUserNameExist(emailId)) {
                                                    System.out.println("Dear " + userName + " the given credentials is already registered to our bank" +
                                                            " kindly put other credentials!!!");
                                                    System.out.println("Would you like to perform any operation" +
                                                            " today " + userName + " !!!");
                                                    System.out.print("1.\tYes.\n2.\tNo.\n");
                                                    int yesOfNo = enter.nextInt();
                                                    switch (yesOfNo) {
                                                        case 1 -> {
                                                            bankXYZ.customerOperations(userName, emailId, password);
                                                            outOfLoopPassword = true;
                                                            outOfLoop = true;
                                                            newUserEnteredCorrectly = true;
                                                        }
                                                        case 2 -> {
                                                            System.out.println("It was nice having you " + userName);
                                                            outOfLoopPassword = true;
                                                            outOfLoop = true;
                                                            newUserEnteredCorrectly = true;
                                                        }
                                                    }

                                                } else {
                                                    System.out.println("Congratulations your account has been created " + userName);
                                                    newUserEnteredCorrectly = true;
                                                    bankXYZ.customerOperations(userName, emailId, password);
                                                    outOfLoopPassword = true;
                                                    outOfLoop = true;
                                                }

                                            }
                                            if (passwordAttempts == 3 && !outOfLoopPassword) {
                                                System.out.println("You have exhausted your limits!!!");
                                                break;
                                            }
                                        } while (!outOfLoopPassword);
                                    }
                                    if (emailIdAttempts == 3 && !outOfLoop) {
                                        System.out.println("You have exhausted your limit!!!");
                                        break;
                                    }
                                } while (!outOfLoop);
                                if (attempts == 3 && !newUserEnteredCorrectly) {
                                    System.out.println("You have exhausted your limit!!!");
                                }

                            } while (!newUserEnteredCorrectly);

                        }

                        default -> System.out.println("Sorry no such option available!!!");
                    }
                }
                default -> System.out.println("Sorry no such user available!!!");
            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }finally {
            enter.close();
        }
    }
}
//apply for a new credit card -> I can list out the cards as "gold", "platinum" and "diamond"
