package field;

import controllers.GameController;
import controllers.Language;
import player.Player;

public class Brewery extends Ownable {
	private int[] rents = { 100, 200 };

	public Brewery(String titel, String sub, String desc, int fieldNo, int price, int pawnPrice, int[] rents) {
		super(titel, sub, desc, fieldNo, price, pawnPrice);
		this.rents = rents;

	}

	@Override
	public boolean landOn(GameController gameController) {
		boolean result = true;
		Player currentPlayer = gameController.getPlayerController().getCurrentPlayer();
		if (this.owner == null && currentPlayer.getBalance() >= this.price || this.isPawn == true && this.owner != currentPlayer) {
			if (gameController.getPlayerController().getCurrentPlayer().getAccount().getBalance() >= price) {
				boolean answer = gameController.getGUIController().askYesNoQuestion(Language.Field_DoYouWantToBuy+this.name+Language.Field_For +this.price+ "?");
				if (answer == true) {
					owner = currentPlayer;
					this.setSubtext(this.owner.getName());
					result = currentPlayer.adjustBalance(-price);

				} else if (answer == false) {
					result = true;
				}
			}
		}

		if (this.owner != null && this.owner != currentPlayer && this.owner.isJailed() == false && this.isPawn == false) {
			int i = gameController.getFieldController().getOwnerShipOfBreweries(this.owner)-(gameController.getFieldController().getAmountofPawnedFleets(gameController, this.owner))-1;
			int pay = rents[i] * gameController.getCup().getDiceSum();
			gameController.getGUIController().showMessage(currentPlayer.getName() + Language.Field_HasLandedOn+ this.getName() + ". " + this.owner.getName()
			+ Language.Field_OwnerOfTheStreet + pay + Language.Field_AmountToPay);
			
			// REMOVED
																		// -1
																		// CAUSING
																		// ARRAY_OOB.
																		// turn
																		// starts
																		// at 0
			if (currentPlayer.getAccount().getBalance() > pay) {
				result = currentPlayer.adjustBalance(-pay);
				this.owner.adjustBalance(pay);
				

			} else{
				int lastBalance = currentPlayer.getBalance();
				this.owner.adjustBalance(lastBalance);
				result = false;
			}
				

		}

		return result;
	}
	/** Returns the rent of a brewery. Considers how many breweries are owned by the player
	 * and also how many of these are pawned.
	 */
	@Override
	public int getRent(GameController gameController){
		int rent = 0;
		int breweries = gameController.getFieldController().getOwnerShipOfBreweries(this.owner)-(gameController.getFieldController().getAmountofPawnedFleets(gameController, this.owner));
		rent = this.rents[breweries-1]*gameController.getCup().getDiceSum();
		
		return rent;
		
	}
}
