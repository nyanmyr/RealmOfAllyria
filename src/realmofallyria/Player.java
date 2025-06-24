
package realmofallyria;

public class Player extends Mob {

    double xp;
    double xpNeeded;
    int accumulatedLVL;

    int totalCoins;

    public Player() {

        updateXPNeeded();

    }

    public void takeCoins(int amountTaken) {

        totalCoins = totalCoins - amountTaken < 0 ? 0 : totalCoins - amountTaken;

    }

    public void receiveXPCoinsReward(double xpGained, double coinsGained) {

        totalCoins += coinsGained;

        while (xpGained > 0) {
            double xpToLevelUp = xpNeeded - xp;

            if (xpGained >= xpToLevelUp) {
                // Level up!
                xpGained -= xpToLevelUp;
                accumulatedLVL++;
                xp = 0;
                xpNeeded += 14;
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

    private void updateXPNeeded() {

        xpNeeded = (level + 1) * 24;

    }

    private void levelUp() {

        // this resets the max HP and MP, fixes the compounding increase of HP and MP bug
        maxHP = 0;
        maxMP = 0;
        fullHeal();

        level += accumulatedLVL;

        updateXPNeeded();

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

        updateSkills();
        addHPMP(healthPoints, intelligencePoints, false);

        fullHeal();

    }

}