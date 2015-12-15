package Data;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The main class that controls the the input and output prompts. It will 
 * prompt the user to first create any accounts, with a default "administrator"
 * already loaded into the HashMap. Then, the user can log in and, depending
 * on their access level, will be able to read, change, create, or delete
 * accounts.
 * 
 * @author Jonathan Lam
 */
public class Username
{
    //public static void main(String[] args)
    {
        //login HashMap for logging in, Database HashMap for holding data
        HashMap<String, Data> Database = new HashMap();
        HashMap<String, SecurityClearance> login = new HashMap();
        
        //temporary data space
        String username = "administrator";
        String password = "password";
        int access = 0;
        String data1 = "";
        int data2 = 0;
        double data3 = 0.0;
        String loginUsername = "";
        String loginPassword = "";
        int loginAccess = 0;
        
        int loginChoice = 0;
        Scanner keyboard = new Scanner(System.in);
        
        //temporary space for user input
        int answerInt = 0;
        String answerString = "";
        
        //setting admin account 
        login.put(username, new SecurityClearance(password, 1));
        Database.put(username, new Data());
        
        //set account phase, adds accounts in login and Database
        System.out.println("First stage - Set Accounts");
        System.out.println("------------------------------");
        while(true)
        {
            System.out.print("\nSet new account?(y)(n) :: ");
            answerString = keyboard.next();
            if (answerString.equals("n"))
            {
                break;
            }
            else
            {
                System.out.print("Enter username :: ");
                username = keyboard.next();
                if(login.keySet().contains(username))
                {
                    System.out.println("The username already exists. Enter another username.");
                    continue;
                }
                System.out.print("Enter password :: ");
                password = keyboard.next();
                System.out.print("Enter access level :: ");
                access = keyboard.nextInt();
                System.out.print("Enter (String) for data1 :: ");
                keyboard.nextLine();
                data1 = keyboard.nextLine();
                System.out.print("Enter (int) for data2 :: ");
                data2 = keyboard.nextInt();
                System.out.print("Enter (double) for data3 :: ");
                data3 = keyboard.nextDouble();
                
                //Set inputted data into login and Database HashMaps
                login.put(username, new SecurityClearance(password,access));
                Database.put(username, new Data(data1, data2, data3));
            }
        }
        
        //login phase, access account information
        System.out.println("\nSecond stage - Login");
        System.out.println("Note: type \"quit\" to end program");
        System.out.println("------------------------------");
        while(true)
        {
            System.out.print("\nLogin...\nEnter username :: ");
            loginUsername = keyboard.next();
            if (loginUsername.equals("quit"))
            {
                break;
            }
            System.out.print("Enter password :: ");
            loginPassword = keyboard.next();
            
            //checks if username and passwords match
            if (login.containsKey(loginUsername))
            {
                if (login.get(loginUsername).getPassword().equals(loginPassword))
                {
                    System.out.println("Success! Logging in...");
                    //access determines what user can do
                    loginAccess = login.get(loginUsername).getAccess();
                    while(true)
                    {
                        //prompts user with options, access levels determine what the user can do
                        accessPrompt(loginAccess);
                        loginChoice = keyboard.nextInt();
                        if (loginAccess == 3)
                        {
                            //prints out this user's account information
                            if (loginChoice == 1)
                            {
                                System.out.println("\n" + Database.get(loginUsername));
                            }
                            if (loginChoice == 0)
                            {
                                break;
                            }
                        }
                        if (loginAccess == 2)
                        {
                            //modifys the user's data
                            if (loginChoice == 1)
                            {
                                while(true)
                                {
                                    System.out.print("\nWhat would you like to modify?\n  (1)data1\n  (2)data2\n  (3)data3\n  (4)password\n  (0)quit\n:: ");
                                    answerInt = keyboard.nextInt();
                                    if (answerInt == 1)
                                    {
                                        System.out.print("\nWrite your string :: ");
                                        keyboard.nextLine();
                                        data1 = keyboard.nextLine();
                                        Database.get(username).setData1(data1);
                                    }
                                    if (answerInt == 2)
                                    {
                                        System.out.print("\nWrite your integer :: ");
                                        data2 = keyboard.nextInt();
                                        Database.get(username).setData2(data2);
                                    }
                                    if (answerInt == 3)
                                    {
                                        System.out.print("\nWrite your double :: ");
                                        data3 = keyboard.nextDouble();
                                        Database.get(username).setData3(data3);
                                    }
                                    if (answerInt == 4)
                                    {
                                        System.out.print("\nWrite your new password :: ");
                                        password = keyboard.next();
                                        login.get(username).setPassword(password);
                                    }
                                    if (answerInt == 0)
                                    {
                                        break;
                                    }
                                    System.out.print("\nContinue? (y)(n) :: ");
                                    answerString = keyboard.next();
                                    if (answerString.equals("n"))
                                    {
                                        break;
                                    }
                                }
                            }
                            
                            //prints out the user's data
                            if (loginChoice == 2)
                            {
                                System.out.println("\n" + Database.get(loginUsername));
                            }
                            if (loginChoice == 0)
                            {
                                break;
                            }
                        }
                        if (loginAccess == 1)
                        {
                            //adds users and user accounts
                            if (loginChoice == 1)
                            {
                                while(true)
                                {
                                    System.out.print("\nSet new account?(y)(n) :: ");
                                    answerString = keyboard.next();
                                    if (answerString.equals("n"))
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        System.out.print("\nEnter username :: ");
                                        username = keyboard.next();
                                        System.out.print("Enter password :: ");
                                        password = keyboard.next();
                                        System.out.print("Enter access level :: ");
                                        access = keyboard.nextInt();
                                        System.out.print("Enter (String) for data1 :: ");
                                        keyboard.nextLine();
                                        data1 = keyboard.nextLine();
                                        System.out.print("Enter (int) for data2 :: ");
                                        data2 = keyboard.nextInt();
                                        System.out.print("Enter (double) for data3 :: ");
                                        data3 = keyboard.nextDouble();
                
                                        login.put(username, new SecurityClearance(password,access));
                                        Database.put(username, new Data(data1, data2, data3));
                                    }
                                }
                            }
                            
                            //deletes a user
                            if (loginChoice == 2)
                            {
                                while(true)
                                {
                                    System.out.print("\nDelete a user and its account data? (y)(n) :: ");
                                    answerString = keyboard.next();
                                    if (answerString.equals("n"))
                                    {
                                        break;
                                    }
                                    
                                    //Loop through usernames and prints them out
                                    System.out.println();
                                    for (int i = 0; i < login.size(); i++)
                                    {
                                        System.out.println((i + 1) + ". " + login.keySet().toArray()[i]);
                                    }
                                    
                                    System.out.print("\nEnter a user to delete :: ");
                                    username = keyboard.next();
                                    if (login.containsKey(username) && Database.containsKey(username))
                                    {
                                        System.out.print("Are you sure you want to delete " + username + "? (y)(n) :: ");
                                        answerString = keyboard.next();
                                        if (answerString.equals("y"))
                                        {
                                            login.remove(username);
                                            Database.remove(username);
                                            System.out.print(username + " has been deleted");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println(username + " does not exist");
                                    }
                                }
                            }
                            //modifies data from a user's account
                            if (loginChoice == 3)
                            {
                                while(true)
                                {
                                    System.out.print("\nModify an account? (y)(n) :: ");
                                    answerString = keyboard.next();
                                    if (answerString.equals("n"))
                                    {
                                        break;
                                    }
                                     //Loop through usernames and prints them out
                                    System.out.println();
                                    for (int i = 0; i < login.size(); i++)
                                    {
                                        System.out.println((i + 1) + ". " + login.keySet().toArray()[i]);
                                    }
                                    
                                    System.out.print("\nEnter a username :: ");
                                    username = keyboard.next();
                                    System.out.print("\nWhat would you like to modify?\n  (1)username\n  (2)password\n  (3)access\n  (4)data1\n  (5)data2\n  (6)data3\n:: ");
                                    answerInt = keyboard.nextInt();
                                    if (answerInt == 1)
                                    {
                                        password = login.get(username).getPassword();
                                        access = login.get(username).getAccess();
                                        data1 = Database.get(username).getData1();
                                        data2 = Database.get(username).getData2();
                                        data3 = Database.get(username).getData3();
                                        login.remove(username);
                                        Database.remove(username);
                                        System.out.print("\nEnter your new username :: ");
                                        username = keyboard.next();
                                        login.put(username, new SecurityClearance(password, access));
                                        Database.put(username, new Data(data1, data2, data3));
                                        System.out.println("Username has been changed");
                                    }
                                    if (answerInt == 2)
                                    {
                                        access = login.get(username).getAccess();
                                        System.out.print("\nEnter your new password :: ");
                                        password = keyboard.next();
                                        login.put(username, new SecurityClearance(password, access));
                                    }
                                    if (answerInt == 3)
                                    {
                                        password = login.get(username).getPassword();
                                        System.out.print("\nEnter your new access level :: ");
                                        access = keyboard.nextInt();
                                        login.put(username, new SecurityClearance(password, access));
                                    }
                                    if (answerInt == 4)
                                    {
                                        System.out.print("\nEnter your string :: ");
                                        keyboard.nextLine();
                                        data1 = keyboard.nextLine();
                                        Database.get(username).setData1(data1);
                                    }
                                    if (answerInt == 5)
                                    {
                                        System.out.print("\nEnter your int :: ");
                                        data2 = keyboard.nextInt();
                                        Database.get(username).setData2(data2);
                                    }
                                    if (answerInt == 6)
                                    {
                                        System.out.print("\nEnter your double :: ");
                                        data3 = keyboard.nextDouble();
                                        Database.get(username).setData3(data3);
                                    }
                                }
                            }
                            //prints out data from a user's account
                            if (loginChoice == 4)
                            {
                                System.out.println();
                                for (int i = 0; i < login.size(); i++)
                                {
                                    System.out.println((i + 1) + ". " + login.keySet().toArray()[i]);
                                }
                                
                                System.out.print("\nEnter a username :: ");
                                username = keyboard.next();
                                System.out.println("\n" + "Username = " + username + "\n" + login.get(username) + "\n" + Database.get(username));
                            }
                            //prints out all users and their information
                            if (loginChoice == 5)
                            {
                                System.out.println();
                                for (int i = 0; i < login.size(); i++)
                                {
                                    System.out.println((i + 1) + ". " + login.keySet().toArray()[i] + "\n" + login.get(login.keySet().toArray()[i]) + "\n" +  Database.values().toArray()[i]);
                                }
                                System.out.println();
                            }
                            if (loginChoice == 0)
                            {
                                break;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("The username does not match the password");
                }
            }
            else
            {
                System.out.println("The username does not match the password");
            }
        }
        System.out.println("You have exited the program, goodbye!");
    }
    
    /**
     * List of prompt options depending on the user's access level
     * 
     * @param acc the access level of the user
     */
    public static void accessPrompt(int acc)
    {
        if (acc == 1)
        {
            System.out.print("\nYou can\n  (1) add a user\n  (2) remove a user\n  (3) modify a user\n  (4) print out the user data\n  (5) show all users\n  (0) exit\n:: ");
        }
        if (acc == 2)
        {
            System.out.print("\nYou can\n  (1) modify your data\n  (2) print out your data\n  (0) exit\n:: ");
        }
        if (acc == 3)
        {
            System.out.print("\nYou can\n  (1) print out your data\n  (0) exit\n:: ");
        }
    }
}
