package cells;

import funcs.FuncSleep;

//import java.util.Scanner;

public class Boss extends Player {
	//	和玩家一样，有血、攻防
	private String dieText = "";

	boolean survive = true;
	boolean getItem = true;

	public Boss(String name, int blood, int strike, int miss, int experience, String dieText) {
		this(name,blood,strike,miss,experience);
		this.dieText = dieText;
	}

	public Boss(String name, int blood, int strike, int miss, int experience) {
		super(name,blood,strike,miss);
		this.experience = experience;
		dieText = name + "跪着向你哀求，不过你残忍地！";
	}

	public Player fight(Player player) {

		int bloodSave = this.blood;
		int bloodSave2 = player.blood;
		int beBeat = (this.strike - player.getDefence());
		int Beat = (player.getStrike() - this.defence);

		if( beBeat <= 0 ) {
			beBeat = 0;
		}
//			打不过
		if( Beat <= 0 ){
			player.blood -= 10;
			System.out.println("你的攻击力小于"+this.name+"的防御力！\n落荒而逃！损失10点体力值！");
		}
		else{
			while(survive){
//					互相扣血
				this.blood -= Beat;
				player.blood -= beBeat;
//					判断
				if( player.blood <= 0 ){

					bloodSave2 -= 5;
					player.blood = bloodSave2;

					this.blood = bloodSave;
					System.out.println("以你现有的体力值无法打倒"+this.name+"！\n落荒而逃！损失5点体力值！");
					break;
				}
				if( this.blood <= 0 ){
//						先把血补回去
					this.blood = bloodSave;
					System.out.println(dieText+"\n胜利而归！你还剩"+player.blood+"点体力值！");
					System.out.println("本次战斗获得了"+player.win(GetExperience())+"点经验值！");
					survive = false;
					getItem = false;
					FuncSleep sleep = new FuncSleep();
					System.out.print("战斗结束");
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
		return this.name;
	}

	public boolean IfGet() {
		return getItem;
	}

	public int GetExperience() {
		if( getItem ){
			System.out.println("Boss挑战成功，获得挑战奖励和额外5点经验奖励！");
			return (this.experience+5);
		}
		else{
			return this.experience;
		}
	}

}
