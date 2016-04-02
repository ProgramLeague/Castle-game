package database;

import cells.Player;
import map.Exits;
import map.GameMap;
import map.Room;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * 封装数据库操作
 * Created by asus1 on 2016/1/28.
 */
public class Database {
	private static final String savePath = "."+ File.separator+"save.ice";
	private String playerName = "";
	private char[] roomsState ;
	private String roomName;
	private int blood = 0;
	private int strike = 0;
	private int defence = 0;
	private int level = 0;
	private int experience = 0;

	public Database() {
		File file = new File(savePath);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			roomName = reader.readLine();
			roomsState = reader.readLine().toCharArray();
			playerName = reader.readLine();
			blood      =  Integer.parseInt(reader.readLine());
			strike     =  Integer.parseInt(reader.readLine());
			defence    =  Integer.parseInt(reader.readLine());
			level      =  Integer.parseInt(reader.readLine());
			experience =  Integer.parseInt(reader.readLine());

			reader.close();

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void loadMap(GameMap map, String defaultName) {
		map.setRoomsState(roomsState);
		if (roomName == null)
			roomName = defaultName;
		map.loadRoom(roomName);
	}

	public void saveMap(GameMap map) throws IOException {
		File file = new File(savePath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		this.roomName = map.getCurrentRoom().toString();
		this.roomsState = map.getRoomsState();
		writer.write(this.toString());
		writer.close();
	}

	public void saveMapAndState(GameMap map, Player player) throws IOException{
		File file = openFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		this.roomName = map.getCurrentRoom().toString();
		this.roomsState = map.getRoomsState();
		this.playerName = player.toString();
		this.blood      = player.getBlood();
		this.strike     = player.getStrike();
		this.defence    = player.getDefence();
		this.level      = player.getLevel();
		this.experience = player.getExperience();

		writer.write(this.toString());
		writer.close();
	}

	@Override
	public String toString() {
		return
				this.roomName   +  "\r\n" +
				String.valueOf(this.roomsState) +  "\r\n" +
				this.playerName +  "\r\n" +
				this.blood      +  "\r\n" +
				this.strike     +  "\r\n" +
				this.defence    +  "\r\n" +
				this.level      +  "\r\n" +
				this.experience +  "\r\n"
				;
	}

	/**
	 * @param player 玩家指针
	 * 读取数据
	 */
	public void loadState(Player player){
		player.setValues(
				playerName,
				blood,
				strike,
				defence,
				level,
				experience
		);
	}

	public void saveState(Player player) throws IOException {
//		System.out.println("正在保存数据。。");
		File file = openFile();
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(file));
		this.playerName = player.toString();
		this.blood      = player.getBlood();
		this.strike     = player.getStrike();
		this.defence    = player.getDefence();
		this.level      = player.getLevel();
		this.experience = player.getExperience();

		writer.write(this.toString());

		writer.close();
	}

	public static boolean isFileExists(){
		return new File(savePath).exists();
	}

	private static Statement getStatement() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
		return connection.createStatement();
	}

	/**
	 CREATE TABLE ROOM(id INTEGER PRIMARY KEY AUTOINCREMENT,
	 disc TEXT, welc TEXT,boss TEXT,blood INTEGER,
	 strike INTEGER, defence INTEGER,exp INTEGER, die TEXT);
	 */
	public static ArrayList<Room> getRooms() throws ClassNotFoundException, SQLException {
		ResultSet set = getStatement().executeQuery("SELECT * FROM ROOM ORDER BY id ASC");
		ArrayList<Room> rooms = new ArrayList<>();
		while (set.next()){
			rooms.add(new Room(
					set.getString("disc"),
					set.getString("welc"),
					set.getString("boss"),
					set.getInt("blood"),
					set.getInt("strike"),
					set.getInt("defence"),
					set.getInt("exp"),
					set.getString("die")
			));
		}
		set.close();
		return rooms;
	}

	/**
	 *  CREATE TABLE MAP( id INTEGER PRIMARY KEY AUTOINCREMENT, fromid INTEGER, toid INTEGER, dir INTEGER);
	 */
	public static ArrayList<Exits> getExits() throws ClassNotFoundException, SQLException{
		// 与顺序无关
		ResultSet set = getStatement().executeQuery("SELECT * FROM MAP");
		ArrayList<Exits> exitses = new ArrayList<>();
		while(set.next()){
			exitses.add(new Exits(
					set.getInt("fromid"),
					set.getInt("toid"),
					set.getInt("dir")
			));
		}
		set.close();
		return exitses;
	}

	private File openFile() throws IOException {
		File file = new File(savePath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		return file;
	}
}
