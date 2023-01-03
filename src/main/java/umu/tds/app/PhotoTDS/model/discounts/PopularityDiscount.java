package umu.tds.app.PhotoTDS.model.discounts;

import umu.tds.app.PhotoTDS.model.User;

public class PopularityDiscount extends Discount {
	
	private final static double DISCOUNT_RATE = 0.1;
	private final static int POPULAR = 3;
	
	public PopularityDiscount() {
		
	}
	
	public boolean usable(User u) {
		if(u == null)
			return false;
		
		int followers = u.getNumFollowers();
		return (followers > POPULAR);
	}
	
	public int getDiscount(int c, User u) {
		
		int followers = u.getNumFollowers();
		return followers > POPULAR ? c - (int) Math.round(c * DISCOUNT_RATE) : 0;
	}
}
