package genetic;

import comp.Fitness;

public interface Chromosome<T> extends Fitness {

	T getData();

	Chromosome<T> mate(Chromosome<T> other);

	Chromosome<T> mutate();
	
}
