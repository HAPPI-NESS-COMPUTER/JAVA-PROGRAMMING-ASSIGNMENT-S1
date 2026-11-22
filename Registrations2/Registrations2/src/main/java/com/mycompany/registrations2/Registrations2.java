/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registrations2;

import java.util.Scanner;

public class Registrations2 {

    public static void main(String[] args) {
        //Setup input scanners and data managers
        Scanner input = new Scanner(System.in);
        MessageManager manager = new MessageManager();

        //Local array to track messages sent during this session run
        String[] sentMessages = new String[100];
        int totalMessages = 0;

        //Default test user initialization
        Login2 user = new Login2("Kyle", "Smith");
        user.userInput();

        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String loginUserName = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        String loginMessage = user.loginUser(loginUserName, loginPassword);
        System.out.println(loginMessage);

        if (loginMessage.contains("Welcome")) {

            manager.loadMessagesFromJSON();

            System.out.println("Welcome to QuickChat.");
            int option = 0;

            while (option != 3) {
                System.out.println("\nMAIN MENU");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("4. Stored Messages Options");
                System.out.print("Choose option: ");

                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("How many messages do you wish to enter? ");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 1; i <= numMessages; i++) {
                            //.... capture values....
                            System.out.println("\n--- Entering Message " + i + " ---");
                            System.out.print("Enter recipient cellphone: ");
                            String cell = input.nextLine();

                            System.out.print("Enter short message: ");
                            String msgText = input.nextLine();

                            Message msg = new Message(i, cell, msgText);
                            String cellValidation = msg.checkRecipientCell();
                            String lengthValidation = msg.checkMessageLength();

                            System.out.println(cellValidation);
                            System.out.println(lengthValidation);

                            //Ensure inputs meet assignment validation criteria before proceeding
                            if (cellValidation.contains("successfully") && lengthValidation.contains("ready")) {
                                String generatedHash = msg.createMessageHash();

                                System.out.println("\nChoose action code:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Selection: ");
                                int actionChoice = input.nextInt();
                                input.nextLine();

                                //... action logic ...
                                //Map inputs to a unified status string for persistence tracking
                                String statusFlag = "Disregard";
                                String statusResult = "";

                                if (actionChoice == 1) {
                                    sentMessages[totalMessages] = msg.getMessageText();
                                    totalMessages++;
                                    statusFlag = "Sent";
                                    statusResult = msg.sentMessage(actionChoice);
                                } else if (actionChoice == 3) {
                                    statusFlag = "Stored";
                                    statusResult = "Message successfully stored.";
                                } else {
                                    statusResult = msg.sentMessage(actionChoice);
                                }

                                manager.addMessageRecord(msg.getMessageID(), msg.getMessageHash(), msg.getRecipient(), msg.getMessageText(), statusFlag);
                                manager.saveMessagesToJSON();

                                System.out.println(statusResult);

                                System.out.println("\n--- TRANSACTION RECORD ---");
                                System.out.println("Message ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessageText());
                            } else {
                                System.out.println("Transaction canceled due to validation errors.");
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\nSENT MESSAGES");
                        if (totalMessages == 0) {
                            System.out.println("No messages have been sent.");
                        } else {
                            for (int i = 0; i < totalMessages; i++) {
                                System.out.println(sentMessages[i]);
                            }
                            System.out.println("\nTotal messages sent: " + totalMessages);
                        }
                        break;

                    case 4:
                        System.out.println("\n--- STORED DATA UTILITIES ---");
                        System.out.println("a) View Stored Recipients");
                        System.out.println("b) View Longest Stored Message");
                        System.out.println("c) Search by Message ID");
                        System.out.println("d) Search by Recipient Number");
                        System.out.println("e) Delete Message using Hash");
                        System.out.println("f) Print Full Message Report");
                        System.out.print("Select choice letter: ");
                        String subChoice = input.nextLine().toLowerCase();

                        if (subChoice.equals("a")) {
                            System.out.println(manager.displayStoredRecipients());
                        } else if (subChoice.equals("b")) {
                            System.out.println("Longest Message: " + manager.displayLongestStoredMessage());
                        } else if (subChoice.equals("c")) {
                            System.out.print("Enter Message ID to search: ");
                            String searchId = input.nextLine();
                            System.out.println(manager.searchByMessageID(searchId));
                        } else if (subChoice.equals("d")) {
                            System.out.print("Enter Recipient Cell Number: ");
                            String searchRec = input.nextLine();
                            System.out.println(manager.searchByRecipient(searchRec));
                        } else if (subChoice.equals("e")) {
                            System.out.print("Enter Message Hash to delete: ");
                            String targetHash = input.nextLine();
                            System.out.println(manager.deleteMessageByHash(targetHash));
                            manager.saveMessagesToJSON();
                        } else if (subChoice.equals("f")) {
                            System.out.println(manager.displayMessageReport());
                        } else {
                            System.out.println("Invalid utility option.");
                        }
                        break;

                    case 3:
                        System.out.println("Program ended.");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Access Denied. Program terminated.");
        }
        input.close();
    }
}
/*
Reference List
Emeris_SoCS. (2025). PROG5121 Unit Testing Getting started. [Online Video]. Available at: https://youtu.be/MOhiM2SXZl0 [Accessed 13 Apr. 2026].

Farrell, J. (2019). Java Programming. 10th ed. Boston: Cengage Learning.

IIE. (2026). Programming 1A (PROG5121/p/w) Module Manual. [Online]. Available on: MOJO (IIE student portal) [Accessed 13 Apr. 2026].

Stack Overflow. (2026). Java password validation using regex. [Online]. Available at: https://stackoverflow.com/ [Accessed 13 Apr. 2026].

W3Schools. (2026). Java Tutorial. [Online]. Available at: https://www.w3schools.com/java/ [Accessed 13 Apr. 2026].
 */
