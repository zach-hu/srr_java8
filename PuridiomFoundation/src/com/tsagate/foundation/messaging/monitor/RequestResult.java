package com.tsagate.foundation.messaging.monitor;

public class RequestResult {
   private String userId;
   private String organizationId;
   private long elapsedTime;
   private boolean success;
   private String processName;
   
   public RequestResult() {
      super();
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getOrganizationId() {
      return organizationId;
   }

   public void setOrganizationId(String organizationId) {
      this.organizationId = organizationId;
   }

   public long getElapsedTime() {
      return elapsedTime;
   }

   public void setElapsedTime(long elapsedTime) {
      this.elapsedTime = elapsedTime;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getProcessName() {
      return processName;
   }

   public void setProcessName(String processName) {
      this.processName = processName;
   }
}
