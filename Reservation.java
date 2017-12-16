
/**
 * Write a description of class SeatReservation here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Reservation extends SeatAllocation
{ 
  private boolean used;
  
  public Reservation(Projection projection, Integer row, Integer num) { 
    this.row = row; 
    this.num = num;
    this.projection = projection;
    this.price = (row==5)? projection.getPriceVip() : projection.getPriceRegular(); 
    this.used = false;
  } 
  
  public void setUsed()
  {
      this.used = true;
  }
    
} 