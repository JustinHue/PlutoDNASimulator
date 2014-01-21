package scene

import gui.TextLabel
import java.awt.Font
import gui.AlignmentEnum

class MenuScene extends Scene {
	
	public MenuScene() {
		this.addComponent(new TextLabel(text:"Title", alignment:AlignmentEnum.TOP_MIDDLE))
		this.addComponent(new TextLabel(text:"Version", coordinate:[10, -10], alignment:AlignmentEnum.FLUSH_BOTTOM_LEFT))
		this.addComponent(new TextLabel(text:"Copyright", coordinate:[-10, -10], alignment:AlignmentEnum.FLUSH_BOTTOM_RIGHT))
	}

	@Override
	public def AI() {

	}
	
}
