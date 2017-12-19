/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author luannm
 */
public class TimerJob implements Job {

  @Override
  public void execute(JobExecutionContext jec) throws JobExecutionException {
    JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
    int minutes = dataMap.getInt("minutes");
    System.out.println(minutes);
    System.out.println(new Date());
  }
  
}
