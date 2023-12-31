/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ControlCentre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Control Centre main class.
 */
public class Main extends javax.swing.JFrame {
    
    private ArrayList<Process> monitorProcesses;
    private ArrayList<Process> loadBalancerProcesses;
    private ArrayList<Process> serverProcesses;
    private ArrayList<Process> clientProcesses;

    /**
     * Creates new form Main
     */
    public Main() {
        this.monitorProcesses = new ArrayList<>();
        this.loadBalancerProcesses = new ArrayList<>();
        this.serverProcesses = new ArrayList<>();
        this.clientProcesses = new ArrayList<>();
        
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
        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        buttonAddMonitor = new javax.swing.JButton();
        buttonRemoveMonitor = new javax.swing.JButton();
        buttonAddLoadBalancer = new javax.swing.JButton();
        buttonRemoveLoadBalancer = new javax.swing.JButton();
        buttonAddServer = new javax.swing.JButton();
        buttonRemoveServer = new javax.swing.JButton();
        buttonAddClient = new javax.swing.JButton();
        buttonRemoveClient = new javax.swing.JButton();
        listPanel = new javax.swing.JPanel();
        listScrollPane1 = new javax.swing.JScrollPane();
        list1 = new javax.swing.JList<>();
        listScrollPane2 = new javax.swing.JScrollPane();
        list2 = new javax.swing.JList<>();
        listScrollPane3 = new javax.swing.JScrollPane();
        list3 = new javax.swing.JList<>();
        listScrollPane4 = new javax.swing.JScrollPane();
        list4 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control Centre");
        setResizable(false);

        basePanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(55, 55, 55));
        headerPanel.setForeground(new java.awt.Color(255, 255, 255));
        headerPanel.setPreferredSize(new java.awt.Dimension(400, 50));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Monitor");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Load Balancer(s)");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Server(s)");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Client(s)");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)))
        );

        basePanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        buttonPanel.setBackground(new java.awt.Color(65, 65, 65));
        buttonPanel.setForeground(new java.awt.Color(255, 255, 255));

        buttonAddMonitor.setText("+");
        buttonAddMonitor.setActionCommand("addMonitor");
        buttonAddMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonRemoveMonitor.setText("-");
        buttonRemoveMonitor.setActionCommand("removeMonitor");
        buttonRemoveMonitor.setEnabled(false);
        buttonRemoveMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonAddLoadBalancer.setText("+");
        buttonAddLoadBalancer.setActionCommand("addLoadBalancer");
        buttonAddLoadBalancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonRemoveLoadBalancer.setText("-");
        buttonRemoveLoadBalancer.setActionCommand("removeLoadBalancer");
        buttonRemoveLoadBalancer.setEnabled(false);
        buttonRemoveLoadBalancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonAddServer.setText("+");
        buttonAddServer.setActionCommand("addServer");
        buttonAddServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonRemoveServer.setText("-");
        buttonRemoveServer.setActionCommand("removeServer");
        buttonRemoveServer.setEnabled(false);
        buttonRemoveServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonAddClient.setText("+");
        buttonAddClient.setActionCommand("addClient");
        buttonAddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        buttonRemoveClient.setText("-");
        buttonRemoveClient.setActionCommand("removeClient");
        buttonRemoveClient.setEnabled(false);
        buttonRemoveClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(buttonAddMonitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveMonitor)
                .addGap(30, 30, 30)
                .addComponent(buttonAddLoadBalancer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveLoadBalancer)
                .addGap(30, 30, 30)
                .addComponent(buttonAddServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveServer)
                .addGap(30, 30, 30)
                .addComponent(buttonAddClient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveClient)
                .addGap(0, 0, 0))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddClient)
                        .addComponent(buttonRemoveClient))
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddServer)
                        .addComponent(buttonRemoveServer))
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddLoadBalancer)
                        .addComponent(buttonRemoveLoadBalancer))
                    .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddMonitor)
                        .addComponent(buttonRemoveMonitor)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        basePanel.add(buttonPanel, java.awt.BorderLayout.CENTER);

        listPanel.setBackground(new java.awt.Color(65, 65, 65));
        listPanel.setForeground(new java.awt.Color(255, 255, 255));
        listPanel.setPreferredSize(new java.awt.Dimension(400, 200));

        list1.setBackground(new java.awt.Color(55, 55, 55));
        list1.setForeground(new java.awt.Color(255, 255, 255));
        listScrollPane1.setViewportView(list1);

        list2.setBackground(new java.awt.Color(55, 55, 55));
        list2.setForeground(new java.awt.Color(255, 255, 255));
        listScrollPane2.setViewportView(list2);

        list3.setBackground(new java.awt.Color(55, 55, 55));
        list3.setForeground(new java.awt.Color(255, 255, 255));
        listScrollPane3.setViewportView(list3);

        list4.setBackground(new java.awt.Color(55, 55, 55));
        list4.setForeground(new java.awt.Color(255, 255, 255));
        listScrollPane4.setViewportView(list4);

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(listScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(listScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(listScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(listScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listScrollPane1)
                    .addComponent(listScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(listScrollPane2)
                    .addComponent(listScrollPane3))
                .addContainerGap())
        );

        basePanel.add(listPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(basePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        try {
            if (evt.getActionCommand().contains("addMonitor")) {
                //System.out.println("addMonitor");
                monitorProcesses.add(executeProcess("javac -cp src src/Monitor/Main.java -d build/classes/", "java -cp build/classes/ Monitor.Main"));
                this.buttonAddMonitor.setEnabled(false);
                this.buttonRemoveMonitor.setEnabled(true);

                this.list1.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() { return monitorProcesses.size(); }
                    public String getElementAt(int i) { return "MONIT"; }
                });
            }
            else if (evt.getActionCommand().contains("removeMonitor")) {
                //System.out.println("removeMonitor");
                int selectedIndex = this.list1.getSelectedIndex();
                if (selectedIndex == -1)
                    selectedIndex = 0;

                System.out.println("> Destroying monitor " + selectedIndex);

                destroyProcess(monitorProcesses, selectedIndex, this.list1, "MONIT");

                this.buttonAddMonitor.setEnabled(true);
                this.buttonRemoveMonitor.setEnabled(false);

                // clear load balancer processes
                while (!this.loadBalancerProcesses.isEmpty())
                    destroyProcess(this.loadBalancerProcesses, 0, this.list2, "LB");
                // clear server processes
                while (!this.serverProcesses.isEmpty())
                    destroyProcess(this.serverProcesses, 0, this.list3, "SV");

                // re-enable load balancer add button
                this.buttonAddLoadBalancer.setEnabled(true);

                // disable load balancer and server remove button
                this.buttonRemoveLoadBalancer.setEnabled(false);
                this.buttonRemoveServer.setEnabled(false);
            }
            else if (evt.getActionCommand().contains("addLoadBalancer")) {
                //System.out.println("addLoadBalancer");
                this.loadBalancerProcesses.add(executeProcess("javac -cp src src/LoadBalancer/Main.java -d build/classes/", "java -cp build/classes/ LoadBalancer.Main"));
                if (this.loadBalancerProcesses.size() > 1)
                    this.buttonAddLoadBalancer.setEnabled(false);
                this.buttonRemoveLoadBalancer.setEnabled(true);

                this.list2.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() { return loadBalancerProcesses.size(); }
                    public String getElementAt(int i) { return "LB" + i; }
                });
            }
            else if (evt.getActionCommand().contains("removeLoadBalancer")) {
                //System.out.println("removeLoadBalancer");
                int selectedIndex = this.list2.getSelectedIndex();
                if (selectedIndex == -1)
                    selectedIndex = 0;

                System.out.println("> Destroying load balancer " + selectedIndex);

                destroyProcess(loadBalancerProcesses, selectedIndex, this.list2, "LB");

                this.buttonAddLoadBalancer.setEnabled(true);
                if (this.loadBalancerProcesses.isEmpty())
                    this.buttonRemoveLoadBalancer.setEnabled(false);
            }
            else if (evt.getActionCommand().contains("addServer")) {
                //System.out.println("addServer");
                this.serverProcesses.add(executeProcess("javac -cp src src/Server/Main.java -d build/classes/", "java -cp build/classes/ Server.Main"));
                this.buttonRemoveServer.setEnabled(true);

                this.list3.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() { return serverProcesses.size(); }
                    public String getElementAt(int i) { return "SV" + i; }
                });
            }
            else if (evt.getActionCommand().contains("removeServer")) {
                //System.out.println("removeServer");
                int selectedIndex = this.list3.getSelectedIndex();
                if (selectedIndex == -1)
                    selectedIndex = 0;

                System.out.println("> Destroying server " + selectedIndex);

                destroyProcess(serverProcesses, selectedIndex, this.list3, "SV");

                if (this.serverProcesses.isEmpty())
                    this.buttonRemoveServer.setEnabled(false);
            }
            else if (evt.getActionCommand().contains("addClient")) {
                //System.out.println("addClient");
                this.clientProcesses.add(executeProcess("javac -cp src src/Client/Main.java -d build/classes/", "java -cp build/classes/ Client.Main"));
                this.buttonRemoveClient.setEnabled(true);

                this.list4.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() { return clientProcesses.size(); }
                    public String getElementAt(int i) { return "CL" + i; }
                });
            }
            else if (evt.getActionCommand().contains("removeClient")) {
                //System.out.println("removeClient");
                int selectedIndex = this.list4.getSelectedIndex();
                if (selectedIndex == -1)
                    selectedIndex = 0;

                System.out.println("> Destroying client " + selectedIndex);

                destroyProcess(clientProcesses, selectedIndex, this.list4, "CL");

                if (this.clientProcesses.isEmpty())
                    this.buttonRemoveClient.setEnabled(false);
            }
        } catch (IOException e) {
            System.out.append("IOException " + e.getMessage());
        }
    }//GEN-LAST:event_buttonActionPerformed

    private void destroyProcess(ArrayList<Process> processes, int removeIndex, javax.swing.JList list, String type) {
        processes.get(removeIndex).destroy();
        processes.remove(removeIndex);
        
        list.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return processes.size(); }
            public String getElementAt(int i) { return type + i; }
        });
    }

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
        
        /*try {
            executeProcess("javac -cp src src/LoadBalancer/Main.java -d build/classes/", "java -cp build/classes/ LoadBalancer.Main");
        } catch (IOException e) {
            System.out.append("IOException " + e.getMessage());
        }*/
    }
    
    @SuppressWarnings("empty-statement")
    public static Process executeProcess(String sourceDir, String classDir) throws IOException {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        
        Process sourceProcess = Runtime.getRuntime()
            .exec(sourceDir);        // "javac -cp src src/LoadBalancer/Main.java -d build/classes/"
        
        Process classProcess = Runtime.getRuntime() 
            .exec(classDir);        // "java -cp build/classes/ LoadBalancer.Main"
        
        // start a thread to handle the subprocess input stream
        new Thread(() -> {
            try {
                // read from the subprocess's input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(classProcess.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null);
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }).start();
        
        return classProcess;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basePanel;
    private javax.swing.JButton buttonAddClient;
    private javax.swing.JButton buttonAddLoadBalancer;
    private javax.swing.JButton buttonAddMonitor;
    private javax.swing.JButton buttonAddServer;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton buttonRemoveClient;
    private javax.swing.JButton buttonRemoveLoadBalancer;
    private javax.swing.JButton buttonRemoveMonitor;
    private javax.swing.JButton buttonRemoveServer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> list1;
    private javax.swing.JList<String> list2;
    private javax.swing.JList<String> list3;
    private javax.swing.JList<String> list4;
    private javax.swing.JPanel listPanel;
    private javax.swing.JScrollPane listScrollPane1;
    private javax.swing.JScrollPane listScrollPane2;
    private javax.swing.JScrollPane listScrollPane3;
    private javax.swing.JScrollPane listScrollPane4;
    // End of variables declaration//GEN-END:variables
}
