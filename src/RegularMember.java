/*
 * Name: Huijun Bu, YanLin Li.
 * Modified: 20-29 Dec, 2023.
 * Description:
 *  Regular MemberShip information extends Member System class;
 *  Function: Generate Member ID, authentic information confirmation,Member information modify.
 *  
 * */

import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class RegularMember extends MemberSystem {
	
	protected double memberDiscount=0.95;
	protected double memberPointsRate=0.2;
	protected int memberId;
	protected String password;
	protected byte[] salt;//salt for secured hash Pass word.
	protected ArrayList<Integer> memberIdArray = new ArrayList<>();
	protected Date registerTime;
	protected double totalAmount;
	protected double countPoints;
	protected String passWordFilePath="C:\\Users\\BU1\\Desktop\\laptop\\大学课程\\Level 2\\Project\\passWord.txt";
	// keep member is unique;
	protected Set<Integer> uniqueMemberId=new HashSet<>();
	
	//initiate "regularId" as a instance variable.
	protected Random regularId=new Random();
	
	
	public int getMemberId() {
	
	return memberId;
	
	}
	

	public void setMemberId(int memberId) {

		this.memberId=memberId;
		
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public double getMemberDiscount() {
		return memberDiscount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount=totalAmount;
	}
	
	public double getCountPoints() {
		return countPoints;
	}
	
	public void setCountPoints(double countPoints) {
		this.countPoints=countPoints;
	}
	
	public void setMemberDiscount(double memberDiscount) {
		this.memberDiscount=memberDiscount;
	}
	
	public void setMemberPiontsRate(double memberDiscount) {
		this.memberDiscount=memberDiscount;
	}
	
	public Date getRegisterTime() {
        return registerTime;
    }
	
	public void setRegisterTime(Date registerTime) {
		this.registerTime=registerTime;
	}
	            
	
	public void customerInfo(Scanner input) {
		
		super.customerInfo(input);
		
		registerTime=new Date();
		
		
		memberId=assignMemberId();
		
		createPassWord(input);
		storePassWord();
		
	}
	
	//craate Salt
//	public byte[] createSalt() {
//		SecureRandom random = new SecureRandom();
//		salt=new byte[32];
//		random.nextBytes(salt);
//		return salt;
//	}
//	
//	//create hashPassword
//	public String createHashPassWord(String passWord, byte[] salt) {
//		try {
//		MessageDigest hash=MessageDigest.getInstance("SHA-256");
//		hash.update(salt);
//		byte[] hashPassword = hash.digest(passWord.getBytes());
//		return Base64.getEncoder().encodeToString(hashPassword);
//		}catch(NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//		
//	}
	
	//create hashPassWord
//	public String createPassWord(Scanner input) {
//		passWord = input.nextLine();
//		return createHashPassWord(passWord,createSalt());
//	}
	
	public String createPassWord(Scanner input) {
		
		System.out.print("Please enter your password: ");
		passWord=input.nextLine();
		
		
		return passWord;
		
	}
	
	public void storePassWord() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(passWordFilePath,true))){
			
			
			writer.write(passWord);
			writer.write(",");
			writer.write(String.valueOf(memberId));
			writer.newLine();
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String readPassWord(Scanner input) {
	    String memberId = null;
	    String memberPassWord = null;
	    int count = 0;
	    final int maxAttempts = 5;

	    try (BufferedReader reader = new BufferedReader(new FileReader(passWordFilePath))) {
	        while (count < maxAttempts) {
	            System.out.print("Please enter your member Id: ");
	            memberId = input.nextLine();
	            System.out.print("Please enter your password: ");
	            memberPassWord = input.nextLine();

	            boolean loginSuccess = false;
	            
	            

	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] info = line.split(",");
	                if (info.length == 2 && memberId.equals(info[1].trim()) && memberPassWord.equals(info[0].trim())) {
	                    System.out.println("You have logged in.");
	                    loginSuccess = true;
	                    break;
	                }
	            }

	            if (loginSuccess) {
	                break;
	            } else {
	                count++;
	                int attemptsLeft = maxAttempts - count;
	                System.out.printf("Wrong password or Member Id, please try again. You have %d more attempts left!%n", attemptsLeft);
	            }
	        }

	        if (count == maxAttempts) {
	            System.out.println("Input error exceeded limited times!");
	            
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return memberId;
	}
	
	//set unique Id method
	public int assignMemberId() {
		
		int newMemberId;
		do {
			newMemberId=regularId.nextInt(100)+1;
			//use Set to confirm the Id is not duplicated;
		}while(uniqueMemberId.contains(newMemberId));
		
		memberIdArray.add(newMemberId);
		return newMemberId;
	}
	
	
	public void printDetails() {
		
		super.printDetails();
		System.out.printf("Member Points are: %.2f | Member Id: %d | Total Amount: %.2f |",countPoints=totalAmount*memberPointsRate,memberId,totalAmount);
		System.out.println(" Register date: "+registerTime);
		String star="*";
		System.out.println(star.repeat(100));
	}


}
