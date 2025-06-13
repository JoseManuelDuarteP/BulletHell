package org.example.bullethell.Entities;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public abstract class NaveEnemiga {
    protected double coordenadaX;
    protected double coordenadaY;
    protected ImageView sprite;
    protected int hp;
    protected double velocidadX;

    protected int patronMovimiento;
    protected double tiempoMovimiento = 0;

    protected double posicionBaseY;
    protected double velocidadY = 5;
    protected double limiteSuperior;
    protected double limiteInferior;

    public NaveEnemiga(double coordenadaX, double coordenadaY, ImageView sprite, int hp, int patronMovimiento,
                       double limiteSuperior, double limiteInferior, double velocidadX) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.posicionBaseY = coordenadaY;
        this.sprite = sprite;
        this.hp = hp;
        this.patronMovimiento = patronMovimiento;
        this.limiteSuperior = limiteSuperior;
        this.limiteInferior = limiteInferior;
        this.velocidadX = velocidadX;
        actualizarPosicionSprite();
    }

    // Sobrecarga del constructor anterior por compatibilidad (limites por defecto)
    public NaveEnemiga(double coordenadaX, double coordenadaY, ImageView sprite, int hp, int patronMovimiento) {
        this(coordenadaX, coordenadaY, sprite, hp, patronMovimiento, 0, 600, -3);
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

    public abstract void disparar(Pane fondoDelJuego, List<Disparo> listaDisparos);

    public void mover(double anchoPane) {
        tiempoMovimiento += 0.1;

        switch (patronMovimiento) {
            case 1:
                coordenadaX += velocidadX;
                break;

            case 2:
                coordenadaX += velocidadX;

                coordenadaY += velocidadY;

                // Rebote en los bordes verticales
                if (coordenadaY <= limiteSuperior || coordenadaY >= limiteInferior) {
                    velocidadY = -velocidadY;

                    // Ajuste para no salirse
                    if (coordenadaY < limiteSuperior) coordenadaY = limiteSuperior;
                    if (coordenadaY > limiteInferior) coordenadaY = limiteInferior;
                }
                break;

            case 3:
                coordenadaX += velocidadX;
                coordenadaY = posicionBaseY + Math.sin(tiempoMovimiento * 2 * Math.PI * 0.1) * 90;
                break;
        }

        actualizarPosicionSprite();
    }

    private void actualizarPosicionSprite() {
        sprite.setLayoutX(coordenadaX);
        sprite.setLayoutY(coordenadaY);
    }

    public boolean estaFueraDePantalla() {
        return coordenadaX + sprite.getFitWidth() < 0;
    }
}
