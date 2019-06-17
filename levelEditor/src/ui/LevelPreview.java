package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import level.Level;

public class LevelPreview extends JFrame {

	private final EditorFrame editorFrame;
	private Level level;
	
	public LevelPreview(EditorFrame editorFrame, Level level) {
			
			super();
			
			this.editorFrame = editorFrame;
			this.level = level;
	
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(800, 1200));
		}
	
}
