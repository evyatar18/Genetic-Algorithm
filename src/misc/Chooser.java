package misc;

import java.util.List;

public interface Chooser {

	<T> T choose(List<T> list);
}
