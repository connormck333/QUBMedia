package part01;

import audio.AudioPlayer;

public interface iManager {
	public void loadAudio(AudioFile af);
	public void deleteAudio(int index);
	public String[] getAllData();
	public String play(AudioFile af);
	public String play(int audioId);
	public String topTen();
	public void setPlayer(AudioPlayer p);
}
