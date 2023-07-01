package controller;

import controller.method.ChatMethods;
import model.Manage;
import model.User;

import java.io.IOException;

public class SendInformationToMasterServer {
    public static void sendCurrentUser(User user) throws IOException {
        String data = User.convertUserToJson(user);
        Manage.masterServerDataOutputStream.writeUTF("LOGIN_USER");
        Manage.masterServerDataOutputStream.writeUTF(data);
        System.out.println(Manage.masterServerDataInputStream.readUTF());
        ChatMethods.getAllChatsOfUsers(user);
    }
    public static void sendMapToServer(String map) throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("ADD_MAP");
        Manage.masterServerDataOutputStream.writeUTF(map);
    }
}
