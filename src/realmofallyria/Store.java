
package realmofallyria;

import java.util.HashMap;

public class Store {

    // store bought equipment
    // hashmap for store locations and their merchandise (as their pair)
    HashMap<String, HashMap<String, Equipment>> storeMerchandise = new HashMap<>();

    // bought merchandise
    // bought merchandise format: <"storeLocation", HashMap<"item bought", lvlInt>>
    HashMap<String, HashMap<String, Equipment>> boughtMerchandise = new HashMap<>();

    public Store(String firstStoreLocation) {

        this.storeMerchandise.put(firstStoreLocation, null);

    }

    public void generateMerchandiseInfo(String givenStoreLocation,
            HashMap<String, Equipment> merchandiseHashMap, int storeLevel) {

//        System.out.println(givenStoreLocation);
//        System.out.println(merchandiseHashMap);
//        System.out.println(storeLevel);

        /*
        
        put("Sword", new String[] {"Iron Sword", "Steel Sword"});
        put("Bow", new String[] {"Simple Bow", "Short Bow"});
        put("Wand", new String[] {"Crude Wand", "Novice Wand"});
        put("Armor", new String[] {"Leather Armor", "Reinforced Leather Armor"});
        
         */
        HashMap<String, Equipment> shopMerchandise = new HashMap<>() {

            {

                for (String shopEquipmentStr : merchandiseHashMap.keySet()) {

                    Equipment equipmentModified = merchandiseHashMap.get(shopEquipmentStr);
                    equipmentModified.coinsWorth = 50 + ((storeLevel - 1) * 50);
//                    System.out.println(equipmentModified.coinsWorth);
                    put(shopEquipmentStr, equipmentModified);

                }

            }

        };

        storeMerchandise.put(givenStoreLocation, shopMerchandise);

    }

    public int purchaseMerchandise(String buyLocation, String merchandiseType) {

        Equipment upgradedEquipment;

        if (merchandiseType.equals("Armor")) {

            upgradedEquipment = new Armor(storeMerchandise.get(buyLocation).get(merchandiseType).equipmentName,
                    storeMerchandise.get(buyLocation).get(merchandiseType).equipmentLVL,
                    storeMerchandise.get(buyLocation).get(merchandiseType).arHealthPoints > 0 ? 3 : 0,
                    storeMerchandise.get(buyLocation).get(merchandiseType).arDefensePoints > 0 ? 3 : 0
            );

        } else {

            upgradedEquipment = new Weapon(storeMerchandise.get(buyLocation).get(merchandiseType).equipmentName,
                    storeMerchandise.get(buyLocation).get(merchandiseType).equipmentLVL,
                    storeMerchandise.get(buyLocation).get(merchandiseType).basicAttackSkill,
                    storeMerchandise.get(buyLocation).get(merchandiseType).wpStrengthPoints > 0 ? 3 : 0,
                    storeMerchandise.get(buyLocation).get(merchandiseType).wpIntelligencePoints > 0 ? 3 : 0,
                    storeMerchandise.get(buyLocation).get(merchandiseType).wpAgilityPoints > 0 ? 3 : 0
            );

        }

        if (boughtMerchandise.get(buyLocation) == null) {

            boughtMerchandise.put(buyLocation, new HashMap<>());

        }
        HashMap<String, Equipment> temp = boughtMerchandise.get(buyLocation);
        temp.put(merchandiseType, upgradedEquipment);
        boughtMerchandise.put(buyLocation, boughtMerchandise.get(buyLocation));

        storeMerchandise.get(buyLocation).get(merchandiseType).equipmentLVL += 1;

        storeMerchandise.get(buyLocation).get(merchandiseType).wpStrengthPoints
                = storeMerchandise.get(buyLocation).get(merchandiseType).wpStrengthPoints > 0
                ? storeMerchandise.get(buyLocation).get(merchandiseType).wpStrengthPoints += 3 : 0;

        storeMerchandise.get(buyLocation).get(merchandiseType).wpIntelligencePoints
                = storeMerchandise.get(buyLocation).get(merchandiseType).wpIntelligencePoints > 0
                ? storeMerchandise.get(buyLocation).get(merchandiseType).wpIntelligencePoints += 3 : 0;

        storeMerchandise.get(buyLocation).get(merchandiseType).wpAgilityPoints
                = storeMerchandise.get(buyLocation).get(merchandiseType).wpAgilityPoints > 0
                ? storeMerchandise.get(buyLocation).get(merchandiseType).wpAgilityPoints += 3 : 0;

        storeMerchandise.get(buyLocation).get(merchandiseType).arHealthPoints
                = storeMerchandise.get(buyLocation).get(merchandiseType).arHealthPoints > 0
                ? storeMerchandise.get(buyLocation).get(merchandiseType).arHealthPoints += 3 : 0;

        storeMerchandise.get(buyLocation).get(merchandiseType).arDefensePoints
                = storeMerchandise.get(buyLocation).get(merchandiseType).arDefensePoints > 0
                ? storeMerchandise.get(buyLocation).get(merchandiseType).arDefensePoints += 3 : 0;

        storeMerchandise.get(buyLocation).get(merchandiseType).coinsWorth *= 1.25;

        return storeMerchandise.get(buyLocation).get(merchandiseType).coinsWorth;

    }

    public Equipment equipBoughtMerchandise(String equipLocation, String merchandiseType) {

        return boughtMerchandise.get(equipLocation).get(merchandiseType);

    }

}
