package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class NaveEnemiga {
    private double coordenadaX;
    private double coordenadaY;
    private ImageView sprite;

    public NaveEnemiga(double coordenadaX, double coordenadaY, ImageView sprite) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.sprite = sprite;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
}
