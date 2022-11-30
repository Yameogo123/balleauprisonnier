package fr.icom.info.m1.balleauprisonnier_mvn.view;


import java.util.*;

import fr.icom.info.m1.balleauprisonnier_mvn.controller.MachineController;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.PlayerController;
import fr.icom.info.m1.balleauprisonnier_mvn.controller.ProjectileController;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Machine;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Projectile;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */

	private List<Machine> machines;
	private List<Player> joueurs;
	private List<Projectile> projectiles= new ArrayList<>();
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();


	private PlayerController playerController= new PlayerController();
	private MachineController machineController= new MachineController();
	private ProjectileController projectileController= new ProjectileController();

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */
		//les joueurs
		this.joueurs= playerController.initJoueur(1, gc, colorMap[0], width, height);
		playerController.displayAll();

		//la machine
		this.machines= machineController.initJoueur(4, gc, colorMap[1], width-300, 20);
		this.machineController.displayAll();



	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);

	            playerController.controlKeyboard(input, gc, projectiles);
				machineController.displayAll();
				machineController.randomMove();
				try {
					projectileController.move(projectiles);
					projectileController.degat(projectiles, machines);
				} catch (Exception e){

				}

	    	}
		}.start(); // On lance la boucle de rafraichissement
	}

	public List<Player> getJoueurs() {
		return joueurs;
	}

	public List<Machine> getMachines() {
		return machines;
	}

}
