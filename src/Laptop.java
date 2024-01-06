/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  Shopping cart. 
 *  implements Product InterFace.
 *  			
 *  
 * */

import java.util.Scanner;

public class Laptop implements Product {
	
	protected String item="Laptop";
	protected double productPrice=333.33;
	protected String brand="Acer";
	protected String size="21''";
	protected int productQuantity;
	protected String details;
	
	protected double subTotal;
	
	public Laptop() {
		
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity=productQuantity;
	}
	
	public String getItem() {
		return item;
	}
	
//	public double getSubTotal() {
//		
//		return subTotal;
//	}
	
	public void addProduct(Scanner input) {
		
		System.out.print("Please select the quantity..");
		productQuantity=input.nextInt();
		input.nextLine();//consume the newline character;
		System.out.println("Please wait for a moment, we are adding your shopping item to shopping list....");
		int numberStar=100;
		String star="*".repeat(numberStar);
		System.out.println(star);
		
	}
	
	public double subTotal() {
		
		return subTotal = productQuantity * productPrice * (1+TAX);
		
	}
	
	public String printDetails() {
		
		details = String.format("You order included | %d | | %s | Laptop. The price(CAD): | %.2f |. The subtotal amount(CAD): | %.2f | %n",productQuantity,brand,productPrice,subTotal);
		//System.out.print(details);
//		int numberStar=100;
//		String star="*".repeat(numberStar);
//		System.out.println(star);
		return details;
	}

}
