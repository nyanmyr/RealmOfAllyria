
package realmofallyria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Battle {

    Queue<String> turns = new LinkedList<>();
    Random battleRandomizer = new Random();

    Player battlePlayer;
    Hostile battleHostile;

    // the longer the battle, the more xp
    double battleXPTurnIncrease;
    double battleXPGain;

    double battleCoinTurnIncrease;
    double battleCoinGain;

    int totalTurns;

    double escapeChance;

    boolean battleEnded = false;

    public Battle(Player givenPlayer, Hostile givenHostile) {

        this.battlePlayer = givenPlayer;
        this.battleHostile = givenHostile;

        // calculate xp reward
        battleXPGain = battleRandomizer.nextDouble(givenHostile.level * 3, givenHostile.level * 4);
        battleXPTurnIncrease = (battleXPGain * 0.125);

        // calculate coins reward
        battleCoinGain = battleRandomizer.nextDouble(givenHostile.level * 5, givenHostile.level * 7);
        battleCoinTurnIncrease = (battleCoinGain * 0.125);

        // calculates escape chance
        // this ensures that the escape chance never exceeds 90.00%
        // and never falls short short of 20.00%
        escapeChance = 30 + ((battlePlayer.agilityPoints - battleHostile.agilityPoints) * 5) > 90 ? 90
                : 30 + ((battlePlayer.agilityPoints - battleHostile.agilityPoints) * 5) < 30 ? 30
                        : 30 + ((battlePlayer.agilityPoints - battleHostile.agilityPoints) * 5);
//        System.out.printf("escapeChance: %.2f%%\n", escapeChance);

        if (battlePlayer.agilityPoints > battleHostile.agilityPoints) {
            turns.add(battlePlayer.name);
        } else {
            turns.add(battleHostile.name);
        }

        totalTurns++;
    }

    public String takeTurn(Skill battleSkillUsed, Mob defendingMob, Mob attackingMob) {

        moveQueue();

        battleXPGain += battleXPTurnIncrease;
        battleCoinGain += battleCoinTurnIncrease;
        Skill battleTurnSkill = defendingMob.defend(attackingMob.useSkill(battleSkillUsed));

        String attackString;

        attackString = String.format(""" 
                                            <html>

                                            <head>
                                            <h2 align="center">
                                            %s
                                            </h2>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s %s %s %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format(battleSkillUsed.skillPrompt, attackingMob.name, defendingMob.name),
                battleTurnSkill.effects.containsKey("TotalPhysicalDamage") ? String.format("<br>PDmg inflicted (-%.2f HP)%s",
                battleTurnSkill.effects.get("TotalPhysicalDamage"),
                battleTurnSkill.effects.containsKey("IgnoreDefense") ? "<br>PDef IGNORED!" : String.format("<br>PDmg defended (%.2f HP)", battleTurnSkill.effects.get("PhysicalDamageDefended"))) : "",
                
                battleTurnSkill.effects.containsKey("TotalMagicalDamage") ? String.format("<br>MDmg inflicted (-%.2f HP)%s",
                battleTurnSkill.effects.get("TotalMagicalDamage"),
                battleTurnSkill.effects.containsKey("IgnoreDefense") ? "<br>MDef IGNORED!" : String.format("<br>MDmg defended (%.2f HP)", battleTurnSkill.effects.get("MagicalDamageDefended"))) : "",
                
                battleTurnSkill.skillBaseCost > 0 ? String.format("<br>used %s MP", battleTurnSkill.skillBaseCost) : "",
                
                battleTurnSkill.effects.containsKey("HealSelf") ? String.format("<br>HP RESTORED (+%.2f HP)", battleTurnSkill.effects.get("HPHealed")) : "",
                battleTurnSkill.effects.containsKey("CriticalHit") ? String.format("<br>CRITICAL HIT! (2x inflicted dmg)") : "",
                battleTurnSkill.effects.containsKey("Missed") ? String.format("<br>ATTACK MISSED!") : ""
        );

//        System.out.println(attackString);
//        System.out.println("NEXT TURN:" + turns.peek());
        totalTurns++;

        return attackString;

    }

    public void moveQueue() {

        // <editor-fold desc="uses a queue data structure to determine turns.">
        if (turns.peek().equals(battlePlayer.name)) {
            turns.add(battleHostile.name);
        } else {
            turns.add(battlePlayer.name);
        }
        turns.poll();
        // </editor-fold>

    }

    public boolean attemptFlee() {

        int fleeRoll = battleRandomizer.nextInt(1, 101);

        // success if roll is under or equal to escapeChance
        return fleeRoll <= escapeChance;

    }

    public String battleEnd(Boolean fleeing) {

        int battleGoldCoins = 0;
        int battleSilverCoins = 0;
        int battleCopperCoins = 0;

        String battleEndString;

        if (!fleeing) {
            String winningMob;
            String defeatedMob;
            if (battlePlayer.currentHP < 0) {
                defeatedMob = battlePlayer.name;
                winningMob = battleHostile.name;
                battleXPGain = battleXPGain * 0.05;
                battleCoinGain = 0 * 0.05;
            } else {
                defeatedMob = battleHostile.name;
                winningMob = battlePlayer.name;
            }

            battleGoldCoins = (int) battleCoinGain / 2500;
            int battleRemainingAfterGold = (int) battleCoinGain % 2500;
            battleSilverCoins = battleRemainingAfterGold / 50;
            battleCopperCoins = battleRemainingAfterGold % 50;

            battleEndString = String.format("""
                                                <html>

                                                <head>
                                                <h3 align="center">
                                                %s
                                                </h3>
                                                </head>

                                                <body>
                                                <p align="center">
                                                %s %s %s %s %s %s
                                                </p>
                                                </body>

                                                </html>
                                                """, String.format("%s defeated %s.", winningMob, defeatedMob),
                    String.format("<br> (%s turns)", totalTurns / 2),
                    battleXPGain > 0 ? String.format("<br> +%.2f XP gained.", battleXPGain) : "",
                    battleCopperCoins > 0 ? String.format("<br> %s (Copper) ", battleCopperCoins) : "",
                    battleSilverCoins > 0 ? String.format(" %s (Silver) ", battleSilverCoins) : "",
                    battleGoldCoins > 0 ? String.format(" %s (Gold) ", battleGoldCoins) : "",
                    battleCoinGain > 0 ? String.format(" coins acquired ") : "");

        } else {

            battleEndString = String.format("%s successfully fled from battle.", battlePlayer);

        }

        battleEnded = true;

        return battleEndString;

    }

}
