package assignment0;
import java.util.ArrayList;
import java.util.List;

public class Cart {
     List<CartItem> cartItems;
     
     public Cart() {
    	 cartItems = new ArrayList<CartItem>();
     }
     
     public Boolean addToCart(Item item,Integer quantity){
         
         if(item==null || quantity<=0)
             return false;
    	 
    	 if(item.isValid()==false)
     	    return false;
    	 
    	 for (CartItem c:cartItems){
   	        if(c.item.same(item)){
   	        	//Item already exist 
   	        	//either update the quantity or just return false indicating that the update didn't take place  
   	        	//c.quantity=quantity; 
   	        	return false;
   	        }
   	     }
    	 
    	 CartItem c = new CartItem(item,quantity);
         
    	 cartItems.add(c);
    	 return true;
     }
     
     public Integer displayQty(Item item){
         
    	 if(item==null || item.isValid()==false)
     	    return 0;
    	 
    	 // simply loop the arrayList and return item quantity if the item is found 
    	 for (CartItem c:cartItems){
    	        if(c.item.same(item)){
    	        	return c.quantity;
    	        }
    	 } 
    	  
    	return 0; 
     }
     
     public Boolean updateQty(Item item,Integer quantity){
    	 
         if(item==null || quantity<=0)
             return false;
         
    	 if(item.isValid()==false)
     	    return false;
    	 
    	 for (CartItem c:cartItems){
 	        if(c.item.same(item)){
 	        	c.quantity = quantity; 
 	        	return true;
 	        }
 	     } 
    	 
    	 return false;
     }
     
     public Boolean deleteItem(Item item){
    	 
    	 if(item==null || item.isValid()==false)
    	    return false;
    	 
    	 for (CartItem c:cartItems){
 	        if(c.item.same(item)){
 	        	cartItems.remove(c); 
 	        	return true;
 	        }
 	     } 
    	 
    	 return false;
     }
     
     public Double displayBill(){
    	 
    	 Double value=0.0;
    	 
    	 for (CartItem c:cartItems){
    		 value+=c.item.price *c.quantity;
  	     } 
    	 
    	 return value;
     }
}
