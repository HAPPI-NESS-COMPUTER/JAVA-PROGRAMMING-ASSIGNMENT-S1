/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrations2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class MessageManager {

    private String[] messageIDs = new String[100];
    private String[] messageHashes = new String[100];
    private String[] recipients = new String[100];
    private String[] messagesText = new String[100];
    private String[] messageStatus = new String[100];
    private int messageCount = 0;

    public void addMessageRecord(String id, String hash, String recipient, String text, String status) {
        if (messageCount < 100) {
            messageIDs[messageCount] = id;
            messageHashes[messageCount] = hash;
            recipients[messageCount] = recipient;
            messagesText[messageCount] = text;
            messageStatus[messageCount] = status;
            messageCount++;
        }
    }

    public String displayStoredRecipients() {
        String result = "--- STORED MESSAGES RECIPIENTS ---\n";
        boolean found = false;
        for (int i = 0; i < messageCount; i++) {
            if (messageStatus[i].equalsIgnoreCase("Stored")) {
                result += "Recipient: " + recipients[i] + "\n";
                found = true;
            }
        }
        if (!found) {
            result += "No stored messages available.\n";
        }
        return result;
    }

    public String displayLongestStoredMessage() {
        String longest = "";
        for (int i = 0; i < messageCount; i++) {
            if (messageStatus[i].equalsIgnoreCase("Stored")) {
                if (messagesText[i].length() > longest.length()) {
                    longest = messagesText[i];
                }
            }
        }
        if (longest.equals("")) {
            return "No stored messages available.";
        }
        return longest;
    }

    public String searchByMessageID(String targetID) {
        for (int i = 0; i < messageCount; i++) {
            if (messageIDs[i].equals(targetID)) {
                return "Recipient: " + recipients[i] + " | Message: " + messagesText[i];
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String targetRecipient) {
        String result = "";
        boolean found = false;
        for (int i = 0; i < messageCount; i++) {
            if (recipients[i].equals(targetRecipient)) {
                result += recipients[i] + " -> " + messagesText[i] + "\n";
                found = true;
            }
        }
        if (!found) {
            return "No messages found for recipient.";
        }
        return result.trim();
    }

    public String deleteMessageByHash(String targetHash) {
        for (int i = 0; i < messageCount; i++) {
            if (messageHashes[i].equalsIgnoreCase(targetHash)) {
                String removedText = messagesText[i];

                // Target found: Shift trailing array items left to close the blank index gap
                for (int j = i; j < messageCount - 1; j++) {
                    messageIDs[j] = messageIDs[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    recipients[j] = recipients[j + 1];
                    messagesText[j] = messagesText[j + 1];
                    messageStatus[j] = messageStatus[j + 1];
                }
                messageCount--; // Decrement size counter to reflect the deletion
                return "Message: “" + removedText + "” successfully deleted.";
            }
        }
        return "Message Hash not found.";
    }

    public String displayMessageReport() {
        if (messageCount == 0) {
            return "No messages captured in the report yet.";
        }
        String report = "--- FULL MESSAGE REPORT ---\n";
        for (int i = 0; i < messageCount; i++) {
            report += "Status: [" + messageStatus[i] + "]\n"
                    + "ID: " + messageIDs[i] + "\n"
                    + "Hash: " + messageHashes[i] + "\n"
                    + "Recipient: " + recipients[i] + "\n"
                    + "Message: " + messagesText[i] + "\n"
                    + "----------------------------\n";
        }
        return report;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void loadMessagesFromJSON() {
        File file = new File("messages.json");
        if (!file.exists()) {
            return; // Exit early if no previous session file exists
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("messages.json")) {
            Message[] loadedMessages = gson.fromJson(reader, Message[].class);
            if (loadedMessages != null) {
                this.messageCount = 0;  //Reset active records array count before populating
                for (Message m : loadedMessages) {
                    addMessageRecord(m.getMessageID(), m.getMessageHash(), m.getRecipient(), m.getMessageText(), "Stored");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    public void saveMessagesToJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Message[] toSave = new Message[messageCount];

        for (int i = 0; i < messageCount; i++) {
            toSave[i] = new Message(i, recipients[i], messagesText[i]);
            toSave[i].setMessageID(messageIDs[i]);
            toSave[i].setMessageHash(messageHashes[i]);
        }

        try (FileWriter writer = new FileWriter("messages.json")) {
            gson.toJson(toSave, writer);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
