package genetic.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp.Fitness;
import genetic.Chromosome;
import genetic.ChromosomeMaker;
import genetic.Nature;
import genetic.Population;

public class BasicPopulation<T> implements Population<T> {

	private List<Chromosome<T>> chromos;
	
	public BasicPopulation() {
		this.chromos = new ArrayList<>();
	}
	
	@Override
	public void addChromo(Chromosome<T> chromo) {
		this.chromos.add(chromo);
	}

	@Override
	public void removeChromo(Chromosome<T> chromo) {
		this.chromos.remove(chromo);
	}
	
	@Override
	public void doGeneration(Nature<T> nature) {
		List<Chromosome<T>> mate = new ArrayList<>(this.chromos.size());
		List<Chromosome<T>> mutate = new ArrayList<>(this.chromos.size());
		
		nature.mate(this.chromos, mate);
		nature.mutate(mate, mutate);
		
		this.chromos = mate;
	}

	@Override
	public List<Chromosome<T>> getChromos(boolean ordered) {
		if (ordered) {
			this.chromos.sort(null);
		}
		
		return this.chromos;
	}

	@Override
	public void addChromos(ChromosomeMaker<T> maker, int amount) {
		List<Chromosome<T>> newChromos = new ArrayList<>(amount);
		
		while (amount-- > 0) {
			newChromos.add(maker.make());
		}
		
		this.chromos.addAll(newChromos);
	}

	@Override
	public Chromosome<T> solution() {
		Chromosome<T> max = Collections.max(this.chromos);
		return max.fitness() == Fitness.FIT ? max : null;
	}
	

}
