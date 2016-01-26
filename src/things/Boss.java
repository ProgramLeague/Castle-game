package things;

import funcs.FuncSleep;

//import java.util.Scanner;

public class Boss extends Player {
//	�����һ������Ѫ������
	private String dieText = "";

	boolean survive = true;
	boolean getItem = true;

	public Boss(String name, int blood, int strike, int miss, int experience, String dieText) {
		super(name,blood,strike,miss);
		this.dieText = dieText;
		this.experience = experience;
	}

	public Boss(String name, int blood, int strike, int miss, int experience) {
		super(name,blood,strike,miss);
		dieText = name + "�������㰧�󣬲�������̵أ�";
	}

	public Player fight(Player player) {

		int bloodsave = this.blood;
		int bloodsave2 = player.blood;
		int beBeat = (this.strike - player.miss);
		int Beat = (player.strike - this.miss);

		if( beBeat <= 0 ) {
			beBeat = 0;
		}
//			�򲻹�
		if( Beat <= 0 ){
			player.blood -= 10;
			System.out.println("��Ĺ�����С��"+this.name+"�ķ�������\n��Ķ��ӣ���ʧ10������ֵ��");
		}
		else{
			while(survive){
//					�����Ѫ
				this.blood -= Beat;
				player.blood -= beBeat;
//					�ж�
				if( player.blood <= 0 ){
					
					bloodsave2 -= 5;
					player.blood = bloodsave2;

					this.blood = bloodsave;
					System.out.println("�������е�����ֵ�޷���"+this.name+"��\n��Ķ��ӣ���ʧ5������ֵ��");
					break;
				}
				if( this.blood <= 0 ){
//						�Ȱ�Ѫ����ȥ
					this.blood = bloodsave;
					System.out.println(dieText+"\nʤ�����飡�㻹ʣ"+player.blood+"������ֵ��");
					System.out.println("����ս�������"+player.win(GetExperience())+"�㾭��ֵ��");
					survive = false;
					getItem = false;
					FuncSleep sleep = new FuncSleep();
					System.out.print("ս������");
					for( int i = 800; i >= 0; i -= 100 ){
						sleep.SleepTime(i);
					}
					System.out.println();
				}
			}
		}

		survive = true;
		return player;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public boolean IfGet() {
		// TODO Auto-generated method stub
		return getItem;
	}
	
	public int GetExperience() {
		if( getItem )
		{
			System.out.println("Boss��ս�ɹ��������ս�����Ͷ���5�㾭�齱����");
			return (this.experience+5);
		}
		else
		{
			return this.experience;
		}
	}

}
