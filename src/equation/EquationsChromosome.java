package equation;

import java.util.Random;

import genetic.Chromosome;

public class EquationsChromosome implements Chromosome<Equation[]> {

	private static final Random RAND = new Random();

	public static final int MAX_INT_POW = 5;
	public static final int TARGET = 186;

	private final Equation[] eqs;

	public EquationsChromosome(Integer[] vals) {		
		this.eqs = new Equation[] { new Equation(vals.length), new Equation(vals.length) };

		for (int i = 0; i < vals.length; i++) {
			this.eqs[0].set(i, vals[i]);
			this.eqs[1].set(i, vals[(i + 1) % vals.length]);
		}
	}

	@Override
	public Equation[] getData() {
		return this.eqs;
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
	public Chromosome<Equation[]> mate(Chromosome<Equation[]> other) {
		Integer[] vals = this.eqs[0].vals();

		for (int i = 0; i < vals.length; i++) {
			int cp = RAND.nextInt(MAX_INT_POW);

			vals[i] = crossover(vals[i], other.getData()[0].vals()[i], cp);
		}

		return new EquationsChromosome(vals);
	}

	@Override
	public Chromosome<Equation[]> mutate() {
		int index = RAND.nextInt(eqs[0].size());
		int flipped = RAND.nextInt(MAX_INT_POW);

		int newValue = eqs[0].get(index) ^ (1 << flipped);

		eqs[0].set(index, newValue);

		return new EquationsChromosome(eqs[0].vals());
	}

	private int sum1 = Integer.MIN_VALUE;
	private int sum2 = Integer.MIN_VALUE;

	@Override
	public double fitness() {
		this.sum1 = this.eqs[0].calc();
		this.sum2 = this.eqs[1].calc();

		if (this.sum1 == TARGET && this.sum2 == TARGET) {
			return FIT;
		}

		int diff1 = Math.abs(TARGET - this.sum1);
		int diff2 = Math.abs(TARGET - this.sum2);

		return 1 /(float) (diff1 + diff2);
	}

	@Override
	public String toString() {
		return String.format("fitness: %s, eqn1: %s, eqn2: %s", fitness(), this.eqs[0], this.eqs[1]);
	}
}
