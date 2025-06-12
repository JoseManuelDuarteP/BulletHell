package org.example.bullethell.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class JuegoController {
    @FXML
    private ImageView nave;

    @FXML
    private Pane fondoDelJuego;

    @FXML
    public void iniciarJuego() {
        ponerFondo(fondoDelJuego, "/sprites/fondo.jpg");

        fondoDelJuego.setFocusTraversable(true);

        Platform.runLater(() -> fondoDelJuego.requestFocus());

        fondoDelJuego.setOnKeyPressed(e -> {
            System.out.println("Tecla pulsada: " + e.getCode());  // Para debug

            switch (e.getCode()) {
                case W -> moverNave(0, -10);
                case A -> moverNave(-10, 0);
                case S -> moverNave(0, 10);
                case D -> moverNave(10, 0);
            }
        });
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
}
