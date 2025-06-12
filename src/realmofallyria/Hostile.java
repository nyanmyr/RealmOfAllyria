
package realmofallyria;

public class Hostile extends Mob {

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="mob generation mechanics stuff">
    // generates a mob
    public Hostile(String hostileName,
            String hostileTypeAffinity,
            int hostileLevel,
            Weapon hostileWeapon,
            Armor hostileArmor
    ) {

        this.name = hostileName;
        this.typeAffinity = hostileTypeAffinity;
        this.level = hostileLevel;

        initialize(hostileWeapon, hostileArmor);

    }

    private void initialize(
            Weapon givenWeapon,
            Armor givenArmor
    ) {

        chooseAffinity(8);
        randomizeAttributes();
        confirmAttributeChanges();
        equipGear(givenWeapon, givenArmor);

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
}
