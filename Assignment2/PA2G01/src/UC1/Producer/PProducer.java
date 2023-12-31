/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UC1.Producer;

import UC1.Producer.Sensor.ISensor;
import UC1.Producer.Sensor.ISensor_Main;
import UC1.Producer.Sensor.MSensor;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * Producer process class.
 */
public class PProducer extends javax.swing.JFrame {
    /**
     * Producers started flag.
     */
    private boolean producersStarted = false;

    /**
     * Creates new form PProducer.
     */
    public PProducer() {
        initComponents();
    }
    
    /**
     * Sensor monitor object instance.
     */
    public static ISensor mSensor = MSensor.getInstance();
    
    /**
     * Initializes Producer threads.
     */
    public static void initProducers() {
        /*
        ISensor mSensor = MSensor.getInstance();
        
        ArrayList<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread producer = new Thread(TProducer.getInstance(i, mSensor));
            producers.add(producer);
            
            // start producers
            producer.start();
        }
        
        try {
            for (Thread t : producers)
                t.join();
        } catch (InterruptedException ex) {}
        
        System.out.println("Producer threads finished");
        */
        
        //BasicConfigurator.configure();
        
        //ISensor mSensor = MSensor.getInstance();
        
        String HOST = "localhost";
        int PORT = 9092;
        
        Properties properties = new Properties();
        String hosts = "";
        for (int i = 0; i < 3; i++) {
            hosts += HOST + ":" + (PORT + i);
            if (i != 2) hosts += ",";
        }
        properties.put("bootstrap.servers", hosts);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        ArrayList<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread producer = new Thread(TProducer.getInstance(i, mSensor, properties));
            producers.add(producer);
            
            // start producers
            producer.start();
        }
        
        try {
            for (Thread t : producers)
                t.join();
        } catch (InterruptedException ex) {}
        
        System.out.println("Producer threads finished");
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
        sendModeLabel = new javax.swing.JLabel();
        sendModeComboBox = new javax.swing.JComboBox<>();
        producersLabel = new javax.swing.JLabel();
        producersComboBox = new javax.swing.JComboBox<>();
        requestTimeoutLabel = new javax.swing.JLabel();
        requestTimeoutComboBox = new javax.swing.JComboBox<>();
        maxBlockLabel = new javax.swing.JLabel();
        maxBlockComboBox = new javax.swing.JComboBox<>();
        lingerLabel = new javax.swing.JLabel();
        lingerComboBox = new javax.swing.JComboBox<>();
        displayPanel = new javax.swing.JPanel();
        centerDisplayPanel = new javax.swing.JPanel();
        displayTextField1 = new javax.swing.JTextField();
        displayTextField2 = new javax.swing.JTextField();
        displayTextField3 = new javax.swing.JTextField();
        displayPanelLabel = new javax.swing.JLabel();
        producer1ConsoleLabel = new javax.swing.JLabel();
        producer2ConsoleLabel = new javax.swing.JLabel();
        producer3ConsoleLabel = new javax.swing.JLabel();
        elapsedTimeLabel = new javax.swing.JLabel();

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

        configFlowPanel.setBackground(new java.awt.Color(55, 55, 55));
        configFlowPanel.setPreferredSize(new java.awt.Dimension(600, 200));
        configFlowPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 28, 50));

        startButton.setBackground(new java.awt.Color(55, 55, 55));
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        configFlowPanel.add(startButton);

        sendModeLabel.setForeground(new java.awt.Color(255, 255, 255));
        sendModeLabel.setText("Sending mode");
        configFlowPanel.add(sendModeLabel);

        sendModeComboBox.setBackground(new java.awt.Color(55, 55, 55));
        sendModeComboBox.setForeground(new java.awt.Color(255, 255, 255));
        sendModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fire and forget", "Synchronous", "Asynchronous" }));
        configFlowPanel.add(sendModeComboBox);

        producersLabel.setForeground(new java.awt.Color(255, 255, 255));
        producersLabel.setText("Producers");
        configFlowPanel.add(producersLabel);

        producersComboBox.setBackground(new java.awt.Color(55, 55, 55));
        producersComboBox.setForeground(new java.awt.Color(255, 255, 255));
        producersComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        configFlowPanel.add(producersComboBox);

        requestTimeoutLabel.setForeground(new java.awt.Color(255, 255, 255));
        requestTimeoutLabel.setText("Request timeout (ms)");
        configFlowPanel.add(requestTimeoutLabel);

        requestTimeoutComboBox.setBackground(new java.awt.Color(55, 55, 55));
        requestTimeoutComboBox.setForeground(new java.awt.Color(255, 255, 255));
        requestTimeoutComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1000", "2000", "3000", "4000", "5000" }));
        configFlowPanel.add(requestTimeoutComboBox);

        maxBlockLabel.setForeground(new java.awt.Color(255, 255, 255));
        maxBlockLabel.setText("Max block (ms)");
        configFlowPanel.add(maxBlockLabel);

        maxBlockComboBox.setBackground(new java.awt.Color(55, 55, 55));
        maxBlockComboBox.setForeground(new java.awt.Color(255, 255, 255));
        maxBlockComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1000", "2000", "3000", "4000", "5000" }));
        configFlowPanel.add(maxBlockComboBox);

        lingerLabel.setForeground(new java.awt.Color(255, 255, 255));
        lingerLabel.setText("Linger (ms)");
        configFlowPanel.add(lingerLabel);

        lingerComboBox.setBackground(new java.awt.Color(55, 55, 55));
        lingerComboBox.setForeground(new java.awt.Color(255, 255, 255));
        lingerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1000", "2000", "3000", "4000", "5000" }));
        configFlowPanel.add(lingerComboBox);

        centerConfigPanel.add(configFlowPanel);

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(centerConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
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

        producer1ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        producer1ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        producer1ConsoleLabel.setText("Producer 1 Console");

        producer2ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        producer2ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        producer2ConsoleLabel.setText("Producer 2 Console");

        producer3ConsoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        producer3ConsoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        producer3ConsoleLabel.setText("Producer 3 Console");

        elapsedTimeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        elapsedTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        elapsedTimeLabel.setText("Elapsed Time: --------");

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(displayPanelLabel))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(producer1ConsoleLabel)
                        .addGap(76, 76, 76)
                        .addComponent(producer2ConsoleLabel)
                        .addGap(78, 78, 78)
                        .addComponent(producer3ConsoleLabel))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(elapsedTimeLabel))
                    .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(displayPanelLabel)
                .addGap(18, 18, 18)
                .addComponent(elapsedTimeLabel)
                .addGap(28, 28, 28)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producer1ConsoleLabel)
                    .addComponent(producer2ConsoleLabel)
                    .addComponent(producer3ConsoleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(centerDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        basePanel.add(displayPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(basePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if (!this.producersStarted) {
            this.producersStarted = true;
            
            int sendingMode = sendModeComboBox.getSelectedIndex();
            int producersNumber = producersComboBox.getSelectedIndex() + 1;
            
            int requestTimeout = Integer.parseInt((String)requestTimeoutComboBox.getSelectedItem());
            int maxBlock = Integer.parseInt((String)maxBlockComboBox.getSelectedItem());
            int linger = Integer.parseInt((String)lingerComboBox.getSelectedItem());
            
            System.out.println("Starting Producers: Sending mode = " + sendingMode + " | Producers number = " + producersNumber);

            /* Initialize Producer threads */
            ISensor_Main mainSensor = (ISensor_Main)mSensor;
            mainSensor.signalStart(sendingMode, producersNumber, requestTimeout, maxBlock, linger);
        }
    }//GEN-LAST:event_buttonActionPerformed

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
        
        /* Initialize Producer threads */
        initProducers();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Java swing base panel.
     */
    private javax.swing.JPanel basePanel;
    /**
     * Java swing center config panel.
     */
    private javax.swing.JPanel centerConfigPanel;
    /**
     * Java swing center display panel.
     */
    private javax.swing.JPanel centerDisplayPanel;
    /**
     * Java swing config flow panel.
     */
    private javax.swing.JPanel configFlowPanel;
    /**
     * Java swing config panel.
     */
    private javax.swing.JPanel configPanel;
    /**
     * Java swing panel label.
     */
    private javax.swing.JLabel configPanelLabel;
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
     * Java swing elapsed time label.
     */
    public static javax.swing.JLabel elapsedTimeLabel;
    /**
     * Java swing linger combo box.
     */
    private javax.swing.JComboBox<String> lingerComboBox;
    /**
     * Java swing linger label.
     */
    private javax.swing.JLabel lingerLabel;
    /**
     * Java swing max block combo box.
     */
    private javax.swing.JComboBox<String> maxBlockComboBox;
    /**
     * Java swing max block label.
     */
    private javax.swing.JLabel maxBlockLabel;
    /**
     * Java swing producer console 1 label.
     */
    private javax.swing.JLabel producer1ConsoleLabel;
    /**
     * Java swing producer console 2 label.
     */
    private javax.swing.JLabel producer2ConsoleLabel;
    /**
     * Java swing producer console 3 label.
     */
    private javax.swing.JLabel producer3ConsoleLabel;
    /**
     * Java swing producers combo box.
     */
    private javax.swing.JComboBox<String> producersComboBox;
    /**
     * Java swing producers label.
     */
    private javax.swing.JLabel producersLabel;
    /**
     * Java swing request timeout combo box.
     */
    private javax.swing.JComboBox<String> requestTimeoutComboBox;
    /**
     * Java swing request timeout label.
     */
    private javax.swing.JLabel requestTimeoutLabel;
    /**
     * Java swing send mode combo box.
     */
    private javax.swing.JComboBox<String> sendModeComboBox;
    /**
     * Java swing send mode label.
     */
    private javax.swing.JLabel sendModeLabel;
    /**
     * Java swing start button.
     */
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
