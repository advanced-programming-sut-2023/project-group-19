package masterServer;

import com.google.gson.Gson;

public class MasterRequest {
    public String type ;
    public int playerNumber ;
    public int portOfJoinToGame;
    public String usernameOfSender ;

    public MasterRequest(String type,int playerNumber,int portOfJoinToGame,String usernameOfSender){
        this.usernameOfSender = usernameOfSender ;
        this.portOfJoinToGame =  portOfJoinToGame ;
        this.playerNumber  = playerNumber ;
        this.type = type  ;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }
}

// server 1 : master --> unique port --> unique socket --> input output stream
// server 2 : game --> unique port --> unique socket --> input output stream
// server 3 : chat

// master socket
// game socket
// chat socket