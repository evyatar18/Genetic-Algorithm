package comp;

import java.util.List;
import java.util.Random;

public class ProbabilityChooser<T extends Estimatable> implements Chooser<T> {

	private List<T> items;
	private double sum;
	private Random random;

	public ProbabilityChooser(List<T> items) {
		this.items = items;
		this.random = new Random();
		sum();
	}
	
	private void sum() {
		this.sum = 0;
		
		for (T item : items) {
			this.sum += item.estimate();
		}
	}

	@Override
	public T choose() {
		double val = this.sum * this.random.nextDouble();
		
		double s = 0;
		
		for (T item : this.items) {
			s += item.estimate();
			
			if (s >= val) {
				return item;
			}
		}
		
		return null;
	}

	

	
}
