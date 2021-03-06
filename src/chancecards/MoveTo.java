 package chancecards;

import controllers.GameController;
import player.Player;

public class MoveTo extends ChanceCard {
	
	private int fieldID;

	public MoveTo(String description, int ID) {
		super(description);
		this.fieldID = ID;
	}
	
	@Override
	public boolean drawCardAction(GameController gc){
		Player currentPlayer = gc.getPlayerController().getCurrentPlayer();
		int pos = currentPlayer.getPosition();
		
		//add 4k if player pass start
		if(fieldID<pos){
			if(fieldID!=39)//townhallCC no reward for passing start
				currentPlayer.adjustBalance(4000);
		}
		//set new player pos
		currentPlayer.setPosition(fieldID);
		
		//update car position on GUI (to bypass position not being updated before questions asked due to app-stall from landOn)
		gc.getGUIController().updatePlayerPosition(currentPlayer);
		gc.getFieldController().getFields()[fieldID].landOn(gc);
		
		return true;
	}

}
