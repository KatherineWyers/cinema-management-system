import java.util.Scanner;
/**
 * Write a description of class CliUserInputter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CliUserInputter
{

    /**
     * Constructor for objects of class CliUserInputter
     */
    public CliUserInputter()
    {}

    /**
     * getUserInputInteger()
     * @param int maxValue
     * @return int input
     */
    public static int getUserInputInteger(int maxValue)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                selection = scanner.nextInt();
                if(selection==0||selection>maxValue)// Validate the input in range
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    return selection;// input is valid 
                }  
            }
            else
            {
                // Input is not an integer
                scanner.next();// Remove input from scanner
                System.out.println("The input was not a number");// Display notification
            }
        }    
    }

        
    /**
     * acceptIntegerInput()
     * @param int maxValue
     * @param String question
     * @return int input
     */
    public static int getUserInputInteger(int maxValue, String question)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==0||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range 1 to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }

    

        
    /**
     * getUserInputIntegerRange()
     * @param int minValue
     * @param int maxValue
     * @param String question
     * @return int input
     */
    public static int getUserInputIntegerRange(int minValue, int maxValue, String question)
    {
        Scanner scanner = new Scanner(System.in);
        int selection = -1;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextInt())
            {
                // Input is an integer
                
                // Update the choice
                selection = scanner.nextInt();

                // Validate the input in range
                if(selection==-1||selection<minValue||selection>maxValue)
                {
                    System.out.println("The number you entered is not in range " + minValue + " to " + maxValue);
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not an integer
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
            

        }    
    }
        
    /**
     * getUserInputDouble()
     * @param String question
     * @return float input
     */
    public static float getUserInputFloat(String question)
    {
        Scanner scanner = new Scanner(System.in);
        float selection = 0;
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextFloat())
            {
                // Input is an float
                
                // Update the choice
                selection = scanner.nextFloat();

                // Validate the input in range
                if(selection==0)
                {
                    System.out.println("The number you entered is not valid");
                }
                else
                {
                    // input is valid 
                    return selection;
                }            
            
            }
            else
            {
                // Input is not a double
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input was not a number");
            }
        }    
    }
    
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @return String input
     */
    public static String getUserInputString(int maxLength)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please make your selection:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * getUserInputString()
     * @param int maxLength
     * @param String question
     * @return String input
     */
    public static String getUserInputString(int maxLength, String question)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if(input=="")
                {
                    System.out.println("Input cannot be blank");
                }
                else if(input.length()>maxLength)
                {
                    System.out.println("Input too long. Input must be less than " + maxLength + " characters.");
                }
                else
                {
                    // input is valid 
                    return input;
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }    
    }   
    
    /**
     * getUserInputYN()
     * @return String input
     */
    public static String getUserInputYN()
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Enter Y or N:");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if((input.equals("Y"))||(input.equals("y")))
                {
                    return "Y";
                }
                else if((input.equals("N"))||(input.equals("n")))
                {
                    return "N";
                }
                else
                {
                    System.out.println("Input must be either Y or N");
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
    
    /**
     * getUserInputYN()
     * @param String question
     * @return String input
     */
    public static String getUserInputYN(String question)
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(true)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(question + ":");
            if(scanner.hasNextLine())
            {
                // Input is an integer
                
                // Update the choice
                input = scanner.nextLine();

                // Validate the input in range
                if((input.equals("Y"))||(input.equals("y")))
                {
                    return "Y";
                }
                else if((input.equals("N"))||(input.equals("n")))
                {
                    return "N";
                }
                else
                {
                    System.out.println("Input must be either Y or N");
                }            
            
            }
            else
            {
                // Input is not a string
                
                // Remove input from scanner and display notification
                scanner.next();
                System.out.println("The input is not valid");
            }
            

        }     
    }
}
