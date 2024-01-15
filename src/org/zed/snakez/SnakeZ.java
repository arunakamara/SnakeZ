package org.zed.snakez;

import org.jline.jansi.Ansi;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.jansi.Ansi.*;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeZ {
    static int termWidth;
    static int termHeight;

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.enterRawMode();
        termWidth = terminal.getWidth();
        termHeight = terminal.getHeight();
        Snake snake = new Snake(termWidth, termHeight);
        terminal.puts(InfoCmp.Capability.cursor_invisible);
        for (int i = 1; i < termWidth; i++) {
            for (int j = 0; j < termHeight; j++) {
                terminal.puts(InfoCmp.Capability.cursor_address, i, j);
                System.out.print(" ");
            }
        }
        while (true) {
            for (Bit bit : snake.bits) {
                int x = new Random().nextInt(termHeight) + 1;
                int y = new Random().nextInt(termWidth) + 1;
                terminal.puts(InfoCmp.Capability.cursor_address, x, y);
                terminal.flush();
                System.out.print("0");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                terminal.puts(InfoCmp.Capability.cursor_address, x, y);
                terminal.flush();
                System.out.print(" ");
            }
        }
    }
}

class Input extends Thread {
    public void run() {
        NonBlockingReader keyReader;
        try {
            keyReader = TerminalBuilder.builder().build().reader();
            System.out.println(keyReader.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Bit {
    int currX;
    int currY;
    int prevX;
    int prevY;

    Bit(int x, int y) {
        this.currX = x;
        this.currY = y;
    }
}

class Snake {
    ArrayList<Bit> bits = new ArrayList<>();

    Snake(int termHeight, int termWidth) {
        int x = new Random().nextInt(termHeight) + 1;
        int y = new Random().nextInt(termWidth) + 1;
        Bit bit = new Bit(x, y);
        bits.add(bit);
    }
}
