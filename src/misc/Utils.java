package misc;

import java.util.List;
import java.util.Random;

public class Utils {

	public static <T> T choose(List<T> list, Random rand) {
		return list.get(rand.nextInt(list.size()));
	}
}
