package plutoDNA_simulator

import scene.MenuScene
import java.awt.event.KeyEvent

class SimulatorMenuScene extends MenuScene {

	public SimulatorMenuScene() {	
		title.text = "Pluto DNA Simulator"
		version.text = "Pluto DNA Simulator v1.0"
		copyright.text = "Copyright Pluto Corporation. Do not distribute!"
	}

	@Override
	public def AI() {

	}
	
	@Override
	public def keysDown(keys) {

	}

	@Override
	public def keysUp(keys) {

	}

	@Override
	public def keysPressed(keys) {
		if (keys[KeyEvent.VK_DOWN]) {
			menu.selected += 1
		} 
		if (keys[KeyEvent.VK_UP]) {
			menu.selected -= 1
		}
		if (keys[KeyEvent.VK_ENTER]) {
			if (menu.selectedMenuItem().text == "Play") {
				exit = true
			} else if (menu.selectedMenuItem().text == "Exit") {
				quit = true
			}
		}
	}

	@Override
	public def mousePressed(buttons) {

	}

	@Override
	public def mouseReleased(buttons) {

	}

	@Override
	public def mouseDown(buttons) {

	}

	@Override
	public def mouseUp(buttons) {

	}


}
