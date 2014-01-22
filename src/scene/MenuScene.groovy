package scene

import gui.Menu
import gui.MenuItem
import gui.TextLabel
import gui.AlignmentEnum
import java.awt.Font

class MenuScene extends Scene {
	
	TextLabel title = new TextLabel(text:"Title", alignment:AlignmentEnum.TOP_MIDDLE)
	TextLabel version = new TextLabel(text:"Version", coordinate:[10, -10], alignment:AlignmentEnum.FLUSH_BOTTOM_LEFT)
	TextLabel copyright = new TextLabel(text:"Copyright", coordinate:[-10, -10], alignment:AlignmentEnum.FLUSH_BOTTOM_RIGHT)
	Menu menu = new Menu(items:[new MenuItem(text:"Play", coordinate:[0, -50]), 
		new MenuItem(text:"Options", coordinate:[0, 0]), 
		new MenuItem(text:"Exit", coordinate:[0, 50])])
	
	public MenuScene() {
		this.addComponent(title)
		this.addComponent(version)
		this.addComponent(copyright)
		this.addComponent(menu)
	}

	@Override
	public def AI() {

	}
	
}
