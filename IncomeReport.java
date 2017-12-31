
/**
 * IncomeReport Class
 * Each object is a single report on a single film 
 * for a specified month and year
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class IncomeReport extends Report
{
    private float incomeSum = (float)0.0;
    
    /**
     * Constructor for objects of class IncomeReport
     * 
     * @param film Film 
     * @param month int 
     * @param year int 
     */
    public IncomeReport(Film film, int month, int year)
    {
        this.film = film;
        this.month = month;
        this.year = year;
    }
    
    /**
     * getIncomeSum
     * 
     * Get total amount of income from the film
     * 
     * @return float
     */
    public float getIncomeSum()
    {
        return this.incomeSum;
    } 
    
    /**
     * addIncome
     * 
     * Add income to the IncomeReport. This is 
     * used by the Transferer object when generating 
     * the report
     * 
     * @param income float
     */
    public void addIncome(float income)
    {
        this.incomeSum = this.incomeSum + income;
    } 
    
    /**
     * toString()
     * 
     * Get a string with film-title and income generated
     * 
     * @return String 
     */
    public String toString()
    {
        return "Film: " + this.film.getTitle() + ", " + "Income: $" + this.incomeSum;
    }
}
