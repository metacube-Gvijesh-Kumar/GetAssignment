package assignment0;


public class CartItem {
      Integer quantity;
      Item item;
      CartItem(Item i,Integer quantity) throws InstantiationError{
          if(i==null || quantity<=0)
            throw new InstantiationError("item can't be null and quantity should be positive integer");  
    	  this.item =i;
    	  this.quantity=quantity;
      }
}
