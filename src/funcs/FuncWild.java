package funcs;

import castle.Game;

public class FuncWild extends FuncSrc {

	public FuncWild(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		// 传送
		game.WildRoom();
	}

}
