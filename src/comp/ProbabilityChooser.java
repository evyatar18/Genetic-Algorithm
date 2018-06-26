package comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProbabilityChooser<T extends Fitness> implements Chooser<T> {

	private List<T> items;
	private double sum;
	private Random random;

	public ProbabilityChooser(List<T> items) {
		this.items = items;
		this.random = new Random();
		sum();
	}
	
	private List<T> sols = new ArrayList<>();
	
	private void sum() {
		this.sum = 0;
		
		for (T item : items) {
			double fitness = item.fitness();
			
			if (fitness == Fitness.FIT) {
				this.sols.add(item);
			} else {
				if (fitness != Fitness.UNDEFINED) {
					this.sum += fitness;
				}
			}
		}
	}

	@Override
	public T choose() {
		double val = this.sum * this.random.nextDouble();
		
		double s = 0;
		
		// if there are solutions
		if (this.sols.size() > 0) {
			return this.sols.get(this.random.nextInt(this.sols.size()));
		}
		
		for (T item : this.items) {
			double fit = item.fitness();
			
			if (fit == Fitness.UNDEFINED) {
				continue;
			}
			
			s += fit;
			
			if (s >= val) {
				return item;
			}
		}
		
		return this.items.get(this.items.size() - 1);
	}

}
