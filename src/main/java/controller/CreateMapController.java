package controller;

import model.GroundType;
import model.Map;
import model.Obstacle.Obstacle;
import model.Obstacle.Stone;
import model.Obstacle.Tree;
import model.Obstacle.WaterSources;
import model.* ;
import model.Building.*;

public class CreateMapController {
    public static boolean mapIsReadyForGame = false ;
    public static int numberOfEmpiers ;

    private static boolean mapIsBuilded = false ;
    private static int sizeOfMap ;

    public static int getSizeOfMap() {
        return sizeOfMap;
    }

    public static String CreateMap(int size){
        mapIsBuilded = true ;
        if(size != 200 && size != 400) return "You must choose size 200 or 400";
        sizeOfMap = size ;
        Map.CreateMap(size);
        return "Map is builded successfully!";
    }
    public static String settextureOneByOne(int x,int y,String type){
        if (!mapIsBuilded) return "At first.You must build a map!";
        if(x < 1 || x > sizeOfMap || y < 1 || y > sizeOfMap) return "yure location is out of bounds";
        if(Map.notBuildable[x - 1][y - 1]) return "Is occupied";
        GroundType groundType = GroundType.getEnumGroundType(type);
        if(groundType == null){
            WaterSources waterSources = WaterSources.getWaterSourcesByName("type");
            if(waterSources == null) return "Choose type correctly";
            Map.getObstacleMap()[x - 1][y - 1].add(waterSources);
            Map.notBuildable[x - 1][y - 1] = true ;
            if(!type.equals("Plain")) Map.notPassable[x - 1][y - 1] = true ;
            return "Change is done successfully!";
        }
        Map.getGroundType()[x - 1][y - 1].add(groundType);
        return "Change is done successfully!";
    }
    public static String settextureGroup(int x1 , int x2 , int y1 , int y2 , String type){
        if (!mapIsBuilded) return "You first must build a map!";
        if(x1 >= x2 || y1 >= y2) return "Please make sure that x and y are correctly assigned!";
        if(x1 < 1 || x2 > sizeOfMap || y1 < 1 || y2 > sizeOfMap) return "Yure location is out of bounds";
        GroundType groundType = GroundType.getEnumGroundType(type);
        if(groundType == null) return "Choose type correctly";
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                if(Map.notBuildable[i - 1][j - 1]) return "Is occupied";
            }
        }
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                Map.getGroundType()[i - 1][j - 1].add(groundType);
            }
        }
        return "Change is done successfully!";
    }
    public static String clear(int x , int y){
        if (!mapIsBuilded) return "You first must build a map!";
        if(x < 1 || x > sizeOfMap || y < 1 || y > sizeOfMap) return "yure location is out of bounds";
        Map.getTroopMap()[x - 1][y - 1].clear();
        Map.getBuildingMap()[x - 1][y - 1].clear();
        Map.getGroundType()[x - 1][y - 1].clear();
        Map.getGroundType()[x - 1][y - 1].clear();
        Map.notPassable[x - 1][y - 1] = false ;
        Map.notBuildable[x - 1][y - 1] = false ;
        return "Clear successfully";
    }
    public static String dropRock(int x , int y , String type){
        if(!type.equals("n") && !type.equals("e") && !type.equals("w") && !type.equals("s")) return "Choose direction correctly!";
        if (!mapIsBuilded) return "You first must build a map!";
        if(x < 1 || x > sizeOfMap || y < 1 || y > sizeOfMap) return "yure location is out of bounds";
        for(int i = 1 ; i <= x ; i ++){
            for(int j = 1 ; j <= y ; j ++){
                if(Map.notBuildable[i - 1][j - 1]) return "Is occupied";
            }
        }
        Map.obstacleMap[x - 1][y - 1].add(new Stone());
        Map.notPassable[x - 1][y - 1] = true ;
        Map.notBuildable[x - 1][y - 1] = true ;
        return "Successfully";
    }
    public static String dropTree(int x , int y , String type){
        if (!mapIsBuilded) return "You first must build a map!";
        if(x < 1 || x > sizeOfMap || y < 1 || y > sizeOfMap) return "yure location is out of bounds";
        for(int i = 1 ; i <= x ; i ++){
            for(int j = 1 ; j <= y ; j ++){
                if(Map.notBuildable[i - 1][j - 1]) return "Is occupied";
            }
        }
        if(!Map.getGroundType()[x - 1][y - 1].get(0).equals(GroundType.FILLFUL_DASH) &&
                !Map.getGroundType()[x - 1][y - 1].get(0).equals(GroundType.GRASS)) return "not good type of ground!";
        Tree tree = new Tree();;
        if(type.equals("desertTree")){
            tree.desertTree();
        }else if(type.equals("cherryTree")){
            tree.cherryTree();
        }else if(type.equals("oliveTree")){
            tree.oliveTree();;
        }else if(type.equals("coconutTree")){
            tree.coconutTree();
        }else if(type.equals("dateTree")){
            tree.dateTree();
        }else{
            return "Selected tree does not exist";
        }
        Map.obstacleMap[x - 1][y - 1].add(tree);
        Map.notBuildable[x - 1][y - 1] = true ;
        return "successfully";
    }
    public static int indexOfUser = 0 ;
    
    public static String locateCatle(int x , int y) {
        int numberOfUsers = User.loginUsers.size();
        if(CreateMapController.numberOfEmpiers == numberOfUsers){
            return "you must have more user to continue!" ;
        }
        if (!mapIsBuilded) return "You first must build a map!";
        if(x < 1 || x > sizeOfMap || y < 1 || y > sizeOfMap) return "yure location is out of bounds";
        for(int i = 1 ; i <= x ; i ++){
            for(int j = 1 ; j <= y ; j ++){
                if(Map.notBuildable[i - 1][j - 1]) return "Is occupied";
            }
        }
        if(!Map.getGroundType()[x][y].get(0).equals(GroundType.DEFAULT)) return "The type of buiding must be default";
        
        Empire empire = new Empire();
        Manage.allEmpires.add(empire);
        empire.setUser(User.loginUsers.get(indexOfUser));
        indexOfUser ++ ;
        Building building = new Castle(empire);
        Map.getBuildingMap()[x][y].add(building);
        numberOfEmpiers ++ ;
        if(numberOfEmpiers == 2) mapIsReadyForGame = true ;

        
        return "Successfully done!";
    }
}
