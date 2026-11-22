/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrations2;

import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public void setMessageID(String id) {
        this.messageID = id;
    }

    public void setMessageHash(String hash) {
        this.messageHash = hash;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessageText(String text) {
        this.messageText = text;
    }

    private static int totalSentMessagesCount = 0;
    private static StringBuilder allSentMessagesAccumulator = new StringBuilder();

    public Message(int loopCounter, String recipient, String messageText) {
        this.messageNumber = loopCounter;
        this.recipient = recipient;
        this.messageText = messageText;

        // Generate a secure, pseudo-random 10-digit tracking key 
        Random rand = new Random();
        long numericId = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(numericId);
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (this.recipient != null && this.recipient.startsWith("+27") && this.recipient.replace("+", "").length() <= 12) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (this.messageText == null || this.messageText.length() > 250) {
            int excess = (this.messageText != null) ? (this.messageText.length() - 250) : 0;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            this.messageHash = "00:0:EMPTY";
            return this.messageHash;
        }

        String firstTwoDigits = messageID.substring(0, 2);

        //Split text by whitespace to isolate the first and last words
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        this.messageHash = (firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    public String sentMessage(int userChoice) {
        if (userChoice == 1) {
            totalSentMessagesCount++;
            allSentMessagesAccumulator.append("Message Text: ").append(messageText).append("\n");
            return "Message successfully sent.";
        } else if (userChoice == 2) {
            return "Press 0 to delete the message.";
        } else if (userChoice == 3) {
            return "Message successfully stored.";
        }
        return "Invalid choice.";
    }

    public String printMessages() {
        return allSentMessagesAccumulator.toString();
    }

    public int returnTotalMessagess() {
        return totalSentMessagesCount;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
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
