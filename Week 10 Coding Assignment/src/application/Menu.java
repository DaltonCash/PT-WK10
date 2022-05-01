package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.AlbumDao;
import entities.Album;

public class Menu {
	
	private AlbumDao albumDao = new AlbumDao();
	Scanner scanner = new Scanner(System.in);
	private List<String> selections = Arrays.asList( "Quit Menu", "Display Albums", "Create Album", "Update Album", "Delete Album");
	private List<String> updateSelections = Arrays.asList("Name","Artist","Record Date", "Release Date");
	
	public void Start() {
		String selection;
		displayMenu();
		do {
			selection = scanner.nextLine();
			try {
				switch(selection) {
				case "0":
					System.out.println("Menu Closed.");
					break;
				case "1":
					displayAlbums();
					break;
				case "2":
					createAlbum();
					break;
				case "3":
					updateAlbum();
					break;
				case "4":
					deleteAlbum();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}while(!selection.equals("0"));
	}

	private void displayMenu() {
		System.out.println("Please choose a selection:");
		for(int i = 0; i < selections.size(); i++) {
			System.out.println(i + ") " + selections.get(i));	
		}
		System.out.println("Press Enter After Selection...");
	}
	
	private String correct(String oldString) {
		String space = "                    ";
		String newString = "||";
		newString += oldString;
		newString += space;
		return newString.substring(0,20);
	}
	
	private String correct(int integer) {
		String oldInt = Integer.toString(integer);
		String space = "                    ";
		String newInt = "||";
		newInt += oldInt;
		newInt += space;
		return newInt.substring(0,20);
	}
	
	private void displayAlbums() throws SQLException {
		List<Album> albums = albumDao.getAlbums();
		System.out.println("||Id----------------||Name--------------||Artist------------||Record Date-------||Release Date");
		for(Album album : albums) {
			System.out.println(correct(album.getAlbumId()) + correct(album.getName()) + correct(album.getArtist()) + correct(album.getRecordDate()) + correct(album.getReleaseDate()));
		}
	}
	
	private void createAlbum() throws SQLException {
		System.out.print("Enter New Album Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Artist Name For New Album:");
		String artist = scanner.nextLine();
		System.out.print("Enter Record Date For New Album (YYYY-MM-DD):");
		String recordDate = scanner.nextLine();
		System.out.print("Enter Release Date For New Album (YYYY-MM-DD): ");
		String releaseDate = scanner.nextLine();
		albumDao.createAlbum(name, artist, recordDate, releaseDate);
		System.out.println("New Album Created!");
	}
	
	private void deleteAlbum() throws SQLException {
		System.out.print("Enter Album Id to Delete:");
		int id = Integer.parseInt(scanner.nextLine());
		albumDao.deleteAlbum(id);
		System.out.println("Album with Id '" + id + "' Deleted!");
	}
	
	private void updateAlbum() throws SQLException{
		System.out.print("Enter The Id of Album to Update: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Select a Value to Change:");
		for(int i = 0; i < updateSelections.size(); i++) {
			System.out.println((i + 1) + ") " + updateSelections.get(i));
		}
		String valueToChange = updateSelection(id, scanner.nextLine());
		System.out.println("Album " + id + "'s " + valueToChange.substring(0, 1).toUpperCase() + valueToChange.substring(1) + " Updated!");	
	}

	private  String updateSelection(int id, String valueToChange) throws SQLException {
		String choice = "";
		String newValue = "";
		switch(valueToChange) {
		case "1":
			choice = "name";
			System.out.print("Enter New Value For Name: ");
			newValue = scanner.nextLine();
			albumDao.updateAlbumName(id, newValue);
			break;
			
		case "2":
			choice = "artist";
			System.out.print("Enter New Value For Artist: ");
			newValue = scanner.nextLine();
			albumDao.updateAlbumArtist(id, newValue);
			break;
			
		case "3":
			choice = "recordDate";
			System.out.print("Enter New Value For Record Date: ");
			newValue = scanner.nextLine();
			albumDao.updateAlbumRecordDate(id, newValue);
			break;
			
		case "4":
			choice = "releaseDate";
			System.out.print("Enter New Value For Release Date: ");
			newValue = scanner.nextLine();
			albumDao.updateAlbumReleaseDate(id, newValue);
			break;
		}
		
		return choice;
	}
}