package genetic.imp;

import java.util.List;

import genetic.Chromosome;
import genetic.Population;

public class BasicPopulation<T> implements Population<T> {

	private List<Chromosome<T>> chromos;
	
	@Override
	public void addChromo(Chromosome<T> chromo) {
		this.chromos.add(chromo);
	}

	@Override
	public void removeChromo(Chromosome<T> chromo) {
		this.chromos.remove(chromo);
	}

	
	
	@Override
	public void doGeneration() {
		
	}

	@Override
	public List<Chromosome<T>> getChromos(boolean ordered) {
		if (ordered) {
			this.chromos.sort(null);
		}
		
		return this.chromos;
	}
	

}
