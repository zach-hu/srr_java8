package com.tsagate.foundation.messaging;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;

@SuppressWarnings("unchecked")
public class ProcessResponse {
   public class ProcessDetail {
      private String name;
      private String connectionType;
      private String connectionName;
      private String ldapConnectionType;
      private int status = Status.READY;
      private boolean executed;
      private boolean onceExecution;
      private String applicationName;
      
      public ProcessDetail() {
         super();
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getConnectionType() {
         return connectionType;
      }

      public void setConnectionType(String connectionType) {
         this.connectionType = connectionType;
      }

      public String getConnectionName() {
         return connectionName;
      }

      public void setConnectionName(String connectionName) {
         this.connectionName = connectionName;
      }

      public String getLdapConnectionType() {
         return ldapConnectionType;
      }

      public void setLdapConnectionType(String ldapConnectionType) {
         this.ldapConnectionType = ldapConnectionType;
      }

      public int getStatus() {
         return status;
      }

      public void setStatus(int status) {
         this.status = status;
      }

      public boolean isExecuted() {
         return executed;
      }

      public void setExecuted(boolean executed) {
         this.executed = executed;
      }

      public boolean isOnceExecution() {
         return onceExecution;
      }

      public void setOnceExecution(boolean onceExecution) {
         this.onceExecution = onceExecution;
      }

      public String getApplicationName() {
         return applicationName;
      }

      public void setApplicationName(String applicationName) {
         this.applicationName = applicationName;
      }
   }
   
   private ProcessRequest request;
   private boolean success = false;
   private String message;
   private Map response;
   private ProcessDetail processDetail = new ProcessDetail();
   private Throwable throwable; 
   
   public ProcessResponse() {
      super();
   }
   
   public ProcessResponse(ProcessRequest request, boolean success, String message, Map response) {
      super();
      this.request = request;
      this.success = success;
      this.message = message;
      this.response = response;
      
      processDetail.setApplicationName(request.getApplicationName());
   }

   public Throwable getThrowable() {
      return throwable;
   }

   public void setThrowable(Throwable throwable) {
      this.throwable = throwable;
   }

   public ProcessDetail getProcessDetail() {
      return processDetail;
   }

   public void setProcessDetail(String name,
                                String connectionType,
                                String connectionName,
                                String ldapConnectionType,
                                int status,
                                boolean executed,
                                boolean onceExecution,
                                String applicationName) {
      processDetail.setName(name);
      processDetail.setConnectionType(connectionType);
      processDetail.setConnectionName(connectionName);
      processDetail.setLdapConnectionType(ldapConnectionType);
      processDetail.setStatus(status);
      processDetail.setExecuted(executed);
      processDetail.setOnceExecution(onceExecution);
      processDetail.setApplicationName(applicationName);
   }

   public ProcessRequest getRequest() {
      return request;
   }

   public void setRequest(ProcessRequest request) {
      this.request = request;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Map getResponse() {
      return response;
   }

   public void setResponse(Map response) {
      this.response = response;
   }
}
