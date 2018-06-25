package genetic;

public interface Chromosome<Type> extends Comparable<Type> {

	Type getData();
	
	Chromosome<Type> mate(Chromosome<Type> other);
	
	Chromosome<Type> mutate();
	
	double getFitness();
}
