package machinelearning;

public class TestRecord extends Record {
	public int predictedLabel;
	
	public TestRecord(double[] attributes, int classLabel) {
		super(attributes, classLabel);
	}
}