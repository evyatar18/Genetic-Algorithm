package genetic;

import comp.Estimatable;

public interface Chromosome<Type> extends Comparable<Type>, Estimatable{

	Type getData();
	
	Chromosome<Type> mate(Chromosome<Type> other);
	
	Chromosome<Type> mutate();
	
	double getFitness();
	
	
	default double estimate() {
		return getFitness();
	}
}
