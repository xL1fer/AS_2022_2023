/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package HCP.Main;

import javax.swing.DefaultListSelectionModel;

import HCP.Comms.TServer;
import HCP.Log.ILog;
import HCP.Log.MLog;

/**
 * Extension of default list selection model without item selection.
 */
class DisabledItemSelectionModel extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index0, int index1) {
        super.setSelectionInterval(-1, -1);
    }
}

/**
 *
 * Hostel Control Process main class.
 */
public class Main extends javax.swing.JFrame {
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }
    
    /**
     * Initialize acceptance of TCP connections from CCP client.
     */
    private static void initHCP() {
        // the user must define the port number
        int port = 5000;

        ILog mLog = MLog.getInstance();
        Thread tServer = new Thread(TServer.getInstance(port, mLog));
        tServer.start();
        try {
            tServer.join();
        } catch (InterruptedException ex) {}
        System.out.println("Hostel Centre connection finished");
      
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
        upFloorsPanel = new javax.swing.JPanel();
        floor3Label = new javax.swing.JLabel();
        floor3Pane = new javax.swing.JScrollPane();
        floor3 = new javax.swing.JList<>();
        floor2Label = new javax.swing.JLabel();
        floor2Pane = new javax.swing.JScrollPane();
        floor2 = new javax.swing.JList<>();
        floor1Label = new javax.swing.JLabel();
        floor1Pane = new javax.swing.JScrollPane();
        floor1 = new javax.swing.JList<>();
        bathrooms3Label = new javax.swing.JLabel();
        bathrooms3Pane = new javax.swing.JScrollPane();
        bathrooms3 = new javax.swing.JList<>();
        bathrooms2Label = new javax.swing.JLabel();
        bathrooms2Pane = new javax.swing.JScrollPane();
        bathrooms2 = new javax.swing.JList<>();
        bathrooms1Label = new javax.swing.JLabel();
        bathrooms1Pane = new javax.swing.JScrollPane();
        bathrooms1 = new javax.swing.JList<>();
        groundFloorPanel = new javax.swing.JPanel();
        checkInQueueLabel = new javax.swing.JLabel();
        checkInQueuePane = new javax.swing.JScrollPane();
        checkInQueue = new javax.swing.JList<>();
        receptionQueueLabel = new javax.swing.JLabel();
        receptionQueuePane = new javax.swing.JScrollPane();
        receptionQueue = new javax.swing.JList<>();
        mealQueueLabel = new javax.swing.JLabel();
        mealQueuePane = new javax.swing.JScrollPane();
        mealQueue = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hostel Centre 0.1");
        setBounds(new java.awt.Rectangle(900, 200, 800, 600));
        setSize(new java.awt.Dimension(800, 600));

        basePanel.setLayout(new java.awt.BorderLayout());

        upFloorsPanel.setBackground(new java.awt.Color(65, 65, 65));
        upFloorsPanel.setPreferredSize(new java.awt.Dimension(800, 350));

        floor3Label.setBackground(new java.awt.Color(55, 55, 55));
        floor3Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        floor3Label.setForeground(new java.awt.Color(255, 255, 255));
        floor3Label.setText("Floor 3");

        floor3.setSelectionModel(new DisabledItemSelectionModel());
        floor3.setBackground(new java.awt.Color(55, 55, 55));
        floor3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        floor3.setForeground(new java.awt.Color(255, 255, 255));
        floor3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "000", "000", "000", "000", "000", "000", "000", "000", "000" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        floor3.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        floor3.setVisibleRowCount(1);
        floor3Pane.setViewportView(floor3);

        floor2Label.setBackground(new java.awt.Color(55, 55, 55));
        floor2Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        floor2Label.setForeground(new java.awt.Color(255, 255, 255));
        floor2Label.setText("Floor 2");

        floor2.setSelectionModel(new DisabledItemSelectionModel());
        floor2.setBackground(new java.awt.Color(55, 55, 55));
        floor2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        floor2.setForeground(new java.awt.Color(255, 255, 255));
        floor2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "000", "000", "000", "000", "000", "000", "000", "000", "000" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        floor2.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        floor2.setVisibleRowCount(1);
        floor2Pane.setViewportView(floor2);

        floor1Label.setBackground(new java.awt.Color(55, 55, 55));
        floor1Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        floor1Label.setForeground(new java.awt.Color(255, 255, 255));
        floor1Label.setText("Floor 1");

        floor1.setSelectionModel(new DisabledItemSelectionModel());
        floor1.setBackground(new java.awt.Color(55, 55, 55));
        floor1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        floor1.setForeground(new java.awt.Color(255, 255, 255));
        floor1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "000", "000", "000", "000", "000", "000", "000", "000", "000" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        floor1.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        floor1.setVisibleRowCount(1);
        floor1Pane.setViewportView(floor1);

        bathrooms3Label.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms3Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bathrooms3Label.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms3Label.setText("Bathrooms 3");

        bathrooms3.setSelectionModel(new DisabledItemSelectionModel());
        bathrooms3.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bathrooms3.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        bathrooms3.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        bathrooms3.setVisibleRowCount(1);
        bathrooms3Pane.setViewportView(bathrooms3);

        bathrooms2Label.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms2Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bathrooms2Label.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms2Label.setText("Bathrooms 2");

        bathrooms2.setSelectionModel(new DisabledItemSelectionModel());
        bathrooms2.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bathrooms2.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        bathrooms2.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        bathrooms2.setVisibleRowCount(1);
        bathrooms2Pane.setViewportView(bathrooms2);

        bathrooms1Label.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms1Label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bathrooms1Label.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms1Label.setText("Bathrooms 1");

        bathrooms1.setSelectionModel(new DisabledItemSelectionModel());
        bathrooms1.setBackground(new java.awt.Color(55, 55, 55));
        bathrooms1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bathrooms1.setForeground(new java.awt.Color(255, 255, 255));
        bathrooms1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        bathrooms1.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        bathrooms1.setVisibleRowCount(1);
        bathrooms1Pane.setViewportView(bathrooms1);

        javax.swing.GroupLayout upFloorsPanelLayout = new javax.swing.GroupLayout(upFloorsPanel);
        upFloorsPanel.setLayout(upFloorsPanelLayout);
        upFloorsPanelLayout.setHorizontalGroup(
            upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upFloorsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bathrooms1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bathrooms1Label)
                    .addComponent(bathrooms2Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bathrooms3Label)
                    .addComponent(bathrooms3Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bathrooms2Label))
                .addGap(33, 33, 33)
                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(floor3Label)
                    .addComponent(floor1Label)
                    .addComponent(floor2Label)
                    .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(floor3Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(floor2Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(floor1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        upFloorsPanelLayout.setVerticalGroup(
            upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upFloorsPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(floor3Label)
                    .addComponent(bathrooms3Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upFloorsPanelLayout.createSequentialGroup()
                        .addComponent(floor3Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(floor2Label)
                            .addComponent(bathrooms2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upFloorsPanelLayout.createSequentialGroup()
                                .addComponent(floor2Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(floor1Label)
                                    .addComponent(bathrooms1Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(upFloorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(floor1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bathrooms1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(bathrooms2Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bathrooms3Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        basePanel.add(upFloorsPanel, java.awt.BorderLayout.PAGE_START);

        groundFloorPanel.setBackground(new java.awt.Color(55, 55, 55));
        groundFloorPanel.setPreferredSize(new java.awt.Dimension(800, 250));

        checkInQueueLabel.setBackground(new java.awt.Color(55, 55, 55));
        checkInQueueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        checkInQueueLabel.setForeground(new java.awt.Color(255, 255, 255));
        checkInQueueLabel.setText("Check-in queue");

        checkInQueue.setSelectionModel(new DisabledItemSelectionModel());
        checkInQueue.setBackground(new java.awt.Color(55, 55, 55));
        checkInQueue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        checkInQueue.setForeground(new java.awt.Color(255, 255, 255));
        checkInQueue.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00", "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        checkInQueue.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        checkInQueue.setVisibleRowCount(1);
        checkInQueuePane.setViewportView(checkInQueue);

        receptionQueueLabel.setBackground(new java.awt.Color(55, 55, 55));
        receptionQueueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        receptionQueueLabel.setForeground(new java.awt.Color(255, 255, 255));
        receptionQueueLabel.setText("Reception queue");

        receptionQueue.setSelectionModel(new DisabledItemSelectionModel());
        receptionQueue.setBackground(new java.awt.Color(55, 55, 55));
        receptionQueue.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        receptionQueue.setForeground(new java.awt.Color(255, 255, 255));
        receptionQueue.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        receptionQueuePane.setViewportView(receptionQueue);

        mealQueueLabel.setBackground(new java.awt.Color(55, 55, 55));
        mealQueueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mealQueueLabel.setForeground(new java.awt.Color(255, 255, 255));
        mealQueueLabel.setText("Meal queue");

        mealQueue.setSelectionModel(new DisabledItemSelectionModel());
        mealQueue.setBackground(new java.awt.Color(55, 55, 55));
        mealQueue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        mealQueue.setForeground(new java.awt.Color(255, 255, 255));
        mealQueue.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "00", "00", "00", "00", "00", "00", "00", "00", "00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        mealQueue.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        mealQueue.setVisibleRowCount(1);
        mealQueuePane.setViewportView(mealQueue);

        javax.swing.GroupLayout groundFloorPanelLayout = new javax.swing.GroupLayout(groundFloorPanel);
        groundFloorPanel.setLayout(groundFloorPanelLayout);
        groundFloorPanelLayout.setHorizontalGroup(
            groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groundFloorPanelLayout.createSequentialGroup()
                .addGroup(groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(groundFloorPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkInQueueLabel)
                            .addComponent(checkInQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(receptionQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mealQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mealQueueLabel)))
                    .addGroup(groundFloorPanelLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(receptionQueueLabel)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        groundFloorPanelLayout.setVerticalGroup(
            groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groundFloorPanelLayout.createSequentialGroup()
                .addGroup(groundFloorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(groundFloorPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(checkInQueueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkInQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(groundFloorPanelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(mealQueueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mealQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(groundFloorPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(receptionQueueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(receptionQueuePane, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        basePanel.add(groundFloorPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(basePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * HCP main class entry point.
     * 
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
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
        
        /* Receive connection from Control Centre */
        initHCP();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
	/**
	* Java swing panel base panel 
	*/
    private javax.swing.JPanel basePanel;
	/**
	* Floor 1 bathrooms GUI list
	*/
    public static javax.swing.JList<String> bathrooms1;
	/**
	* Java swing label floor 1 bathrooms label
	*/
    private javax.swing.JLabel bathrooms1Label;
	/**
	* Java swing scrol pane floor 1 bathrooms pane
	*/
    private javax.swing.JScrollPane bathrooms1Pane;
	/**
	* Floor 2 bathrooms GUI list
	*/
    public static javax.swing.JList<String> bathrooms2;
	/**
	* Java swing label floor 2 bathrooms label
	*/
    private javax.swing.JLabel bathrooms2Label;
	/**
	* Java swing scrol pane floor 2 bathrooms pane
	*/
    private javax.swing.JScrollPane bathrooms2Pane;
	/**
	* Floor 3 bathrooms GUI list
	*/
    public static javax.swing.JList<String> bathrooms3;
	/**
	* Java swing label floor 3 bathrooms label
	*/
    private javax.swing.JLabel bathrooms3Label;
	/**
	* Java swing scrol pane floor 3 bathrooms pane
	*/
    private javax.swing.JScrollPane bathrooms3Pane;
	/**
	* Check in queue GUI list
	*/
    public static javax.swing.JList<String> checkInQueue;
	/**
	* Java swing label check in queue label
	*/
    private javax.swing.JLabel checkInQueueLabel;
	/**
	* Java swing scrol pane check in queue pane
	*/
    private javax.swing.JScrollPane checkInQueuePane;
	/**
	* Floor 1 beds GUI list
	*/
    public static javax.swing.JList<String> floor1;
	/**
	* Java swing label floor 1 beds label
	*/
    private javax.swing.JLabel floor1Label;
	/**
	* Java swing scrol pane floor 1 beds pane
	*/
    private javax.swing.JScrollPane floor1Pane;
	/**
	* Floor 2 beds GUI list
	*/
    public static javax.swing.JList<String> floor2;
	/**
	* Java swing label floor 2 beds label
	*/
    private javax.swing.JLabel floor2Label;
	/**
	* Java swing scrol pane floor 2 beds pane
	*/
    private javax.swing.JScrollPane floor2Pane;
	/**
	* Floor 3 beds GUI list
	*/
    public static javax.swing.JList<String> floor3;
	/**
	* Java swing label floor 3 beds label
	*/
    private javax.swing.JLabel floor3Label;
	/**
	* Java swing scrol pane floor 3 beds pane
	*/
    private javax.swing.JScrollPane floor3Pane;
	/**
	* Java swing panel ground floor panel
	*/
    private javax.swing.JPanel groundFloorPanel;
	/**
	* Meal queue GUI list
	*/
    public static javax.swing.JList<String> mealQueue;
	/**
	* Java swing label meal queue label
	*/
    private javax.swing.JLabel mealQueueLabel;
	/**
	* Java swing scrol pane meal queue pane
	*/
    private javax.swing.JScrollPane mealQueuePane;
	/**
	* Reception queue GUI list
	*/
    public static javax.swing.JList<String> receptionQueue;
	/**
	* Java swing label reception queue label
	*/
    private javax.swing.JLabel receptionQueueLabel;
	/**
	* Java swing scrol pane reception queue pane
	*/
    private javax.swing.JScrollPane receptionQueuePane;
	/**
	* Java swing panel up floors panel
	*/
    private javax.swing.JPanel upFloorsPanel;
    // End of variables declaration//GEN-END:variables
}