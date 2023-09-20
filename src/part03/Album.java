package part03;

import javax.swing.ImageIcon;

public class Album {
	
	private String title;
	private String artist;
	private ImageIcon cover;
	
	// Constructor
	public Album(String _title, String _artist, ImageIcon _cover) {
		this.title = _title;
		this.artist = _artist;
		this.cover = _cover;
	}
	
	// GET Methods
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public ImageIcon getCover() {
		return this.cover;
	}

	
	public String toString() {
		return "Album Title: " + getTitle() + ", Artist: " + getArtist() + ", Album Cover: " + getCover();
	}
}
