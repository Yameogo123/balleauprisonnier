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
public class Projectile
{
    private double x;       // position horizontale de la balle
    private double y; 	  // position verticale de la balle
    private double angle = 90; // rotation de la balle
    private double step;    // vitesse de la balle

    //coté d'ou part la balle
    private String side;
    private Sprite sprite;
    private ImageView ProjectileImg;
    private Image img;
    private GraphicsContext graphicsContext;

    public Projectile(GraphicsContext gc, double xInit, double yInit, String side, double vitesse)
    {
        // Tous les joueurs commencent au centre du canvas,
        this.x = xInit;
        this.y = yInit;
        this.graphicsContext = gc;

        this.angle = 0;

        this.side=side;
        this.img = new Image("assets/ball.png");
        this.ProjectileImg = new ImageView();
        this.ProjectileImg.setImage(this.img);
        this.ProjectileImg.setFitWidth(5);
        this.ProjectileImg.setPreserveRatio(true);
        this.ProjectileImg.setSmooth(true);
        this.ProjectileImg.setCache(true);
        this.step = vitesse;

        this.sprite = new Sprite(this.img, 0,0, Duration.seconds(.2), side);
        this.sprite.setX(x);
        this.sprite.setY(y);

    }

    /**
     *  Affichage de la balle
     */
    public void display()
    {
        this.graphicsContext.save(); // saves the current state on stack, including the current transform
        rotate(graphicsContext, angle, x + img.getWidth() / 2, y + img.getHeight() / 2);
        this.graphicsContext.drawImage(this.img, x, y);
        this.graphicsContext.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }


    /**
     *  Deplacement de la balle
     */
    public void moveTop()
    {
        if (y > 10 && y < 520)
        {
            spriteAnimate();
            if(side=="bottom"){
                y -= step;
                x+= angle/20;
            }else{
                y += step;
                x-= angle/20;
            }
        }
    }



    public void shoot(){
        this.sprite.playShoot();
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
