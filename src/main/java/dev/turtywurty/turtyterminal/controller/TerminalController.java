package dev.turtywurty.turtyterminal.controller;

import dev.turtywurty.turtyterminal.view.TerminalView;
import dev.turtywurty.turtyterminal.view.tab.TerminalTab;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class TerminalController {
    private TerminalView view;
    private final TerminalTabController terminalTabController;

    public TerminalController() {
        this.terminalTabController = new TerminalTabController();
    }

    public void control(TerminalView view) {
        if(this.view != null || view == null) return;

        this.view = view;
        this.terminalTabController.control(this.view.getFileMenu(), this.view.getTerminalTabPane());

        Node content = this.view.getTerminalTabPane().getSelectionModel().getSelectedItem().getContent();
        if(content instanceof TerminalTab.TerminalTabContent terminalTabContent) {
            terminalTabContent.getTextArea().setOnKeyReleased(event -> {
                if(event.getCode() == KeyCode.ENTER) {
                    String[] lines = terminalTabContent.getTextArea().getText().split("\n");
                    runCommand(lines[lines.length - 1].trim(), terminalTabContent.getTextArea());
                }
            });
        }
    }

    private void runCommand(String command, TextArea textArea) {
        new Thread(() -> {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(System.getProperty("user.home")));
            processBuilder.command("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);

            try {
                Process process = processBuilder.start();
                try(var in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        String finalLine = line;
                        Platform.runLater(() -> textArea.appendText(finalLine + System.lineSeparator()));
                    }

                    process.waitFor();
                }
            } catch (Exception exception) {
                Platform.runLater(() -> {
                    textArea.appendText(exception.getMessage() + System.lineSeparator());
                    for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                        textArea.appendText(stackTraceElement.toString() + System.lineSeparator());
                    }
                });
            }
        }).start();
    }
}
