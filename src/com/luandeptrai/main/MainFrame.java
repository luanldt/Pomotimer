/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.main;

import com.luandeptrai.controller.ProgressTimer;
import com.luandeptrai.controller.Settings;
import com.luandeptrai.controller.TimerJob;
import java.util.Timer;
import javax.swing.UIManager;

/**
 *
 * @author luannm
 */
public class MainFrame extends javax.swing.JFrame {

  public static final Settings SETTINGS = new Settings();
  private final ProgressTimer progressTimer = new ProgressTimer();
  
  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    this.setTitle("Podotimer");
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

    progressBarDuration = new javax.swing.JProgressBar();
    btnStart = new javax.swing.JButton();
    btnStop = new javax.swing.JButton();
    lblTime = new javax.swing.JLabel();

    setAlwaysOnTop(true);
    setLocation(new java.awt.Point(0, 0));
    setLocationByPlatform(true);
    setResizable(false);
    setType(java.awt.Window.Type.UTILITY);

    progressBarDuration.setForeground(java.awt.Color.green);

    btnStart.setText("Start");
    btnStart.setName("btnStart"); // NOI18N
    btnStart.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnStartActionPerformed(evt);
      }
    });

    btnStop.setText("Stop");
    btnStop.setEnabled(false);
    btnStop.setName("btnStop"); // NOI18N
    btnStop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnStopActionPerformed(evt);
      }
    });

    lblTime.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTime.setText("00 : 00");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(progressBarDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(btnStart)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnStop)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(lblTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(progressBarDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnStart)
          .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
    String time = SETTINGS.readProperty("settings.basic.shortbreak.time");
    int iTime = Integer.parseInt(time);
    progressBarDuration.setMaximum(iTime * 60);
    progressBarDuration.setMinimum(0);  
    progressTimer.registerControl(progressBarDuration, lblTime, btnStart, btnStop);
    progressTimer.start(iTime);
    this.btnStart.setEnabled(false);
    this.btnStop.setEnabled(true);
    TimerJob.CURRENT_MINUTE = iTime;
    TimerJob.CURRENT_SECONDS = 0;
  }//GEN-LAST:event_btnStartActionPerformed

  private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
    progressTimer.stop();
    this.btnStop.setEnabled(false);
    this.btnStart.setEnabled(true);
    this.progressBarDuration.setValue(0);
    TimerJob.iCOUNT = 0;
    TimerJob.CURRENT_MINUTE = 0;
    TimerJob.CURRENT_SECONDS = 0;
  }//GEN-LAST:event_btnStopActionPerformed

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
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
      try {
        System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
      } catch(Exception e) {
        e.getMessage();
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnStart;
  private javax.swing.JButton btnStop;
  private javax.swing.JLabel lblTime;
  private javax.swing.JProgressBar progressBarDuration;
  // End of variables declaration//GEN-END:variables
}
