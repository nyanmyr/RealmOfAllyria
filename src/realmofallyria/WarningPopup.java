
package realmofallyria;

public class WarningPopup extends javax.swing.JFrame {

    String warningHeader;
    boolean closeDispose;

    public WarningPopup(String headerText, String bodyText, boolean givenCloseDispose) {

        initComponents();

        this.warningHeader = headerText;
        this.closeDispose = givenCloseDispose;

        Game.loadText(headerText, 
                label_WarningTitle, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        Game.loadText(bodyText, 
                label_WarningBody, false, true, TextLoadingSpeeds.SLOW.textSpeed);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_Warning = new javax.swing.JPanel();
        label_WarningTitle = new javax.swing.JLabel();
        panel_WarningMessage = new javax.swing.JPanel();
        button_CloseWarning = new javax.swing.JButton();
        label_WarningBody = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(new java.awt.Dimension(460, 350));

        jPanel1.setMinimumSize(new java.awt.Dimension(460, 350));
        jPanel1.setLayout(null);

        panel_Warning.setBackground(new java.awt.Color(10, 10, 13));
        panel_Warning.setLayout(null);

        label_WarningTitle.setFont(Game.fontsMap.get("Header").deriveFont(26f)
        );
        label_WarningTitle.setForeground(new java.awt.Color(242, 242, 242));
        label_WarningTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_WarningTitle.setText("jLabel1");
        panel_Warning.add(label_WarningTitle);
        label_WarningTitle.setBounds(10, 10, 440, 40);

        panel_WarningMessage.setBackground(new java.awt.Color(25, 25, 33));
        panel_WarningMessage.setLayout(null);

        button_CloseWarning.setBackground(new java.awt.Color(67, 67, 79));
        button_CloseWarning.setFont(Game.fontsMap.get("Body"));
        button_CloseWarning.setForeground(new java.awt.Color(242, 242, 242));
        button_CloseWarning.setText("Close");
        button_CloseWarning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_CloseWarningMouseClicked(evt);
            }
        });
        panel_WarningMessage.add(button_CloseWarning);
        button_CloseWarning.setBounds(130, 230, 170, 40);

        label_WarningBody.setFont(Game.fontsMap.get("Body"));
        label_WarningBody.setForeground(new java.awt.Color(242, 242, 242));
        label_WarningBody.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_WarningBody.setText("jLabel1");
        panel_WarningMessage.add(label_WarningBody);
        label_WarningBody.setBounds(10, 10, 420, 210);

        panel_Warning.add(panel_WarningMessage);
        panel_WarningMessage.setBounds(10, 60, 440, 280);

        jPanel1.add(panel_Warning);
        panel_Warning.setBounds(0, 0, 460, 350);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_CloseWarningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CloseWarningMouseClicked

        if (closeDispose) {
            Game.warningsMap.remove(warningHeader);
        }
        dispose();

    }//GEN-LAST:event_button_CloseWarningMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new WarningPopup("Header", "Body", true).setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CloseWarning;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_WarningBody;
    private javax.swing.JLabel label_WarningTitle;
    private javax.swing.JPanel panel_Warning;
    private javax.swing.JPanel panel_WarningMessage;
    // End of variables declaration//GEN-END:variables
}
