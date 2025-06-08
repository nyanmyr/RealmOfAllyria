
package realmofallyria;

public class TestPlace {

    public static void main(String[] args) {

        // equation for MP cost increase per IP
        int intelligencePoints = 25;

        double maxMP = intelligencePoints * 2.5;
        double currentMP = maxMP;

        double skillUseCost = 10;
        double skillLevelModifier;
        double skillCostIncrease = 0.4;
        double skillCostMultiplier = 0.5;
        double totalSkillUseCost;

        System.out.println("IP: " + intelligencePoints);
        System.out.printf("MP: %s / %s\n", currentMP, maxMP);
        System.out.println("-------");

        for (int i = 1; i <= 1; i++) {

            System.out.println("Level: " + i);

            for (int j = 10; j <= 250; j += 10) {

                System.out.println("IP: " + j);
                skillLevelModifier = skillCostIncrease * (1 - ((skillCostIncrease * skillCostMultiplier) / (5 - i))) < (skillCostIncrease * 0.01)
                        ? skillCostIncrease * 0.01 : skillCostIncrease * (1 - ((skillCostIncrease * skillCostMultiplier) / (5 - i)));
//            System.out.println("Cost Increase per IP: " + skillLevelModifier);
//            System.out.println("Cost Increased: " + (j <= 4 ? 0 : (j - 4) * skillLevelModifier));
                totalSkillUseCost = (skillUseCost + (j <= 4 ? 0 : (j - 4) * skillLevelModifier));
//            System.out.println("Use Cost: " + totalSkillUseCost);
                System.out.printf("Available Uses: %.0f\n", ((j * 2.5) / totalSkillUseCost));
                System.out.println("-------");

            }

        }

    }

}
