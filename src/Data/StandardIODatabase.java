/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.*;
import java.util.*;

public class StandardIODatabase
{
    static HashMap<String,Data> database = new HashMap<String,Data>();
    static HashMap<String,SecurityClearance> login = new HashMap<String,SecurityClearance>();
    static Scanner scan;
    
    public static void main(String[] args)
    {
        //temp variables
        Scanner temp;
        String username = "";
        String password = "";
        int accessLevel = 0;
        String data1 = "";
        int data2 = 0;
        double data3 = 0.0;
        int choice = 0;
        String line = "";
        
        Data dat = new Data();
        SecurityClearance sec = new SecurityClearance();
        
        //load or create database.txt
        try
        {
            scan = new Scanner(new File("database.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage() + "...");
            System.out.println("Creating new database file...");
            makeNewFile();
        }
        
        //parsing information into database
        while(scan.hasNextLine())
        {
            temp = new Scanner(scan.nextLine());
            username = temp.next();
            password = temp.next();
            accessLevel = temp.nextInt();
            data1 = temp.next();
            data2 = temp.nextInt();
            data3 = temp.nextDouble();
            
            dat = new Data(data1,data2,data3);
            sec = new SecurityClearance(password,accessLevel);
            
            database.put(username, dat);
            login.put(username, sec);
        }
        
        //logging in
        while(true)
        {
            System.out.println("Note: to exit the program, type \"quit\"..." + "\n" + "Logging in...");
            scan = new Scanner(System.in);
            System.out.print("\nEnter username :: ");
            username = scan.next();
            if(username.equals("quit"))
            {
                break;
            }
            System.out.print("Enter password :: ");
            password = scan.next();
            
            //checks if username and password are valid
            if(login.containsKey(username) && database.containsKey(username))
            {
                if(login.get(username).getPassword().equals(password))
                {
                    accessLevel = login.get(username).getAccess();
                    System.out.println("\nUsername and password match...");
                    System.out.println("Access level :: " + accessLevel);
                    int currentAccessLevel = accessLevel;
                    while(true)
                    {
                        accessPrompt(currentAccessLevel);
                        scan = new Scanner(System.in);
                        choice = scan.nextInt();
                    
                        if(accessLevel == 1)
                        {
                            if(choice == 0)
                            {
                                break;
                            }
                            if(choice == 1)
                            {
                                while(true)
                                {
                                    System.out.print("\nAdd new account?(y)(n) :: ");
                                    line = scan.next();
                                    if (line.equals("n"))
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        String user1 = "";
                                        String pass = "";
                                        int access = 0;
                                        
                                        System.out.print("\nEnter username :: ");
                                        user1 = scan.next();
                                        System.out.print("Enter password :: ");
                                        pass = scan.next();
                                        System.out.print("Enter access level :: ");
                                        access = scan.nextInt();
                                        System.out.print("Enter (String) for data1 :: ");
                                        data1 = scan.next();
                                        System.out.print("Enter (int) for data2 :: ");
                                        data2 = scan.nextInt();
                                        System.out.print("Enter (double) for data3 :: ");
                                        data3 = scan.nextDouble();
                                        login.put(user1, new SecurityClearance(pass,access));
                                        database.put(user1, new Data(data1, data2, data3));
                                        
                                        System.out.println("New account made...");
                                    }
                                }
                            }
                            if(choice == 2)
                            {
                                while(true)
                                {
                                    System.out.print("\nDelete a user and its account data? (y)(n) :: ");
                                    line = scan.next();
                                    if (line.equals("n"))
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
                                    username = scan.next();
                                    if (login.containsKey(username) && database.containsKey(username))
                                    {
                                        System.out.print("Are you sure you want to delete " + line + "? (y)(n) :: ");
                                        line = scan.next();
                                        if (line.equals("y"))
                                        {
                                            login.remove(username);
                                            database.remove(username);
                                            System.out.print(username + " has been deleted");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println(username + " does not exist");
                                    }
                                }
                            }
                            if(choice == 3)
                            {
                                int choice3 = 0;
                                while(true)
                                {
                                    System.out.print("\nModify an account? (y)(n) :: ");
                                    line = scan.next();
                                    if (line.equals("n"))
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
                                    username = scan.next();
                                    System.out.print("\nWhat would you like to modify?\n  (1)username\n  (2)password\n  (3)access\n  (4)data1\n  (5)data2\n  (6)data3\n:: ");
                                    choice3 = scan.nextInt();
                                    if (choice3 == 1)
                                    {
                                        password = login.get(username).getPassword();
                                        accessLevel = login.get(username).getAccess();
                                        data1 = database.get(username).getData1();
                                        data2 = database.get(username).getData2();
                                        data3 = database.get(username).getData3();
                                        login.remove(username);
                                        database.remove(username);
                                        System.out.print("\nEnter your new username :: ");
                                        username = scan.next();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        database.put(username, new Data(data1, data2, data3));
                                        System.out.println("Username has been changed");
                                    }
                                    if (choice3 == 2)
                                    {
                                        accessLevel = login.get(username).getAccess();
                                        System.out.print("\nEnter your new password :: ");
                                        password = scan.next();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        System.out.println("Password has been changed");
                                    }
                                    if (choice3 == 3)
                                    {
                                        password = login.get(username).getPassword();
                                        System.out.print("\nEnter your new access level :: ");
                                        accessLevel = scan.nextInt();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        System.out.println("Access level has been changed");
                                    }
                                    if (choice3 == 4)
                                    {
                                        System.out.print("\nEnter your string :: ");
                                        scan.nextLine();
                                        data1 = scan.nextLine();
                                        database.get(username).setData1(data1);
                                        System.out.println("data1 has been changed");
                                    }
                                    if (choice3 == 5)
                                    {
                                        System.out.print("\nEnter your int :: ");
                                        data2 = scan.nextInt();
                                        database.get(username).setData2(data2);
                                        System.out.println("data2 has been changed");
                                    }
                                    if (choice3 == 6)
                                    {
                                        System.out.print("\nEnter your double :: ");
                                        data3 = scan.nextDouble();
                                        database.get(username).setData3(data3);
                                        System.out.println("data3 has been changed");
                                    }
                                }
                            }
                            if(choice == 4)
                            {
                                System.out.println();
                                for (int i = 0; i < login.size(); i++)
                                {
                                    System.out.println((i + 1) + ". " + login.keySet().toArray()[i]);
                                }
                                
                                System.out.print("\nEnter a username :: ");
                                username = scan.next();
                                System.out.println("\n" + "Username = " + username + "\n" + login.get(username) + "\n" + database.get(username));
                            }
                            if(choice == 5)
                            {
                                System.out.println();
                                for (int i = 0; i < login.size(); i++)
                                {
                                    System.out.println((i + 1) + ". " + login.keySet().toArray()[i] + "\n" + login.get(login.keySet().toArray()[i]) + "\n" +  database.values().toArray()[i]);
                                }
                                System.out.println();
                            }
                        }
                        if(accessLevel == 2)
                        {
                            if(choice == 0)
                            {
                                break;
                            }
                            if(choice == 1)
                            {
                                int choice3 = 0;
                                while(true)
                                {
                                    System.out.print("\nModify your account? (y)(n) :: ");
                                    line = scan.next();
                                    if (line.equals("n"))
                                    {
                                        break;
                                    }
                                     //Loop through usernames and prints them out
                                    System.out.print("\nWhat would you like to modify?\n  (1)username\n  (2)password\n  (3)access\n  (4)data1\n  (5)data2\n  (6)data3\n:: ");
                                    choice3 = scan.nextInt();
                                    if (choice3 == 1)
                                    {
                                        password = login.get(username).getPassword();
                                        accessLevel = login.get(username).getAccess();
                                        data1 = database.get(username).getData1();
                                        data2 = database.get(username).getData2();
                                        data3 = database.get(username).getData3();
                                        login.remove(username);
                                        database.remove(username);
                                        System.out.print("\nEnter your new username :: ");
                                        username = scan.next();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        database.put(username, new Data(data1, data2, data3));
                                        System.out.println("Username has been changed");
                                    }
                                    if (choice3 == 2)
                                    {
                                        accessLevel = login.get(username).getAccess();
                                        System.out.print("\nEnter your new password :: ");
                                        password = scan.next();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        System.out.println("Password has been changed");
                                    }
                                    if (choice3 == 3)
                                    {
                                        password = login.get(username).getPassword();
                                        System.out.print("\nEnter your new access level :: ");
                                        accessLevel = scan.nextInt();
                                        login.put(username, new SecurityClearance(password, accessLevel));
                                        System.out.println("Access level has been changed");
                                    }
                                    if (choice3 == 4)
                                    {
                                        System.out.print("\nEnter your string :: ");
                                        scan.nextLine();
                                        data1 = scan.nextLine();
                                        database.get(username).setData1(data1);
                                        System.out.println("data1 has been changed");
                                    }
                                    if (choice3 == 5)
                                    {
                                        System.out.print("\nEnter your int :: ");
                                        data2 = scan.nextInt();
                                        database.get(username).setData2(data2);
                                        System.out.println("data2 has been changed");
                                    }
                                    if (choice3 == 6)
                                    {
                                        System.out.print("\nEnter your double :: ");
                                        data3 = scan.nextDouble();
                                        database.get(username).setData3(data3);
                                        System.out.println("data3 has been changed");
                                    }
                                }
                            }
                            if(choice == 2)
                            {
                                System.out.println("\n" + "Username = " + username + "\n" + login.get(username) + "\n" + database.get(username) + "\n");
                            }
                        }
                        if(accessLevel == 3)
                        {
                            if(choice == 0)
                            {
                                break;
                            }
                            if(choice == 1)
                            {
                                System.out.println("\n" + "Username = " + username + "\n" + login.get(username) + "\n" + database.get(username) + "\n");
                            }
                        
                        }   
                    }
                }
            }
            else
            {
                System.out.println("The username or password is invalid, try again...\n");
            }
            
        }
        System.out.println("\nExiting program...");
        System.out.println("Writing new file...");
        String tempUsername = "";
        try
        {
            PrintWriter finalPrint = new PrintWriter(new File("database.txt"));
            for(int i = 0; i < database.size(); i++)
            {
                tempUsername = (String)login.keySet().toArray()[i];
                finalPrint.println(tempUsername + " " + login.get(tempUsername).getPassword() + " " + login.get(tempUsername).getAccess() + " " + database.get(tempUsername).getData1() + " " + database.get(tempUsername).getData2() + " " + database.get(tempUsername).getData3());
            }
            finalPrint.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("File complete...\nGoodbye...");
    }
    
    //creates new file if not found
    public static void makeNewFile()
    {
        try
        {
            PrintWriter printer = new PrintWriter("database.txt","ASCII");
            printer.println("administrator password 1 admin 1 1.1");
            printer.close();
            scan = new Scanner(new File("database.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error creating new file");
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("Character encoding error");
        }
    }
    
    //prints out prompt options depending on user's access level
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
