package fr.icom.info.m1.balleauprisonnier_mvn.controller;

import fr.icom.info.m1.balleauprisonnier_mvn.view.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.model.Machine;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MachineController {

    //contiendra la liste des ennemis
    List<Machine> machines=new ArrayList<>();

    //direction of ennemis
    String[] move = {"right", "left", "left", "right", "left"};

    //initialise les ennemis et les ajoute dans la liste avec leur position et le nombre
    public List<Machine> initJoueur(int n, GraphicsContext gc, String color, int w, int h){
        if(n==1){
            Machine m1 = new Machine(gc,  color, w/2, h, "top", 1);
            this.machines.add(m1);
        }else{
            for (int i = 0; i < n; i++) {
                Machine m= new Machine(gc, color, w/2+100*i, h, "top", 1);
                machines.add(m);
            }
        }
        return machines;
    }

    //affiche tous les ennemis
    public void displayAll(){
        for (Machine p: machines) {
            p.display();
        }
    }

    //les charge dans la racine de l'app
    public void loadInStage(Group root, Field gameField){
        for (int i = 0; i < gameField.getMachines().size(); i++) {
            root.getChildren().add(gameField.getMachines().get(i).getSprite());
        }
    }

    //gere le mouvement aleatoire des ennemis
    public void randomMove(){
        for (int i = 0; i < machines.size(); i++) {
            if(machines.get(i).getX() < 15){
                move[i]= "right";
            }else if(machines.get(i).getX() >= 510){
                move[i]="left";
            }
            if (move[i].compareToIgnoreCase("left")==0){
                machines.get(i).moveLeft();
            }else{
                machines.get(i).moveRight();
            }
        }
    }
}
