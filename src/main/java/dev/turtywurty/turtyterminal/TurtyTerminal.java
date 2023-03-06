package dev.turtywurty.turtyterminal;

import dev.turtywurty.turtyterminal.controller.TerminalController;
import dev.turtywurty.turtyterminal.view.TerminalView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TurtyTerminal extends Application {
    @Override
    public void start(Stage primaryStage) {
        var view = new TerminalView();
        var controller = new TerminalController();

        Scene scene = new Scene(view.getPrimaryNode(), 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("TurtyTerminal");
        primaryStage.centerOnScreen();
        primaryStage.show();

        controller.control(view);
    }
}
