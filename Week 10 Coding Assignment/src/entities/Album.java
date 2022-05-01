package entities;

public class Album {
	private Integer albumId;
	private String name;
	private String artist;
	private String recordDate;
	private String releaseDate;
	
	public Album(Integer albumId, String name, String artist, String recordDate, String releaseDate) {
		this.setAlbumId(albumId);
		this.setName(name);
		this.setArtist(artist);
		this.setRecordDate(recordDate);
		this.setReleaseDate(releaseDate);
	}
	
	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer id) {
		this.albumId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
}