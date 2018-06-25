package genetic;

import java.util.List;

/**
 * {@link Population} is a population of chromosomes.
 * 
 * @param <T> : the type of each chromosome
 */
public interface Population<T> {

	/**
	 * Add a chromosome to the population.
	 * @param chromo the chromosome
	 */
	void addChromo(Chromosome<T> chromo);
	
	/**
	 * Remove a chromosome.
	 * @param chromo the chromosome
	 */
	void removeChromo(Chromosome<T> chromo);
	
	/**
	 * Do one generation.
	 */
	void doGeneration();
	
	/**
	 * Get the chromosomes inside this population.
	 * @param ordered whether to order the chromosomes
	 * @return a list of chromosomes
	 */
	List<Chromosome<T>> getChromos(boolean ordered);
}
