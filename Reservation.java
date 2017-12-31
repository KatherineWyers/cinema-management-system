/**
 * Reservation Class
 * Reservation objects are temporary seat allocations used by 
 * the Booker and Transferer
 *
 * @author Katherine Wyers
 * @version DEC-2017
 */
public class Reservation extends SeatAllocation
{ 
  public Reservation(Show show, Integer row, Integer num) { 
    this.row = row; 
    this.num = num;
    this.show = show;
    this.price = (row==5)? show.getPriceVip() : show.getPriceRegular(); 
  } 
} 