package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;


public class EditorFrame extends JFrame {
	
	private final Editor editor;
	private final FileMenuBar fileMenuBar;
	private final LevelPreview levelPreview;
	private final ObjectList objectList;
	
	public EditorFrame (Editor editor) {
		
		this.editor=editor;
		setVisible(true);
		setLayout(new BorderLayout());
		
		//menuBar
		fileMenuBar = new FileMenuBar(this);
		setJMenuBar(fileMenuBar);
		
		//level preview
		levelPreview= new LevelPreview(EditorFrame);
		
		//buttonList
		objectList=new ObjectList(EditorFrame);
		
		Container c = getContentPane();
		c.add(levelPreview, BorderLayout.CENTER);
		c.add(objectList, BorderLayout.EAST);
		
		setPreferredSize(new Dimension(1920, 1080));
		pack();
		
	}
	
}
