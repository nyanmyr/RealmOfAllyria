
package realmofallyria;

public class BossLair extends Dungeon {

    // battleHostile.generateMob("Slime", "Madeis", 1, "Slime Armor", 1, "Body", 1);
    public BossLair(int givenWildernessLevel,
            String[] givenMonsterNames,
            String[][] givenAffinities,
            String[][] givenEquipment,
            String[] givenBasicAttacks,
            String[] scenicViews,
            int playerLevel) {

        this.dungeonLevel = givenWildernessLevel;
        this.dungeonMonsterNames = givenMonsterNames;
        this.dungeonMonsterAffinities = givenAffinities;
        this.dungeonMonsterEquipment = givenEquipment;
        this.dungeonMonsterBasicAttack = givenBasicAttacks;
        this.scenicViewPrompts = scenicViews;

        // must not exceed 100
        // if battlePlayer level - wilderness level * 20 < 0 then it returns 0
        // else if the battlePlayer level - wilderness level * 20 > 0 then it returns 100
        // finally it gives right result if it does not meet the previous requirements
//        System.out.println("difficultyDiceRollModifier: " + difficultyDiceRollModifier);

        for (int i = 0; i < 9; i++) {

            int diceRoll = dungeonRandomizer.nextInt(20);

            if (diceRoll < 6) {

                dungeonEncounters.add("Combat");

            } else {

                dungeonEncounters.add("Scenery");

            }

        }

        dungeonEncounters.add("Boss");

        exploreTurns = 0;

    }

    @Override
    public void generateDungeonMob() {

        int generatedMobLVL = 7 + dungeonRandomizer.nextInt(2);
        int generatedAffinity = dungeonRandomizer.nextInt(2);
        int generatedArmorLVL = dungeonRandomizer.nextInt(1, 4);
        int generatedWeaponLVL = dungeonRandomizer.nextInt(1, 4);

        obstructed = true;

        dungeonMobName = dungeonMonsterNames[0];
        dungeonMobAffinity = dungeonMonsterAffinities[0][generatedAffinity];
        dungeonMobLVL = (generatedMobLVL + (dungeonLevel * 10));
        dungeonMobWeapon = new Weapon(dungeonMonsterEquipment[0][1], generatedWeaponLVL, new Skill(dungeonMonsterBasicAttack[0]),
                0, 0, 0);
        dungeonMobArmor = new Armor(dungeonMonsterEquipment[0][0], generatedArmorLVL, 0, 0);

//        System.out.println();
//        System.out.println("Wilderness Player Generated:");
//        System.out.printf("Name: %s (LVL %s)\n", wildernessMob.name, wildernessMob.level);
//        System.out.printf("Affinity: %s\n", wildernessMob.typeAffinity);
//        System.out.printf("Armor: %s (LVL %s)\n", wildernessMob.equippedArmor, wildernessMob.equippedArmorLVL);
//        System.out.printf("Weapon: %s (LVL %s)\n", wildernessMob.equippedWeapon, wildernessMob.equippedWeaponLVL);
    }

}
