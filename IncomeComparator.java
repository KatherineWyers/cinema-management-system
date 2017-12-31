import java.util.Comparator;
/**
 * IncomeComparator Class
 * Compares two objects based on their getIncomeSum() methods
 *
 * @author Katherine Wyers
 * 
 * Adapted from the online tutorial
 * 'How to sort objects using Comparator interface'
 * Created by: Java Made Easy
 * https://www.youtube.com/watch?v=u8D2fydghj4
 * Accessed 23-DEC-2017
 * 
 * @version DEC-2017
 */
public class IncomeComparator implements Comparator<IncomeReport>
{

    /**
     * Constructor for objects of class IncomeComparator
     */
    public IncomeComparator()
    {}

    /**
     * compare
     * 
     * Compare the two objects based on their 
     * getIncomeSum values. Return a positive 
     * or negative integer, depending on which 
     * object has the greater getIncomeSum
     * 
     * @param incomeReport1 IncomeReport 
     * @param incomeReport2 IncomeReport 
     * @return int
     */
    @Override
    public int compare(IncomeReport incomeReport1, IncomeReport incomeReport2)
    {
        return (int)(incomeReport2.getIncomeSum() - incomeReport1.getIncomeSum());// narrowing the value may change the order if they are within $1 of each other
    }
}
