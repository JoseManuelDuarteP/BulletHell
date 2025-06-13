package org.example.bullethell.Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Destructor extends NaveEnemiga {

    private static final Image imagenDestructor = new Image(Destructor.class.getResourceAsStream("/sprites/destructor.png")); // Puedes cambiar luego

    public Destructor(double x, double y, int patron) {
        super(x, y, new ImageView(imagenDestructor), 6, patron, 0, 600, -2);
        sprite.setFitWidth(90);
        sprite.setFitHeight(90);
    }

    @Override
    public void disparar(Pane fondoDelJuego, List<Disparo> listaDisparos) {
        // Dispara 3 proyectiles: recto, diagonal arriba, diagonal abajo

        Image imagenDisparo = new Image(getClass().getResourceAsStream("/sprites/disparo_enemigo.png"));

        // Recto
        ImageView d1 = new ImageView(imagenDisparo);
        d1.setFitWidth(20);
        d1.setFitHeight(10);
        DisparoNormal disparoRecto = new DisparoNormal(coordenadaX - d1.getFitWidth(),
                coordenadaY + sprite.getFitHeight() / 2 - d1.getFitHeight() / 2, d1,1);
        fondoDelJuego.getChildren().add(d1);
        listaDisparos.add(disparoRecto);

        // Diagonal arriba
        ImageView d2 = new ImageView(imagenDisparo);
        d2.setFitWidth(20);
        d2.setFitHeight(10);
        d2.setRotate(30);
        DisparoDiagonal disparoDiagArriba = new DisparoDiagonal(coordenadaX - d2.getFitWidth(),
                coordenadaY + sprite.getFitHeight() / 2 - d2.getFitHeight() / 2, d2, 1, -2); // -2 velocidadY para subir
        fondoDelJuego.getChildren().add(d2);
        listaDisparos.add(disparoDiagArriba);

        // Diagonal abajo
        ImageView d3 = new ImageView(imagenDisparo);
        d3.setFitWidth(20);
        d3.setFitHeight(10);
        d3.setRotate(-30);
        DisparoDiagonal disparoDiagAbajo = new DisparoDiagonal(coordenadaX - d3.getFitWidth(),
                coordenadaY + sprite.getFitHeight() / 2 - d3.getFitHeight() / 2, d3, 1, 2); // +2 velocidadY para bajar
        fondoDelJuego.getChildren().add(d3);
        listaDisparos.add(disparoDiagAbajo);
    }
}
