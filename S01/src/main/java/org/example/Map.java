package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private final int EMPTY_DOT = 0;
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int DOT_PADDING = 10; // отступы

    public static final int STATE_GAME = 0; // игра идет
    public static final int STATE_WIN_HUMAN = 1;
    public static final int STATE_WIN_AI = 2;
    public static final int STATE_DRAW = 3;
    public static final String MSG_WIN_HUMAN = "Победил игрок!";
    public static final String MSG_WIN_AI = "Победил компьютер!";
    public static final String MSG_DRAW = "Ничья!";

    private int gameStateType; // to store game states (0-1-2-3), types are above: STATE_...
    private int panelWidth, panelHeight, cellWidth, cellHeight; // ширина, высота панели и соотв. кол-во ячеек по высоте и ширине
    private int fieldSizeX = 3;
    private int fieldSizeY = 3;
    private int mode, winLen;
    private int[][] field; //initially filled in with 0 - EMPTY_DOT
    private boolean gameWork; // flag meaning it's time to play


    public Map() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameWork) update(e);
            }
        });
    }

    /**
     * Tic-Tac-Toe logic
     */
    public void initMap() {
        field = new int[fieldSizeX][fieldSizeY];
    }

    public void startNewGame(int mode, int fSzX, int fSzY, int wLen) {

        this.mode = mode;
        this.fieldSizeX = fSzX;
        this.fieldSizeY = fSzY;
        this.winLen = wLen;

        initMap();

        gameWork = true; // now MouseListener is ON - constructor
        gameStateType = STATE_GAME;

        repaint(); // redrawing an empty field
    }

    // ход игрока + ход компбютера
    private void update(MouseEvent e) {

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.println("x = " + cellX + ", y = " + cellY);

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;

        field[cellX][cellY] = HUMAN_DOT;

        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();

        repaint();

        if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;
    }


    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y) || !isValidCell(x, y));
        field[x][y] = AI_DOT;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameStateType = gameOverType;
            repaint();
            return true;
        } else if (isMapFull()) {
            this.gameStateType = STATE_DRAW;
            repaint();
            return true;
        }
        return false;
    }


    private boolean checkWin(int dot) {

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            }
        }

        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot) {
        int farX = x + (len - 1) * vx;
        int farY = y + (len - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[x + i * vx][y + i * vy] != dot) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameWork) render(g);
    }

    private void render(Graphics g) {

        // better in separate method - the same every time!!!
        panelHeight = getHeight();
        panelWidth = getWidth();
        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;
        // makes the grid
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;// x-pos of vertical lines
            g.drawLine(x, 0, x, panelHeight);
        }
        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight; // y-pos of horizontal lines
            g.drawLine(0, y, panelWidth, y);
        }

        // what to do in a field?
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == EMPTY_DOT) continue; // переход к следующей ячейке
                if (field[x][y] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else if (field[x][y] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[x][y] +
                            " in cell: x=" + x + ", y=" + y);
                }
            }
        }
        if (gameStateType != STATE_GAME) showMessageGameOver(g);

    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 38));

        switch (gameStateType) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOverState: " + gameStateType);
        }
        gameWork = false;
    }

}

