import java.util.Comparator;
/**
 * Write a description of class IncomeComparator here.
 *
 * @author Katherine Wyers
 * 
 * Adapted from the online tutorial
 * 'How to sort objects using Comparator interface'
 * Created by: Java Made Easy
 * https://www.youtube.com/watch?v=u8D2fydghj4
 * Accessed 23-DEC-2017
 * 
 * @version 23-DEC-2017
 */
public class IncomeComparator implements Comparator<IncomeReport>
{

    /**
     * Constructor for objects of class IncomeComparator
     */
    public IncomeComparator()
    {}

    /**
     *
     * @param IncomeReport incomeReport1
     * @param IncomeReport incomeReport2
     * @return int
     */
    @Override
    public int compare(IncomeReport incomeReport1, IncomeReport incomeReport2)
    {
        return (int)(incomeReport2.getIncomeSum() - incomeReport1.getIncomeSum());// narrowing the value may change the order if they are within $1 of each other
    }
}
