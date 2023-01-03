package umu.tds.app.PhotoTDS.model.discounts;

import umu.tds.app.PhotoTDS.model.User;

public class AgeDiscount extends Discount {
	
	// 18 y/old --> 20% discount
	// 27 y/old --> 2 percent discount

	private final static double DISCOUNT_PER_AGE = 0.02;
	private final static int MIN_AGE = 18;
	private final static int MAX_AGE = 28;
	
	public AgeDiscount() {
		
	}
	
	public boolean usable(User u) {
		if(u == null)
			return false;
		
		int edad = u.getAgeYears();
		return (edad >= MIN_AGE || edad <= MAX_AGE);	
	}
	
	public int getDiscount(int c, User u) {
		
		if(u == null)
			return 0;
		
		int edad = u.getAgeYears();
		
		if(edad < MIN_AGE || edad > MAX_AGE)
			return 0;
		
		int x = MAX_AGE - edad;
			
		return Math.abs(c - (int) Math.round(c * x * DISCOUNT_PER_AGE)); 
	}
}
