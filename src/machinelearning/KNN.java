package machinelearning;

public class KNN {

	public static void main(String[] args) {
		knn("", "", 1);
	}

	// Find K-NN and classify
	public static void knn(String trainingFile, String testFile, int K) {
		// Get the current time

		// Make sure the input arguments are legal

		// Read trainingSet and testingSet

		// Test those TestRecords one by one

		// Calculate the accuracy
		
		// Print the result (after interpretation)

		// Print the accuracy

		// Print the total execution time
	}

	// Find K nearest neighbours of testRecord within trainingSet
	public static TrainRecord[] findKNearestNeighbours(TrainRecord[] trainingSet, TestRecord testRecord,int K) {
		return null;
	}

	// Get the class label by using neighbours
	public static int classify(TrainRecord[] neighbors) {
		return 0;
	}

	// Calculate the distance between two points
	public static double getDistance(Record s, Record e) {
		return 0;
	}
}