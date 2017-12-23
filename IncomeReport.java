
/**
 * Write a description of class IncomeReport here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IncomeReport extends Report
{
    private float incomeSum = (float)0.0;
    
    /**
     * Constructor for objects of class IncomeReport
     */
    public IncomeReport(Film film, int month, int year)
    {
        this.film = film;
        this.month = month;
        this.year = year;
    }
    
    /**
     * getIncomeSum
     * Get total amount of income from the film
     * @return float
     */
    public float getIncomeSum()
    {
        return this.incomeSum;
    } 
    
    /**
     * addIncome
     * @return void
     */
    public void addIncome(float income)
    {
        this.incomeSum = this.incomeSum + income;
    } 
    
    /**
     * toString()
     * @return String 
     */
    public String toString()
    {
        return "Film: " + this.film.getTitle() + ", " + "Income: $" + this.incomeSum;
    }
}
