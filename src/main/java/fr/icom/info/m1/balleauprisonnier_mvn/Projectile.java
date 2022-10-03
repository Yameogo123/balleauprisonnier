package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Projectile {

    private double vitesse;
    private String direction;


    Projectile(double vitesse, String direction){
        this.vitesse= vitesse;
        this.direction= direction;
        Image bal = new Image("assets/ball.png");
    }


}
