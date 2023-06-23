/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UC4.Consumer;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * Consumer process class.
 */
public class PConsumer extends javax.swing.JFrame {
    /**
     * Consumers started flag.
     */
    private boolean consumersStarted = false;
    /**
     * Consumer threads reference list.
     */
    private static ArrayList<TConsumer> consumers = new ArrayList<>();

    /**
     * Creates new form PConsumer
     */
    public PConsumer() {
        initComponents();
    }
    
    /**
     * Initializes Consumer thread
     * 
     * @param consumersNumber consumers number
     * @param commitMode commit mode
     * @param groupRebalance group rebalance
     */
    public static void initConsumers(int consumersNumber, int commitMode, boolean groupRebalance) {
        //BasicConfigurator.configure();
        
        String HOST = "localhost";
        int PORT = 9092;
        
        Properties properties = new Properties();
        String hosts = "";
        for (int i = 0; i < 3; i++) {
            hosts += HOST + ":" + (PORT + i);
            if (i != 2) hosts += ",";
        }
        properties.put("bootstrap.servers", hosts);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "KafkaConsumersGroup");
        
        // "throttled" commit mode
        if (commitMode == 1) {
            properties.put("mp.messaging.incoming.my-channel.connector", "smallrye-kafka");
            properties.put("mp.messaging.incoming.my-channel.commit-strategy", "throttled");
        }
        // "ignore" commit mode
        else if (commitMode == 2) {
            properties.put("mp.messaging.incoming.my-channel.connector", "smallrye-kafka");
            properties.put("mp.messaging.incoming.my-channel.commit-strategy", "ignore");
        }
        // "latest" commit mode
        else if (commitMode == 3) {
            properties.put("mp.messaging.incoming.my-channel.connector", "smallrye-kafka");
            properties.put("mp.messaging.incoming.my-channel.commit-strategy", "latest");
        }
        
        // group rebalance enabled
        if (groupRebalance)
            properties.put("rebalance.max.retries", consumersNumber);
        // group rebalance disabled
        else
            properties.put("rebalance.max.retries", 0);
        
        //System.out.println(properties);
        
        ArrayList<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < consumersNumber; i++) {
            properties.put("bootstrap.servers", HOST + ":" + (PORT + i));
            
            //Thread consumer = new Thread(TConsumer.getInstance(i, properties));
            TConsumer consumer = new TConsumer(i, properties);
            
            consumers.add(consumer);
            
            // start consumers
            //consumer.start();
            
            Thread t = new Thread(consumer);
            threads.add(t);
            t.start();
        }
        
        try {
            for (Thread t : threads)
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
        configPanel = new javax.swing.JPanel();
        centerConfigPanel = new javax.swing.JPanel();
        configPanelLabel = new javax.swing.JLabel();
        configFlowPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        consumersLabel = new javax.swing.JLabel();
        consumersComboBox = new javax.swing.JComboBox<>();
        commitModeLabel = new javax.swing.JLabel();
        commitModeComboBox = new javax.swing.JComboBox<>();
        groupRebalanceLabel = new javax.swing.JLabel();
        groupRebalanceComboBox = new javax.swing.JComboBox<>();
        displayPanel = new javax.swing.JPanel();
        centerDisplayPanel = new javax.swing.JPanel();
        displayTextField1 = new javax.swing.JTextField();
        displayTextField2 = new javax.swing.JTextField();
        displayTextField3 = new javax.swing.JTextField();
        displayPanelLabel = new javax.swing.JLabel();
        consumer1ConsoleLabel = new javax.swing.JLabel();
        consumer2ConsoleLabel = new javax.swing.JLabel();
        consumer3ConsoleLabel = new javax.swing.JLabel();
        terminateConsumer1Button = new javax.swing.JButton();
        terminateConsumer2Button = new javax.swing.JButton();
        terminateConsumer3Button = new javax.swing.JButton();
        elapsedTimeLabel1 = new javax.swing.JLabel();
        elapsedTimeLabel2 = new javax.swing.JLabel();
        elapsedTimeLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UC4 Consumer Process");
        setBounds(new java.awt.Rectangle(900, 200, 800, 600));
        setSize(new java.awt.Dimension(800, 600));

        basePanel.setLayout(new java.awt.BorderLayout());

        configPanel.setBackground(new java.awt.Color(55, 55, 55));
        configPanel.setPreferredSize(new java.awt.Dimension(800, 300));

        centerConfigPanel.setBackground(new java.awt.Color(55, 55, 55));

        configPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        configPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        configPanelLabel.setText("Config Panel");
        centerConfigPanel.add(configPanelLabel);

        configFlowPanel.setBackground(new java.awt.Color(55, 55, 55));
        configFlowPanel.setPreferredSize(new java.awt.Dimension(600, 200));
        configFlowPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 80));

        startButton.setBackground(new java.awt.Color(55, 55, 55));
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        configFlowPanel.add(startButton);

        consumersLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumersLabel.setText("Consumers");
        configFlowPanel.add(consumersLabel);

        consumersComboBox.setBackground(new java.awt.Color(55, 55, 55));
        consumersComboBox.setForeground(new java.awt.Color(255, 255, 255));
        consumersComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        configFlowPanel.add(consumersComboBox);

        commitModeLabel.setForeground(new java.awt.Color(255, 255, 255));
        commitModeLabel.setText("Commit mode");
        configFlowPanel.add(commitModeLabel);

        commitModeComboBox.setBackground(new java.awt.Color(55, 55, 55));
        commitModeComboBox.setForeground(new java.awt.Color(255, 255, 255));
        commitModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto-commit", "Throttled", "Ignore", "Latest" }));
        configFlowPanel.add(commitModeComboBox);

        groupRebalanceLabel.setForeground(new java.awt.Color(255, 255, 255));
        groupRebalanceLabel.setText("Group rebalance");
        configFlowPanel.add(groupRebalanceLabel);

        groupRebalanceComboBox.setBackground(new java.awt.Color(55, 55, 55));
        groupRebalanceComboBox.setForeground(new java.awt.Color(255, 255, 255));
        groupRebalanceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enabled", "Disabled" }));
        configFlowPanel.add(groupRebalanceComboBox);

        centerConfigPanel.add(configFlowPanel);

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(centerConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        configPanelLayout.setVerticalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(centerConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        basePanel.add(configPanel, java.awt.BorderLayout.NORTH);

        displayPanel.setBackground(new java.awt.Color(65, 65, 65));
        displayPanel.setPreferredSize(new java.awt.Dimension(800, 300));

        centerDisplayPanel.setBackground(new java.awt.Color(65, 65, 65));
        centerDisplayPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        displayTextField1.setEditable(false);
        displayTextField1.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField1.setColumns(22);
        displayTextField1.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField1.setFocusable(false);
        centerDisplayPanel.add(displayTextField1);

        displayTextField2.setEditable(false);
        displayTextField2.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField2.setColumns(22);
        displayTextField2.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField2.setFocusable(false);
        centerDisplayPanel.add(displayTextField2);

        displayTextField3.setEditable(false);
        displayTextField3.setBackground(new java.awt.Color(55, 55, 55));
        displayTextField3.setColumns(22);
        displayTextField3.setForeground(new java.awt.Color(255, 255, 255));
        displayTextField3.setFocusable(false);
        centerDisplayPanel.add(displayTextField3);

        displayPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        displayPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        displayPanelLabel.setText("Display Panel");

        consumer1ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer1ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer1ConsoleLabel.setText("Consumer 1 Console");

        consumer2ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer2ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer2ConsoleLabel.setText("Consumer 2 Console");

        consumer3ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        consumer3ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        consumer3ConsoleLabel.setText("Consumer 3 Console");

        terminateConsumer1Button.setBackground(new java.awt.Color(55, 55, 55));
        terminateConsumer1Button.setForeground(new java.awt.Color(255, 255, 255));
        terminateConsumer1Button.setText("Terminate 1");
        terminateConsumer1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateConsumerButtonActionPerformed(evt);
            }
        });

        terminateConsumer2Button.setBackground(new java.awt.Color(55, 55, 55));
        terminateConsumer2Button.setForeground(new java.awt.Color(255, 255, 255));
        terminateConsumer2Button.setText("Terminate 2");
        terminateConsumer2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateConsumerButtonActionPerformed(evt);
            }
        });

        terminateConsumer3Button.setBackground(new java.awt.Color(55, 55, 55));
        terminateConsumer3Button.setForeground(new java.awt.Color(255, 255, 255));
        terminateConsumer3Button.setText("Terminate 3");
        terminateConsumer3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateConsumerButtonActionPerformed(evt);
            }
        });

        elapsedTimeLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        elapsedTimeLabel1.setForeground(new java.awt.Color(255, 255, 255));
        elapsedTimeLabel1.setText("Total Time: --------");

        elapsedTimeLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        elapsedTimeLabel2.setForeground(new java.awt.Color(255, 255, 255));
        elapsedTimeLabel2.setText("Total Time: --------");

        elapsedTimeLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        elapsedTimeLabel3.setForeground(new java.awt.Color(255, 255, 255));
        elapsedTimeLabel3.setText("Total Time: --------");

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(displayPanelLabel))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(consumer1ConsoleLabel)
                        .addGap(81, 81, 81)
                        .addComponent(consumer2ConsoleLabel)
                        .addGap(81, 81, 81)
                        .addComponent(consumer3ConsoleLabel))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(terminateConsumer1Button)
                            .addComponent(elapsedTimeLabel1))
                        .addGap(123, 123, 123)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(terminateConsumer2Button)
                            .addComponent(elapsedTimeLabel2))
                        .addGap(124, 124, 124)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(terminateConsumer3Button)
                            .addComponent(elapsedTimeLabel3)))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(displayPanelLabel)
                .addGap(50, 50, 50)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consumer1ConsoleLabel)
                    .addComponent(consumer2ConsoleLabel)
                    .addComponent(consumer3ConsoleLabel))
                .addGap(21, 21, 21)
                .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(terminateConsumer1Button)
                    .addComponent(terminateConsumer2Button)
                    .addComponent(terminateConsumer3Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(elapsedTimeLabel1)
                    .addComponent(elapsedTimeLabel2)
                    .addComponent(elapsedTimeLabel3))
                .addGap(34, 34, 34))
        );

        basePanel.add(displayPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(basePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if (!this.consumersStarted) {
            this.consumersStarted = true;

            int consumersNumber = consumersComboBox.getSelectedIndex() + 1;
            int commitMode = commitModeComboBox.getSelectedIndex();
            boolean groupRebalance = groupRebalanceComboBox.getSelectedIndex() == 0;

            System.out.println("Starting Consumers: Consumers number = " + consumersNumber + " | Commit mode = " + commitMode + " | Group rebalance = " + groupRebalance);

            /* Initialize Consumer threads */
            new Thread(() -> {
                // code goes here.
                initConsumers(consumersNumber, commitMode, groupRebalance);
            }).start();
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void terminateConsumerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminateConsumerButtonActionPerformed
        if (consumers.size() > 0 && evt.getActionCommand().equals("Terminate 1"))
            consumers.get(0).terminate();
        else if (consumers.size() > 1 &&evt.getActionCommand().equals("Terminate 2"))
            consumers.get(1).terminate();
        else if (consumers.size() > 2)
            consumers.get(2).terminate();
    }//GEN-LAST:event_terminateConsumerButtonActionPerformed

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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Java swing base panel.
     */
    private javax.swing.JPanel basePanel;
    /**
     * Java swing config panel.
     */
    private javax.swing.JPanel centerConfigPanel;
    /**
     * Java swing display panel.
     */
    private javax.swing.JPanel centerDisplayPanel;
    /**
     * Java swing commit mode combo box.
     */
    private javax.swing.JComboBox<String> commitModeComboBox;
    /**
     * Java swing commit mode label.
     */
    private javax.swing.JLabel commitModeLabel;
    /**
     * Java swing config flow panel.
     */
    private javax.swing.JPanel configFlowPanel;
    /**
     * Java swing config panel.
     */
    private javax.swing.JPanel configPanel;
    /**
     * Java swing config panel label.
     */
    private javax.swing.JLabel configPanelLabel;
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
     * Java swing cosumers combo box.
     */
    private javax.swing.JComboBox<String> consumersComboBox;
    /**
     * Java swing consumers label.
     */
    private javax.swing.JLabel consumersLabel;
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
     * Java swing elapsed time 1 label.
     */
    public static javax.swing.JLabel elapsedTimeLabel1;
    /**
     * Java swing elapsed time 2 label.
     */
    public static javax.swing.JLabel elapsedTimeLabel2;
    /**
     * Java swing elapsed time 3 label.
     */
    public static javax.swing.JLabel elapsedTimeLabel3;
    /**
     * Java swing group rebalance combo box.
     */
    private javax.swing.JComboBox<String> groupRebalanceComboBox;
    /**
     * Java swing group rebalance label.
     */
    private javax.swing.JLabel groupRebalanceLabel;
    /**
     * Java swing start button.
     */
    private javax.swing.JButton startButton;
    /**
     * Java swing terminate consumer 1 button.
     */
    private javax.swing.JButton terminateConsumer1Button;
    /**
     * Java swing terminate consumer 2 button.
     */
    private javax.swing.JButton terminateConsumer2Button;
    /**
     * Java swing terminate consumer 3 button.
     */
    private javax.swing.JButton terminateConsumer3Button;
    // End of variables declaration//GEN-END:variables
}
