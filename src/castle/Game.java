package castle;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import castle.Room;
import funcs.*;
import things.*;

public class Game {
	
	private HashMap<String, Funcsrc> funcs = new HashMap<String, Funcsrc>();
	private String[] funcsString ;
	public  ArrayList<Room> theRooms = new ArrayList<Room>();
	public  ArrayList<Item> theItems = new ArrayList<Item>();
    private Room currentRoom;
    private Player player;

//    ���췽��
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

        //	�����ͼ�ṹ
        /*0*/theRooms.add(new Room("�Ǳ���","Ӣ����С͵ͷĿ",		
        		200,25,10,8,"С͵ͷĿ��Ǯȫ�������ˣ�"));
        /*1*/theRooms.add(new Room("һ¥����","��ò�Ĵ��þ���",		
        		100,15,12,5,"���þ����������ˣ�"));
        /*2*/theRooms.add(new Room("С�ư�","�����ľư���å",
        		150,10,5,3,"�ư���å�����ˣ�"));
        /*3*/theRooms.add(new Room("�鷿","���ŵĶ�����",			
        		100,7,5,2,"�����˵���������ˣ�"));
        /*4*/theRooms.add(new Room("����","�ɰ���Ů��",			
        		10,6,3,1,"Ů�ͱ����Ƶ��ˣ�"));
        /*5*/theRooms.add(new Room("��¥˯��","�����Ĺܼ�",		
        		300,20,5,15,"�ܼ��˽֡����������Ƶ��ˣ�"));
        /*6*/theRooms.add(new Room("��һ¥","��ֵ���",			
        		200,30,15,25,"����ˡ�����֮���Ǻ����"));
        /*7*/theRooms.add(new Room("����¥","���Ű�������ʿ",			
        		100,50,35,35,"��ʿ���·����ˣ�"));

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

        currentRoom = theRooms.get(0);  //	�ӳǱ����⿪ʼ
    }
    
    private void createItems() {
    	@SuppressWarnings("unused")
		Item wilder;
    	theItems.add(wilder = new Item("������"));
    }

    private void printWelcome() {
        System.out.println("��ӭ�����Ǳ���");
//        System.out.println("����һ���������ĵ���Ϸ��");
//        System.out.println("�����ھ����˱���ĸ�������������������˼��");
        Player.setFileName("D://save.ice");
        if(!Player.isFileExist()){
	        System.out.println("�����������֣�");
			Scanner name = new Scanner(System.in);
	        player = new Player(name.nextLine(),200,10,5);
//	        name.close();
        }
        else {
        	System.out.println("��⵽�浵�����ڶ�ȡ...");
        	player = new Player();
        }
        System.out.println("���"+player);
        System.out.println("�����Ҫ������������ '\\help' ��\n");
        System.out.print("����");
        currentRoom.showPrompt();
    }
	/**
	 * ����
	 */
    public void goRoom(String direction){
    	if( currentRoom.CheckExit(direction) )
    		currentRoom = currentRoom.showRoom(direction);
    	else
    		System.out.println("û��������ڡ�");
		currentRoom.showPrompt();
    }
	/**
	 * �������
	 */
    public void WildRoom(){
    	int index = (int) (Math.random()*2000);
		index %= theRooms.size();
		currentRoom = theRooms.get(index);
		currentRoom.showPrompt();
    }
	/**
	 * ս������
	 */
    public void Fight() {
//    	��Ѫ��ֵ��ԭ����
    	player = currentRoom.fightBoss(player);
//    	currentRoom.fightBoss( player.getStrike(), player.getMiss(), player.blood );
    	currentRoom.showPrompt();
	}
	/**
	 * ָ�������Ĳ�Ѫ
	 */
    public void Treat(int bloodMore) {
    	player.blood += bloodMore;
	}
    /**
     * ��Ѫ
     */
    public boolean Treat() {
    	return player.treat();
	}
	/**
	 * ����Ƿ����˯��
	 */
    public boolean TreatRoomCheck() {
    	return (currentRoom.equals("����")||currentRoom.equals("����"));
	}
	/**
	 * ��ʾ�������
	 * @return �������
	 */
    public String PLayerToString() {
    	return player.stateToString();
//    	return player;
	}
	/**
	 * ����BOSS�Ƿ񱻴�ܹ�
	 * @return BOSS�Ƿ񱻴�ܹ�
	 */
    public boolean BossGetItem() {
		return currentRoom.BossGetItem();
	}
    public void saveData(){
    	player.saveState();
    }
//	    ��Ϸ���У�����ָ��
    private void gameRun() {
    	String line;
		Scanner in = new Scanner(System.in);
		while ( true ) {
			
			System.out.print("\\");
			line = in.nextLine();
			String[] words = line.split(" ");
			Funcsrc func = funcs.get(words[0]);
			String value2 = "";
	
			if( words.length > 1 )
				value2 = words[1];
			
//			����ҵ��˸�ָ��
			if( func != null ){
				func.DoFunc(value2);
				if( func.isGameEnded() ){
//					�˳�ָ�����⴦��
					saveData();
					break;
				}
			}
			else
				System.out.println("�Բ�������ָ�����");
		}
		in.close();
    }

	public static void main(String[] args) {
		
		Game game = new Game();
		game.printWelcome();
		game.gameRun();
        
        System.out.println("�˳���Ϸ���ټ���");
        return ;
	}

}
