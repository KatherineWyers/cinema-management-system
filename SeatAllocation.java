
/**
 * Abstract class SeatAllocation - write a description of the class here
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
     * @return float price
     */
    public float getPrice()
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
    
    /**
     * getSeatName
     * @return String seatName
     */
    public String getSeatName()
    {
        return this.convertToRowLetter(this.getRow()) + this.getNum();
    }
    
    /**
     * Convert row int to row letter
     * Convert int 1-5 to String A-E
     * If int outside range {1, 2,...5}, return string with one white space
     * @param int row at int
     * @return String rowLetter
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
