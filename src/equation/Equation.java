package equation;

import java.util.ArrayList;
import java.util.List;

public class Equation {

	private final List<Integer> ints;
	private final int size;
	
	public Equation(int size) {
		this.size = size;
		this.ints = new ArrayList<>(size);
		
		while (size-- > 0) {
			this.ints.add(0);
		}
		
	}
	
	public int get(int i) {
		return this.ints.get(i);
	}
	
	public void set(int index, int val) {
		this.ints.set(index, val);
	}
	
	public int calc() {
		int sum = 0;
		
		for (int i = 0; i < this.ints.size(); i++) {
			sum += (i + 1) * this.ints.get(i);
			
		}
		
		return sum;
	}

	public int size() {
		return this.size;
	}

	public Integer[] vals() {
		return this.ints.toArray(new Integer[this.size]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.ints.size(); i++) {
			if (sb.length() > 0)
				sb.append(" + ");
			
			sb.append(String.format("%d*%d", i + 1, this.ints.get(i)));
		}
		
		sb.append(" = " + calc());
		
		return sb.toString();
	}
}
