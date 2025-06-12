package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class NaveEnemiga {
    private double coordenadaX;
    private double coordenadaY;
    private ImageView sprite;
    private int hp;

    public NaveEnemiga(double coordenadaX, double coordenadaY, ImageView sprite, int hp) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.sprite = sprite;
        this.hp = hp;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
