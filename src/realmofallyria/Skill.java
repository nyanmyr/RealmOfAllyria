
package realmofallyria;

import java.util.HashMap;

public class Skill {

    String skillName;
    String skillPrompt;
    
    double skillBaseCost;
    double skillCostIncrease;
    int skillCostModifier;

    HashMap<String, Double> effects = new HashMap<>();

//    double skillPDmg;
//    double skillMDmg;
//    boolean selfInflict;

    public Skill(
            String givenSkillName
    ) {

        this.skillName = givenSkillName;

        switch (this.skillName) {

            // <editor-fold desc="hostile skills">
            case "Bite": {
                this.skillPrompt = "%s bit %s";
                effects.put("PhysicalDamageMultiplier", 1.0);
                break;
            }
            case "Club": {
                this.skillPrompt = "%s clubbed %s";
                effects.put("PhysicalDamageMultiplier", 1.0);
                break;
            }
            case "Tackle": {
                this.skillPrompt = "%s tackled %s";
                effects.put("PhysicalDamageMultiplier", 1.0);
                break;
            }
            case "Punch": {
                this.skillPrompt = "%s punched %s";
                effects.put("PhysicalDamageMultiplier", 1.0);
                break;
            }
            case "Slash": {
                this.skillPrompt = "%s slashed %s";
                effects.put("PhysicalDamageMultiplier", 1.0);
                break;
            }
            case "Shoot": {
                this.skillPrompt = "%s shot %s";
                effects.put("PhysicalDamageMultiplier", 0.65);
                break;
            }
            case "Magic Missile": {
                this.skillPrompt = "%s shot a magic missile at %s";
                effects.put("MagicalDamageMultiplier", 0.5);
                break;
            }
            case "Fireball": {
                this.skillPrompt = "%s shot a fireball at %s";
                this.skillBaseCost = 25;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 8;
                effects.put("MagicalDamageMultiplier", 2.5);
                break;
            }
            case "True Strike": {
                this.skillPrompt = "%s struck %s truthfully.";
                this.skillBaseCost = 30;
                this.skillCostIncrease = 2;
                this.skillCostModifier = 8;
                effects.put("PhysicalDamageMultiplier", 1.5);
                effects.put("IgnoreDefense", 1.0);
                break;
            }
            case "Ice Shards": {
                this.skillPrompt = "%s shot ice shards at %s";
                this.skillBaseCost = 50;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                effects.put("MagicalDamageMultiplier", 2.5);
                break;
            }
            case "Frozen Hellfire": {
                this.skillPrompt = "%s used frozen hellfire on %s";
                this.skillBaseCost = 70;
                this.skillCostIncrease = 2.5;
                this.skillCostModifier = 28;
                effects.put("MagicalDamageMultiplier", 5.0);
                break;
            }
            // </editor-fold>            // </editor-fold>

            // <editor-fold desc="defensive skills">
            case "Parry": {
                this.skillPrompt = "%s parried %s";
                this.skillBaseCost = 35;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                break;
            }
            case "Aegis": {
                this.skillPrompt = "%s used aegis";
                effects.put("SelfInflicted", 1.0);
                this.skillBaseCost = 70;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 28;
                break;
            }
            // </editor-fold>
            // <editor-fold desc="self-inflicted skills">
            case "Heal":
                this.skillPrompt = "%s used heal";
                effects.put("SelfInflicted", 1.0);
                effects.put("HealSelf", 0.25);
                this.skillBaseCost = 20;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 8;
                break;
            case "Magic Shield":
                this.skillPrompt = "%s used magic shield";
                effects.put("SelfInflicted", 1.0);
                effects.put("Shield", 1.0);
                this.skillBaseCost = 40;
                this.skillCostIncrease = 1;
                this.skillCostModifier = 18;
                break;
            case "Revive":
                this.skillPrompt = "%s used revive";
                effects.put("SelfInflicted", 1.0);
                effects.put("Revive", 1.0);
                this.skillBaseCost = 70;
                this.skillCostIncrease = 2.5;
                this.skillCostModifier = 28;
                break;
            // </editor-fold>

        }

    }

}
