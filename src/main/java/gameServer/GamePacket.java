package gameServer;

public class GamePacket {
    public String dropType ;
    public String dropName;
    public String command ;
    public int amount ;

    public String getDropType() {
        return dropType;
    }

    public String getDropName() {
        return dropName;
    }

    public int getAmount() {
        return amount;
    }

    public String getCommand(){
        return command;
    }
}
