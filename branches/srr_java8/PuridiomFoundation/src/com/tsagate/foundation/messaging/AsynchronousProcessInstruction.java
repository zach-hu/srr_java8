package com.tsagate.foundation.messaging;

public class AsynchronousProcessInstruction {
   private String successProcessName;
   private String failureProcessName;
   
   public AsynchronousProcessInstruction() {
      super();
   }

   public String getSuccessProcessName() {
      return successProcessName;
   }

   public void setSuccessProcessName(String successProcessName) {
      this.successProcessName = successProcessName;
   }

   public String getFailureProcessName() {
      return failureProcessName;
   }

   public void setFailureProcessName(String failureProcessName) {
      this.failureProcessName = failureProcessName;
   }
}
