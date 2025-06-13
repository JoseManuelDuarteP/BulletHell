package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;

public class DisparoFuerte extends Disparo {

    public DisparoFuerte(double coordenadasX, double coordenadasY, ImageView sprite, int danyo) {
        super(coordenadasX, coordenadasY, sprite, danyo);
        this.setDanyo(3);
    }
}
