
package realmofallyria;

public class Wilderness extends Dungeon {

    // battleHostile.generateMob("Slime", "Madeis", 1, "Slime Armor", 1, "Body", 1);
    public Wilderness(int givenWildernessLevel,
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
        difficultyDiceRollModifier = (playerLevel - (dungeonLevel * 10)) * 20 < 0 ? 0
                : (playerLevel - (dungeonLevel * 10)) * 20 > 100 ? 100
                        : (playerLevel - (dungeonLevel * 10)) * 20;
//        System.out.println("difficultyDiceRollModifier: " + difficultyDiceRollModifier);

        for (int i = 0; i < 20; i++) {

            int diceRoll = dungeonRandomizer.nextInt(20);

            if (diceRoll < 6) {

                dungeonEncounters.add("Combat");

            } else {

                dungeonEncounters.add("Scenery");

            }

        }

        exploreTurns = 0;

    }

    public void generateWildernessMob() {

        // 1 2 3
        // 0 1 2
        int mobTypeDiceRoll = dungeonRandomizer.nextInt(1, 201);
//        System.out.println("randomizeChosenMob: " + mobTypeDiceRoll);
        int totalDiceRoll = mobTypeDiceRoll + difficultyDiceRollModifier;
        int chosenMob = totalDiceRoll > 190 ? 2 : totalDiceRoll > 150 ? 1 : 0;
        int generatedMobLVL = (chosenMob == 0 ? 1 : chosenMob == 1 ? 2 : 3) * 2 - dungeonRandomizer.nextInt(2);
        int generatedAffinity = dungeonRandomizer.nextInt(2);
        int generatedArmorLVL = dungeonRandomizer.nextInt(1, 4);
        int generatedWeaponLVL = dungeonRandomizer.nextInt(1, 4);

        obstructed = true;

        dungeonMobName = dungeonMonsterNames[chosenMob];
        dungeonMobAffinity = dungeonMonsterAffinities[chosenMob][generatedAffinity];
        dungeonMobLVL = (generatedMobLVL + (dungeonLevel * 10));
        dungeonMobWeapon = new Weapon(dungeonMonsterEquipment[chosenMob][1], generatedWeaponLVL, new Skill(dungeonMonsterBasicAttack[chosenMob]),
                0, 0, 0);
        dungeonMobArmor = new Armor(dungeonMonsterEquipment[chosenMob][0], generatedArmorLVL, 0, 0);

//        System.out.println();
//        System.out.println("Wilderness Player Generated:");
//        System.out.printf("Name: %s (LVL %s)\n", wildernessMob.name, wildernessMob.level);
//        System.out.printf("Affinity: %s\n", wildernessMob.typeAffinity);
//        System.out.printf("Armor: %s (LVL %s)\n", wildernessMob.equippedArmor, wildernessMob.equippedArmorLVL);
//        System.out.printf("Weapon: %s (LVL %s)\n", wildernessMob.equippedWeapon, wildernessMob.equippedWeaponLVL);
    }

}