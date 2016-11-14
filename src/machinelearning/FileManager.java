package machinelearning;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
	
	// read training files
	public static TrainRecord[] readTrainFile(String fileName) throws IOException{
		File file = new File(fileName);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);

		// read file
		int NumOfSamples = scanner.nextInt();
		int NumOfAttributes = scanner.nextInt();
		scanner.nextLine();
		
		// Transform data from file into TrainRecord objects
		TrainRecord[] records = new TrainRecord[NumOfSamples];
		int index = 0;
		while(scanner.hasNext()){
			double[] attributes = new double[NumOfAttributes];
			int classLabel = -1;
			
			// Read a whole line for a TrainRecord
			for(int i = 0; i < NumOfAttributes; i ++){
				attributes[i] = scanner.nextDouble();
			}
			
			// Read classLabel
			classLabel = (int) scanner.nextDouble();
			
			records[index] = new TrainRecord(attributes, classLabel);
			index ++;
		}
		
		return records;
	}
	
	
	public static TestRecord[] readTestFile(String fileName) throws IOException{
		File file = new File(fileName);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);

		// Read file
		int NumOfSamples = scanner.nextInt();
		int NumOfAttributes = scanner.nextInt();
		scanner.nextLine();
		
		TestRecord[] records = new TestRecord[NumOfSamples];
		int index = 0;
		while(scanner.hasNext()){
			double[] attributes = new double[NumOfAttributes];
			int classLabel = -1;
			
			// Read a whole line for a TestRecord
			for(int i = 0; i < NumOfAttributes; i ++){
				attributes[i] = scanner.nextDouble();
			}
			
			// Read the true label of a TestRecord which is later used for validation
			classLabel = (int) scanner.nextDouble();
			
			records[index] = new TestRecord(attributes, classLabel);
			index ++;
		}
		
		return records;
	}
}
