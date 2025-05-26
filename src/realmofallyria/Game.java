
package realmofallyria;

import java.util.Random;

/*
    to do:
    • edit affinity description
    • save/ load
 */
class TestingGrounds {

    public static void main() {

        Random randomizer = new Random();

        String monsterName = "Slime";
        int monsterLVL = 50;

        int monsterAttributePoints = monsterLVL * 2;
        int monsterHP = monsterLVL * 10;
        int monsterAP = monsterLVL * 1;
        int monsterMP = monsterLVL * 1;
        int monsterDP = monsterLVL * 1;
        int monsterSP = monsterLVL * 1;

        int monsterXPRewardMin = monsterLVL * 3;
        int monsterXPRewardMax = monsterLVL * 4;

        String affinity = "Sanitas";
        switch (affinity) {
            case "Sanitas" -> {
                monsterHP += monsterLVL * 10;
            }
            case "Celeritas" -> {
                monsterAP += monsterLVL * 1;
            }
            case "Madeis" -> {
                monsterMP += monsterLVL * 1;
            }
            case "Tetula" -> {
                monsterDP += monsterLVL * 1;
            }
            case "Virtus" -> {
                monsterSP += monsterLVL * 1;
            }
        }

        for (int i = 0; i < monsterAttributePoints; i++) {

            switch (randomizer.nextInt(5)) {
                case 0 -> {
                    monsterHP += 10;
                }
                case 1 -> {
                    monsterAP++;
                }
                case 2 -> {
                    monsterMP++;
                }
                case 3 -> {
                    monsterDP++;
                }
                case 4 -> {
                    monsterSP++;
                }
            }

            monsterAttributePoints--;
        }

        System.out.println("Name: " + monsterName);
        System.out.println("Attribute Points: " + monsterAttributePoints);
        System.out.println("Monster HP: " + monsterHP);
        System.out.println("Monster AP: " + monsterAP);
        System.out.println("Monster MP: " + monsterMP);
        System.out.println("Monster DP: " + monsterDP);
        System.out.println("Monster SP: " + monsterSP);
        System.out.printf("XP Reward: %s - %s\n", monsterXPRewardMin, monsterXPRewardMax);


//        calculateXPNeeded();
    }

    public static void calculateXPNeeded() {
        int lvl = 35;
        int targetLevel = lvl + 1;
        int xpNeeded;
        System.out.printf("LVL: %s >>", lvl);

        lvl++;
        xpNeeded = lvl * 14;

        System.out.println(" " + lvl);
        System.out.printf("XP need to LVL %s: %s\n", targetLevel, xpNeeded);
    }

}

class Mob {

    int level;
    int attributePoints;

    // attributes
    int healthPoints;
    int magicPoints;
    int agiltiyPoints;
    int defensePoints;
    int strengthPoints;

    // temporary attributes addition
    int attributePointsAddition;

    int HPAddition;
    int MPAddition;
    int APAddition;
    int DPAddition;
    int SPAddition;

    String name;
    String typeAffinity;

    public void chooseAffinity() {
        level = 1;
        attributePoints = 2;

        // attributes
        healthPoints = 20;
        agiltiyPoints = 2;
        magicPoints = 2;
        defensePoints = 2;
        strengthPoints = 2;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints += 10;
            }
            case "Celeritas" -> {
                agiltiyPoints += 1;
            }
            case "Madeis" -> {
                magicPoints += 1;
            }
            case "Tutela" -> {
                defensePoints += 1;
            }
            case "Virtus" -> {
                strengthPoints += 1;
            }
        }

    }

    public void attributeAddition(String affinityAddition) {
        attributePointsAddition++;

        switch (affinityAddition) {
            case "Sanitas" -> {
                HPAddition += 10;
            }
            case "Celeritas" -> {
                APAddition += 1;
            }
            case "Madeis" -> {
                MPAddition += 1;
            }
            case "Tutela" -> {
                DPAddition += 1;
            }
            case "Virtus" -> {
                SPAddition += 1;
            }
        }

    }

    public void confirmAttributeChanges() {

        attributePointsAddition = 0;

        healthPoints += HPAddition;
        magicPoints += MPAddition;
        agiltiyPoints += APAddition;
        defensePoints += DPAddition;
        strengthPoints += SPAddition;

    }

    public void resetAttributeChanges() {

        attributePoints = attributePointsAddition;
        attributePointsAddition = 0;

        HPAddition = 0;
        MPAddition = 0;
        APAddition = 0;
        DPAddition = 0;
        SPAddition = 0;

    }

    
    // level up method here
}

public class Game extends javax.swing.JFrame {

    int textIndex = 0;
    int dialogueIndex = 1;
    String[] introDialogue = {"Welcome to the Realm of Allyria.",
        "Before your travels begin adventurer...",
        "What is your name?"};
    String[] chooseClassDialogue = {"<html>Before you begin your adventure, you must choose your affinity.",
        "<html>Affinities determine the boost you will receive a certain attribute.",
        "<html>Everytime you level up, you will receive an automatic addition to the attribute of your affinity.",
        "<html>Those who have an affinity to Sanitas have a boost in health.",
        "<html>Those who have an affinity to Celeritas have a boost in agility.",
        "<html>Those who have an affinity to Madeis have a boost in magic.",
        "<html>Those who have an affinity to Tutela have a boost in defense.",
        "<html>Those who have an affinity to Virtus have a boost in strength.",
        "<html>Now choose your affinity wisely. Your affinity will be permanent."};
    String[] editAttributesDialogue = {"<html>Now adjust your attributes wisely. (Click to continue)"};
    String[] adventureBeginsDialogue = {"<html>Once again, welcome to the Realm of Allyria.",
        "<html>Your journey begins in a quiet village in the peaceful grasslands.",
        "<html>Word has spread that the nefarious Demon Lord %s of the Nether Realm has launched an invasion.",
        "<html>Along the way they kidnapped the kingdom's own Princess %s.",
        "<html>Thus it is now your task to venture across the kingdom and enter the Nether Realm to vanquish the evil army of the Demon Lord...",
        "<html>... and save princess."};

    String playerName = "";
    Mob player;

    public Game() {
        initComponents();

        textField_NameField.setVisible(false);
        button_Confirm.setVisible(false);
        panel_ClassMenu.setVisible(false);
        panel_Attributes.setVisible(false);

    }
    
    private String generateName(String type) {
        String generatedName = "";
        
        
        return generatedName;
    }

    public static void linebreak(int type) {

        int margin = 0;
        for (int i = 0; i <= type; i++) {
            if (margin > 1) {
                System.out.println();
                margin = 0;
            }
            System.out.printf("---------------------------------------");
            margin++;
        }
        System.out.println("\n");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Main = new javax.swing.JPanel();
        label_Header = new javax.swing.JLabel();
        textField_NameField = new javax.swing.JTextField();
        button_Confirm = new javax.swing.JButton();
        panel_Game = new javax.swing.JPanel();
        label_Location = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panel_Attributes = new javax.swing.JPanel();
        panel_AttributesActions = new javax.swing.JPanel();
        button_AttributesConfirm = new javax.swing.JButton();
        button_AttributesReset = new javax.swing.JButton();
        label_PlayerName = new javax.swing.JLabel();
        label_PlayerAffinity = new javax.swing.JLabel();
        label_Level = new javax.swing.JLabel();
        label_AvailablePoints = new javax.swing.JLabel();
        label_HealthPoints = new javax.swing.JLabel();
        label_AgilityPoints = new javax.swing.JLabel();
        label_MagicPoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
        panel_AttributesAddition = new javax.swing.JPanel();
        button_HP = new javax.swing.JButton();
        button_AP = new javax.swing.JButton();
        button_MP = new javax.swing.JButton();
        button_DP = new javax.swing.JButton();
        button_SP = new javax.swing.JButton();
        panel_ClassMenu = new javax.swing.JPanel();
        button_Sanitas = new javax.swing.JButton();
        label_Sanitas = new javax.swing.JLabel();
        button_Celeritas = new javax.swing.JButton();
        label_Celeritas = new javax.swing.JLabel();
        button_Madeis = new javax.swing.JButton();
        label_Madeis = new javax.swing.JLabel();
        button_Tutela = new javax.swing.JButton();
        label_Tutela = new javax.swing.JLabel();
        button_Virtus = new javax.swing.JButton();
        label_Virtus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_Main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_MainMouseClicked(evt);
            }
        });
        panel_Main.setLayout(null);

        label_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Header.setText("REALM OF ALLYRIA (CLICK TO START)");
        panel_Main.add(label_Header);
        label_Header.setBounds(6, 6, 520, 47);

        textField_NameField.setText("Adventurer");
        panel_Main.add(textField_NameField);
        textField_NameField.setBounds(6, 59, 520, 22);

        button_Confirm.setText("Confirm");
        button_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ConfirmActionPerformed(evt);
            }
        });
        panel_Main.add(button_Confirm);
        button_Confirm.setBounds(6, 97, 160, 23);

        panel_Game.setBackground(new java.awt.Color(69, 69, 69));
        panel_Game.setLayout(null);

        label_Location.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Location.setText("Location: ");
        panel_Game.add(label_Location);
        label_Location.setBounds(10, 10, 500, 40);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Inventory");
        panel_Game.add(jButton1);
        jButton1.setBounds(360, 250, 150, 40);

        jLabel1.setText("Location");
        panel_Game.add(jLabel1);
        jLabel1.setBounds(270, 60, 240, 180);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Inventory");
        panel_Game.add(jButton2);
        jButton2.setBounds(10, 250, 150, 40);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Inventory");
        panel_Game.add(jButton3);
        jButton3.setBounds(180, 250, 150, 40);

        panel_Main.add(panel_Game);
        panel_Game.setBounds(5, 130, 520, 300);

        panel_Attributes.setBackground(new java.awt.Color(69, 69, 69));
        panel_Attributes.setLayout(null);

        panel_AttributesActions.setOpaque(false);
        panel_AttributesActions.setLayout(null);

        button_AttributesConfirm.setText("<html>Confirm");
        button_AttributesConfirm.setToolTipText("");
        button_AttributesConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesConfirmActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesConfirm);
        button_AttributesConfirm.setBounds(0, 0, 100, 20);

        button_AttributesReset.setText("<html>Reset");
        button_AttributesReset.setToolTipText("");
        button_AttributesReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesResetActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesReset);
        button_AttributesReset.setBounds(0, 30, 100, 20);

        panel_Attributes.add(panel_AttributesActions);
        panel_AttributesActions.setBounds(410, 80, 100, 50);

        label_PlayerName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        label_PlayerName.setForeground(new java.awt.Color(221, 221, 222));
        label_PlayerName.setText("Adventurer");
        panel_Attributes.add(label_PlayerName);
        label_PlayerName.setBounds(10, 10, 500, 30);

        label_PlayerAffinity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_PlayerAffinity.setForeground(new java.awt.Color(221, 221, 222));
        label_PlayerAffinity.setText("Affinity");
        panel_Attributes.add(label_PlayerAffinity);
        label_PlayerAffinity.setBounds(10, 40, 390, 30);

        label_Level.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_Level.setForeground(new java.awt.Color(221, 221, 222));
        label_Level.setText("LVL: 0");
        panel_Attributes.add(label_Level);
        label_Level.setBounds(10, 70, 170, 30);

        label_AvailablePoints.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_AvailablePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AvailablePoints.setText("Available Points: 0");
        panel_Attributes.add(label_AvailablePoints);
        label_AvailablePoints.setBounds(10, 100, 390, 30);

        label_HealthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_HealthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_HealthPoints.setText("Health Points: 0");
        panel_Attributes.add(label_HealthPoints);
        label_HealthPoints.setBounds(10, 140, 500, 30);

        label_AgilityPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_AgilityPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AgilityPoints.setText("Agility Points: 0");
        panel_Attributes.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 170, 500, 30);

        label_MagicPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_MagicPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_MagicPoints.setText("Magic Points: 0");
        panel_Attributes.add(label_MagicPoints);
        label_MagicPoints.setBounds(10, 200, 500, 30);

        label_DefensePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DefensePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_DefensePoints.setText("Defense Points: 0");
        panel_Attributes.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 230, 500, 30);

        label_StrengthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StrengthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_StrengthPoints.setText("Strength Points: 0");
        panel_Attributes.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 260, 500, 30);

        panel_AttributesAddition.setOpaque(false);
        panel_AttributesAddition.setLayout(null);

        button_HP.setText("<html>+");
        button_HP.setToolTipText("");
        button_HP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HPActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_HP);
        button_HP.setBounds(20, 0, 50, 30);

        button_AP.setText("<html>+");
        button_AP.setToolTipText("");
        button_AP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_APActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_AP);
        button_AP.setBounds(20, 30, 50, 30);

        button_MP.setText("<html>+");
        button_MP.setToolTipText("");
        button_MP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MPActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_MP);
        button_MP.setBounds(20, 60, 50, 30);

        button_DP.setText("<html>+");
        button_DP.setToolTipText("");
        button_DP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DPActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_DP);
        button_DP.setBounds(20, 90, 50, 30);

        button_SP.setText("<html>+");
        button_SP.setToolTipText("");
        button_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SPActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_SP);
        button_SP.setBounds(20, 120, 50, 30);

        panel_Attributes.add(panel_AttributesAddition);
        panel_AttributesAddition.setBounds(420, 140, 90, 150);

        panel_Main.add(panel_Attributes);
        panel_Attributes.setBounds(5, 130, 520, 300);

        panel_ClassMenu.setBackground(new java.awt.Color(69, 69, 69));
        panel_ClassMenu.setLayout(null);

        button_Sanitas.setText("<html>Sanitas");
        button_Sanitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SanitasActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Sanitas);
        button_Sanitas.setBounds(10, 10, 72, 23);

        label_Sanitas.setForeground(new java.awt.Color(221, 221, 222));
        label_Sanitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Sanitas.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Healers mainly deal with restoring health which makes them very good at surviving.</p>  </body> </html>");
        label_Sanitas.setToolTipText("");
        panel_ClassMenu.add(label_Sanitas);
        label_Sanitas.setBounds(6, 39, 76, 207);

        button_Celeritas.setText("<html>Celeritas");
        button_Celeritas.setToolTipText("");
        button_Celeritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CeleritasActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Celeritas);
        button_Celeritas.setBounds(110, 10, 76, 23);

        label_Celeritas.setForeground(new java.awt.Color(221, 221, 222));
        label_Celeritas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Celeritas.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Rogues prefer to have the quickest wit and initiative when it comes to battle.</p>  </body> </html>");
        label_Celeritas.setToolTipText("");
        panel_ClassMenu.add(label_Celeritas);
        label_Celeritas.setBounds(110, 40, 76, 207);

        button_Madeis.setText("<html>Madeis");
        button_Madeis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MadeisActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Madeis);
        button_Madeis.setBounds(220, 10, 72, 23);

        label_Madeis.setForeground(new java.awt.Color(221, 221, 222));
        label_Madeis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Madeis.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_Madeis.setToolTipText("");
        panel_ClassMenu.add(label_Madeis);
        label_Madeis.setBounds(220, 40, 76, 207);

        button_Tutela.setText("<html>Tutela");
        button_Tutela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TutelaActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Tutela);
        button_Tutela.setBounds(330, 10, 72, 23);

        label_Tutela.setForeground(new java.awt.Color(221, 221, 222));
        label_Tutela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutela.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_Tutela.setToolTipText("");
        panel_ClassMenu.add(label_Tutela);
        label_Tutela.setBounds(330, 40, 76, 207);

        button_Virtus.setText("Virtus");
        button_Virtus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VirtusActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Virtus);
        button_Virtus.setBounds(430, 10, 72, 23);

        label_Virtus.setForeground(new java.awt.Color(221, 221, 222));
        label_Virtus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Virtus.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Warriors focus on striking hard through brute strength.</p>  </body> </html>");
        label_Virtus.setToolTipText("");
        panel_ClassMenu.add(label_Virtus);
        label_Virtus.setBounds(430, 40, 76, 207);

        panel_Main.add(panel_ClassMenu);
        panel_ClassMenu.setBounds(5, 130, 520, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_MainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_MainMouseClicked

        moveDialogue();

    }//GEN-LAST:event_panel_MainMouseClicked

    private void moveDialogue() {
        String[] loadedDialoge = new String[0];

        // determine which dialogue array to load
        switch (dialogueIndex) {
            case 0 -> {
                loadedDialoge = introDialogue;
            }
            case 1 -> {
                loadedDialoge = chooseClassDialogue;
            }
            case 2 -> {
                loadedDialoge = editAttributesDialogue;
            }
            case 3 -> {
                loadedDialoge = adventureBeginsDialogue;
            }
        }

        // determine the text in the header
        if (dialogueIndex == 3 && textIndex == 2) {
            label_Header.setText(String.format(loadedDialoge[textIndex],"DEMON NAME"));
        } else if (dialogueIndex == 3 && textIndex == 3) {
            label_Header.setText(String.format(loadedDialoge[textIndex],"PRINCESS NAME"));
        } else {
            label_Header.setText(loadedDialoge[textIndex]);
        }

        if (dialogueIndex == 0 && textIndex == 2) {
            textField_NameField.setVisible(true);
            button_Confirm.setVisible(true);
        } else if (dialogueIndex == 1 && textIndex == 8) {
            panel_ClassMenu.setVisible(true);
            player = new Mob();
            player.name = playerName;
        } else if (dialogueIndex == 2 && textIndex == 0 && !panel_Attributes.isVisible()) {
            player.chooseAffinity();
            label_PlayerName.setText(player.name);
            label_PlayerAffinity.setText(player.typeAffinity);

            openAttributesMenu();
        }

        if (textIndex < loadedDialoge.length - 1) {
            textIndex++;
        }
    }

    private void openAttributesMenu() {
        panel_Attributes.setVisible(true);
        label_Level.setText(String.format("Level: %s\n", String.valueOf(player.level)));
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        label_HealthPoints.setText(String.format("Health Points (HP): %s\n", String.valueOf(player.healthPoints)));
        label_AgilityPoints.setText(String.format("Agility Points (AP): %s\n", String.valueOf(player.agiltiyPoints)));
        label_MagicPoints.setText(String.format("Magic Points (MP): %s\n", String.valueOf(player.magicPoints)));
        label_DefensePoints.setText(String.format("Defense Points (DP): %s\n", String.valueOf(player.defensePoints)));
        label_StrengthPoints.setText(String.format("Strength Points (SP): %s\n", String.valueOf(player.strengthPoints)));

        ShowAvailableAttributePoints();
    }

    private void button_ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ConfirmActionPerformed

        // ask for adventurer name
        if (dialogueIndex == 0 && textIndex == 2) {
            playerName = textField_NameField.getText();

            textField_NameField.setVisible(false);
            button_Confirm.setVisible(false);

            nextDialogueArray();
        }

    }//GEN-LAST:event_button_ConfirmActionPerformed

    private void nextDialogueArray() {
        textIndex = 0;
        dialogueIndex++;
        moveDialogue();
    }

    private void button_VirtusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VirtusActionPerformed

        setPlayerClass("Virtus");

    }//GEN-LAST:event_button_VirtusActionPerformed

    private void button_MadeisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MadeisActionPerformed

        setPlayerClass("Madeis");

    }//GEN-LAST:event_button_MadeisActionPerformed

    private void button_CeleritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CeleritasActionPerformed

        setPlayerClass("Celeritas");


    }//GEN-LAST:event_button_CeleritasActionPerformed

    private void button_SanitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SanitasActionPerformed

        setPlayerClass("Sanitas");

    }//GEN-LAST:event_button_SanitasActionPerformed

    private void button_TutelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TutelaActionPerformed

        setPlayerClass("Tutela");

    }//GEN-LAST:event_button_TutelaActionPerformed

    private void setPlayerClass(String playerClass) {
        player.typeAffinity = playerClass;
        panel_ClassMenu.setVisible(false);
        nextDialogueArray();
    }

    private void button_HPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HPActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Sanitas");
        }

    }//GEN-LAST:event_button_HPActionPerformed

    private void button_APActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_APActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Celeritas");
        }

    }//GEN-LAST:event_button_APActionPerformed

    private void button_MPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MPActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Madeis");
        }

    }//GEN-LAST:event_button_MPActionPerformed

    private void button_DPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DPActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Tutela");
        }

    }//GEN-LAST:event_button_DPActionPerformed

    private void button_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SPActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Virtus");
        }

    }//GEN-LAST:event_button_SPActionPerformed

    private void button_AttributesConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesConfirmActionPerformed

        player.confirmAttributeChanges();

        if (player.attributePoints <= 0) {
            openAttributesMenu();
        } else {
            setAttributesAddition();
        }

        panel_AttributesActions.setVisible(false);

        if (dialogueIndex == 2 && textIndex == 0) {

            panel_Attributes.setVisible(false);

            nextDialogueArray();
        }

    }//GEN-LAST:event_button_AttributesConfirmActionPerformed

    private void button_AttributesResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesResetActionPerformed

        player.resetAttributeChanges();
        openAttributesMenu();

    }//GEN-LAST:event_button_AttributesResetActionPerformed

    private void attributeAddition(String attribute) {

        player.attributePoints--;

        player.attributeAddition(attribute);

        setAttributesAddition();

    }

    private void setAttributesAddition() {
        panel_Attributes.setVisible(true);
        label_Level.setText(String.format("Level: %s\n", String.valueOf(player.level)));
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        if (player.HPAddition > 0) {
            label_HealthPoints.setText(String.format("Health Points (HP): %s (+%s)\n",
                    String.valueOf(player.healthPoints),
                    String.valueOf(player.HPAddition)));
        }
        if (player.APAddition > 0) {
            label_AgilityPoints.setText(String.format("Agility Points (AP): %s (+%s)\n",
                    String.valueOf(player.agiltiyPoints),
                    String.valueOf(player.APAddition)));
        }
        if (player.MPAddition > 0) {
            label_MagicPoints.setText(String.format("Magic Points (MP): %s (+%s)\n",
                    String.valueOf(player.magicPoints),
                    String.valueOf(player.MPAddition)));
        }
        if (player.DPAddition > 0) {
            label_DefensePoints.setText(String.format("Defense Points (DP): %s (+%s)\n",
                    String.valueOf(player.defensePoints),
                    String.valueOf(player.DPAddition)));
        }
        if (player.SPAddition > 0) {
            label_StrengthPoints.setText(String.format("Strength Points (SP): %s (+%s)\n",
                    String.valueOf(player.strengthPoints),
                    String.valueOf(player.SPAddition)));
        }

        ShowAvailableAttributePoints();
    }

    public void ShowAvailableAttributePoints() {

        if (player.attributePoints > 0) {
            panel_AttributesAddition.setVisible(true);
            panel_AttributesActions.setVisible(true);
        } else {
            panel_AttributesAddition.setVisible(false);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Game().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_AP;
    private javax.swing.JButton button_AttributesConfirm;
    private javax.swing.JButton button_AttributesReset;
    private javax.swing.JButton button_Celeritas;
    private javax.swing.JButton button_Confirm;
    private javax.swing.JButton button_DP;
    private javax.swing.JButton button_HP;
    private javax.swing.JButton button_MP;
    private javax.swing.JButton button_Madeis;
    private javax.swing.JButton button_SP;
    private javax.swing.JButton button_Sanitas;
    private javax.swing.JButton button_Tutela;
    private javax.swing.JButton button_Virtus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label_AgilityPoints;
    private javax.swing.JLabel label_AvailablePoints;
    private javax.swing.JLabel label_Celeritas;
    private javax.swing.JLabel label_DefensePoints;
    private javax.swing.JLabel label_Header;
    private javax.swing.JLabel label_HealthPoints;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Location;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_MagicPoints;
    private javax.swing.JLabel label_PlayerAffinity;
    private javax.swing.JLabel label_PlayerName;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_StrengthPoints;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JPanel panel_Attributes;
    private javax.swing.JPanel panel_AttributesActions;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_ClassMenu;
    private javax.swing.JPanel panel_Game;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JTextField textField_NameField;
    // End of variables declaration//GEN-END:variables
}
