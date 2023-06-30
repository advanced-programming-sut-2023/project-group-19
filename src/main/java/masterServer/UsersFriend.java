package masterServer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersFriend {
    public String userName;
    UsersFriend(String userName){
        this.userName = userName;
    }
    public ArrayList<String> friendRequest = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(ArrayList<String> friendRequest) {
        this.friendRequest = friendRequest;
    }
}
