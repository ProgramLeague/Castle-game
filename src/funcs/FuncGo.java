package funcs;

import castle.Game;

public class FuncGo extends Funcsrc {

	public FuncGo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void DoFunc(String cmd) {
		// �����ƶ���
		game.goRoom(cmd);
	}

}
