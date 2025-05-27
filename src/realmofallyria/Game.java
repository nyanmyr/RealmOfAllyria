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

    // current
    int currentHP;
    int currentMP;

    int level;
    int attributePoints;

    // attributes
    int healthPoints;
    int intelligencePoints;
    int agilityPoints;
    int defensePoints;
    int strengthPoints;

    // gear attribute addition: the attributes added from certain gears
    int HPGearAddition;
    int IPGearAddition;
    int APGearAddition;
    int DPGearAddition;
    int SPGearAddition;

    // attributes addition (used in attribute menu)
    int attributePointsAddition;

    int HPAddition;
    int IPAddition;
    int APAddition;
    int DPAddition;
    int SPAddition;

    String name;
    String typeAffinity;

    String equippedArmor;
    String equippedWeapon;

    public void chooseAffinity() {
        level = 1;
        attributePoints = 5;

        // attributes
        healthPoints = 10;
        agilityPoints = 1;
        intelligencePoints = 1;
        defensePoints = 1;
        strengthPoints = 1;

        currentHP = healthPoints;
        currentMP = intelligencePoints * 5;

        switch (typeAffinity) {
            case "Sanitas" -> {
                healthPoints += 10;
            }
            case "Celeritas" -> {
                agilityPoints += 1;
            }
            case "Madeis" -> {
                intelligencePoints += 1;
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
                IPAddition += 1;
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
        intelligencePoints += IPAddition;
        agilityPoints += APAddition;
        defensePoints += DPAddition;
        strengthPoints += SPAddition;

        currentHP += HPAddition;
        currentMP += IPAddition * 5;

        HPAddition = 0;
        IPAddition = 0;
        APAddition = 0;
        DPAddition = 0;
        SPAddition = 0;

    }

    public void resetAttributeChanges() {

        attributePoints += attributePointsAddition;
        attributePointsAddition = 0;

        HPAddition = 0;
        IPAddition = 0;
        APAddition = 0;
        DPAddition = 0;
        SPAddition = 0;

    }

    public void equipGear(String chosenArmor, int armorLevel, String chosenWeapon, int weaponLevel) {

        equippedArmor = chosenArmor;
        equippedWeapon = chosenWeapon;

        HPGearAddition = 0;
        IPGearAddition = 0;
        APGearAddition = 0;
        DPGearAddition = 0;
        SPGearAddition = 0;

        switch (chosenArmor) {
            case "Leather Armor" -> {
                HPGearAddition += armorLevel * 5;
            }
        }

        switch (chosenWeapon) {
            case "Iron Sword" -> {
                SPGearAddition += weaponLevel * 2;
            }
            case "Simple Bow" -> {
                APGearAddition += weaponLevel * 2;
            }
            case "Crude Wand" -> {
                IPGearAddition += weaponLevel * 2;
            }
        }

        System.out.println("HPGearAddition: " + HPGearAddition);

        healthPoints += HPGearAddition;
        intelligencePoints += IPGearAddition;
        agilityPoints += APGearAddition;
        defensePoints += DPGearAddition;
        strengthPoints += SPGearAddition;

        currentHP += HPGearAddition;
        currentMP += IPGearAddition * 5;

    }

    // level up method here
}

public class Game extends javax.swing.JFrame {

    // <editor-fold desc="variables">
    int textIndex = 0;
    int dialogueIndex = 1;

    // intro/ opening dialogue
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
    String[] chooseGearDialogue = {"<html>The world out there is dangerous.",
        "You will need something to defend yourself.",
        "Choose any of the following weapons to your liking..."};
    String[] bonusArmorDialogue = {"<html>This weapon is not much.",
        "So I will provide you a full set of leather armor along with it.",
        "Safe travels adventurer..."};
    String[] adventureBeginsDialogue = {"<html>Once again, welcome to the Realm of Allyria.",
        "<html>Your journey begins in a quiet village in the peaceful grasslands.",
        "<html>Word has spread that the nefarious Demon Lord %s of the Nether Realm has launched an invasion.",
        "<html>Along the way they kidnapped the kingdom's own Princess %s.",
        "<html>Thus it is now your task to venture across the kingdom and enter the Nether Realm to vanquish the evil army of the Demon Lord...",
        "<html>... and save the princess."};

    String playerName = "";
    Mob player;

    boolean introSequenceFinished = false;
    // </editor-fold>

    public Game() {
        initComponents();

        textField_NameField.setVisible(false);
        button_DialogueConfirm.setVisible(false);
        panel_ClassMenu.setVisible(false);
        panel_Attributes.setVisible(false);
        panel_StartingGear.setVisible(false);
        panel_Game.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Travel.setVisible(false);
        button_Return.setVisible(false);

    }

    private String generateName(String type) {
        // use linked list

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
        button_Return = new javax.swing.JButton();
        label_Header = new javax.swing.JLabel();
        textField_NameField = new javax.swing.JTextField();
        button_DialogueConfirm = new javax.swing.JButton();
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
        label_IntelligencePoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
        panel_GearAddition = new javax.swing.JPanel();
        label_GearHP = new javax.swing.JLabel();
        label_GearAP = new javax.swing.JLabel();
        label_GearMP = new javax.swing.JLabel();
        label_GearDP = new javax.swing.JLabel();
        label_GearSP = new javax.swing.JLabel();
        panel_Total = new javax.swing.JPanel();
        label_TotalHP = new javax.swing.JLabel();
        label_TotalAP = new javax.swing.JLabel();
        label_TotalMP = new javax.swing.JLabel();
        label_TotalDP = new javax.swing.JLabel();
        label_TotalSP = new javax.swing.JLabel();
        panel_AttributesAddition = new javax.swing.JPanel();
        button_HPAddition = new javax.swing.JButton();
        button_APAddition = new javax.swing.JButton();
        button_IPAddition = new javax.swing.JButton();
        button_DPAddition = new javax.swing.JButton();
        button_SPAddition = new javax.swing.JButton();
        panel_StartingGear = new javax.swing.JPanel();
        button_IronSword = new javax.swing.JButton();
        label_IronSword = new javax.swing.JLabel();
        button_SimpleBow = new javax.swing.JButton();
        label_SimpleBow = new javax.swing.JLabel();
        button_CrudeWand = new javax.swing.JButton();
        label_CrudeWand = new javax.swing.JLabel();
        panel_Game = new javax.swing.JPanel();
        label_GameMP = new javax.swing.JLabel();
        label_GameHP = new javax.swing.JLabel();
        label_Location = new javax.swing.JLabel();
        label_LocationImage = new javax.swing.JLabel();
        label_Travel = new javax.swing.JButton();
        label_Inventory = new javax.swing.JButton();
        label_Status = new javax.swing.JButton();
        panel_Inventory = new javax.swing.JPanel();
        label_Armor = new javax.swing.JLabel();
        label_PDef = new javax.swing.JLabel();
        label_MDef = new javax.swing.JLabel();
        label_EquippedWeapon = new javax.swing.JLabel();
        label_PDmg = new javax.swing.JLabel();
        label_MDmg = new javax.swing.JLabel();
        label_CC = new javax.swing.JLabel();
        panel_Travel = new javax.swing.JPanel();
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

        button_Return.setText("Return");
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Main.add(button_Return);
        button_Return.setBounds(420, 90, 100, 27);

        label_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Header.setText("REALM OF ALLYRIA (CLICK TO START)");
        label_Header.setToolTipText("");
        panel_Main.add(label_Header);
        label_Header.setBounds(6, 6, 520, 47);

        textField_NameField.setText("Adventurer");
        panel_Main.add(textField_NameField);
        textField_NameField.setBounds(6, 59, 520, 26);

        button_DialogueConfirm.setText("Confirm");
        button_DialogueConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DialogueConfirmActionPerformed(evt);
            }
        });
        panel_Main.add(button_DialogueConfirm);
        button_DialogueConfirm.setBounds(6, 97, 160, 27);

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
        label_PlayerName.setBounds(10, 10, 390, 30);

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
        label_HealthPoints.setText("Health Points:");
        panel_Attributes.add(label_HealthPoints);
        label_HealthPoints.setBounds(10, 140, 260, 30);

        label_AgilityPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_AgilityPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AgilityPoints.setText("Agility Points:");
        panel_Attributes.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 170, 260, 30);

        label_IntelligencePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_IntelligencePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_IntelligencePoints.setText("Intelligence Points:");
        panel_Attributes.add(label_IntelligencePoints);
        label_IntelligencePoints.setBounds(10, 200, 260, 30);

        label_DefensePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DefensePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_DefensePoints.setText("Defense Points:");
        panel_Attributes.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 230, 260, 30);

        label_StrengthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StrengthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_StrengthPoints.setText("Strength Points:");
        panel_Attributes.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 260, 260, 30);

        panel_GearAddition.setOpaque(false);
        panel_GearAddition.setLayout(null);

        label_GearHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearHP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearHP.setText("(+0)");
        panel_GearAddition.add(label_GearHP);
        label_GearHP.setBounds(10, 0, 40, 30);

        label_GearAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearAP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearAP.setText("(+0)");
        panel_GearAddition.add(label_GearAP);
        label_GearAP.setBounds(10, 30, 40, 30);

        label_GearMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearMP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearMP.setText("(+0)");
        panel_GearAddition.add(label_GearMP);
        label_GearMP.setBounds(10, 60, 40, 30);

        label_GearDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearDP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearDP.setText("(+0)");
        panel_GearAddition.add(label_GearDP);
        label_GearDP.setBounds(10, 90, 40, 30);

        label_GearSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GearSP.setForeground(new java.awt.Color(221, 221, 222));
        label_GearSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearSP.setText("(+0)");
        panel_GearAddition.add(label_GearSP);
        label_GearSP.setBounds(10, 120, 40, 30);

        panel_Attributes.add(panel_GearAddition);
        panel_GearAddition.setBounds(280, 140, 60, 150);

        panel_Total.setOpaque(false);
        panel_Total.setLayout(null);

        label_TotalHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalHP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalHP.setText("0");
        panel_Total.add(label_TotalHP);
        label_TotalHP.setBounds(10, 0, 40, 30);

        label_TotalAP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalAP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalAP.setText("0");
        panel_Total.add(label_TotalAP);
        label_TotalAP.setBounds(10, 30, 40, 30);

        label_TotalMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalMP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalMP.setText("0");
        panel_Total.add(label_TotalMP);
        label_TotalMP.setBounds(10, 60, 40, 30);

        label_TotalDP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalDP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalDP.setText("0");
        panel_Total.add(label_TotalDP);
        label_TotalDP.setBounds(10, 90, 40, 30);

        label_TotalSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_TotalSP.setForeground(new java.awt.Color(221, 221, 222));
        label_TotalSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalSP.setText("0");
        panel_Total.add(label_TotalSP);
        label_TotalSP.setBounds(10, 120, 40, 30);

        panel_Attributes.add(panel_Total);
        panel_Total.setBounds(350, 140, 60, 150);

        panel_AttributesAddition.setOpaque(false);
        panel_AttributesAddition.setLayout(null);

        button_HPAddition.setText("<html>+");
        button_HPAddition.setToolTipText("");
        button_HPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_HPAddition);
        button_HPAddition.setBounds(20, 0, 50, 30);

        button_APAddition.setText("<html>+");
        button_APAddition.setToolTipText("");
        button_APAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_APAdditionAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_APAddition);
        button_APAddition.setBounds(20, 30, 50, 30);

        button_IPAddition.setText("<html>+");
        button_IPAddition.setToolTipText("");
        button_IPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_IPAddition);
        button_IPAddition.setBounds(20, 60, 50, 30);

        button_DPAddition.setText("<html>+");
        button_DPAddition.setToolTipText("");
        button_DPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_DPAddition);
        button_DPAddition.setBounds(20, 90, 50, 30);

        button_SPAddition.setText("<html>+");
        button_SPAddition.setToolTipText("");
        button_SPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAddition.add(button_SPAddition);
        button_SPAddition.setBounds(20, 120, 50, 30);

        panel_Attributes.add(panel_AttributesAddition);
        panel_AttributesAddition.setBounds(420, 140, 90, 150);

        panel_Main.add(panel_Attributes);
        panel_Attributes.setBounds(5, 130, 520, 300);

        panel_StartingGear.setBackground(new java.awt.Color(69, 69, 69));
        panel_StartingGear.setLayout(null);

        button_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Iron Sword</p>  </body> </html>");
        button_IronSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IronSwordActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_IronSword);
        button_IronSword.setBounds(120, 10, 80, 50);

        label_IronSword.setForeground(new java.awt.Color(221, 221, 222));
        label_IronSword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Healers mainly deal with restoring health which makes them very good at surviving.</p>  </body> </html>");
        label_IronSword.setToolTipText("");
        panel_StartingGear.add(label_IronSword);
        label_IronSword.setBounds(120, 60, 76, 207);

        button_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Simple Bow</p>  </body> </html>");
        button_SimpleBow.setToolTipText("");
        button_SimpleBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SimpleBowActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_SimpleBow);
        button_SimpleBow.setBounds(230, 10, 76, 50);

        label_SimpleBow.setForeground(new java.awt.Color(221, 221, 222));
        label_SimpleBow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Rogues prefer to have the quickest wit and initiative when it comes to battle.</p>  </body> </html>");
        label_SimpleBow.setToolTipText("");
        panel_StartingGear.add(label_SimpleBow);
        label_SimpleBow.setBounds(230, 60, 76, 207);

        button_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Crude Wand</p>  </body> </html>");
        button_CrudeWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CrudeWandActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_CrudeWand);
        button_CrudeWand.setBounds(340, 10, 76, 50);

        label_CrudeWand.setForeground(new java.awt.Color(221, 221, 222));
        label_CrudeWand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Mages specialize in the use of magical spells to their advantage.</p>  </body> </html>");
        label_CrudeWand.setToolTipText("");
        panel_StartingGear.add(label_CrudeWand);
        label_CrudeWand.setBounds(340, 60, 76, 207);

        panel_Main.add(panel_StartingGear);
        panel_StartingGear.setBounds(5, 130, 520, 300);

        panel_Game.setBackground(new java.awt.Color(69, 69, 69));
        panel_Game.setLayout(null);

        label_GameMP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameMP.setForeground(new java.awt.Color(221, 221, 222));
        label_GameMP.setText("MP:");
        panel_Game.add(label_GameMP);
        label_GameMP.setBounds(10, 100, 230, 40);

        label_GameHP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_GameHP.setForeground(new java.awt.Color(221, 221, 222));
        label_GameHP.setText("HP:");
        panel_Game.add(label_GameHP);
        label_GameHP.setBounds(10, 60, 230, 40);

        label_Location.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Location.setForeground(new java.awt.Color(221, 221, 222));
        label_Location.setText("Location: ");
        panel_Game.add(label_Location);
        label_Location.setBounds(10, 10, 500, 40);

        label_LocationImage.setText("Location");
        panel_Game.add(label_LocationImage);
        label_LocationImage.setBounds(270, 60, 240, 180);

        label_Travel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Travel.setText("Travel");
        panel_Game.add(label_Travel);
        label_Travel.setBounds(10, 250, 150, 40);

        label_Inventory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Inventory.setText("Inventory");
        panel_Game.add(label_Inventory);
        label_Inventory.setBounds(180, 250, 150, 40);

        label_Status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Status.setText("Status");
        label_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_StatusActionPerformed(evt);
            }
        });
        panel_Game.add(label_Status);
        label_Status.setBounds(360, 250, 150, 40);

        panel_Main.add(panel_Game);
        panel_Game.setBounds(5, 130, 520, 300);

        panel_Inventory.setBackground(new java.awt.Color(69, 69, 69));
        panel_Inventory.setLayout(null);

        label_Armor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Armor.setText("Equipped Armor: ");
        panel_Inventory.add(label_Armor);
        label_Armor.setBounds(110, 10, 270, 50);

        label_PDef.setText("Physical Defence (PDef): ");
        panel_Inventory.add(label_PDef);
        label_PDef.setBounds(110, 60, 270, 30);

        label_MDef.setText("Magical Defense (MDef): ");
        panel_Inventory.add(label_MDef);
        label_MDef.setBounds(110, 90, 270, 30);

        label_EquippedWeapon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_EquippedWeapon.setText("Equipped Weapon: ");
        panel_Inventory.add(label_EquippedWeapon);
        label_EquippedWeapon.setBounds(110, 120, 270, 50);

        label_PDmg.setText("Physical Damage (PDmg): ");
        panel_Inventory.add(label_PDmg);
        label_PDmg.setBounds(110, 170, 270, 30);

        label_MDmg.setText("Magical Damage (MDmg): ");
        panel_Inventory.add(label_MDmg);
        label_MDmg.setBounds(110, 200, 270, 30);

        label_CC.setText("Critical Chance (CC): ");
        panel_Inventory.add(label_CC);
        label_CC.setBounds(110, 230, 270, 30);

        panel_Main.add(panel_Inventory);
        panel_Inventory.setBounds(5, 130, 520, 300);

        panel_Travel.setBackground(new java.awt.Color(69, 69, 69));
        panel_Travel.setLayout(null);
        panel_Main.add(panel_Travel);
        panel_Travel.setBounds(5, 130, 520, 300);

        panel_ClassMenu.setBackground(new java.awt.Color(69, 69, 69));
        panel_ClassMenu.setLayout(null);

        button_Sanitas.setText("<html>Sanitas");
        button_Sanitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SanitasActionPerformed(evt);
            }
        });
        panel_ClassMenu.add(button_Sanitas);
        button_Sanitas.setBounds(10, 10, 76, 27);

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
        button_Celeritas.setBounds(110, 10, 76, 27);

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
        button_Madeis.setBounds(220, 10, 76, 27);

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
        button_Tutela.setBounds(330, 10, 76, 27);

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
        button_Virtus.setBounds(430, 10, 76, 27);

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
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        if (!introSequenceFinished) {

            moveDialogue();

        } else {

            label_GameHP.setText(String.format("Health Points (HP): %s / %s\n", String.valueOf(player.currentHP),
                    String.valueOf(player.healthPoints)));
            label_GameMP.setText(String.format("Magic Points (MP): %s / %s\n", String.valueOf(player.currentMP),
                    String.valueOf(player.intelligencePoints * 5)));

        }

    }//GEN-LAST:event_panel_MainMouseClicked

    private void moveDialogue() {

        if (!(dialogueIndex == 5 && textIndex == 5)) {
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
                    loadedDialoge = chooseGearDialogue;
                }
                case 4 -> {
                    loadedDialoge = bonusArmorDialogue;
                }
                case 5 -> {
                    loadedDialoge = adventureBeginsDialogue;
                }
            }

            // determine the text in the header
            if (dialogueIndex == 3 && textIndex == 2) {
                label_Header.setText(String.format(loadedDialoge[textIndex], "DEMON NAME"));
            } else if (dialogueIndex == 3 && textIndex == 3) {
                label_Header.setText(String.format(loadedDialoge[textIndex], "PRINCESS NAME"));
            } else {
                label_Header.setText(loadedDialoge[textIndex]);
            }

            if (dialogueIndex == 0 && textIndex == 2) {
                textField_NameField.setVisible(true);
                button_DialogueConfirm.setVisible(true);
            } else if (dialogueIndex == 1 && textIndex == 8) {
                panel_ClassMenu.setVisible(true);
                player = new Mob();
                player.name = playerName;
            } else if (dialogueIndex == 2 && textIndex == 0 && !panel_Attributes.isVisible()) {
                player.chooseAffinity();
                label_PlayerName.setText(player.name);
                label_PlayerAffinity.setText(player.typeAffinity);
                openAttributesMenu();
            } else if (dialogueIndex == 3 && textIndex == 2) {
                panel_StartingGear.setVisible(true);
            } else if (dialogueIndex == 4 && textIndex == 2) {
                button_DialogueConfirm.setVisible(true);
                button_DialogueConfirm.setText("<html>Begin Adventure");
            }

            if (textIndex < loadedDialoge.length - 1) {
                textIndex++;
            }
        } else {

            label_Header.setText("");
            openGameScreen();
            label_GameHP.setText(String.format("Health Points (HP): %s / %s\n", String.valueOf(player.currentHP),
                    String.valueOf(player.healthPoints)));
            label_GameMP.setText(String.format("Magic Points (MP): %s / %s\n", String.valueOf(player.currentMP),
                    String.valueOf(player.intelligencePoints * 5)));
            introSequenceFinished = true;

        }
    }

    private void openGameScreen() {

        panel_Travel.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Attributes.setVisible(false);
        button_Return.setVisible(false);
        panel_Game.setVisible(true);
    }

    private void openAttributesMenu() {
        panel_Attributes.setVisible(true);
        label_Level.setText(String.format("Level: %s\n", String.valueOf(player.level)));
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        label_TotalHP.setText(String.valueOf(player.healthPoints));
        label_TotalAP.setText(String.valueOf(player.agilityPoints));
        label_TotalMP.setText(String.valueOf(player.intelligencePoints));
        label_TotalDP.setText(String.valueOf(player.defensePoints));
        label_TotalSP.setText(String.valueOf(player.strengthPoints));

        ShowAvailableAttributePoints();
    }

    private void button_DialogueConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DialogueConfirmActionPerformed

        // ask for adventurer name
        if (dialogueIndex == 0 && textIndex == 2) {
            playerName = textField_NameField.getText();

            textField_NameField.setVisible(false);

        }

        button_DialogueConfirm.setVisible(false);
        nextDialogueArray();


    }//GEN-LAST:event_button_DialogueConfirmActionPerformed

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

    private void button_HPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Sanitas");
        }

    }//GEN-LAST:event_button_HPAdditionActionPerformed

    private void button_APAdditionAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_APAdditionAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Celeritas");
        }

    }//GEN-LAST:event_button_APAdditionAdditionActionPerformed

    private void button_IPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Madeis");
        }

    }//GEN-LAST:event_button_IPAdditionActionPerformed

    private void button_DPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Tutela");
        }

    }//GEN-LAST:event_button_DPAdditionActionPerformed

    private void button_SPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Virtus");
        }

    }//GEN-LAST:event_button_SPAdditionActionPerformed

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

        if (player.HPAddition > 0
                || player.APAddition > 0
                || player.IPAddition > 0
                || player.DPAddition > 0
                || player.SPAddition > 0) {
            player.resetAttributeChanges();
            button_HPAddition.setText("+");
            button_APAddition.setText("+");
            button_IPAddition.setText("+");
            button_DPAddition.setText("+");
            button_SPAddition.setText("+");
            openAttributesMenu();
        }

    }//GEN-LAST:event_button_AttributesResetActionPerformed

    private void label_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_StatusActionPerformed

        panel_Game.setVisible(false);
        button_Return.setVisible(true);
        openAttributesMenu();

    }//GEN-LAST:event_label_StatusActionPerformed

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed

        openGameScreen();

    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_IronSwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IronSwordActionPerformed

        setPlayerStartingWeapon("Iron Sword");

    }//GEN-LAST:event_button_IronSwordActionPerformed

    private void button_SimpleBowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SimpleBowActionPerformed

        setPlayerStartingWeapon("Simple Bow");

    }//GEN-LAST:event_button_SimpleBowActionPerformed

    private void button_CrudeWandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CrudeWandActionPerformed

        setPlayerStartingWeapon("Crude Wand");

    }//GEN-LAST:event_button_CrudeWandActionPerformed

    private void setPlayerStartingWeapon(String startingWeapon) {

        player.equipGear("Leather Armor", 1, startingWeapon, 1);
        panel_StartingGear.setVisible(false);
        nextDialogueArray();

    }

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
            button_HPAddition.setText(String.valueOf(player.HPAddition));
        }
        if (player.APAddition > 0) {
            button_APAddition.setText(String.valueOf(player.APAddition));
        }
        if (player.IPAddition > 0) {
            button_IPAddition.setText(String.valueOf(player.IPAddition));
        }
        if (player.DPAddition > 0) {
            button_DPAddition.setText(String.valueOf(player.DPAddition));
        }
        if (player.SPAddition > 0) {
            button_SPAddition.setText(String.valueOf(player.SPAddition));
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

    // <editor-fold desc="java swing variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_APAddition;
    private javax.swing.JButton button_AttributesConfirm;
    private javax.swing.JButton button_AttributesReset;
    private javax.swing.JButton button_Celeritas;
    private javax.swing.JButton button_CrudeWand;
    private javax.swing.JButton button_DPAddition;
    private javax.swing.JButton button_DialogueConfirm;
    private javax.swing.JButton button_HPAddition;
    private javax.swing.JButton button_IPAddition;
    private javax.swing.JButton button_IronSword;
    private javax.swing.JButton button_Madeis;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_SPAddition;
    private javax.swing.JButton button_Sanitas;
    private javax.swing.JButton button_SimpleBow;
    private javax.swing.JButton button_Tutela;
    private javax.swing.JButton button_Virtus;
    private javax.swing.JLabel label_AgilityPoints;
    private javax.swing.JLabel label_Armor;
    private javax.swing.JLabel label_AvailablePoints;
    private javax.swing.JLabel label_CC;
    private javax.swing.JLabel label_Celeritas;
    private javax.swing.JLabel label_CrudeWand;
    private javax.swing.JLabel label_DefensePoints;
    private javax.swing.JLabel label_EquippedWeapon;
    private javax.swing.JLabel label_GameHP;
    private javax.swing.JLabel label_GameMP;
    private javax.swing.JLabel label_GearAP;
    private javax.swing.JLabel label_GearDP;
    private javax.swing.JLabel label_GearHP;
    private javax.swing.JLabel label_GearMP;
    private javax.swing.JLabel label_GearSP;
    private javax.swing.JLabel label_Header;
    private javax.swing.JLabel label_HealthPoints;
    private javax.swing.JLabel label_IntelligencePoints;
    private javax.swing.JButton label_Inventory;
    private javax.swing.JLabel label_IronSword;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Location;
    private javax.swing.JLabel label_LocationImage;
    private javax.swing.JLabel label_MDef;
    private javax.swing.JLabel label_MDmg;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_PDef;
    private javax.swing.JLabel label_PDmg;
    private javax.swing.JLabel label_PlayerAffinity;
    private javax.swing.JLabel label_PlayerName;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_SimpleBow;
    private javax.swing.JButton label_Status;
    private javax.swing.JLabel label_StrengthPoints;
    private javax.swing.JLabel label_TotalAP;
    private javax.swing.JLabel label_TotalDP;
    private javax.swing.JLabel label_TotalHP;
    private javax.swing.JLabel label_TotalMP;
    private javax.swing.JLabel label_TotalSP;
    private javax.swing.JButton label_Travel;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JPanel panel_Attributes;
    private javax.swing.JPanel panel_AttributesActions;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_ClassMenu;
    private javax.swing.JPanel panel_Game;
    private javax.swing.JPanel panel_GearAddition;
    private javax.swing.JPanel panel_Inventory;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JPanel panel_StartingGear;
    private javax.swing.JPanel panel_Total;
    private javax.swing.JPanel panel_Travel;
    private javax.swing.JTextField textField_NameField;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}
