package com.tsagate.foundation.messaging;

import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unchecked")
public class ProcessRequest {
   private String id = UUID.randomUUID().toString();
   private String applicationName;
   private String processName;
   private String orgId;
   private Map request;
   private boolean asynchronousRequest;
   
   public String getId() {
      return id;
   }
   
   public boolean isAsynchronousRequest() {
      return asynchronousRequest;
   }

   public void setAsynchronousRequest(boolean asynchronousRequest) {
      this.asynchronousRequest = asynchronousRequest;
   }

   public AsynchronousProcessInstruction getAsynchronousProcessInstruction() {
      return asynchronousProcessInstruction;
   }

   public void setAsynchronousProcessInstruction(
         AsynchronousProcessInstruction asynchronousProcessInstruction) {
      this.asynchronousProcessInstruction = asynchronousProcessInstruction;
   }

   private AsynchronousProcessInstruction asynchronousProcessInstruction;
   
   public ProcessRequest() {
      super();
   }

   public String getApplicationName() {
      return applicationName;
   }

   public void setApplicationName(String applicationName) {
      this.applicationName = applicationName;
   }

   public String getProcessName() {
      return processName;
   }

   public void setProcessName(String processName) {
      this.processName = processName;
   }

   public Map getRequest() {
      return request;
   }

   public void setRequest(Map request) {
      this.request = request;
   }

   public String getOrgId() {
      return orgId;
   }

   public void setOrgId(String orgId) {
      this.orgId = orgId;
   }
}
