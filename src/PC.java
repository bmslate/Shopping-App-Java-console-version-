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



public class PC implements Product {
	
	protected String item="PC";
	protected double productPrice = 111.25;
	protected String brand = "Dell";
	protected String details;
	
	
	protected int productQuantity;
	
	protected double subTotal;
	
	public String getItem() {
		return item;
	}
	
	public double getSubTotal() {
		
		return subTotal;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity=productQuantity;
	}
	
	public void addProduct(Scanner input) {
		
		System.out.print("Please select the quantity..");
		productQuantity=input.nextInt();
		input.nextLine();
		System.out.println("Please wait for a moment, we are adding your shopping item to shopping list....");
		int numberStar=100;
		String star="*".repeat(numberStar);
		System.out.println(star);
		
	}
	
	
	public double subTotal() {
		
		return subTotal=productQuantity * productPrice * (1+TAX);
		
	}

	public String printDetails() {
		
		details = String.format("You order included | %d | | %s | PC. The price(CAD): | %.2f |. The subtotal amount(CAD): | %.2f | %n",productQuantity,brand,productPrice,subTotal);

		return details;
	}

}
