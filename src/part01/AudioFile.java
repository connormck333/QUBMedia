package part01;

public class AudioFile implements iAudio {
	
	// Initialise variables
	private String title;
	private int duration; // in seconds
	private String dataSource;
	private int playCount;
	
	// Audio file identifier
	private int code;
	private static int nextCode;
	
	public AudioFile(String _title, int _duration, String _dataSource) {
		this.title = _title;
		this.duration = _duration;
		this.dataSource = _dataSource;
		
		// Set Code
		this.code = AudioFile.nextCode;
		AudioFile.nextCode++;
	}
	
	// GET Methods
	public int getCode() {
		return this.code;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public String getDataSource() {
		return this.dataSource;
	}
	
	public int getPlayCount() {
		return this.playCount;
	}
	
	// Increases playCount by 1
	public void registerPlay() {
		this.playCount++;
	}
	
	public String toString() {
		String ln1 = "Title: " + getTitle() + "\n";
		String ln2 = "Duration: " + getDuration() + "\n";
		String ln3 = "Data Source: " + getDataSource() + "\n";
		String ln4 = "Audio ID: " + getCode() + "\n";
		String ln5 = "Play count: " + getPlayCount() + "\n";
		return ln1 + ln2 + ln3 + ln4 + ln5;
	}
}
