/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author luannm
 */
public class TimerJob implements Job {

  private javax.swing.JProgressBar progressBarDuration;
  private javax.swing.JLabel lblTime;
  private javax.swing.JButton btnStart;
  private javax.swing.JButton btnEnd;
  private int minutes;
  public static int CURRENT_MINUTE = 0;
  public static int CURRENT_SECONDS = 0;
  public static int iCOUNT = 0;
  
  @Override
  public void execute(JobExecutionContext jec) throws JobExecutionException {
    JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
    progressBarDuration = (JProgressBar) dataMap.get("progressBar");
    lblTime = (JLabel) dataMap.get("label");
    btnStart = (JButton) dataMap.get("btnStart");
    btnEnd = (JButton) dataMap.get("btnEnd");
    minutes = dataMap.getInt("minutes");
    
    if(iCOUNT <= minutes * 60) {
      progressBarDuration.setValue(++iCOUNT);
      --CURRENT_SECONDS;
      if(CURRENT_SECONDS < 0) {
        if(CURRENT_MINUTE - 1 >= 0) {
          CURRENT_SECONDS = 59;
          --CURRENT_MINUTE;
        }
      }
    }
    
    System.out.print(iCOUNT + "\t");
    
    
    if(iCOUNT > minutes * 60) {
      iCOUNT = 0;
      CURRENT_MINUTE = 0;
      CURRENT_SECONDS = 0;
      btnStart.setEnabled(true);
      btnEnd.setEnabled(false);
    }
    
    lblTime.setText(String.format("%02d", CURRENT_MINUTE) + " : " + String.format("%02d", CURRENT_SECONDS < 0 ? 0 : CURRENT_SECONDS));
    
  }
  
}
