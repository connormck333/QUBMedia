package part02;

public interface iAudio {
	public int getCode();
	public String getTitle();
	public int getDuration();
	public String getDataSource();
	public int getPlayCount();
	public void registerPlay();
	public String toString();
}
