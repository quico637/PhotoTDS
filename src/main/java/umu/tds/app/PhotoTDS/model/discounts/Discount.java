package umu.tds.app.PhotoTDS.model.discounts;

import java.util.LinkedList;
import java.util.List;

import umu.tds.app.PhotoTDS.model.User;

public abstract class Discount {
		
	public static List<Discount> getPossibleDiscounts() {
		List<Discount> l = new LinkedList<>();
		
		l.add(new AgeDiscount());
		l.add(new PopularityDiscount());
		l.add(new CompoundDiscount());
		
		return l;
	}
	
	public abstract boolean usable(User u);
	
	public abstract int getDiscount(int c, User u);
}
