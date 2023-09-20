package part02;

import audio.AudioPlayer;

public class IntegrationTesting {

	public static void main(String[] args) {
		
		testCase10();
		System.out.println();
		testCase11();
		System.out.println();
		testCase12();
		System.out.println();
		testCase13();
		System.out.println();
		testCase14();
		System.out.println();
		testCase15();
		System.out.println();
		testCase16();
		System.out.println();
		testCase17();
		System.out.println();
		testCase18();
		System.out.println();
		testCase19();
		System.out.println();
		testCase20();
		System.out.println();
		testCase21();
		System.out.println();
		testCase22();
		System.out.println();
		testCase23();
		System.out.println();
		testCase24();
	}
	
	private static void testCase10() {
		// Construction test of AudioManager with valid data
		
		System.out.println("+--- TC10 - Construction test of AudioManager with valid data ---+\n");
		
		// Preconditions
		AudioPlayer myPlayer = new AudioPlayer();
		
		try {
			AudioManager manager = new AudioManager(myPlayer);
			System.out.println("Successfully constructed AudioManager.");
		} catch (Exception e) {
			System.out.println("Exception - construction failed.");
		}
		
		System.out.println("\n+--- End of TC10 ---+");
	}
	
	private static void testCase11() {
		// Construction test of AudioManager with invalid data
		
		System.out.println("+--- TC11 - Construction test of AudioManager with valid data ---+\n");
		
		// Preconditions
		AudioPlayer myPlayer = null;
		
		try {
			AudioManager manager = new AudioManager(myPlayer);
			System.out.println("Successfully constructed AudioManager.");
		} catch (Exception e) {
			System.out.println("Exception - construction failed.");
		}
		
		System.out.println("\n+--- End of TC11 ---+");
	}

	private static void testCase12() {
		// Test loadAudio method in AudioManager
		
		System.out.println("+--- TC12 - Test of loadAudio() ---+\n");
		
		// Preconditions
		AudioFile myFile = new AudioFile("My Audio", 30, "/MyFile.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		try {
			manager.loadAudio(myFile);
			
			System.out.println("loadAudio method executed without exception.");
			System.out.println("myFile should be added to audioData. See AudioManager.getAllData() below:\n");
		
			String[] allData = manager.getAllData();
			for (int i=0; i<allData.length; i++) {
				System.out.println(allData[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception - loadAudio could not be executed.");
		}
		
		System.out.println("\n+--- End of TC12 ---+");
	}
	
	private static void testCase13() {
		// Test deleteAudio method with good data
		
		System.out.println("+--- TC13 - Test of deleteAudio() with valid data ---+\n");
		
		// Preconditions
		int code = 1;
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		
		try {
			// Show files in audioData before running deleteAudio
			System.out.println("Items in audioData before execution:\n");
			String[] allData = manager.getAllData();
			for (int i=0; i<allData.length; i++) {
				System.out.println(allData[i]);
			}
			
			manager.deleteAudio(code);
			
			System.out.println("\ndeleteAudio has been executed. File with code 1 should be removed audioData (see below):\n");
			String[] allData2 = manager.getAllData();
			for (int i=0; i<allData2.length; i++) {
				System.out.println(allData2[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception - deleteAudio could not be executed.");
		}
		
		System.out.println("\n+--- End of TC13 ---+");
	}
	
	private static void testCase14() {
		// Test deleteAudio method with invalid data
		
		System.out.println("+--- TC14 - Test of deleteAudio() with invalid data ---+\n");
		
		// Preconditions
		int code = -1;
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		
		try {
			// Show files in audioData before running deleteAudio
			System.out.println("Items in audioData before execution:\n");
			String[] allData = manager.getAllData();
			for (int i=0; i<allData.length; i++) {
				System.out.println(allData[i]);
			}
			
			manager.deleteAudio(code);
			
			System.out.println("\ndeleteAudio has been executed. No files should be removed.\n");
			String[] allData2 = manager.getAllData();
			for (int i=0; i<allData2.length; i++) {
				System.out.println(allData2[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception - deleteAudio could not be executed.");
		}
		
		System.out.println("\n+--- End of TC14 ---+");
	}
	
	private static void testCase15() {
		// Test getAllData method with existing files in audioData ArrayList
		
		System.out.println("+--- TC15 - Test of getAllData() with existing files ---+\n");
		
		// Preconditions
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		
		try {
			System.out.println("2 elements should be displayed below.\n");
			String[] allData = manager.getAllData();
			for (int i=0; i<allData.length; i++) {
				System.out.println(allData[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception - getAllData could not be executed.");
		}
		
		System.out.println("\n+--- End of TC15 ---+");
	}
	
	private static void testCase16() {
		// Test getAllData method with no files in audioData ArrayList

		System.out.println("+--- TC16 - Test of getAllData() with no existing files ---+\n");
		
		// Preconditions
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		try {
			String[] allData = manager.getAllData();
			
			System.out.println("Length of array returned from getAllData: " + allData.length);
			System.out.println("0 elements should be displayed below.\n");
			
			for (int i=0; i<allData.length; i++) {
				System.out.println(allData[i]);
			}
		} catch (Exception e) {
			System.out.println("Exception - getAllData could not be executed.");
		}
		
		System.out.println("\n+--- End of TC16 ---+");
	}
	
	private static void testCase17() {
		// Test play with valid code
		
		System.out.println("+--- TC17 - Test of play() with valid code ---+\n");
		
		// Preconditions
		int code = 0;
		AudioFile myFile = new AudioFile("My Audio", 30, "/MyFile.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile);
		
		try {
			String res = manager.play(code);
			System.out.println("Response from play(): " + res);
			System.out.println("My Audio should be playing.");
		} catch (Exception e) {
			System.out.println("Exception - play could not be executed");
		}
		System.out.println("\n+--- End of TC17 ---+");
	}
	
	private static void testCase18() {
		// Test play with valid AudioFile
		
		System.out.println("+--- TC18 - Test of play() with valid AudioFile ---+\n");
		
		// Preconditions
		AudioFile myFile = new AudioFile("My Audio", 30, "/MyFile.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile);
		
		try {
			String res = manager.play(myFile);
			System.out.println("Response from play(): " + res);
			System.out.println("My Audio should be playing.");
		} catch (Exception e) {
			System.out.println("Exception - play could not be executed");
		}
		
		System.out.println("\n+--- End of TC18 ---+");
	}
	
	private static void testCase19() {
		// Test play with invalid code
		
		System.out.println("+--- TC19 - Test of play() with invalid code ---+\n");
		
		// Preconditions
		int code = -1;
		AudioFile myFile = new AudioFile("My Audio", 30, "/MyFile.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile);
		
		try {
			String res = manager.play(code);
			System.out.println("Response from play(): " + res);
			System.out.println("No file should be playing. Error message should be shown.");
		} catch (Exception e) {
			System.out.println("Exception - play could not be executed");
		}
		System.out.println("\n+--- End of TC19 ---+");
	}
	
	private static void testCase20() {
		// Test play with valid AudioFile
		
		System.out.println("+--- TC20 - Test of play() with invalid AudioFile ---+\n");
		
		// Preconditions
		AudioFile myFile = new AudioFile("My Audio", 30, "/MyFile.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		try {
			String res = manager.play(myFile);
			System.out.println("Response from play(): " + res);
			System.out.println("No file should be playing. Error message should be shown.");
		} catch (Exception e) {
			System.out.println("Exception - play could not be executed");
		}
		
		System.out.println("\n+--- End of TC20 ---+");
	}
	
	private static void testCase21() {
		// Test topTen method with less than 10 files loaded.
		
		System.out.println("+--- TC21 - Test of topTen() with less than 10 files loaded. ---+\n");
		
		// Preconditions
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioFile myFile3 = new AudioFile("My Audio 3", 15, "/MyFile3.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		manager.loadAudio(myFile3);
		
		// Set playCount for myFile1 to 5
		int i=0;
		while(i < 5) {
			manager.play(0);
			i++;
		}
		
		// Set playCount for myFile2 to 3
		int j=0;
		while(j < 3) {
			manager.play(1);
			j++;
		}
		
		// Set playCount for myFile3 to 10
		int k=0;
		while(k < 10) {
			manager.play(2);
			k++;
		}
		
		try {
			String topTen = manager.topTen();
			System.out.println("topTen() has been executed. See below:\n");
			System.out.println(topTen);
			
			System.out.println("It should follow this order: My Audio 3, My Audio 1, My Audio 2.");
			System.out.println("Only 3 files should be listed.");
		} catch (Exception e) {
			System.out.println("Exception - topTen could not be executed");
		}
		
		System.out.println("\n+--- End of TC21 ---+");
	}
	
	private static void testCase22() {
		// Test topTen method with more than 10 files loaded.
		
		System.out.println("+--- TC22 - Test of topTen() with more than 10 files loaded. ---+\n");
		
		// Preconditions
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioFile myFile3 = new AudioFile("My Audio 3", 15, "/MyFile3.wav");
		AudioFile myFile4 = new AudioFile("My Audio 4", 10, "/MyFile4.wav");
		AudioFile myFile5 = new AudioFile("My Audio 5", 12, "/MyFile5.wav");
		AudioFile myFile6 = new AudioFile("My Audio 6", 65, "/MyFile6.wav");
		AudioFile myFile7 = new AudioFile("My Audio 7", 10, "/MyFile7.wav");
		AudioFile myFile8 = new AudioFile("My Audio 8", 100, "/MyFile8.wav");
		AudioFile myFile9 = new AudioFile("My Audio 9", 45, "/MyFile9.wav");
		AudioFile myFile10 = new AudioFile("My Audio 10", 13, "/MyFile10.wav");
		AudioFile myFile11 = new AudioFile("My Audio 11", 87, "/MyFile11.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		manager.loadAudio(myFile3);
		manager.loadAudio(myFile4);
		manager.loadAudio(myFile5);
		manager.loadAudio(myFile6);
		manager.loadAudio(myFile7);
		manager.loadAudio(myFile8);
		manager.loadAudio(myFile9);
		manager.loadAudio(myFile10);
		manager.loadAudio(myFile11);
		
		// Set playCount for myFile1 to 5
		int i=0;
		while(i < 5) {
			manager.play(0);
			i++;
		}
		
		// Set playCount for myFile2 to 3
		int j=0;
		while(j < 3) {
			manager.play(1);
			j++;
		}
		
		// Set playCount for myFile3 to 10
		int k=0;
		while(k < 10) {
			manager.play(2);
			k++;
		}
		
		// Set playCount for myFile4 to 1
		manager.play(3);
		
		// Leave playCount for myFile5 to 0
		
		// Set playCount for myFile6 to 12
		int l=0;
		while(l < 12) {
			manager.play(5);
			l++;
		}
		
		// Set playCount for myFile7 to 2
		int m=0;
		while(m < 2) {
			manager.play(6);
			m++;
		}
		
		// Set playCount for myFile8 to 4
		int n=0;
		while(n < 4) {
			manager.play(7);
			n++;
		}
		
		// Set playCount for myFile9 to 6
		int o=0;
		while(o < 6) {
			manager.play(8);
			o++;
		}
		
		// Set playCount for myFile10 to 14
		int p=0;
		while(p < 14) {
			manager.play(9);
			p++;
		}
		
		// Set playCount for myFile11 to 11
		int q=0;
		while(q < 11) {
			manager.play(10);
			q++;
		}
		
		try {
			String topTen = manager.topTen();
			System.out.println("topTen() has been executed. See below:\n");
			System.out.println(topTen);
			
			System.out.println("It should follow this order:\nMy Audio 10,\nMy Audio 6,\nMy Audio 11,\nMy Audio 3,\nMy Audio 9,\nMy Audio 1,\nMy Audio 8,\nMy Audio 2,\nMy Audio 7,\nMy Audio 4");
			System.out.println("\nOnly 10 files should be listed.");
		} catch (Exception e) {
			System.out.println("Exception - topTen could not be executed");
		}
		
		System.out.println("\n+--- End of TC22 ---+");
	}
	
	private static void testCase23() {
		// Test topTen method with no files having been loaded.
		
		System.out.println("+--- TC23 - Test of topTen() with no files loaded. ---+\n");
		
		// Preconditions
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		try {
			String topTen = manager.topTen();
			System.out.println("topTen() has been executed. See below:\n");
			System.out.println(topTen);

			System.out.println("No files should be listed.");
		} catch (Exception e) {
			System.out.println("Exception - topTen could not be executed");
		}
		
		System.out.println("\n+--- End of TC23 ---+");
	}
	
	private static void testCase24() {
		// Test topTen method when two files have the same playCount value.
		
		System.out.println("+--- TC24 - Test of topTen() when two files have the same playCount value ---+\n");
		
		// Preconditions
		AudioFile myFile1 = new AudioFile("My Audio 1", 30, "/MyFile1.wav");
		AudioFile myFile2 = new AudioFile("My Audio 2", 25, "/MyFile2.wav");
		AudioFile myFile3 = new AudioFile("My Audio 3", 15, "/MyFile3.wav");
		AudioManager manager = new AudioManager(new AudioPlayer());
		
		manager.loadAudio(myFile1);
		manager.loadAudio(myFile2);
		manager.loadAudio(myFile3);
		
		// Set playCount for myFile1 to 3
		int i=0;
		while(i < 3) {
			manager.play(0);
			i++;
		}
		
		// Set playCount for myFile2 to 5
		int j=0;
		while(j < 5) {
			manager.play(1);
			j++;
		}
		
		// Set playCount for myFile3 to 3
		int k=0;
		while(k < 3) {
			manager.play(2);
			k++;
		}
		
		try {
			String topTen = manager.topTen();
			System.out.println("topTen() has been executed. See below:\n");
			System.out.println(topTen);
			
			System.out.println("It should follow this order: My Audio 2, My Audio 1, My Audio 3.");
			System.out.println("Files with equivalent playCount should be ordered by their code in ascending order.");
		} catch (Exception e) {
			System.out.println("Exception - topTen could not be executed");
		}
		
		System.out.println("\n+--- End of TC24 ---+");
	}
}
