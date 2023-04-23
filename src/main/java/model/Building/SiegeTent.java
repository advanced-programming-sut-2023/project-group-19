package model.Building;

import model.Empire;

import java.util.HashMap;

public class SiegeTent extends Building {

        private Names names;

    public SiegeTent(Empire government) {
        super(government);
    }

    public Names getNames() {
            return names;
        }

        //TODO add WORKER


        @Override
        public int maxHp() {
            return 0;
        }

        @Override
        public int hp() {
            return 0;
        }

        @Override
        public String groundType() {
            return null;
        }
        public HashMap<String, Integer> cost = new HashMap<>();
        @Override
        public String showBuildingName() {
            return names.getName();
        }
        public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
            cost.put("wood", wood);
            cost.put("stone", stone);
            cost.put("gold", gold);
            cost.put("iron", iron);
            cost.put("oil", oil);
        }
        public void siegeTent() {
            names = Names.SIEGE_TENT;
            createBuildingCost(0, 0, 0, 0, 0);
        }




}
