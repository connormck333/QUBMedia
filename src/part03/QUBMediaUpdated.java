package part03;

import java.awt.Color;

import javax.swing.ImageIcon;

import audio.AudioPlayer;
import console.Console;

public class QUBMediaUpdated {
	
	private static Console con;
	private static AudioManager manager = new AudioManager(null);

	public static void main(String[] args) {
		
		// Set AudioPlayer in AudioManager
		AudioPlayer player = new AudioPlayer();
		manager.setPlayer(player);
		
		con = new Console(true);
		
		// Console setup
		con.setSize(1200, 500);
		con.setVisible(true);
		con.setTitle("QUB Media Updated");
		con.setBgColour(Color.BLACK);
		con.setColour(Color.WHITE);
		
		// Menu setup
		String options[] = {
				"Display All Files",
				"Load Audio File",
				"Delete Audio",
				"Play Audio",
				"Display Top 10 Audio Files",
				"Exit"
		};
		
		int choice = -1;
		while (choice != 6) {
			
			con.setColour(Color.WHITE);
			con.println("QUB Media Updated");
			con.println("+++++++++++++++++");
			
			for (int i=0; i<options.length; i++) {
				con.println(i+1 + ". " + options[i]);
			}
			con.println();
			
			choice = getUserSelection();
			
			switch (choice) {
			case 1:
				displayAllFiles(true);
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
			case 6:
				exitSystem();
				break;
			default:
				con.println("Invalid option: " + choice);
			}
			
			con.setColour(Color.GREEN);
			con.println("\nPress Enter Key to return to menu.");
			con.readLn();
			con.clear();
		}
		
	}
	
	private static void displayAllFiles(boolean displayCover) {
		String data[] = manager.getAllData();
		
		// Check if data is empty array
		if (data.length == 0) {
			con.setColour(Color.RED);
			con.println("No files could be found.\n");
			return;
		}
	
		for (int i=0; i<data.length; i++) {
			con.println(data[i]);
			if (data[i].contains("Album Cover:") && displayCover) {
				String[] current = data[i].split(" ");
				con.println(new ImageIcon(current[current.length-1])); // display album cover image
			}
		}
		
		con.println();
	}
	
	private static void loadFile() {
		// Get title name
		con.print("Enter title: ");
		String title = con.readLn();
		
		// Get duration
		int duration = -1;
		boolean validDuration = false;
		do {
			duration = getInt("Enter duration (in seconds): ");
			
			if (duration < 1) {
				con.setColour(Color.RED);
				con.println("ERROR: Duration cannot be less than 1 second.");
			} else {
				validDuration = true;
			}
			con.setColour(Color.WHITE);
		} while (!validDuration);
		
		// Get data source
		con.print("Enter file path: ");
		String dataSource = con.readLn();
		
		// Allow for option to add to existing or new Album
		con.print("Would you like to add " + title + " to an Album (Y/N): ");
		String albumOption;
		boolean validOption = false; // Only accept "y" or "n"
		
		do {
			albumOption = con.readLn();
			if (albumOption.equals("y") || albumOption.equals("Y") || albumOption.equals("n") || albumOption.equals("N")) {
				validOption = true;
			} else {
				con.setColour(Color.RED);
				con.print("ERROR: Enter 'Y' or 'N': ");
			}
			con.setColour(Color.WHITE);
		} while (!validOption);
		
		if (albumOption.equals("y") || albumOption.equals("Y")) {
			addToAlbum(title, duration, dataSource);
			return;
		}
		
		// Create new object of AudioFile using user input
		AudioFile newAudio = new AudioFile(title, duration, dataSource);
		
		// Check if dataSource exists already
		if (doesFileExist(newAudio)) {
			con.setColour(Color.RED);
			con.println("ERROR: This file already exists.\n");
			con.setColour(Color.WHITE);
			return;
		}
		
		// Save object to ArrayList
		manager.loadAudio(newAudio);
		
		// Send user confirmation message
		con.setColour(Color.GREEN);
		con.println("File has been loaded successfully.\n");
	}
	
	private static void deleteAudio() {
		
		con.println("+--- Delete Audio File ---+");
		
		// Display all files to choose from
		int numOfFiles = getNumOfFiles();
		if (numOfFiles == 0) {
			con.setColour(Color.RED);
			con.println("No files could be found.\n"); // Exit function if no files have been loaded
			return;
		}
		displayAllFiles(false);
		
		// Get code of file to delete
		int fileCode = -1;
		boolean validCode = false;
		do {
			try {
				
				fileCode = getInt("Enter the code of the file you would like to delete: ");
				
				// Range check on user input
				if (!doesCodeExist(fileCode)) {
					con.setColour(Color.RED);
					con.println("ERROR: That file does not exist.");
				} else {
					validCode = true;
				}
			} catch (Exception e) {
				con.setColour(Color.RED);
				con.println("ERROR: You must enter a valid integer.");
			}
			con.setColour(Color.WHITE);
		} while (!validCode);
		
		
		// Delete file
		manager.deleteAudio(fileCode);
		
		// Display confirmation message
		con.setColour(Color.GREEN);
		con.println("File has been deleted successfully.");
	}
	
	private static void playAudio() {
		
		// Display all files to choose from
		int numOfFiles = getNumOfFiles();
		if (numOfFiles == 0) {
			con.setColour(Color.RED);
			con.println("No files could be found.\n"); // Exit function if no files have been loaded
			return;
		}
		displayAllFiles(false);
		
		// Get code of file to play
		int fileCode = -1;
		boolean validCode = false;
		do {
			try { 
				fileCode = getInt("Enter the code of the file you would like to play: ");
				
				if (!doesCodeExist(fileCode)) {
					con.setColour(Color.RED);
					con.println("ERROR: That file does not exist.");
				} else {
					validCode = true;
				}
			} catch (Exception e) {
				con.setColour(Color.RED);
				con.println("ERROR: You must enter a valid integer.");
			}
			con.setColour(Color.WHITE);
		} while(!validCode);
		
		// Play file
		String res = manager.play(fileCode);
		displayImageIcon(fileCode);
		if (res.contains("Couldn't play") || res.contains("Could not find file")) {
			con.setColour(Color.RED);
		} else {
			con.setColour(Color.GREEN);
		}
		
		// Display response from manager
		con.println(res);
	}
	
	private static void displayTopTen() {
		con.println("\n+--- Top 10 Most listened to files ---+\n");
		String topTen = manager.topTen();
		
		// Print error if no files can be found.
		if (topTen == "") {
			con.setColour(Color.RED);
			con.println("ERROR: No files have been loaded yet.\n");
			return;
		}
		
		con.println(topTen);
	}
	
	// Method to close and exit console
	private static void exitSystem() {
		con.setColour(Color.YELLOW);
		con.println("Goodbye!");
		con.readLn();
		con.dispose();
	}
	
	// Get user selection for menu
	private static int getUserSelection() {
		
		boolean valid = false;
		int choice = -1;
		
		do {
			con.print("Enter Selection: ");
			con.setColour(Color.GRAY);
			String strChoice = con.readLn();
			con.setColour(Color.WHITE);
			
			try {
				choice = Integer.parseInt(strChoice);
				if (choice < 1 || choice > 6) {
					
					con.setColour(Color.RED);
					con.println("ERROR: Enter a number between 1 and 6.");
				} else {
					valid = true;
				}
			} catch (Exception e) {
				con.setColour(Color.RED);
				con.println("ERROR: Please enter a number.");
			}
			con.setColour(Color.WHITE);
		} while (!valid);
		
		return choice;
	}
	
	// Method to load a file as an AlbumTrack into AudioManager
	private static void addToAlbum(String title, int duration, String dataSource) {
		// Get album title
		con.print("Enter album title: ");
		String alTitle = con.readLn();
		
		// Get album title
		con.print("Enter album artist: ");
		String alArtist = con.readLn();
		
		// Get album cover image
		con.print("Enter file path to album cover image: ");
		String coverSource = con.readLn();
		ImageIcon alCover = new ImageIcon(coverSource);
		
		// Create album and AlbumTrack and add to AudioManager
		Album newAlbum = new Album(alTitle, alArtist, alCover);
		AlbumTrack track = new AlbumTrack(title, duration, dataSource, newAlbum);
		manager.loadAudio(track);
		
		// Display confirmation message
		con.setColour(Color.GREEN);
		con.println("Successfully loaded file to Album.");
	}
	
	// Method to read a number from Console
	private static int getInt(String message) {
		boolean valid = false;
		int number = -1;
		
		do {
			con.print(message);
			String str = con.readLn();
			
			try {
				number = Integer.parseInt(str);
				valid = true;
			} catch (Exception e) {
				con.setColour(Color.RED);
				con.println("ERROR: Enter a number.");
			}
			
			con.setColour(Color.WHITE);
		} while (!valid);
		
		return number;
	}
	
	// Method to check if file has already been loaded
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
	
	// Method to display an ImageIcon to console
	private static void displayImageIcon(int code) {
		
		String[] data = manager.getAllData();
		for (int i =0; i<data.length; i++) {
			String[] current = data[i].split(" "); // Split String into array by spaces
			
			// Check if code is found in String
			int currentCode = Integer.parseInt(current[1].substring(0, current[1].length() - 1)); // Format code for check
			if (currentCode == code) {
				if (data[i].contains("Album Cover:")) {
					String source = current[current.length - 1];
					con.println(new ImageIcon(source));
				}
			}
		}
	}

}
