package funcs;

import castle.Game;

public class FuncHelp extends Funcsrc {

	public FuncHelp(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void DoFunc(String cmd) {
    	 System.out.println("��·������������������У�");
    	 String[] strings = game.getFuncs();
    	 for(String s : strings){
    		 System.out.println("\\" + s);
    	 }
         System.out.println("�磺\\go east");
    }
}
