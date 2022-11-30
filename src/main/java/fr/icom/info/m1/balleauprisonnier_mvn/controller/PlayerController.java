package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import fr.icom.info.m1.balleauprisonnier_mvn.model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.view.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Player;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    //récupere la liste des joueurs à afficher
    private List<Player> joueurs = new ArrayList<>();
    private ProjectileController projectileController= new ProjectileController();

    //initialise les joueurs et les charges dans la liste
    public List<Player> initJoueur(int n, GraphicsContext gc, String color, int w, int h){
        if(n==1){
            Player joueur1 = new Player(gc,  color, w/2, h-100, "bottom", 1);
            joueurs.add(joueur1);
        }else{
            for (int i = 0; i < n; i++) {
                Player j= new Player(gc,  color, w/2+100*i, h-100, "bottom", 1);
                joueurs.add(j);
            }
        }
        return joueurs;
    }

    //permet l'affichage des joueurs dans le sprite charger dans la liste
    public void displayAll(){
        for (Player p: joueurs) {
            p.display();
        }
    }

    //permet de de controller les gestes des joueurs
    public void controlKeyboard(ArrayList<String> input, GraphicsContext gc, List<Projectile> l){
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
            if (i==1 && input.contains("SHIFT")){
                joueurs.get(i).shoot();
            }
            if (i==0 && input.contains("SPACE")){
                Projectile p=new Projectile(gc, joueurs.get(i).getX(), joueurs.get(i).getY(), "bottom", 2);
                p.setAngle(joueurs.get(i).getAngle());
                projectileController.fill(p, l);
                joueurs.get(i).shoot();
            }
            joueurs.get(i).display();
        }
    }

    //charge le sprite du joueur dans la vue
    public void loadInStage(Group root, Field gameField){
        for (int i = 0; i < gameField.getJoueurs().size(); i++) {
            root.getChildren().add(gameField.getJoueurs().get(i).getSprite());
        }
    }
}
