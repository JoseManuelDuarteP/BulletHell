package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public abstract class Disparo {
    private double coordenadasX;
    private double coordenadasY;
    private ImageView sprite;
    private int danyo;

    public Disparo(double coordenadasX, double coordenadasY, ImageView sprite, int danyo) {
        this.coordenadasX = coordenadasX;
        this.coordenadasY = coordenadasY;
        this.sprite = sprite;
        this.danyo = danyo;
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

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }
}
