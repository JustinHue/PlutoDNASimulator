package gui

import java.awt.Font
import java.awt.FontMetrics
import java.awt.Color

class TextLabel extends GUIComponent implements ITextLabel {

	String text = ""
	Font font = new Font("Serif", Font.BOLD, 12)
	Color color = Color.WHITE
	String alignment = AlignmentEnum.MIDDLE

}
