/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.core.jmx.JobDataMapSupport.newJobDataMap;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author luannm
 */
public class ProgressTimer {

  private Scheduler scheduler;
  private JobKey currentJobKey;
  private javax.swing.JProgressBar progressBarDuration;
  private javax.swing.JLabel lblTime;
  private javax.swing.JButton btnStart;
  private javax.swing.JButton btnEnd;

  public ProgressTimer() {
    try {
      BasicConfigurator.configure();
      Logger.getRootLogger().setLevel(Level.OFF);
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
    } catch (SchedulerException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void registerControl(javax.swing.JProgressBar progressBarDuration,
          javax.swing.JLabel lblTime, javax.swing.JButton btnStart,
          javax.swing.JButton btnEnd) {
    this.progressBarDuration = progressBarDuration;
    this.lblTime = lblTime;
    this.btnStart = btnStart;
    this.btnEnd = btnEnd;
  }

  public void start(int minutes) {
    try {
      Map<String, Object> m = new HashMap<>();
      m.put("progressBar", this.progressBarDuration);
      m.put("label", this.lblTime);
      m.put("btnStart", this.btnStart);
      m.put("btnEnd", this.btnEnd);
      m.put("minutes", minutes);
      JobDataMap dataMap = new JobDataMap(m);
      JobDetail job = newJob(TimerJob.class)
              .withIdentity("TimerJob", "Timer")
              .usingJobData(dataMap)
              .build();
      Trigger trigger = newTrigger()
              .withIdentity("TimerTrigger", "Timer")
              .startNow()
              .withSchedule(SimpleScheduleBuilder.simpleSchedule()
              .withIntervalInSeconds(1)
              .withRepeatCount(minutes * 60))
              .build();
      this.currentJobKey = job.getKey();
      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException e) {
      System.out.println("START SCHEDULES: " + e.getMessage());
    }
  }

  public void stop() {
    if (currentJobKey != null) {
      try {
        this.scheduler.clear();
      } catch (SchedulerException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
