class Fibonacci{

	public static void main(String[] args){
		boolean[] test = new boolean[8];

		//test for isPrime
		test[0]  = (isPrime(0) == false);
		test[1] = (isPrime(2) == true);
		test[2] = (isPrime(7) == true);
		test[3] = (isPrime(15) == false);

		test[4]  = (getFibonacci(1) == 1);
		test[5]  = (getFibonacci(3) == 2);
		test[6]  = (getFibonacci(5) == 5);
		test[7]  = (getFibonacci(8) == 21);


		boolean testFlag=true;
		for (int i = 0; i < test.length; i++){
			if(test[i]){
				System.out.println("test " + i + " passed");
			}
			
			else{
				System.out.println("test " + i + " failed");
				testFlag = false;
			}
		}
		
		if(testFlag ){
				System.out.println("All tests are successful");
			}
			
		else{
			System.out.println("Not all tests are successful");
		}

	}

	//method that determines if the number x is prime
	public static boolean isPrime(int x){
		if(x <= 1 ){
			return false;
		}
		boolean prime = true;
		for(int i = 2; i < x; i++){
			if (x%i==0) {
				prime = false;
			}
		}
		return prime;
	}

	//returns the fibonacci number at the position in parameter
	public static int getFibonacci(int position){
		int num = 0;
		int num2 = 1;
		int fibonacci = 0;
		if (position == 1) {
			fibonacci = 1;
			num = fibonacci;
			return num;
		} else {
			for (int i = 2; i <= position; i++) {
				fibonacci = num + num2;
				num = num2;
				num2 = fibonacci;
			}
		}
		return fibonacci;

	}
	

}