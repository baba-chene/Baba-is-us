package com.babachene.controller;

import java.io.IOException;

import com.babachene.cliserv.InputEvent;
import com.babachene.gui.LevelState;
import com.babachene.gui.MainGame;
import com.babachene.logic.GameLogic;
import com.babachene.logic.data.LevelMap;
import com.babachene.logic.data.MapEditorConverter;
import com.babachene.userinput.EventGiver;
import com.babachene.userinput.KeyboardMap;
import com.babachene.userinput.LevelInputProcessor;
import com.badlogic.gdx.Input.Keys;

/**
 * A controller for a solo game with no client / server tempering.
 * @author jeremy
 *
 */
public class SoloController extends Controller {
	
	private MainGame mainGame;
	private GameLogic logic;
	private EventGiver giver;
	private LevelInputProcessor inputProcessor;
	
	public SoloController(MainGame mainGame) {
		if (mainGame == null)
			throw new IllegalArgumentException("MainGame cannot be null.");
		this.mainGame = mainGame;
		giver = new EventGiver();
		inputProcessor = new LevelInputProcessor(new KeyboardMap(Keys.Z, Keys.S, Keys.Q, Keys.D, Keys.A, Keys.E, Keys.A, Keys.A,
													Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT), giver, true);
		
		// No creation of logic, map. It's in launchLevel
	}
	
	@Override
	public void launchLevel(String levelName) {
		/*
		 * TEST ZONE : Create a custom map for testing.
		 */
		/*
		LevelMap map = new LevelMap(10, 10);
		
		map.addEntity(2, 4, "baba");
		map.addEntity(5, 5, "textis");
		map.addEntity(5, 8, "textbaba");
		map.addEntity(4, 2, "rock");
		map.addEntity(5, 5, "textis");
		map.addEntity(5, 8, "textbaba");
		map.addEntity(4, 2, "rock");
		map.addEntity(5, 2, "textrock");
		map.addEntity(5, 4, "textpush");
		map.addEntity(5, 7, "textsink");
		map.addEntity(1, 1, "textbaba");
		map.addEntity(2, 1, "textis");
		map.addEntity(3, 1, "textyou");
		map.addEntity(6, 5, "textyou");
		map.addEntity(6, 6, "textrock");
		map.addEntity(4, 7, "water");
		map.addEntity(3, 7, "textwater");
		map.addEntity(7, 2, "wall");
		map.addEntity(6, 3, "textand");*/
		
	/*	LevelMap map = new LevelMap(30,20);
		map.addEntity(0, 0, "textbaba");
		map.addEntity(1, 0, "textis");
		map.addEntity(2, 0, "textyou");
		map.addEntity(0, 1, "textlava");
		map.addEntity(1, 1, "textis");
		map.addEntity(2, 1, "textkill");
		map.addEntity(0, 2, "baba");
		map.addEntity(15, 12, "lava");
		map.addEntity(12,14,"lava");*/


			
		
		MapEditorConverter mapEditorConverter = new MapEditorConverter(30, 20);
		try {

			//mapEditorConverter.open("maps\\K�k� is lost.txt\\");
			mapEditorConverter.open("C:\\Users\\Guillaume\\Desktop\\test7.txt\\");
		//	mapEditorConverter.open("maps/"+levelName+".txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Level doesn't exist");
			e.printStackTrace();
		}
		LevelMap map = mapEditorConverter.getMap();
//		LevelMap map = CtrlTest.gimmeLevel(); // comment this line if you want to load a level file.
		logic = new GameLogic(map);
		
		mainGame.push(new LevelState(mainGame, map, inputProcessor));
	}
	
	@Override
	public void update() {
		
		fetchEvent();
		
	}
	
	public void fetchEvent() {
		while (giver.size() != 0) {
			logic.processInput( (InputEvent) giver.pollEvent());
		}
	}

	@Override
	public void close() {
	}
	
}
