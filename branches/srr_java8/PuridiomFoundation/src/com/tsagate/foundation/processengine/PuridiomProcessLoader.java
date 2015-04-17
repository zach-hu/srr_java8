/*
 * Created on Jul 31, 2003
 */
package com.tsagate.foundation.processengine;

import com.tsagate.foundation.messaging.PurdiomProcessForwarder;

/**
 * @author Administrator
 */
public class PuridiomProcessLoader {
   private String applicationName = "";
   private String organizationId = "";

   /**
    * @return Returns the organizationId.
    */
   public String getOrganizationId() {
      return this.organizationId;
   }

   /**
    * @param organizationId
    *           The organizationId to set.
    */
   public void setOrganizationId(String organizationId) {
      this.organizationId = organizationId;
   }

   public PuridiomProcessLoader(String asOrganizationId) {
      this.setOrganizationId(asOrganizationId);
   }

   public PuridiomProcessLoader() {
   }

   public PuridiomProcess loadProcess(String processName) throws Exception {
      return new PurdiomProcessForwarder(getApplicationName(), processName, getOrganizationId());
   }

   public String getApplicationName() {
      return applicationName;
   }

   public void setApplicationName(String appName) {
      this.applicationName = appName;
   }
}
