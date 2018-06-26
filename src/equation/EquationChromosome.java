package equation;

import java.util.Random;

import genetic.Chromosome;

public class EquationChromosome implements Chromosome<Equation> {

	private static final Random RAND = new Random();
	
	public static final int MAX_INT_POW = 5;
	public static final int TARGET = 97;
	
	private final Equation eq;
	
	public EquationChromosome(Integer[] vals) {
		this.eq = new Equation(vals.length);
		
		for (int i = 0; i < vals.length; i++)
			this.eq.set(i, vals[i]);
	}
	
	@Override
	public Equation getData() {
		return this.eq;
	}
	
	/**
	 * crossover two ints
	 * @param a first int
	 * @param b second int
	 * @param cp the crossover point
	 * @return a crossedovered int
	 */
	private int crossover(int a, int b, int cp) {
		int max = (1 << MAX_INT_POW) - 1;
		
		int mask1 = (1 << cp) - 1;
		int mask2 = max - mask1;
		
		return (a & mask1) + (b & mask2);
	}

	@Override
	public Chromosome<Equation> mate(Chromosome<Equation> other) {
		Integer[] vals = this.eq.vals();
		
		for (int i = 0; i < vals.length; i++) {
			int cp = RAND.nextInt(MAX_INT_POW);
			
			vals[i] = crossover(vals[i], other.getData().vals()[i], cp);
		}

		return new EquationChromosome(vals);
	}

	@Override
	public Chromosome<Equation> mutate() {
		int index = RAND.nextInt(eq.size());
		int flipped = RAND.nextInt(MAX_INT_POW);
		
		int newValue = eq.get(index) ^ (1 << flipped);
		
		eq.set(index, newValue);
		
		return new EquationChromosome(eq.vals());
	}

	private int sum = Integer.MIN_VALUE;
	
	@Override
	public double fitness() {
		if (this.sum == Integer.MIN_VALUE) {
			this.sum = this.eq.calc();
		}
		
		if (this.sum == TARGET)
			return FIT;
		
		return 1 /(float) (Math.abs(TARGET - this.sum));
	}

	@Override
	public String toString() {
		return String.format("fitness: %s, eqn: %s", fitness(), this.eq);
	}
}
