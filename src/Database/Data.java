package Database;

/**
 * Data contains three pieces of data. The first holds a String, the
 * second holds an int, and the third holds a double. The class has set and
 * get methods for data manipulation and retrieval, and a toString prints 
 * out the data values.
 * 
 * @author Jonathan Lam
 */
public class Data
{
    private String data1 = "";
    private int data2 = 0;
    private double data3 = 0.0;
    
    public Data()
    {
        data1 = "The cake is a lie";
        data2 = 0;
        data3 = 0.0;
    }
    
    public Data(String d1, int d2, double d3)
    {
        data1 = d1;
        data2 = d2;
        data3 = d3;
    }
    
    /**
     * Sets a String to data1
     * 
     * @param d1 a String to set for the Data class
     */
    public void setData1(String d1)
    {
        data1 = d1;
    }
    
    /**
     * Sets a int to data2
     * 
     * @param d2 a int to set for the Data class
     */
    public void setData2(int d2)
    {
        data2 = d2;
    }
    
    /**
     * Sets a double to data3
     * 
     * @param d3 a double to set for the Data class
     */
    public void setData3(double d3)
    {
        data3 = d3;
    }
    
    /**
     * Returns data1
     * 
     * @return the String from data1
     */
    public String getData1()
    {
        return data1;
    }
    
    /**
     * Returns data2
     * 
     * @return the int from data2
     */
    public int getData2()
    {
        return data2;
    }
    
    /**
     * Returns data3
     * 
     * @return the double from data3
     */
    public double getData3()
    {
        return data3;
    }
    
    /**
     * Returns a String that contains all the data values
     * 
     * @return a String that shows all the data values
     */
    public String toString()
    {
        String words = "";
        words = "data1 = " + data1 + "\ndata2 = " + data2 + "\ndata3 = " + data3;
        return words;
    }
}