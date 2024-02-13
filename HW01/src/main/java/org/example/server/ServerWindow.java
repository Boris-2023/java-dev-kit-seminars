package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {

    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final String LOG_FILE_NAME = "src/main/java/org/example/server/log.txt";

    private JButton btnStart, btnStop;
    private JTextArea log;


    private boolean isServerWorking;

    public ServerWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(true);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        isServerWorking = false;

        add(getBottomPanel(), BorderLayout.SOUTH);
        add(getTextArea());

        setVisible(true);
    }

    private JPanel getBottomPanel() {

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isServerWorking) {
                    loadLogFromFile(LOG_FILE_NAME);
                    appendToLog("Сервер запущен!");
                    isServerWorking = true;
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isServerWorking) {
                    appendToLog("Сервер НЕ запущен!");
                    saveLogToFile(LOG_FILE_NAME);
                    isServerWorking = false;
                }
            }
        });

        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);

        return bottomPanel;
    }

    private JScrollPane getTextArea() {
        log = new JTextArea();

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);

        return scrollLog;
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void appendToLog(String text) {
        log.append(text + "\n");
    }

    public JTextArea getLog() {
        return log;
    }

    private void saveLogToFile(String fileName) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            String text = log.getText();
            out.write(text);
            out.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    private void loadLogFromFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            br.lines()
                    .map(e -> (e + "\n"))
                    .forEach(log::append);
            log.append("\n");

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
