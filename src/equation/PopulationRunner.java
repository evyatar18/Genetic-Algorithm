package equation;

import java.util.List;

import comp.ProbabilityMaker;
import genetic.Chromosome;
import genetic.Nature;
import genetic.Population;
import genetic.imp.BasicNature;
import genetic.imp.BasicPopulation;

public class PopulationRunner {

	public static void main(String[] args) {
		Population<Equation> p = new BasicPopulation<>();
		
		p.addChromos(new EquationChromoMaker(), 120);
		
		List<Chromosome<Equation>> sols;
		int iter = 0;
		
		Nature<Equation> nature = new BasicNature<>(new ProbabilityMaker<Chromosome<Equation>>());
		
		while ((sols = p.solutions()).size() == 0) {
			p.doGeneration(nature);
			iter++;
		}
		
		System.out.println(p.getChromos(true));
		System.out.println("took " + iter + " iterations");
		System.out.println("solution(s):");
		sols.stream().map(x -> x.getData()).forEach(x -> System.out.println(x));
	}
}
