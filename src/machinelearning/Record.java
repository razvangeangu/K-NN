package machinelearning;

public class Record {
	double[] attributes;
	int classLabel;
	
	public Record(double[] attributes, int classLabel) {
		this.attributes = attributes;
		this.classLabel = classLabel;
	}
	
}