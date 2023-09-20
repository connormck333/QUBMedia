package part03;

public class AlbumTrack extends AudioFile {
	
	private Album record;

	// Constructor
	public AlbumTrack(String _title, int _duration, String _dataSource, Album _record) {
		super(_title, _duration, _dataSource);
		this.record = _record;
	}
	
	// GET Methods
	public Album getAlbum() {
		return this.record;
	}
	
	@Override
	public String toString() {
		return super.toString() + this.record.toString();
	}

}
