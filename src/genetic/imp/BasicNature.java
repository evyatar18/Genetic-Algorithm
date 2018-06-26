package genetic.imp;

import java.util.List;
import java.util.Random;

import comp.Chooser;
import comp.ChooserFactory;
import genetic.Chromosome;
import genetic.Nature;

public class BasicNature<T> implements Nature<T> {

	private static final float MATE_CHANCE = .7f;
	private static final float MUTATE_CHANCE = .5f;
	
	private final ChooserFactory<Chromosome<T>> chooserMaker;
	private final Random rand;
	
	public BasicNature(ChooserFactory<Chromosome<T>> chromosomeChooser) {
		this.chooserMaker = chromosomeChooser;
		this.rand = new Random();
	}
	
	@Override
	public void mate(List<Chromosome<T>> currGen, List<Chromosome<T>> nextGen) {
		Chooser<Chromosome<T>> chooser = this.chooserMaker.create(currGen);
		
		while (nextGen.size() < currGen.size()) {
			Chromosome<T> c = chooser.choose();
			
			if (this.rand.nextFloat() < MATE_CHANCE) {
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
			
			if (this.rand.nextFloat() < MUTATE_CHANCE) {
				c = c.mutate();
			}
			
			nextGen.add(c);
		}
	}


}
