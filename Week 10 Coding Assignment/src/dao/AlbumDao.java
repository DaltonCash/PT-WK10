package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Album;

public class AlbumDao {
	
	private Connection connection = DBConnection.getConnection();
	private final String GET_ALBUMS_QUERY = "SELECT * FROM albums";
	private final String CREATE_ALBUM_QUERY = "INSERT INTO albums(name, artist, recordDate, releaseDate) VALUES (?,?,?,?)" ;
	private final String DELETE_ALBUM_QUERY = "DELETE FROM albums WHERE albumId = ?";
	private final String UPDATE_ALBUM_NAME_QUERY = "UPDATE albums SET name = ? WHERE albumId = ?";
	private final String UPDATE_ALBUM_ARTIST_QUERY = "UPDATE albums SET artist = ? WHERE albumId = ?";
	private final String UPDATE_ALBUM_RECORD_DATE_QUERY = "UPDATE albums SET recordDate = ? WHERE albumId = ?";
	private final String UPDATE_ALBUM_RELEASE_DATE_QUERY = "UPDATE albums SET releaseDate = ? WHERE albumId = ?";

	public AlbumDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Album> getAlbums() throws SQLException{
		
		ResultSet rs = connection.prepareStatement(GET_ALBUMS_QUERY).executeQuery();
		List<Album> albums = new ArrayList<Album>();
		
		while(rs.next()) {
			albums.add(populateAlbums(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return albums;
	}
	
	public void createAlbum(String name, String artist, String recordDate, String releaseDate) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_ALBUM_QUERY);
		ps.setString(1,name);
		ps.setString(2, artist);
		ps.setString(3, recordDate);
		ps.setString(4, releaseDate);
		ps.executeUpdate();
	}
	
	public void deleteAlbum(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_ALBUM_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateAlbumName(int id, String newValue) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_NAME_QUERY);
		ps.setString(1, newValue);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateAlbumArtist(int id, String newValue) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_ARTIST_QUERY);
		ps.setString(1, newValue);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateAlbumRecordDate(int id, String newValue) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_RECORD_DATE_QUERY);
		ps.setString(1, newValue);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateAlbumReleaseDate(int id, String newValue) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_RELEASE_DATE_QUERY);
		ps.setString(1, newValue);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	private Album populateAlbums(Integer albumId, String name, String artist, String recordDate, String releaseDate) {
		
		return new Album(albumId, name, artist, recordDate, releaseDate);
	}
}	