package controller.method;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.JsonController;
import controller.ObstacleAdapter;
import model.GroundType;
import model.Manage;
import model.Map;
import model.Obstacle.SavedObstacles;
import model.Obstacle.Stone;
import model.Obstacle.Tree;
import model.Obstacle.WaterSources;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MapMethod {

    public static void addNewMapToServer(Map map) throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("ADD_NEW_MAP_TO_SERVER");
        int index =  Map.getSavedMaps().indexOf(map);
        ArrayList<SavedObstacles> savedObstacles = Map.allJsonMaps.get(index);
        String data = convertArrayListsOfSavedObstacleIntoJsonForm(savedObstacles);
        Manage.masterServerDataOutputStream.writeUTF(data);
    }

    public static void getMapsFromServer() throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("GET_SAVED_MAPS");
        String number = Manage.masterServerDataInputStream.readUTF();
        int num = Integer.parseInt(number);
        for(int i = 0 ; i < num ; i ++){
            String data = Manage.masterServerDataInputStream.readUTF();
            ArrayList<SavedObstacles> savedObstacles = convertJsonToArrayList(data);
            makeMapFromSaveObstacle(savedObstacles);
            if(Map.getMapWithName(savedObstacles.get(0).getNameOfMap()) != null) {
                //TODO : here add a graphic for the maps that you have
            }else {
                //TODO : and here add a graph that you dont have
            }
            //TODO : in this situation save every savedObstacle into a button(or everything you need)

        }
    }
    private static String convertArrayListsOfSavedObstacleIntoJsonForm(ArrayList<SavedObstacles> savedObstacles){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonAsString = gson.toJson(savedObstacles);
        return jsonAsString ;
    }
    private static ArrayList<SavedObstacles> convertJsonToArrayList(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (data.equals("")) return null;
        Type type = new TypeToken<ArrayList<SavedObstacles>>(){}.getType();
        ArrayList<SavedObstacles> a2 = gson.fromJson(data,type);
        return a2;
    }
    private static ArrayList<ArrayList<SavedObstacles>> getAllMaps(String data) throws IOException {
        ArrayList<ArrayList<SavedObstacles>> allSavedObstacles = new ArrayList<>();
        String text = data ;
        if(text.equals("")) return null;
        String[] arrays = text.split("\\[");
        int counter = 0  ;
        for(String array : arrays){
            counter  ++ ;
            if(counter  == 1) continue;
            String map = "[" + array ;
            ArrayList<SavedObstacles> mapSaved = getObstaclesArrayList(map);
            allSavedObstacles.add(mapSaved);
        }
        return allSavedObstacles ;
    }
    private void selectAnMapFromServer(int index) throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("DOWNLOAD_MAP");
        Manage.masterServerDataOutputStream.writeUTF(Integer.toString(index));
        String data = Manage.masterServerDataInputStream.readUTF();
        ArrayList<SavedObstacles> savedObstacles = getObstaclesArrayList(data);

    }
    private static ArrayList<SavedObstacles> getObstaclesArrayList(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (data.equals("")) return null;
        Type type = new TypeToken<ArrayList<SavedObstacles>>(){}.getType();
        ArrayList<SavedObstacles> a2 = gson.fromJson(data,type);
        return a2;
    }
    //TODO : for Adding a map into maps list please call function
    public static boolean makeMapFromSaveObstacle(ArrayList<SavedObstacles> arrayList){
        Map map = new Map();
        map.CreateMap(Map.mapSize);
        for(SavedObstacles saveObject  : arrayList){
            if(Map.getMapWithName(saveObject.nameOfMap) != null) return false; //TODO : for here please show an alert with this titre : repeated name!
            map.numberOfPlayers =  saveObject.numberOfPlayers ;
            map.name = saveObject.nameOfMap ;
            boolean isGroundType = false ;
            int x = saveObject.x ;
            int y = saveObject.y ;

            switch (saveObject.name) {
                case "TREE":
                    Tree tree = new Tree();
                    map.getObstacleMap()[x][y].add(tree);
                    break;
                case "SEA":
                    WaterSources waterSources = new WaterSources();
                    map.getObstacleMap()[x][y].add(waterSources);
                    break;
                case "STONE":
                    Stone stone = new Stone();
                    map.getObstacleMap()[x][y].add(stone);
                    break;
                case "GROUND_TYPE" :
                    isGroundType = true ;
                    switch (saveObject.type){
                        case "dash":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.DASH);
                            break;
                        case "grass":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.GRASS);
                            break;
                        case "gravel":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.GROUND_WITH_STONE);
                            break;
                        case "iron":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.DEFAULT);
                            break;
                        case "plain":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.PLAIN);
                            break;
                    }
                    break;
            }
            if(!isGroundType) {
                map.notBuildable[x][y] = saveObject.notBuildable;
                map.notPassable[x][y] = saveObject.notPassable;
            }
        }
        Map.getSavedMaps().add(map);
        return true ;
    }
}
