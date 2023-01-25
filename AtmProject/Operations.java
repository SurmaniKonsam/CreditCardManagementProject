package Practice.InterviewQuestion.Random.AtmProject;

import java.io.*;
import java.util.*;

 public class Operations extends CreditCardObjects implements ParentOperations{
    static Scanner enter = new Scanner(System.in).useDelimiter("\n");
    static File fileLocation = new File("src/main/java/Practice/InterviewQuestion/Random/AtmProject/BankXYZFolder");
    static File sourceToWrite = new File(fileLocation,"creditCardUser.csv");
    static File blockCardToWrite = new File(fileLocation,"blockedCards.csv");
    static File adminCredentials = new File(fileLocation,"AdministratorCredentials.csv");



    public void operationsAvailable(){
        System.out.println("1.\tApply for new credit card.");
        System.out.println("2.\tView balance.");
        System.out.println("3.\tclose/block credit-card.");
        System.out.println("4.\tBuy items."); //level-2
        System.out.println("5.\tView all customers data.");
        System.out.println("6.\tLog-Out.");
    }


    @Override
    public void customerOperations(String userName,String emailId,String password) throws IOException, InputMismatchException {
        int continueOrNot = 0;
        int choices;
        boolean logOut = false;
        try{
            do{
                System.out.println("Select the operation listed "+userName);
                this.operationsAvailable();
                System.out.println("Choose one of them!!!");
                choices = enter.nextInt();
                switch (choices){
                    case 1-> {
                        this.applyForNewCard(userName,emailId,password);
                        System.out.println("Do you want to continue "+userName);
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        continueOrNot = enter.nextInt();
                        if(continueOrNot==2){
                            logOut = true;
                        }
                    }
                    case 2 -> {
                        this.viewBalanceBasedOnUserNameAndCreditCardNumber(userName,emailId,password);
                        System.out.println("Do you want to continue "+userName);
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        continueOrNot = enter.nextInt();
                        if(continueOrNot==2){
                            logOut = true;
                        }
                    }
                    case 3 -> {
                        this.closeBlockCard(userName,emailId,password);
                        System.out.println("Do you want to continue "+userName);
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        continueOrNot = enter.nextInt();
                        if(continueOrNot==2){
                            logOut = true;
                        }
                    }
                    case 4 -> {
                        this.purchaseItems(emailId,password);
                        System.out.println("Do you want to continue "+userName);
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        continueOrNot = enter.nextInt();
                        if(continueOrNot==2){
                            logOut = true;
                        }
                    }
                    case 5 -> {
                        this.readCardUser();
                        System.out.println("Do you want to continue "+userName);
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        continueOrNot = enter.nextInt();
                        if(continueOrNot==2){
                            logOut = true;
                        }
                    }

                    case 6 -> {
                        System.out.println("It was nice having you "+userName+" have a great day!!!");
                        logOut = true;
                    }
                    default -> System.out.println("No such operations available "+userName);
                }
                if(continueOrNot==2){
                    System.out.println("It was nice having you "+userName+" have a great day!!!");
                }

            }while(!logOut);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void administratorOperations() throws IOException {
        boolean adminLogOut = false;
        try {
            do{
                System.out.println("Select the operations to perform Administrator.");
                System.out.println("1.\tView all customers data.");
                System.out.println("2.\tView all issued cards.");
                System.out.println("3.\tAdd new customers.");
                System.out.println("4.\tIssue new credit card.");
                System.out.println("5.\tClose/Block cards");
                System.out.println("6.\tView block cards.");
                System.out.println("7.\tLog-Out.");
                int choices = enter.nextInt();
                switch (choices){
                    case 1 -> {
                        this.readCardUser();
                        System.out.println("Do you want to continue or not Administrator!!!");
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        choices = enter.nextInt();
                        if(choices==2){
                            adminLogOut = true;
                        }
                    }
                    case 2 -> {
                        System.out.println("Please enter customer email_Id");
                        String emailId = enter.next();
                        System.out.println("Please enter the customer password");
                        String password = enter.next();
                        this.viewIssuedCards(emailId,password);
                        System.out.println("Do you want to continue or not Administrator!!!");
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        choices = enter.nextInt();
                        if(choices==2){
                            adminLogOut = true;
                        }
                    }
                    case 3,4 -> {
                        System.out.println("Please enter customer User_Name");
                        String userName = enter.next();
                        int emailIdAttempts = 0;
                        boolean emailIdLoop = false;
                        do{
                            emailIdAttempts++;
                            System.out.println("Please create customer email_Id");
                            String emailId = enter.next();
                            String emailIdRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                            boolean validEmailId = emailId.matches(emailIdRegex);
                            int passwordAttempts = 0;
                            boolean validPassword;
                            boolean passwordLoop = false;
                            if(validEmailId)
                            {
                                do {
                                    passwordAttempts++;
                                    System.out.println("Please create customer Password");
                                    String password = enter.next();
                                    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
                                    validPassword = password.matches(passwordRegex);
                                    if (validPassword) {
                                        this.applyForNewCard(userName,emailId,password);
                                        passwordLoop = true;
                                        emailIdLoop = true;
                                    }else{
                                        System.out.println("You password type is invalid");
                                    }
                                    if(passwordAttempts==3 && !passwordLoop){
                                        System.out.println("You have exhausted your attempt of 3");
                                        emailIdLoop = true;
                                        break;
                                    }
                                }while(!passwordLoop);
                            }
                            else{
                                System.out.println("EmailId is not valid");
                            }
                            if(emailIdAttempts==3 && !emailIdLoop){
                                System.out.println("Dear Administrator you have exhausted your attempt of 3");
                                break;
                            }
                        }while(!emailIdLoop);
                        System.out.println("Do you want to continue or not Administrator!!!");
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        choices = enter.nextInt();
                        if(choices==2){
                            adminLogOut = true;
                        }
                    }
                    case 5 -> {
                        System.out.println("Please enter customer User_Name");
                        String userName = enter.next();
                        System.out.println("Please enter customer email_Id");
                        String emailId = enter.next();
                        System.out.println("Please enter customer Password");
                        String password = enter.next();
                        this.closeBlockCard(userName,emailId,password);
                        System.out.println("Do you want to continue or not Administrator!!!");
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        choices = enter.nextInt();
                        if(choices==2){
                            adminLogOut = true;
                        }
                    }
                    case 6 -> {
                        this.viewBlockedCard();
                        System.out.println("Do you want to continue or not Administrator!!!");
                        System.out.print("1.\tYes.\n2.\tNo.\n");
                        choices = enter.nextInt();
                        if(choices==2){
                            adminLogOut = true;
                        }
                    }
                    case 7 -> {
                        System.out.println("It was nice having you administrator!!!");
                        adminLogOut = true;
                    }
                }
                if(choices==2){
                    System.out.println("It was nice having you administrator!!!");
                }
            }while (!adminLogOut);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void applyForNewCard(String userName,String emailId,String password) throws IOException {
        System.out.println("Applying for a new card");
        System.out.println("Please enter your age!!!");
        try{
            int age = enter.nextInt();
            if (age <= 18) {
                System.out.println("Sorry you are not eligible to create card");
            } else {
                System.out.println("Hello "+userName+"!!!!");
                System.out.println("Number of credentials with the same id " +emailId+ " " +
                        "is : " + this.countExistingEmailIds(emailId));
                int countExistingUser = this.countExistingEmailIds(emailId);
                if (countExistingUser >= 5) {
                    System.out.println("Sorry maximum 5 credit card can be made under same "+emailId+" id");
                } else {
                    long cardNumber;
                    int attempts = 0;
                    int pinAttempts = 0;
                    boolean outOfLoop = false;
                    do {
                        System.out.println("Please enter 12 digit card number to create your card");
                        cardNumber = enter.nextLong();
                        attempts++;
                        if (this.countCardNumberDigitsCount(cardNumber)) {
                            //if true cardNumber doesn't exist.
                            if (this.creditCardNumberDoesNotExist(cardNumber)) {
                                outOfLoop = true;
                                super.setCreditCardNumber(cardNumber);
                                int pin;
                                do {
                                    System.out.println("Please enter 4 digit pins for your card");
                                    pin = enter.nextInt();
                                    pinAttempts++;
                                    if (this.countPinDigitCount(pin)) {
                                        super.setCardPin(pin);
                                        int deposit;
                                        int depositAttempt = 0;
                                        do {
                                            System.out.println("Please deposit an initial amount of 5000 or more into your account!!!");
                                            deposit = enter.nextInt();
                                            depositAttempt++;
                                            if (deposit >= 5000) {
                                                super.setDepositedAmount(deposit);
                                                FileWriter  fileWriter = new FileWriter(sourceToWrite,true);
                                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                                bufferedWriter.write(userName + "," +emailId + "," +
                                                        "" + password + "," + super.getCreditCardNumber() + "," + super.getCardPin() + "," +
                                                        super.getDepositedAmount());
                                                bufferedWriter.flush();
                                                bufferedWriter.newLine();
                                                bufferedWriter.close();
                                                System.out.println("Thank you " + userName + ", Your account has been" +
                                                        " successfully created");
                                            }
                                            if (depositAttempt == 5) {
                                                System.out.println("You have exhausted your limit");
                                                break;
                                            }
                                        } while (deposit < 5000);
                                    } else if (pinAttempts == 5) {
                                        System.out.println("You have exhausted your limit");
                                        break;
                                    }
                                } while (!countPinDigitCount(pin));
                            }
                        }
                        if(attempts==3 && !outOfLoop){
                            System.out.println("You have exhausted your limit!!!");
                            break;
                        }
                    }while(!outOfLoop);
                }
            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }



    public void readCardUser(){
        //used try with resources
        try (FileReader fileReader = new FileReader(sourceToWrite); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
    }

    public int countExistingEmailIds(String emailId) throws IOException {
        BufferedReader bufferedReader = null;
        int countExistingEmailIds = 0;
        try{
            FileReader fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] intoArray;
            while ((line = bufferedReader.readLine()) != null) {
                intoArray = line.split(",");
                if (Objects.equals(intoArray[1], emailId)) {
                    countExistingEmailIds++;
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            //with IO exception fileNotFound exception is also caught.
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        return countExistingEmailIds;
    }

    @Override //done. to view balance for customer required pin to do so.
    public void viewBalanceBasedOnUserNameAndCreditCardNumber(String name,String emailId,String password) throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            if(validateUserNameEmailAddressAndPassword(emailId,password)){
                String line;
                int countSerialNo = 0;
                int choices;
                long creditCardNumber;
                String[] intoArray;
                System.out.println("Select your available card to view balance");
                HashMap<Integer,Long> cardNumberMappedToSerialNo = new HashMap<>();
                HashMap<Integer, Integer> balanceMappedWithSerialNo = new HashMap<>();
                while((line=bufferedReader.readLine())!=null){
                    intoArray = line.split(",");
                    if(Objects.equals(intoArray[1], emailId) && Objects.equals(intoArray[2], password)){
                        countSerialNo++;
                        balanceMappedWithSerialNo.put(countSerialNo, Integer.parseInt(intoArray[5]));
                        cardNumberMappedToSerialNo.put(countSerialNo,Long.parseLong(intoArray[3]));
                        System.out.println(countSerialNo + ".\t" + intoArray[3]);
                    }
                }
                System.out.println("Please select the serial_No. listed above.");
                choices = enter.nextInt();
                for (Map.Entry<Integer, Integer> entry : balanceMappedWithSerialNo.entrySet()){
                    if (entry.getKey() == choices){
                        creditCardNumber = cardNumberMappedToSerialNo.get(choices);
                        boolean rightPin = false;
                        int pinAttempts = 0;
                        int pin;
                        int cardNumberPin = getCardPin(creditCardNumber);
                        System.out.println("Your card is :: "+creditCardNumber);
                        do{
                            System.out.println("Please enter the 4 digit pin for the card thus chosen!!!");
                            pin = enter.nextInt();
                            pinAttempts++;
                            if(this.countPinDigitCount(pin)){
                                if(pin==cardNumberPin){
                                    System.out.println(name+ " you can spend up-to " + entry.getValue());
                                    rightPin = true;
                                }
                            }
                            if(pinAttempts==3 && !rightPin){
                                System.out.println("You have exhausted your limit!!!");
                                break;
                            }

                        }while(pin!=cardNumberPin);
                    }
                }
            }
            else{
                System.out.println("Sorry "+name+" your card has not been applied to us");
                System.out.println("Would you like to create a new card");
                System.out.print("1.\tYes.\n2.\tNo.\n");
                int yesOrNo = enter.nextInt();
                switch (yesOrNo){
                    case 1-> this.applyForNewCard(name,emailId,password);
                    case 2 -> System.out.println("Thank you for visiting us "+name);
                    default -> System.out.println("No such options available!!!");
                }
            }

        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        System.out.println();
    }



    @Override
    public void viewIssuedCards(String emailId,String password) throws IOException {
        FileReader fileReader = new FileReader(sourceToWrite);
        //try-with resources.
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String[] intoArray;
            boolean readUserName = false;
            while ((line = bufferedReader.readLine()) != null) {
                intoArray = line.split(",");
                if (Objects.equals(emailId, intoArray[1]) && Objects.equals(password, intoArray[2])) {
                    System.out.println("Issued cards : " + intoArray[3]);
                    readUserName = true;
                }
            }
            if (!readUserName) {
                System.out.println("No such user present!!!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override //done.
    public void closeBlockCard(String userName,String emailId,String password) throws IOException {
        if(whetherTheUserExist(emailId,password)) {
            System.out.println("Are you sure you want to block your card!!!");
            System.out.print("1.\tYes.\n2.\tNo.\n");
            int yesOrNo = enter.nextInt();
            switch (yesOrNo) {
                case 1 -> {
                    FileReader fileReader = new FileReader(sourceToWrite);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    int countSerialNo = 0;
                    int choices;
                    String[] intoArray;
                    System.out.println("These are the cards under your credentials "+userName+". Kindly please choose one of them to block");
                    HashMap<Integer, Long> cardNumberMappedWithSerialNo = new HashMap<>();
                    while ((line = bufferedReader.readLine()) != null) {
                        intoArray = line.split(",");
                        if (Objects.equals(intoArray[1],emailId) && Objects.equals(intoArray[2],password)) {
                            countSerialNo++;
                            cardNumberMappedWithSerialNo.put(countSerialNo, Long.parseLong(intoArray[3]));
                            System.out.println(countSerialNo + ".\t" + intoArray[3]);
                        }
                    }
                    long cardNumber = 0;
                    choices = enter.nextInt();
                    int cardPin = 0;
                    for (Map.Entry<Integer, Long> entry : cardNumberMappedWithSerialNo.entrySet()) {
                        if (entry.getKey() == choices) {
                            cardNumber = entry.getValue();
                            cardPin = getCardPin(cardNumber);
                        }
                    }
                    int pin;
                    int pinAttempts = 0;
                    boolean outOfLoop = false;
                    do {
                        System.out.println("Please enter your 4 digit card pin!!!");
                        pin = enter.nextInt();
                        pinAttempts++;
                        if (pin == cardPin) {
                            fileReader = new FileReader(sourceToWrite);
                            bufferedReader = new BufferedReader(fileReader);
                            List<String> cardData = new ArrayList<>();
                            FileWriter fileWriterBlock = new FileWriter(blockCardToWrite, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriterBlock);
                            while ((line = bufferedReader.readLine()) != null) {
                                intoArray = line.split(",");
                                try {
                                    if (!Objects.equals(intoArray[3], String.valueOf(cardNumber))) {
                                        cardData.add(line);
                                    } else {
                                        bufferedWriter.write(line);
                                        bufferedWriter.newLine();
                                        bufferedWriter.flush();
                                    }
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                                    System.out.println(e.getMessage());
                                }finally {
                                    bufferedWriter.close();
                                    bufferedReader.close();
                                }
                            }
                            FileWriter fileWriter = null;
                            BufferedWriter bufferedWriter1 = null;
                            System.out.println("Your card has been successfully blocked!!!");
                            try{
                                fileWriter = new FileWriter(sourceToWrite, false);
                                bufferedWriter1 = new BufferedWriter(fileWriter);
                                for (String readCardNumber : cardData) {
                                    new FileWriter(sourceToWrite, true);
                                    bufferedWriter1.write(readCardNumber);
                                    bufferedWriter1.newLine();
                                    bufferedWriter1.flush();
                                }
                            }catch (FileNotFoundException e){
                                System.out.println(e.getMessage());
                            }finally {
                                assert fileWriter != null;
                                fileWriter.close();
                                fileWriterBlock.close();
                                bufferedReader.close();
                                bufferedWriter.close();
                            }
                            outOfLoop = true;
                        }
                        if (pinAttempts == 3 && !outOfLoop) {
                            System.out.println("You have exhausted your limit");
                            break;
                        }
                    } while (!outOfLoop);
                }
                case 2 -> System.out.println("It was nice having you "+userName+" have a great day ahead!!!");
                default -> System.out.println("Sorry dear customer there are no such options available");
            }
        }
        else{
            System.out.println("Sorry "+userName+" is not part of our bank!!!");
        }
    }

    @Override //done. Log-Out
    public void logOut() throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            System.out.println("Enter your name please!!!");
            String name = enter.next();
            String line;
            String[] intoArray;
            boolean flag = false;
            while((line=bufferedReader.readLine())!=null){
                intoArray = line.split(",");
                if(Objects.equals(intoArray[0], name)){
                    System.out.println(intoArray[0]+" it was nice having you we'll meet again");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Sorry "+name+" you are not part of our BankXYZ!!!");
            }
        }catch (IOException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }


    }

    public boolean whetherTheUserExist(String emailId,String password) throws IOException {
        FileReader fileReader = new FileReader(sourceToWrite);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] intoArray;
        boolean flag = false;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                intoArray = line.split(",");
                if (Objects.equals(intoArray[1], emailId) && Objects.equals(intoArray[2], password)){
                    flag = true;
                    break;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            bufferedReader.close();
        }
        return flag;
    }

    @Override
    public void viewBlockedCard() throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(blockCardToWrite);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException | StringIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }

    }

    @Override
    public void purchaseItems(String emailId,String password) throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] intoArray;
            int countCreditCards = 0;
            System.out.println("These are the cards under your name. Kindly please choose one of" +
                    " them to have your purchase");
            HashMap<Integer,Long> cardNumberMappedWithSerialNo = new HashMap<>();
            while((line=bufferedReader.readLine())!=null){
                intoArray =  line.split(",");
                if(Objects.equals(intoArray[1], emailId) && Objects.equals(intoArray[2],password)){
                    countCreditCards++;
                    cardNumberMappedWithSerialNo.put(countCreditCards, Long.parseLong(intoArray[3]));
                    System.out.println(countCreditCards + ".\t" + intoArray[3]);
                }
            }
            long cardNumber = 0;
            int choices = enter.nextInt();
            int cardPin = 0;
            for (Map.Entry<Integer,Long> entry : cardNumberMappedWithSerialNo.entrySet()) {
                if (entry.getKey() == choices) {
                    cardNumber = entry.getValue();
                    cardPin = getCardPin(cardNumber);
                }
            }
            int pin;
            int countAttempts = 0;
            boolean outerMostLoop = false;
            do{
                countAttempts++;
                System.out.println("Please enter your 4 digit pin");
                pin = enter.nextInt();
                boolean outOfLoop = false;
                if(countPinDigitCount(pin)){
                    int matchedPinWithCardNumberCount = 0;
                    do {
                        matchedPinWithCardNumberCount++;
                        if(pin==cardPin){
                            outerMostLoop = true;
                            String readLineAgain;
                            fileReader = new FileReader(sourceToWrite);
                            bufferedReader = new BufferedReader(fileReader);
                            int spendableAmount = 0;
                            while((readLineAgain=bufferedReader.readLine())!=null){
                                intoArray = readLineAgain.split(",");
                                if(cardNumber==Long.parseLong(intoArray[3])){
                                    spendableAmount = Integer.parseInt(intoArray[5]);
                                    System.out.println("You have "+spendableAmount+" balance in your account.");
                                }
                            }
                            System.out.println("We have following items available with us!!!");
                            System.out.print("1.\tGoggles\tRs-500\n");
                            System.out.print("2.\tJeans\tRs-1500\n");
                            System.out.print("3.\tBelt\tRs-950\n");
                            int choice = enter.nextInt();
                            int depositAmount = 0;
                            switch (choice){
                                case 1 -> {
                                    System.out.println("Dear Customer how many goggles you want to purchase?");
                                    int numbers = enter.nextInt();
                                    int totalCost;
                                    if(numbers>=1 && numbers<=10){
                                        totalCost = numbers*500;
                                        System.out.println("Your total goggles cost : "+totalCost);
                                        if(totalCost<=spendableAmount){
                                            updatePrice(totalCost,cardNumber, true,depositAmount);
                                            System.out.println("Thank you for shopping with BankXYZ "+userName);
                                            outOfLoop = true;
                                        }
                                        else{
                                            System.out.println("You have insufficient amount in your card!!!");
                                            System.out.println("Dear customer you will be given 3 attempts to deposit the " +
                                                    "required amount for transaction");
                                            int depositAttempts = 0;
                                            do {
                                                depositAttempts++;
                                                System.out.println("Please enter your require amount");
                                                depositAmount = enter.nextInt();
                                                if (depositAmount > spendableAmount && depositAmount > totalCost) {
                                                    updatePrice(totalCost, cardNumber, false, depositAmount);
                                                    outOfLoop = true;
                                                }
                                                if(depositAttempts==3 && !outOfLoop){
                                                    System.out.println("You have exhausted your attempts");
                                                    break;
                                                }
                                            }while(depositAmount<totalCost);
                                        }
                                        //if totalCost < amount available in the credit-card the customer won't be able to purchase.
                                    }else{
                                        System.out.println("Please provide an appropriate quantity");
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Dear Customer how many jeans you want to purchase?");
                                    int numbers = enter.nextInt();
                                    int totalCost;
                                    if(numbers>=1 && numbers<=10){
                                        totalCost = numbers*1500;
                                        System.out.println("Your total jeans cost : "+totalCost);
                                        if(totalCost<spendableAmount){
                                            updatePrice(totalCost,cardNumber,true,depositAmount);
                                            System.out.println("Thank you for shopping with BankXYZ");
                                        }else{
                                            System.out.println("You have insufficient amount in your card!!!");
                                            System.out.println("Please enter your require amount");
                                            depositAmount = enter.nextInt();
                                            updatePrice(totalCost,cardNumber,false,depositAmount);
                                        }
                                        outOfLoop = true;
                                    }else{
                                        System.out.println("Kindly please provide an appropriate quantity");
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Dear Customer how many Belt you want to purchase?");
                                    int numbers = enter.nextInt();
                                    int totalCost;
                                    if(numbers>1 && numbers<1000){
                                        totalCost = numbers*950;
                                        System.out.println("Your total belt cost : "+totalCost);
                                        if(totalCost<spendableAmount){
                                            updatePrice(totalCost,cardNumber,true,depositAmount);
                                            System.out.println("Thank you for shopping with BankXYZ");
                                        }else{
                                            System.out.println("You have insufficient amount in your card!!!");
                                            System.out.println("Please enter your require amount");
                                            depositAmount =  enter.nextInt();
                                            updatePrice(totalCost,cardNumber,false,depositAmount);
                                        }
                                        outOfLoop = true;
                                    }else{
                                        System.out.println("Kindly please provide an appropriate quantity");
                                    }
                                }
                                default -> System.out.println("No such item available!!!");
                            }
                        }
                        if(matchedPinWithCardNumberCount==3 && !outOfLoop){
                            System.out.println("You have exhausted your limit why");
                            break;
                        }
                    }while(!outOfLoop);
                }
                if(countAttempts==3 && !outOfLoop){
                    System.out.println("You have exhausted your limit");
                    break;
                }
            }while(!outerMostLoop);
        }catch (IOException | ArithmeticException | ArrayIndexOutOfBoundsException | InputMismatchException e){
            System.out.println(e.getMessage());
        }finally {
            bufferedReader.close();
        }
    }

     //I can use this for deposit as well as for spending.
    public void updatePrice(int price,Long cardNumber,boolean subtract,int depositAmount) throws IOException {
        List<String> dataToUpdate = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter;
        BufferedWriter bufferedWriter = null;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            fileWriter = new FileWriter(sourceToWrite,true);
            String line;
            String[] intoArray;
            int updatingOperation;
            while ((line=bufferedReader.readLine())!=null){
                intoArray = line.split(",");
                if(Long.parseLong(intoArray[3])==cardNumber){
                    if(!subtract){
                        updatingOperation = Integer.parseInt(intoArray[5])+depositAmount;
                        updatingOperation = updatingOperation-price;
                    }else{
                        updatingOperation = Integer.parseInt(intoArray[5])-price;
                    }
                    intoArray[5] = String.valueOf(updatingOperation);
                    dataToUpdate.add(intoArray[0]+","+intoArray[1]+","+intoArray[2]+","+intoArray[3]+","+intoArray[4]+","+
                            intoArray[5]);
                }else{
                    dataToUpdate.add(line);
                }
            }
            new FileWriter(sourceToWrite,false);
            new FileWriter(sourceToWrite,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String readData : dataToUpdate) {
                bufferedWriter.write(readData);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (IOException | ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert bufferedReader != null;
            bufferedReader.close();
            assert bufferedWriter != null;
            bufferedWriter.close();
        }
    }

    public boolean countCardNumberDigitsCount(long cardNumber){
        int countDigits = 0;
        try{
            while(cardNumber!=0){
                countDigits++;
                cardNumber = cardNumber/10;
            }
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }

        return countDigits == 12;
    }

    public boolean countPinDigitCount(int pin){
        int countPinDigits = 0;
        try {
            while (pin != 0) {
                countPinDigits++;
                pin = pin / 10;
            }
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        return countPinDigits == 4;
    }


    //true means cardNumber doesn't exist.
     //false means cardNumber exist.
    public boolean creditCardNumberDoesNotExist(long cardNumber) throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            if(bufferedReader.readLine()==null){
                return true;
            }else{
                String line;
                while((line=bufferedReader.readLine())!=null){
                    String[] intoArray = line.split(",");
                    if(Long.parseLong(intoArray[3])==cardNumber){
                        return false;
                    }
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }

        return true;
    }

     public int getCardPin(long cardNumber) throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
         int pin = 0;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            String line;

            String[] intoArray;
            while((line=bufferedReader.readLine())!=null){
                intoArray = line.split(",");
                if(cardNumber==Long.parseLong(intoArray[3])){
                    pin = Integer.parseInt(intoArray[4]);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }

        return pin;
     }

     public static boolean validateUserNameEmailAddressAndPassword(String emailId,String password) throws IOException {
        CreditCardObjects creditCardObjects =new CreditCardObjects();
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        String line;
        try{
            fileReader = new FileReader(sourceToWrite);
            bufferedReader = new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null){
                String[] intoArray = line.split(",");
                if(Objects.equals(intoArray[1], emailId) && Objects.equals(intoArray[2], password)){
                    creditCardObjects.setFetchTheUser(intoArray[0]);
                    return true;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        return false;
     }

     public static boolean checkIfUserNameExist(String emailId) throws IOException {
         FileReader fileReader;
         BufferedReader bufferedReader = null;
         String line;
         String[] intoArray;
         try{
             fileReader = new FileReader(sourceToWrite);
             bufferedReader = new BufferedReader(fileReader);
             while((line=bufferedReader.readLine())!=null){
                 intoArray = line.split(",");
                 if(Objects.equals(intoArray[1], emailId)){
                     return true;
                 }
             }
         }catch (ArrayIndexOutOfBoundsException e){
             System.out.println(e.getMessage());
         }finally {
             assert bufferedReader != null;
             bufferedReader.close();
         }
         return false;
     }

     public static boolean validateAdminCredentials(String emailId,String password) throws IOException {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
         boolean welcomeAdmin = false;
        try {
            fileReader = new FileReader(adminCredentials);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine())!=null){
                String[] intoArray = line.split(",");
                if(Objects.equals(intoArray[0], emailId) && Objects.equals(intoArray[1], password)){
                    welcomeAdmin  =  true;
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            bufferedReader.close();
        }
        return welcomeAdmin;
     }
}

