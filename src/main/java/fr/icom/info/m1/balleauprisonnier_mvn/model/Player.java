package fr.icom.info.m1.balleauprisonnier_mvn.model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Classe gerant un joueur
 *
 */
@Getter
@Setter
public class Player 
{
	  private double x;       // position horizontale du joueur
	  private final double y; 	  // position verticale du joueur
	  private double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	  private double step;    // pas d'un joueur
	  private String playerColor;

	  //
	  private String side;
	  
	  // On une image globale du joueur 
	  private Image directionArrow;
	  private Sprite sprite;
	  private ImageView PlayerDirectionArrow;
	  private Image tilesheetImage;
	  private GraphicsContext graphicsContext;

	  private Projectile proj;
	  
	  /**
	   * Constructeur du Joueur
	   * 
	   * @param gc ContextGraphic dans lequel on va afficher le joueur
	   * @param color couleur du joueur
	   * @param yInit position verticale
	   */
	  public Player(GraphicsContext gc, String color, int xInit, int yInit, String side, double step)
	  {
		// Tous les joueurs commencent au centre du canvas, 
	    this.x = xInit;
	    this.y = yInit;
	    this.graphicsContext = gc;
	    this.playerColor=color;
	    
	    this.angle = 0;

		this.side=side;

	    // On charge la representation du joueur
        if(side=="top"){
        	this.directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			this.directionArrow = new Image("assets/PlayerArrowUp.png");
		}
        
        this.PlayerDirectionArrow = new ImageView();
        this.PlayerDirectionArrow.setImage(directionArrow);
        this.PlayerDirectionArrow.setFitWidth(10);
        this.PlayerDirectionArrow.setPreserveRatio(true);
        this.PlayerDirectionArrow.setSmooth(true);
        this.PlayerDirectionArrow.setCache(true);

        this.tilesheetImage = new Image("assets/orc.png");
        this.sprite = new Sprite(this.tilesheetImage, 0,0, Duration.seconds(.2), side);
        this.sprite.setX(x);
        this.sprite.setY(y);
        //directionArrow = sprite.getClip().;

	    // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
        // Random randomGenerator = new Random();
        // step = randomGenerator.nextFloat();

        // Pour commencer les joueurs ont une vitesse / un pas fixe
        this.step = step;
	    
	  }

	  /**
	   *  Affichage du joueur
	   */
	  public void display()
	  {
		  this.graphicsContext.save(); // saves the current state on stack, including the current transform
	      rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
		  this.graphicsContext.drawImage(directionArrow, x, y);
		  this.graphicsContext.restore(); // back to original state (before rotation)
	  }

	  private void rotate(GraphicsContext gc, double angle, double px, double py) {
		  Rotate r = new Rotate(angle, px, py);
		  gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	  }
	  
	  /**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */

	  public void moveLeft()
	  {	    
	    if (this.x > 10 && this.x < 520)
	    {
			spriteAnimate();
		    this.x -= step;
	    }
	  }

	  /**
	   *  Deplacement du joueur vers la droite
	   */
	  public void moveRight()
	  {
	    if (x > 10 && x < 520) 
	    {
			spriteAnimate();
		    x += step;
	    }
	  }

	  
	  /**
	   *  Rotation du joueur vers la gauche
	   */
	  public void turnLeft()
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle += 1;
	    }
	    else {
	    	angle += 1;
	    }

	  }

	  
	  /**
	   *  Rotation du joueur vers la droite
	   */
	  public void turnRight()
	  {
	    if (angle > 0 && angle < 180) 
	    {
	    	angle -=1;
	    }
	    else {
	    	angle -= 1;
	    }
	  }


	  public void shoot(){
		  //this.proj= new Projectile(graphicsContext, 1, this.angle,x, y);
		  this.sprite.playShoot();
		  //proj.shoot();
		  //System.out.println(proj.getY());
	  }
	  
	  /**
	   *  Deplacement en mode boost
	   */
	  public void boost()
	  {
	    x += step*2;
		  spriteAnimate();
	  }

	  public void spriteAnimate(){
	  	  //System.out.println("Animating sprite");
		  if(!sprite.isRunning) {sprite.playContinuously();}
		  sprite.setX(x);
		  sprite.setY(y);
	  }




	  
}
