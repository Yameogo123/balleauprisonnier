package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import fr.icom.info.m1.balleauprisonnier_mvn.model.Machine;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Projectile;

import java.util.List;
import javafx.scene.image.Image;

public class ProjectileController {

    //permet d'ajouter la balle dans la liste de projectile
    public void fill(Projectile projectile, List<Projectile> all){
        all.add(projectile);
    }

    //permet de faire bouger le projectile juqu'au camp de l'ennemi
    public void move(List<Projectile> projectiles){
        if(!projectiles.isEmpty()){
            for(Projectile p: projectiles){
                p.display();
                p.moveTop();
                //si le projectile atteint un certain y il disparait du canvas
                if(p.getY()==10) projectiles.remove(p);
            }
        }
    }

    //gere les degat occasionner par les tirs
    public void degat(List<Projectile> projectiles, List<Machine> ennemis){
        if(!projectiles.isEmpty()) {
            for (Projectile p : projectiles) {
                for (Machine e : ennemis) {
                    //System.out.println(Math.round(p.getX())+" --e "+e.getX());
                    if (e.getY() == p.getY() && Math.round(p.getX())-15 <= Math.round(e.getX()) && Math.round(e.getX()) <= Math.round(p.getX())+15) {
                        //stop le sprite
                        e.getSprite().stop();
                        //charge l'image de l'explosion
                        Image img= new Image("assets/explosion.png");
                        e.getSprite().setImage(img);
                        //puis retire l'ennemi de la liste
                        ennemis.remove(e);
                    }
                }
            }
        }
    }
}
