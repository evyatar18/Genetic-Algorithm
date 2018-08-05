package comp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProbabilityChooser<T extends Fitness> implements Chooser<T> {

	private List<T> items;
	private double sum;
	private Random random;
	
	private final SegmentDivision sd;

	public ProbabilityChooser(List<T> items) {
		this.items = items;
		this.sd = new FitnessSegment(items.stream().map(x -> x.fitness()).collect(Collectors.toList()));
		this.random = new Random();
		sum();
	}
	
	private List<T> sols = new ArrayList<>();
	
	private void sum() {
		this.sum = 0;
		
		for (T item : this.items) {
			double fitness = item.fitness();
			
			if (fitness == Fitness.FIT) {
//				this.sols.add(item);
				this.sum += 3d;
			} else {
				if (fitness != Fitness.UNDEFINED) {
					this.sum += fitness;
				}
			}
		}
	}

	@Override
	public T choose() {
		double val = this.sd.length() * this.random.nextDouble();
		
		double s = 0;
		
		// if there are solutions
//		if (this.sols.size() > 0) {
//			return this.sols.get(this.random.nextInt(this.sols.size()));
//		}
		
//		for (T item : this.items) {
//			double fit = item.fitness();
//			
//			if (fit == Fitness.UNDEFINED) {
//				continue;
//			}
//			
//			s += fit;
//			
//			if (s >= val) {
//				return item;
//			}
//		}
		
		int index = this.sd.getIndex(val);
		int maxIndex = this.items.size() - 1;
		
		if (index > maxIndex) {
			index = maxIndex;
		}
		
		return this.items.get(index);
	}

}
