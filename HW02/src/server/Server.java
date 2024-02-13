package server;

import client.ClientView;
import server.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Server {

    String log;

    List<ClientView> clientViewList;
    ServerView serverView;
    Repository repository;
    boolean work;

    public Server(ServerView serverView, Repository repository) {
        clientViewList = new ArrayList<>();
        this.repository = repository;
        this.serverView = serverView;
    }

    public void start() {
        if (work) {
            serverView.addMessageToViewLog("Сервер уже был запущен");
        } else {
            work = true;
            serverView.setViewLog("");
            serverView.addMessageToViewLog("Сервер запущен!");
            serverView.addMessageToViewLog(getHistory());
        }
    }

    public void stop() {
        if (!work) {
            serverView.addMessageToViewLog("Сервер уже был остановлен");
        } else {
            work = false;
            disconnectAllUsers();
            serverView.addMessageToViewLog("Сервер остановлен!");
        }
    }

    public boolean connectUser(ClientView clientView) {
        if (!work) {
            return false;
        }
        clientViewList.add(clientView);
        serverView.addMessageToViewLog(clientView.getClientName() + " подключился к беседе!");
        return true;
    }

    public void disconnectUser(ClientView clientView) {
        clientViewList.remove(clientView);
        if (clientView != null) {
            clientView.disconnectFromServer();
        }
    }

    public void disconnectAllUsers() {
        while (!clientViewList.isEmpty()) {
            ClientView clientView = clientViewList.get(clientViewList.size() - 1);
            serverView.addMessageToViewLog(clientView.getClientName() + " отключен от сервера!");
            disconnectUser(clientView);
        }
    }

    public String getHistory() {
        return repository.load();
    }

    public void saveHistory(String log) {
        repository.save(log);
    }

    public void message(String text) {
        if (!work) {
            return;
        }
        appendLog(text);
        serverView.addMessageToViewLog(text);
        answerAll(text);
        saveHistory(text);
    }

    private void answerAll(String text) {
        for (ClientView clientView : clientViewList) {
            clientView.showMessage(text);
        }
    }

    private void appendLog(String text) {
        log += (text + "\n");
    }

}