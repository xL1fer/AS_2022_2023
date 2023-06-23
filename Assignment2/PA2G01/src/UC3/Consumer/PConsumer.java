/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UC3.Consumer;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * Consumer process class.
 */
public class PConsumer extends javax.swing.JFrame {

    /**
     * Creates new form PConsumer.
     */
    public PConsumer() {
        initComponents();
    }
    
    /**
     * Initializes Consumer thread.
     */
    public static void initConsumers() {
        //BasicConfigurator.configure();
        
        String HOST = "localhost";
        int PORT = 9092;
        
        Properties properties = new Properties();
        /*
        String hosts = "";
        for (int i = 0; i < 3; i++) {
            hosts += HOST + ":" + (PORT + i);
            if (i != 2) hosts += ",";
        }
        properties.put("bootstrap.servers", hosts);
        */
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "KafkaConsumersGroup");
        
        ArrayList<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            properties.put("bootstrap.servers", HOST + ":" + (PORT + i));
            
            Thread consumer = new Thread(TConsumer.getInstance(i, properties));
            consumers.add(consumer);
            
            // start consumers
            consumer.start();
        }
        
        try {
            for (Thread t : consumers)
                t.join();
        } catch (InterruptedException ex) {}
        
        System.out.println("Consumer threads finished");
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
        displayPanel = new javax.swing.JPanel();
        displayPanelLabel = new javax.swing.JLabel();
        centerDisplayPanel = new javax.swing.JPanel();
        displayCenterPanel = new javax.swing.JPanel();
        consumer1ConsoleLabel = new javax.swing.JLabel();
        displayTextField1 = new javax.swing.JTextField();
        totalRecordsLabel1 = new javax.swing.JLabel();
        consumer2ConsoleLabel = new javax.swing.JLabel();
        displayTextField2 = new javax.swing.JTextField();
        totalRecordsLabel2 = new javax.swing.JLabel();
        consumer3ConsoleLabel = new javax.swing.JLabel();
        displayTextField3 = new javax.swing.JTextField();
        totalRecordsLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UC3 Consumer Process");
        setBounds(new java.awt.Rectangle(900, 200, 800, 600));
        setSize(new java.awt.Dimension(800, 600));

        basePanel.setLayout(new java.awt.BorderLayout());

        displayPanel.setBackground(new java.awt.Color(55, 55, 55));

        displayPanelLabel.setBackground(new java.awt.Color(55, 55, 55));
        displayPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        displayPanelLabel.setText("Display Panel");

        centerDisplayPanel.setBackground(new java.awt.Color(55, 55, 55));
        centerDisplayPanel.setToolTipText("");
        centerDisplayPanel.setLayout(new java.awt.BorderLayout());

        displayCenterPanel.setBackground(new java.awt.Color(65, 65, 65));

        consumer1ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer1ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer1ConsoleLabel.setText("Consumer 1 Console");

        displayTextField1.setEditable(false);
        displayTextField1.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayTextField1.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField1.setFocusable(false);

        totalRecordsLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalRecordsLabel1.setForeground(new java.awt.Color(255, 255, 255));
        totalRecordsLabel1.setText("Total Records: 0");

        consumer2ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer2ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer2ConsoleLabel.setText("Consumer 2 Console");

        displayTextField2.setEditable(false);
        displayTextField2.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayTextField2.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField2.setFocusable(false);

        totalRecordsLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalRecordsLabel2.setForeground(new java.awt.Color(255, 255, 255));
        totalRecordsLabel2.setText("Total Records: 0");

        consumer3ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer3ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer3ConsoleLabel.setText("Consumer 3 Console");

        displayTextField3.setEditable(false);
        displayTextField3.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayTextField3.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField3.setFocusable(false);

        totalRecordsLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalRecordsLabel3.setForeground(new java.awt.Color(255, 255, 255));
        totalRecordsLabel3.setText("Total Records: 0");

        javax.swing.GroupLayout displayCenterPanelLayout = new javax.swing.GroupLayout(displayCenterPanel);
        displayCenterPanel.setLayout(displayCenterPanelLayout);
        displayCenterPanelLayout.setHorizontalGroup(
            displayCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayCenterPanelLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(displayCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayCenterPanelLayout.createSequentialGroup()
                        .addComponent(consumer2ConsoleLabel)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayCenterPanelLayout.createSequentialGroup()
                        .addComponent(consumer1ConsoleLabel)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayCenterPanelLayout.createSequentialGroup()
                        .addComponent(consumer3ConsoleLabel)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayCenterPanelLayout.createSequentialGroup()
                        .addGroup(displayCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(displayTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(displayTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(displayTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(totalRecordsLabel2)
                            .addComponent(totalRecordsLabel3)
                            .addComponent(totalRecordsLabel1))
                        .addGap(50, 50, 50))))
        );
        displayCenterPanelLayout.setVerticalGroup(
            displayCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayCenterPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(consumer1ConsoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRecordsLabel1)
                .addGap(12, 12, 12)
                .addComponent(consumer2ConsoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRecordsLabel2)
                .addGap(13, 13, 13)
                .addComponent(consumer3ConsoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRecordsLabel3)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        centerDisplayPanel.add(displayCenterPanel, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(displayPanelLabel)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(displayPanelLabel)
                .addGap(33, 33, 33)
                .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
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
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PConsumer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PConsumer().setVisible(true);
            }
        });
        
        /* Initialize Consumer thread */
        initConsumers();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Java swing base panel.
     */
    private javax.swing.JPanel basePanel;
    /**
     * Java swing center display panel.
     */
    private javax.swing.JPanel centerDisplayPanel;
    /**
     * Java swing consumer console 1 label.
     */
    private javax.swing.JLabel consumer1ConsoleLabel;
    /**
     * Java swing consumer console 2 label.
     */
    private javax.swing.JLabel consumer2ConsoleLabel;
    /**
     * Java swing consumer console 3 label.
     */
    private javax.swing.JLabel consumer3ConsoleLabel;
    /**
     * Java swing display center panel.
     */
    private javax.swing.JPanel displayCenterPanel;
    /**
     * Java swing display panel.
     */
    private javax.swing.JPanel displayPanel;
    /**
     * Java swing display panel label.
     */
    private javax.swing.JLabel displayPanelLabel;
    /**
     * Java swing display 1 text field.
     */
    public static javax.swing.JTextField displayTextField1;
    /**
     * Java swing display 2 text field.
     */
    public static javax.swing.JTextField displayTextField2;
    /**
     * Java swing display 3 text field.
     */
    public static javax.swing.JTextField displayTextField3;
    /**
     * Java swing total records 1 label.
     */
    public static javax.swing.JLabel totalRecordsLabel1;
    /**
     * Java swing total records 2 label.
     */
    public static javax.swing.JLabel totalRecordsLabel2;
    /**
     * Java swing total records 3 label.
     */
    public static javax.swing.JLabel totalRecordsLabel3;
    // End of variables declaration//GEN-END:variables
}