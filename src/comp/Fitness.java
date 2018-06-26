package comp;

public interface Fitness extends Comparable<Fitness> {

	int FIT = -1;
	
	int UNDEFINED = -2;
	
	double fitness();
	
	default int compareTo(Fitness o) {
		double fit1 = fitness();
		double fit2 = o.fitness();
		
		if (fit1 == fit2) return 0;
		else if (fit1 == FIT) return 1;
		else if (fit2 == FIT) return -1;
		else if (fit1 == UNDEFINED) return -1;
		else if (fit2 == UNDEFINED) return 1;
		else return Double.compare(fit1, fit2);
	}
}
