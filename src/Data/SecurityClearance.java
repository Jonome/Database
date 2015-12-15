package Data;

/**
 * SecurityClearance holds a password String and a int access. The password
 * will be used to check if the login is valid. The access will tell the 
 * program how much the user can manipulate data. Access level 1 represents
 * administration level, allowing the user to change data and account
 * information. Access level 2 represents user level, allowing the user to 
 * alter their own information. Access level 3 represents guest level, allowing
 * user to read their own data. The various methods will be used to set and 
 * get the variables, while the toString method returns a String that holds 
 * all the variables.
 * 
 * @author Jonathan Lam
 */
public class SecurityClearance
{
    private String password = "";
    private int access = 0;
    
    public SecurityClearance()
    {
        password = "password";
        access = 3;
    }
    
    public SecurityClearance(String pass, int  acc)
    {
        password = pass;
        access = acc;
    }
    
    /**
     * Sets the password
     * 
     * @param pass the String to set to password
     */
    public void setPassword(String pass)
    {
        password = pass;
    }
    
    /**
     * Sets the access
     * 
     * @param acc the int to set to access
     */
    public void setAccess(int acc)
    {
        access = acc;
    }
    
    /**
     * Returns the password
     * 
     * @return the String from password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Returns the access level
     * 
     * @return the int from access
     */
    public int getAccess()
    {
        return access;
    }
    
    /**
     * Returns a String that contains all the information in the class
     * 
     * @return the String that holds password and access
     */
    public String toString()
    {
        String words = "";
        words = "Password = " + password + "\nAccess = " + access;
        return words;
    }
}
