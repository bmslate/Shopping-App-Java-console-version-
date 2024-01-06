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

public class Tablet implements Product {
	
	protected String item="Tablet";
	protected double productPrice=222.33;
	protected String brand="Apple";
	protected String size="16''";
	protected int productQuantity;
	protected double subTotal;
	protected String details;
	
	public Tablet() {
		
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
	
	public double getSubTotal() {
		
		return subTotal;
	}
	
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
		
		return subTotal=productQuantity*productPrice * (1+TAX);
		
	}
	
	public String printDetails() {
		
		details = String.format("You order included | %d | | %s | Tablet. The price(CAD): | %.2f |. The subtotal amount(CAD): | %.2f | %n",productQuantity,brand,productPrice,subTotal);
		//System.out.print(details);
//		int numberStar=100;
//		String star="*".repeat(numberStar);
//		System.out.println(star);
		return details;
	}

}
