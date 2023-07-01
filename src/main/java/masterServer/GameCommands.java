package masterServer;

import java.util.ArrayList;

public class GameCommands {
    public String userName;
    GameCommands(String userName){
        this.userName = userName;
    }
    public ArrayList<String> commandsToDo = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getCommandsToDo() {
        return commandsToDo;
    }

    public void setCommandsToDo(ArrayList<String> commandsToDo) {
        this.commandsToDo = commandsToDo;
    }
}
