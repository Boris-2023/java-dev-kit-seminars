package client;

import server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private static int clientNum = 0;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    Client client;

    public ClientGUI(ServerWindow server) {
        setting(server);
        createPanel();
        setVisible(true);

        clientNum++;
    }

    private void setting(ServerWindow server) {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");

        setLocation(server.getX() + (1 - 2 * (clientNum % 2)) * 500, server.getY());

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        client = new Client(server.getServer(), this);
    }

    @Override
    public void showMessage(String msg) {
        log.append(msg + "\n");
    }

    @Override
    public void disconnectFromServer() {
        showHeaderPanel(true);
        client.disconnectFromServer();
    }

    public void showHeaderPanel(boolean visible) {
        headerPanel.setVisible(visible);
    }

    private void message() {
        client.message(tfMessage.getText());
        tfMessage.setText("");
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connectToServer(tfLogin.getText());
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }
        super.processWindowEvent(e);
    }

    public void setLog(String log) {
        this.log.setText(log);
    }

    public String getClientName(){
        return tfLogin.getText();
    }
}
