package com.babachene.test;

import com.babachene.gui.BabaIsUs;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Classe de test qui sert à montrer quelques bases pour afficher des trucs
 * avec libGDX. Pour le moment, j'ai compris une méthode pour afficher le monde
 * entre deux grosses barres noires lorsque les dimensions de la fenêtre ne
 * correspondent pas à celle du jeu (qui a ses dimensions virtuelles fixées).
 * <p>
 * <li>Le mode fullscreen chope la souris et la rend inutilisable si on revient
 * au bureau. Sortir du mode plein écran résout le problème.
 * </li><p>
 * @author jeremy
 */
public class TestGame extends ApplicationAdapter {
	
	/** Dimensions virtuelles du monde, en lien avec le FitViewport
	 * Attention, il se passe des trucs chelous si ces valeurs sont plus petites
	 * que config.width ou config.height dans le DesktopLauncher.
	 */
	public static final int W = 1920, H = 1080;
	
	private static Color backgroundColor = new Color(0.35f, 0.4f, 0.2f, 1);
	
	// Je ne sais toujours ce qu'est un batch.
	private ShapeRenderer sr;
	private SpriteBatch sb;
	
	// Ce truc doit servir à maintenir un bon affichage pour toute taille de fenêtre.
	private Viewport viewport;
	// Caméra pour la 2D.
	private OrthographicCamera cam;
	
	public TestGame() {}
	
	/////////////////////////////////////////
	
	@Override
	public void create() {
		sr = new ShapeRenderer();
		sb = new SpriteBatch();
		
		// Les arguments semblent peu importer car le viewport les change après.
		cam = new OrthographicCamera(W, H);//30, 30f * H / W); 
		cam.update();
		
		// Le FitViewport considère un écran virtuel avec une dimension fixée, et met des barres noires sur les côtés pour l'écran réel.
		viewport = new FitViewport(W, H, cam); // Il existe beaucoup de classes dérivant de Viewport
		
		// Hum...
		//Gdx.graphics.setWindowedMode(800, 800);
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		Gdx.gl.glViewport(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		// Change plein-écran / fenêtré avec la touche F. (pour Fullscreen, pas pour Respect)
		if (Gdx.input.isKeyJustPressed(Keys.F))
			if (Gdx.graphics.isFullscreen())
				Gdx.graphics.setWindowedMode(800, 800);
			else
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		
		// Très important : c'est ça qui manquait et que j'ai résolu par hasard au commit f9a873a6b16b955da84dda3dcc0e813ccd4664f9
		sr.setProjectionMatrix(cam.combined);
//		sr.setTransformMatrix(cam.combined);
		// Les shape renderers doivent avoir un argument dans begin.
		sr.begin(ShapeType.Filled);
		sr.setColor(backgroundColor);
		sr.rect(0, 0, W, H);
		sr.end(); // Fin du remplissage de l'arrière-plan.
		sr.begin(ShapeType.Line);
		sr.setColor(Color.CORAL);
		sr.line(W, 0f, 0f, H);
		sr.line(0, 0, W, H);
		sr.setColor(Color.CYAN);
		sr.ellipse(W / 2f, H / 2f, 150, 20, 45f);
		sr.setColor(Color.CHARTREUSE);
		sr.circle(0, 0, 100);
		sr.end();
		
		
//		sb.setProjectionMatrix(cam.combined);
//		sb.begin();
//		sb.draw(new Texture(BabaIsUs.textures.PEPE), 0,0,100,100);
//		sb.end();
		
		/*
		 * Un InputHandler basique qui fait bouger la caméra.
		 */
		
		if (Gdx.input.isKeyPressed(Keys.UP))
			viewport.getCamera().translate(0, 1f, 0);
		else if (Gdx.input.isKeyPressed(Keys.DOWN))
			viewport.getCamera().translate(0, -1, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			viewport.getCamera().translate(1, 0, 0);
		else if (Gdx.input.isKeyPressed(Keys.LEFT))
			viewport.getCamera().translate(-1, 0, 0);
		cam.update();
		/* Comment on peut le voir, redimensionner la fenêtre recentre la caméra.
		 * Cela est dû à la ligne « viewport.update(width, height, true) » le viewport
		 * recentre la caméra automatiquement. Il faudra enlever ça et gérer nous-même
		 * la position de la caméra si l'on veut avoir un niveau plus grand que la fenêtre
		 * où le personnage se déplace et la caméra avec.
		 */
	}
	
	@Override
	public void resize(int width, int height) {
		// Très important.
		viewport.update(width, height, true);
	}
	
	@Override
	public void dispose() {
		sr.dispose();
		sb.dispose();
	};
	
}
