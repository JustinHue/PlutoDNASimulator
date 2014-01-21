package plutoDNA_simulator

import java.awt.Font

import javax.imageio.*;

import groovy.io.FileType
import gui.TextLabel;

class Assets {

	public static IMAGE_EXTENSION = ".png"
	public static IMAGE_NAME_DELIMETER = "_"
	
	public static globalConfig
	
	public static ASSETS = [:]
	
	def static prepare() {
		this.globalConfig = new ConfigSlurper().parse(new File('global.ini').toURL())
		this.loadAssets(this.globalConfig.url.assets, null)
	
		// Set Text Label
		TextLabel.STANDARD_FONT = new Font(Assets.globalConfig.default_text.type,
			Assets.globalConfig.default_text.shape,
			Assets.globalConfig.default_text.font_size)
	}
	
	def static loadAssets(directory, key) {
		new File(directory).eachFile { file ->
			if (file.getName().indexOf(this.IMAGE_EXTENSION) == -1) {
				if (key != null) {
					key[file.getName()] = [:]
					this.loadAssets(directory + "/" + file.getName(), key[file.getName()])
				} else {
					this.ASSETS[file.getName()] = [:]		
					this.loadAssets(directory + "/" + file.getName(), this.ASSETS[file.getName()])
				}	
			} else {
				def tokens = file.getName().tokenize(this.IMAGE_NAME_DELIMETER)
				if (key != null) {
					if (!key[tokens[0]]) {
						key[tokens[0]] = []
					}
					key[tokens[0]].add(ImageIO.read(new File(directory + "/" + file.getName())))
				} else {
					if (!this.ASSETS[tokens[0]]) {
						this.ASSETS[tokens[0]] = []
					}
					this.ASSETS[tokens[0]].add(ImageIO.read(new File(directory + "/" + file.getName())))
				}
			}
		}
		
	}
	
}
