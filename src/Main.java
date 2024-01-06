import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input=new Scanner(System.in);
		
		System.out.println("Welcome to our shopping app");
			
		Menu menu=new Menu();
		
		menu.readMember();
		
		System.out.println("Please choose a item that you are interested in....");
		
		menu.product();
		
		input.close();
		
	}

}
