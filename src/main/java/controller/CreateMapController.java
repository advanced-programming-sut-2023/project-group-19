package controller;

import controller.Building.BuildingController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GroundType;
import model.Map;
import model.Obstacle.*;
import model.*;
import model.Building.*;
import view.Model.NewButton;
import view.TileManager;

public class CreateMapController {
    public Map map;
    public static int numberOfEmpires;
    public static boolean mapIsBuilt = false;
    private static int sizeOfMap = Map.mapSize;

    public CreateMapController(Map map) {
        this.map =  map;
    }

    public static int getSizeOfMap() {
        return sizeOfMap;
    }

    public void recovery() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                numberOfEmpires = 0 ;
                map.getBuildingMap()[i][j].clear();
                map.getTroopMap()[i][j].clear();
                map.getObstacleMap()[i][j].clear();
                map.getGroundType()[i][j].clear();
//                map.notBuildable[i][j] = false ;
                map.notPassable[i][j] = false ;
                User.loginUsers.clear();
                User.loginUsers.add(User.getCurrentUser());
                mapIsBuilt = false;
            }
        }
    }

    public String CreateMap(int size) {
        mapIsBuilt = true;
        if (size != 200 && size != 400) return "You must choose size 200 or 400";
        sizeOfMap = size;
//        ShowMapController.size = size ;
        map.CreateMap(size);
        return "Map is builded successfully!";
    }
    public String settextureOneByOne(int x,int y,String type){
        if (!mapIsBuilt) return "At first.You must build a map!";
        if(x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return "Yure location is out of bounds";
//        if(Map.notBuildable[x][y]) return "Is occupied";
        GroundType groundType = GroundType.getEnumGroundType(type);
        if(groundType == null){
            WaterSources waterSources = WaterSources.getWaterSourcesByName(type);
            if(waterSources == null) return "Choose type correctly";
            map.getGroundType()[x][y].clear();
            map.getGroundType()[x][y].add(GroundType.DEFAULT);
            map.getObstacleMap()[x][y].add(waterSources);
//            Map.notBuildable[x][y]  = true ;
            if(!type.equals("shallowWater")) map.notPassable[x][y] = true ;
            return "Change is done successfully!";
        }
        map.getGroundType()[x][y].clear();
        if(groundType.equals(GroundType.STONE_ROCK)){
            map.notPassable[x][y] = true ;
//            Map.notBuildable[x][y] = true ;
        }
        map.getGroundType()[x][y].add(groundType);
        return "Change is done successfully!";
    }
    public String settextureGroup(int x1 , int x2 , int y1 , int y2 , String type){
//        if (!mapIsBuilt) return "You first must build a map!";
        if(x1 > x2 || y1 > y2) return "Please make sure that x and y are correctly assigned!";
        if(x1 < 0 || x2 >= sizeOfMap || y1 < 0 || y2 >= sizeOfMap) return "Yure location is out of bounds";
        GroundType groundType = GroundType.getEnumGroundType(type);
        if(groundType == null) return "Choose type correctly";
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
//                if(Map.notBuildable[i][j]) return "x:  " + i + " and y: " + j + " is Is occupied";
                map.getGroundType()[i][j].clear();
                map.getGroundType()[i][j].add(groundType);
                SavedObstacles savedObstacles = new SavedObstacles();
                consGorSavedObject(savedObstacles,"GROUND_TYPE",type,false,false,i,j);
                map.savingObstacle.add(savedObstacles);
                if(groundType.equals(GroundType.STONE_ROCK)){
                    map.notPassable[i][j] = true ;
//                    Map.notBuildable[i][j] = true ;
                }
            }
        }
        return "Change is done successfully!";
    }

    public String clear(int x, int y) {
        if (!mapIsBuilt) return "You first must build a map!";
        if (x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return "Yure location is out of bounds";
        map.getTroopMap()[x][y].clear();
        map.getBuildingMap()[x][y].clear();
        map.getGroundType()[x][y].clear();
        map.getGroundType()[x][y].add(GroundType.DEFAULT);
        map.getObstacleMap()[x][y].clear();
        map.notPassable[x][y] = false;
//        Map.notBuildable[x][y] = false;
        return "Clear successfully";
    }

    public String dropRock(int x, int y, String type) {
        if(type.equals("r")) type = "w";
        if (!type.equals("n") && !type.equals("e") && !type.equals("w") && !type.equals("s"))
            return "Choose direction correctly!";
//        if (!mapIsBuilt) return "You first must build a map!";
        if(x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return "Yure location is out of bounds";
        if(map.notBuildable[x][y]) return "Is occupied";
        Stone stone = new Stone();
        stone.stone(type);
        map.getGroundType()[x][y].clear();
        map.getGroundType()[x][y].add(GroundType.DEFAULT);
        map.getObstacleMap()[x][y].add(stone);

//        s =  new ObstacleName("stone","w",x,y);
        SavedObstacles savedObstacles = new SavedObstacles();
        consGorSavedObject(savedObstacles,"STONE","",true,true,x,y);
        map.savingObstacle.add(savedObstacles);
//        map.savingObstacle.add(savedObstacles);
        return "Successfully";
    }
    public void dropSeveralStone(int x1 , int x2, int y1 , int y2){
        for(int i =  x1 ; i  <=  x2 ; i ++){
            for(int j = y1 ; j <= y2  ; j ++){
                dropRock(i,j,"w");
            }
        }
    }
    public void dropSeveralTrees(int x1 ,  int x2, int y1 , int y2){
        for(int i =  x1 ; i  <=  x2 ; i ++){
            for(int j = y1 ; j <= y2  ; j ++){
                dropTree(i,j,"desertTree");
            }
        }
    }
    public void dropSea(int x , int y){
        if(x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return ;
        if(map.notBuildable[x][y]) return ;

        WaterSources waterSources = new WaterSources();
        map.getGroundType()[x][y].clear();
        map.getGroundType()[x][y].add(GroundType.DEFAULT);
        map.getObstacleMap()[x][y].add(waterSources);
        map.notBuildable[x][y]  = true ;
       map.notPassable[x][y] = true ;
        SavedObstacles savedObstacles = new SavedObstacles();
        consGorSavedObject(savedObstacles,"SEA","",true,true,x,y);
        map.savingObstacle.add(savedObstacles);

    }
    private void consGorSavedObject(SavedObstacles savedObstacles,String name  , String type , boolean notBuildable ,boolean notPassable , int x , int y){
        savedObstacles.name =  name ;
        savedObstacles.type  = type ;
        savedObstacles.notBuildable =  notBuildable ;
        savedObstacles.notPassable =  notPassable ;
        savedObstacles.x = x ;
        savedObstacles.y = y ;
    }
    public void dropSeveralSea(int x1, int x2, int y1, int y2) {
        for(int i =  x1 ; i  <=  x2 ; i ++){
            for(int j = y1 ; j <= y2  ; j ++){
                dropSea(i,j);
            }
        }
    }
    public String dropTree(int x , int y , String type){
//        if (!mapIsBuilt) return "You first must build a map!";
        if(x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return "yure location is out of bounds";

/*        if(map.notBuildable[x][y]) return "Is occupied";
        if (map.getGroundType()[x][y].get(0).equals(GroundType.IRON) ||
                map.getGroundType()[x][y].get(0).equals(GroundType.STONE_ROCK) ||
                map.getGroundType()[x][y].get(0).equals(GroundType.STONE)
        ) return "not good type of ground!";*/
        Tree tree = new Tree();
        if (type.equals("desertTree")) {
            tree.desertTree();
        } else if (type.equals("cherryTree")) {
            tree.cherryTree();
        } else if (type.equals("oliveTree")) {
            tree.oliveTree();
        } else if (type.equals("coconutTree")) {
            tree.coconutTree();
        } else if (type.equals("dateTree")) {
            tree.dateTree();
        } else {
            return "Selected tree does not exist";
        }
        SavedObstacles savedObstacles = new SavedObstacles();
        consGorSavedObject(savedObstacles,"TREE","",true,false,x,y);
        map.savingObstacle.add(savedObstacles);
//        map.savingObstacle.add(savedObstacles);


        map.getObstacleMap()[x][y].add(tree);
        map.notBuildable[x][y] = true;
        return "successfully";
    }

    public void dropSeveralPlain(int x1, int x2, int y1, int y2) {
    }


//    public static int indexOfUser = 0 ;
//    public String locateCatle(int x , int y) {
//        if (!mapIsBuilt) return "You first must build a map!";
//        int numberOfUsers = User.loginUsers.size();
//        if (CreateMapController.numberOfEmpires == numberOfUsers) {
//            return "you must have more user to continue!";
//        }
//        if(x < 0 || x >= sizeOfMap || y < 0 || y >= sizeOfMap) return "Yure location is out of bounds";
//
//        if(Map.notBuildable[x][y]) return "Is occupied";
//
//        Empire empire = new Empire();
//        Manage.allEmpires.add(empire);
//        empire.setUser(User.loginUsers.get(indexOfUser));
//        indexOfUser++;
//        Castle castle = new Castle(empire);
//        castle.castle();
//        Map.AddToBuildingMap(x, y, castle);
//        empire.castleXCoordinate = x;
//        empire.castleYCCoordinate = y;
//        Map.notBuildable[x][y] = true;
//        Map.notPassable[x][y] = true;
//        Map.wallPassable[x][y] = true;
//        BuildingController.currentEmpire = empire ;
////        BuildingController.dropFirstStockpile(x, y);
//        BuildingController.currentEmpire = null ;
//
//        numberOfEmpires ++ ;
//        return "Successfully done!";
//    }
}
