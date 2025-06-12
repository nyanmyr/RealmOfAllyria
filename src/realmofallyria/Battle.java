
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

    double[] battleDamageTaken;

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
        battleDamageTaken = defendingMob.defend(attackingMob.useSkill(battleSkillUsed));

        String skilluseString = "";

        switch (battleSkillUsed.skillName) {

            // <editor-fold desc="hostile attacks">
            case "Tackle" -> {
                skilluseString = "%s tackled %s";
            }
            case "Punch" -> {
                skilluseString = "%s punched %s";
            }
            case "Slash" -> {
                skilluseString = "%s slashed %s";
            }
            case "Shoot" -> {
                skilluseString = "%s hit %s";
            }
            case "Magic Missile" -> {
                skilluseString = "%s fired a magic missile at %s";
            }
            case "Bite" -> {
                skilluseString = "%s bit %s";
            }
            case "Club" -> {
                skilluseString = "%s bonked %s";
            }
            case "Fireball" -> {
                skilluseString = "%s fired a fireball at %s";
            }
            case "True Strike" -> {
                skilluseString = "%s struck %s truthfully";
            }
            case "Ice Shards" -> {
                skilluseString = "%s fired shards of ice at %s";
            }
            // </editor-fold>
            // <editor-fold desc="non-hostile attacks">
            case "Heal" -> {
                skilluseString = "%s used heal.";
            }
            // </editor-fold>

        }

        String attackString;

        if (battleSkillUsed.selfInflict) {
            attackString = String.format(""" 
                                            <html>

                                            <head>
                                            <h3 align="center">
                                            %s
                                            </h3>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format(skilluseString, attackingMob.name, defendingMob.name),
                    String.format("<br> HP restored (+%.2f HP)", battleDamageTaken[0]),
                    battleSkillUsed.skillCost > 0 ? String.format("<br> (-%.2f MP)", battleSkillUsed.skillCost) : ""
            );
        } else {
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
                                            """, String.format(skilluseString, attackingMob.name, defendingMob.name),
                    (battleDamageTaken[0] > 0 ? String.format("<br> PDmg inflicted (-%.2f HP)", battleDamageTaken[0]) : battleDamageTaken[5] > 0 ? "<br> Attacked completely blocked!" : ""),
                    (battleDamageTaken[0] > 0 ? String.format("<br> PDmg defended (%.2f HP)", battleDamageTaken[3]) : ""),
                    (battleDamageTaken[1] > 0 ? String.format("<br> MDmg inflicted (-%.2f MP)", battleDamageTaken[1]) : battleDamageTaken[6] > 0 ? "<br> Attacked completely resisted!" : ""),
                    (battleDamageTaken[1] > 0 ? String.format("<br> MDmg defended (%.2f MP)", battleDamageTaken[4]) : ""),
                    (battleDamageTaken[2] > 0 ? String.format("<br> CRITICAL HIT! (2x inflicted dmg)") : ""),
                    battleSkillUsed.skillCost > 0 ? String.format("<br> (-%.2f MP)", battleSkillUsed.skillCost) : ""
            );
        }

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

        if (fleeRoll < escapeChance) {

//            System.out.println("Escape Success");
            return false;

        } else {

//            System.out.println("Escape Failure");
            return true;

        }

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
                battleXPGain = 0;
                battleCoinGain = 0;
            } else {
                defeatedMob = battleHostile.name;
                winningMob = battlePlayer.name;
                battleGoldCoins = (int) battleCoinGain / 2500;
                int battleRemainingAfterGold = (int) battleCoinGain % 2500;
                battleSilverCoins = battleRemainingAfterGold / 50;
                battleCopperCoins = battleRemainingAfterGold % 50;
            }

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
