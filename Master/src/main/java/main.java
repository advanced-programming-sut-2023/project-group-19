import com.google.gson.Gson;
import masterServer.MasterServer;

import java.util.HashMap;

public class main {
    public static void main(String[] args){
//        new MasterServer(8080);
//        HashMap<String,Integer> jj = new HashMap<>();
//        System.out.println(jj.get("g"));
        Gson gson = new Gson();
        System.out.println(gson.toJson(null) + "d");

    }
}
