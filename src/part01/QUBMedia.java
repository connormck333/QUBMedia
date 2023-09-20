package part01;

import audio.AudioPlayer;
import java.util.Scanner;
import menu.Menu;

public class QUBMedia {
	
	private static Scanner input;
	private static AudioManager manager = new AudioManager(null);

	public static void main(String[] args) {
		AudioPlayer player = new AudioPlayer();
		manager.setPlayer(player);
		
		input = new Scanner(System.in);
		
		// Menu setup
		String options[] = {
				"Display All Files",
				"Load Audio File",
				"Delete Audio",
				"Play Audio",
				"Display Top 10 Audio Files",
				"Exit"
		};
		Menu audioMenu = new Menu("QUBMedia", options);

		int choice = audioMenu.getUserChoice();
		while (choice != 6) {
			
				switch (choice) {
				case 1:
					displayAllFiles();
					break;
				case 2:
					loadFile();
					break;
				case 3:
					deleteAudio();
					break;
				case 4:
					playAudio();
					break;
				case 5:
					displayTopTen();
					break;
				default:
					System.out.println("Invalid option: " + choice);
				}
			
			choice = audioMenu.getUserChoice();
		}
		System.out.println("Good bye!");

	}
	
	public static void displayAllFiles() {
		String data[] = manager.getAllData();
		
		// Check if data is empty array
		if (data.length == 0) {
			System.out.println("No files could be found.\n");
			return;
		}
		
		for (int i=0; i<data.length; i++) {
			System.out.println(data[i]);
		}
	}
	
	public static void loadFile() {
		// Get title name
		System.out.print("Enter title: ");
		String title = input.nextLine();
		
		// Get duration
		int duration = -1;
		boolean validDuration = false;
		do {
			try {
				System.out.print("Enter duration (in seconds): ");
				duration = input.nextInt();
				
				if (duration <= 0) {
					System.out.println("ERROR: Duration can not be less than 1 second.");
				} else {
					validDuration = true;
				}
			} catch (Exception e) {
				System.out.println("ERROR: You must enter a valid integer.");
			}
			input.nextLine();
		} while (!validDuration);
		
		// Get data source
		System.out.print("Enter file path: ");
		String dataSource = input.nextLine();
		
		// Create new object of AudioFile using user input
		AudioFile newAudio = new AudioFile(title, duration, dataSource);
		
		// Check if dataSource exists already
		if (doesFileExist(newAudio)) {
			System.out.println("ERROR: This file already exists.\n");
			return;
		}
		
		// Save object to ArrayList
		manager.loadAudio(newAudio);
		
		// Send user confirmation message
		System.out.println("File has been loaded successfully.\n");
	}
	
	public static void deleteAudio() {
		
		System.out.println("+--- Delete Audio File ---+");
		
		// Display all files to choose from
		int numOfFiles = getNumOfFiles();
		if (numOfFiles == 0) {
			System.out.println("No files could be found.\n"); // Exit function if no files have been loaded
			return;
		}
		displayAllFiles();
		
		// Get code of file to delete
		int fileCode = -1;
		boolean validCode = false;
		do {
			System.out.print("Enter the code of the file you would like to delete: ");
			try {
				fileCode = input.nextInt();
				
				// Range check on user input
				if (!doesCodeExist(fileCode)) {
					System.out.println("ERROR: That file does not exist.");
				} else {
					validCode = true;
				}
			} catch (Exception e) {
				System.out.println("ERROR: You must enter a valid integer.");
			}
			input.nextLine();
		} while (!validCode);
		
		
		// Delete file
		manager.deleteAudio(fileCode);
		
		// Display confirmation message
		System.out.println("File has been deleted successfully.");
	}
	
	public static void playAudio() {
		
		// Display all files to choose from
		int numOfFiles = getNumOfFiles();
		if (numOfFiles == 0) {
			System.out.println("No files could be found.\n"); // Exit function if no files have been loaded
			return;
		}
		displayAllFiles();
		
		// Get code of file to play
		int fileCode = -1;
		boolean validCode = false;
		do {
			System.out.print("Enter the code of the file you would like to play: ");
			try { 
				fileCode = input.nextInt();
				
				if (!doesCodeExist(fileCode)) {
					System.out.println("ERROR: That file does not exist.");
				} else {
					validCode = true;
				}
			} catch (Exception e) {
				System.out.println("ERROR: You must enter a valid integer.");
			}
			input.nextLine();
		} while(!validCode);
		
		// Play file
		String res = manager.play(fileCode);
		System.out.println(res);
	}
	
	public static void displayTopTen() {
		System.out.println("+--- Top 10 Most listened to files ---+\n");
		String topTen = manager.topTen();
		
		// Print error if no files can be found.
		if (topTen == "") {
			System.out.println("ERROR: No files have been loaded yet.\n");
			return;
		}
		
		System.out.println(topTen);
	}
	
	
	// Private Methods
	
	private static int getNumOfFiles() {
		// Return length of audioData
		
		return manager.getAllData().length;
	}
	
	private static boolean doesCodeExist(int code) {
		// Check if code exists in audioManager
		
		String[] data = manager.getAllData();
		for (int i=0; i<data.length; i++) {
			String[] current = data[i].split(" "); // Split String into array by spaces
			
			// Check if code is found in String
			int currentCode = Integer.parseInt(current[1].substring(0, current[1].length() - 1)); // Format code for check
			if (currentCode == code) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean doesFileExist(AudioFile file) {
		
		String[] data = manager.getAllData();
		for (int i=0; i<data.length; i++) {
			String[] current = data[i].split(" "); // Split String into array by spaces
			
			// Check if dataSource is found in String
			String currentSource = current[current.length - 1];
			if (currentSource.equals(file.getDataSource())) {
				return true;
			}
		}
		
		return false;
	}

}
