package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class DisparoNormal extends Disparo {

    public DisparoNormal(double coordenadasX, double coordenadasY, ImageView sprite, int danyo) {
        super(coordenadasX, coordenadasY, sprite, danyo);
        this.setDanyo(1);
    }
}
