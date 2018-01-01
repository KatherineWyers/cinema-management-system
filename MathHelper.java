
/**
 * MathHelper
 * Utility class for Gui
 *
 * @author Katherine Wyers
 * @version 30-DEC-2017
 */
public class MathHelper
{
    /**
     * Constructor for objects of class MathHelper
     */
    public MathHelper()
    {}
    
    /**
     * isLengthInRange
     * 
     * Utility method to check if the length 
     * of a string is within the specified range
     * 
     * @param string String 
     * @param minLength int 
     * @param maxLength int 
     * @return boolean
     */
    public static boolean isLengthInRange(String string, int minLength, int maxLength)
    {
        return(string.length()>=minLength&&string.length()<=maxLength);
    }
    
    /**
     * convertToRowLetter
     * 
     * Convert row int to row letter
     * Convert int 1-5 to A-E. 
     * If int out of range, return string with single white space
     * 
     * @param rowInt int
     * @return String rowLetter
     */
    public static String convertToRowLetter(int rowInt)
    {
        String rowLetter;
        // convert rows to letters
        switch(rowInt){
           case 1: 
                rowLetter = "A";
                break;
           case 2: 
                rowLetter = "B";
                break;
           case 3:
                rowLetter = "C";
                break;
           case 4:
                rowLetter = "D";
                break;
           case 5:
                rowLetter = "E";
                break;
           default:
                rowLetter = " ";
        }
        return rowLetter;
    }
    
    /**
     * convertToRowNum
     * 
     * Convert row letter to row int
     * Convert int A-E to 1-5. 
     * If letter out of range, return -1
     * 
     * @param rowLetter String
     * @return int
     */
    public static int convertToRowNum(String rowLetter)
    {
        // convert rows to letters
        switch(rowLetter){
            case "A": 
                return 1;
            case "B": 
                return 2;
            case "C": 
                return 3;
            case "D": 
                return 4;
            case "E": 
                return 5;
        }
        return -1;
    }
}
