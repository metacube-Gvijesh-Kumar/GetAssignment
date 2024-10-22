package assignment0;

public class Item {
      String itemId;
      String name;
      String description;
      double price;
      
      public Item(String itemId,String name,String description,double price) {
    	  this.itemId=itemId;
    	  this.name=name;
    	  this.description=description;
    	  this.price=price;
      }
      
      public Boolean isValid(){
    	  // if for the item the itemId or name are not defined then item is invalid
    	  if(itemId!=null && name!=null )
    	  return true;
    	  return false;
      }
      
      public Boolean same(Item i) {
    	  //if object reference is not same then we are here checking that the item id is same or not if itemid is same then we consider that items are same else not same
    	  if(i.itemId==itemId)
    		  return true;
    	  return false;
      }
}
