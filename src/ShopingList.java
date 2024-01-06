/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  Shopping cart. 
 *  Function A:Print every item information and total amount on the shopping cart.
 *  		 B:Store all shopping history in a file.
 *  		 C:Modifying Shopping cart, delete or change quantity.
 *  			
 *  
 * */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class ShopingList  {
	
	protected double total;
	
	protected int memberId;
	
	protected ArrayList<Product> product = new ArrayList<>();
	
	//For method printShoppingList.
	protected String shoppingListDetails = "";
	protected String fullShoppingList = "";
	protected int count=1;
	protected String details = "";
	
	//For passing value to Buffered writer.
	protected String addDetails="";

	
	public ShopingList() {
		
		
	}
	
	public void setTotal(double total) {
		this.total=total;
		
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId=memberId;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void addProductToCart (Scanner input) {
		boolean match=true;
		do {
			System.out.print("Please enter your option..");
			int menuOption=input.nextInt();
			input.nextLine();
			
			
			if(menuOption==1) {
				PC pc=new PC();
				pc.addProduct(input);
				pc.subTotal();
				System.out.print(pc.printDetails());
				product.add(pc);
				
			}
			
				else if(menuOption==2) {
					Tablet tablet=new Tablet();
					tablet.addProduct(input);
					tablet.subTotal();
					System.out.print(tablet.printDetails());
					product.add(tablet);
				
				}
			
				else if(menuOption==3) {
					Laptop laptop=new Laptop();
					laptop.addProduct(input);
					laptop.subTotal();
					System.out.print(laptop.printDetails());
					product.add(laptop);
				}
				
				else {
					System.out.println("Input invalid...");
					match=false;
				}
			

			boolean modificationMatch=true;
			do {
				
			
			System.out.print("Do you want to modify your selections?(Y/N)");
			
			String modification=input.nextLine();
			
			
			
			if (modification.equalsIgnoreCase("Y")) {
				System.out.print("To Delete Item please press [D].To modify the quantity of your item press [M]");
				String update=input.nextLine();
				if (update.equalsIgnoreCase("D")) {
					
					System.out.println("Please enter the item name that you want to delete");
					String delete=input.nextLine();
					Iterator<Product> remove=product.iterator();
					
					while(remove.hasNext()) {
						
						Product p=remove.next();
						if(delete.equalsIgnoreCase(p.getItem())) {
							
							remove.remove();
							System.out.println("Item deleted...");
							
							
						}
						
					}
					
				}
				
				//Try-catch 怎么写？
				if (update.equalsIgnoreCase("M")) {
					System.out.println("Please enter the item that you want to modify: ");
					String modify=input.nextLine();
					for(Product p : product) {
						if(p.getItem().equalsIgnoreCase(modify)) {
							boolean limit =true;
							do {
								//try {
								System.out.print("Please click [+] or [-] to add or cut the quantity by one or directely set the quantity : ");
								if(input.hasNextInt()) {
									int newQuantity=input.nextInt();
									input.nextLine();
									// this line can be set as a gateway to storage system.
									if(newQuantity>1 || newQuantity<100) {
										p.setProductQuantity(newQuantity);
										System.out.printf("The quantity of %s has been set to %d.%n",p.getItem(),p.getProductQuantity());
									}else {
										System.out.println("Invalid input...");
									}
									
								}else {
									String math=input.nextLine();
									int count=1;
									
									if(math.equalsIgnoreCase("+")) {
									
										p.setProductQuantity(p.getProductQuantity()+count);
										
										System.out.printf("The quantity of %s has been set to %d.%n",p.getItem(),p.getProductQuantity());
										//"100" can be set to gateway to reach the storage System.
										if(p.getProductQuantity()==100) {
											System.out.print("Reached the limits.... ");
											limit=false;
										}
									}
									
									if(math.equalsIgnoreCase("-")) {
										
										p.setProductQuantity(p.getProductQuantity()-count);
										
										System.out.printf("The quantity of %s has been set to %d.%n",p.getItem(),p.getProductQuantity());
										//
										if(p.getProductQuantity()==1) {
											System.out.print("Reached the limits.... ");
											limit=false;
										}
									}
								}
								
								
								boolean ifContinue=true;
								do {
								System.out.print("Do you want to continue?(Y/N)");
								String choice=input.nextLine();	
								if(choice.equalsIgnoreCase("n")) {
									ifContinue=false;
									limit=false;
								}
								else if(choice.equalsIgnoreCase("y")) {
									ifContinue=false;
									//limit=true;//because limit have been already be set to true;
								}
								else {
									System.out.println("Invalid input...");
								}
								}while(ifContinue);//还不知道写什么
								
								
							}while(limit);
						}
					}
					
				}
				
			}
			
			else if(modification.equalsIgnoreCase("n")) {
				match=true;
				
			}else
			{
				System.out.println("Invalid Input....");
				modificationMatch=false;
			}
			}while(!modificationMatch);
		
		}while(!match);
		
		
		
	}
	
	public double calculateTotal() {
		for(Product p: product) {
			
			total += p.subTotal();
		}
		return total;
	}
	
	
	public String printShoppingList() {
		
		for(Product p: product) {
			
			  details += p.printDetails();

		}
		
		shoppingListDetails = String.format("The total(CAD): | %.2f |. Member Id: | %d | %n",total,memberId);

		addDetails = String.format("%s %n %s",details,shoppingListDetails);

		return addDetails;
	}

}
