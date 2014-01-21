package plutoDNA_simulator

import gui.TextLabel;

class MenuItem implements IMenuItem {

	def label
	
	public MenuItem() {
		this.label = new TextLabel("Menu Item")
	}
	
}
