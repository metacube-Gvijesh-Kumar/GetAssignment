package assignment0;
public class TestClass{
	public static void main(String [] args)
	{
		Cart c = new Cart();
		Item a = new Item("i1","d1","p1",1.0);
		Item b = new Item("i2","d2","p2",2.0);
		Item d = new Item("i3","d3","p3",3.0);
		
		c.addToCart(d,2);
		c.addToCart(b,1);
	 	
		System.out.println("d quantity " + c.displayQty(d));
		System.out.println("a quantity " + c.displayQty(a));
		System.out.println("cart value " + c.displayBill());
		
		c.updateQty(d,1);
		
		System.out.println("d quantity " + c.displayQty(d));
		
		c.deleteItem(b);
		System.out.println("b quantity " + c.displayQty(b));
		System.out.println("cart value " + c.displayBill());
		
	}
}
