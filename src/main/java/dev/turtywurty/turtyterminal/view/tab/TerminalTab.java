package dev.turtywurty.turtyterminal.view.tab;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class TerminalTab {
    private final Tab tab;
    private final TerminalType type;

    public TerminalTab(TerminalType type) {
        this.type = type;

        this.tab = new Tab();
        this.tab.setText(this.type.getName());
        this.tab.setClosable(true);
        this.tab.setContent(new TerminalTabContent());
    }

    public Tab getTab() {
        return this.tab;
    }

    public TerminalType getType() {
        return this.type;
    }

    public static class TerminalTabContent extends BorderPane {
        private final TextArea textArea;

        public TerminalTabContent() {
            this.textArea = new TextArea();
            this.textArea.setEditable(true);
            this.textArea.setWrapText(true);
            setCenter(this.textArea);

            this.textArea.appendText("echo \"Hello World!\"");
        }

        public TextArea getTextArea() {
            return this.textArea;
        }
    }

    public enum TerminalType {
        CMD("Command Prompt"), POWERSHELL("PowerShell"), WSL("WSL");

        private final String name;

        TerminalType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
