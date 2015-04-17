package com.tsagate.foundation.messaging.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tsagate.foundation.messaging.monitor.SummarizedRequestResult;

public class RequestMonitor {
   private List<String> processNames = new ArrayList<String>();
   private List<RequestResult> results = new ArrayList<RequestResult>();
   
   public RequestMonitor() {
      super();
   }
   
   public RequestMonitor(int httpPort) {
      super();
   }
   
   public void addProcessResult(RequestResult result) {
      if (result.getProcessName() != null && !processNames.contains(result.getProcessName())) {
         processNames.add(result.getProcessName());
      }
      
      results.add(result);
   }
   
   public List<SummarizedRequestResult> getSummarizedResults() {
      List<SummarizedRequestResult> rv = new ArrayList<SummarizedRequestResult>();
      
      for (String name : processNames) {
         rv.add(getSummarizedResultForProcess(name));
      }
      
      return rv;
   }
   
   public SummarizedRequestResult getSummarizedResultForProcess(String processName) {
      SummarizedRequestResult rv = new SummarizedRequestResult();

      int ctr = 0;
      long minTime = Integer.MAX_VALUE;
      long maxTime = Integer.MIN_VALUE;
      long totalTime = 0;
      
      for (RequestResult rslt : results) {
         if (rslt.getProcessName().equals(processName)) {
            ctr++;
            totalTime += rslt.getElapsedTime();
            
            if (rslt.getElapsedTime() > maxTime) {
               maxTime = rslt.getElapsedTime();
            }
            
            if (rslt.getElapsedTime() < minTime) {
               minTime = rslt.getElapsedTime();
            }
         }
      }

      if (ctr != 0) {
         rv.setAverageElapsedTime(totalTime / ctr);
         rv.setCount(ctr);
         rv.setElapsedTime(totalTime);
         rv.setMaxElapsedTime(maxTime);
         rv.setMinElapsedTime(minTime);
         rv.setProcessName(processName);
      }
      
      return rv;
   }
   
   public SummarizedRequestResult getSummarizedResultForOrganization(String orgName) {
      SummarizedRequestResult rv = null;
      
      return rv;
   }
   
   public SummarizedRequestResult getSummarizedResultForUser(String userName) {
      SummarizedRequestResult rv = null;
      
      return rv;
   }
}
