/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  
 *  1.Member System: regular member, Premium Member.
 *  Function A: counting Member points ,subtotal from each purchasing.
 *  		 B: Registering new member
 *  		 C: Print Member Information;
 *  		 D: Modify Member Information;
 *  
 * */

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class MemberSystem {
	
	protected String firstName;
	protected String lastName;
	protected String address;
	protected String email;
	protected String passWord;
	
	protected int telephone;
	protected double spendingRecord;
	protected Date birthDay;
	protected String displayDoB;
	
	public MemberSystem() {
		
	}
	public abstract Date getRegisterTime();
	
	public abstract void setRegisterTime(Date registerTime);
	
	public abstract double getMemberDiscount();
	
	public abstract double getCountPoints();
						 
	public abstract void setCountPoints(double countPoints);
	
	public abstract double getTotalAmount();
	
	public abstract void setTotalAmount(double total);
	
	public abstract int getMemberId();
	
	public abstract void setMemberId(int memberid);
	
	public abstract String readPassWord(Scanner input);
	
	public double getSpendingRecord() {
		return spendingRecord;
	}
	
	public void setSpendingRecord(double spendingRecord) {
		this.spendingRecord=spendingRecord;
	}
	
//	public int getMemberId() {
//		
//		return memberId;
//		
//	}
//	
//	public void setMemberId(int MemberId) {
//		
//		this.memberId=memberId;
		
	//}
	
	public String getFirstName() {
		
		return firstName;
		
	}
	
	public void setFirstName(String firstName) {
		
		this.firstName=firstName;
		
	}
	
	public String getLastName() {
		
		return lastName;
		
	}
	
	public void setLastName(String lastName) {
		
		this.lastName=lastName;
		
	}
	
	public String getAddress() {
		
		return address;
		
	}
	
	public void setAddress(String address) {
		
		this.address=address;
		
	}
	
	public String getEmail() {
		
		return email;
		
	}
	
	public void setEmail(String email) {
		
		this.email=email;
		
	}
	
	public Date getBirthDay() {
		
		return birthDay;
		
	}
	
	public void setBirthDay(Date birthDay) {
		
		this.birthDay=birthDay;
		
	}
	
	
	public int getTelephone() {
		
		return telephone;
	}
	
	public void setTelephone(int telephone) {
		
		this.telephone=telephone;
		
	}

	
	public void customerInfo(Scanner input) {
		
//		MemberSystem regular=new RegularMember();
		try {
			System.out.print("Please enter your First Name: ");
			firstName=input.nextLine();
			
			System.out.print("Please enter your Last Name: ");
			lastName=input.nextLine();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.print("Please enter your date of birth(dd/MM/yy): ");
			String dob=input.nextLine();
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
			birthDay =formatter.parse(dob);
			displayDoB = formatter.format(birthDay);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		try {
		System.out.print("Please enter your Email address: ");
		email=input.nextLine();
		
		System.out.print("Please enter your Telephone Number: ");
		telephone=input.nextInt();
		input.nextLine();
		
		System.out.print("Please enter your Address: ");
		address=input.nextLine();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.printf("|First Name: %s | Last Name: %s | Email Address: %s | Address: %s | Telephone number: %d | Date of Birth: %s %n|",firstName,lastName,email,address,telephone,displayDoB);
		boolean option=true;
		do {
		System.out.print("Please confirm the information you filled(Y/N):");
		String confirm=input.nextLine();
		if(confirm.equalsIgnoreCase("n")) {
//			System.out.println("Your information as follows: ");
			System.out.println("Which kind of information you want to change?:");
//			this.printDetails();
			System.out.println();
			System.out.println("1. First Name. 2. Last Name. 3. Email. 4. Address. 5. Telephone Number. 6. Date of Birth.");
			int modify=input.nextInt();
			input.nextLine();
			try {
				switch(modify) {
				case 1: 
					System.out.print("Please enter your First Name: ");
					firstName=input.nextLine();
					break;
				case 2:
					System.out.print("Please enter your Last Name: ");
					lastName=input.nextLine();
					break;
				case 3:
					System.out.print("Please enter your Email address: ");
					email=input.nextLine();
					break;
				case 4:
					System.out.print("Please enter your Address: ");
					address=input.nextLine();
					break;
				case 5: 
					System.out.print("Please enter your Telephone Number: ");
					telephone=input.nextInt();
					input.nextLine();
					break;
				case 6:
					try {
						System.out.print("Please enter your date of birth(dd/MM/yy): ");
						String dob=input.nextLine();
						SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yy");
						birthDay =formatter.parse(dob);
						displayDoB = formatter.format(birthDay);
					}catch(ParseException e) {
						e.printStackTrace();
						}
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			option=false;
		}
		}while(option);
		
	}
	
//	public void RegisterMember(Scanner input) {
//
//			System.out.println("Do you want to register a new membership to abtain more benefit of your shopping ?(Y/N)");
//			String newMember=input.nextLine();
//			
//			if(newMember.equalsIgnoreCase("Y")) {
//				
//				this.customerInfo(input);
//				
//				
//			}
//			else if (newMember.equalsIgnoreCase("N")) {
//				System.out.println("Thank you for shopping on our App! Your shopping lis is following: ");
//				
//			}
//		
//	}
	
	
	
	public void printDetails() {
		
		System.out.printf("MemberShip information:%n");
		String star="*";
		System.out.println(star.repeat(100));
		System.out.printf("First Name: %s  | Last Name: %s | Email Address: %s | Address: %s | Date of Birth: %s | %n",firstName,lastName,email,address,displayDoB);
		System.out.println(star.repeat(100));
	}
	
	//for BufferWritter to store customer information
	public String toString() {
		return String.format("First Name: %s | Last Name: %s | Email Address: %s | Address: %s | Date of Birth: %s | Member Points are: %.2f | Member Id: %d | Total Amount: %.2f | Register date: %s |",firstName,lastName,email,address,new SimpleDateFormat("dd/MM/yy").format(birthDay),this.getCountPoints(),this.getMemberId(),this.getTotalAmount(),new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(this.getRegisterTime()));
	}
	
}
