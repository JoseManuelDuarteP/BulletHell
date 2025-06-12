module org.example.bullethell {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens org.example.bullethell to javafx.fxml;
    exports org.example.bullethell.Controllers;
    opens org.example.bullethell.Controllers to javafx.fxml;
    exports org.example.bullethell;
}