package comp;

import java.util.List;

public interface ChooserFactory<T> {

	Chooser<T> create(List<T> items);
}
