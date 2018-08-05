package comp;

public interface SegmentDivision {

	double length();
	
	double min();
	
	double max();
	
	int getIndex(double val);
	
	double getValue(int index);
	
	double getLength(int index);
}
