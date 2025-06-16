
package realmofallyria;

import java.util.Random;

public abstract class Mob {

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="variables stuff">
    Random mobRandomizer = new Random();
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="characteristics variables">
    String name;
    String typeAffinity;

    int level;
    int attributePoints;

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
    // <editor-fold desc="dungeonMonsterEquipment variables">
    Equipment equippedWeapon;
    Equipment equippedArmor;
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="skills variables">
    Skill basicAttackSkill;

    Skill skill1;
    Skill skill2;
    Skill skill3;

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="attribute mechanics, equip gear, full heal stuff">
    public void chooseAffinity(int usableAttributePoints) {

        attributePoints = level * usableAttributePoints;

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

        updateSkills();
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

        updateSkills();
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

    public void addHPMP(int addHP, int addMP, boolean negative) {
        addHP = negative ? addHP * -1 : addHP;
        addMP = negative ? addMP * -1 : addMP;

        currentHP += addHP * 10;
        currentMP += addMP * 5;

        maxHP += addHP * 10;
        maxMP += addMP * 5;
    }

    public void setSubAttributes() {

        physicalDefense = defensePoints * 1;
        magicalDefense = intelligencePoints * 0.5;
        physicalDamage = strengthPoints * 2;
        magicalDamage = intelligencePoints * 1;
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
    public double[] useSkill(Skill skillUsed) {

        // index 0 is for pdmg, and 1 is for mdmg
        double[] damageDealt = new double[9];

        damageDealt[0] = skillUsed.skillPDmg * physicalDamage;
        damageDealt[1] = skillUsed.skillMDmg * magicalDamage;
        damageDealt[7] = skillUsed.selfInflict == true ? 1 : 0;

//        System.out.println(skillUsed.skillName.equals("True Strike"));
        damageDealt[8] = skillUsed.skillName.equals("True Strike") ? 1 : 0;

        if (damageDealt[7] == 0) {

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

        } else {

            damageDealt[0] = (maxHP * 0.25);

            currentHP = currentHP + damageDealt[0] > maxHP ? maxHP : currentHP + damageDealt[0];

            // returns a blank dmg array which determines the attack as harmless or self-inglicted
            return damageDealt;

        }

        currentMP = currentMP - skillUsed.skillCost < 0 ? 0 : currentMP - skillUsed.skillCost;

        return damageDealt;

    }

    public double[] defend(double[] damageTaken) {

        // <editor-fold desc="damageTaken indexes legend">
        // if all values are 0, it means the user did not have enough MP to use this skill
        // [0] - physical damage (from attacker)
        // [1] - magical damage (from attacker)
        // [2] - determines if hit is critical (1 for crit, 0 for not)
        // [3] - physical defense (from defender)
        // [4] - magical defense (from defender)
        // [5] - physical damage (from attacker): without defense reductions
        // [6] - magical damage (from attacker): without defense reductions
        // [7] - determines if attack is self-inflicted or harmless to the defender (1 for true, 0 for false)
        // [8] - determines if the attack ignores defense points (1 for true, 0 for false)
        // </editor-fold>
        // saves the total damaged suffered
        damageTaken[5] = damageTaken[0];
        damageTaken[6] = damageTaken[1];

        // checks if the damage was self inflicted by the attacker, hence no damage will be defended/ registered
        if (damageTaken[7] == 0) {
            // calculates actual dmg taken, taking into account physical defense
            // if statement checks if the skill used is self inflicted or not
            damageTaken[0] = (damageTaken[0] > 0
                    ? (damageTaken[0] - physicalDefense < 0
                            ? (damageTaken[0] * 0.1) : damageTaken[0] * 0.9
                            - (damageTaken[8] == 0 ? physicalDefense : 0))
                    : 0);
            damageTaken[1] = (damageTaken[1] > 0
                    ? (damageTaken[1] - magicalDefense < 0
                            ? (damageTaken[1] * 0.1) : damageTaken[1] * 0.9
                            - (damageTaken[8] == 0 ? physicalDefense : 0))
                    : 0);

            // saves the dmg defended
            damageTaken[3] = damageTaken[8] == 0 ? physicalDefense : 0;
            damageTaken[4] = damageTaken[8] == 0 ? magicalDefense : 0;

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
        }

        return damageTaken;

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="gears and skills mechanics stuff">
    public void equipGear(Equipment givenWeapon, Equipment givenArmor) {

        equippedWeapon = givenWeapon;
        equippedArmor = givenArmor;

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

        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="unused">
        /*
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
                basicAttackSkill = "Slash";
                break;
            }
            case "Simple Bow": {
                SPGearAddition += weaponLevel * 1;
                APGearAddition += weaponLevel * 2;
                basicAttackSkill = "Shoot";
                break;
            }
            case "Crude Wand": {
                IPGearAddition += weaponLevel * 3;
                basicAttackSkill = "Magic Missile";
                break;
            }
            case "Body": {
                basicAttackSkill = "Tackle";
                break;
            }
            case "Club": {
                basicAttackSkill = "Club";
                break;
            }
            case "Mouth": {
                basicAttackSkill = "Bite";
                break;
            }
            case "DEBUG": {
                IPGearAddition += weaponLevel * 1000;
                basicAttackSkill = "Punch";
                break;
            }
            default: {
                break;
            }
        }
         */
        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------
        SPGearAddition = equippedWeapon.wpStrengthPoints;
        IPGearAddition = equippedWeapon.wpIntelligencePoints;
        APGearAddition = equippedWeapon.wpAgilityPoints;
        basicAttackSkill = equippedWeapon.basicAttackSkill;

        HPGearAddition = equippedArmor.arHealthPoints;
        DPGearAddition = equippedArmor.arDefensePoints;

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

    public void equipSkill(Skill givenSkill, int skillIncreaseModifier, boolean allocateSkill) {

        // scales the skill cost
        givenSkill.skillCost += intelligencePoints > skillIncreaseModifier ? givenSkill.skillCostIncrease : 0;

        // chooses where the skill will be alloted
        if (allocateSkill) {

            if (skill1 == null) {
                skill1 = givenSkill;
            } else if (skill2 == null) {
                skill2 = givenSkill;
            } else if (skill3 == null) {
                skill3 = givenSkill;
            }

        }

    }

    public void updateSkills() {

        if (skill1 != null) {
            equipSkill(skill1, skill1.skillCostModifier, false);
        }
        if (skill2 != null) {
            equipSkill(skill2, skill2.skillCostModifier, false);
        }
        if (skill3 != null) {
            equipSkill(skill3, skill3.skillCostModifier, false);
        }

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
}
