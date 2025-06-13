package org.example.bullethell.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.bullethell.BHApplication;

import java.io.IOException;

public class MenuController {

    @FXML
    protected void iniciar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BHApplication.class.getResource("juego-view.fxml"));
        Parent root = fxmlLoader.load();

        JuegoController juego = fxmlLoader.getController();
        juego.setStage((Stage) ((Node)event.getSource()).getScene().getWindow());
        juego.iniciarJuego();

        cambiarEscena(event,root);
    }

    @FXML
    protected void salir() {
        System.exit(0);
    }

    protected void cambiarEscena(ActionEvent event, Parent nuevaEscena) throws IOException {
        Scene escenaJuego = new Scene(nuevaEscena);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(escenaJuego);
        stage.centerOnScreen();
        stage.setFullScreen(true);
        stage.show();
    }
}