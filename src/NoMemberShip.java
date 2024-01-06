import java.util.Date;
import java.util.Scanner;

public class NoMemberShip //extends MemberSystem
	{
	
	protected double memberDiscount=1;
	protected Date registerTime;
	
	public NoMemberShip() {
		
		
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
	
	public void customerInfo(Scanner input) {
		
		//super.customerInfo(input);
		
		registerTime=new Date();
		
		System.out.print(" | Register date: "+registerTime);
		
	}

}
