package realmofallyria;

import java.util.Scanner;

class Mob {

    int level;
    int attributePoints;

    // attributes
    int healthPoints;
    int magicPoints;
    int agiltiyPoints;
    int defensePoints;
    int strengthPoints;

    String name;
    String typeAffinity;

    public void chooseAffinity() {
        level = 1;
        attributePoints = 1;

        // attributes
        healthPoints = 20;
        agiltiyPoints = 2;
        magicPoints = 2;
        defensePoints = 2;
        strengthPoints = 2;

        System.out.println("typeAffinity: ");
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

}

public class Game extends javax.swing.JFrame {

    int textIndex = 0;
    int dialogueIndex = 0;
    String[] introDialogue = {"Welcome to the Realm of Allyria.",
        "Before your travels begin adventurer...",
        "What is your name?"};
    String[] chooseClassDialogue = {"<html>Before you begin your adventure, you must choose your affinity.",
        "Affinities determine you a boost to a certain attributes.",
        "Those who have an affinity to Sanitas have a boost in health,",
        "Those who have an affinity to Celeritas have a boost in agility,",
        "Those who have an affinity to Madeis have a boost in magic,",
        "Those who have an affinity to Tutela have a boost in defense,",
        "Those who have an affinity to Virtus have a boost in strength,",
        "Now choose your affinity wisely. It will be permanent."};
    String[] editAttributesDialogue = {"<html>Now adjust your attributes wisely."};

    String playerName = "";
    Mob player;

    public Game() {
        initComponents();

        textField_NameField.setVisible(false);
        button_Confirm.setVisible(false);
        panel_ClassMenu.setVisible(false);
        panel_Attributes.setVisible(false);

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
        panel_Attributes = new javax.swing.JPanel();
        panel_AttributesAddition = new javax.swing.JPanel();
        button_HP = new javax.swing.JButton();
        button_AP = new javax.swing.JButton();
        button_MP = new javax.swing.JButton();
        button_DP = new javax.swing.JButton();
        button_SP = new javax.swing.JButton();
        label_Level = new javax.swing.JLabel();
        label_AvailablePoints = new javax.swing.JLabel();
        label_HealthPoints = new javax.swing.JLabel();
        label_AgilityPoints = new javax.swing.JLabel();
        label_MagicPoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
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
        textField_NameField.setBounds(6, 59, 520, 26);

        button_Confirm.setText("Confirm");
        button_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ConfirmActionPerformed(evt);
            }
        });
        panel_Main.add(button_Confirm);
        button_Confirm.setBounds(6, 97, 160, 27);

        panel_Attributes.setBackground(new java.awt.Color(69, 69, 69));
        panel_Attributes.setLayout(null);

        panel_AttributesAddition.setOpaque(false);
        panel_AttributesAddition.setLayout(null);

        button_HP.setText("+");
        panel_AttributesAddition.add(button_HP);
        button_HP.setBounds(0, 0, 30, 30);

        button_AP.setText("+");
        panel_AttributesAddition.add(button_AP);
        button_AP.setBounds(0, 30, 30, 30);

        button_MP.setText("+");
        panel_AttributesAddition.add(button_MP);
        button_MP.setBounds(0, 60, 30, 30);

        button_DP.setText("+");
        panel_AttributesAddition.add(button_DP);
        button_DP.setBounds(0, 90, 30, 30);

        button_SP.setText("+");
        panel_AttributesAddition.add(button_SP);
        button_SP.setBounds(0, 120, 30, 30);

        panel_Attributes.add(panel_AttributesAddition);
        panel_AttributesAddition.setBounds(480, 80, 30, 150);

        label_Level.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_Level.setForeground(new java.awt.Color(221, 221, 222));
        label_Level.setText("LVL: 0");
        panel_Attributes.add(label_Level);
        label_Level.setBounds(10, 10, 170, 30);

        label_AvailablePoints.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_AvailablePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AvailablePoints.setText("Available Points: 0");
        panel_Attributes.add(label_AvailablePoints);
        label_AvailablePoints.setBounds(10, 40, 500, 30);

        label_HealthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_HealthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_HealthPoints.setText("Health Points: 0");
        panel_Attributes.add(label_HealthPoints);
        label_HealthPoints.setBounds(10, 80, 500, 30);

        label_AgilityPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_AgilityPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_AgilityPoints.setText("Agility Points: 0");
        panel_Attributes.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 110, 500, 30);

        label_MagicPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_MagicPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_MagicPoints.setText("Magic Points: 0");
        panel_Attributes.add(label_MagicPoints);
        label_MagicPoints.setBounds(10, 140, 500, 30);

        label_DefensePoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_DefensePoints.setForeground(new java.awt.Color(221, 221, 222));
        label_DefensePoints.setText("Defense Points: 0");
        panel_Attributes.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 170, 500, 30);

        label_StrengthPoints.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_StrengthPoints.setForeground(new java.awt.Color(221, 221, 222));
        label_StrengthPoints.setText("Strength Points: 0");
        panel_Attributes.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 200, 500, 30);

        panel_Main.add(panel_Attributes);
        panel_Attributes.setBounds(5, 130, 520, 252);

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
        panel_ClassMenu.setBounds(5, 130, 520, 252);

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
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        }

        label_Header.setText(loadedDialoge[textIndex]);

        // events
        if (dialogueIndex == 0 && textIndex == 2) {
            textField_NameField.setVisible(true);
            button_Confirm.setVisible(true);
        } else if (dialogueIndex == 1 && textIndex == 7) {
            panel_ClassMenu.setVisible(true);
            player = new Mob();
            player.name = playerName;
        } else if (dialogueIndex == 2 && textIndex == 0) {
            
            player.chooseAffinity();
            
            openAttributesMenu();
        }

        if (textIndex + 1 < loadedDialoge.length) {
            textIndex++;
        }
    }

    private void openAttributesMenu() {
        panel_Attributes.setVisible(true);
        label_Level.setText("Level: " + String.valueOf(player.level));
        label_AvailablePoints.setText("Available Attributes Points: " + String.valueOf(player.attributePoints));
        
        label_HealthPoints.setText("Health Points (HP): " + String.valueOf(player.healthPoints));
        label_AgilityPoints.setText("Agility Points (AP): " + String.valueOf(player.agiltiyPoints));
        label_MagicPoints.setText("Magic Points (MP): " + String.valueOf(player.magicPoints));
        label_DefensePoints.setText("Defense Points (DP): " + String.valueOf(player.defensePoints));
        label_StrengthPoints.setText("Strength Points (SP): " + String.valueOf(player.strengthPoints));
        
        if (player.attributePoints > 0) {
            panel_AttributesAddition.setVisible(true);
        }
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
        // TODO add your handling code here:
    }//GEN-LAST:event_button_TutelaActionPerformed

    private void setPlayerClass(String playerClass) {
        player.typeAffinity = playerClass;
        panel_ClassMenu.setVisible(false);
        nextDialogueArray();
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
    private javax.swing.JLabel label_AgilityPoints;
    private javax.swing.JLabel label_AvailablePoints;
    private javax.swing.JLabel label_Celeritas;
    private javax.swing.JLabel label_DefensePoints;
    private javax.swing.JLabel label_Header;
    private javax.swing.JLabel label_HealthPoints;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_MagicPoints;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_StrengthPoints;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JPanel panel_Attributes;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_ClassMenu;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JTextField textField_NameField;
    // End of variables declaration//GEN-END:variables
}
