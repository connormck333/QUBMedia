package part02;

public class UnitTesting {

	public static void main(String[] args) {
		
		testCase1();
		System.out.println();
		testCase2();
		System.out.println();
		testCase3();
		System.out.println();
		testCase4();
		System.out.println();
		testCase5();
		System.out.println();
		testCase6();
		System.out.println();
		testCase7();
		System.out.println();
		testCase8();
		System.out.println();
		testCase9();

	}
	
	private static void testCase1() {
		// Construction test of AudioFile with good data
		
		System.out.println("+--- TC1 - Construction Test with good data ---+\n");
		
		// Test data
		String _title = "My Track";
		int _duration = 30;
		String _dataSource = "/Downloads/MyTrack.wav";
		
		try {
			AudioFile myFile = new AudioFile(_title, _duration, _dataSource);
			
			System.out.println("Construction of AudioFile complete - (toString) data below:");
			System.out.println(myFile);
			
			System.out.println("Title should be: " + _title);
			System.out.println("Duration should be: " + _duration);
			System.out.println("Data Source should be: " + _dataSource);
		} catch (Exception e) {
			System.out.println("Exception - construction of object rejected.");
		}
		
		System.out.println("\n+--- End of TC1 ---+");
		
	}
	
	private static void testCase2() {
		// Construction test of AudioFile with bad data
		
		System.out.println("+--- TC2 - Construction Test with bad data ---+\n");
		
		// Test data
		String _title = null;
		int _duration = 30;
		String _dataSource = null;
		
		try {
			AudioFile myFile = new AudioFile(_title, _duration, _dataSource);
			
			System.out.println("Construction of AudioFile complete - (toString) data below:");
			System.out.println(myFile);
			
			System.out.println("Title should be: " + _title);
			System.out.println("Duration should be: " + _duration);
			System.out.println("Data Source should be: " + _dataSource);
		} catch (Exception e) {
			System.out.println("Exception - construction of object rejected.");
		}
		
		System.out.println("\n+--- End of TC2 ---+");
		
	}
	
	private static void testCase3() {
		// Test of AudioFile.getCode()
		
		System.out.println("+--- TC3 - getCode Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		try {
			int code = testFile.getCode();
			System.out.println("getCode has been successfully executed.");
			System.out.println("testFile's code: " + code);
		} catch (Exception e) {
			System.out.println("Exception - getCode could not be executed.");
		}
		
		System.out.println("\n+--- End of TC3 ---+");
	}
	
	private static void testCase4() {
		// Test of AudioFile.getTitle()
		
		System.out.println("+--- TC4 - getTitle Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		try {
			String title = testFile.getTitle();
			System.out.println("getTitle has been successfully executed.");
			System.out.println("testFile's title: " + title);
			
			System.out.println("\nTitle should be: My File");
		} catch (Exception e) {
			System.out.println("Exception - getTitle could not be executed.");
		}
		
		System.out.println("\n+--- End of TC4 ---+");
	}
	
	private static void testCase5() {
		// Test of AudioFile.getDuration()
		
		System.out.println("+--- TC5 - getDuration Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		try {
			int duration = testFile.getDuration();
			System.out.println("getDuration has been successfully executed.");
			System.out.println("testFile's duration: " + duration);
			
			System.out.println("\nDuration should be: 30");
		} catch (Exception e) {
			System.out.println("Exception - getDuration could not be executed.");
		}
		
		System.out.println("\n+--- End of TC5 ---+");
	}
	
	private static void testCase6() {
		// Test of AudioFile.getDataSource()
		
		System.out.println("+--- TC6 - getDataSource Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		try {
			String dataSource = testFile.getDataSource();
			System.out.println("getDataSource has been successfully executed.");
			System.out.println("testFile's Data Source: " + dataSource);
			
			System.out.println("\nData Source should be: /MyFile.wav");
		} catch (Exception e) {
			System.out.println("Exception - getDataSource could not be executed.");
		}
		
		System.out.println("\n+--- End of TC6 ---+");
	}
	
	private static void testCase7() {
		// Test of AudioFile.getPlayCount()
		
		System.out.println("+--- TC7 - getPlayCount Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		try {
			int playCount = testFile.getPlayCount();
			System.out.println("getPlayCount has been successfully executed.");
			System.out.println("testFile's play count: " + playCount);
			
			System.out.println("\nPlay Count should be (default value): 0");
		} catch (Exception e) {
			System.out.println("Exception - getPlayCount could not be executed.");
		}
		
		System.out.println("\n+--- End of TC7 ---+");
	}
	
	private static void testCase8() {
		// Test of AudioFile.registerPlay()
		
		System.out.println("+--- TC8 - registerPlay Test ---+\n");
		
		// Precondition values
		AudioFile testFile = new AudioFile("My File", 30, "/MyFile.wav");
		
		int playCount = testFile.getPlayCount();
		System.out.println("testFile's play count before executing registerPlay: " + playCount);
		
		try {
			testFile.registerPlay();
			
			int playCount2 = testFile.getPlayCount();
			System.out.println("testFile's play count after executing registerPlay: " + playCount2);
		} catch (Exception e) {
			System.out.println("Exception - registerPlay could not be executed.");
		}
		
		System.out.println("\n+--- End of TC8 ---+");
	}
	
	private static void testCase9() {
		// Test of AudioFile.toString()
		
		System.out.println("+--- TC9 - toString Test ---+\n");
		
		// Test values
		String title = "My File";
		int duration = 30;
		String dataSource = "/MyFile.wav";
		
		// Precondition values
		AudioFile testFile = new AudioFile(title, duration, dataSource);
		
		try {
			System.out.println("See toString below:\n");
			System.out.println(testFile.toString());
			
			System.out.println("\nTitle should be: " + title);
			System.out.println("Duration should be: " + duration);
			System.out.println("Data source should be: " + dataSource);
			System.out.println("Code should be: 0");
			System.out.println("Play count should be: 0");
		} catch (Exception e) {
			System.out.println("Exception - toString could not be executed.");
		}
		
		System.out.println("\n+--- End of TC9 ---+");

	}

}


/*
	
	1. valid and invalid menu options
	2. valid and invalid inputs for loadAudio()
	3. valid and invalid inputs for deleteAudio()
	4. valid and invalid inputs for playAudio()
	5. topTen() displays actual top ten files by playcount
	6. display all files works when files exist / don't exist
	7. topTen() works when files exist / don't exist
	
*/