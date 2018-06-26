package equation;

import comp.ProbabilityMaker;
import genetic.Chromosome;
import genetic.Nature;
import genetic.Population;
import genetic.imp.BasicNature;
import genetic.imp.BasicPopulation;

public class PopulationRunner {

	public static void main(String[] args) {
		Population<Equation> p = new BasicPopulation<>();
		
		p.addChromos(new EquationChromoMaker(), 40);
		
		Chromosome<Equation> sol;
		int iter = 0;
		
		while ((sol = p.solution()) == null) {
			Nature<Equation> nature = new BasicNature<>(new ProbabilityMaker<Chromosome<Equation>>());
			p.doGeneration(nature);
			
			System.out.println(p.getChromos(true));
			
			iter++;
		}
		
		System.out.println("took " + iter + " iterations");
		System.out.println("solution: " + sol.getData());
	}
}
