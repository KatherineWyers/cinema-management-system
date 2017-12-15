
/**
 * Write a description of class SeatReservation here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class SeatReservation { 
  private final Integer row; 
  private final Integer num;
  private double price;
  private Projection projection;
  private boolean used;
  
  public SeatReservation(Projection projection, Integer row, Integer num, double price) { 
    this.row = row; 
    this.num = num;
    this.price = price;
    this.projection = projection;
    this.used = false;
  } 
  
  public double getPrice()
  {
      return this.price;
  }
  
  public int getRow()
  {
      return this.row;
  }
  
  public int getNum()
  {
      return this.num;
  }
  
  public Projection getProjection()
  {
      return this.projection;
  }
  
  public void setUsed()
  {
      this.used = true;
  }
    
} 