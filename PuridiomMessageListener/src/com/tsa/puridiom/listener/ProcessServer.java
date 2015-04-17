package com.tsa.puridiom.listener;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

import com.tsagate.properties.DictionaryManager;

import com.thoughtworks.xstream.XStream;
import com.tsagate.foundation.messaging.ProcessRequest;
import com.tsagate.foundation.messaging.ProcessRequestManager;
import com.tsagate.foundation.messaging.ProcessResponse;
import com.tsagate.foundation.processengine.Status;

public class ProcessServer {
   private static Logger LOGGER = Logger.getLogger(ProcessServer.class);

   private final static String REQUEST_JMS_PROVIDER_MODE_PARM_NAME = "JMS_PROVIDER_MODE";
   private final static String JMS_PROVIDER_USER_NAME_PARM_NAME = "JMS_PROVIDER_USERNAME";
   private final static String JMS_PROVIDER_PASSWORD_PARM_NAME = "JMS_PROVIDER_PASSWORD";

   private final static String DEFAULT_MESSAGE_BROKER_URL = "tcp://localhost:9988";
   private final static String DEFAULT_QUEUE_NAME = "RQST_QUEUE";
   private final static String DEFAULT_JMS_PROVIDER_TYPE = "IN_MEMORY";

   private final static int DEFAULT_ACK =Session.AUTO_ACKNOWLEDGE;

   public class ProcessEngineListener implements MessageListener {
      private int ackMode;
      private String messageQueueName;
      private String messageBrokerUrl;

      private Session session;
      private boolean transacted = false;
      private MessageProducer replyProducer;
      private BrokerService broker;
      private boolean remoteProvider;

      public ProcessEngineListener(String messageBrokerUrl, String messageQueueName, int ackMode, boolean remoteProvider) {
         super();
         this.messageBrokerUrl = messageBrokerUrl;
         this.messageQueueName = messageQueueName;
         this.ackMode = ackMode;
         this.remoteProvider = remoteProvider;
      }

      public void stop() throws Exception {
         LOGGER.info("Stopping ProcessEngineListener ...");
         if (broker != null) {
            broker.stop();
         }
         LOGGER.info("... ProcessEngineListener stopped.");
      }

      public void start_broker() throws Exception {
         LOGGER.info("entering start_broker - remoteProvider: " + remoteProvider);
         if (!remoteProvider) {
            broker = new BrokerService();
            broker.setPersistent(false);
            broker.setUseJmx(false);
            broker.addConnector(messageBrokerUrl);
            broker.start();
         }
         LOGGER.info("exiting start_broker");
      }

      public void start_queue_consumer() throws Exception {
         LOGGER.info("entering start_queue_consumer");
         String username = DictionaryManager.getInstance(ProcessRequestManager.JMS_PROPERTY_FILE_NAME, "").getProperty(JMS_PROVIDER_USER_NAME_PARM_NAME);
         String password = DictionaryManager.getInstance(ProcessRequestManager.JMS_PROPERTY_FILE_NAME, "").getProperty(JMS_PROVIDER_PASSWORD_PARM_NAME);

         ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, messageBrokerUrl);
         Connection connection;
         try {
            connection = connectionFactory.createConnection();
            connection.start();
            this.session = connection.createSession(this.transacted, ackMode);
            Destination adminQueue = this.session.createQueue(messageQueueName);

            // Setup a message producer to respond to messages from clients, we
            // will get the destination
            // to send to from the JMSReplyTo header field from a Message
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Set up a consumer to consume messages off of the admin queue
            MessageConsumer consumer = this.session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
         } catch (JMSException e) {
            LOGGER.error("Error starting queue consumer: " + e.getMessage(), e);
         }
         LOGGER.info("exiting start_queue_consumer");
      }

      public void onMessage(Message message) {
         LOGGER.debug("entering onMessage: "  + message);

         ProcessResponse resp = null;
         try {
            TextMessage response = this.session.createTextMessage();
            if (message instanceof TextMessage) {
               TextMessage txtMsg = (TextMessage) message;
               String messageText = txtMsg.getText();
               try {
                  ProcessRequest rqst = (ProcessRequest) (new XStream()).fromXML(messageText);
                  if (rqst.getProcessName().equals("Ping")) {
                     resp = new ProcessResponse(rqst,
                           true,
                           "Pong",
                           rqst.getRequest());
                     resp.setProcessDetail("Ping", null, null, null, Status.SUCCEEDED, true, true, null);
                  }
                  else {
                     resp = ProcessRequestManager.getInstance().process(rqst);
                  }
               }
               catch (Throwable t) {
                  LOGGER.error("error processing message: " + t.getMessage(), t);
                  resp = new ProcessResponse(null,
                        false,
                        t.getMessage(),
                        null);
               }
            }

            if (resp == null) {
               resp = new ProcessResponse(null,
                     false,
                     "Unsupported request type.",
                     null);
            }

            response.setJMSCorrelationID(message.getJMSCorrelationID());
            response.setText((new XStream()).toXML(resp));
            this.replyProducer.send(message.getJMSReplyTo(), response);
         } catch (JMSException e) {
            LOGGER.error("Unexpected error received while processing message: " + e.getMessage(), e);
         }

         LOGGER.debug("exiting onMessage");
      }
   }

   private ProcessEngineListener listener;

   public void start(boolean remoteProvider, String messageBrokerUrl, String messageQueueName, int ackMode) throws Exception {
      LOGGER.info("starting [brokerURL:" + messageBrokerUrl + ", queueName:" + messageQueueName + ", ackMode: " + ackMode + ", remoteProvider: " + remoteProvider + "]");

      listener = new ProcessEngineListener(messageBrokerUrl, messageQueueName, ackMode, remoteProvider);
      listener.start_broker();
      listener.start_queue_consumer();

      Runtime.getRuntime().addShutdownHook(new Thread() {
         public void run() {
            try {
               listener.stop();
            } catch (Exception e) {
               LOGGER.error("Unexpected error received while stopping listener: " + e.getMessage(), e);
            }
         }
     });
   }

   public void stop() throws Exception {
      listener.stop();
   }

   public static void main(String[] args) {
      try {
         ProcessRequestManager.getInstance().setMode(ProcessRequestManager.Mode.INLINE);

         ProcessServer ps = new ProcessServer();
         if (args.length == 3) {
            ps.start(false, args[0], args[1], Integer.parseInt(args[2]));
         }
         else {
            LOGGER.info("Parameters missing in ProcessServer.  Attempting to retrieve from property files...");

            String messageBrokerUrl = DictionaryManager.getInstance(ProcessRequestManager.JMS_PROPERTY_FILE_NAME, "").getProperty(ProcessRequestManager.JMS_BROKER_URL_PARAMETER_NAME, DEFAULT_MESSAGE_BROKER_URL);
            String messageQueueName = DictionaryManager.getInstance(ProcessRequestManager.JMS_PROPERTY_FILE_NAME, "").getProperty(ProcessRequestManager.JMS_REQUEST_QUEUE_NAME_PARAMETER_NAME, DEFAULT_QUEUE_NAME);
            String jmsProviderType = DictionaryManager.getInstance(ProcessRequestManager.JMS_PROPERTY_FILE_NAME, "").getProperty(REQUEST_JMS_PROVIDER_MODE_PARM_NAME, DEFAULT_JMS_PROVIDER_TYPE);
            boolean remoteProvider = false;
            if (jmsProviderType != null && jmsProviderType.equals("REMOTE")) {
               remoteProvider = true;
            }
            ps.start(remoteProvider, messageBrokerUrl, messageQueueName, DEFAULT_ACK);
         }
      }
      catch (Throwable t) {
         LOGGER.error("Unexpected error encountered: " + t.getMessage(), t);
      }
   }
}
