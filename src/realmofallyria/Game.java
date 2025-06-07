
package realmofallyria;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.ImageIcon;

// -----------------------------------------------------------------------------------------------------------
// <editor-fold desc="Mob class">
class Mob {

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="variables stuff">
    Random mobRandomizer = new Random();
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="characteristics variables">
    String name;
    String typeAffinity;

    int level;
    int attributePoints;

    double xp;
    double xpNeeded;
    int accumulatedLVL;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="hp and mp variables">
    // current
    double currentHP;
    double currentMP;

    double maxHP;
    double maxMP;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attributes variables">
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attributes variables">
    // attributes
    int healthPoints;
    int strengthPoints;
    int defensePoints;
    int intelligencePoints;
    int agilityPoints;

    // gear attribute addition: the attributes added from certain gears
    int HPGearAddition;
    int SPGearAddition;
    int DPGearAddition;
    int IPGearAddition;
    int APGearAddition;

    // attributes addition (used in attribute menu)
    int usedAttributePoints;

    int HPAddition;
    int SPAddition;
    int DPAddition;
    int IPAddition;
    int APAddition;

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="subattributes variables">
    // subattributes
    double physicalDefense;
    double magicalDefense;
    double physicalDamage;
    double magicalDamage;
    double critChance;

    // gear attribute addition: the attributes added from certain gears
    double pDefGearAddition;
    double mDefGearAddition;
    double pDmgGearAddition;
    double mDmgGearAddition;
    double cCGearAddition;

    // created especially for cc because of its scalability
    double addedCC;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="equipment variables">
    String equippedArmor;
    int equippedArmorLVL;
    String equippedWeapon;
    int equippedWeaponLVL;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="skills variables">
    String skill1;
    String skill2;
    String skill3;
    String skill4;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="currency variables">
    int totalCoins;
    int copperCoins;
    int silverCoins;
    int goldCoins;

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // </editor-fold>
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    //
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="mob generation mechanics stuff">
    // generates a mob
    public void generateMob(String mobName,
            String mobTypeAffinity,
            int mobLevel,
            String mobArmor,
            int mobArmorLevel,
            String mobWeapon,
            int mobWeaponLevel) {

        this.name = mobName;
        this.typeAffinity = mobTypeAffinity;
        this.level = mobLevel;

        xpNeeded = (mobLevel + 1) * 12;
//        System.out.printf("%s needs %.2f XP to level up.\n", name, xpNeeded);

        chooseAffinity();
        randomizeAttributes();
        confirmAttributeChanges();
        equipGear(mobArmor, mobArmorLevel, mobWeapon, mobWeaponLevel);

    }

    private void randomizeAttributes() {

        while (attributePoints > 0) {

            switch (mobRandomizer.nextInt(5)) {
                case 0 -> {
                    HPAddition++;
                }
                case 1 -> {
                    IPAddition++;
                }
                case 2 -> {
                    APAddition++;
                }
                case 3 -> {
                    DPAddition++;
                }
                case 4 -> {
                    SPAddition++;
                }
            }

            attributePoints--;
        }

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attribute mechanics, equip gear, full heal stuff">
    public void chooseAffinity() {

        xpNeeded = (level + 1) * 12;

        attributePoints = level * 10;

        // attributes
        healthPoints = level;
        agilityPoints = level;
        intelligencePoints = level;
        defensePoints = level;
        strengthPoints = level;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints = level * 2;
            }
            case "Virtus" -> {
                strengthPoints = level * 2;
            }
            case "Tutela" -> {
                defensePoints = level * 2;
            }
            case "Madeis" -> {
                intelligencePoints = level * 2;
            }
            case "Celeritas" -> {
                agilityPoints = level * 2;
            }
        }

        addHPMP(healthPoints, intelligencePoints, false);

    }

    public void attributeAddition(String affinityAddition) {
        usedAttributePoints++;

        switch (affinityAddition) {
            case "Sanitas" -> {
                HPAddition++;
            }
            case "Virtus" -> {
                SPAddition++;
            }
            case "Tutela" -> {
                DPAddition++;
            }
            case "Madeis" -> {
                IPAddition++;
            }
            case "Celeritas" -> {
                APAddition++;
            }
        }

    }

    public void confirmAttributeChanges() {

        usedAttributePoints = 0;

        healthPoints += HPAddition;
        strengthPoints += SPAddition;
        defensePoints += DPAddition;
        intelligencePoints += IPAddition;
        agilityPoints += APAddition;

        addHPMP(HPAddition, IPAddition, false);

        HPAddition = 0;
        SPAddition = 0;
        DPAddition = 0;
        IPAddition = 0;
        APAddition = 0;

        setSubAttributes();

    }

    public void resetAttributeChanges() {

        attributePoints += usedAttributePoints;
        usedAttributePoints = 0;

        HPAddition = 0;
        SPAddition = 0;
        DPAddition = 0;
        IPAddition = 0;
        APAddition = 0;

    }

    public void equipGear(String chosenArmor, int armorLevel, String chosenWeapon, int weaponLevel) {

        equippedArmor = chosenArmor;
        equippedArmorLVL = armorLevel;
        equippedWeapon = chosenWeapon;
        equippedWeaponLVL = weaponLevel;

        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="reset any additions done by already equipped gear">
        physicalDefense -= pDefGearAddition;
        magicalDefense -= mDefGearAddition;
        physicalDamage -= pDmgGearAddition;
        magicalDamage -= mDmgGearAddition;
        critChance -= addedCC;

        healthPoints -= HPGearAddition;
        strengthPoints -= SPGearAddition;
        defensePoints -= DPGearAddition;
        intelligencePoints -= IPGearAddition;
        agilityPoints -= APGearAddition;

        addHPMP(HPGearAddition, IPGearAddition, true);

        HPGearAddition = 0;
        SPGearAddition = 0;
        DPGearAddition = 0;
        IPGearAddition = 0;
        APGearAddition = 0;

        pDefGearAddition = 0;
        mDefGearAddition = 0;
        pDmgGearAddition = 0;
        mDmgGearAddition = 0;
        cCGearAddition = 0;

        addedCC = 0;
        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------

        switch (chosenArmor) {
            case "Slime Armor": {
                IPGearAddition += armorLevel * 2;
                break;
            }
            case "Leather Armor": {
                HPGearAddition += armorLevel * 3;
                DPGearAddition += armorLevel * 3;
                break;
            }
            case "DEBUG": {
                HPGearAddition += armorLevel * 1000;
                DPGearAddition += armorLevel * 1000;
                break;
            }
            case "Unarmored":
            default: {
                break;
            }
        }

        switch (chosenWeapon) {
            case "Iron Sword": {
                SPGearAddition += weaponLevel * 3;
                skill1 = "Slash";
                break;
            }
            case "Simple Bow": {
                SPGearAddition += weaponLevel * 1;
                APGearAddition += weaponLevel * 2;
                skill1 = "Shoot";
                break;
            }
            case "Crude Wand": {
                IPGearAddition += weaponLevel * 3;
                skill1 = "Magic Missile";
                break;
            }
            case "Body": {
                skill1 = "Tackle";
                break;
            }
            case "Club": {
                skill1 = "Club";
                break;
            }
            case "Mouth": {
                skill1 = "Bite";
                break;
            }
            case "DEBUG": {
                IPGearAddition += weaponLevel * 1000;
                skill1 = "Punch";
                break;
            }
            default: {
                break;
            }
        }

        pDefGearAddition += DPGearAddition * 0.75;
        mDefGearAddition += IPGearAddition * 0.375;
        pDmgGearAddition += SPGearAddition * 1.5;
        mDmgGearAddition += IPGearAddition * 0.75;

        physicalDefense += pDefGearAddition;
        magicalDefense += mDefGearAddition;
        physicalDamage += pDmgGearAddition;
        magicalDamage += mDmgGearAddition;

        healthPoints += HPGearAddition;
        strengthPoints += SPGearAddition;
        defensePoints += DPGearAddition;
        agilityPoints += APGearAddition;
        intelligencePoints += IPGearAddition;

        addHPMP(HPGearAddition, IPGearAddition, false);

        scaleCritChance();

    }

    private void addHPMP(int addHP, int addMP, boolean negative) {
        addHP = negative ? addHP * -1 : addHP;
        addMP = negative ? addMP * -1 : addMP;

        currentHP += addHP * 5;
        currentMP += addMP * 2.5;

        maxHP += addHP * 5;
        maxMP += addMP * 2.5;
    }

    public void setSubAttributes() {

        physicalDefense = defensePoints * 0.75;
        magicalDefense = intelligencePoints * 0.375;
        physicalDamage = strengthPoints * 1.5;
        magicalDamage = intelligencePoints * 0.75;
        scaleCritChance();

    }

    private void scaleCritChance() {

        double baseAgility = agilityPoints - APGearAddition;
        double gearBonus = APGearAddition;

        double critChanceWithoutGear = calculateCritChance(baseAgility);
        double critChanceWithGear = calculateCritChance(baseAgility + gearBonus);

        critChance = critChanceWithGear;
        addedCC = cCGearAddition = critChanceWithGear - critChanceWithoutGear;

    }

    private double calculateCritChance(double agility) {

//        double above0 = Math.min(agility, 50);
//        double above50 = Math.min(Math.max(agility - 50, 0), 50);
//        double above100 = Math.max(agility - 100, 0);
        // (1 * 25) + (0.75 * 25) + (0.5 * 25) + (0.25 * 25) + (0.125 * 25) == 65.625
        // 1 (25) >> 0.75 (25) >> 0.5 (25) >> 0.25 (25) >> 0.125 (25)
        double[] critChancesTable = new double[5];
        critChancesTable[0] = Math.min(agility, 25);
        critChancesTable[1] = Math.min(Math.max(agility - 25, 0), 25);
        critChancesTable[2] = Math.min(Math.max(agility - 50, 0), 25);
        critChancesTable[3] = Math.min(Math.max(agility - 75, 0), 25);
        critChancesTable[4] = Math.max(agility - 100, 0);

        return (critChancesTable[0] * 1)
                + (critChancesTable[1] * 0.75)
                + (critChancesTable[2] * 0.5)
                + (critChancesTable[3] * 0.25)
                + (critChancesTable[4] * 0.125);
    }

    public void fullHeal() {

        currentHP = maxHP;
        currentMP = maxMP;

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="combat mechanics stuff">
    // use skill method here
    public double[] useSkill(String skillUsed) {

        // index 0 is for pdmg, and 1 is for mdmg
        double[] damageDealt = new double[7];

        switch (skillUsed) {
            case "Bite":
            case "Club":
            case "Tackle":
            case "Punch":
            case "Slash": {
                damageDealt[0] = physicalDamage;
                break;
            }
            case "Shoot": {
                damageDealt[0] = physicalDamage * 0.75;
                break;
            }
            case "Magic Missile": {
                damageDealt[1] = magicalDamage;
                break;
            }
        }

        // randomizes the damage dealt, for variation
        if (damageDealt[0] > 0) {
            damageDealt[0] += mobRandomizer.nextDouble((physicalDamage * 0.25) * -1, (physicalDamage * 0.25));
        }
        if (damageDealt[1] > 0) {
            damageDealt[1] += mobRandomizer.nextDouble((magicalDamage * 0.25) * -1, (magicalDamage * 0.25));
        }

        // third index indicates if the hit is a crit (0 for false, 1 for true)
        damageDealt[2] = 0;

        if (critChance > mobRandomizer.nextDouble(1, 101)) {
            damageDealt[0] *= 2;
            damageDealt[1] *= 2;
            damageDealt[2] = 1;
        }

        return damageDealt;

    }

    public double[] defend(double[] damageTaken) {

        // saves the total damaged suffered
        damageTaken[5] = damageTaken[0];
        damageTaken[6] = damageTaken[1];

        // calculates actual dmg taken, taking into account physical defense
        damageTaken[0] = (damageTaken[0] > 0
                ? (damageTaken[0] - physicalDefense < 0
                        ? (damageTaken[0] * 0.1) : damageTaken[0] * 0.9 - physicalDefense)
                : 0);
        damageTaken[1] = (damageTaken[1] > 0
                ? (damageTaken[1] - magicalDefense < 0
                        ? (damageTaken[1] * 0.1) : damageTaken[1] * 0.9 - magicalDefense)
                : 0);

        // saves the dmg defended
        damageTaken[3] = physicalDefense;
        damageTaken[4] = physicalDefense;

        // ensures that there is no way of increasing health from attack
        damageTaken[0] = damageTaken[0] < 0 ? 0 : damageTaken[0];
        damageTaken[1] = damageTaken[1] < 0 ? 0 : damageTaken[1];

//        System.out.println();
//        System.out.println("PDmg: " + damageTaken[5]);
//        System.out.println("PDmg Defended: " + damageTaken[3]);
//        System.out.println("PDmg Total: " + damageTaken[0]);
        // reduces the health by the damage suffered
        currentHP -= damageTaken[0];
        currentHP -= damageTaken[1];

        return damageTaken;

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="xp, coins, level mechanics stuff">
    public void receiveXPCoinsReward(double xpGained, double coinsGained) {

        totalCoins += coinsGained;
        convertCoins();

        accumulatedLVL = 0;

        while (xpGained > 0) {
            double xpToLevelUp = xpNeeded - xp;

            if (xpGained >= xpToLevelUp) {
                // Level up!
                xpGained -= xpToLevelUp;
                accumulatedLVL++;
                xp = 0;
                xpNeeded += 12;
            } else {
                // Not enough XP to level up
                xp += xpGained;
                xpGained = 0;
            }
        }

        if (accumulatedLVL > 0) {
            levelUp();
        }

    }

    private void levelUp() {

        level += accumulatedLVL;

        attributePoints += accumulatedLVL * 10;

        // attributes
        healthPoints += accumulatedLVL;
        strengthPoints += accumulatedLVL;
        defensePoints += accumulatedLVL;
        agilityPoints += accumulatedLVL;
        intelligencePoints += accumulatedLVL;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints += accumulatedLVL * 2;
            }
            case "Virtus" -> {
                strengthPoints += accumulatedLVL * 2;
            }
            case "Tutela" -> {
                defensePoints += accumulatedLVL * 2;
            }
            case "Madeis" -> {
                intelligencePoints += accumulatedLVL * 2;
            }
            case "Celeritas" -> {
                agilityPoints += accumulatedLVL * 2;
            }
        }

        addHPMP(healthPoints, intelligencePoints, false);

        fullHeal();

        accumulatedLVL = 0;

    }

    private void convertCoins() {

        // 50 bronze coins == 1 silver coin, 2500 bronze coins = 1 gold coin
        goldCoins = totalCoins / 2500;
        int remainingAfterGold = totalCoins % 2500;

        silverCoins = remainingAfterGold / 50;
        copperCoins = remainingAfterGold % 50;

//        System.out.println("Total Coins: " + totalCoins);
//        System.out.println("Gold Coins: " + goldCoins);
//        System.out.println("Silver Coins: " + silverCoins);
//        System.out.println("Bronze Coins: " + copperCoins);
    }
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------

}
// </editor-fold>
// -----------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------
// <editor-fold desc="Battle class">

class Battle {

    Queue<String> turns = new LinkedList<>();
    Random battleRandomizer = new Random();

    Mob player;
    Mob enemy;

    // the longer the battle, the more xp
    double battleXPTurnIncrease;
    double battleXPGain;

    double battleCoinTurnIncrease;
    double battleCoinGain;

    double[] battleDamageTaken;

    int totalTurns;

    double escapeChance;

    boolean battleEnded = false;

    public Battle(Mob givenPlayer, Mob givenEnemy) {

        this.player = givenPlayer;
        this.enemy = givenEnemy;

        // calculate xp reward
        battleXPGain = battleRandomizer.nextDouble(givenEnemy.level * 4, givenEnemy.level * 5);
        battleXPTurnIncrease = (battleXPGain * 0.125);

        // calculate coins reward
        battleCoinGain = battleRandomizer.nextDouble(givenEnemy.level * 5, givenEnemy.level * 10);
        battleCoinTurnIncrease = (battleXPGain * 0.0625);

        // calculates escape chance
        // this ensures that the escape chance never exceeds 90.00%
        // and never falls short short of 20.00%
        escapeChance = 30 + ((player.agilityPoints - enemy.agilityPoints) * 5) > 90 ? 90
                : 30 + ((player.agilityPoints - enemy.agilityPoints) * 5) < 30 ? 30
                        : 30 + ((player.agilityPoints - enemy.agilityPoints) * 5);
//        System.out.printf("escapeChance: %.2f%%\n", escapeChance);

        if (player.agilityPoints > enemy.agilityPoints) {
            turns.add(player.name);
        } else {
            turns.add(enemy.name);
        }

        totalTurns++;
    }

    public String takeTurn(String battleSkillUsed, Mob defendingMob, Mob attackingMob) {

        // <editor-fold desc="uses a queue data structure to determine turns.">
        if (turns.peek().equals(player.name)) {
            turns.add(enemy.name);
        } else {
            turns.add(player.name);
        }
        turns.poll();
        // </editor-fold>

        battleXPGain += battleXPTurnIncrease;
        battleCoinGain += battleCoinTurnIncrease;
        battleDamageTaken = defendingMob.defend(attackingMob.useSkill(battleSkillUsed));

        String skilluseString = "";

        switch (battleSkillUsed) {

            case "Tackle" -> {
                skilluseString = "%s tackled %s";
            }
            case "Punch" -> {
                skilluseString = "%s punched %s";
            }
            case "Slash" -> {
                skilluseString = "%s slashed %s";
            }
            case "Shoot" -> {
                skilluseString = "%s hit %s";
            }
            case "Magic Missile" -> {
                skilluseString = "%s fired a magic missile at %s";
            }
            case "Bite" -> {
                skilluseString = "%s bit %s";
            }
            case "Club" -> {
                skilluseString = "%s bonked %s";
            }

        }

        // no such thing as blocked damage
        String attackString = String.format(""" 
                                            <html>

                                            <head>
                                            <h3 align="center">
                                            %s
                                            </h3>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s %s %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format(skilluseString, attackingMob.name, defendingMob.name),
                (battleDamageTaken[0] > 0 ? String.format("<br> PDmg inflicted (-%.2f HP)", battleDamageTaken[0]) : battleDamageTaken[5] > 0 ? "<br> Attacked completely blocked!" : ""),
                (battleDamageTaken[0] > 0 ? String.format("<br> PDmg defended (%.2f HP)", battleDamageTaken[3]) : ""),
                (battleDamageTaken[1] > 0 ? String.format("<br> MDmg inflicted (-%.2f MP)", battleDamageTaken[1]) : battleDamageTaken[6] > 0 ? "<br> Attacked completely resisted!" : ""),
                (battleDamageTaken[1] > 0 ? String.format("<br> MDmg defended (%.2f MP)", battleDamageTaken[4]) : ""),
                (battleDamageTaken[2] > 0 ? String.format("<br> CRITICAL HIT! (2x inflicted dmg)") : ""));

//        System.out.println(attackString);
//        System.out.println("NEXT TURN:" + turns.peek());
        totalTurns++;

        return attackString;

    }

    public boolean attemptFlee() {

        int fleeRoll = battleRandomizer.nextInt(1, 101);

        if (fleeRoll < escapeChance) {

            System.out.println("Escape Success");
            return false;

        } else {

            System.out.println("Escape Failure");
            return true;

        }

    }

    public String battleEnd(Boolean fleeing) {

        int battleGoldCoins = 0;
        int battleSilverCoins = 0;
        int battleCopperCoins = 0;

        String battleEndString = "";

        if (!fleeing) {
            String winningMob = "";
            String defeatedMob = "";
            if (player.currentHP < 0) {
                defeatedMob = player.name;
                winningMob = enemy.name;
                battleXPGain = 0;
                battleCoinGain = 0;
            } else {
                defeatedMob = enemy.name;
                winningMob = player.name;
                battleGoldCoins = (int) battleCoinGain / 2500;
                int battleRemainingAfterGold = (int) battleCoinGain % 2500;
                battleSilverCoins = battleRemainingAfterGold / 50;
                battleCopperCoins = battleRemainingAfterGold % 50;
            }

            battleEndString = String.format("""
                                                <html>

                                                <head>
                                                <h3 align="center">
                                                %s
                                                </h3>
                                                </head>

                                                <body>
                                                <p align="center">
                                                %s %s %s %s %s %s
                                                </p>
                                                </body>

                                                </html>
                                                """, String.format("%s defeated %s.", winningMob, defeatedMob),
                    String.format("<br> (%s turns)", totalTurns / 2),
                    battleXPGain > 0 ? String.format("<br> +%.2f XP gained.", battleXPGain) : "",
                    battleCopperCoins > 0 ? String.format("<br> %s (Copper) ", battleCopperCoins) : "",
                    battleSilverCoins > 0 ? String.format(" %s (Silver) ", battleSilverCoins) : "",
                    battleGoldCoins > 0 ? String.format(" %s (Gold) ", battleGoldCoins) : "",
                    battleCoinGain > 0 ? String.format(" coins acquired ") : "");

        } else {

            battleEndString = String.format("%s successfully fled from battle.", player);

        }

        battleEnded = true;

        return battleEndString;

    }

}
// </editor-fold>
// -----------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------
// <editor-fold desc="Wilderness class">

class Wilderness {

    Random wildernessRandomizer = new Random();
    int wildernessLevel;
    String[] monsterNames;
    String[][] affinities;
    String[][] equipment;
    String[] wildernessScenicViews;
    int exploreTurns;
    boolean obstructed = false;

    // determines a chance increase to encountering stronger mobs
    int difficultyDiceRollModifier;

    String wildernessMobName = "";
    String wildernessMobAffinity = "";
    int wildernessMobLVL = 0;
    String wildernessMobArmor = "";
    int wildernessMobArmorLVL = 0;
    String wildernessMobWeapon = "";
    int wildernessMobWeaponLVL = 0;

    Queue wildernessEncounters = new LinkedList<>();
    int scenicViewIndex;

    // enemy.generateMob("Slime", "Madeis", 1, "Slime Armor", 1, "Body", 1);
    public Wilderness(int givenWildernessLevel,
            String[] givenMonsterNames,
            String[][] givenAffinities,
            String[][] givenEquipment,
            String[] scenicViews,
            int playerLevel) {

        this.wildernessLevel = givenWildernessLevel;
        this.monsterNames = givenMonsterNames;
        this.affinities = givenAffinities;
        this.equipment = givenEquipment;
        this.wildernessScenicViews = scenicViews;

        // must not exceed 100
        // if player level - wilderness level * 20 < 0 then it returns 0
        // else if the player level - wilderness level * 20 > 0 then it returns 100
        // finally it gives right result if it does not meet the previous requirements
        difficultyDiceRollModifier = (playerLevel - (wildernessLevel * 10)) * 20 < 0 ? 0
                : (playerLevel - (wildernessLevel * 10)) * 20 > 100 ? 100
                        : (playerLevel - (wildernessLevel * 10)) * 20;
        System.out.println("difficultyDiceRollModifier: " + difficultyDiceRollModifier);

        for (int i = 0; i < 20; i++) {

            int diceRoll = wildernessRandomizer.nextInt(20);

            if (diceRoll < 6) {

                wildernessEncounters.add("Combat");

            } else {

                wildernessEncounters.add("Scenery");

            }

        }

        exploreTurns = 0;

    }

    public String exploreTurn() {

        String exploreResult;

        if (wildernessEncounters.poll().equals("Combat")) {

            exploreResult = "Combat";

        } else {

            exploreResult = "Scenery";

        }

        exploreTurns++;
        return exploreResult;

    }

    public void generateWildernessMob() {

        // 1 2 3
        // 0 1 2
        int mobTypeDiceRoll = wildernessRandomizer.nextInt(1, 201);
//        System.out.println("randomizeChosenMob: " + mobTypeDiceRoll);
        int chosenMob = mobTypeDiceRoll + difficultyDiceRollModifier > 180 ? 2 : mobTypeDiceRoll + difficultyDiceRollModifier > 150 ? 1 : 0;
        int generatedMobLVL = (chosenMob == 0 ? 1 : chosenMob == 1 ? 2 : 3) * 2 - wildernessRandomizer.nextInt(2);
        int generatedAffinity = wildernessRandomizer.nextInt(2);
        int generatedArmorLVL = wildernessRandomizer.nextInt(1, 4);
        int generatedWeaponLVL = wildernessRandomizer.nextInt(1, 4);

        obstructed = true;

        wildernessMobName = monsterNames[chosenMob];
        wildernessMobAffinity = affinities[chosenMob][generatedAffinity];
        wildernessMobLVL = (generatedMobLVL + (wildernessLevel * 10));
        wildernessMobArmor = equipment[chosenMob][0];
        wildernessMobArmorLVL = generatedArmorLVL;
        wildernessMobWeapon = equipment[chosenMob][1];
        wildernessMobWeaponLVL = generatedWeaponLVL;

//        System.out.println();
//        System.out.println("Wilderness Mob Generated:");
//        System.out.printf("Name: %s (LVL %s)\n", wildernessMob.name, wildernessMob.level);
//        System.out.printf("Affinity: %s\n", wildernessMob.typeAffinity);
//        System.out.printf("Armor: %s (LVL %s)\n", wildernessMob.equippedArmor, wildernessMob.equippedArmorLVL);
//        System.out.printf("Weapon: %s (LVL %s)\n", wildernessMob.equippedWeapon, wildernessMob.equippedWeaponLVL);
    }

}

// </editor-fold>
// -----------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------
// <editor-fold desc="Quest class">
class Quest {

    // format <Monster Name, {kill count, kill needed}>
    HashMap<String, Integer[]> QuestTasks = new HashMap<>();
    String questName = "";
    double questXPReward;
    double questCoinsReward;

    /*
    
    format for new quest <String, Integer[]}> {
    
    {
        put("Slime", new Integer[]{0, 3});
    }
    
    }
     */
    public void newQuest(HashMap<String, Integer[]> newQuest,
            String givenQuestName,
            double givenXPReward,
            double givenCoinsReward) {

        QuestTasks.clear();

        this.questName = givenQuestName;
        this.QuestTasks.putAll(newQuest);
        this.questXPReward = givenXPReward;
        this.questCoinsReward = givenCoinsReward;

        QuestTasks.put("Completed", new Integer[]{0, 1});

    }

    // gets an array of enemy levels and count of each enemies then returns the upperend of xp reward
    public double[] generateReward(int[] enemyLevels, int[] enemyCount) {

        double generatedXPReward = 0;
        double generatedCoinsReward = 0;

        for (int i = 0; i < enemyLevels.length; i++) {

            generatedXPReward += (enemyLevels[i] * 5) * enemyCount[i];
            generatedCoinsReward += (enemyLevels[i] * 10) * enemyCount[i];

        }

        return new double[]{generatedXPReward, generatedCoinsReward};

    }

    public String generateCoinsRewardString() {

        int battleGoldCoins = (int) questCoinsReward / 2500;
        int battleRemainingAfterGold = (int) questCoinsReward % 2500;
        int battleSilverCoins = battleRemainingAfterGold / 50;
        int battleCopperCoins = battleRemainingAfterGold % 50;

        return String.format(String.format(
                battleCopperCoins > 0 ? String.format("%s (Copper) ", battleCopperCoins) : "",
                battleSilverCoins > 0 ? String.format(" %s (Silver) ", battleSilverCoins) : "",
                battleGoldCoins > 0 ? String.format(" %s (Gold) ", battleGoldCoins) : "",
                " coins acquired "));

    }

    public void updateTask(String mobKilledName) {

        if (QuestTasks.containsKey(mobKilledName)) {

            QuestTasks.put(mobKilledName,
                    new Integer[]{(QuestTasks.get(mobKilledName)[0] + 1),
                        QuestTasks.get(mobKilledName)[1]});

        }

    }

    public boolean isQuestCompleted() {

        boolean questCompleted = true;

        for (String questTaskKey : QuestTasks.keySet()) {

            if (!questTaskKey.equals("Completed")) {

                if (QuestTasks.get(questTaskKey)[0] < QuestTasks.get(questTaskKey)[1]) {

                    questCompleted = false;

                }

            }

        }

        return questCompleted;

    }

}
// </editor-fold>
// -----------------------------------------------------------------------------------------------------------

public class Game extends javax.swing.JFrame {

    // --+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    // <editor-fold desc="variables">
    int textIndex = 0;
    int storylineIndex = 0;

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="dialogue manuscript">
    String[] introDialogue = {
        "Welcome to the Realm of Allyria.",
        "Before your travels begin adventurer...",
        "What is your name?"};
    String[] chooseClassDialogue = {
        "Before you begin your adventure, you must choose your affinity.",
        "Affinities determine the boost you will receive a certain attribute.",
        "Everytime you level up, you will receive an automatic addition to the attribute of your affinity.",
        "Those who have an affinity to Sanitas have a boost in health.",
        "Those who have an affinity to Celeritas have a boost in agility.",
        "Those who have an affinity to Madeis have a boost in magic.",
        "Those who have an affinity to Tutela have a boost in defense.",
        "Those who have an affinity to Virtus have a boost in strength.",
        "Now choose your affinity wisely. Your affinity will be permanent."};
    String[] editAttributesDialogue = {
        "Now adjust your attributes wisely. (Click to continue)"};
    String[] chooseGearDialogue = {
        "The world out there is dangerous.",
        "You will need something to defend yourself.",
        "Choose any of the following weapons to your liking..."};
    String[] bonusArmorDialogue = {
        "This weapon is not much.",
        "So I will provide you a full set of leather armor along with it."};
    String[] adventureBeginsDialogue = {
        "Once again, welcome to the Realm of Allyria.",
        "Your journey begins in a quiet village in the peaceful grasslands.",
        "Word has spread that the nefarious Demon Lord {UNDERWORLDPRINCE} of the Nether Realm has launched an invasion.",
        "Along the way they kidnapped the kingdom's own {PRINCESS}.",
        "Thus it is now your task to venture across the kingdom and enter the Nether Realm to vanquish the evil army of the Demon Lord...",
        "... and save the princess.",
        "However...",
        "All adventures begin with humble beginnings...",
        "I am the Village Elder of the village in which you reside.",
        "Come talk to me so that you may learn how to fight."};
    String[] startingVillageElderDialogue = {
        "Greetings, {PLAYER}.",
        "As you know, the world is in peril as of now.",
        "The kingdom's forces are engaged in a fierce war againts the demons of the Nether Continent.",
        "Most importantly {PRINCESS} has been captured.",
        "That is why time is of the essence, so I will teach you quickly.",
        "Your current strength will not do you any good against even the weakest kind of demon.",
        "You will need to train.",
        "I have taught you as much as I can.",
        "In order to face strong foes you must get stronger first.",
        "Combat out there is vastly different to a training setting.",
        "Which is why you will have a taste of real combat soon.",
        "I captuted a slime from the grasslands.",
        "They appear harmless with their gelatinous amorphous bodies.",
        "But never let appearances deceive you.",
        "These creatures have been captured still filled with tiny bones and skulls...",
        "Sometimes that of humans...",
        "You should always keep something in mind when you venture into the wilderness.",
        "You may face foes far surpassing your current power.",
        "Which makes it all the more pertinent to start your training right away.",
        "But before you begin, you need to learn a few things.",
        "Whoever has the higher agility points will attack first.",
        "You do not know their agility points so be careful.",
        "Lastly, the longer your battle lasts the more experience you gain.",
        "I do not expect you to die against this slime, but please do not surprise me.",
        "I cannot let you flee combat.",
        "You must either defeat it or be defeated.",
        "Defeat the slime and complete your training.",};
    String[] tutorialDefeat = {
        "You were defeated by a mere slime?",
        "Fret not, {PLAYER}."};
    String[] tutorialVictory = {
        "Good work on defeating that slime.",
        "You have done excellently, {PLAYER}."};
    String[] tutorialEnd = {
        "Take this as a learning experience.",
        "It goes to show you still have much potential in you.",
        "You tried your best.",
        "Thus I shall give you my blessing to explore the world and become stronger",
        "But before you go I will patch you up to full health.",
        "You must be prepared to fight the monsters in the wilderness.",
        "When you need healing rest in your home for a moment.",
        "Afterward you may venture into the wilderness",
        "Earn experience points and grow stronger.",
        "Acquire currency from defeating monsters.",
        "Visit the local travelling merchant if you wish to upgrade your gear.",
        "And if you are interested, then I may have a quest for you.",
        "I will pay fairly.",
        "Regardless, everything after this point is up to you now.",
        "Save {PRINCESS}.",
        "Defeat {UNDERWORLDPRINCE}.",
        "Safe travels and may the gods be with you, {PLAYER}."};
    String[] slimeQuest = {
        "Greetings, {PLAYER}.",
        "All great quests begin small.",
        "So for your first quest I have a request for you.",
        "The wilderness past the walls of this village are dangerous and riddled with monsters lurking about.",
        "These monsters instill fear within the hearts of my citizens.",
        "That's why I request you to rid these lands of these monsters.",
        "Not only will my citizens feel safer, you will be recognized as a hero soon enough",
        "Besides, I will pay heft.",
        "Even for the task at hand.",
        "The task being kill 3 slimes from the grassland land wilderness."};
    String[] goblinQuest = {
        "Greetings, {PLAYER}.",
        "You have done excellently once again, {PLAYER}.",
        "As promised you will be compensated for your efforts.",
        "But as you have probably guessed, that first task was simply a test.",
        "And you passed it with flying colors.",
        "Now we have to escalate.",
        "You must grow stronger now that you have a true taste in combat.",
        "That is why you will continue your quest to rid the grasslands of such wretched monsters.",
        "In your adventures into the grasslands you may have encountered a few pecular green mongrels already.",
        "Goblins is what they're usually called.",
        "And pests is all they're worth.",
        "The grasslands will be better off without them.",
        "Kill 2 of the pests from the grasslands."};
    String[] wolfQuest = {
        "Greetings, {PLAYER}.",
        "I extend myself and my people's utmost gratitue to you for your previous work, {PLAYER}.",
        "Once again we ask something of you subjugator of the grasslands.",
        "You have done excellent work clearing pests and keeping this land safe.",
        "However, another threat arises in our peaceful settlement.",
        "Locals have sighted a lone wolf lurking around, often attacking livestock.",
        "And in some cases villagers.",
        "You may or may not have encountered it yourself in your travels to the grasslands.",
        "This lone wolf has become a pest for long enough.",
        "Rid this place of that troublesome monster."};
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------

    Mob player;
    Mob enemy;
    Battle battle;
    Wilderness wilderness;
    Quest quest;

    String currentLocation = "";

    // indicates whether the dialogue menu is opened.
    Random gameRandomizer = new Random();

    // format: <character index, {"first name", "last name", "title", "gender"}>
    HashMap<Integer, String[]> characterNames = new HashMap<>();

    // </editor-fold>
    // --+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    public Game() {

        // debugging stuff (0 for normal)
        // 6 for testing dialogue menu
        // 8 for skipping tutorial
        storylineIndex = 9;
        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="debugging/ QA testing stuff">
        player = new Mob();
        quest = new Quest();
        characterNames.put(0, new String[]{"Meme Bashame", "", "Player", "m"});

        // debug characters
//        player.generateMob("Noko Shikanoko",
//                "Virtus", 1,
//                "Leather Armor", 1,
//                "Iron Sword", 100);
//        player.generateMob("Torako Koshi",
//                "Virtus", 1,
//                "Leather Armor", 1,
//                "Iron Sword", 100);
//        player.generateMob("Anko Koshi",
//                "Virtus", 1,
//                "Leather Armor", 1,
//                "Iron Sword", 100);
        player.generateMob("Meme Bashame",
                "Celeritas", 1,
                "Leather Armor", 1,
                "Simple Bow", 100);
        player.attributePoints += 1000;
//        player.currentHP = 1;
        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------
        initComponents();
        hideScreens();
        generateNPCNames();

        // enable these along with putting storylineIndex to 8 to skip tutorial
        openGameScreen();
        travelToLocation("Village");
    }

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="screen visibility stuff">
    private void openGameScreen() {

        if (player.attributePoints > 0) {

            button_Status.setText("Status (•)");

        } else {

            button_Status.setText("Status");

        }

        label_GameHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", player.currentHP,
                player.maxHP));
        label_GameMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", player.currentMP,
                player.maxMP));
        label_GameXP.setText(String.format("Experience Points (XP): %.2f / %.2f\n", player.xp,
                player.xpNeeded));
        label_GameCurrency.setText(String.format("""
                                                 <html>
                                                 <p>
                                                 Coins:%s %s %s
                                                 </p>
                                                 </html>
                                                 """, player.copperCoins > 0 ? String.format(" %s [Copper]", player.copperCoins) : "",
                player.silverCoins > 0 ? String.format(" %s [Silver]", player.silverCoins) : "",
                player.goldCoins > 0 ? String.format(" %s [Gold]", player.goldCoins) : ""));

        hideScreens();

        panel_Game.setVisible(true);
    }

    private void hideScreens() {
        textField_NameField.setVisible(false);
        panel_AffinitiesMenu.setVisible(false);
        panel_Attributes.setVisible(false);
        panel_StartingGear.setVisible(false);
        panel_Game.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Travel.setVisible(false);
        button_Return.setVisible(false);
        panel_Home.setVisible(false);
        panel_Storyline.setVisible(false);
        panel_Combat.setVisible(false);
        panel_Warning.setVisible(false);
        panel_Wilderness.setVisible(false);
        panel_Quest.setVisible(false);
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Main = new javax.swing.JPanel();
        button_Return = new javax.swing.JButton();
        label_Header = new javax.swing.JLabel();
        panel_Wilderness = new javax.swing.JPanel();
        label_WildernessLocation = new javax.swing.JLabel();
        label_EncounterLog = new javax.swing.JLabel();
        button_WildernessAttack = new javax.swing.JButton();
        button_WildernessFlee = new javax.swing.JButton();
        label_WildernessBackground = new javax.swing.JLabel();
        panel_Game = new javax.swing.JPanel();
        label_GameCurrency = new javax.swing.JLabel();
        label_GameXP = new javax.swing.JLabel();
        label_GameMP = new javax.swing.JLabel();
        label_GameHP = new javax.swing.JLabel();
        label_Location = new javax.swing.JLabel();
        button_Quest = new javax.swing.JButton();
        button_Travel = new javax.swing.JButton();
        button_Inventory = new javax.swing.JButton();
        button_Status = new javax.swing.JButton();
        panel_LocationPanel = new javax.swing.JPanel();
        button_Place1 = new javax.swing.JButton();
        button_Place2 = new javax.swing.JButton();
        button_Place3 = new javax.swing.JButton();
        label_GameBackground = new javax.swing.JLabel();
        panel_Quest = new javax.swing.JPanel();
        label_MainQuest = new javax.swing.JLabel();
        panel_Storyline = new javax.swing.JPanel();
        label_Talker = new javax.swing.JLabel();
        label_StorylineText = new javax.swing.JLabel();
        button_Yes = new javax.swing.JButton();
        panel_Attributes = new javax.swing.JPanel();
        panel_AttributesActions = new javax.swing.JPanel();
        button_AttributesConfirm = new javax.swing.JButton();
        button_AttributesReset = new javax.swing.JButton();
        label_PlayerName = new javax.swing.JLabel();
        label_PlayerAffinity = new javax.swing.JLabel();
        label_Level = new javax.swing.JLabel();
        label_AvailablePoints = new javax.swing.JLabel();
        label_HealthPoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_IntelligencePoints = new javax.swing.JLabel();
        label_AgilityPoints = new javax.swing.JLabel();
        panel_Dashes1 = new javax.swing.JPanel();
        label_Dash6 = new javax.swing.JLabel();
        label_Dash7 = new javax.swing.JLabel();
        label_Dash8 = new javax.swing.JLabel();
        label_Dash9 = new javax.swing.JLabel();
        label_Dash10 = new javax.swing.JLabel();
        panel_Dashes = new javax.swing.JPanel();
        label_Dash1 = new javax.swing.JLabel();
        label_Dash2 = new javax.swing.JLabel();
        label_Dash3 = new javax.swing.JLabel();
        label_Dash4 = new javax.swing.JLabel();
        label_Dash5 = new javax.swing.JLabel();
        panel_GearAddition = new javax.swing.JPanel();
        label_GearHP = new javax.swing.JLabel();
        label_GearSP = new javax.swing.JLabel();
        label_GearDP = new javax.swing.JLabel();
        label_GearIP = new javax.swing.JLabel();
        label_GearAP = new javax.swing.JLabel();
        panel_AttributesAddition = new javax.swing.JPanel();
        label_HPAddition = new javax.swing.JLabel();
        label_SPAddition = new javax.swing.JLabel();
        label_DPAddition = new javax.swing.JLabel();
        label_IPAddition = new javax.swing.JLabel();
        label_APAddition = new javax.swing.JLabel();
        panel_Total = new javax.swing.JPanel();
        label_TotalHP = new javax.swing.JLabel();
        label_TotalSP = new javax.swing.JLabel();
        label_TotalDP = new javax.swing.JLabel();
        label_TotalIP = new javax.swing.JLabel();
        label_TotalAP = new javax.swing.JLabel();
        panel_AttributesAdditionButtons = new javax.swing.JPanel();
        button_HPAddition = new javax.swing.JButton();
        button_SPAddition = new javax.swing.JButton();
        button_DPAddition = new javax.swing.JButton();
        button_IPAddition = new javax.swing.JButton();
        button_APAddition = new javax.swing.JButton();
        panel_Combat = new javax.swing.JPanel();
        label_CombatPlayer = new javax.swing.JLabel();
        label_CombatHP = new javax.swing.JLabel();
        label_CombatMP = new javax.swing.JLabel();
        label_CombatEnemy = new javax.swing.JLabel();
        label_EnemyHP = new javax.swing.JLabel();
        label_EnemyMP = new javax.swing.JLabel();
        button_UseInventory = new javax.swing.JButton();
        button_FleeCombat = new javax.swing.JButton();
        button_UseAttack = new javax.swing.JButton();
        panel_CombatLog = new javax.swing.JPanel();
        label_CombatLog = new javax.swing.JLabel();
        panel_Skills = new javax.swing.JPanel();
        button_UseSkill1 = new javax.swing.JButton();
        button_UseSkill2 = new javax.swing.JButton();
        button_UseSkill3 = new javax.swing.JButton();
        button_UseSkill4 = new javax.swing.JButton();
        panel_Home = new javax.swing.JPanel();
        label_HomeLabel = new javax.swing.JLabel();
        panel_Travel = new javax.swing.JPanel();
        button_Village = new javax.swing.JButton();
        button_Grasslands = new javax.swing.JButton();
        label_Wilderness = new javax.swing.JLabel();
        label_Civilization = new javax.swing.JLabel();
        panel_StartingGear = new javax.swing.JPanel();
        button_IronSword = new javax.swing.JButton();
        label_IronSword = new javax.swing.JLabel();
        button_SimpleBow = new javax.swing.JButton();
        label_SimpleBow = new javax.swing.JLabel();
        button_CrudeWand = new javax.swing.JButton();
        label_CrudeWand = new javax.swing.JLabel();
        panel_AffinitiesMenu = new javax.swing.JPanel();
        button_Sanitas = new javax.swing.JButton();
        label_Sanitas = new javax.swing.JLabel();
        button_Virtus = new javax.swing.JButton();
        label_Virtus = new javax.swing.JLabel();
        button_Tutela = new javax.swing.JButton();
        label_Tutela = new javax.swing.JLabel();
        button_Madeis = new javax.swing.JButton();
        label_Madeis = new javax.swing.JLabel();
        button_Celeritas = new javax.swing.JButton();
        label_Celeritas = new javax.swing.JLabel();
        panel_Inventory = new javax.swing.JPanel();
        label_Armor = new javax.swing.JLabel();
        label_PDef = new javax.swing.JLabel();
        label_MDef = new javax.swing.JLabel();
        label_Weapon = new javax.swing.JLabel();
        label_PDmg = new javax.swing.JLabel();
        label_MDmg = new javax.swing.JLabel();
        label_CC = new javax.swing.JLabel();
        panel_SubTotal = new javax.swing.JPanel();
        label_TotalPDef = new javax.swing.JLabel();
        label_TotalMDef = new javax.swing.JLabel();
        label_TotalPDmg = new javax.swing.JLabel();
        label_TotalMDmg = new javax.swing.JLabel();
        label_TotalCC = new javax.swing.JLabel();
        panel_Dashes2 = new javax.swing.JPanel();
        label_Dash15 = new javax.swing.JLabel();
        label_Dash14 = new javax.swing.JLabel();
        label_Dash13 = new javax.swing.JLabel();
        label_Dash12 = new javax.swing.JLabel();
        label_Dash11 = new javax.swing.JLabel();
        panel_SubGearAddition = new javax.swing.JPanel();
        label_GearPDef = new javax.swing.JLabel();
        label_GearMDef = new javax.swing.JLabel();
        label_GearPDmg = new javax.swing.JLabel();
        label_GearMDmg = new javax.swing.JLabel();
        label_GearCC = new javax.swing.JLabel();
        panel_Warning = new javax.swing.JPanel();
        label_WarningTitle = new javax.swing.JLabel();
        panel_WarningMessage = new javax.swing.JPanel();
        label_WarningBody = new javax.swing.JLabel();
        button_CloseWarning = new javax.swing.JButton();
        textField_NameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_Main.setOpaque(false);
        panel_Main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_MainMouseClicked(evt);
            }
        });
        panel_Main.setLayout(null);

        button_Return.setText("Return");
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Main.add(button_Return);
        button_Return.setBounds(420, 63, 100, 30);

        label_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Header.setText("REALM OF ALLYRIA [CLICK TO START]");
        label_Header.setToolTipText("");
        panel_Main.add(label_Header);
        label_Header.setBounds(6, 6, 520, 47);

        panel_Wilderness.setBackground(new java.awt.Color(69, 69, 69));
        panel_Wilderness.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_WildernessMouseClicked(evt);
            }
        });
        panel_Wilderness.setLayout(null);

        label_WildernessLocation.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_WildernessLocation.setForeground(new java.awt.Color(221, 221, 222));
        label_WildernessLocation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_WildernessLocation.setText("Location");
        label_WildernessLocation.setOpaque(true);
        panel_Wilderness.add(label_WildernessLocation);
        label_WildernessLocation.setBounds(150, 10, 220, 40);

        label_EncounterLog.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_EncounterLog.setForeground(new java.awt.Color(221, 221, 222));
        label_EncounterLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_EncounterLog.setText("Encounter Log");
        label_EncounterLog.setOpaque(true);
        panel_Wilderness.add(label_EncounterLog);
        label_EncounterLog.setBounds(10, 60, 500, 160);

        button_WildernessAttack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_WildernessAttack.setText("Attack");
        button_WildernessAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_WildernessAttackActionPerformed(evt);
            }
        });
        panel_Wilderness.add(button_WildernessAttack);
        button_WildernessAttack.setBounds(100, 240, 150, 40);

        button_WildernessFlee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_WildernessFlee.setText("Flee");
        button_WildernessFlee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_WildernessFleeActionPerformed(evt);
            }
        });
        panel_Wilderness.add(button_WildernessFlee);
        button_WildernessFlee.setBounds(280, 240, 150, 40);
        panel_Wilderness.add(label_WildernessBackground);
        label_WildernessBackground.setBounds(-3, 0, 530, 380);

        panel_Main.add(panel_Wilderness);
        panel_Wilderness.setBounds(5, 60, 520, 370);

        panel_Game.setBackground(new java.awt.Color(69, 69, 69));
        panel_Game.setOpaque(false);
        panel_Game.setLayout(null);

        label_GameCurrency.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_GameCurrency.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameCurrency.setForeground(new java.awt.Color(222, 222, 222));
        label_GameCurrency.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameCurrency.setText("Coins: ");
        label_GameCurrency.setOpaque(true);
        panel_Game.add(label_GameCurrency);
        label_GameCurrency.setBounds(10, 260, 240, 40);

        label_GameXP.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_GameXP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameXP.setForeground(new java.awt.Color(222, 222, 222));
        label_GameXP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameXP.setText("XP:");
        label_GameXP.setOpaque(true);
        panel_Game.add(label_GameXP);
        label_GameXP.setBounds(10, 210, 240, 40);

        label_GameMP.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_GameMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameMP.setForeground(new java.awt.Color(222, 222, 222));
        label_GameMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameMP.setText("MP:");
        label_GameMP.setOpaque(true);
        panel_Game.add(label_GameMP);
        label_GameMP.setBounds(10, 160, 240, 40);

        label_GameHP.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_GameHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameHP.setForeground(new java.awt.Color(222, 222, 222));
        label_GameHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameHP.setText("HP:");
        label_GameHP.setOpaque(true);
        panel_Game.add(label_GameHP);
        label_GameHP.setBounds(10, 110, 240, 40);

        label_Location.setBackground(new java.awt.Color(65, 65, 65, 225));
        label_Location.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Location.setForeground(new java.awt.Color(222, 222, 222));
        label_Location.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Location.setText("Location: ");
        label_Location.setOpaque(true);
        panel_Game.add(label_Location);
        label_Location.setBounds(10, 60, 240, 40);

        button_Quest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Quest.setText("Quest");
        button_Quest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_QuestActionPerformed(evt);
            }
        });
        panel_Game.add(button_Quest);
        button_Quest.setBounds(10, 10, 150, 40);

        button_Travel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Travel.setText("Travel");
        button_Travel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TravelActionPerformed(evt);
            }
        });
        panel_Game.add(button_Travel);
        button_Travel.setBounds(10, 320, 150, 40);

        button_Inventory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Inventory.setText("Inventory");
        button_Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_InventoryActionPerformed(evt);
            }
        });
        panel_Game.add(button_Inventory);
        button_Inventory.setBounds(180, 320, 150, 40);

        button_Status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Status.setText("Status");
        button_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_StatusActionPerformed(evt);
            }
        });
        panel_Game.add(button_Status);
        button_Status.setBounds(360, 320, 150, 40);

        panel_LocationPanel.setBackground(new java.awt.Color(60, 63, 63, 225));
        panel_LocationPanel.setLayout(null);

        button_Place1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Place1.setText("Village Elder");
        button_Place1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place1ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place1);
        button_Place1.setBounds(10, 10, 220, 30);

        button_Place2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Place2.setText("Travelling Merchant");
        button_Place2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place2ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place2);
        button_Place2.setBounds(10, 50, 220, 30);

        button_Place3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Place3.setText("Home");
        button_Place3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place3ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place3);
        button_Place3.setBounds(10, 90, 220, 30);

        panel_Game.add(panel_LocationPanel);
        panel_LocationPanel.setBounds(270, 60, 240, 240);
        panel_Game.add(label_GameBackground);
        label_GameBackground.setBounds(-3, 0, 530, 380);

        panel_Main.add(panel_Game);
        panel_Game.setBounds(5, 60, 520, 370);

        panel_Quest.setBackground(new java.awt.Color(69, 69, 69));
        panel_Quest.setLayout(null);

        label_MainQuest.setForeground(new java.awt.Color(187, 187, 186));
        label_MainQuest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_MainQuest.setText(" ");
        panel_Quest.add(label_MainQuest);
        label_MainQuest.setBounds(20, 40, 480, 320);

        panel_Main.add(panel_Quest);
        panel_Quest.setBounds(5, 60, 520, 370);

        panel_Storyline.setBackground(new java.awt.Color(69, 69, 69));
        panel_Storyline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_StorylineMouseClicked(evt);
            }
        });
        panel_Storyline.setLayout(null);

        label_Talker.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Talker.setForeground(new java.awt.Color(221, 221, 222));
        label_Talker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Talker.setText("Talker");
        panel_Storyline.add(label_Talker);
        label_Talker.setBounds(30, 40, 460, 90);

        label_StorylineText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StorylineText.setForeground(new java.awt.Color(221, 221, 222));
        label_StorylineText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_StorylineText.setText("[CLICK TO START]");
        panel_Storyline.add(label_StorylineText);
        label_StorylineText.setBounds(30, 130, 460, 180);

        button_Yes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Yes.setText("Confirm");
        button_Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_YesActionPerformed(evt);
            }
        });
        panel_Storyline.add(button_Yes);
        button_Yes.setBounds(180, 320, 160, 40);

        panel_Main.add(panel_Storyline);
        panel_Storyline.setBounds(5, 60, 520, 370);

        panel_Attributes.setBackground(new java.awt.Color(69, 69, 69));
        panel_Attributes.setLayout(null);

        panel_AttributesActions.setOpaque(false);
        panel_AttributesActions.setLayout(null);

        button_AttributesConfirm.setText("<html>Confirm");
        button_AttributesConfirm.setToolTipText("");
        button_AttributesConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesConfirmActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesConfirm);
        button_AttributesConfirm.setBounds(0, 0, 100, 20);

        button_AttributesReset.setText("<html>Reset");
        button_AttributesReset.setToolTipText("");
        button_AttributesReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesResetActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesReset);
        button_AttributesReset.setBounds(0, 30, 100, 20);

        panel_Attributes.add(panel_AttributesActions);
        panel_AttributesActions.setBounds(410, 80, 100, 50);

        label_PlayerName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        label_PlayerName.setForeground(new java.awt.Color(221, 221, 222));
        label_PlayerName.setText("Adventurer");
        panel_Attributes.add(label_PlayerName);
        label_PlayerName.setBounds(10, 10, 390, 30);

        label_PlayerAffinity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_PlayerAffinity.setForeground(new java.awt.Color(221, 221, 222));
        label_PlayerAffinity.setText("Affinity");
        panel_Attributes.add(label_PlayerAffinity);
        label_PlayerAffinity.setBounds(10, 40, 390, 30);

        label_Level.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_Level.setForeground(new java.awt.Color(221, 221, 222));
        label_Level.setText("LVL: 0");
        panel_Attributes.add(label_Level);
        label_Level.setBounds(10, 70, 390, 30);

        label_AvailablePoints.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_AvailablePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AvailablePoints.setText("Available Points: 0");
        panel_Attributes.add(label_AvailablePoints);
        label_AvailablePoints.setBounds(10, 100, 390, 30);

        label_HealthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_HealthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_HealthPoints.setText("Health Points:");
        panel_Attributes.add(label_HealthPoints);
        label_HealthPoints.setBounds(10, 140, 130, 30);

        label_StrengthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StrengthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_StrengthPoints.setText("Strength Points:");
        panel_Attributes.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 170, 130, 30);

        label_DefensePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DefensePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_DefensePoints.setText("Defense Points:");
        panel_Attributes.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 200, 130, 30);

        label_IntelligencePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_IntelligencePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_IntelligencePoints.setText("Intelligence Points:");
        panel_Attributes.add(label_IntelligencePoints);
        label_IntelligencePoints.setBounds(10, 230, 130, 30);

        label_AgilityPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_AgilityPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AgilityPoints.setText("Agility Points:");
        panel_Attributes.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 260, 130, 30);

        panel_Dashes1.setOpaque(false);
        panel_Dashes1.setLayout(null);

        label_Dash6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash6.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash6.setText("-");
        panel_Dashes1.add(label_Dash6);
        label_Dash6.setBounds(0, 0, 30, 30);

        label_Dash7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash7.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash7.setText("-");
        panel_Dashes1.add(label_Dash7);
        label_Dash7.setBounds(0, 30, 30, 30);

        label_Dash8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash8.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash8.setText("-");
        panel_Dashes1.add(label_Dash8);
        label_Dash8.setBounds(0, 60, 30, 30);

        label_Dash9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash9.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash9.setText("-");
        panel_Dashes1.add(label_Dash9);
        label_Dash9.setBounds(0, 90, 30, 30);

        label_Dash10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash10.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash10.setText("-");
        panel_Dashes1.add(label_Dash10);
        label_Dash10.setBounds(0, 120, 30, 30);

        panel_Attributes.add(panel_Dashes1);
        panel_Dashes1.setBounds(320, 140, 30, 150);

        panel_Dashes.setOpaque(false);
        panel_Dashes.setLayout(null);

        label_Dash1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash1.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash1.setText("-");
        panel_Dashes.add(label_Dash1);
        label_Dash1.setBounds(0, 0, 30, 30);

        label_Dash2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash2.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash2.setText("-");
        panel_Dashes.add(label_Dash2);
        label_Dash2.setBounds(0, 30, 30, 30);

        label_Dash3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash3.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash3.setText("-");
        panel_Dashes.add(label_Dash3);
        label_Dash3.setBounds(0, 60, 30, 30);

        label_Dash4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash4.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash4.setText("-");
        panel_Dashes.add(label_Dash4);
        label_Dash4.setBounds(0, 90, 30, 30);

        label_Dash5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash5.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash5.setText("-");
        panel_Dashes.add(label_Dash5);
        label_Dash5.setBounds(0, 120, 30, 30);

        panel_Attributes.add(panel_Dashes);
        panel_Dashes.setBounds(230, 140, 30, 150);

        panel_GearAddition.setOpaque(false);
        panel_GearAddition.setLayout(null);

        label_GearHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearHP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearHP.setText("(+0)");
        panel_GearAddition.add(label_GearHP);
        label_GearHP.setBounds(10, 0, 40, 30);

        label_GearSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearSP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearSP.setText("(+0)");
        panel_GearAddition.add(label_GearSP);
        label_GearSP.setBounds(10, 30, 40, 30);

        label_GearDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearDP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearDP.setText("(+0)");
        panel_GearAddition.add(label_GearDP);
        label_GearDP.setBounds(10, 60, 40, 30);

        label_GearIP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearIP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearIP.setText("(+0)");
        panel_GearAddition.add(label_GearIP);
        label_GearIP.setBounds(10, 90, 40, 30);

        label_GearAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearAP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearAP.setText("(+0)");
        panel_GearAddition.add(label_GearAP);
        label_GearAP.setBounds(10, 120, 40, 30);

        panel_Attributes.add(panel_GearAddition);
        panel_GearAddition.setBounds(260, 140, 60, 150);

        panel_AttributesAddition.setOpaque(false);
        panel_AttributesAddition.setLayout(null);

        label_HPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_HPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_HPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_HPAddition);
        label_HPAddition.setBounds(10, 0, 40, 30);

        label_SPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_SPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_SPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_SPAddition);
        label_SPAddition.setBounds(10, 30, 40, 30);

        label_DPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_DPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_DPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_DPAddition);
        label_DPAddition.setBounds(10, 60, 40, 30);

        label_IPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_IPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_IPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_IPAddition);
        label_IPAddition.setBounds(10, 90, 40, 30);

        label_APAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_APAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_APAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_APAddition.setText("(+0)");
        panel_AttributesAddition.add(label_APAddition);
        label_APAddition.setBounds(10, 120, 40, 30);

        panel_Attributes.add(panel_AttributesAddition);
        panel_AttributesAddition.setBounds(350, 140, 60, 150);

        panel_Total.setOpaque(false);
        panel_Total.setLayout(null);

        label_TotalHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalHP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalHP.setText("0");
        panel_Total.add(label_TotalHP);
        label_TotalHP.setBounds(10, 0, 40, 30);

        label_TotalSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalSP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalSP.setText("0");
        panel_Total.add(label_TotalSP);
        label_TotalSP.setBounds(10, 30, 40, 30);

        label_TotalDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalDP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalDP.setText("0");
        panel_Total.add(label_TotalDP);
        label_TotalDP.setBounds(10, 60, 40, 30);

        label_TotalIP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalIP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalIP.setText("0");
        panel_Total.add(label_TotalIP);
        label_TotalIP.setBounds(10, 90, 40, 30);

        label_TotalAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalAP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalAP.setText("0");
        panel_Total.add(label_TotalAP);
        label_TotalAP.setBounds(10, 120, 40, 30);

        panel_Attributes.add(panel_Total);
        panel_Total.setBounds(170, 140, 60, 150);

        panel_AttributesAdditionButtons.setOpaque(false);
        panel_AttributesAdditionButtons.setLayout(null);

        button_HPAddition.setText("<html>+");
        button_HPAddition.setToolTipText("");
        button_HPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_HPAddition);
        button_HPAddition.setBounds(20, 0, 50, 30);

        button_SPAddition.setText("<html>+");
        button_SPAddition.setToolTipText("");
        button_SPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_SPAddition);
        button_SPAddition.setBounds(20, 30, 50, 30);

        button_DPAddition.setText("<html>+");
        button_DPAddition.setToolTipText("");
        button_DPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_DPAddition);
        button_DPAddition.setBounds(20, 60, 50, 30);

        button_IPAddition.setText("<html>+");
        button_IPAddition.setToolTipText("");
        button_IPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_IPAddition);
        button_IPAddition.setBounds(20, 90, 50, 30);

        button_APAddition.setText("<html>+");
        button_APAddition.setToolTipText("");
        button_APAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_APAdditionAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_APAddition);
        button_APAddition.setBounds(20, 120, 50, 30);

        panel_Attributes.add(panel_AttributesAdditionButtons);
        panel_AttributesAdditionButtons.setBounds(420, 140, 90, 150);

        panel_Main.add(panel_Attributes);
        panel_Attributes.setBounds(5, 60, 520, 370);

        panel_Combat.setBackground(new java.awt.Color(69, 69, 69));
        panel_Combat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_CombatMouseClicked(evt);
            }
        });
        panel_Combat.setLayout(null);

        label_CombatPlayer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_CombatPlayer.setForeground(new java.awt.Color(221, 221, 222));
        label_CombatPlayer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatPlayer.setText("Player (LVL 50)");
        panel_Combat.add(label_CombatPlayer);
        label_CombatPlayer.setBounds(10, 10, 250, 40);

        label_CombatHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_CombatHP.setForeground(new java.awt.Color(221, 221, 222));
        label_CombatHP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatHP.setText("HP: 0 / 0");
        panel_Combat.add(label_CombatHP);
        label_CombatHP.setBounds(10, 50, 250, 20);

        label_CombatMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_CombatMP.setForeground(new java.awt.Color(221, 221, 222));
        label_CombatMP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatMP.setText("MP: 0 / 0");
        panel_Combat.add(label_CombatMP);
        label_CombatMP.setBounds(10, 70, 250, 20);

        label_CombatEnemy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_CombatEnemy.setForeground(new java.awt.Color(221, 221, 222));
        label_CombatEnemy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_CombatEnemy.setText("Enemy (LVL 50)");
        panel_Combat.add(label_CombatEnemy);
        label_CombatEnemy.setBounds(270, 10, 240, 40);

        label_EnemyHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_EnemyHP.setForeground(new java.awt.Color(221, 221, 222));
        label_EnemyHP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_EnemyHP.setText("HP: 0 / 0");
        panel_Combat.add(label_EnemyHP);
        label_EnemyHP.setBounds(270, 50, 240, 20);

        label_EnemyMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_EnemyMP.setForeground(new java.awt.Color(221, 221, 222));
        label_EnemyMP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_EnemyMP.setText("MP: 0 / 0");
        panel_Combat.add(label_EnemyMP);
        label_EnemyMP.setBounds(270, 70, 240, 20);

        button_UseInventory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseInventory.setText("Inventory");
        button_UseInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseInventoryActionPerformed(evt);
            }
        });
        panel_Combat.add(button_UseInventory);
        button_UseInventory.setBounds(180, 240, 160, 40);

        button_FleeCombat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_FleeCombat.setText("Flee");
        button_FleeCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_FleeCombatActionPerformed(evt);
            }
        });
        panel_Combat.add(button_FleeCombat);
        button_FleeCombat.setBounds(350, 240, 150, 40);

        button_UseAttack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseAttack.setText("Attack");
        button_UseAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseAttackActionPerformed(evt);
            }
        });
        panel_Combat.add(button_UseAttack);
        button_UseAttack.setBounds(20, 240, 150, 40);

        panel_CombatLog.setLayout(null);

        label_CombatLog.setBackground(new java.awt.Color(99, 99, 99));
        label_CombatLog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_CombatLog.setForeground(new java.awt.Color(9, 9, 9));
        label_CombatLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CombatLog.setText("Actions");
        label_CombatLog.setOpaque(true);
        panel_CombatLog.add(label_CombatLog);
        label_CombatLog.setBounds(0, 0, 480, 130);

        panel_Combat.add(panel_CombatLog);
        panel_CombatLog.setBounds(20, 100, 480, 130);

        panel_Skills.setLayout(null);

        button_UseSkill1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseSkill1.setText("Skill1");
        button_UseSkill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill1ActionPerformed(evt);
            }
        });
        panel_Skills.add(button_UseSkill1);
        button_UseSkill1.setBounds(10, 10, 230, 40);

        button_UseSkill2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseSkill2.setText("Skill2");
        button_UseSkill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill2ActionPerformed(evt);
            }
        });
        panel_Skills.add(button_UseSkill2);
        button_UseSkill2.setBounds(10, 50, 230, 40);

        button_UseSkill3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseSkill3.setText("Skill3");
        button_UseSkill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill3ActionPerformed(evt);
            }
        });
        panel_Skills.add(button_UseSkill3);
        button_UseSkill3.setBounds(240, 10, 230, 40);

        button_UseSkill4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_UseSkill4.setText("Skill4");
        button_UseSkill4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill4ActionPerformed(evt);
            }
        });
        panel_Skills.add(button_UseSkill4);
        button_UseSkill4.setBounds(240, 50, 230, 40);

        panel_Combat.add(panel_Skills);
        panel_Skills.setBounds(20, 130, 480, 100);

        panel_Main.add(panel_Combat);
        panel_Combat.setBounds(5, 60, 520, 370);

        panel_Home.setBackground(new java.awt.Color(69, 69, 69));
        panel_Home.setLayout(null);

        label_HomeLabel.setForeground(new java.awt.Color(221, 221, 222));
        label_HomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HomeLabel.setText("Restore full HP and MP");
        panel_Home.add(label_HomeLabel);
        label_HomeLabel.setBounds(150, 10, 220, 280);

        panel_Main.add(panel_Home);
        panel_Home.setBounds(5, 60, 520, 370);

        panel_Travel.setBackground(new java.awt.Color(69, 69, 69));
        panel_Travel.setLayout(null);

        button_Village.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Village.setText("Village");
        button_Village.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VillageActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Village);
        button_Village.setBounds(90, 60, 150, 40);

        button_Grasslands.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Grasslands.setText("Grasslands");
        button_Grasslands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_GrasslandsActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Grasslands);
        button_Grasslands.setBounds(280, 60, 150, 40);

        label_Wilderness.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Wilderness.setForeground(new java.awt.Color(221, 221, 222));
        label_Wilderness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Wilderness.setText("Wilderness");
        panel_Travel.add(label_Wilderness);
        label_Wilderness.setBounds(280, 10, 150, 40);

        label_Civilization.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Civilization.setForeground(new java.awt.Color(221, 221, 222));
        label_Civilization.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Civilization.setText("Civilization");
        panel_Travel.add(label_Civilization);
        label_Civilization.setBounds(90, 10, 150, 40);

        panel_Main.add(panel_Travel);
        panel_Travel.setBounds(5, 60, 520, 370);

        panel_StartingGear.setBackground(new java.awt.Color(69, 69, 69));
        panel_StartingGear.setLayout(null);

        button_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Iron Sword</p>  </body> </html>");
        button_IronSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IronSwordActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_IronSword);
        button_IronSword.setBounds(90, 10, 110, 50);

        label_IronSword.setForeground(new java.awt.Color(221, 221, 222));
        label_IronSword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IronSword.setText("<html><body><p align=\"center\">A warrior's proven weapon. It deals great amounts of physical damage consistently, perfect for the common brawler.<br> (Blades increase strength points and physical damage)</p></body></html> ");
        label_IronSword.setToolTipText("");
        panel_StartingGear.add(label_IronSword);
        label_IronSword.setBounds(90, 70, 110, 207);

        button_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Simple Bow</p>  </body> </html>");
        button_SimpleBow.setToolTipText("");
        button_SimpleBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SimpleBowActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_SimpleBow);
        button_SimpleBow.setBounds(330, 10, 110, 50);

        label_SimpleBow.setForeground(new java.awt.Color(221, 221, 222));
        label_SimpleBow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SimpleBow.setText("<html><body><p align=\"center\">A ranger's reliable weapon. It deals less physical damage but improves the chances of a critical hit.<br> (Bows increase agility points and crit chances)</p></body></html>");
        label_SimpleBow.setToolTipText("");
        panel_StartingGear.add(label_SimpleBow);
        label_SimpleBow.setBounds(330, 70, 110, 207);

        button_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Crude Wand</p>  </body> </html>");
        button_CrudeWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CrudeWandActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_CrudeWand);
        button_CrudeWand.setBounds(210, 10, 110, 50);

        label_CrudeWand.setForeground(new java.awt.Color(221, 221, 222));
        label_CrudeWand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CrudeWand.setText("<html><body><p align=\"center\">A mage's handy weapon. It deals magical damage, though a mage's true potential lies in their magical abilities.<br> (Wands/ staves increase intelligence points, magical damage, and magical defence)</p></body></html> ");
        label_CrudeWand.setToolTipText("");
        panel_StartingGear.add(label_CrudeWand);
        label_CrudeWand.setBounds(210, 70, 110, 207);

        panel_Main.add(panel_StartingGear);
        panel_StartingGear.setBounds(5, 60, 520, 370);

        panel_AffinitiesMenu.setBackground(new java.awt.Color(69, 69, 69));
        panel_AffinitiesMenu.setLayout(null);

        button_Sanitas.setText("<html>Sanitas");
        button_Sanitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SanitasActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Sanitas);
        button_Sanitas.setBounds(10, 10, 90, 50);

        label_Sanitas.setForeground(new java.awt.Color(221, 221, 222));
        label_Sanitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Sanitas.setText("<html><body><p align=\"center\">Sanitas blesses the holders of her affinity by improving their constitution.<br> (Health Points increases every level)</p></body></html> ");
        label_Sanitas.setToolTipText("");
        panel_AffinitiesMenu.add(label_Sanitas);
        label_Sanitas.setBounds(10, 70, 90, 220);

        button_Virtus.setText("Virtus");
        button_Virtus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VirtusActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Virtus);
        button_Virtus.setBounds(110, 10, 90, 50);

        label_Virtus.setForeground(new java.awt.Color(221, 221, 222));
        label_Virtus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Virtus.setText("<html><body><p align=\"center\">Virtus blesses the holders of his affinity by improving their might.<br> (Strength Points increases every level)</p></body></html> ");
        label_Virtus.setToolTipText("");
        panel_AffinitiesMenu.add(label_Virtus);
        label_Virtus.setBounds(110, 70, 90, 220);

        button_Tutela.setText("<html>Tutela");
        button_Tutela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TutelaActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Tutela);
        button_Tutela.setBounds(210, 10, 90, 50);

        label_Tutela.setForeground(new java.awt.Color(221, 221, 222));
        label_Tutela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutela.setText("<html><body><p align=\"center\">Tutela blesses the holders of her affinity by improving their fortitude.<br> (Defense Points increases every level)</p></body></html> ");
        label_Tutela.setToolTipText("");
        panel_AffinitiesMenu.add(label_Tutela);
        label_Tutela.setBounds(210, 70, 90, 220);

        button_Madeis.setText("<html>Madeis");
        button_Madeis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MadeisActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Madeis);
        button_Madeis.setBounds(310, 10, 90, 50);

        label_Madeis.setForeground(new java.awt.Color(221, 221, 222));
        label_Madeis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Madeis.setText("<html><body><p align=\"center\">Madeis blesses the holders of his affinity by improving their magical abilities.<br> (Intelligence Points increases every level)</p></body></html> ");
        label_Madeis.setToolTipText("");
        panel_AffinitiesMenu.add(label_Madeis);
        label_Madeis.setBounds(310, 70, 90, 220);

        button_Celeritas.setText("<html>Celeritas");
        button_Celeritas.setToolTipText("");
        button_Celeritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CeleritasActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Celeritas);
        button_Celeritas.setBounds(410, 10, 90, 50);

        label_Celeritas.setForeground(new java.awt.Color(221, 221, 222));
        label_Celeritas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Celeritas.setText("<html><body><p align=\"center\">Sanitas blesses the holders of her affinity by improving their constitution.<br> (Health Points increases every level)</p></body></html> ");
        label_Celeritas.setToolTipText("");
        panel_AffinitiesMenu.add(label_Celeritas);
        label_Celeritas.setBounds(410, 70, 90, 220);

        panel_Main.add(panel_AffinitiesMenu);
        panel_AffinitiesMenu.setBounds(5, 60, 520, 370);

        panel_Inventory.setBackground(new java.awt.Color(69, 69, 69));
        panel_Inventory.setLayout(null);

        label_Armor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Armor.setForeground(new java.awt.Color(187, 187, 186));
        label_Armor.setText("Equipped Armor: ");
        panel_Inventory.add(label_Armor);
        label_Armor.setBounds(80, 20, 360, 50);

        label_PDef.setForeground(new java.awt.Color(187, 187, 186));
        label_PDef.setText("Physical Defence (PDef): ");
        panel_Inventory.add(label_PDef);
        label_PDef.setBounds(80, 70, 150, 30);

        label_MDef.setForeground(new java.awt.Color(187, 187, 186));
        label_MDef.setText("Magical Defense (MDef): ");
        panel_Inventory.add(label_MDef);
        label_MDef.setBounds(80, 100, 150, 30);

        label_Weapon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Weapon.setForeground(new java.awt.Color(187, 187, 186));
        label_Weapon.setText("Equipped Weapon: ");
        panel_Inventory.add(label_Weapon);
        label_Weapon.setBounds(80, 130, 360, 50);

        label_PDmg.setForeground(new java.awt.Color(187, 187, 186));
        label_PDmg.setText("Physical Damage (PDmg): ");
        panel_Inventory.add(label_PDmg);
        label_PDmg.setBounds(80, 180, 150, 30);

        label_MDmg.setForeground(new java.awt.Color(187, 187, 186));
        label_MDmg.setText("Magical Damage (MDmg): ");
        panel_Inventory.add(label_MDmg);
        label_MDmg.setBounds(80, 210, 150, 30);

        label_CC.setForeground(new java.awt.Color(187, 187, 186));
        label_CC.setText("Critical Chance (CC): ");
        panel_Inventory.add(label_CC);
        label_CC.setBounds(80, 240, 150, 30);

        panel_SubTotal.setOpaque(false);
        panel_SubTotal.setLayout(null);

        label_TotalPDef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalPDef.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalPDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalPDef.setText("0");
        panel_SubTotal.add(label_TotalPDef);
        label_TotalPDef.setBounds(10, 0, 60, 30);

        label_TotalMDef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalMDef.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalMDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalMDef.setText("0");
        panel_SubTotal.add(label_TotalMDef);
        label_TotalMDef.setBounds(10, 30, 60, 30);

        label_TotalPDmg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalPDmg.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalPDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalPDmg.setText("0");
        panel_SubTotal.add(label_TotalPDmg);
        label_TotalPDmg.setBounds(10, 110, 60, 30);

        label_TotalMDmg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalMDmg.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalMDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalMDmg.setText("0");
        panel_SubTotal.add(label_TotalMDmg);
        label_TotalMDmg.setBounds(10, 140, 60, 30);

        label_TotalCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalCC.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalCC.setText("0");
        panel_SubTotal.add(label_TotalCC);
        label_TotalCC.setBounds(10, 170, 60, 30);

        panel_Inventory.add(panel_SubTotal);
        panel_SubTotal.setBounds(250, 70, 80, 200);

        panel_Dashes2.setOpaque(false);
        panel_Dashes2.setLayout(null);

        label_Dash15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash15.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash15.setText("-");
        panel_Dashes2.add(label_Dash15);
        label_Dash15.setBounds(0, 170, 30, 30);

        label_Dash14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash14.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash14.setText("-");
        panel_Dashes2.add(label_Dash14);
        label_Dash14.setBounds(0, 140, 30, 30);

        label_Dash13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash13.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash13.setText("-");
        panel_Dashes2.add(label_Dash13);
        label_Dash13.setBounds(0, 110, 30, 30);

        label_Dash12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash12.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash12.setText("-");
        panel_Dashes2.add(label_Dash12);
        label_Dash12.setBounds(0, 30, 30, 30);

        label_Dash11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dash11.setForeground(new java.awt.Color(221, 221, 222));
        label_Dash11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash11.setText("-");
        panel_Dashes2.add(label_Dash11);
        label_Dash11.setBounds(0, 0, 30, 30);

        panel_Inventory.add(panel_Dashes2);
        panel_Dashes2.setBounds(330, 70, 30, 200);

        panel_SubGearAddition.setOpaque(false);
        panel_SubGearAddition.setLayout(null);

        label_GearPDef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearPDef.setForeground(new java.awt.Color(221, 221, 222));
        label_GearPDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearPDef.setText("0");
        panel_SubGearAddition.add(label_GearPDef);
        label_GearPDef.setBounds(10, 0, 60, 30);

        label_GearMDef.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearMDef.setForeground(new java.awt.Color(221, 221, 222));
        label_GearMDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearMDef.setText("0");
        panel_SubGearAddition.add(label_GearMDef);
        label_GearMDef.setBounds(10, 30, 60, 30);

        label_GearPDmg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearPDmg.setForeground(new java.awt.Color(221, 221, 222));
        label_GearPDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearPDmg.setText("0");
        panel_SubGearAddition.add(label_GearPDmg);
        label_GearPDmg.setBounds(10, 110, 60, 30);

        label_GearMDmg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearMDmg.setForeground(new java.awt.Color(221, 221, 222));
        label_GearMDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearMDmg.setText("0");
        panel_SubGearAddition.add(label_GearMDmg);
        label_GearMDmg.setBounds(10, 140, 60, 30);

        label_GearCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearCC.setForeground(new java.awt.Color(221, 221, 222));
        label_GearCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearCC.setText("0");
        panel_SubGearAddition.add(label_GearCC);
        label_GearCC.setBounds(10, 170, 60, 30);

        panel_Inventory.add(panel_SubGearAddition);
        panel_SubGearAddition.setBounds(360, 70, 80, 200);

        panel_Main.add(panel_Inventory);
        panel_Inventory.setBounds(5, 60, 520, 370);

        panel_Warning.setLayout(null);

        label_WarningTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_WarningTitle.setText("jLabel1");
        panel_Warning.add(label_WarningTitle);
        label_WarningTitle.setBounds(10, 10, 370, 40);

        panel_WarningMessage.setBackground(new java.awt.Color(69, 69, 69));
        panel_WarningMessage.setLayout(null);

        label_WarningBody.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_WarningBody.setText("jLabel1");
        panel_WarningMessage.add(label_WarningBody);
        label_WarningBody.setBounds(10, 10, 350, 150);

        button_CloseWarning.setText("Close");
        button_CloseWarning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_CloseWarningMouseClicked(evt);
            }
        });
        panel_WarningMessage.add(button_CloseWarning);
        button_CloseWarning.setBounds(100, 160, 170, 40);

        panel_Warning.add(panel_WarningMessage);
        panel_WarningMessage.setBounds(10, 60, 370, 210);

        panel_Main.add(panel_Warning);
        panel_Warning.setBounds(70, 110, 390, 280);

        textField_NameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField_NameField.setText("Adventurer");
        panel_Main.add(textField_NameField);
        textField_NameField.setBounds(30, 190, 470, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="opening sequence stuff">

    private void panel_MainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_MainMouseClicked

        if (storylineIndex == 0) {

            // starts the game proper
            speakToNPC(2);
            label_Header.setText("REALM OF ALLYRIA (v0.8)");

        }

    }//GEN-LAST:event_panel_MainMouseClicked

    private void moveDialogue() {
//        System.out.println("storylineIndex: " + storylineIndex);
//        System.out.println("textIndex: " + textIndex);

        String[] loadedStorylineText = new String[0];

        // determine which dialogue array to load
        switch (storylineIndex) {

            case 0 -> {
                loadedStorylineText = introDialogue;
            }
            case 1 -> {
                loadedStorylineText = chooseClassDialogue;
            }
            case 2 -> {
                loadedStorylineText = editAttributesDialogue;
            }
            case 3 -> {
                loadedStorylineText = chooseGearDialogue;
            }
            case 4 -> {
                loadedStorylineText = bonusArmorDialogue;
            }
            case 5 -> {
                loadedStorylineText = adventureBeginsDialogue;
            }
            case 6 -> {
                loadedStorylineText = startingVillageElderDialogue;
            }
            case 7 -> {
                if (player.currentHP < 0) {
                    loadedStorylineText = tutorialDefeat;
                } else {
                    loadedStorylineText = tutorialVictory;
                }
            }
            case 8 -> {
                loadedStorylineText = tutorialEnd;
            }
            case 9 -> {
                loadedStorylineText = slimeQuest;
            }
            case 10 -> {
                loadedStorylineText = goblinQuest;
            }
            case 11 -> {
                loadedStorylineText = wolfQuest;
            }

        }

        label_StorylineText.setText(formatText(loadedStorylineText[textIndex]));

        if (storylineIndex == 0 && textIndex >= 2) {

            // entering name sequence
            textField_NameField.setVisible(true);
            panel_Main.setComponentZOrder(textField_NameField, 0);
            button_Yes.setVisible(true);

        } else if (storylineIndex == 1 && textIndex >= 8) {

            // entering affinity sequence
            player = new Mob();
            quest = new Quest();
            player.name = textField_NameField.getText();
            button_Yes.setVisible(true);

        } else if (storylineIndex == 2 && textIndex >= 0) {

            // adjusting attributes sequence
            // sets the player name and affinity text in the attributes menu
            label_PlayerName.setText(player.name);
            label_PlayerAffinity.setText(player.typeAffinity);

            button_Yes.setVisible(true);

        } else if (storylineIndex == 3 && textIndex >= 2) {

            // starting gear sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 4 && textIndex >= 1) {

            // leather armor gift sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 5 && textIndex >= 9) {

            // lore dump sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 6 && textIndex >= 26) {

            // slime combat sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 7 && textIndex >= 1) {

            // post slime combat sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 8 && textIndex >= 16) {

            // tutorial end sequence
            button_Yes.setVisible(true);
            button_Yes.setText("End Tutorial");

        } else if (storylineIndex == 9 && textIndex >= 9) {

            // slime quest sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 10 && textIndex >= 12) {

            // goblin quest sequence
            button_Yes.setVisible(true);

        } else if (storylineIndex == 11 && textIndex >= 9) {

            // wolf quest sequence
            button_Yes.setVisible(true);

        }

        // moves the text index to the next line if thhe current index is not at the end of the loaded storyline text
        if (textIndex < loadedStorylineText.length - 1) {
            textIndex++;
        }

    }

    private void nextDialogueArray() {

        panel_Storyline.setVisible(true);
        storylineIndex++;
        textIndex = 0;

        String continueText = "(CLICK TO CONTINUE)";
        label_StorylineText.setText(continueText);

    }

    private String formatText(String textToBeFormatted) {

        /*
        character name tags:
        player: {PLAYER}
        princess: {PRINCESS}

        ALLIES
        village elder: {VILLAGEELDER}
        lord/ lady: {LORD}
        duke/ duchess: {DUKE}
        commander: {COMMANDER}
        king/ queen: {KING}

        DEMONS
        baron/ baroness: {BARON}
        general:  {GENERAL}
        lesser lord/ lady: {LESSERLORD}
        arch demon: {ARCH}
        prince/ princess of the underworld: {UNDERWORLDPRINCE}
         */
        HashMap<Integer, String> characterIndexeMap = new HashMap<Integer, String>() {

            {
                put(0, "{PLAYER}");
                put(1, "{PRINCESS}");
                put(2, "{VILLAGEELDER}");
                put(3, "{LORD}");
                put(4, "{DUKE}");
                put(5, "{COMMANDER}");
                put(6, "{KING}");
                put(7, "{BARON}");
                put(8, "{GENERAL}");
                put(9, "{LESSERLORD}");
                put(10, "{ARCH}");
                put(11, "{UNDERWORLDPRINCE}");
            }

        };

        for (int characterFormatIndex : characterIndexeMap.keySet()) {

            if (textToBeFormatted.contains(characterIndexeMap.get(characterFormatIndex))) {
                String fullName = String.format("%s %s", characterNames.get(characterFormatIndex)[0], characterNames.get(characterFormatIndex)[1]);
                textToBeFormatted = textToBeFormatted.replace(characterIndexeMap.get(characterFormatIndex), fullName);
            }

        }

        textToBeFormatted = "<html><p align=\"center\">" + textToBeFormatted + "</p></html>";

        return textToBeFormatted;

    }

    private void generateNPCNames() {

        Random nameRandomizer = new Random();

        /*
            player = 0
            princess = 1
            village elder = 2
            lord/ lady = 3
            duke/ duchess = 4
            commander = 5
            king/ queen = 6
            baroness/ baron = 7
            general = 8
            lesser lord/ lady = 9
            arch demon/ demoness = 10
            prince/ princess of the underworld = 11
         */
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="Array lists of names">
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="princess first names">
        ArrayList<String> listOfPrincessFirstNames = new ArrayList<>();
        listOfPrincessFirstNames.add("Evelyn");
        listOfPrincessFirstNames.add("Rosamont");
        listOfPrincessFirstNames.add("Elowen");
        listOfPrincessFirstNames.add("Cecilia");
        listOfPrincessFirstNames.add("Gwendolyn");
        listOfPrincessFirstNames.add("Leonora");
        listOfPrincessFirstNames.add("Theodora");
        listOfPrincessFirstNames.add("Marianne");
        listOfPrincessFirstNames.add("Beatrix");
        listOfPrincessFirstNames.add("Emilia");
        listOfPrincessFirstNames.add("Lorelei");
        listOfPrincessFirstNames.add("Anneliese");
        listOfPrincessFirstNames.add("Eléonore");
        listOfPrincessFirstNames.add("Carmilla");
        listOfPrincessFirstNames.add("Genevieve");
        listOfPrincessFirstNames.add("Celestina");
        listOfPrincessFirstNames.add("Evangeline");
        listOfPrincessFirstNames.add("Giselle");
        listOfPrincessFirstNames.add("Lysandra");
        listOfPrincessFirstNames.add("Delphina");
        listOfPrincessFirstNames.add("Esmeralda");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="royalty first names">
        ArrayList<String[]> listOfRoyalFirstNames = new ArrayList<>();
        listOfRoyalFirstNames.add(new String[]{"William", "m"});
        listOfRoyalFirstNames.add(new String[]{"Charles", "m"});
        listOfRoyalFirstNames.add(new String[]{"George", "m"});
        listOfRoyalFirstNames.add(new String[]{"Connor", "m"});
        listOfRoyalFirstNames.add(new String[]{"Nicholas", "m"});
        listOfRoyalFirstNames.add(new String[]{"Wulfric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Bartholomew", "m"});
        listOfRoyalFirstNames.add(new String[]{"Benedict", "m"});
        listOfRoyalFirstNames.add(new String[]{"Alexander", "m"});
        listOfRoyalFirstNames.add(new String[]{"Alaric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Hadrian", "m"});
        listOfRoyalFirstNames.add(new String[]{"Thelric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Lucas", "m"});
        listOfRoyalFirstNames.add(new String[]{"Sebastian", "m"});
        listOfRoyalFirstNames.add(new String[]{"Arthur", "m"});
        listOfRoyalFirstNames.add(new String[]{"Seraphina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Maerith", "f"});
        listOfRoyalFirstNames.add(new String[]{"Adelindra", "f"});
        listOfRoyalFirstNames.add(new String[]{"Alinora", "f"});
        listOfRoyalFirstNames.add(new String[]{"Eveline", "f"});
        listOfRoyalFirstNames.add(new String[]{"Melaina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Marigold", "f"});
        listOfRoyalFirstNames.add(new String[]{"Miranda", "f"});
        listOfRoyalFirstNames.add(new String[]{"Katharina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Ophelia", "f"});
        listOfRoyalFirstNames.add(new String[]{"Beatrice", "f"});
        listOfRoyalFirstNames.add(new String[]{"Rosalina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Isabella", "f"});
        listOfRoyalFirstNames.add(new String[]{"Elizabeth", "f"});
        listOfRoyalFirstNames.add(new String[]{"Margaret", "f"});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="royalty surnames">
        ArrayList<String> listOfRoyalSurames = new ArrayList<>();
        listOfRoyalSurames.add("Valenfort");
        listOfRoyalSurames.add("Lionhart");
        listOfRoyalSurames.add("Alderwynd");
        listOfRoyalSurames.add("Kaisereich");
        listOfRoyalSurames.add("Eichenwald");
        listOfRoyalSurames.add("Grimsburg");
        listOfRoyalSurames.add("Eastershire");
        listOfRoyalSurames.add("Ravenwell");
        listOfRoyalSurames.add("Hearthsvale");
        listOfRoyalSurames.add("Drachtenfeld");
        listOfRoyalSurames.add("Falkenford");
        listOfRoyalSurames.add("Shwarzeholdt");
        listOfRoyalSurames.add("Himmelfurt");
        listOfRoyalSurames.add("Montrevaux");
        listOfRoyalSurames.add("Duclaret");
        listOfRoyalSurames.add("Wolfsheim");
        listOfRoyalSurames.add("Steinwulf");
        listOfRoyalSurames.add("Montclaire");
        listOfRoyalSurames.add("Clermontaine");
        listOfRoyalSurames.add("Florandis");
        listOfRoyalSurames.add("Belleroix");
        listOfRoyalSurames.add("Clermontaine");
        listOfRoyalSurames.add("Rousselique");
        listOfRoyalSurames.add("Laurevigne");
        listOfRoyalSurames.add("Duvallon");
        listOfRoyalSurames.add("Carmichael");
        listOfRoyalSurames.add("Bradfort");
        listOfRoyalSurames.add("Bonavich");
        listOfRoyalSurames.add("Harrington");
        listOfRoyalSurames.add("Barkshire");
        listOfRoyalSurames.add("Aldereich");
        listOfRoyalSurames.add("Astor");
        listOfRoyalSurames.add("Lorraine");
        listOfRoyalSurames.add("Ellington");
        listOfRoyalSurames.add("Castleton");
        listOfRoyalSurames.add("Davenport");
        listOfRoyalSurames.add("Delacroix");
        listOfRoyalSurames.add("Havilland");
        listOfRoyalSurames.add("Greenwood");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="demonic first names">
        ArrayList<String[]> listOfDemonicFirstNames = new ArrayList<>();
        listOfDemonicFirstNames.add(new String[]{"Setaroth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Satanir", "m"});
        listOfDemonicFirstNames.add(new String[]{"Maddon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Belphegon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Asmodenus", "m"});
        listOfDemonicFirstNames.add(new String[]{"Beelzus", "m"});
        listOfDemonicFirstNames.add(new String[]{"Remiael", "m"});
        listOfDemonicFirstNames.add(new String[]{"Begemoth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Baeloth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Vareximon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Obrithiel", "m"});
        listOfDemonicFirstNames.add(new String[]{"Zekarion", "m"});
        listOfDemonicFirstNames.add(new String[]{"Samaqel", "m"});
        listOfDemonicFirstNames.add(new String[]{"Belzahir", "m"});
        listOfDemonicFirstNames.add(new String[]{"Azrakael", "m"});

        listOfDemonicFirstNames.add(new String[]{"Malgrith", "f"});
        listOfDemonicFirstNames.add(new String[]{"Loravael", "f"});
        listOfDemonicFirstNames.add(new String[]{"Lucifera", "f"});
        listOfDemonicFirstNames.add(new String[]{"Lilith", "f"});
        listOfDemonicFirstNames.add(new String[]{"Nameenah", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ashtera", "f"});
        listOfDemonicFirstNames.add(new String[]{"Uriela", "f"});
        listOfDemonicFirstNames.add(new String[]{"Zahreh", "f"});
        listOfDemonicFirstNames.add(new String[]{"Belphevra", "f"});
        listOfDemonicFirstNames.add(new String[]{"Drevaelah", "f"});
        listOfDemonicFirstNames.add(new String[]{"Malithra", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ezkireth", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ysmar", "f"});
        listOfDemonicFirstNames.add(new String[]{"Helviatha", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ophiriel", "f"});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="demonic surnames">
        ArrayList<String> listOfDemonicSurnames = new ArrayList<>();
        listOfDemonicSurnames.add("Blackthorn");
        listOfDemonicSurnames.add("Shadowmere");
        listOfDemonicSurnames.add("Grimshaw");
        listOfDemonicSurnames.add("Darkweaver");
        listOfDemonicSurnames.add("Hellstrom");
        listOfDemonicSurnames.add("Nightshade");
        listOfDemonicSurnames.add("Dreadmore");
        listOfDemonicSurnames.add("Gravecourt");
        listOfDemonicSurnames.add("Harrowfell");
        listOfDemonicSurnames.add("Crimsonreach");
        listOfDemonicSurnames.add("Blutkreig");
        listOfDemonicSurnames.add("Hexenwaldt");
        listOfDemonicSurnames.add("Eisenwraith");
        listOfDemonicSurnames.add("Malrevoir");
        listOfDemonicSurnames.add("Charnoire");
        listOfDemonicSurnames.add("Sangversé");
        listOfDemonicSurnames.add("Bellombre");
        listOfDemonicSurnames.add("Vaulremort");
        listOfDemonicSurnames.add("Infernus");
        listOfDemonicSurnames.add("Malachai");
        listOfDemonicSurnames.add("Grimharrow");
        listOfDemonicSurnames.add("Nightbramble");
        listOfDemonicSurnames.add("Ruinmarsh");
        listOfDemonicSurnames.add("Kriegfaust");
        listOfDemonicSurnames.add("Blutnacht");
        listOfDemonicSurnames.add("Flammenriss");
        listOfDemonicSurnames.add("Totensee");
        listOfDemonicSurnames.add("Noireclat");
        listOfDemonicSurnames.add("Vallombreux");
        listOfDemonicSurnames.add("Belleschain");
        listOfDemonicSurnames.add("Faucheval");
        listOfDemonicSurnames.add("Morvelain");
        listOfDemonicSurnames.add("Revenoir");
        listOfDemonicSurnames.add("Darkenveil");
        listOfDemonicSurnames.add("Hellhammer");
        listOfDemonicSurnames.add("Hellscream");
        listOfDemonicSurnames.add("Hexmourne");
        listOfDemonicSurnames.add("Umbramist");
        listOfDemonicSurnames.add("Gloomwraith");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="generates npc names">
        int randomizedPrincessName = nameRandomizer.nextInt(listOfPrincessFirstNames.size());
        int randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(1,
                new String[]{listOfPrincessFirstNames.get(randomizedPrincessName),
                    listOfRoyalSurames.remove(randomizedLastName),
                    "Princess",
                    "f"});

        int randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(2,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    "Village Elder",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        // lord lady
        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(3,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Lady" : "Lord",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(4,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Duchess" : "Duke",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(5,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    "Commander",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(6,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Queen" : "King",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="generates boss names">
        int randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        int randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(7,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Baroness" : "Baron",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(8,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    "General",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(9,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Lesser Lady" : "Lesser Lord",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(10,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Arch Demoness" : "Arch Demon",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(11,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Princess of the Underworld" : "Prince of the Underworld",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------

    }
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choosing your affinity stuff">

    private void button_VirtusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VirtusActionPerformed

        setPlayerClass("Virtus");

    }//GEN-LAST:event_button_VirtusActionPerformed

    private void button_MadeisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MadeisActionPerformed

        setPlayerClass("Madeis");

    }//GEN-LAST:event_button_MadeisActionPerformed

    private void button_CeleritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CeleritasActionPerformed

        setPlayerClass("Celeritas");


    }//GEN-LAST:event_button_CeleritasActionPerformed

    private void button_SanitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SanitasActionPerformed

        setPlayerClass("Sanitas");

    }//GEN-LAST:event_button_SanitasActionPerformed

    private void button_TutelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TutelaActionPerformed

        setPlayerClass("Tutela");

    }//GEN-LAST:event_button_TutelaActionPerformed

    private void setPlayerClass(String playerClass) {

        player.typeAffinity = playerClass;
        panel_AffinitiesMenu.setVisible(false);
        nextDialogueArray();

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attributes menu stuff">
    private void openAttributesMenu() {
        panel_Attributes.setVisible(true);
        label_Level.setText(String.format("Level: %s (%.2f / %.2f)\n", String.valueOf(player.level),
                player.xp, player.xpNeeded));
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        label_TotalHP.setText(String.valueOf(player.healthPoints));
        label_TotalAP.setText(String.valueOf(player.agilityPoints));
        label_TotalIP.setText(String.valueOf(player.intelligencePoints));
        label_TotalDP.setText(String.valueOf(player.defensePoints));
        label_TotalSP.setText(String.valueOf(player.strengthPoints));

        showGearAdditions();

        resetAttributeChanges();

        showAvailableAttributePoints();

    }

    private void showGearAdditions() {

        if (player.HPGearAddition > 0) {
            label_GearHP.setText(String.format("(+%s)", String.valueOf(player.HPGearAddition)));
            label_GearHP.setVisible(true);
        } else {
            label_GearHP.setVisible(false);
        }
        if (player.APGearAddition > 0) {
            label_GearAP.setText(String.format("(+%s)", String.valueOf(player.APGearAddition)));
            label_GearAP.setVisible(true);
        } else {
            label_GearAP.setVisible(false);
        }
        if (player.IPGearAddition > 0) {
            label_GearIP.setText(String.format("(+%s)", String.valueOf(player.IPGearAddition)));
            label_GearIP.setVisible(true);
        } else {
            label_GearIP.setVisible(false);
        }
        if (player.DPGearAddition > 0) {
            label_GearDP.setText(String.format("(+%s)", String.valueOf(player.DPGearAddition)));
            label_GearDP.setVisible(true);
        } else {
            label_GearDP.setVisible(false);
        }
        if (player.SPGearAddition > 0) {
            label_GearSP.setText(String.format("(+%s)", String.valueOf(player.SPGearAddition)));
            label_GearSP.setVisible(true);
        } else {
            label_GearSP.setVisible(false);
        }

    }

    private void button_HPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Sanitas");
        }

    }//GEN-LAST:event_button_HPAdditionActionPerformed

    private void button_APAdditionAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_APAdditionAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Celeritas");
        }

    }//GEN-LAST:event_button_APAdditionAdditionActionPerformed

    private void button_IPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Madeis");
        }

    }//GEN-LAST:event_button_IPAdditionActionPerformed

    private void button_DPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Tutela");
        }

    }//GEN-LAST:event_button_DPAdditionActionPerformed

    private void button_SPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Virtus");
        }

    }//GEN-LAST:event_button_SPAdditionActionPerformed

    private void button_AttributesConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesConfirmActionPerformed

        player.confirmAttributeChanges();

        openAttributesMenu();
        setAttributesAddition();

        if (player.attributePoints < 1) {
            panel_AttributesActions.setVisible(false);
        }

        if (storylineIndex == 2 && textIndex == 0) {

            panel_Attributes.setVisible(false);

            nextDialogueArray();
        }

    }//GEN-LAST:event_button_AttributesConfirmActionPerformed

    private void button_AttributesResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesResetActionPerformed

        resetAttributeChanges();
        openAttributesMenu();


    }//GEN-LAST:event_button_AttributesResetActionPerformed

    private void resetAttributeChanges() {

        label_HPAddition.setText("");
        label_APAddition.setText("");
        label_IPAddition.setText("");
        label_DPAddition.setText("");
        label_SPAddition.setText("");

        player.resetAttributeChanges();
    }

    // opens the attributes menu
    private void button_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_StatusActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        openAttributesMenu();

    }//GEN-LAST:event_button_StatusActionPerformed

    // return to game menu (appears in travel, inventory, and attributes/ status menu)
    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed

        travelToLocation(currentLocation);

    }//GEN-LAST:event_button_ReturnActionPerformed

    // adds an attribute point to the selected attribute (given as a String parameter)
    private void attributeAddition(String attribute) {

        player.attributePoints--;

        player.attributeAddition(attribute);

        setAttributesAddition();

    }

    // finalizes the attributes added once confirmed has been selected
    private void setAttributesAddition() {
        panel_Attributes.setVisible(true);
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        panel_AttributesAddition.setVisible(true);

        if (player.HPAddition > 0) {
            label_HPAddition.setText(String.format("+%s", String.valueOf(player.HPAddition)));
            label_HPAddition.setVisible(true);
        } else {
            label_HPAddition.setVisible(false);
        }
        if (player.APAddition > 0) {
            label_APAddition.setText(String.format("+%s", String.valueOf(player.APAddition)));
            label_APAddition.setVisible(true);
        } else {
            label_APAddition.setVisible(false);
        }
        if (player.IPAddition > 0) {
            label_IPAddition.setText(String.format("+%s", String.valueOf(player.IPAddition)));
            label_IPAddition.setVisible(true);
        } else {
            label_IPAddition.setVisible(false);
        }
        if (player.DPAddition > 0) {
            label_DPAddition.setText(String.format("+%s", String.valueOf(player.DPAddition)));
            label_DPAddition.setVisible(true);
        } else {
            label_DPAddition.setVisible(false);
        }
        if (player.SPAddition > 0) {
            label_SPAddition.setText(String.format("+%s", String.valueOf(player.SPAddition)));
            label_SPAddition.setVisible(true);
        } else {
            label_SPAddition.setVisible(false);
        }

        showAvailableAttributePoints();
    }

    public void showAvailableAttributePoints() {

        if (player.attributePoints > 0
                || player.HPAddition > 0
                || player.APAddition > 0
                || player.IPAddition > 0
                || player.DPAddition > 0
                || player.SPAddition > 0) {
            panel_AttributesActions.setVisible(true);
            panel_AttributesAdditionButtons.setVisible(true);
            panel_AttributesAddition.setVisible(true);
            panel_AttributesActions.setVisible(true);
        } else {
            panel_AttributesActions.setVisible(false);
            panel_AttributesAdditionButtons.setVisible(false);
        }
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choosing the starting gear and inventory stuff">

    private void button_IronSwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IronSwordActionPerformed

        setPlayerStartingWeapon("Iron Sword");

    }//GEN-LAST:event_button_IronSwordActionPerformed

    private void button_SimpleBowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SimpleBowActionPerformed

        setPlayerStartingWeapon("Simple Bow");

    }//GEN-LAST:event_button_SimpleBowActionPerformed

    private void button_CrudeWandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CrudeWandActionPerformed

        setPlayerStartingWeapon("Crude Wand");

    }//GEN-LAST:event_button_CrudeWandActionPerformed

    private void button_InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_InventoryActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        openInventory();

    }//GEN-LAST:event_button_InventoryActionPerformed

    private void openInventory() {
        panel_Inventory.setVisible(true);

        label_Armor.setText(String.format("Equipped Armor: %s (LVL %s)", player.equippedArmor, player.equippedArmorLVL));
        label_TotalPDef.setText(String.valueOf(player.physicalDefense));
        label_TotalMDef.setText(String.valueOf(player.magicalDefense));

        label_Weapon.setText(String.format("Equipped Armor: %s (LVL %s)", player.equippedWeapon, player.equippedWeaponLVL));
        label_TotalPDmg.setText(String.valueOf(player.physicalDamage));
        label_TotalMDmg.setText(String.valueOf(player.magicalDamage));
        label_TotalCC.setText(String.valueOf(player.critChance));

        if (player.pDefGearAddition > 0) {
            label_GearPDef.setText(String.format("(+%s)", String.valueOf(player.pDefGearAddition)));
            label_GearPDef.setVisible(true);
        } else {
            label_GearPDef.setVisible(false);
        }
        if (player.mDefGearAddition > 0) {
            label_GearMDef.setText(String.format("(+%s)", String.valueOf(player.mDefGearAddition)));
            label_GearMDef.setVisible(true);
        } else {
            label_GearMDef.setVisible(false);
        }
        if (player.pDmgGearAddition > 0) {
            label_GearPDmg.setText(String.format("(+%s)", String.valueOf(player.pDmgGearAddition)));
            label_GearPDmg.setVisible(true);
        } else {
            label_GearPDmg.setVisible(false);
        }
        if (player.mDmgGearAddition > 0) {
            label_GearMDmg.setText(String.format("(+%s)", String.valueOf(player.mDmgGearAddition)));
            label_GearMDmg.setVisible(true);
        } else {
            label_GearMDmg.setVisible(false);
        }
        if (player.cCGearAddition > 0) {
            label_GearCC.setText(String.format("(+%s)", String.valueOf(player.addedCC)));
            label_GearCC.setVisible(true);
        } else {
            label_GearCC.setVisible(false);
        }
    }

    private void setPlayerStartingWeapon(String startingWeapon) {

        player.equipGear("Leather Armor", 1, startingWeapon, 1);
        panel_StartingGear.setVisible(false);
        nextDialogueArray();

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="location places buttons stuff">
    private void button_Place3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place3ActionPerformed

        placeButtonAction("place3");

    }//GEN-LAST:event_button_Place3ActionPerformed

    private void button_Place2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place2ActionPerformed

        placeButtonAction("place2");

    }//GEN-LAST:event_button_Place2ActionPerformed

    private void button_Place1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place1ActionPerformed

        placeButtonAction("place1");

    }//GEN-LAST:event_button_Place1ActionPerformed

    private void placeButtonAction(String placeActionType) {

        switch (placeActionType) {

            case "place1" -> {

                if (currentLocation.equals("Village")) {

                    if (storylineIndex <= 6) {

                        speakToNPC(2);

                    } else if (storylineIndex <= 9) {

                        speakToNPC(2);

                    } else if (storylineIndex <= 10) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    } else if (storylineIndex <= 11) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    }

                } else if (currentLocation.equals("Grasslands")) {

                    // explore wilderness
                    exploreWilderness(currentLocation, true);

                }

            }
            case "place2" -> {

                if (currentLocation.equals("Village")) {

                    if (storylineIndex == 6) {

                        messagePopup("Talk to the Village Elder first");

                    }

                }

            }
            case "place3" -> {

                if (currentLocation.equals("Village")) {
                    if (player.currentHP < player.maxHP
                            || player.currentMP < player.maxMP) {

                        if (currentLocation.equals("Village")) {

                            returnHome("Home");

                        }

                    } else {

                        messagePopup("Full HP and MP");

                    }

                }

            }

        }

    }

    private void returnHome(String restType) {

        travelToLocation("Village");

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        panel_Home.setVisible(true);

        String homeLabelStr = "";

        switch (restType) {

            case "Home" -> {
                homeLabelStr = "<html> <p align=\"enter\">You rested nicely in your home.";
            }
            case "Defeated" -> {
                homeLabelStr = "<html> <p align=\"enter\">You were foundly barely alive and brought to your home. Though you do not know how long you have been resting..";
            }

        }

        label_HomeLabel.setText(String.format(homeLabelStr + "<br>(CLICK RETURN)</p>"));

        player.fullHeal();
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="travel stuff">
    private void travelToLocation(String locationTravelledTo) {

        button_Place1.setVisible(false);
        button_Place2.setVisible(false);
        button_Place3.setVisible(false);

        ImageIcon ii = new ImageIcon();

        // put the locations here
        switch (locationTravelledTo) {
            case "Village" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/village.jpg"));
                button_Place1.setText("Village Elder");
                button_Place1.setVisible(true);
                button_Place2.setText("Travelling Merchat");
                button_Place2.setVisible(true);
                button_Place3.setText("Home");
                button_Place3.setVisible(true);
            }
            case "Grasslands" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/grasslands.jpg"));
                button_Place1.setText("Explore");
                button_Place1.setVisible(true);
            }
        }

        Image image = (ii).getImage().getScaledInstance(label_GameBackground.getWidth(),
                label_GameBackground.getHeight(), Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        label_WildernessBackground.setIcon(ii);
        label_GameBackground.setIcon(ii);

        currentLocation = locationTravelledTo;

        label_Location.setText(String.format("Location: %s", locationTravelledTo));
        openGameScreen();

    }

    private void button_TravelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TravelActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        panel_Travel.setVisible(true);

        button_Village.setVisible(false);
        button_Grasslands.setVisible(false);

        if (storylineIndex > 7) {
            button_Village.setVisible(true);
            button_Grasslands.setVisible(true);
        }

    }//GEN-LAST:event_button_TravelActionPerformed

    private void button_VillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VillageActionPerformed

        travelToLocation("Village");

    }//GEN-LAST:event_button_VillageActionPerformed

    private void button_GrasslandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_GrasslandsActionPerformed

        travelToLocation("Grasslands");

    }//GEN-LAST:event_button_GrasslandsActionPerformed

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="dialogue menu stuff">
    private void speakToNPC(int npcIndex) {

        hideScreens();

        panel_Storyline.setVisible(true);
        label_Talker.setText(String.format("""
                                                <html>

                                                <head>
                                                <h2 align="center">
                                                %s %s
                                                </h2>
                                                </head>

                                                <body>
                                                <p align="center">
                                                %s
                                                </p>
                                                </body>

                                                </html>
                                                """, characterNames.get(npcIndex)[0],
                characterNames.get(npcIndex)[1],
                characterNames.get(npcIndex)[2]));
        label_StorylineText.setText("[CLICK TO START]");

        button_Yes.setVisible(false);

        textIndex = 0;

    }

    private void button_YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_YesActionPerformed

        switch (storylineIndex) {
            case 0:
                textField_NameField.setVisible(false);
                nextDialogueArray();
                break;
            case 1:
                panel_Storyline.setVisible(false);
                panel_AffinitiesMenu.setVisible(true);
                break;
            case 2:
                player.level = 1;
                player.chooseAffinity();

                // puts the player name in the characterNames hashmap
                characterNames.put(0, new String[]{textField_NameField.getText(), "", "Player", "m"});
                panel_Storyline.setVisible(false);
                openAttributesMenu();
                break;
            case 3:
                panel_Storyline.setVisible(false);
                panel_StartingGear.setVisible(true);
                break;
            case 4:
                nextDialogueArray();
                break;
            case 5:

                travelToLocation("Village");
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put(String.format("Talk to %s %s %s in the village.",
                                characterNames.get(2)[2], characterNames.get(2)[0], characterNames.get(2)[1]),
                                new Integer[]{0, 1});

                    }

                }, String.format("Talk to %s %s %s",
                        characterNames.get(2)[2], characterNames.get(2)[0], characterNames.get(2)[1]),
                        10, 5);

                break;
            case 6:
                nextDialogueArray();
                panel_Storyline.setVisible(false);
                generateBattle("Slime", "Madeis", 1, "Slime Armor", 1, "Body", 1, true);
                battle.escapeChance = 0;
                break;
            case 7:
                nextDialogueArray();
                break;
            case 8:

                player.fullHeal();

                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                player.receiveXPCoinsReward(quest.questXPReward, quest.questCoinsReward);
                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put(String.format("Talk to %s %s %s about your new quest.",
                                characterNames.get(2)[2], characterNames.get(2)[0], characterNames.get(2)[1]),
                                new Integer[]{0, 1});

                    }

                }, String.format("Talk to %s %s %s",
                        characterNames.get(2)[2], characterNames.get(2)[0], characterNames.get(2)[1]),
                        10, 5);

                break;
            case 9:

                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                player.receiveXPCoinsReward(quest.questXPReward, quest.questCoinsReward);
                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Slime", new Integer[]{0, 3});

                    }

                }, "Kill slimes from the grasslands.",
                        quest.generateReward(new int[]{2}, new int[]{3})[0], quest.generateReward(new int[]{2}, new int[]{3})[1]);

                break;
            case 10:

                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                player.receiveXPCoinsReward(quest.questXPReward, quest.questCoinsReward);
                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Goblin", new Integer[]{0, 2});

                    }

                }, "Kill goblins from the grasslands.",
                        quest.generateReward(new int[]{4}, new int[]{2})[0], quest.generateReward(new int[]{6}, new int[]{2})[1]);

                break;
            case 11:

                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                player.receiveXPCoinsReward(quest.questXPReward, quest.questCoinsReward);
                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Wolf", new Integer[]{0, 1});

                    }

                }, "Kill the lone wolf from the grasslands.",
                        quest.generateReward(new int[]{6}, new int[]{1})[0], quest.generateReward(new int[]{6}, new int[]{1})[1]);

                break;
            default:
                break;
        }

        button_Yes.setText("Confirm");
        button_Yes.setVisible(false);

    }//GEN-LAST:event_button_YesActionPerformed

    private void panel_StorylineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_StorylineMouseClicked

        moveDialogue();

    }//GEN-LAST:event_panel_StorylineMouseClicked

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="combat stuff">
    private void generateBattle(String battleMobName,
            String battleMobAffinity,
            int battleMobLVL,
            String battleMobArmor,
            int battleMobArmorLVL,
            String battleMobWeapon,
            int battleMobWeaponLVL, boolean forcedEncounter) {

        enemy = new Mob();
        enemy.generateMob(battleMobName, battleMobAffinity, battleMobLVL, battleMobArmor, battleMobArmorLVL, battleMobWeapon, battleMobWeaponLVL);
        battle = new Battle(player, enemy);

        if (forcedEncounter) {

            startBattle();

        }

    }

    private void startBattle() {

        hideScreens();

        panel_Combat.setVisible(true);
        panel_CombatLog.setVisible(true);
        panel_Skills.setVisible(false);

        combatTurn();

    }

    private void updateCombatScreen() {

        button_FleeCombat.setText(String.format("Flee (%.0f%%)", battle.escapeChance));

        label_CombatPlayer.setText(String.format("%s (LVL %s)", battle.player.name,
                battle.player.level));
        label_CombatHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.player.currentHP,
                battle.player.maxHP));
        label_CombatMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.player.currentMP,
                battle.player.maxMP));

        label_CombatEnemy.setText(String.format("%s (LVL %s)", battle.enemy.name,
                battle.enemy.level));
        label_EnemyHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.enemy.currentHP,
                battle.enemy.maxHP));
        label_EnemyMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.enemy.currentMP,
                battle.enemy.maxMP));

    }

    private void combatTurn() {

        button_UseAttack.setVisible(false);
        button_FleeCombat.setVisible(false);
        button_UseInventory.setVisible(false);

        if (battle.player.currentHP > 0 && battle.enemy.currentHP > 0) {

            if (battle.turns.peek().equals(battle.enemy.name)) {

                label_CombatLog.setText(battle.takeTurn(enemy.skill1, player, enemy));

            } else {

                label_CombatLog.setText(battle.player.name + "'s turn");
                button_UseAttack.setVisible(true);
                button_FleeCombat.setVisible(true);
                button_UseInventory.setVisible(true);

            }

            updateCombatScreen();

        } else {

            label_CombatLog.setText(battle.battleEnd(false));

        }

    }

    private void button_UseSkill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill1ActionPerformed

        label_CombatLog.setText(battle.takeTurn(player.skill1, enemy, player));
        button_UseAttack.setVisible(false);
        button_FleeCombat.setVisible(false);
        button_UseInventory.setVisible(false);

        button_UseAttack.setText("Attack");
        panel_CombatLog.setVisible(true);
        panel_Skills.setVisible(false);

        updateCombatScreen();

    }//GEN-LAST:event_button_UseSkill1ActionPerformed

    private void button_FleeCombatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_FleeCombatActionPerformed

        if (battle.escapeChance > 0) {

            fleeAttempt();

        }

    }//GEN-LAST:event_button_FleeCombatActionPerformed

    private void panel_CombatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_CombatMouseClicked

//        System.out.println("TEST: " + storylineIndex);
//        System.out.println("TEST: " + battle.battleEnded);
        if (battle.battleEnded) {

            player.receiveXPCoinsReward(battle.battleXPGain, battle.battleCoinGain);
            quest.updateTask(battle.enemy.name);

            if (storylineIndex == 7) {

                // post slime combat sequence
                speakToNPC(2);

            } else {

                if (player.currentHP < (player.maxHP * 0.25)
                        && player.currentHP > 0
                        && player.currentMP < (player.maxMP * 0.25)
                        && player.currentMP > 0) {

                    messagePopup("Low HP and MP");

                } else if (player.currentHP < (player.maxHP * 0.25) && player.currentHP > 0) {

                    messagePopup("Low HP");

                } else if (player.currentMP < (player.maxMP * 0.25) && player.currentMP > 0) {

                    messagePopup("Low MP");

                }

                if (player.currentHP < 0) {

                    returnHome("Defeated");

                }

                if (wilderness != null && !wilderness.wildernessEncounters.isEmpty()) {

                    wilderness.obstructed = false;
                    button_WildernessAttack.setVisible(false);
                    button_WildernessFlee.setVisible(false);
                    exploreWilderness(currentLocation, false);

                } else {

                    openGameScreen();

                }

            }

            if (player.accumulatedLVL > 0) {

                messagePopup("Level increased!");
            }

        } else {

            combatTurn();

        }

    }//GEN-LAST:event_panel_CombatMouseClicked

    private void button_UseInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseInventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_UseInventoryActionPerformed

    private void button_UseAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseAttackActionPerformed

        if (button_UseAttack.getText().equals("Attack")) {

            button_UseAttack.setText("Cancel");
            panel_CombatLog.setVisible(false);
            panel_Skills.setVisible(true);
            // <editor-fold desc="show skill buttons">
            if (player.skill1 != null) {
                button_UseSkill1.setVisible(true);
                button_UseSkill1.setText(player.skill1);
            } else {
                button_UseSkill1.setVisible(false);
            }
            if (player.skill2 != null) {
                button_UseSkill2.setVisible(true);
                button_UseSkill2.setText(player.skill2);
            } else {
                button_UseSkill2.setVisible(false);
            }
            if (player.skill3 != null) {
                button_UseSkill3.setVisible(true);
                button_UseSkill3.setText(player.skill3);
            } else {
                button_UseSkill3.setVisible(false);
            }
            if (player.skill4 != null) {
                button_UseSkill4.setVisible(true);
                button_UseSkill4.setText(player.skill4);
            } else {
                button_UseSkill4.setVisible(false);
            }
            // </editor-fold>

        } else {

            button_UseAttack.setText("Attack");
            panel_CombatLog.setVisible(true);
            panel_Skills.setVisible(false);

        }

    }//GEN-LAST:event_button_UseAttackActionPerformed

    private void button_UseSkill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_UseSkill3ActionPerformed

    private void button_UseSkill4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_UseSkill4ActionPerformed

    private void button_UseSkill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_UseSkill2ActionPerformed

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="warning/ popup stuff">
    private void messagePopup(String type) {

        String warningLabel = "";

        switch (type) {
            case "Talk to the Village Elder first" -> {
                warningLabel = "You are not ready.";
            }
            case "Level increased!" -> {
                warningLabel = String.format("""
                                             <p>
                                             %s %s %s
                                             </p>
                                             """, String.format("<br> %s >> %s", (player.level - player.accumulatedLVL), player.level),
                        String.format("<br> +%.2f XP gained.", battle.battleXPGain),
                        String.format("<br> You have acquired (+%s) attribute point(s).", player.attributePoints));
                openGameScreen();
            }
            case "Full HP and MP" -> {
                warningLabel = "Your HP and MP are both full.";
            }
            case "Low HP and MP" -> {
                warningLabel = "You are on the brink of death and your magic power stores are nearly exhausted. Going back into the wilderness at this state is utterly unadvisable.";
            }
            case "Low HP" -> {
                warningLabel = "You are on the brink of death. Going back into the wilderness at this state is utterly unadvisable.";
            }
            case "Low MP" -> {
                warningLabel = "Your magic power stores are nearly exhausted. Going back into the wilderness at this state is utterly unadvisable.";
            }
            case "Quest Incomplete" -> {
                warningLabel = "You must complete your quest before continuing.";
            }
            case "Quest Completed" -> {
                warningLabel = String.format("""
                                             <p align="center">
                                             %s %s
                                             </p>
                                             """, quest.questName,
                        String.format("<br>Rewards Received: +%.2f XP +%s", quest.questXPReward, quest.generateCoinsRewardString()));
                openGameScreen();
            }

            // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
            // unused for now
            // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
            case "You have encountered Baron %s's encampment." -> {
                warningLabel = "You may attack and bring their reign of terror down whenever you are ready.";
            }
        }

        button_Place1.setEnabled(false);
        button_Place2.setEnabled(false);
        button_Place3.setEnabled(false);
        button_Travel.setEnabled(false);
        button_Inventory.setEnabled(false);
        button_Status.setEnabled(false);
        button_WildernessAttack.setEnabled(false);
        button_WildernessFlee.setEnabled(false);

        panel_Main.setComponentZOrder(panel_Warning, 0);
        panel_Warning.setVisible(true);

        label_WarningTitle.setText(type);
        label_WarningBody.setText("<html>" + warningLabel + "</html>");

    }

    private void button_CloseWarningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CloseWarningMouseClicked

        panel_Warning.setVisible(false);

        button_Place1.setEnabled(true);
        button_Place2.setEnabled(true);
        button_Place3.setEnabled(true);
        button_Travel.setEnabled(true);
        button_Inventory.setEnabled(true);
        button_Status.setEnabled(true);
        button_WildernessAttack.setEnabled(true);
        button_WildernessFlee.setEnabled(true);

    }//GEN-LAST:event_button_CloseWarningMouseClicked
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="wilderness stuff">
    private void exploreWilderness(String wildernessLocation, boolean newExploration) {

        hideScreens();

        panel_Wilderness.setVisible(true);

        if (newExploration) {
            label_WildernessLocation.setText(wildernessLocation);
            label_EncounterLog.setText("(CLICK TO START)");

            button_WildernessAttack.setVisible(false);
            button_WildernessFlee.setVisible(false);

            switch (wildernessLocation) {

                case "Grasslands" -> {

                    String[] grasslandMonsters = {"Slime", "Goblin", "Wolf"};
                    String[][] grasslandAffinities = {
                        {"Madeis", "Celeritas"},
                        {"Sanitas", "Celeritas"},
                        {"Virtus", "Sanitas"}
                    };
                    String[][] grasslandEquipment = {
                        {"Unarmored", "Body"},
                        {"Unarmored", "Club"},
                        {"Unarmored", "Mouth"}
                    };
                    String[] grasslandScenicViews = {
                        "You come across a pretty flower.",
                        "You come across a lonesome tree.",
                        "The tall grass blades dance along the wind.",
                        "A powerful gust of wind bursts across the plains.",
                        "A flock of wild sheep graze in the open field.",
                        "A swallow drifts across the sky.",
                        "A shallow creek runs along your pathway.",
                        "A lone hawk circles high above.",
                        "A breeze carries the scent of wild herbs.",
                        "The sun peeks through scattered clouds.",
                        "A butterfly flits between wildflowers.",
                        "A trail of ants marches through the dirt.",
                        "A cloud drifts lazily overhead.",
                        "The grass crunches beneath your steps.",
                        "A beetle flips onto its back, struggling.",
                        "A pair of birds chase each other midair.",
                        "Tiny yellow flowers dot the ground.",
                        "You notice paw prints in the dirt.",
                        "A warm breeze tickles your face.",
                        "The sun casts long shadows over the field.",
                        "An old, twisted root snakes along the surface.",
                        "A small hill rises gently ahead.",
                        "Distant mountains loom on the horizon.",
                        "A puddle reflects the passing clouds.",
                        "A snail crawls slowly along a blade of grass.",
                        "A gust of wind sends a swirl of dust skyward.",
                        "You see a worn path cutting through the field.",
                        "A dandelion’s seeds float through the air."
                    };

                    wilderness = new Wilderness(0,
                            grasslandMonsters,
                            grasslandAffinities,
                            grasslandEquipment,
                            grasslandScenicViews,
                            player.level);

                }

            }

        } else {

            if (wilderness.exploreTurns < 20 && !wilderness.obstructed) {

                switch (wilderness.exploreTurn()) {

                    case "Combat" -> {

                        wilderness.generateWildernessMob();

                        generateBattle(wilderness.wildernessMobName,
                                wilderness.wildernessMobAffinity,
                                wilderness.wildernessMobLVL,
                                wilderness.wildernessMobArmor,
                                wilderness.wildernessMobArmorLVL,
                                wilderness.wildernessMobWeapon,
                                wilderness.wildernessMobWeaponLVL, false);

                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h3 align="center">
                                            %s
                                            </h3>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format("Encountered %s (LVL %s)", battle.enemy.name, battle.enemy.level),
                                "Flee or attack.",
                                String.format("<br> (%s / 20 turns taken)", wilderness.exploreTurns)));

                        button_WildernessAttack.setVisible(true);
                        button_WildernessFlee.setVisible(true);
                        button_WildernessFlee.setText(String.format("Flee (%.0f%%)", battle.escapeChance));

                    }
                    case "Scenery" -> {

                        // makes sure the same scenic view is not used twice in a row
                        int newScenicViewIndex = 0;
                        while (newScenicViewIndex == wilderness.scenicViewIndex) {

                            newScenicViewIndex = gameRandomizer.nextInt(wilderness.wildernessScenicViews.length);

                        }
                        wilderness.scenicViewIndex = newScenicViewIndex;

                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h3 align="center">
                                            %s
                                            </h3>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, wilderness.wildernessScenicViews[newScenicViewIndex],
                                "(CLICK TO CONTINUE)",
                                String.format("<br> (%s / 20 turns taken)", wilderness.exploreTurns)));
                    }

                }

            } else if (wilderness.exploreTurns >= 20) {

                travelToLocation(currentLocation);

            }

        }

    }

    private void panel_WildernessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_WildernessMouseClicked

        exploreWilderness(currentLocation, false);

    }//GEN-LAST:event_panel_WildernessMouseClicked

    private void button_WildernessAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_WildernessAttackActionPerformed

        startBattle();

    }//GEN-LAST:event_button_WildernessAttackActionPerformed

    private void button_WildernessFleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_WildernessFleeActionPerformed

        fleeAttempt();

    }//GEN-LAST:event_button_WildernessFleeActionPerformed

    private void fleeAttempt() {

        panel_Combat.setVisible(false);

        if (battle.attemptFlee()) {

            startBattle();
            label_CombatLog.setText("Escape unsuccessful");

        } else {

            wilderness.obstructed = false;
            panel_Wilderness.setVisible(true);

            button_WildernessAttack.setVisible(false);
            button_WildernessFlee.setVisible(false);

            label_EncounterLog.setText(String.format("""
                    <html>

                    <head>
                    <h3 align="center">
                    %s
                    </h3>
                    </head>

                    <body>
                    <p align="center">
                    %s %s
                    </p>
                    </body>

                    </html>
                    """, "Escape Successful",
                    "(CLICK TO CONTINUE)",
                    String.format("<br> (%s / 20 turns taken)", wilderness.exploreTurns)));

        }
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="quest stuff">
    private void button_QuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_QuestActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        panel_Quest.setVisible(true);

        // used to generate the task string
        String tasksString = "";

        for (String questTaskUpdateKey : quest.QuestTasks.keySet()) {

            if (!questTaskUpdateKey.equals("Completed")) {

                tasksString += String.format("<br>%s (%s/ %s)",
                        questTaskUpdateKey,
                        quest.QuestTasks.get(questTaskUpdateKey)[0],
                        quest.QuestTasks.get(questTaskUpdateKey)[1]);

            }

        }

        label_MainQuest.setText(String.format("""
            <html>

            <head>
            <h2 align="center">
            %s
            </h2>
            </head>

            <body>
            <p align="center">
            %s %s %s
            </p>
            </body>

            </html>
            """, quest.questName,
                tasksString,
                String.format("<br>Rewards: %.2f XP, %s", quest.questXPReward, quest.generateCoinsRewardString()),
                quest.isQuestCompleted() == true ? "<br>(Completed)" : "<br>(Incomplete)")
        );

    }//GEN-LAST:event_button_QuestActionPerformed
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="html stuff (making labels)">

    /*
<html>
<body>
<p align="center">
Sanitas blesses the holders of her affinity by improving their overall constitution.
<br> (HP increases every level)
</p>
</body>
</html>
     */

 /*
<html><body><p align="center">A warrior's proven weapon. It deals great amounts of physical damage consistently, perfect for the common brawler.<br> (Blades increase strength points and physical damage)</p></body></html>
<html><body><p align="center">A mage's handy weapon. It deals magical damage, though a mage's true potential lies in their magical abilities.<br> (Wands/ staves increase intelligence points, magical damage, and magical defence)</p></body></html>
<html><body><p align="center">A ranger's reliable weapon. It deals less physical damage but improves the chances of a critical hit.<br> (Bows increase agility points and crit chances)</p></body></html>
     */
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Game().setVisible(true);
            }

        });
    }

    // <editor-fold desc="java swing variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_APAddition;
    private javax.swing.JButton button_AttributesConfirm;
    private javax.swing.JButton button_AttributesReset;
    private javax.swing.JButton button_Celeritas;
    private javax.swing.JButton button_CloseWarning;
    private javax.swing.JButton button_CrudeWand;
    private javax.swing.JButton button_DPAddition;
    private javax.swing.JButton button_FleeCombat;
    private javax.swing.JButton button_Grasslands;
    private javax.swing.JButton button_HPAddition;
    private javax.swing.JButton button_IPAddition;
    private javax.swing.JButton button_Inventory;
    private javax.swing.JButton button_IronSword;
    private javax.swing.JButton button_Madeis;
    private javax.swing.JButton button_Place1;
    private javax.swing.JButton button_Place2;
    private javax.swing.JButton button_Place3;
    private javax.swing.JButton button_Quest;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_SPAddition;
    private javax.swing.JButton button_Sanitas;
    private javax.swing.JButton button_SimpleBow;
    private javax.swing.JButton button_Status;
    private javax.swing.JButton button_Travel;
    private javax.swing.JButton button_Tutela;
    private javax.swing.JButton button_UseAttack;
    private javax.swing.JButton button_UseInventory;
    private javax.swing.JButton button_UseSkill1;
    private javax.swing.JButton button_UseSkill2;
    private javax.swing.JButton button_UseSkill3;
    private javax.swing.JButton button_UseSkill4;
    private javax.swing.JButton button_Village;
    private javax.swing.JButton button_Virtus;
    private javax.swing.JButton button_WildernessAttack;
    private javax.swing.JButton button_WildernessFlee;
    private javax.swing.JButton button_Yes;
    private javax.swing.JLabel label_APAddition;
    private javax.swing.JLabel label_AgilityPoints;
    private javax.swing.JLabel label_Armor;
    private javax.swing.JLabel label_AvailablePoints;
    private javax.swing.JLabel label_CC;
    private javax.swing.JLabel label_Celeritas;
    private javax.swing.JLabel label_Civilization;
    private javax.swing.JLabel label_CombatEnemy;
    private javax.swing.JLabel label_CombatHP;
    private javax.swing.JLabel label_CombatLog;
    private javax.swing.JLabel label_CombatMP;
    private javax.swing.JLabel label_CombatPlayer;
    private javax.swing.JLabel label_CrudeWand;
    private javax.swing.JLabel label_DPAddition;
    private javax.swing.JLabel label_Dash1;
    private javax.swing.JLabel label_Dash10;
    private javax.swing.JLabel label_Dash11;
    private javax.swing.JLabel label_Dash12;
    private javax.swing.JLabel label_Dash13;
    private javax.swing.JLabel label_Dash14;
    private javax.swing.JLabel label_Dash15;
    private javax.swing.JLabel label_Dash2;
    private javax.swing.JLabel label_Dash3;
    private javax.swing.JLabel label_Dash4;
    private javax.swing.JLabel label_Dash5;
    private javax.swing.JLabel label_Dash6;
    private javax.swing.JLabel label_Dash7;
    private javax.swing.JLabel label_Dash8;
    private javax.swing.JLabel label_Dash9;
    private javax.swing.JLabel label_DefensePoints;
    private javax.swing.JLabel label_EncounterLog;
    private javax.swing.JLabel label_EnemyHP;
    private javax.swing.JLabel label_EnemyMP;
    private javax.swing.JLabel label_GameBackground;
    private javax.swing.JLabel label_GameCurrency;
    private javax.swing.JLabel label_GameHP;
    private javax.swing.JLabel label_GameMP;
    private javax.swing.JLabel label_GameXP;
    private javax.swing.JLabel label_GearAP;
    private javax.swing.JLabel label_GearCC;
    private javax.swing.JLabel label_GearDP;
    private javax.swing.JLabel label_GearHP;
    private javax.swing.JLabel label_GearIP;
    private javax.swing.JLabel label_GearMDef;
    private javax.swing.JLabel label_GearMDmg;
    private javax.swing.JLabel label_GearPDef;
    private javax.swing.JLabel label_GearPDmg;
    private javax.swing.JLabel label_GearSP;
    private javax.swing.JLabel label_HPAddition;
    private javax.swing.JLabel label_Header;
    private javax.swing.JLabel label_HealthPoints;
    private javax.swing.JLabel label_HomeLabel;
    private javax.swing.JLabel label_IPAddition;
    private javax.swing.JLabel label_IntelligencePoints;
    private javax.swing.JLabel label_IronSword;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Location;
    private javax.swing.JLabel label_MDef;
    private javax.swing.JLabel label_MDmg;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_MainQuest;
    private javax.swing.JLabel label_PDef;
    private javax.swing.JLabel label_PDmg;
    private javax.swing.JLabel label_PlayerAffinity;
    private javax.swing.JLabel label_PlayerName;
    private javax.swing.JLabel label_SPAddition;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_SimpleBow;
    private javax.swing.JLabel label_StorylineText;
    private javax.swing.JLabel label_StrengthPoints;
    private javax.swing.JLabel label_Talker;
    private javax.swing.JLabel label_TotalAP;
    private javax.swing.JLabel label_TotalCC;
    private javax.swing.JLabel label_TotalDP;
    private javax.swing.JLabel label_TotalHP;
    private javax.swing.JLabel label_TotalIP;
    private javax.swing.JLabel label_TotalMDef;
    private javax.swing.JLabel label_TotalMDmg;
    private javax.swing.JLabel label_TotalPDef;
    private javax.swing.JLabel label_TotalPDmg;
    private javax.swing.JLabel label_TotalSP;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JLabel label_WarningBody;
    private javax.swing.JLabel label_WarningTitle;
    private javax.swing.JLabel label_Weapon;
    private javax.swing.JLabel label_Wilderness;
    private javax.swing.JLabel label_WildernessBackground;
    private javax.swing.JLabel label_WildernessLocation;
    private javax.swing.JPanel panel_AffinitiesMenu;
    private javax.swing.JPanel panel_Attributes;
    private javax.swing.JPanel panel_AttributesActions;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_AttributesAdditionButtons;
    private javax.swing.JPanel panel_Combat;
    private javax.swing.JPanel panel_CombatLog;
    private javax.swing.JPanel panel_Dashes;
    private javax.swing.JPanel panel_Dashes1;
    private javax.swing.JPanel panel_Dashes2;
    private javax.swing.JPanel panel_Game;
    private javax.swing.JPanel panel_GearAddition;
    private javax.swing.JPanel panel_Home;
    private javax.swing.JPanel panel_Inventory;
    private javax.swing.JPanel panel_LocationPanel;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JPanel panel_Quest;
    private javax.swing.JPanel panel_Skills;
    private javax.swing.JPanel panel_StartingGear;
    private javax.swing.JPanel panel_Storyline;
    private javax.swing.JPanel panel_SubGearAddition;
    private javax.swing.JPanel panel_SubTotal;
    private javax.swing.JPanel panel_Total;
    private javax.swing.JPanel panel_Travel;
    private javax.swing.JPanel panel_Warning;
    private javax.swing.JPanel panel_WarningMessage;
    private javax.swing.JPanel panel_Wilderness;
    private javax.swing.JTextField textField_NameField;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    public static void linebreak(int type) {

        int margin = 0;
        for (int i = 0; i <= type; i++) {
            if (margin > 1) {
                System.out.println();
                margin = 0;
            }
            System.out.printf("---------------------------------------");
            margin++;
        }
        System.out.println("\n");

    }

}
