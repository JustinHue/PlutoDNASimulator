package gui

import java.awt.Color

class Menu extends GUIComponent {

	MenuItem[] items = []
	Color unselectedColor = Color.WHITE
	Color selectedColor = Color.RED
	int selected = -1
	
	
	def addMenuItem(item) {
		items.add(item)
	}
	
	def removeMenuItem(item) {
		items.remove(item)
	}
	
	def setSelected(nSelected) {
		items[selected].selected = false
		selected = (nSelected < 0) ? 0 : (nSelected == items.size()) ? items.size() - 1 : nSelected		
		items[selected].selected = true
	}
	
	def setItems(nItems) {
		items = nItems
		if (items.size() > 0) {
			selected = 0
			items[selected].selected = true
		}		
	}
	
	def selectedMenuItem() {
		items[selected]	
	}
	
	
}
