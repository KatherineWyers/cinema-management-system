/**
 * Abstract class SeatAllocation
 * The abstract parent class for Reservation and Ticket
 * This class has seat location (row and num), and the price 
 * of the seat
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public abstract class SeatAllocation
{
    protected int id;
    protected int row;  // 1 to 5
    protected int num; // 1 to 10
    protected float price;
    protected Show show;
    
    /**
     * get the id
     * 
     * Get the SeatAllocation Id
     * 
     * @return int
     */
    public int getId()
    {
        return this.id;
    }
    
    /**
     * get the row
     * 
     * Get the seat row// from 1 to 5 
     * 
     * @return int
     */
    public int getRow()
    {
        return this.row;
    }
    
    /**
     * getPrice
     * 
     * get the price of the seat
     * 
     * @return float
     */
    public float getPrice()
    {
        return this.price;
    }
    

    /**
     * getNum
     * 
     * get the num of the seat// 1 to 10
     * 
     * @return int
     */
    public int getNum()
    {
        return this.num;
    }

    /**
     * getShow
     * 
     * get the show of the seat
     * 
     * @return Show
     */
    public Show getShow()
    {
        return this.show;
    }
    

    /**
     * getType
     * 
     * Rows 1 to 4, return "Regular"
     * Row 5, return "Vip"
     * 
     * @return String
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
    
    /**
     * getSeatName
     * 
     * Get the name of the seat. Eg "A1", "B7", "E5"
     * 
     * @return String
     */
    public String getSeatName()
    {
        return this.convertToRowLetter(this.getRow()) + this.getNum();
    }
    
    /**
     * Convert row int to row letter
     * 
     * Convert int 1-5 to String A-E
     * If int outside range {1, 2,...5}, return string with one white space
     * 
     * @param rowInt int
     * @return String
     */
    public String convertToRowLetter(int rowInt)
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
}
