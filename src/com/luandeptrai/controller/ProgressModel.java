/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author luannm
 */
public class ProgressModel extends TimerTask {
  
  private static Timer timer;
  private static int tick = 0, minutes, seconds, minuteOriginze, secondOriginze;
  private static String timeLabel;
  private static int stopValue;
  private static javax.swing.JProgressBar progress;
  private static javax.swing.JButton button;
  private static javax.swing.JLabel label;
  
  public ProgressModel(javax.swing.JProgressBar progressBar, int maxValue) {
    progress = progressBar;
    tick = 0;
    stopValue = maxValue;
  }
 
  public static void start(javax.swing.JProgressBar progress, int iTime, javax.swing.JButton btn, javax.swing.JLabel lbl) {
    timer = new Timer();
    button = btn;
    label = lbl;
    minuteOriginze = minutes = iTime;
    secondOriginze = seconds =  0;
    progress.setMinimum(0);
    progress.setMaximum(iTime * 60);
    progress.setValue(0);
    timer.scheduleAtFixedRate(new ProgressModel(progress, iTime * 60), 0, 1000);
  }
  
  public static void stop() {
    timer.cancel();
    button.setEnabled(true);
    calculateTime(true);
    label.setText(timeLabel);
  }
  
  private static void calculateTime(boolean isReset) {
    if(isReset) { 
      seconds = 0;
      minutes = 0;
    } else {
      seconds--;
      if(seconds <= 0) {
        seconds = 59;
        minutes--;
      }
      if(minutes < 0) {
        minutes = 0;
      }
    }
    timeLabel = String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
  }
  
  @Override
  public void run() {
    if(tick <= stopValue) {
      progress.setValue(tick);
      tick++;
      calculateTime(false);
      label.setText(timeLabel);
    } else {
      button.setEnabled(true);
    }
  }
}
