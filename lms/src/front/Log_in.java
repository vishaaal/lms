
package front;

import java.sql.Connection;
import java.sql.SQLException;   
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Log_in extends javax.swing.JFrame {
    
    public Log_in() {
        initComponents();
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        heading = new javax.swing.JLabel();
        uNameL = new javax.swing.JLabel();
        uText = new javax.swing.JTextField();
        pL = new javax.swing.JLabel();
        pT = new javax.swing.JPasswordField();
        stdR = new javax.swing.JRadioButton();
        stfR = new javax.swing.JRadioButton();
        adminR = new javax.swing.JRadioButton();
        logIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        heading.setText("LIBRARY MANAGEMENT SYSTEM");

        uNameL.setText("USER NAME");

        pL.setText("PASSWORD");

        pT.setEchoChar((char)149);

        buttonGroup1.add(stdR);
        stdR.setSelected(true);
        stdR.setText("STUDENT");

        buttonGroup1.add(stfR);
        stfR.setText("STAFF");

        buttonGroup1.add(adminR);
        adminR.setText("ADMIN");
        adminR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRActionPerformed(evt);
            }
        });

        logIn.setText("LOG IN");
        logIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pL)
                    .addComponent(uNameL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(uText, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(pT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(stdR)
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logIn)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stfR)
                        .addGap(74, 74, 74)
                        .addComponent(adminR)))
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(heading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(heading)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uNameL)
                    .addComponent(uText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pL)
                    .addComponent(pT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stdR)
                    .addComponent(stfR)
                    .addComponent(adminR))
                .addGap(71, 71, 71)
                .addComponent(logIn)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInActionPerformed
        
        final ExecutorService service;
        final Future<Boolean>  task;
        String a[]=new String[2];
        a[0]=uText.getText();
        if(stdR.isSelected()) a[1]="student";
        else if(stfR.isSelected()) a[1]="staff";
        else a[1]="admin";
        
        service = Executors.newFixedThreadPool(1);        
        task    = service.submit(new processing.LogInQuerry(a[1], a[0], String.copyValueOf(pT.getPassword())));

        try {
            // waits the 10 seconds for the Callable.call to finish.
            // this raises ExecutionException if thread dies
           if(task.get()){
            this.setVisible(false);
            
            Student_main.main(a);
           }
        else javax.swing.JOptionPane.showMessageDialog(null, "incorrect credentials");
            pT.setText("");
        } catch(final InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        service.shutdownNow();
    }//GEN-LAST:event_logInActionPerformed

    private void adminRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminRActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Log_in.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Log_in().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adminR;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel heading;
    private javax.swing.JButton logIn;
    private javax.swing.JLabel pL;
    private javax.swing.JPasswordField pT;
    private javax.swing.JRadioButton stdR;
    private javax.swing.JRadioButton stfR;
    private javax.swing.JLabel uNameL;
    private javax.swing.JTextField uText;
    // End of variables declaration//GEN-END:variables
}
