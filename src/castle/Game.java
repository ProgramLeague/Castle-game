package castle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import funcs.*;
import things.*;

public class Game {

	private HashMap<String, FuncSrc> funcs = new HashMap<String, FuncSrc>();
	private String[] funcsString ;
	public  ArrayList<Room> theRooms = new ArrayList<Room>();
	public  ArrayList<Item> theItems = new ArrayList<Item>();
	private Room currentRoom;
	private Player player;
	public final String savePath_1 = "D://save//player.ice";
	public final String savePath_2 = "D://save//envi.ice";

	//    构造方法
	public Game(){
		createRooms();
		createItems();

		funcsString = new String[]{
				"help",
				"go",
				"wild",
				"exit",
				"state",
				//    	"pack",
				"fight",
				"sleep",
				"save"
		};

		funcs.put(funcsString[0], new FuncHelp(this));
		funcs.put(funcsString[1], new FuncGo(this));
		funcs.put(funcsString[2], new FuncWild(this));
		funcs.put(funcsString[3], new FuncBye(this));
		funcs.put(funcsString[4], new FuncState(this));
//      funcs.put(funcsString[5], new FuncPack(this));
//      funcs.put(funcsString[6], new FuncFight(this));
//      funcs.put(funcsString[7], new FuncSleep(this));
//      funcs.put(funcsString[8], new FuncSave(this));
		funcs.put(funcsString[5], new FuncFight(this));
		funcs.put(funcsString[6], new FuncSleep(this));
		funcs.put(funcsString[7], new FuncSave(this));

	}

	public String[] getFuncs(){
		return funcsString;
	}

	private void createRooms(){

		//	构造地图结构
        /*0*/theRooms.add(new Room("城堡外","英俊的小偷头目",
				200,25,10,8,"小偷头目的钱全掉出来了！"));
        /*1*/theRooms.add(new Room("一楼大堂","礼貌的大堂经理",
				100,15,12,5,"大堂经理的帐算错了！"));
        /*2*/theRooms.add(new Room("小酒吧","潇洒的酒吧流氓",
				150,10,5,3,"酒吧流氓喝醉了！"));
        /*3*/theRooms.add(new Room("书房","优雅的读书人",
				100,7,5,2,"读书人的书掉出来了！"));
        /*4*/theRooms.add(new Room("宾馆","可爱的女仆",
				10,6,3,1,"女仆被你推倒了！"));
        /*5*/theRooms.add(new Room("二楼睡房","公主的管家",
				300,20,5,15,"管家扑街、公主被你推倒了！"));
        /*6*/theRooms.add(new Room("负一楼","奇怪的男人",
				200,30,15,25,"男人身边站出来一名浑身是伤的女孩。。"));
        /*7*/theRooms.add(new Room("负二楼","穿着霸气的绅士",
				100,50,35,35,"绅士的衣服脏了！"));
        /*8*/theRooms.add(new Room("负三楼","身穿铠甲的战士",
				300,30,25,45,"战士被自己绊倒了！"));
        /*9*/theRooms.add(new Room("负四楼","持剑的骑士",
				400,40,35,60,"骑士的剑断了！"));
        /*null*/theRooms.add(new Room("神秘空间","冰封",
				1000,100,100,200,"冰封继续开发中。。。"));

		theRooms.get(0).setExit("east", theRooms.get(1));
		theRooms.get(0).setExit("south",theRooms.get(3));
		theRooms.get(0).setExit("west", theRooms.get(2));
		theRooms.get(1).setExit("west",	theRooms.get(0));
		theRooms.get(2).setExit("east",	theRooms.get(0));
		theRooms.get(3).setExit("north",theRooms.get(0));
		theRooms.get(3).setExit("east", theRooms.get(4));
		theRooms.get(4).setExit("west",	theRooms.get(3));
		theRooms.get(1).setExit("up", 	theRooms.get(5));
		theRooms.get(5).setExit("down", theRooms.get(1));
		theRooms.get(1).setExit("down", theRooms.get(6));
		theRooms.get(6).setExit("up", 	theRooms.get(1));
		theRooms.get(6).setExit("down", theRooms.get(7));
		theRooms.get(7).setExit("up", 	theRooms.get(6));
		theRooms.get(7).setExit("down", theRooms.get(8));
		theRooms.get(8).setExit("up", 	theRooms.get(7));
		theRooms.get(8).setExit("down", theRooms.get(9));
		theRooms.get(9).setExit("up", 	theRooms.get(8));

		currentRoom = theRooms.get(0);  //	从城堡门外开始
	}

	private void createItems() {
		@SuppressWarnings("unused")
		Item wilder;
		theItems.add(wilder = new Item("传送门"));
	}

	private void printWelcome() {
		System.out.println("欢迎来到城堡！");
//        System.out.println("这是一个超级无聊的游戏。");
//        System.out.println("不过在经过了冰封的改造后，你会觉得这个很有意思。");
		Player.setFileName(savePath_1);
		if(!Player.isFileExist()){
			System.out.println("请键入你的名字：");
			Scanner name = new Scanner(System.in);
			player = new Player(name.nextLine(),200,10,5);
//	        name.close();
		}
		else {
			System.out.println("检测到存档。正在读取...");
			player = new Player();
			loadData();
			System.out.println("读取成功");
		}
		System.out.println("你好"+player);
		System.out.println("如果需要帮助，请输入 '\\help' 。\n");
		System.out.print("现在");
		currentRoom.showPrompt();
	}
	/**
	 * 到达
	 */
	public void goRoom(String direction){
		if( currentRoom.CheckExit(direction) )
			currentRoom = currentRoom.showRoom(direction);
		else
			System.out.println("没有这个出口。");
		currentRoom.showPrompt();
	}
	/**
	 * 随机传送
	 */
	public void WildRoom(){
		int index = (int) (Math.random()*2000);
		index %= theRooms.size();
		currentRoom = theRooms.get(index);
		currentRoom.showPrompt();
	}
	/**
	 * 战斗函数
	 */
	public void Fight() {
//    	减血赋值给原来的
		player = currentRoom.fightBoss(player);
//    	currentRoom.fightBoss( player.getStrike(), player.getMiss(), player.blood );
		currentRoom.showPrompt();
	}
	/**
	 * 指定数量的补血
	 */
	public void Treat(int bloodMore) {
		player.blood += bloodMore;
	}
	/**
	 * 补血
	 */
	public boolean Treat() {
		return player.treat();
	}
	/**
	 * 检查是否可以睡觉
	 */
	public boolean TreatRoomCheck() {
		return (currentRoom.equals("宾馆")||currentRoom.equals("卧室"));
	}
	/**
	 * 显示玩家数据
	 * @return 玩家数据
	 */
	public String PLayerToString() {
		return player.stateToString();
//    	return player;
	}
	/**
	 * 返回BOSS是否被打败过
	 * @return BOSS是否被打败过
	 */
	public boolean BossGetItem() {
		return currentRoom.BossGetItem();
	}
	private void loadData(){
		File file = new File(savePath_2);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
	public void saveData(){
		player.saveState();
		File file = new File(savePath_2);
		try {
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(currentRoom.toString());
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
	//	    游戏运行，接受指令
	private void gameRun() {
		String line;
		Scanner in = new Scanner(System.in);
		while ( true ) {

			System.out.print("\\");
			line = in.nextLine();
			String[] words = line.split(" ");
			FuncSrc func = funcs.get(words[0]);
			String value2 = "";

			if( words.length > 1 )
				value2 = words[1];

//			如果找到了该指令
			if( func != null ){
				func.DoFunc(value2);
				if( func.isGameEnded() ){
//					退出指令特殊处理
					saveData();
					break;
				}
			}
			else
				System.out.println("对不起，输入指令错误！");
		}
		in.close();
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.printWelcome();
		game.gameRun();

		System.out.println("退出游戏。再见！");
		return ;
	}

}
