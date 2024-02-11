package org.example.client;

import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private final String MESSAGE_MARK = ":";
    public final int SERVER_CHECK_FREQUENCY_MILLISEC = 1000;

    private final JTextArea log = new JTextArea();

    private JTextField tfIPAddress, tfPort, tfLogin, tfPassword, tfMessage;
    private JButton btnLogin, btnSend;
    ServerWindow server;
    private boolean isConnected = false;

    public ClientGUI(ServerWindow server) {
        setLocationRelativeTo(server);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        this.server = server;

        add(getTopPanel(), BorderLayout.NORTH);
        add(getBottomPanel(), BorderLayout.SOUTH);
        add(getLogArea());

        checkingServer(SERVER_CHECK_FREQUENCY_MILLISEC);

        setVisible(true);
    }

    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(2, 3));

        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("login_name");
        tfPassword = new JPasswordField("123456");

        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking()) {
                    log.setText("");
                    log.append("Вы успешно подключились!\n");
                    log.append(getMessagesFromLog(server.getLog(), MESSAGE_MARK));

                    server.appendToLog(tfLogin.getText() + " подключился к беседе");

                    isConnected = true;
                } else {
                    log.append("Подключение не удалось...\n");
                    isConnected = false;
                }
            }
        });

        topPanel.add(tfIPAddress);
        topPanel.add(tfPort);
        topPanel.add(new JLabel(""));
        topPanel.add(tfLogin);
        topPanel.add(tfPassword);

        topPanel.add(btnLogin);

        return topPanel;
    }

    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        tfMessage = new JTextField();
        btnSend = new JButton("Send");

        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });

        bottomPanel.add(btnSend, BorderLayout.EAST);
        bottomPanel.add(tfMessage);

        return bottomPanel;
    }

    private JScrollPane getLogArea() {
        log.setEditable(false);

        JScrollPane scrollLog = new JScrollPane(log);

        return scrollLog;
    }

    private boolean isServerWorking() {
        return server.isServerWorking();
    }

    private void sendMessage() {
        if (isConnected && isServerWorking()) {
            if (!tfMessage.getText().isEmpty()) {

                server.appendToLog(tfLogin.getText() + ": " + tfMessage.getText());
                updateClientMessages();

                tfMessage.setText("");
            } else {
                log.append("Введите текст сообщения!\n");
            }
        } else {
            log.append("Вы не подключены к серверу!\n");
        }
    }

    private void updateClientMessages() {

        if (isServerHasNewMessage()) {
            String[] msgsServer = getMessagesFromLog(server.getLog(), MESSAGE_MARK).split("\n");
            String[] msgsClient = getMessagesFromLog(log, MESSAGE_MARK).split("\n");

            int clientMessageNumber, serverMessagesNumber;
            if (msgsClient[0].equals("")) {
                clientMessageNumber = 0;
            } else {
                clientMessageNumber = msgsClient.length;
            }
            if (msgsServer[0].equals("")) {
                serverMessagesNumber = 0;
            } else {
                serverMessagesNumber = msgsServer.length;
            }

            for (int i = clientMessageNumber; i < serverMessagesNumber; i++) {
                log.append(msgsServer[i] + "\n");
            }
        }
    }

    private boolean isServerHasNewMessage() {
        int serverMessagesNumber = getMessagesFromLog(server.getLog(), MESSAGE_MARK).split("\n").length;
        int clientMessagesNumber = getMessagesFromLog(log, MESSAGE_MARK).split("\n").length;
        if (clientMessagesNumber == 1 && getMessagesFromLog(log, MESSAGE_MARK).equals("")) clientMessagesNumber = 0;
        if (serverMessagesNumber == 1 && getMessagesFromLog(server.getLog(), MESSAGE_MARK).equals(""))
            serverMessagesNumber = 0;

        if (serverMessagesNumber > clientMessagesNumber) return true;

        return false;
    }

    public String getMessagesFromLog(JTextArea fullLog, String messageMark) {
        String messages = "";
        String[] msgArr = fullLog.getText().split("\n");

        for (int i = 0; i < msgArr.length; i++) {
            if (msgArr[i].contains(messageMark)) messages += (msgArr[i] + "\n");
        }

        return messages;
    }

    private void checkingServer(int checkFrequencyMilliSec) {

        Thread checkServer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (isConnected) {
                            if (!isServerWorking()) {
                                isConnected = false;
                                log.append("Вы ОТКЛЮЧЕНЫ от сервера!\n");
                            } else if (isServerHasNewMessage()) {
                                updateClientMessages();
                            }
                        }
                        Thread.sleep(checkFrequencyMilliSec); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        checkServer.start();
    }

}
