package genetic;

import java.util.List;

public interface Nature<T> {

	void mate(List<Chromosome<T>> currGen, List<Chromosome<T>> nextGen);
	
	void mutate(List<Chromosome<T>> currGen, List<Chromosome<T>> nextGen);
}
