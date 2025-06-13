package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class DisparoDiagonal extends Disparo {
    private double velocidadX = -6; // Siempre hacia la izquierda
    private double velocidadY;

    public DisparoDiagonal(double coordenadasX, double coordenadasY, ImageView sprite, int danyo, double velocidadY) {
        super(coordenadasX, coordenadasY, sprite, danyo);
        this.velocidadY = velocidadY;
    }

    public void mover() {
        setCoordenadasX(getCoordenadasX() + velocidadX);
        setCoordenadasY(getCoordenadasY() + velocidadY);
    }
}
