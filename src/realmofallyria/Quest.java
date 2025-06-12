
package realmofallyria;

import java.util.HashMap;

public class Quest {

    // format <Monster Name, {kill count, kill needed}>
    HashMap<String, Integer[]> QuestTasks = new HashMap<>();
    String questName = "";
    double questXPReward;
    int questCoinsReward;

    /*
    
    format for new quest <String, Integer[]}> {
    
    {
        put("Slime", new Integer[]{0, 3});
    }
    
    }
     */
    public void newQuest(HashMap<String, Integer[]> newQuest,
            String givenQuestName,
            double givenXPReward,
            double givenCoinsReward) {

        QuestTasks.clear();

        this.questName = givenQuestName;
        this.QuestTasks.putAll(newQuest);
        this.questXPReward = givenXPReward;
        this.questCoinsReward = (int) givenCoinsReward;

        QuestTasks.put("Completed", new Integer[]{0, 1});

    }

    // gets an array of battleHostile levels and count of each enemies then returns the upperend of xp reward
    public double[] generateReward(int[] enemyLevels, int[] enemyCount) {

        double generatedXPReward = 0;
        int generatedCoinsReward = 0;

        for (int i = 0; i < enemyLevels.length; i++) {

            generatedXPReward += (enemyLevels[i] * 5) * enemyCount[i];
            generatedCoinsReward += (enemyLevels[i] * 10) * enemyCount[i];

        }

        return new double[]{generatedXPReward, generatedCoinsReward};

    }

    public void updateTask(String mobKilledName) {

        if (QuestTasks.containsKey(mobKilledName)) {

            QuestTasks.put(mobKilledName,
                    new Integer[]{(QuestTasks.get(mobKilledName)[0] + 1),
                        QuestTasks.get(mobKilledName)[1]});

        }

    }

    public boolean isQuestCompleted() {

        boolean questCompleted = true;

        for (String questTaskKey : QuestTasks.keySet()) {

            if (!questTaskKey.equals("Completed")) {

                if (QuestTasks.get(questTaskKey)[0] < QuestTasks.get(questTaskKey)[1]) {

                    questCompleted = false;

                }

            }

        }

        return questCompleted;

    }

}
