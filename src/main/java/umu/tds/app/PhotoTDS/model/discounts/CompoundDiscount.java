package umu.tds.app.PhotoTDS.model.discounts;

import java.util.LinkedList;
import java.util.List;

import umu.tds.app.PhotoTDS.model.User;

public class CompoundDiscount extends Discount {

	List<Discount> dcs;
	
	
	public CompoundDiscount(List<Discount> dcs) {
		super();
		this.dcs = dcs;
	}
	
	public CompoundDiscount() {
		this(new LinkedList<>());
	}
	
	public boolean usable(User u) {
		if (u == null)
			return false;
		
		for(Discount d : this.dcs )
			if(!d.usable(u))
				return false;
		return true;
	}

	public int getDiscount(int c, User u) {
		int n = c;
		for(Discount d : dcs)
			n -= d.getDiscount(c, u);
		
		return n;
	}
	
	public void addDiscount(Discount d) {
		this.dcs.add(d);
	}
	
	public void removeDiscount(Discount d) {
		this.dcs.remove(d);
	}
	
	public Discount getIndex(int idx) {
		return this.dcs.get(idx);
	}
}
