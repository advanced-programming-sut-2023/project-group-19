package model.Obstacle;

import model.Building.Names;

public class Tree extends Obstacle {

    public  void desertTree(){
        name = ObstacleName.DESERT_TREE;
    }
    public void cherryTree(){
        name = ObstacleName.CherryTree;
    }
    public void oliveTree(){
        name = ObstacleName.OliveTree;
    }
    public void coconutTree(){
        name = ObstacleName.CoconutTree;
    }
    public void dateTree(){
        name = ObstacleName.DateTree;
    }

}
