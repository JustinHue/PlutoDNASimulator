package plutoDNA_simulator

import java.awt.image.BufferedImage
import javax.swing.JFrame

class SimulatorWindow extends JFrame {

	private SimulatorSurface surface
	
	public SimulatorWindow(world) {
				
		this.surface = new SimulatorSurface(world) 
		this.add(this.surface)
		
		this.setTitle(Assets.globalConfig.window.title)
		this.setSize(Assets.globalConfig.window.width, Assets.globalConfig.window.height)
		if (Assets.globalConfig.window.options.setDefaultCloseOperation) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		}
		if (Assets.globalConfig.window.options.setLocationRelativeTo) {
			setLocationRelativeTo(null)
		}		

	}
	
	public start() {
		this.setVisible(Assets.globalConfig.window.options.setVisible)
		this.surface.start()
	}
	
	public drawBuffer(buffer) {
		this.surface.render(buffer)
	}
}
