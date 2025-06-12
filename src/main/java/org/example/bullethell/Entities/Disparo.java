package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class Disparo {
    private double coordenadasX;
    private double coordenadasY;
    private ImageView sprite;

    public Disparo(double coordenadasX, double coordenadasY, ImageView sprite) {
        this.coordenadasX = coordenadasX;
        this.coordenadasY = coordenadasY;
        this.sprite = sprite;
    }

    public double getCoordenadasX() {
        return coordenadasX;
    }

    public void setCoordenadasX(double coordenadasX) {
        this.coordenadasX = coordenadasX;
    }

    public double getCoordenadasY() {
        return coordenadasY;
    }

    public void setCoordenadasY(double coordenadasY) {
        this.coordenadasY = coordenadasY;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
}
