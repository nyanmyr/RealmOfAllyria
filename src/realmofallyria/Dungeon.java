
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

    public abstract void generateDungeonMob();

    public void generateBossMob() {

        int bossMobLVL = 12;
        int generatedAffinity = dungeonRandomizer.nextInt(2);
        int generatedArmorLVL = dungeonRandomizer.nextInt(1, 4);
        int generatedWeaponLVL = dungeonRandomizer.nextInt(1, 4);

        obstructed = true;

        dungeonMobName = dungeonMonsterNames[1];
        dungeonMobAffinity = dungeonMonsterAffinities[1][generatedAffinity];
        dungeonMobLVL = (bossMobLVL + (dungeonLevel * 10));
        dungeonMobWeapon = new Weapon(dungeonMonsterEquipment[1][1], generatedWeaponLVL, new Skill(dungeonMonsterBasicAttack[1]),
                0, 0, 0);
        dungeonMobArmor = new Armor(dungeonMonsterEquipment[1][0], generatedArmorLVL, 0, 0);

    }

}
