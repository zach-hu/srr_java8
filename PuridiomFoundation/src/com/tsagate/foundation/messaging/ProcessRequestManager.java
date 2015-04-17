package com.tsagate.foundation.messaging;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.jms.Session;

import org.apache.log4j.Logger;

import com.tsagate.foundation.messaging.jms.QueueHandler;
import com.tsagate.foundation.processengine.PuridiomProcess;

import com.thoughtworks.xstream.XStream;
import com.tsagate.properties.DictionaryManager;


@SuppressWarnings("unchecked")
public class ProcessRequestManager {
   private static Logger LOGGER = Logger.getLogger(ProcessRequestManager.class);
   
   private final static ProcessRequestManager instance = new ProcessRequestManager();
   
   public final static String JMS_PROPERTY_FILE_NAME = "puridiom-jms";
   public final static String JMS_BROKER_URL_PARAMETER_NAME = "JMS_BROKER_URL";
   public final static String JMS_REQUEST_QUEUE_NAME_PARAMETER_NAME = "JMS_REQUEST_QUEUE_NAME";
   public final static String REQUEST_PROCESS_MODE_PARAMETER_NAME = "REQUEST_PROCESSING_MODE";
         
   private final static String DEFAULT_MESSAGE_BROKER_URL = "tcp://localhost:9988";
   private final static String DEFAULT_QUEUE_NAME = "RQST_QUEUE";
   
   @SuppressWarnings("unused")
   private final static int DEFAULT_ACK = Session.AUTO_ACKNOWLEDGE;
   
   public static enum Mode {
      JMS,
      INLINE
   }

   private Map<Thread, QueueHandler> queueHandlers = new HashMap<Thread, QueueHandler>();
   
   private Mode mode = Mode.INLINE;
   
   private ProcessRequestManager() {
      super();
   }
   
   public void setMode(Mode mode) {
      this.mode = mode;
   }
   
   public Mode getMode() {
      return mode;
   }
   
   public static ProcessRequestManager getInstance() {
      return instance;
   }
   
   {
      String tmpMode = DictionaryManager.getInstance(JMS_PROPERTY_FILE_NAME, "").getProperty(REQUEST_PROCESS_MODE_PARAMETER_NAME, "");
      if (tmpMode != null && tmpMode.equals("JMS")) {
         mode = Mode.JMS;
      }
   }
  
   private void remove_queue_handler(QueueHandler qh) {
      Thread match = null;
      for (Map.Entry<Thread, QueueHandler> entry : queueHandlers.entrySet()) {
         if (entry.getValue() == qh) {
            match = entry.getKey();
            break;
         }
      }
      
      if (match != null) {
         try {
            qh.stop();
         }
         catch (Throwable t) {
            // eat it
         }
         queueHandlers.remove(match);
      }
   }
   
   private QueueHandler get_queue_handler() throws Exception {
      QueueHandler qh = queueHandlers.get(Thread.currentThread());
      if (qh == null) {
         String messageBrokerUrl = DictionaryManager.getInstance(JMS_PROPERTY_FILE_NAME, "").getProperty(JMS_BROKER_URL_PARAMETER_NAME, DEFAULT_MESSAGE_BROKER_URL);
         String messageQueueName = DictionaryManager.getInstance(JMS_PROPERTY_FILE_NAME, "").getProperty(JMS_REQUEST_QUEUE_NAME_PARAMETER_NAME, DEFAULT_QUEUE_NAME);

         qh = new QueueHandler(messageBrokerUrl, messageQueueName, UUID.randomUUID().toString());
         queueHandlers.put(Thread.currentThread(), qh);
      }
      return qh;
   }
   
   private ProcessResponse process_jms(ProcessRequest rqst) throws Exception {
      LOGGER.debug("entering process_jms - applicationName: " + rqst.getApplicationName() + " name: " + rqst.getProcessName() + " orgId: " + rqst.getOrgId());

      QueueHandler qh = get_queue_handler();
      qh.connect();
      
      ProcessResponse resp = qh.send_receive(rqst);
      if (rqst.getRequest() != null) {
         rqst.getRequest().put("PRQM_SUCCESS", resp.isSuccess());
         rqst.getRequest().put("PRQM_MESSAGE_TEXT", resp.getMessage());
      }
      
      if (resp.getResponse() != null) {
         Iterator keyIter = resp.getResponse().keySet().iterator();
         Object key = null;
         while (keyIter.hasNext()) {
            key = keyIter.next();
            rqst.getRequest().put(key, resp.getResponse().get(key));
         }
         remove_queue_handler(qh);
      }
      LOGGER.debug("exiting process_jms");
      
      if (resp.getThrowable() != null) {
         if (resp.getThrowable() instanceof Exception) {
            throw (Exception) resp.getThrowable();
         }
      }
      return resp;
   }
   
   private ProcessResponse process_inline(ProcessRequest rqst) throws Exception {
      LOGGER.debug("entering process_inline - applicationName: " + rqst.getApplicationName() + " name: " + rqst.getProcessName() + " orgId: " + rqst.getOrgId());
      
      PuridiomProcessLoader ppl = new PuridiomProcessLoader();
      ppl.setApplicationName(rqst.getApplicationName());
      ppl.setOrganizationId(rqst.getOrgId());
      PuridiomProcess pp = ppl.loadProcess(rqst.getProcessName());
      ProcessResponse resp = null;
      
      try {
         pp.executeProcess(rqst.getRequest());
         resp = new ProcessResponse(rqst, true, "Message processed.", rqst.getRequest());
      }
      catch (Throwable t) {
         LOGGER.error("Processing process: " + rqst.getProcessName() + " error: " + t.getMessage(), t);
         resp = new ProcessResponse(rqst, false, "Error executing process: " + rqst.getProcessName() + " error: " + t.getMessage(), rqst.getRequest());
         resp.setThrowable(t);
      }

      if (pp != null) {
         // Copy the detail from the last process
         resp.setProcessDetail(pp.getName(),
                               pp.getConnectionType(),
                               pp.getConnectionName(),
                               pp.getLdapConnectionType(),
                               pp.getStatus(),
                               pp.isExecuted(),
                               pp.isOnceExecution(),
                               pp.getApplicationName());
      }
      
      LOGGER.debug("exiting process_inline");
      
      return resp;
   }
   
   public ProcessResponse process(ProcessRequest request) throws Exception {
      LOGGER.debug("entering process - mode: " + mode);
      
      ProcessResponse rv = null;
      
      if (mode == Mode.JMS) {
         rv = process_jms(request);
      }
      else {
         rv = process_inline(request);
      }
      
      LOGGER.debug("exiting process");
      
      return rv;
   }
   
   public void shutdown() {
      LOGGER.info("shutting down ...");
      for (QueueHandler qh : queueHandlers.values()) {
         try {
            qh.stop();
         } catch (Exception e) {
         }
      }
      LOGGER.info("... shut down");
   }
   
   private static ProcessRequest build_request(String xml) {
      String requestXml = xml;
      // It might not be xml - it might be a file that contains the xml
      try {
         BufferedReader r = new BufferedReader(new FileReader(xml));
         StringBuilder sb = new StringBuilder();
         String line = r.readLine();
         while (line != null) {
            sb.append(line);
            line = r.readLine();
         }
         r.close();
         requestXml = sb.toString();
      }
      catch (Throwable t) {
         // mustn't be a file
      }
      
      ProcessRequest rv = null;
      try {
         rv = (ProcessRequest) ((new XStream()).fromXML(requestXml));
      }
      catch (Throwable t) {
         System.err.println("Invalid ProcessRequest: " + requestXml);
         System.exit(1);
      }
      
      return rv;
   }
   
   public static void main(String[] args) {
      if (args.length != 1) {
         System.err.println("usage: [request_filename/request_xml]");
         System.exit(1);
      }
      
      ProcessRequest rqst = build_request(args[0]);
      
      try {
         ProcessResponse resp = ProcessRequestManager.getInstance().process(rqst);
         ProcessRequestManager.getInstance().shutdown();
         System.out.println((new XStream()).toXML(resp));
      } catch (Exception e) {
         LOGGER.error("Error: " + e.getMessage(), e);
      }
   }
}
