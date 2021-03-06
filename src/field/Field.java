  package field;

import controllers.GameController;

public abstract class Field {
	
	protected String name, subText, descriptionText ; 
	private int number;
	
	
	public Field (String name, String descriptionText, String subText, int number) {
		this.name = name;
		this.subText = subText;
		this.descriptionText = descriptionText;
		this.number = number;
		
	}

	/**
	 * General landOn method
	 * @param gameController
	 * @return
	 */
	public abstract boolean landOn(GameController gameController);

	public String getName() {
		return name;
	}

	public String getSubText() {
		return subText;
	}

	public String getDescriptionText() {
		return descriptionText;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setSubtext(String text){
		this.subText += text;
	}


	
	
	
}
	
	

