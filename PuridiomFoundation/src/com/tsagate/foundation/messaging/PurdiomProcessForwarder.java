package com.tsagate.foundation.messaging;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;

@SuppressWarnings("unchecked")
public class PurdiomProcessForwarder extends PuridiomProcess {
   private String applicationName;
   private String processName;
   private String organizationId;
   
   public PurdiomProcessForwarder(String applicationName, String processName, String organizationId) {
      super();
      this.applicationName = applicationName;
      this.processName = processName;
      this.organizationId = organizationId;
   }
   
   public void executeProcess(Map request) throws Exception {
      ProcessRequest rqst = new ProcessRequest();
      rqst.setApplicationName(applicationName);
      rqst.setProcessName(processName);
      rqst.setOrgId(organizationId);
      rqst.setRequest(request);
      ProcessResponse resp = ProcessRequestManager.getInstance().process(rqst);
      setStatus(resp.getProcessDetail().getStatus());
      setConnectionName(resp.getProcessDetail().getConnectionName());
      setConnectionType(resp.getProcessDetail().getConnectionType());
      if (resp.getThrowable() != null) {
         if (resp.getThrowable() instanceof Exception) {
            throw (Exception) resp.getThrowable();
         }
      }
   }
}
