package field;

import controllers.GameController;

public class Chance extends Field {

	public Chance(String Titel, String Sub, String Desc, int fieldNo) {
		super(Titel, Sub, Desc, fieldNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean landOn(GameController GameController) {
		GameController.ChanceCardController.drawCard(GameController);
		return false;
	}

}