package com.tsagate.foundation.messaging.monitor;

public class SummarizedRequestResult extends RequestResult {
   private int count;
   private long minElapsedTime;
   private long maxElapsedTime;
   private double averageElapsedTime;
   
   public SummarizedRequestResult() {
      super();
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public long getMinElapsedTime() {
      return minElapsedTime;
   }

   public void setMinElapsedTime(long minElapsedTime) {
      this.minElapsedTime = minElapsedTime;
   }

   public long getMaxElapsedTime() {
      return maxElapsedTime;
   }

   public void setMaxElapsedTime(long maxElapsedTime) {
      this.maxElapsedTime = maxElapsedTime;
   }

   public double getAverageElapsedTime() {
      return averageElapsedTime;
   }

   public void setAverageElapsedTime(double averageElapsedTime) {
      this.averageElapsedTime = averageElapsedTime;
   }
}
