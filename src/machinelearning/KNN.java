package machinelearning;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class KNN {

	public static void main(String[] args) {
		knn("src/datasets/iris_train.txt", "src/datasets/iris_test.txt", 1);
	}

	// Find K-NN and classify
	public static void knn(String trainingFile, String testFile, int K) {
		// Get the current time
		final long startTime = System.currentTimeMillis();

		// Make sure the input arguments are legal
		if (K <= 0) {
			System.err.println("The K number is illegal");
			return;
		}

		try {
			// Read trainingSet and testingSet
			TrainRecord[] trainingSet = FileManager.readTrainFile(trainingFile);
			TestRecord[] testingSet = FileManager.readTestFile(testFile);
			
			// Test those TestRecords one by one
			for (int i = 0; i < trainingSet.length; i++) {
				TrainRecord[] neighbours = findKNearestNeighbours(trainingSet, testingSet[i], K);
				int classLabel = classify(neighbours);
				testingSet[i].predictedLabel = classLabel;
			}

			// Calculate the accuracy
			
			
			// Print the result (after interpretation)
			for (int i = 0; i < testingSet.length; i++ ) {
				if (i == 1) {
					System.out.println("Test is Iris-setosa");
				}
				
				if (i == 2) {
					System.out.println("Test is Iris-versicolor");
				}
				
				if (i == 3) {
					System.out.println("Test is Iris-virginica");
				}
			}

			// Print the accuracy
			

			// Print the total execution time
			final long endTime = System.currentTimeMillis();
			System.out.println("The total execution time is: " + (endTime - startTime) / 1000 + " seconds.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// Find K nearest neighbours of testRecord within trainingSet
	public static TrainRecord[] findKNearestNeighbours(TrainRecord[] trainingSet, TestRecord testRecord, int K) {
		int numOfAttributesForTraining = trainingSet.length;
		
		TrainRecord[] neighbours = new TrainRecord[K];
		
		// Computing the distance and adding k neighbours to the array
		for (int index = 0; index < K; index++) {
			trainingSet[index].distance = getDistance(trainingSet[index], testRecord);
			neighbours[index] = trainingSet[index];
		}
		
		// Go through the remaining records from the training set
		for (int i = K; i < numOfAttributesForTraining; i++) {
			// Getting all the distances for each record on the graph  
			trainingSet[i].distance = getDistance(trainingSet[i], testRecord);
			
			// Saving the index of the next close neighbour;
			int maxIndex = 0;
			for (int index = 1; index < K; index++) {
				
				// The next closest neighbour based on the distance of the last neighbour found
				// from the neighbours test
				if (neighbours[index].distance > neighbours[maxIndex].distance) {
					maxIndex = index;
				}
			}
			
			// Check to see if the neighbour is closer than the last neighbour
			// Add it to the neighbours array
			if (neighbours[maxIndex].distance > trainingSet[i].distance) {
				neighbours[maxIndex] = trainingSet[i];
			}
		}
		
		return neighbours;
	}

	// Get the class label by using neighbours
	public static int classify(TrainRecord[] neighbours) {
		
		// Step 1: Iterate through the neighbours
		// Step 2: Create a map with the labels (classification name)
		// that points to a weight of the label.
		// Step 3: Check whether the label is part of the map and if yes, update its weight
		// otherwise add it to the map
		// Step 4: find the most likely label using the similarity
		
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		
		for (int index = 0; index < neighbours.length; index++) {
			TrainRecord temp = neighbours[index];
			int key = temp.classLabel;
			
			if (!map.containsKey(key)) {
				map.put(key, 1 / temp.distance);
			} else {
				double value = map.get(key);
				value += 1 / temp.distance;
				map.put(key, value);
			} 
			
		}
		
		// find the most likely label using the similarity
		double maxSimilarity = 0;
		int returnLabel = -1;
		Set<Integer> labelSet = map.keySet();
		Iterator<Integer> labelIterator = labelSet.iterator();
		
		while (labelIterator.hasNext()) {
			int label = labelIterator.next();
			double value = map.get(label);
			if (value > maxSimilarity) {
				maxSimilarity = value;
				returnLabel = label;
			}
		}
		
		return returnLabel;
	}

	// Calculate the distance between two records
	public static double getDistance(Record s, Record e) {
		int numOfAttributes = s.attributes.length;
		double result = 0;
		
		for (int i = 0; i < numOfAttributes; i++) {
			result += Math.pow(s.attributes[i] - e.attributes[i], 2);
		}
		
		return Math.sqrt(result);
	}
}