package org.barrypress.wizdcc.util;

import java.util.Random;

public class Dice {
	
	public static int dX(int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - 1) + 1) + 1;
	    return randomNum;
	}
	
	public static int d6() {
		return dX(6);
	}
	
	public static int d4() {
		return dX(4);
	}
	
	public static int d6(int num) {
		int sum = 0;
		for (int x = 0; x < num; x++) {
			sum += d6();
		}
		return sum;
	}

}
