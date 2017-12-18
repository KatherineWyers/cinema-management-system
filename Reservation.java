
/**
 * Write a description of class SeatReservation here.
 *
 * @author Katherine Wyers
 * @version 1.0
 */
public class Reservation extends SeatAllocation
{ 
  private boolean used;
  
  public Reservation(Show show, Integer row, Integer num) { 
    this.row = row; 
    this.num = num;
    this.show = show;
    this.price = (row==5)? show.getPriceVip() : show.getPriceRegular(); 
    this.used = false;
  } 
  
  public void setUsed()
  {
      this.used = true;
  }
    
} 