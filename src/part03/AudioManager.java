package part03;

import java.util.ArrayList;
import audio.AudioPlayer;

public class AudioManager implements iManager {
	
	// Initialise variables
	private ArrayList<AudioFile> audioData;
	private AudioPlayer player;

	// Constructor
	public AudioManager(AudioPlayer _player) {
		this.player = _player;
		this.audioData = new ArrayList<AudioFile>();
	}
	
	// Add a new AudioFile to audioData
	public void loadAudio(AudioFile _audio) {
		audioData.add(_audio);
	}
	
	// Remove AudioFile from audioData
	public void deleteAudio(int _id) {
		
		// Find AudioFile with code matching _id
		for (int i=0; i<audioData.size(); i++) {
			AudioFile current = audioData.get(i);
			if (current.getCode() == _id) {
				audioData.remove(i);
				return;
			}
		}
	}
	
	// Returns an array of all audio files in String format
	public String[] getAllData() {
		String data[] = new String[audioData.size()];
		for(int i=0; i < audioData.size(); i++) {
			AudioFile currentFile = audioData.get(i);
			data[i] = "Code: " + currentFile.getCode() + ", Title: " + currentFile.getTitle() + ", Duration: " + currentFile.getDuration() + " seconds, Data path: " + currentFile.getDataSource();
			
			if (currentFile.getClass() == AlbumTrack.class) {
				AlbumTrack track = (AlbumTrack) currentFile;
				data[i] = data[i] + ", " + track.getAlbum();
				
			}
		}
		return data;
	}
	
	// Play by AudioFile
	public String play(AudioFile _audio) {
		try {
			this.player.playFile(_audio.getDataSource());
			_audio.registerPlay();
			return "File " + _audio.getTitle() + " is playing.";
		} catch(Exception e) {
			return "Couldn't play file " + _audio.getTitle();
		}
	}
	
	// Play by code
	public String play(int _id) {
		String msg = "Could not find file with that code.";
		for (int i=0; i<audioData.size(); i++) {
			// find AudioFile with matching code
			if (audioData.get(i).getCode() == _id) {
				msg = play(audioData.get(i));
			}
		}
		
		return msg;
	}
	
	// Returns a String of 10 AudioFiles with the highest playCount
	public String topTen() {
		ArrayList<AudioFile> sortedData = sortData();
		int length = sortedData.size();
		
		
		// Check if 10 or more files exist
		int size = 10;
		if (length < 10) {
			size = length;
		}
		
		String data = "";
		int i = 0;
		while (i < size) {
			AudioFile currentFile = sortedData.get(length - (i+1));
			data = data + (i+1) + ". Code: " + currentFile.getCode() + ", Title: " + currentFile.getTitle() + ", No. of Plays: " + currentFile.getPlayCount() + "\n";
			i++;
		}
		
		return data;
	}
	
	public void setPlayer(AudioPlayer _player) {
		if (_player != null) {
			this.player = _player;
		}
	}
	
	
	// Returns a sorted (by playCount) ArrayList of audioData using bubble sort
	private ArrayList<AudioFile> sortData() {
		ArrayList<AudioFile> newArray = audioData;
		
		int swaps;
		do {
			swaps = 0;
			for (int i=0; i < newArray.size()-1; i++) {
				if (newArray.get(i).getPlayCount() > newArray.get(i+1).getPlayCount()) {
					AudioFile temp = newArray.get(i);
					newArray.set(i, newArray.get(i+1));
					newArray.set(i+1, temp);
					swaps++;
				}
			}
		} while(swaps > 0);
		return newArray;
	}
}
