package org.zed.snakez;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class SnakeZ {
    static int termWidth;
    static int termHeight;
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        termWidth = terminal.getWidth();
        termHeight = terminal.getHeight();
        System.out.println(termWidth+" "+termHeight);
    }
}
