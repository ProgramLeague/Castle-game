package funcs;

import castle.Game;

public class FuncSleep extends Funcsrc {

	public FuncSleep(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public FuncSleep() {}

	@Override
	public void DoFunc(String cmd) {
		// TODO Auto-generated method stub
//		int bloodMore = Integer.parseInt(cmd);
		if( game.TreatRoomCheck() ){
			if( !game.BossGetItem() ){
				System.out.print("Ů��˳�ӵ�����������硣˯����");
			    for(int i = 0; i < 8; i ++ ) {
			    	SleepTime(50);
			    }
			    System.out.print("\n�Ѿ�˯��������");
				if( game.Treat() )
					System.out.println("�ָ���120.");
				else
					System.out.println("����120���ûָ���~");
			}
			else
				System.out.println("˯����ҪŮ�ͷ��̣�Ȼ��������������Ը�Ⱑ������");
		}
		else
			System.out.println("ֻ�б��ݻ�������˯����");
	}
	
	public void SleepTime(int time) {
		try{   
		    Thread.currentThread();
			Thread.sleep(time);  
	    }  
	    catch(Exception e){}
		System.out.print("��");
	}

}
