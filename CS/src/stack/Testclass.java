package stack;

import java.util.Random;

public class Testclass {

	
	public static void main(String[] args){
		CStack mystack = new CStack();
		Random generator = new Random();
		
		System.out.println(mystack.get_first());
		
		int number = 10;
		
		for (int i = 0; i < number; i++){
			
			Integer in = generator.nextInt(100);
			mystack.add_value_top(in);
			
		}
		System.out.printf("After adding %d elements\n", number);
		System.out.println(mystack.printPile());
		
		System.out.printf("Size: %d\n", mystack.get_size());
		
		System.out.println("\nShuffling:");
		for (int i = 0; i < 5; i++){
			mystack.shuffle();
			System.out.println(mystack.printPile());
			System.out.println("First: " + mystack.get_first());
			System.out.println("Third: " + mystack.get_n_last(3));
			System.out.println("Last: " + mystack.get_n_last(1));
			System.out.println("2. Last: " + mystack.get_n_last(2));
			
			System.out.println();
		}
		
		
		
	}
}
