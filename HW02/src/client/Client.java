package client;

import server.Server;

public class Client {
    private boolean connected; // if connected
    private String name;
    private Server server; //as no sockets involved
    private ClientView clientView; // by interface

    public Client(Server server, ClientView clientView) {
        this.server = server;
        this.clientView = clientView;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(clientView)) {
            clientView.setLog("");
            showOnWindow("Вы успешно подключились!\n");
            connected = true;

            clientView.showHeaderPanel(false);

            String log = server.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось!");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу!");
        }
    }

    public String getName(){
        return name;
    }

    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(clientView);
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }

}
