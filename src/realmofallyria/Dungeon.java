
package realmofallyria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public abstract class Dungeon {

    Random dungeonRandomizer = new Random();
    int dungeonLevel;
    String[] dungeonMonsterNames;
    String[][] dungeonMonsterAffinities;
    String[][] dungeonMonsterEquipment;
    String[] dungeonMonsterBasicAttack;
    String[] scenicViewPrompts;
    int exploreTurns;
    boolean obstructed = false;

    // determines a chance increase to encountering stronger mobs
    int difficultyDiceRollModifier;

    String dungeonMobName = "";
    String dungeonMobAffinity = "";
    int dungeonMobLVL = 0;
    Weapon dungeonMobWeapon;
    Armor dungeonMobArmor;

    Queue dungeonEncounters = new LinkedList<>();
    int recentScenicViewPromptIndex;

    public String exploreTurn() {

        String exploreResult = "";

        if (dungeonEncounters.peek().equals("Combat")) {

            exploreResult = "Combat";

        } else if (dungeonEncounters.peek().equals("Scenery")) {

            exploreResult = "Scenery";

        } else if (dungeonEncounters.peek().equals("Boss")) {

            exploreResult = "Boss";

        }

        dungeonEncounters.poll();
        exploreTurns++;
        return exploreResult;

    }

}