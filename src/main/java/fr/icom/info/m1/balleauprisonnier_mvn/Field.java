package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	List<Player> joueurs = new ArrayList<>();
	List<Machine> machines= new ArrayList<>();

	List<Projectile> projectiles= new ArrayList<>();
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

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
    	Player joueur1 = new Player(gc, colorMap[0], w/2, h-50, "bottom", 1);
		joueurs.add(joueur1);
    	joueur1.display();

		//la machine
		Machine m1= new Machine(gc, colorMap[1], w/2, 20, "top", 1);
		Machine m2= new Machine(gc, colorMap[1], w/2+ 100, 20, "top", 1);
		Machine m3= new Machine(gc, colorMap[1], w/2+ 200, 20, "top", 1);
		machines.addAll(Arrays.asList(m1, m2, m3));
    	//joueurs[1] = new Player(gc, colorMap[1], w/2, 20, "top", 2);
    	//joueurs[1].display();


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
	        	
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.size(); i++)
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
	        			joueurs.get(i).moveLeft();
	        		} 
	        		if (i==0 && input.contains("RIGHT")) 
	        		{
	        			joueurs.get(i).moveRight();
	        		}
	        		if (i==0 && input.contains("UP"))
	        		{
	        			joueurs.get(i).turnLeft();
	        		} 
	        		if (i==0 && input.contains("DOWN")) 
	        		{
	        			joueurs.get(i).turnRight();
	        		}
	        		if (i==1 && input.contains("A"))
	        		{
	        			joueurs.get(i).moveLeft();
	        		} 
	        		if (i==1 && input.contains("D")) 
	        		{
	        			joueurs.get(i).moveRight();
	        		}
	        		if (i==1 && input.contains("W"))
	        		{
	        			joueurs.get(i).turnLeft();
	        		} 
	        		if (i==1 && input.contains("S")) 
	        		{
	        			joueurs.get(i).turnRight();
	        		}
	        		if (i==1 && input.contains("SPACE")){
	        			joueurs.get(i).shoot();
					}
					if (i==0 && input.contains("SHIFT")){
						joueurs.get(i).shoot();
					}
	        		joueurs.get(i).display();
	    	    }

				for(Machine m : machines){
					m.display();
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
