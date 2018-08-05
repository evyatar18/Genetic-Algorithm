package comp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FitnessSegment implements SegmentDivision {

	private List<Double> sums;
	private double sum;
	private int total;
	
	public FitnessSegment(Collection<Double> fitnesses) {
		this.sums = new ArrayList<>(fitnesses.size());
		
		sum(fitnesses);
	}
	
	private void sum(Collection<Double> fits) {
		double s = 0;
		int total = 0;
		
		for (Double d : fits) {
			if (d == Fitness.UNDEFINED) {
				continue;
			}			
			else if (d == Fitness.FIT) {
				d = 10d;
			}
			else if (d < 0) {
				throw new IllegalArgumentException("All values must be positive.");
			}
			
			s += d;
			this.sums.add(d);
			
			total++;
		}
		
		this.sum = s;
		this.total = total;
	}
	
	@Override
	public double length() {
		return this.sum;
	}

	@Override
	public double min() {
		return 0;
	}

	@Override
	public double max() {
		return this.sum;
	}

	@Override
	public int getIndex(double val) {
		int index = Collections.binarySearch(this.sums, val);
		
		if (index < 0) {
			index = -index;
			index = index - 1;
		}
		
		return index;
	}

	@Override
	public double getValue(int index) {
		return this.sums.get(index);
	}

	@Override
	public double getLength(int index) {
		if (index == 0) {
			return this.sums.get(0);
		}

		return (this.sums.get(index) - this.sums.get(index - 1));
	}

}
