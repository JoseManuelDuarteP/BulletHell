package org.example.bullethell.Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Interceptor extends NaveEnemiga {

    private static final Image imagenInterceptor = new Image(Interceptor.class.getResourceAsStream("/sprites/interceptor.png"));

    public Interceptor(double x, double y, int patron) {
        super(x, y, new ImageView(imagenInterceptor), 3, patron, 0, 600, -3);
        sprite.setFitWidth(60);
        sprite.setFitHeight(60);
    }

    @Override
    public void disparar(Pane fondoDelJuego, List<Disparo> listaDisparos) {
        // Dispara 1 disparo recto (patrón 1,2 o 3)
        Image imagenDisparo = new Image(getClass().getResourceAsStream("/sprites/disparo_enemigo.png"));
        ImageView spriteDisparo = new ImageView(imagenDisparo);
        spriteDisparo.setFitWidth(20);
        spriteDisparo.setFitHeight(10);

        double x = coordenadaX - spriteDisparo.getFitWidth();
        double y = coordenadaY + sprite.getFitHeight() / 2 - spriteDisparo.getFitHeight() / 2;

        DisparoNormal disparo = new DisparoNormal(x, y, spriteDisparo, 1);
        fondoDelJuego.getChildren().add(spriteDisparo);
        listaDisparos.add(disparo);
    }
}
