package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Machine extends Player {


    /**
     * Constructeur du Joueur
     *
     * @param gc    ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     * @param step
     */
    public Machine(GraphicsContext gc, String color, int xInit, int yInit, String side, double step) {
        super(gc, color, xInit, yInit, side, step);
    }


}
