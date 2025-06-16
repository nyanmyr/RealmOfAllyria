
package realmofallyria;

public class Skill {

    String skillName;
    double skillCost;
    double skillCostIncrease;
    int skillCostModifier;
    double skillPDmg;
    double skillMDmg;
    boolean selfInflict;

    public Skill(
            String givenSkillName
    ) {

        this.skillName = givenSkillName;

        switch (this.skillName) {

            // <editor-fold desc="hostile skills">
            case "Bite":
            case "Club":
            case "Tackle":
            case "Punch":
            case "Slash": {
                this.skillPDmg = 1;
                break;
            }
            case "Shoot": {
                this.skillPDmg = 0.75;
                break;
            }
            case "Magic Missile": {
                this.skillMDmg = 0.5;
                break;
            }
            case "Fireball": {
                this.skillCost = 25;
                this.skillMDmg = 2.5;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 8;
                break;
            }
            case "True Strike": {
                this.skillCost = 30;
                this.skillPDmg = 1.5;
                this.skillCostIncrease = 2;
                this.skillCostModifier = 8;
                break;
            }
            case "Ice Shards": {
                this.skillCost = 50;
                this.skillMDmg = 2.5;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                break;
            }
            case "Frozen Hellfire": {
                this.skillCost = 70;
                this.skillPDmg = 5;
                this.skillCostIncrease = 2.5;
                this.skillCostModifier = 28;
                break;
            }
            // </editor-fold>

            // <editor-fold desc="defensive skills">
            case "Parry": {
                this.skillCost = 35;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                break;
            }
            case "Aegis": {
                this.skillCost = 70;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 28;
                break;
            }
            // </editor-fold>
            // <editor-fold desc="self-inflicted skills">
            case "Heal":
                this.selfInflict = true;
                this.skillCost = 20;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 8;
                break;
            case "Magic Shield":
                this.selfInflict = true;
                this.skillCost = 40;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                break;
            case "Revive":
                this.selfInflict = true;
                this.skillCost = 70;
                this.skillCostIncrease = 2.5;
                this.skillCostModifier = 28;
                break;
            // </editor-fold>

        }

    }

}
