package application.controller;

import java.time.LocalDate;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Jonathan
 *
 */
public class PaneNode extends Pane{
	
	private LocalDate date;
	
	public PaneNode(Node... children) {
		super(children);
		
		//TODO: Set a listener, onPaneClick display info side bar/color the week
	}// END constructor
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
