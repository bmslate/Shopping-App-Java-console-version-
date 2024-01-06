/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  Shopping application code
 *  1.including Member System: regular member, Premium Member.
 *  2.Shopping cart: Function: modifying shopping quantity,delete shopping item
 *  3.Customer service system: Instance Chat function.
 *  
 * */
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class Menu {
	
	protected boolean quitPoint=false;
	protected String filePath="C:\\Users\\BU1\\Desktop\\laptop\\大学课程\\Level 2\\Project\\memberList.txt";
	protected String filePathShoppingList="C:\\Users\\BU1\\Desktop\\laptop\\大学课程\\Level 2\\Project\\shoppingList.txt";
	
	
	Scanner input=new Scanner(System.in);
	ShopingList shoppingList=new ShopingList();
	

	
	ArrayList<ShopingList> addToCart=new ArrayList<>();
	//Store member object
	ArrayList<MemberSystem> memberList=new ArrayList<>();
	
	//Store member Info from this List
	ArrayList<MemberSystem> readMemberList=new ArrayList<>();
	
	//Store memeber Shopping list in this array list.
	ArrayList<ShopingList> memberShoppingList=new ArrayList<>();
	
	public Menu() {
		
	}
	
	public void product (){
		
		boolean ifContinue = false;
		do {
			try {	
			System.out.println("1. PC");
			System.out.println("2. Tablet");
			System.out.println("3. Laptop");
			
			
			shoppingList.addProductToCart(input);
			//input.nextLine();
			//加了两次
			addToCart.add(shoppingList);
			}catch(Exception e) {
				System.out.println("Invalid input..");
				ifContinue=true;
			}
			
			
		if(!ifContinue) {
			
			try {
			System.out.print("Do you want to continue shopping?(Y/N)");
			
			String option=input.nextLine();
			
			if(option.equalsIgnoreCase("y")) {
				//addToCart.add(shoppingList);
				quitPoint=false;
			}
			
			if(option.equalsIgnoreCase("N")) {
	
				System.out.print("Do you have membership of our App?(Y/N)");
				String memeberChoice=input.nextLine();
				if(memeberChoice.equalsIgnoreCase("Y")) {
					String idString;
					int id=0;
					MemberSystem member=new RegularMember();
					
					idString=member.readPassWord(input);
					id=Integer.parseInt(idString);
	
					if (id>1&&id<100) {
						
						for(MemberSystem m:readMemberList) {
							if(id==m.getMemberId()) {
								shoppingList.setMemberId(id);
	
								this.StoreShoppingList();
								
								Set<ShopingList> uniqueShoppingList=new HashSet<>(addToCart);
								
								for(ShopingList s:uniqueShoppingList) {
									
									if(id==s.getMemberId()) {
										Map<Integer, List<List<String>>> ordersByMember= this.readShoppingListData();
										double totalAmount= s.calculateTotal()*m.getMemberDiscount();
										s.setTotal(totalAmount);
										m.setTotalAmount(totalAmount);
										m.setSpendingRecord(totalAmount);
										System.out.println("Your current order as follows: ");
										System.out.print(s.printShoppingList());
										this.StoreShoppingList();
										
										//s.toString();
										this.matchMembersWithShoppingList(ordersByMember, id);
									}
								}
								
							}
							
						}
					}
					
					if (id>200&&id<300) {
						
						for(MemberSystem m:readMemberList) {
							if(id==m.getMemberId()) {
								shoppingList.setMemberId(id);
	
								this.StoreShoppingList();
								
								Set<ShopingList> uniqueShoppingList=new HashSet<>(addToCart);
								
								for(ShopingList s:uniqueShoppingList) {
									
									if(id==s.getMemberId()) {
										Map<Integer, List<List<String>>> ordersByMember= this.readShoppingListData();
										double totalAmount= s.calculateTotal()*m.getMemberDiscount();
										s.setTotal(totalAmount);
										m.setTotalAmount(totalAmount);
										m.setSpendingRecord(totalAmount);
										System.out.println("Your current order as follows: ");
										System.out.print(s.printShoppingList());
										this.StoreShoppingList();
										
										//s.toString();
										this.matchMembersWithShoppingList(ordersByMember, id);
									}
								}
								
							}
							
						}
					}
					
				}
				if(memeberChoice.equalsIgnoreCase("N")) {
					System.out.print("Do you want to register a new membership to abtain more benefit of your shopping ?(Y/N)");
					String newMember=input.nextLine();
				//if (newMember.equalsIgnoreCase("Y")){}	
					if(newMember.equalsIgnoreCase("Y")) {
						System.out.println("Do you want to be registered as a Regular member or Premium Member? ");
						System.out.println("Regular Member will have no annual member fee, and has a 5% discount of your order.Premium member has a CAD 30.00/year annual Member Fee,and has more benefit on discount, like 8% discount of your order.");
						System.out.print("[R] for Regular Member and [P] for Premium Member: ");
						String memberChoose=input.nextLine();
						if (memberChoose.equalsIgnoreCase("R")) {
							MemberSystem regular=new RegularMember();
							regular.customerInfo(input);
							double totalAmount = shoppingList.calculateTotal()*regular.getMemberDiscount();
							regular.setTotalAmount(totalAmount);
							memberList.add(regular);
							regular.printDetails();
							shoppingList.setMemberId(regular.getMemberId());
							shoppingList.setTotal(totalAmount);
		
							System.out.print(shoppingList.printShoppingList());
							this.StoreShoppingList();
							this.storeMemberList();
						}else if (memberChoose.equalsIgnoreCase("p")) {
							MemberSystem premium=new PremiumMember();
							premium.customerInfo(input);
							double totalAmount = shoppingList.calculateTotal()*premium.getMemberDiscount();
							premium.setTotalAmount(totalAmount);
							memberList.add(premium);
							premium.printDetails();
							shoppingList.setMemberId(premium.getMemberId());
							shoppingList.setTotal(totalAmount);
		
							System.out.print(shoppingList.printShoppingList());
							this.StoreShoppingList();
							this.storeMemberList();
						}
						
					}
					else if (newMember.equalsIgnoreCase("N")) {
					
						System.out.println("Thank you for shopping on our App! Your shopping lis is following:" );
						shoppingList.calculateTotal();
						
						
						System.out.print(shoppingList.printShoppingList());
						
						String delimeter="-".repeat(50);
						System.out.println(delimeter);
						
						
						this.StoreShoppingList();
	
					}
					
					
					
				}
				
				quitPoint=true;
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}while(!quitPoint);
		
		
		
	}
	
	//Read information date field by field and encapsulate it as a instance of MemberSystem..
	public MemberSystem readFile(String input) {
		
		String [] read=input.split(" \\| ");
		if(read.length<9) {
			throw new IllegalArgumentException("Invalid line format");
		}
		
		MemberSystem member=new RegularMember();
		
		member.setFirstName(read[0].split(": ")[1].trim());
		member.setLastName(read[1].split(": ")[1].trim());
		member.setEmail(read[2].split(": ")[1].trim());
		member.setAddress(read[3].split(": ")[1].trim());
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			member.setBirthDay(formatter.parse(read[4].split(": ")[1].trim()));
			}catch(ParseException e) {
				e.printStackTrace();
			}
		if (member instanceof RegularMember) {
			((RegularMember)member).setCountPoints(Double.parseDouble(read[5].split(": ")[1].trim()));
		}
		
		member.setMemberId(Integer.parseInt(read[6].split(": ")[1].trim()));
		member.setTotalAmount(Double.parseDouble(read[7].split(": ")[1].trim()));

		try {
			SimpleDateFormat formatter=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			member.setRegisterTime(formatter.parse(read[8].split(": ")[1].trim()));
			}catch(ParseException e) {
				e.printStackTrace();
			}
		return member;
		}
		
	
	//read file content and  adding it to ArrayList.
	public void readMember() {
		
		try (BufferedReader reader=new BufferedReader(new FileReader(filePath));)
		{
			String format;
			while((format=reader.readLine())!=null) {
				try {
					//call readFile method store the object in to MemberSystem type variable. 
				MemberSystem readMember=this.readFile(format);
				// Add it into the ArrayList
				readMemberList.add(readMember);
			
				}catch(IllegalArgumentException e){
						e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//write the member list information to a file
	public void storeMemberList(){
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(filePath,true));) {
			
			for(MemberSystem m: memberList) {
				//String memberInfo=m.toString();
				writer.write(m.toString());
				writer.newLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}				
	}
	
	//output shopping list information to a file
	public void StoreShoppingList() {
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(filePathShoppingList,true));){
			Set<ShopingList> uniqueShoppingLists = new HashSet<>(addToCart);
			for (ShopingList s: uniqueShoppingLists) {
				//String addDetails=s.addDetails;
				
				writer.write(s.addDetails);
				writer.newLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//This method is to read from a shopping list file, and read the information as a string, and use its member ID as the key.
	public Map<Integer, List<List<String>>> readShoppingListData() {
		
		Map<Integer, List<List<String>>> ordersByMember = new HashMap<>();
		try(BufferedReader reader=new BufferedReader(new FileReader(filePathShoppingList))){
			String details;
			List<String> currentOrder = new ArrayList<>();
			int currentMemberId = -1;
			
			while((details=reader.readLine())!= null) {
				currentOrder.add(details);
				
				if (details.contains("Member Id")) {
					String [] id = details.split(" \\| ");
					currentMemberId = Integer.parseInt(id[id.length-1].trim());
					
					ordersByMember.computeIfAbsent(currentMemberId, k -> new ArrayList<>()).add(new ArrayList<>(currentOrder));
					currentOrder.clear();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return ordersByMember;
	}
	
	//Passing the Map list to this method in order to extract the member order information.
	public void matchMembersWithShoppingList(Map<Integer, List<List<String>>> ordersByMember, int memberId) {
		if(ordersByMember.containsKey(memberId)) {
			List<List<String>> memberOrders = ordersByMember.get(memberId);
			System.out.println("Shopping Recorde of Member Id " + memberId);
			String delimeter="*".repeat(50);
			System.out.println(delimeter);
			for(List<String> order: memberOrders) {
				for(String a: order) {
					System.out.printf("%s%n",a);
				}
			}
		}else {
			System.out.println("No order history founded");
		}
	}

	

	
}
