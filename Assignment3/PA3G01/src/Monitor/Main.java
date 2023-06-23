/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Monitor;

/**
 * Monitor main class.
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        Thread monitor = new Thread(TMonitor.getInstance(this.textArea, this.activeLoadBalancersLabel, this.activeServersLabel));
        monitor.start();
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
        headerPanel = new javax.swing.JPanel();
        logsLabel = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        activeLoadBalancersLabel = new javax.swing.JLabel();
        activeServersLabel = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        textPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitor");
        setResizable(false);

        basePanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(55, 55, 55));
        headerPanel.setForeground(new java.awt.Color(255, 255, 255));
        headerPanel.setToolTipText("");
        headerPanel.setPreferredSize(new java.awt.Dimension(400, 50));

        logsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logsLabel.setForeground(new java.awt.Color(255, 255, 255));
        logsLabel.setText("Monitor Interface");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(logsLabel)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(logsLabel)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        basePanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        statusPanel.setBackground(new java.awt.Color(65, 65, 65));
        statusPanel.setForeground(new java.awt.Color(255, 255, 255));

        activeLoadBalancersLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        activeLoadBalancersLabel.setForeground(new java.awt.Color(255, 255, 255));
        activeLoadBalancersLabel.setText("Active Load Balancers: 0");

        activeServersLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        activeServersLabel.setForeground(new java.awt.Color(255, 255, 255));
        activeServersLabel.setText("Active Servers: 0");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(activeLoadBalancersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(activeServersLabel)
                .addGap(27, 27, 27))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activeLoadBalancersLabel)
                    .addComponent(activeServersLabel))
                .addGap(14, 14, 14))
        );

        basePanel.add(statusPanel, java.awt.BorderLayout.CENTER);

        displayPanel.setBackground(new java.awt.Color(65, 65, 65));
        displayPanel.setForeground(new java.awt.Color(255, 255, 255));
        displayPanel.setPreferredSize(new java.awt.Dimension(400, 200));

        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(55, 55, 55));
        textArea.setColumns(20);
        textArea.setForeground(new java.awt.Color(255, 255, 255));
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setFocusable(false);
        textPane.setViewportView(textArea);

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(textPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(textPane, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        basePanel.add(displayPanel, java.awt.BorderLayout.SOUTH);

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeLoadBalancersLabel;
    private javax.swing.JLabel activeServersLabel;
    private javax.swing.JPanel basePanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel logsLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textPane;
    // End of variables declaration//GEN-END:variables
}
