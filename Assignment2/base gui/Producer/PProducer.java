/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UC1.Producer;

/**
 *
 * Producer process class.
 */
public class PProducer extends javax.swing.JFrame {

    /**
     * Creates new form PProducer
     */
    public PProducer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basePanel = new javax.swing.JPanel();
        configPanel = new javax.swing.JPanel();
        centerConfigPanel = new javax.swing.JPanel();
        configPanelLabel = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        centerDisplayPanel = new javax.swing.JPanel();
        displayPanelLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UC1 Producer Process");
        setBounds(new java.awt.Rectangle(50, 200, 800, 600));
        setSize(new java.awt.Dimension(800, 600));

        basePanel.setLayout(new java.awt.BorderLayout());

        configPanel.setBackground(new java.awt.Color(55, 55, 55));
        configPanel.setPreferredSize(new java.awt.Dimension(800, 300));

        centerConfigPanel.setBackground(new java.awt.Color(55, 55, 55));

        configPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        configPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        configPanelLabel.setText("Config Panel");
        centerConfigPanel.add(configPanelLabel);

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(centerConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        configPanelLayout.setVerticalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(centerConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        basePanel.add(configPanel, java.awt.BorderLayout.NORTH);

        displayPanel.setBackground(new java.awt.Color(65, 65, 65));
        displayPanel.setPreferredSize(new java.awt.Dimension(800, 300));

        centerDisplayPanel.setBackground(new java.awt.Color(65, 65, 65));

        displayPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        displayPanelLabel.setText("Display Panel");
        centerDisplayPanel.add(displayPanelLabel);

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        basePanel.add(displayPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(basePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PProducer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PProducer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basePanel;
    private javax.swing.JPanel centerConfigPanel;
    private javax.swing.JPanel centerDisplayPanel;
    private javax.swing.JPanel configPanel;
    private javax.swing.JLabel configPanelLabel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JLabel displayPanelLabel;
    // End of variables declaration//GEN-END:variables
}
