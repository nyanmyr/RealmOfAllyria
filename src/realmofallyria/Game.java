
package realmofallyria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
// <editor-fold desc="XP TESTING">
class XPSystem {

    public static void main() {

        Random randomizer = new Random();

        String monsterName = "Slime";
        int monsterLVL = 50;

        int monsterAttributePoints = monsterLVL * 10;
        int monsterHP = monsterLVL * 2;
        int monsterAP = monsterLVL * 2;
        int monsterMP = monsterLVL * 2;
        int monsterDP = monsterLVL * 2;
        int monsterSP = monsterLVL * 2;

        int monsterXPRewardMin = monsterLVL * 3;
        int monsterXPRewardMax = monsterLVL * 4;

        String affinity = "Sanitas";
        switch (affinity) {
            case "Sanitas" -> {
                monsterHP += monsterLVL * 2;
            }
            case "Celeritas" -> {
                monsterAP += monsterLVL * 2;
            }
            case "Madeis" -> {
                monsterMP += monsterLVL * 2;
            }
            case "Tetula" -> {
                monsterDP += monsterLVL * 2;
            }
            case "Virtus" -> {
                monsterSP += monsterLVL * 2;
            }
        }

        for (int i = 0; i < monsterAttributePoints; i++) {

            switch (randomizer.nextInt(5)) {
                case 0 -> {
                    monsterHP += 2;
                }
                case 1 -> {
                    monsterAP += 2;
                }
                case 2 -> {
                    monsterMP += 2;
                }
                case 3 -> {
                    monsterDP += 2;
                }
                case 4 -> {
                    monsterSP += 2;
                }
            }

            monsterAttributePoints--;
        }

        System.out.println("Name: " + monsterName);
        System.out.println("Attribute Points: " + monsterAttributePoints);
        System.out.println("Monster HP: " + monsterHP);
        System.out.println("Monster AP: " + monsterAP);
        System.out.println("Monster MP: " + monsterMP);
        System.out.println("Monster DP: " + monsterDP);
        System.out.println("Monster SP: " + monsterSP);
        System.out.printf("XP Reward: %s - %s\n", monsterXPRewardMin, monsterXPRewardMax);

//        calculateXPNeeded();
    }

    public static void calculateXPNeeded() {
        int lvl = 35;
        int targetLevel = lvl + 1;
        int xpNeeded;
        System.out.printf("LVL: %s >>", lvl);

        lvl++;
        xpNeeded = lvl * 14;

        System.out.println(" " + lvl);
        System.out.printf("XP need to LVL %s: %s\n", targetLevel, xpNeeded);
    }

}

// </editor-fold>
// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
// <editor-fold desc="Mob class">
class Mob {

    Random mobRandomizer = new Random();

    String name;
    String typeAffinity;

    int level;
    int attributePoints;

    double xp;
    double xpNeeded;

    // current
    double currentHP;
    double currentMP;

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attributes stuff">
    // attributes
    int healthPoints;
    int intelligencePoints;
    int agilityPoints;
    int defensePoints;
    int strengthPoints;

    // gear attribute addition: the attributes added from certain gears
    int HPGearAddition;
    int IPGearAddition;
    int APGearAddition;
    int DPGearAddition;
    int SPGearAddition;

    // attributes addition (used in attribute menu)
    int usedAttributePoints;

    int HPAddition;
    int IPAddition;
    int APAddition;
    int DPAddition;
    int SPAddition;

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="subattributes stuff">
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

    String equippedArmor;
    int equippedArmorLVL;
    String equippedWeapon;
    int equippedWeaponLVL;

    String skill1;
    String skill2;
    String skill3;
    String skill4;

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

            Random randomizer = new Random();

            switch (randomizer.nextInt(5)) {
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

    public void chooseAffinity() {
        attributePoints = level * 10;

        // attributes
        healthPoints = level;
        agilityPoints = level;
        intelligencePoints = level;
        defensePoints = level;
        strengthPoints = level;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints += level * 2;
            }
            case "Celeritas" -> {
                agilityPoints += level * 2;
            }
            case "Madeis" -> {
                intelligencePoints += level * 2;
            }
            case "Tutela" -> {
                defensePoints += level * 2;
            }
            case "Virtus" -> {
                strengthPoints += level * 2;
            }
        }

        currentHP = healthPoints * 5;
        currentMP = intelligencePoints * 2.5;

    }

    public void attributeAddition(String affinityAddition) {
        usedAttributePoints++;

        switch (affinityAddition) {
            case "Sanitas" -> {
                HPAddition += 2;
            }
            case "Celeritas" -> {
                APAddition += 2;
            }
            case "Madeis" -> {
                IPAddition += 2;
            }
            case "Tutela" -> {
                DPAddition += 2;
            }
            case "Virtus" -> {
                SPAddition += 2;
            }
        }

    }

    public void confirmAttributeChanges() {

        usedAttributePoints = 0;

        healthPoints += HPAddition;
        intelligencePoints += IPAddition;
        agilityPoints += APAddition;
        defensePoints += DPAddition;
        strengthPoints += SPAddition;

        currentHP += HPAddition * 5;
        currentMP += IPAddition * 2.5;

        HPAddition = 0;
        IPAddition = 0;
        APAddition = 0;
        DPAddition = 0;
        SPAddition = 0;

        setSubAttributes();

    }

    public void resetAttributeChanges() {

        attributePoints += usedAttributePoints;
        usedAttributePoints = 0;

        HPAddition = 0;
        IPAddition = 0;
        APAddition = 0;
        DPAddition = 0;
        SPAddition = 0;

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
        intelligencePoints -= IPGearAddition;
        agilityPoints -= APGearAddition;
        defensePoints -= DPGearAddition;
        strengthPoints -= SPGearAddition;

        currentHP -= HPGearAddition * 5;
        currentMP -= IPGearAddition * 2.5;

        HPGearAddition = 0;
        IPGearAddition = 0;
        APGearAddition = 0;
        DPGearAddition = 0;
        SPGearAddition = 0;

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
                HPGearAddition += armorLevel * 2;
                DPGearAddition += armorLevel * 2;
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
        agilityPoints += APGearAddition;
        intelligencePoints += IPGearAddition;
        defensePoints += DPGearAddition;
        strengthPoints += SPGearAddition;

        currentHP += HPGearAddition * 5;
        currentMP += IPGearAddition * 2.5;
        scaleCritChance();

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
        double above0 = Math.min(agility, 50);
        double above50 = Math.min(Math.max(agility - 50, 0), 50);
        double above100 = Math.max(agility - 100, 0);

        return (above0 * 0.50) + (above50 * 0.25) + (above100 * 0.125);
    }

    // use skill method here
    public double[] useSkill(String skillUsed) {

        // index 0 is for pdmg, and 1 is for mdmg
        double[] damageDealt = new double[7];

        switch (skillUsed) {
            case "Tackle":
            case "Punch":
            case "Slash": {
                damageDealt[0] = physicalDamage;
                break;
            }
            case "Shoot": {
                damageDealt[0] = physicalDamage * 0.5;
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

        // is damage even greater than 0? 
//        System.out.println("PDmg: " + damageTaken[0]);
//        System.out.println("PDmg Defended: " + physicalDefense);
//        System.out.println("PDmg Total: " + (damageTaken[0] > 0 ? ((damageTaken[0] * 0.75) - physicalDefense < 0 ? (damageTaken[0] * 0.25) : (damageTaken[0] * 0.75) - physicalDefense + (damageTaken[0] * 0.25)) : 0));
//        System.out.println("///");
//        System.out.println("MDmg: " + damageTaken[1]);
//        System.out.println("MDmg Defended: " + magicalDefense);
//        System.out.println("MDmg Total: " + (damageTaken[1] > 0 ? ((damageTaken[1] * 0.75) - magicalDefense < 0 ? (damageTaken[1] * 0.25) : (damageTaken[1] * 0.75) - magicalDefense + (damageTaken[1] * 0.25)) : 0));
//        System.out.println();
        // saves the dmg inflicted
        damageTaken[5] = damageTaken[0];
        damageTaken[6] = damageTaken[1];

        // saves the dmg defended
        damageTaken[3] = physicalDefense;
        damageTaken[4] = magicalDefense;

        // saves the total damaged suffered
        damageTaken[0] = (damageTaken[0] > 0
                ? (damageTaken[0] - physicalDefense < 0
                        ? (damageTaken[0] * 0.25) : damageTaken[0] * 0.75 - physicalDefense)
                : 0);
        damageTaken[1] = (damageTaken[1] > 0
                ? (damageTaken[1] - magicalDefense < 0
                        ? (damageTaken[1] * 0.25) : damageTaken[1] * 0.75 - magicalDefense)
                : 0);

        // ensures that there is no way of increasing health from attack
        damageTaken[0] = damageTaken[0] < 0 ? 0 : damageTaken[0];
        damageTaken[1] = damageTaken[1] < 0 ? 0 : damageTaken[1];

        System.out.println("damageTaken[0]: " + damageTaken[0]);
        System.out.println("damageTaken[1]: " + damageTaken[1]);
        System.out.println();

        // reduces the health by the damage suffered
        currentHP -= damageTaken[0];
        currentHP -= damageTaken[1];

        return damageTaken;

    }

    public void xpGain(double xpGained) {

        double accumulatedXP = 0;
        int accumulatedLVL = 0;
        
        while (true) {
            
            if (xp + xpGained > xpNeeded) {
                
                accumulatedLVL++;
                xp = 0;
                xpGained -= xpNeeded;
                accumulatedXP += xpNeeded;
                
            } else {
                
                xp += xpGained;
                break;
                
            }
            
        }
        
        levelUp(accumulatedLVL);
        
    }

    private void levelUp(int newLevels) {

        level += newLevels;
        
        attributePoints += level * 10;

        // attributes
        healthPoints += level;
        agilityPoints += level;
        intelligencePoints += level;
        defensePoints += level;
        strengthPoints += level;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints += level * 2;
            }
            case "Celeritas" -> {
                agilityPoints += level * 2;
            }
            case "Madeis" -> {
                intelligencePoints += level * 2;
            }
            case "Tutela" -> {
                defensePoints += level * 2;
            }
            case "Virtus" -> {
                strengthPoints += level * 2;
            }
        }

        currentHP = healthPoints * 5;
        currentMP = intelligencePoints * 2.5;
        
    }

    // level/ xp up method here
}
// </editor-fold>
// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
// <editor-fold desc="Battle class">

class Battle {

    Queue<String> turns = new LinkedList<>();
    Random battleRandomizer = new Random();

    Mob player;
    Mob enemy;

    // the longer the battle, the more xp
    double xpTurnIncrease;
    double xpGain;

    double[] battleDamageTaken;

    int totalTurns;

    double escapeChance;

    public Battle(Mob givenPlayer, Mob givenEnemy) {

        this.player = givenPlayer;
        this.enemy = givenEnemy;
        xpGain = battleRandomizer.nextDouble(givenEnemy.level * 2, givenEnemy.level * 3);
        xpTurnIncrease = (xpGain * 0.0625);
        // this ensures that the escape chance never exceeds 90.00%
        // and never falls short short of 20.00%
        escapeChance = 20 + ((player.agilityPoints - enemy.agilityPoints) * 5) > 90 ? 90
                : 20 + ((player.agilityPoints - enemy.agilityPoints) * 5) < 20 ? 20
                        : 20 + ((player.agilityPoints - enemy.agilityPoints) * 5);
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

        xpGain += xpTurnIncrease;
        battleDamageTaken = defendingMob.defend(attackingMob.useSkill(battleSkillUsed));

        String skilluseString = "";

        switch (battleSkillUsed) {

            case "Tackle": {
                skilluseString = "%s tackled %s";
                break;
            }
            case "Punch": {
                skilluseString = "%s punched %s";
                break;
            }
            case "Slash": {
                skilluseString = "%s slashed %s";
                break;
            }
            case "Shoot": {
                skilluseString = "%s hit %s";
                break;
            }
            case "Magic Missile": {
                skilluseString = "%s fired a magic missile at %s";
                break;
            }

        }

        // no such thing as blocked damage
        String attackString = String.format(""" 
                                            <html>
                                            <p align="center">
                                            %s %s %s %s
                                            </p>
                                            </html>
                                            """, String.format(skilluseString, attackingMob.name, defendingMob.name),
                (battleDamageTaken[0] > 0 ? String.format("<br> PDmg inflicted (-%.2f HP)", battleDamageTaken[0]) : battleDamageTaken[5] > 0 ? "<br> Attacked completely blocked!" : ""),
                (battleDamageTaken[1] > 0 ? String.format("<br> MDmg inflicted (-%.2f MP)", battleDamageTaken[1]) : battleDamageTaken[6] > 0 ? "<br> Attacked completely resisted!" : ""),
                (battleDamageTaken[2] > 0 ? String.format("<br> CRITICAL HIT! (2x inflicted dmg)") : ""));

//        System.out.println(attackString);
//        System.out.println("NEXT TURN:" + turns.peek());
        totalTurns++;

        return attackString;

    }

    public String battleEnd(Boolean fled) {

        String battleEndString = "";

        if (!fled) {
            String winningMob = "";
            String defeatedMob = "";
            if (player.currentHP < 0) {
                defeatedMob = player.name;
                winningMob = enemy.name;
            } else {
                defeatedMob = enemy.name;
                winningMob = player.name;
            }

            battleEndString = String.format("""
                                               <html>
                                               <p align="center">
                                               %s %s %s
                                               </p>
                                               </html>
                                               """, String.format("<br> %s defeated %s.", winningMob, defeatedMob),
                    String.format("<br> (%s turns)", totalTurns / 2),
                    String.format("<br> +%.2f XP gained.", xpGain));

        } else {

            battleEndString = String.format("%s successfully fled from battle.", player);

        }

        return battleEndString;

    }

}
// </editor-fold>
// <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>

public class Game extends javax.swing.JFrame {

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="variables">
    int textIndex = 0;
    int dialogueIndex = 0;

    // merge with dialogue hashmap
    // intro/ opening dialogue
    String[] introDialogue = {"Welcome to the Realm of Allyria.",
        "Before your travels begin adventurer...",
        "What is your name?"};
    String[] chooseClassDialogue = {"<html>Before you begin your adventure, you must choose your affinity.",
        "<html>Affinities determine the boost you will receive a certain attribute.",
        "<html>Everytime you level up, you will receive an automatic addition to the attribute of your affinity.",
        "<html>Those who have an affinity to Sanitas have a boost in health.",
        "<html>Those who have an affinity to Celeritas have a boost in agility.",
        "<html>Those who have an affinity to Madeis have a boost in magic.",
        "<html>Those who have an affinity to Tutela have a boost in defense.",
        "<html>Those who have an affinity to Virtus have a boost in strength.",
        "<html>Now choose your affinity wisely. Your affinity will be permanent."};
    String[] editAttributesDialogue = {"<html>Now adjust your attributes wisely. (Click to continue)"};
    String[] chooseGearDialogue = {"<html>The world out there is dangerous.",
        "You will need something to defend yourself.",
        "Choose any of the following weapons to your liking..."};
    String[] bonusArmorDialogue = {"<html>This weapon is not much.",
        "So I will provide you a full set of leather armor along with it.",
        "Safe travels adventurer..."};
    String[] adventureBeginsDialogue = {"<html>Once again, welcome to the Realm of Allyria.",
        "<html>Your journey begins in a quiet village in the peaceful grasslands.",
        "<html>Word has spread that the nefarious Demon Lord %s of the Nether Realm has launched an invasion.",
        "<html>Along the way they kidnapped the kingdom's own Princess %s.",
        "<html>Thus it is now your task to venture across the kingdom and enter the Nether Realm to vanquish the evil army of the Demon Lord...",
        "<html>... and save the princess.",
        "However...",
        "All adventures begin with humble beginnings...",
        "I am the Village Elder of the village in which you reside.",
        "Come talk to me so that you may learn how to fight."};
    String[] startingVillageElderDialogue = {"<html>Greetings, %s.",
        "As you know, the world is in peril as of now.",
        "The kingdom's forces are engaged in a fierce war againts the demons of the Nether Continent.",
        "Most importantly princess %s has been captured.",
        "That is why time is of the essence, so I will teach you quickly.",
        "Your meager %s will not do you any good against even the weakest kind of demon.",
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
        "I do not expect you to die against this slime, but please do not surprise me.",
        "I cannot let you flee combat.",
        "You must either defeat it or be defeated.",
        "Defeat the slime and complete your training.",};

    String playerName = "";
    Mob player;

    String currentLocation = "";

    // turn to int?
    boolean introSequenceFinished = false;

    // use int instead?
    String[] unlockedLocations = new String[10];

    // indicates whether the dialogue menu is opened.
    boolean talkingToNPC = false;

    Mob enemy;

    Battle battle;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------

    public Game() {

        // debugging stuff
        dialogueIndex = 6;

        player = new Mob();
        player.generateMob("Bashame",
                "Celeritas", 1,
                "Leather Armor", 1,
                "Simple Bow", 4);
//        player.attributePoints += 1000;
// player.generateMob("Saitama", "Madeis", 50, "DEBUG", 1, "DEBUG", 1);

        initComponents();

        textField_NameField.setVisible(false);
        button_DialogueConfirm.setVisible(false);
        panel_ClassMenu.setVisible(false);
        panel_Attributes.setVisible(false);
        panel_StartingGear.setVisible(false);
        panel_Game.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Travel.setVisible(false);
        button_Return.setVisible(false);
        panel_Home.setVisible(false);
        panel_Dialogue.setVisible(false);
        panel_Combat.setVisible(false);

    }

    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    // unusued for now
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    private String generateName(String type) {
        // use linked list

        String generatedName = "";

        return generatedName;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Main = new javax.swing.JPanel();
        button_Return = new javax.swing.JButton();
        label_Header = new javax.swing.JLabel();
        textField_NameField = new javax.swing.JTextField();
        button_DialogueConfirm = new javax.swing.JButton();
        panel_Attributes = new javax.swing.JPanel();
        panel_AttributesActions = new javax.swing.JPanel();
        button_AttributesConfirm = new javax.swing.JButton();
        button_AttributesReset = new javax.swing.JButton();
        label_PlayerName = new javax.swing.JLabel();
        label_PlayerAffinity = new javax.swing.JLabel();
        label_Level = new javax.swing.JLabel();
        label_AvailablePoints = new javax.swing.JLabel();
        label_HealthPoints = new javax.swing.JLabel();
        label_AgilityPoints = new javax.swing.JLabel();
        label_IntelligencePoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
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
        label_GearAP = new javax.swing.JLabel();
        label_GearIP = new javax.swing.JLabel();
        label_GearDP = new javax.swing.JLabel();
        label_GearSP = new javax.swing.JLabel();
        panel_AttributesAddition = new javax.swing.JPanel();
        label_HPAddition = new javax.swing.JLabel();
        label_APAddition = new javax.swing.JLabel();
        label_IPAddition = new javax.swing.JLabel();
        label_DPAddition = new javax.swing.JLabel();
        label_SPAddition = new javax.swing.JLabel();
        panel_Total = new javax.swing.JPanel();
        label_TotalHP = new javax.swing.JLabel();
        label_TotalAP = new javax.swing.JLabel();
        label_TotalIP = new javax.swing.JLabel();
        label_TotalDP = new javax.swing.JLabel();
        label_TotalSP = new javax.swing.JLabel();
        panel_AttributesAdditionButtons = new javax.swing.JPanel();
        button_HPAddition = new javax.swing.JButton();
        button_APAddition = new javax.swing.JButton();
        button_IPAddition = new javax.swing.JButton();
        button_DPAddition = new javax.swing.JButton();
        button_SPAddition = new javax.swing.JButton();
        panel_Game = new javax.swing.JPanel();
        label_GameXP = new javax.swing.JLabel();
        label_GameMP = new javax.swing.JLabel();
        label_GameHP = new javax.swing.JLabel();
        label_Location = new javax.swing.JLabel();
        label_Travel = new javax.swing.JButton();
        label_Inventory = new javax.swing.JButton();
        label_Status = new javax.swing.JButton();
        panel_Village = new javax.swing.JPanel();
        button_VillageElder = new javax.swing.JButton();
        buttonl_TravellingMerchant = new javax.swing.JButton();
        button_Home = new javax.swing.JButton();
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
        panel_Dialogue = new javax.swing.JPanel();
        label_Talker = new javax.swing.JLabel();
        label_Dialogue = new javax.swing.JLabel();
        button_Yes = new javax.swing.JButton();
        button_No = new javax.swing.JButton();
        panel_Travel = new javax.swing.JPanel();
        button_Village = new javax.swing.JButton();
        button_Grasslands = new javax.swing.JButton();
        label_Wilderness = new javax.swing.JLabel();
        label_Civilization = new javax.swing.JLabel();
        panel_Home = new javax.swing.JPanel();
        button_Rest = new javax.swing.JButton();
        label_Home = new javax.swing.JLabel();
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
        panel_StartingGear = new javax.swing.JPanel();
        button_IronSword = new javax.swing.JButton();
        label_IronSword = new javax.swing.JLabel();
        button_SimpleBow = new javax.swing.JButton();
        label_SimpleBow = new javax.swing.JLabel();
        button_CrudeWand = new javax.swing.JButton();
        label_CrudeWand = new javax.swing.JLabel();
        panel_ClassMenu = new javax.swing.JPanel();
        button_Sanitas = new javax.swing.JButton();
        label_Sanitas = new javax.swing.JLabel();
        button_Celeritas = new javax.swing.JButton();
        label_Celeritas = new javax.swing.JLabel();
        button_Madeis = new javax.swing.JButton();
        label_Madeis = new javax.swing.JLabel();
        button_Tutela = new javax.swing.JButton();
        label_Tutela = new javax.swing.JLabel();
        button_Virtus = new javax.swing.JButton();
        label_Virtus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        button_Return.setBounds(420, 90, 100, 23);

        label_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Header.setText("REALM OF ALLYRIA (CLICK TO START)");
        label_Header.setToolTipText("");
        panel_Main.add(label_Header);
        label_Header.setBounds(6, 6, 520, 47);

        textField_NameField.setText("Adventurer");
        panel_Main.add(textField_NameField);
        textField_NameField.setBounds(6, 59, 520, 22);

        button_DialogueConfirm.setText("Confirm");
        button_DialogueConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DialogueConfirmActionPerformed(evt);
            }
        });
        panel_Main.add(button_DialogueConfirm);
        button_DialogueConfirm.setBounds(6, 97, 160, 23);

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

        label_AgilityPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_AgilityPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AgilityPoints.setText("Agility Points:");
        panel_Attributes.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 170, 130, 30);

        label_IntelligencePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_IntelligencePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_IntelligencePoints.setText("Intelligence Points:");
        panel_Attributes.add(label_IntelligencePoints);
        label_IntelligencePoints.setBounds(10, 200, 130, 30);

        label_DefensePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DefensePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_DefensePoints.setText("Defense Points:");
        panel_Attributes.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 230, 130, 30);

        label_StrengthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StrengthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_StrengthPoints.setText("Strength Points:");
        panel_Attributes.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 260, 130, 30);

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

        label_GearAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearAP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearAP.setText("(+0)");
        panel_GearAddition.add(label_GearAP);
        label_GearAP.setBounds(10, 30, 40, 30);

        label_GearIP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearIP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearIP.setText("(+0)");
        panel_GearAddition.add(label_GearIP);
        label_GearIP.setBounds(10, 60, 40, 30);

        label_GearDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearDP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearDP.setText("(+0)");
        panel_GearAddition.add(label_GearDP);
        label_GearDP.setBounds(10, 90, 40, 30);

        label_GearSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearSP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearSP.setText("(+0)");
        panel_GearAddition.add(label_GearSP);
        label_GearSP.setBounds(10, 120, 40, 30);

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

        label_APAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_APAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_APAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_APAddition.setText("(+0)");
        panel_AttributesAddition.add(label_APAddition);
        label_APAddition.setBounds(10, 30, 40, 30);

        label_IPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_IPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_IPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_IPAddition);
        label_IPAddition.setBounds(10, 60, 40, 30);

        label_DPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_DPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_DPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_DPAddition);
        label_DPAddition.setBounds(10, 90, 40, 30);

        label_SPAddition.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_SPAddition.setForeground(new java.awt.Color(221, 221, 222));
        label_SPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_SPAddition);
        label_SPAddition.setBounds(10, 120, 40, 30);

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

        label_TotalAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalAP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalAP.setText("0");
        panel_Total.add(label_TotalAP);
        label_TotalAP.setBounds(10, 30, 40, 30);

        label_TotalIP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalIP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalIP.setText("0");
        panel_Total.add(label_TotalIP);
        label_TotalIP.setBounds(10, 60, 40, 30);

        label_TotalDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalDP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalDP.setText("0");
        panel_Total.add(label_TotalDP);
        label_TotalDP.setBounds(10, 90, 40, 30);

        label_TotalSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalSP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalSP.setText("0");
        panel_Total.add(label_TotalSP);
        label_TotalSP.setBounds(10, 120, 40, 30);

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

        button_APAddition.setText("<html>+");
        button_APAddition.setToolTipText("");
        button_APAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_APAdditionAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_APAddition);
        button_APAddition.setBounds(20, 30, 50, 30);

        button_IPAddition.setText("<html>+");
        button_IPAddition.setToolTipText("");
        button_IPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_IPAddition);
        button_IPAddition.setBounds(20, 60, 50, 30);

        button_DPAddition.setText("<html>+");
        button_DPAddition.setToolTipText("");
        button_DPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_DPAddition);
        button_DPAddition.setBounds(20, 90, 50, 30);

        button_SPAddition.setText("<html>+");
        button_SPAddition.setToolTipText("");
        button_SPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_SPAddition);
        button_SPAddition.setBounds(20, 120, 50, 30);

        panel_Attributes.add(panel_AttributesAdditionButtons);
        panel_AttributesAdditionButtons.setBounds(420, 140, 90, 150);

        panel_Main.add(panel_Attributes);
        panel_Attributes.setBounds(5, 130, 520, 300);

        panel_Game.setBackground(new java.awt.Color(69, 69, 69));
        panel_Game.setLayout(null);

        label_GameXP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameXP.setForeground(new java.awt.Color(221, 221, 222));
        label_GameXP.setText("XP:");
        panel_Game.add(label_GameXP);
        label_GameXP.setBounds(10, 140, 240, 40);

        label_GameMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameMP.setForeground(new java.awt.Color(221, 221, 222));
        label_GameMP.setText("MP:");
        panel_Game.add(label_GameMP);
        label_GameMP.setBounds(10, 100, 240, 40);

        label_GameHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameHP.setForeground(new java.awt.Color(221, 221, 222));
        label_GameHP.setText("HP:");
        panel_Game.add(label_GameHP);
        label_GameHP.setBounds(10, 60, 240, 40);

        label_Location.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Location.setForeground(new java.awt.Color(221, 221, 222));
        label_Location.setText("Location: ");
        panel_Game.add(label_Location);
        label_Location.setBounds(10, 10, 240, 40);

        label_Travel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Travel.setText("Travel");
        label_Travel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_TravelActionPerformed(evt);
            }
        });
        panel_Game.add(label_Travel);
        label_Travel.setBounds(10, 250, 150, 40);

        label_Inventory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Inventory.setText("Inventory");
        label_Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_InventoryActionPerformed(evt);
            }
        });
        panel_Game.add(label_Inventory);
        label_Inventory.setBounds(180, 250, 150, 40);

        label_Status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Status.setText("Status");
        label_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_StatusActionPerformed(evt);
            }
        });
        panel_Game.add(label_Status);
        label_Status.setBounds(360, 250, 150, 40);

        panel_Village.setBackground(new java.awt.Color(63, 63, 63));
        panel_Village.setLayout(null);

        button_VillageElder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_VillageElder.setText("Village Elder");
        button_VillageElder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VillageElderActionPerformed(evt);
            }
        });
        panel_Village.add(button_VillageElder);
        button_VillageElder.setBounds(10, 10, 220, 30);

        buttonl_TravellingMerchant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonl_TravellingMerchant.setText("Travelling Merchant");
        buttonl_TravellingMerchant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonl_TravellingMerchantActionPerformed(evt);
            }
        });
        panel_Village.add(buttonl_TravellingMerchant);
        buttonl_TravellingMerchant.setBounds(10, 50, 220, 30);

        button_Home.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Home.setText("Home");
        button_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HomeActionPerformed(evt);
            }
        });
        panel_Village.add(button_Home);
        button_Home.setBounds(10, 90, 220, 30);

        panel_Game.add(panel_Village);
        panel_Village.setBounds(270, 10, 240, 230);

        panel_Main.add(panel_Game);
        panel_Game.setBounds(5, 130, 520, 300);

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
        label_CombatLog.setBounds(0, 0, 480, 100);

        panel_Combat.add(panel_CombatLog);
        panel_CombatLog.setBounds(20, 130, 480, 100);

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
        panel_Combat.setBounds(5, 130, 520, 300);

        panel_Dialogue.setBackground(new java.awt.Color(69, 69, 69));
        panel_Dialogue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_DialogueMouseClicked(evt);
            }
        });
        panel_Dialogue.setLayout(null);

        label_Talker.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_Talker.setForeground(new java.awt.Color(221, 221, 222));
        label_Talker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Talker.setText("Talker");
        panel_Dialogue.add(label_Talker);
        label_Talker.setBounds(180, 10, 150, 40);

        label_Dialogue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Dialogue.setForeground(new java.awt.Color(221, 221, 222));
        label_Dialogue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dialogue.setText("Talker");
        panel_Dialogue.add(label_Dialogue);
        label_Dialogue.setBounds(30, 60, 460, 100);

        button_Yes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Yes.setText("Yes");
        button_Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_YesActionPerformed(evt);
            }
        });
        panel_Dialogue.add(button_Yes);
        button_Yes.setBounds(180, 170, 150, 40);

        button_No.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_No.setText("No");
        button_No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_NoActionPerformed(evt);
            }
        });
        panel_Dialogue.add(button_No);
        button_No.setBounds(180, 220, 150, 40);

        panel_Main.add(panel_Dialogue);
        panel_Dialogue.setBounds(5, 130, 520, 300);

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
        panel_Travel.setBounds(5, 130, 520, 300);

        panel_Home.setBackground(new java.awt.Color(69, 69, 69));
        panel_Home.setLayout(null);

        button_Rest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button_Rest.setText("Rest");
        button_Rest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_RestActionPerformed(evt);
            }
        });
        panel_Home.add(button_Rest);
        button_Rest.setBounds(150, 90, 220, 30);

        label_Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Home.setText("Restore HP and MP");
        panel_Home.add(label_Home);
        label_Home.setBounds(150, 10, 220, 70);

        panel_Main.add(panel_Home);
        panel_Home.setBounds(5, 130, 520, 300);

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
        panel_Inventory.setBounds(5, 130, 520, 300);

        panel_StartingGear.setBackground(new java.awt.Color(69, 69, 69));
        panel_StartingGear.setLayout(null);

        button_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Iron Sword</p>  </body> </html>");
        button_IronSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IronSwordActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_IronSword);
        button_IronSword.setBounds(120, 10, 80, 50);

        label_IronSword.setForeground(new java.awt.Color(221, 221, 222));
        label_IronSword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Healers mainly deal with restoring health which makes them very good at surviving.</p>  </body> </html>");
        label_IronSword.setToolTipText("");
        panel_StartingGear.add(label_IronSword);
        label_IronSword.setBounds(120, 60, 76, 207);

        button_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Simple Bow</p>  </body> </html>");
        button_SimpleBow.setToolTipText("");
        button_SimpleBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SimpleBowActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_SimpleBow);
        button_SimpleBow.setBounds(230, 10, 76, 50);

        label_SimpleBow.setForeground(new java.awt.Color(221, 221, 222));
        label_SimpleBow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Rogues prefer to have the quickest wit and initiative when it comes to battle.</p>  </body> </html>");
        label_SimpleBow.setToolTipText("");
        panel_StartingGear.add(label_SimpleBow);
        label_SimpleBow.setBounds(230, 60, 76, 207);

        button_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Crude Wand</p>  </body> </html>");
        button_CrudeWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CrudeWandActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_CrudeWand);
        button_CrudeWand.setBounds(340, 10, 76, 50);

        label_CrudeWand.setForeground(new java.awt.Color(221, 221, 222));
        label_CrudeWand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_CrudeWand.setToolTipText("");
        panel_StartingGear.add(label_CrudeWand);
        label_CrudeWand.setBounds(340, 60, 76, 207);

        panel_Main.add(panel_StartingGear);
        panel_StartingGear.setBounds(5, 130, 520, 300);

        panel_ClassMenu.setBackground(new java.awt.Color(69, 69, 69));
        panel_ClassMenu.setLayout(null);

        button_Sanitas.setText("<html>Sanitas");
        button_Sanitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SanitasActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Sanitas);
        button_Sanitas.setBounds(10, 10, 72, 23);

        label_Sanitas.setForeground(new java.awt.Color(221, 221, 222));
        label_Sanitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Sanitas.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Healers mainly deal with restoring health which makes them very good at surviving.</p>  </body> </html>");
        label_Sanitas.setToolTipText("");
        panel_ClassMenu.add(label_Sanitas);
        label_Sanitas.setBounds(6, 39, 76, 207);

        button_Celeritas.setText("<html>Celeritas");
        button_Celeritas.setToolTipText("");
        button_Celeritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CeleritasActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Celeritas);
        button_Celeritas.setBounds(110, 10, 76, 23);

        label_Celeritas.setForeground(new java.awt.Color(221, 221, 222));
        label_Celeritas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Celeritas.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Rogues prefer to have the quickest wit and initiative when it comes to battle.</p>  </body> </html>");
        label_Celeritas.setToolTipText("");
        panel_ClassMenu.add(label_Celeritas);
        label_Celeritas.setBounds(110, 40, 76, 207);

        button_Madeis.setText("<html>Madeis");
        button_Madeis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MadeisActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Madeis);
        button_Madeis.setBounds(220, 10, 72, 23);

        label_Madeis.setForeground(new java.awt.Color(221, 221, 222));
        label_Madeis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Madeis.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_Madeis.setToolTipText("");
        panel_ClassMenu.add(label_Madeis);
        label_Madeis.setBounds(220, 40, 76, 207);

        button_Tutela.setText("<html>Tutela");
        button_Tutela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TutelaActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Tutela);
        button_Tutela.setBounds(330, 10, 72, 23);

        label_Tutela.setForeground(new java.awt.Color(221, 221, 222));
        label_Tutela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutela.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_Tutela.setToolTipText("");
        panel_ClassMenu.add(label_Tutela);
        label_Tutela.setBounds(330, 40, 76, 207);

        button_Virtus.setText("Virtus");
        button_Virtus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VirtusActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Virtus);
        button_Virtus.setBounds(430, 10, 72, 23);

        label_Virtus.setForeground(new java.awt.Color(221, 221, 222));
        label_Virtus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Virtus.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Warriors focus on striking hard through brute strength.</p>  </body> </html>");
        label_Virtus.setToolTipText("");
        panel_ClassMenu.add(label_Virtus);
        label_Virtus.setBounds(430, 40, 76, 207);

        panel_Main.add(panel_ClassMenu);
        panel_ClassMenu.setBounds(5, 130, 520, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="opening dialogue cutscene stuff">

    private void panel_MainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_MainMouseClicked

        if (!introSequenceFinished) {

            moveDialogue();

        }

    }//GEN-LAST:event_panel_MainMouseClicked

    private void moveDialogue() {

        String[] loadedDialoge = new String[0];

        // determine which dialogue array to load
        switch (dialogueIndex) {
            case 0 -> {
                loadedDialoge = introDialogue;
            }
            case 1 -> {
                loadedDialoge = chooseClassDialogue;
            }
            case 2 -> {
                loadedDialoge = editAttributesDialogue;
            }
            case 3 -> {
                loadedDialoge = chooseGearDialogue;
            }
            case 4 -> {
                loadedDialoge = bonusArmorDialogue;
            }
            case 5 -> {
                loadedDialoge = adventureBeginsDialogue;
            }
            case 7 -> {
                loadedDialoge = startingVillageElderDialogue;
            }
        }

        if (dialogueIndex < 6 && textIndex < 9) {

            // determine the text in the header
            if (dialogueIndex == 5 && textIndex == 2) {
                label_Header.setText(String.format(loadedDialoge[textIndex], "DEMON NAME"));
            } else if (dialogueIndex == 5 && textIndex == 3) {
                label_Header.setText(String.format(loadedDialoge[textIndex], "PRINCESS NAME"));
            } else {
                label_Header.setText(loadedDialoge[textIndex]);
            }

            if (dialogueIndex == 0 && textIndex == 2) {
                textField_NameField.setVisible(true);
                button_DialogueConfirm.setVisible(true);
            } else if (dialogueIndex == 1 && textIndex == 8) {
                panel_ClassMenu.setVisible(true);
                player = new Mob();
                player.name = playerName;
            } else if (dialogueIndex == 2 && textIndex == 0 && !panel_Attributes.isVisible()) {
                player.chooseAffinity();
                label_PlayerName.setText(player.name);
                label_PlayerAffinity.setText(player.typeAffinity);
                openAttributesMenu();
            } else if (dialogueIndex == 3 && textIndex == 2) {
                panel_StartingGear.setVisible(true);
            } else if (dialogueIndex == 4 && textIndex == 2) {
                button_DialogueConfirm.setVisible(true);
                button_DialogueConfirm.setText("<html>Begin Adventure");
            }

            if (textIndex < loadedDialoge.length - 1) {
                textIndex++;
            }

        } else if (talkingToNPC) {

            label_Dialogue.setText(loadedDialoge[textIndex]);

            // put back to normal: (dialogueIndex == 7 && textIndex == 25)
            if (dialogueIndex == 7 && textIndex == 1) {

                // method this
                enemy = new Mob();
                enemy.generateMob("Slime", "Madeis", 1, "Slime Armor", 1, "Body", 1);
                battle = new Battle(player, enemy);
                battle.escapeChance = 0;

                // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
                // put in a method to hide everything
                // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
                button_Return.setVisible(false);
                panel_Home.setVisible(false);
                panel_Dialogue.setVisible(false);
                panel_Travel.setVisible(false);
                panel_Inventory.setVisible(false);
                panel_Attributes.setVisible(false);
                button_Return.setVisible(false);
                panel_Home.setVisible(false);

                panel_Combat.setVisible(true);
                panel_CombatLog.setVisible(true);
                panel_Skills.setVisible(false);

                combatTurn();

            }

            if (textIndex < loadedDialoge.length - 1) {
                textIndex++;
            }

        } else if (dialogueIndex < 7) {

            label_Header.setText("REALM OF ALLYRIA (v0.4)");
            travelToLocation("Village");
            unlockedLocations[0] = "Village";
            introSequenceFinished = true;
            nextDialogueArray();

        }

    }

    private void button_DialogueConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DialogueConfirmActionPerformed

        // ask for adventurer name
        if (dialogueIndex == 0 && textIndex == 2) {
            playerName = textField_NameField.getText();

            textField_NameField.setVisible(false);

        }

        button_DialogueConfirm.setVisible(false);
        nextDialogueArray();


    }//GEN-LAST:event_button_DialogueConfirmActionPerformed

    private void nextDialogueArray() {

        textIndex = 0;
        dialogueIndex++;
        moveDialogue();

    }

    private void openGameScreen() {

        label_GameHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", player.currentHP,
                (double) player.healthPoints * 5));
        label_GameMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", player.currentMP,
                (double) player.intelligencePoints * 2.5));
        label_GameXP.setText(String.format("Experience Points (XP): %.2f / %.2f\n", player.xp,
                player.xpNeeded));

        // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        // method this
        // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        button_Return.setVisible(false);
        panel_Home.setVisible(false);
        panel_Dialogue.setVisible(false);
        panel_Travel.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Attributes.setVisible(false);
        button_Return.setVisible(false);
        panel_Home.setVisible(false);

        panel_Game.setVisible(true);
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choosing the starting gear stuff">

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
        panel_ClassMenu.setVisible(false);
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

        if (dialogueIndex == 2 && textIndex == 0) {

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
    private void label_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_StatusActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        openAttributesMenu();

    }//GEN-LAST:event_label_StatusActionPerformed

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
        label_Level.setText(String.format("Level: %s\n", String.valueOf(player.level)));
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

        if (player.attributePoints > 0) {
            panel_AttributesAdditionButtons.setVisible(true);
            panel_AttributesAddition.setVisible(true);
            panel_AttributesActions.setVisible(true);
        } else {
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

    private void label_InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_InventoryActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        openInventory();

    }//GEN-LAST:event_label_InventoryActionPerformed

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
    // <editor-fold desc="travel stuff">
    private void travelToLocation(String locationTravelledTo) {

        panel_Village.setVisible(false);

        // put the locations here
        switch (locationTravelledTo) {
            case "Village" -> {
                panel_Village.setVisible(true);
            }
        }

        currentLocation = locationTravelledTo;

        label_Location.setText(String.format("Location: %s", locationTravelledTo));
        openGameScreen();

    }

    private void button_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HomeActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        panel_Home.setVisible(true);

    }//GEN-LAST:event_button_HomeActionPerformed

    private void buttonl_TravellingMerchantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonl_TravellingMerchantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonl_TravellingMerchantActionPerformed

    private void button_VillageElderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VillageElderActionPerformed

        panel_Game.setVisible(false);
        panel_Dialogue.setVisible(true);
        label_Talker.setText("Village Elder");
        label_Dialogue.setText("");
        button_Yes.setVisible(false);
        button_No.setVisible(false);
        talkingToNPC = true;

    }//GEN-LAST:event_button_VillageElderActionPerformed

    private void button_RestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_RestActionPerformed


    }//GEN-LAST:event_button_RestActionPerformed

    private void label_TravelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_TravelActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        panel_Travel.setVisible(true);

        if (unlockedLocations[0] != null) {
            button_Village.setVisible(true);

        } else {
            button_Village.setVisible(false);

        }

        if (unlockedLocations[1] != null) {
            button_Grasslands.setVisible(true);

        } else {
            button_Grasslands.setVisible(false);

        }


    }//GEN-LAST:event_label_TravelActionPerformed

    private void button_VillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VillageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_VillageActionPerformed

    private void button_GrasslandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_GrasslandsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_GrasslandsActionPerformed

    private void button_YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_YesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_YesActionPerformed

    private void button_NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_NoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_NoActionPerformed

    private void panel_DialogueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_DialogueMouseClicked

        moveDialogue();

    }//GEN-LAST:event_panel_DialogueMouseClicked

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="combat stuff">
    private void updateCombatScreen() {

        button_FleeCombat.setText(String.format("Flee (%.0f%%)", battle.escapeChance));

        label_CombatPlayer.setText(String.format("%s (LVL %s)", battle.player.name,
                battle.player.level));
        label_CombatHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.player.currentHP,
                (double) battle.player.healthPoints * 5));
        label_CombatMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.player.currentMP,
                (double) battle.player.intelligencePoints * 2.5));

        label_CombatEnemy.setText(String.format("%s (LVL %s)", battle.enemy.name,
                battle.enemy.level));
        label_EnemyHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.enemy.currentHP,
                (double) battle.enemy.healthPoints * 5));
        label_EnemyMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.enemy.currentMP,
                (double) battle.enemy.intelligencePoints * 2.5));

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
        // TODO add your handling code here:
    }//GEN-LAST:event_button_FleeCombatActionPerformed

    private void panel_CombatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_CombatMouseClicked

        combatTurn();

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
    private javax.swing.JButton button_CrudeWand;
    private javax.swing.JButton button_DPAddition;
    private javax.swing.JButton button_DialogueConfirm;
    private javax.swing.JButton button_FleeCombat;
    private javax.swing.JButton button_Grasslands;
    private javax.swing.JButton button_HPAddition;
    private javax.swing.JButton button_Home;
    private javax.swing.JButton button_IPAddition;
    private javax.swing.JButton button_IronSword;
    private javax.swing.JButton button_Madeis;
    private javax.swing.JButton button_No;
    private javax.swing.JButton button_Rest;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_SPAddition;
    private javax.swing.JButton button_Sanitas;
    private javax.swing.JButton button_SimpleBow;
    private javax.swing.JButton button_Tutela;
    private javax.swing.JButton button_UseAttack;
    private javax.swing.JButton button_UseInventory;
    private javax.swing.JButton button_UseSkill1;
    private javax.swing.JButton button_UseSkill2;
    private javax.swing.JButton button_UseSkill3;
    private javax.swing.JButton button_UseSkill4;
    private javax.swing.JButton button_Village;
    private javax.swing.JButton button_VillageElder;
    private javax.swing.JButton button_Virtus;
    private javax.swing.JButton button_Yes;
    private javax.swing.JButton buttonl_TravellingMerchant;
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
    private javax.swing.JLabel label_Dialogue;
    private javax.swing.JLabel label_EnemyHP;
    private javax.swing.JLabel label_EnemyMP;
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
    private javax.swing.JLabel label_Home;
    private javax.swing.JLabel label_IPAddition;
    private javax.swing.JLabel label_IntelligencePoints;
    private javax.swing.JButton label_Inventory;
    private javax.swing.JLabel label_IronSword;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Location;
    private javax.swing.JLabel label_MDef;
    private javax.swing.JLabel label_MDmg;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_PDef;
    private javax.swing.JLabel label_PDmg;
    private javax.swing.JLabel label_PlayerAffinity;
    private javax.swing.JLabel label_PlayerName;
    private javax.swing.JLabel label_SPAddition;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_SimpleBow;
    private javax.swing.JButton label_Status;
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
    private javax.swing.JButton label_Travel;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JLabel label_Weapon;
    private javax.swing.JLabel label_Wilderness;
    private javax.swing.JPanel panel_Attributes;
    private javax.swing.JPanel panel_AttributesActions;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_AttributesAdditionButtons;
    private javax.swing.JPanel panel_ClassMenu;
    private javax.swing.JPanel panel_Combat;
    private javax.swing.JPanel panel_CombatLog;
    private javax.swing.JPanel panel_Dashes;
    private javax.swing.JPanel panel_Dashes1;
    private javax.swing.JPanel panel_Dashes2;
    private javax.swing.JPanel panel_Dialogue;
    private javax.swing.JPanel panel_Game;
    private javax.swing.JPanel panel_GearAddition;
    private javax.swing.JPanel panel_Home;
    private javax.swing.JPanel panel_Inventory;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JPanel panel_Skills;
    private javax.swing.JPanel panel_StartingGear;
    private javax.swing.JPanel panel_SubGearAddition;
    private javax.swing.JPanel panel_SubTotal;
    private javax.swing.JPanel panel_Total;
    private javax.swing.JPanel panel_Travel;
    private javax.swing.JPanel panel_Village;
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
