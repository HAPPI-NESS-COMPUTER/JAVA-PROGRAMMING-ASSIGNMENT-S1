/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrations2;

import java.util.Scanner;

public class Login2 {

    private Scanner input = new Scanner(System.in);

    private String userName;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    public Login2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Checks if username has an an underscore and stays under 6 charaters 
    public boolean checkUserName(String userName) {
        return userName != null && userName.contains("_") && userName.length() <= 5;
    }

    // Validates complexity: 1 uppercase, 1 digit, 1 special char, minimum 8 long
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";
        return password != null && password.matches(regex);
    }

    //Enforces SA international number standard (+27 followed by 9 digits)
    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone != null && cellPhone.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String userName, String password, String cellPhone) {
        if (!checkUserName(userName)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.userName = userName;
        this.password = password;
        this.cellPhone = cellPhone;

        return "User registered successfully.";
    }

    public String loginUser(String loginUsername, String loginPassword) {
        if (this.userName != null && this.password != null
                && this.userName.equals(loginUsername)
                && this.password.equals(loginPassword)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public void userInput() {
        System.out.print("Enter first name: ");
        firstName = input.nextLine();

        System.out.print("Enter last name: ");
        lastName = input.nextLine();

        System.out.println("\n--- REGISTRATION ---");

        String tempUser;
        do {
            System.out.print("Enter username: ");
            tempUser = input.nextLine();
            if (!checkUserName(tempUser)) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!checkUserName(tempUser));

        String tempPass;
        do {
            System.out.print("Enter password: ");
            tempPass = input.nextLine();
            if (!checkPasswordComplexity(tempPass)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!checkPasswordComplexity(tempPass));

        String tempCell;
        do {
            System.out.print("Enter SA cell number (+27...): ");
            tempCell = input.nextLine();
            if (!checkCellPhoneNumber(tempCell)) {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        } while (!checkCellPhoneNumber(tempCell));

        registerUser(tempUser, tempPass, tempCell);
        System.out.println("\nRegistration successful!");
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
