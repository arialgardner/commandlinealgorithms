import java.util.Scanner;

class algorithmsproject {
	// QUICKSORT
	static String indicator;
	public static void main(String args[]) {
		
		// QUICKSORT MAIN
		System.out.println("\n**QUICKSORT**\n");
		System.out.println("How many elements would you like to sort?");
		Scanner input1 = new Scanner(System.in);
		int len = input1.nextInt();
		int[] numbersToSort = new int[len];

		Scanner input = new Scanner(System.in);
		for (int i = 0; i < numbersToSort.length; i++) {
			System.out.println("Enter number " + (i+1));
			numbersToSort[i] = input.nextInt();
		}
		
		System.out.print("\nYou wanted to sort: ");
		for (int i = 0; i < numbersToSort.length; i++) {
			if (i == numbersToSort.length - 1) {
				System.out.print(numbersToSort[i] + "\n");
				break;
			}
			System.out.print(numbersToSort[i] + ",");
		}	
		
		int numNumbers = numbersToSort.length;
		recursiveSort(numbersToSort, 0, numNumbers - 1);
		
		System.out.print("\nThe sorted result is: ");
		printSolution(numbersToSort);
		System.out.println();
		System.out.println();
		
		
		// GAUSSIAN ELIMINATION MAIN
		Scanner input2 = new Scanner(System.in);
		System.out.println("\n**GAUSSIAN ELIMINATION**\n");
		System.out.println("Enter the number of variables in your equations");
		int numVars = input2.nextInt();

		double[] sols = new double[numVars];
		double[][] coeff = new double[numVars][numVars];

		for (int i = 1; i < numVars + 1; i++) {
			System.out.println("Enter " + numVars + " Equation " + i + "'s coefficients ");
			for (int j = 0; j < numVars; j++)
				coeff[i - 1][j] = input2.nextDouble();
		}

		System.out.println("Enter the " + numVars + " solutions for your equations");
		for (int i = 0; i < numVars; i++)
			sols[i] = input2.nextDouble();
		solve(coeff, sols);
		
		
		input.close();
		input1.close();
		input2.close();
	}

	
	
	
	
	// QUICKSORT
	static void recursiveSort(int nums[], int low, int high) {
		if (low < high) {
			int partitioningIndex = setPivot(nums, low, high);
			recursiveSort(nums, low, partitioningIndex - 1);
			recursiveSort(nums, partitioningIndex + 1, high);
		}
		
	}
	static int setPivot(int nums[], int lower, int higher) {
		int pivot = nums[higher];
		int i = (lower-1);
		for (int j = lower; j < higher; j++) {
			if (nums[j] < pivot) {
				i = i+1;
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}
		int temp = nums[i+1];
		nums[i + 1] = nums[higher];
		nums[higher] = temp;
		return i+1;
	}
	static void printSolution(int nums[]) {
		int len = nums.length;
		for (int i = 0; i < len; ++i) {
			if (i == len-1) {
				System.out.print(nums[i]);
				break;
			}
		System.out.print(nums[i] + ",");
		}
	}

	
	
	
	
	
	
	// GAUSSIAN ELIMINATION
	static void solve(double[][] coefficients, double[] solutions) {
		
		// for j = 1,2,...n-1 do
			// for i = j+1,j+1,...n do
				// mij = aij/aij
				// for k = j+1, j+2,...n do
					//aik = aik-mijajk
				// end
				// bi = bi - mijbj
			//end
		//end
		
		int solutionsLen = solutions.length;
		for (int k = 0; k < solutionsLen; k++) {
			int max = k;
			for (int i = k + 1; i < solutionsLen; i++)
				if (Math.abs(coefficients[i][k]) > Math.abs(coefficients[max][k]))
					max = i;		
			double[] temp = coefficients[k];
			coefficients[k] = coefficients[max];
			coefficients[max] = temp;
			double t = solutions[k];
			solutions[k] = solutions[max];
			solutions[max] = t;
			for (int i = k + 1; i < solutionsLen; i++) { 
				double factor = coefficients[i][k] / coefficients[k][k]; 
				solutions[i] -= factor * solutions[k]; 
				for (int j = k; j < solutionsLen; j++) 
					coefficients[i][j] -= factor * coefficients[k][j]; 
			}
		}

		// back substitution
		 // for i = n,n-1...1 do
			// xi = yi
			// for j = i+1, i+1....n do
				// xi = xi-uijxj
			// end
			// xi = xi/uij
		//end
		double[] solution = new double[solutionsLen];
		for (int i = solutionsLen-1; i >= 0; i--) {
			double sum = 0.0; 
			for (int j = i+1; j < solutionsLen; j++) 
				sum += coefficients[i][j] * solution[j];
			double a = solutions[i]-sum;
			double b = coefficients[i][i];
			solution[i] = a / b; 
		}
		
		int sLen = solutions.length;
		System.out.println("\n\nYour solution is: ");
		for (int i = 0; i < sLen; i++) {
			double v =  solution[i];
			v = (double)Math.round(v*1000d) / 1000d;
			System.out.print(v + " ");
		}
		System.out.println("\n\nAnd the final matrix is: ");
		for (int x = 0; x < sLen; x++) {
			for (int y = 0; y < sLen; y++) {
				double value =  coefficients[x][y];
				value = (double)Math.round(value*1000d) / 1000d;
				System.out.print(value + " ");
			}
			double value2 =  solutions[x];
			value2 = (double)Math.round(value2*1000d) / 1000d;
			System.out.println("|" + value2);
		}
	}
}
