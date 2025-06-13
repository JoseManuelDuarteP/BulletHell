package org.example.bullethell.Controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.example.bullethell.Entities.*;


import java.util.*;

public class JuegoController {
    @FXML
    private ImageView nave;

    @FXML
    private Pane fondoDelJuego;

    private Label labelVida;
    private Label labelPuntuacion;

    private int hpJugador = 10;
    private int puntuacion = 0;

    private Set<KeyCode> teclasPresionadas = new HashSet<>();

    private List<NaveEnemiga> enemigos = new ArrayList<>();
    private Timeline generarEnemigo;

    private List<Disparo> disparosJugador = new ArrayList<>();
    private List<Disparo> disparosEnemigos = new ArrayList<>();

    @FXML
    public void iniciarJuego() {
        ponerFondo(fondoDelJuego, "/sprites/fondo.jpg");

        labelVida = new Label("Vida: " + hpJugador);
        labelVida.setTextFill(Color.WHITE);
        labelVida.setFont(Font.font(18));
        labelVida.setStyle("-fx-background-color: transparent;");
        labelVida.setLayoutX(10);
        labelVida.setLayoutY(10);

        labelPuntuacion = new Label("Puntuación: " + puntuacion);
        labelPuntuacion.setTextFill(Color.WHITE);
        labelPuntuacion.setFont(Font.font(18));
        labelPuntuacion.setStyle("-fx-background-color: transparent;");
        // Lo posicionamos a la derecha mediante binding para que siempre esté alineado a la derecha:
        labelPuntuacion.layoutXProperty().bind(fondoDelJuego.widthProperty().subtract(150));
        labelPuntuacion.setLayoutY(10);

        fondoDelJuego.getChildren().addAll(labelVida, labelPuntuacion);

        fondoDelJuego.setFocusTraversable(true);

        Platform.runLater(() -> fondoDelJuego.requestFocus());

        fondoDelJuego.setOnKeyPressed(e -> {
            teclasPresionadas.add(e.getCode());

            if (e.getCode() == KeyCode.SPACE) {
                dispararJugador();
            }
        });

        fondoDelJuego.setOnKeyReleased(e -> {
            teclasPresionadas.remove(e.getCode());
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moverDisparos();
                moverJugadorSegunTeclas();
                moverEnemigos();
                detectarColisiones();
            }
        };
        timer.start();

        generarEnemigo = new Timeline(new KeyFrame(Duration.seconds(3),
                e -> generarEnemigo()));
        generarEnemigo.setCycleCount(Timeline.INDEFINITE);
        generarEnemigo.play();

        Timeline disparoEnemigos = new Timeline(new KeyFrame(Duration.seconds(2.5), e -> {
            for (NaveEnemiga enemigo : enemigos) {
                dispararEnemigo(enemigo);
            }
        }));
        disparoEnemigos.setCycleCount(Timeline.INDEFINITE);
        disparoEnemigos.play();
    }

    private void actualizarVida(int nuevaVida) {
        hpJugador = nuevaVida;
        labelVida.setText("Vida: " + hpJugador);
    }

    private void sumarPuntuacion(int puntos) {
        puntuacion += puntos;
        labelPuntuacion.setText("Puntuación: " + puntuacion);
    }


    private void moverJugadorSegunTeclas() {
        double dx = 0;
        double dy = 0;
        double velocidad = 5;

        if (teclasPresionadas.contains(KeyCode.W)) dy -= velocidad;
        if (teclasPresionadas.contains(KeyCode.S)) dy += velocidad;
        if (teclasPresionadas.contains(KeyCode.A)) dx -= velocidad;
        if (teclasPresionadas.contains(KeyCode.D)) dx += velocidad;

        moverNave(dx, dy);
    }

    public static void ponerFondo(Pane pane, String ruta) {
        Image imagen = new Image(JuegoController.class.getResource(ruta).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                imagen,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100, 100, true, true, true, true
                )
        );
        pane.setBackground(new Background(backgroundImage));
    }

    private void moverNave(double dx, double dy) {
        double nuevaX = nave.getLayoutX() + dx;
        double nuevaY = nave.getLayoutY() + dy;

        if (nuevaX >= 0 && nuevaX + nave.getFitWidth() <= fondoDelJuego.getWidth()) {
            nave.setLayoutX(nuevaX);
        }
        if (nuevaY >= 0 && nuevaY + nave.getFitHeight() <= fondoDelJuego.getHeight()) {
            nave.setLayoutY(nuevaY);
        }
    }

    private void moverEnemigos() {
        Iterator<NaveEnemiga> iterator = enemigos.iterator();

        while (iterator.hasNext()) {
            NaveEnemiga enemigo = iterator.next();
            enemigo.mover(fondoDelJuego.getWidth());

            if (enemigo.estaFueraDePantalla()) {
                enemigo.getSprite().setVisible(false);
                fondoDelJuego.getChildren().remove(enemigo.getSprite());
                iterator.remove();
            }
        }
    }

    private void generarEnemigo() {
        double x = fondoDelJuego.getWidth() - 60; // posición inicial horizontal

        double y = Math.random() * (fondoDelJuego.getHeight() - 120); // acomoda para el más grande (Acorazado)

        double rand = Math.random();

        NaveEnemiga enemigo;

        if (rand < 0.6) {
            // 60% Interceptor con patrón aleatorio 1-3
            int patron = 1 + (int)(Math.random() * 3);
            enemigo = new Interceptor(x, y, patron);
        } else if (rand < 0.9) {
            // 30% Destructor con patrón 1 o 2
            int patron = 1 + (int)(Math.random() * 2);
            enemigo = new Destructor(x, y, patron);
        } else {
            // 10% Acorazado, solo patrón 1
            enemigo = new Acorazado(x, y);
        }

        fondoDelJuego.getChildren().add(enemigo.getSprite());
        enemigos.add(enemigo);
    }

    private void dispararJugador() {
        Image imagenDisparo = new Image(getClass().getResourceAsStream("/sprites/disparo_jugador.png"));
        ImageView sprite = new ImageView(imagenDisparo);
        sprite.setFitWidth(40);
        sprite.setFitHeight(15);

        double x = nave.getLayoutX() + nave.getFitWidth() - 30;
        double y = nave.getLayoutY() + nave.getFitHeight() / 2 - sprite.getFitHeight() / 2;

        sprite.setLayoutX(x);
        sprite.setLayoutY(y);

        DisparoNormal disparo = new DisparoNormal(x, y, sprite, 1);
        fondoDelJuego.getChildren().add(sprite);
        disparosJugador.add(disparo);
    }

    private void dispararEnemigo(NaveEnemiga enemigo) {
        enemigo.disparar(fondoDelJuego, disparosEnemigos);
    }

    private void moverDisparos() {
        double velocidadJugador = 8;

        // Disparos del jugador (igual)
        Iterator<Disparo> itJugador = disparosJugador.iterator();
        while (itJugador.hasNext()) {
            Disparo d = itJugador.next();
            d.setCoordenadasX(d.getCoordenadasX() + velocidadJugador);

            if (d.getCoordenadasX() > fondoDelJuego.getWidth()) {
                fondoDelJuego.getChildren().remove(d.getSprite());
                itJugador.remove();
            } else {
                d.getSprite().setLayoutX(d.getCoordenadasX());
            }
        }

        // Disparos enemigos: deben moverse según tipo (Disparo o DisparoDiagonal)
        Iterator<Disparo> itEnemigos = disparosEnemigos.iterator();
        while (itEnemigos.hasNext()) {
            Disparo d = itEnemigos.next();

            if (d instanceof DisparoDiagonal) {
                ((DisparoDiagonal) d).mover();
            } else {
                // Disparo normal hacia la izquierda
                d.setCoordenadasX(d.getCoordenadasX() - 6);
            }

            if (d.getCoordenadasX() < 0 || d.getCoordenadasY() < 0 || d.getCoordenadasY() > fondoDelJuego.getHeight()) {
                fondoDelJuego.getChildren().remove(d.getSprite());
                itEnemigos.remove();
            } else {
                d.getSprite().setLayoutX(d.getCoordenadasX());
                d.getSprite().setLayoutY(d.getCoordenadasY());
            }
        }
    }

    private boolean hayColision(ImageView a, ImageView b) {
        return a.getBoundsInParent().intersects(b.getBoundsInParent());
    }

    private void detectarColisiones() {
        // Colisiones disparos jugador contra enemigos
        Iterator<Disparo> iterDisparosJugador = disparosJugador.iterator();
        while (iterDisparosJugador.hasNext()) {
            Disparo disparo = iterDisparosJugador.next();

            Iterator<NaveEnemiga> iterEnemigos = enemigos.iterator();
            while (iterEnemigos.hasNext()) {
                NaveEnemiga enemigo = iterEnemigos.next();

                if (hayColision(disparo.getSprite(), enemigo.getSprite())) {
                    // Daño
                    enemigo.setHp(enemigo.getHp() - disparo.getDanyo());

                    // Eliminar disparo de la pantalla y lista
                    fondoDelJuego.getChildren().remove(disparo.getSprite());
                    iterDisparosJugador.remove();

                    // Si enemigo muerto, eliminar de pantalla y lista
                    if (enemigo.getHp() <= 0) {
                        fondoDelJuego.getChildren().remove(enemigo.getSprite());
                        iterEnemigos.remove();

                        switch (enemigo) {
                            case Interceptor interceptor -> sumarPuntuacion(10);
                            case Destructor destructor -> sumarPuntuacion(50);
                            case Acorazado acorazado -> sumarPuntuacion(120);
                            default -> {
                            }
                        }
                    }

                    break; // Un disparo solo afecta a un enemigo
                }
            }
        }

        // Colisiones disparos enemigos contra jugador
        Iterator<Disparo> iterDisparosEnemigos = disparosEnemigos.iterator();
        while (iterDisparosEnemigos.hasNext()) {
            Disparo disparo = iterDisparosEnemigos.next();

            if (hayColision(disparo.getSprite(), nave)) {
                // Aquí restar vida jugador (debes crear variable hpJugador)
                hpJugador -= disparo.getDanyo();

                // Eliminar disparo
                fondoDelJuego.getChildren().remove(disparo.getSprite());
                iterDisparosEnemigos.remove();

                // Si jugador muerto, actuar (por ejemplo mostrar mensaje o reiniciar)
                actualizarVida(hpJugador);
                if (hpJugador <= 0) {
                    System.out.println("Jugador muerto");
                    // Acción aquí...
                }
            }
        }
    }

}
