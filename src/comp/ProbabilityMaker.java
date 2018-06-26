package comp;

import java.util.List;

public class ProbabilityMaker<T extends Fitness> implements ChooserFactory<T> {

	@Override
	public Chooser<T> create(List<T> items) {
		return new ProbabilityChooser<>(items);
	}

}
