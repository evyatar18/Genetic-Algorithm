package genetic.imp;

import java.util.List;
import java.util.Random;

import comp.Chooser;
import comp.ChooserFactory;
import genetic.Chromosome;
import genetic.Nature;

public class BasicNature<T> implements Nature<T> {

	private static final float DEFAULT_MATE_CHANCE = .7f;
	private static final float DEFAULT_MUTATE_CHANCE = .25f;
	
	private final ChooserFactory<Chromosome<T>> chooserMaker;
	private final Random rand;
	private final float mateChance;
	private final float mutateChance;
	
	public BasicNature(ChooserFactory<Chromosome<T>> chromosomeChooser) {
		this(chromosomeChooser, DEFAULT_MATE_CHANCE, DEFAULT_MUTATE_CHANCE);
	}
	
	public BasicNature(ChooserFactory<Chromosome<T>> chromosomeChooser,
				float mateChance, float mutateChance) {
		this.chooserMaker = chromosomeChooser;
		this.rand = new Random();
		this.mateChance = mateChance;
		this.mutateChance = mutateChance;
	}
	
	@Override
	public void mate(List<Chromosome<T>> currGen, List<Chromosome<T>> nextGen) {
		Chooser<Chromosome<T>> chooser = this.chooserMaker.create(currGen);
		
		while (nextGen.size() < currGen.size()) {
			Chromosome<T> c = chooser.choose();
			
			if (this.rand.nextFloat() < this.mateChance) {
				c = c.mate(chooser.choose());
			}
			
			nextGen.add(c);
		}
	}

	@Override
	public void mutate(List<Chromosome<T>> currGen, List<Chromosome<T>> nextGen) {
		Chooser<Chromosome<T>> chooser = this.chooserMaker.create(currGen);
		
		while (nextGen.size() < currGen.size()) {
			Chromosome<T> c = chooser.choose();
			
			if (this.rand.nextFloat() < this.mutateChance) {
				c = c.mutate();
			}
			
			nextGen.add(c);
		}
	}


}
