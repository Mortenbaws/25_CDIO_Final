package field;

import controllers.GameController;
import controllers.Language; 


public class TaxPercent extends Tax {
	private int taxProcent;
	
	public TaxPercent(String Titel, String Sub, String Desc, int fieldNo, int tax,int taxPro) {
		super(Titel, Sub, Desc, fieldNo, tax);
		
		this.taxProcent = taxPro;
	}
	@Override
	public boolean landOn(GameController gameController){
		
		String answer  = gameController.getGUIController().askQuestion(Language.Field_PayTax4000Or10percent, "4000", "10%");
		if (answer == "4000"){
			return gameController.getPlayerController().getCurrentPlayer().adjustBalance(- tax);
		}
		else {
			int taxpay = gameController.getPlayerController().getTotalValueOfPlayer(gameController.getPlayerController().getCurrentPlayer(), gameController.getFieldController());
			return gameController.getPlayerController().getCurrentPlayer().adjustBalance((int)-(taxpay*(0.01*taxProcent)));
		}
	}
}
