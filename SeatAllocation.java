
/**
 * Abstract class SeatAllocation - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class SeatAllocation
{
    protected int id;
    protected int row; 
    protected int num;
    protected double price;
    protected Show show;
    
    /**
     * get the id
     * @return int id
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * get the row
     * @return String row
     */
    public int getRow()
    {
        return this.row;
    }
    
    /**
     * get the price that was paid for the ticket
     * @return double price
     */
    public double getPrice()
    {
        return this.price;
    }
    

    /**
     * get the number
     * @return int number
     */
    public int getNum()
    {
        return this.num;
    }

    /**
     * get the show
     * @return Show show
     */
    public Show getShow()
    {
        return this.show;
    }
    

    /**
     * get the type
     * @return String type
     */
    public String getType()
    {
        // All seats in row E are Vip seats. All others are regular
        if(this.row == 5)
        {
            return "Vip";
        }
        return "Regular";
    }
}
