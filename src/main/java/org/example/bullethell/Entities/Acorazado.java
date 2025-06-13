package org.example.bullethell.Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Acorazado extends NaveEnemiga {

    private static final Image imagenAcorazado = new Image(Acorazado.class.getResourceAsStream("/sprites/acorazado.png")); // Cambia la imagen

    public Acorazado(double x, double y) {
        super(x, y, new ImageView(imagenAcorazado), 12, 1, 0, 600, -1.5);
        sprite.setFitWidth(200);
        sprite.setFitHeight(120);
    }

    @Override
    public void disparar(Pane fondoDelJuego, List<Disparo> listaDisparos) {
        Image imagenDisparoFuerte = new Image(getClass().getResourceAsStream("/sprites/disparo_enemigo_fuerte.png"));

        // Recto 1
        ImageView d1 = new ImageView(imagenDisparoFuerte);
        d1.setFitWidth(30);
        d1.setFitHeight(15);
        DisparoFuerte disparo1 = new DisparoFuerte(coordenadaX - d1.getFitWidth(),
                coordenadaY + sprite.getFitHeight() / 2 - d1.getFitHeight() - 5, d1, 3);
        fondoDelJuego.getChildren().add(d1);
        listaDisparos.add(disparo1);

        // Recto 2 (debajo del anterior)
        ImageView d2 = new ImageView(imagenDisparoFuerte);
        d2.setFitWidth(30);
        d2.setFitHeight(15);
        DisparoFuerte disparo2 = new DisparoFuerte(coordenadaX - d2.getFitWidth(),
                coordenadaY + sprite.getFitHeight() / 2 + 5, d2, 3);
        fondoDelJuego.getChildren().add(d2);
        listaDisparos.add(disparo2);

        // Diagonal arriba (cañón superior izquierdo)
        ImageView d3 = new ImageView(imagenDisparoFuerte);
        d3.setFitWidth(30);
        d3.setFitHeight(15);
        d3.setRotate(30);
        DisparoDiagonal disparoDiagArriba = new DisparoDiagonal(
                coordenadaX + 50, // bastante detrás del sprite
                coordenadaY + 20, // más cerca del borde superior del sprite
                d3, 3, -3); // y se mueve arriba
        fondoDelJuego.getChildren().add(d3);
        listaDisparos.add(disparoDiagArriba);

        // Diagonal abajo (cañón inferior izquierdo)
        ImageView d4 = new ImageView(imagenDisparoFuerte);
        d4.setFitWidth(30);
        d4.setFitHeight(15);
        d4.setRotate(-30);
        DisparoDiagonal disparoDiagAbajo = new DisparoDiagonal(
                coordenadaX + 50, // también por detrás del sprite
                coordenadaY + sprite.getFitHeight() - 30, // más cerca del borde inferior
                d4, 3, 3); // y se mueve abajo
        fondoDelJuego.getChildren().add(d4);
        listaDisparos.add(disparoDiagAbajo);
    }

}
