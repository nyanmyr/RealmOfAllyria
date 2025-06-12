package realmofallyria;

public class Weapon extends Equipment {

    public Weapon(
            String givenName,
            int givenWPLVL,
            Skill givenBasicAttackSkill,
            int givenWPStrengthPoints,
            int givenWPIntelligencePoints,
            int givenWPAgilityPoints
    ) {

        this.equipmentName = givenName;
        this.equipmentLVL = givenWPLVL;
        this.basicAttackSkill = givenBasicAttackSkill;
        this.wpStrengthPoints = givenWPStrengthPoints * equipmentLVL;
        this.wpIntelligencePoints = givenWPIntelligencePoints * equipmentLVL;
        this.wpAgilityPoints = givenWPAgilityPoints * equipmentLVL;

    }

}
