/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author luannm
 */
public class ProgressTimer {

  private Scheduler scheduler;
  private JobKey currentJobKey;

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

  public void start(int minutes) {
    try {
      JobDetail job = newJob(TimerJob.class)
              .withIdentity("TimerJob", "Timer")
              .usingJobData("minutes", minutes)
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
      System.out.println(e.getMessage());
    }
  }

  public void stop() {
    if (currentJobKey != null) {
      try {
        this.scheduler.deleteJob(this.currentJobKey);
      } catch (SchedulerException e) {
        System.out.println(e.getMessage());
      }
    }
  }
  
  public static void main(String[] args) {
    ProgressTimer progressTimer = new ProgressTimer();
    progressTimer.start(5);
  }

}
