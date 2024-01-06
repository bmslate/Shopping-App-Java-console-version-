/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  Shopping cart. 
 *  Product infterface class.
 *  			
 *  
 * */
import java.util.Scanner;

public interface Product {
	
	public static final double TAX=0.12;
	
	public void addProduct(Scanner input);
	
	public String printDetails();
	
	public String getItem();
	
	public double subTotal();
	
	public void setProductQuantity(int productQuantity);
	
	public int getProductQuantity();

}
