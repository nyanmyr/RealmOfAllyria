
package realmofallyria;

public class Armor extends Equipment {

    public Armor(
            String givenName,
            int givenARLVL,
            int givenARHealthPoints,
            int givenARDefensePoints
    ) {

        this.equipmentName = givenName;
        this.equipmentLVL = givenARLVL;
        this.arHealthPoints = givenARHealthPoints * equipmentLVL;
        this.arDefensePoints = givenARDefensePoints * equipmentLVL;

    }

}
