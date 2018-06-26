package equation;

import java.util.Random;

import genetic.Chromosome;
import genetic.ChromosomeMaker;

public class EquationChromoMaker implements ChromosomeMaker<Equation> {

	private static final Random RAND = new Random();
	
	private int randomInt() {
		return RAND.nextInt(EquationChromosome.MAX_INT_POW);
	}
	
	@Override
	public Chromosome<Equation> make() {
		Integer[] vals = new Integer[4];
		
		for (int i = 0; i < vals.length; i++) {
			vals[i] = randomInt();
		}
		
		return new EquationChromosome(vals);
	}

}
