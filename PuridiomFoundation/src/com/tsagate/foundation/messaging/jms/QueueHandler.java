package com.tsagate.foundation.messaging.jms;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.HashMap;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.tsagate.foundation.messaging.ProcessRequest;
import com.tsagate.foundation.messaging.ProcessResponse;

public class QueueHandler implements MessageListener {
   private static Logger LOGGER = Logger.getLogger(QueueHandler.class);
   
   private int ackMode;
   private String clientQueueName;
   private String messageBrokerUrl;
   
   private boolean transacted = false;
   private MessageProducer producer;

   private boolean connected;
   private Session session;
   private Connection connection;
   private Destination tempDest;
   
   private BlockingQueue<String> responseQueue = new SynchronousQueue<String>();
   
   public QueueHandler(String brokerURL, String queueName, String selector) {
      super();
      this.messageBrokerUrl = brokerURL;
      this.clientQueueName = queueName;
      this.ackMode = Session.AUTO_ACKNOWLEDGE;
   }
   
   private boolean isConnected() {
      return connected;
   }

   public void onMessage(Message message) {
      LOGGER.debug("entering onMessage");
       String messageText = null;
       try {
           if (message instanceof TextMessage) {
               TextMessage textMessage = (TextMessage) message;
               messageText = textMessage.getText();
               responseQueue.put(messageText);
               LOGGER.debug("MessageResponse: " + messageText);
           }
       } catch (Exception e) {
          LOGGER.error("Unexpected error: " + e.getMessage(), e);
       }
       LOGGER.debug("exiting onMessage");
   }
   
   private void send(String messageId, String msg) throws Exception {
      TextMessage txtMessage = session.createTextMessage();
      txtMessage.setText(msg);

      //Set the reply to field to the temp queue you created above, this is the queue the server
      //will respond to
      txtMessage.setJMSReplyTo(tempDest);

      //Set a correlation ID so when you get a response you know which sent message the response is for
      //If there is never more than one outstanding message to the server then the
      //same correlation ID can be used for all the messages...if there is more than one outstanding
      //message to the server you would presumably want to associate the correlation ID with this
      //message somehow...a Map works good
      String correlationId = messageId;
      if (correlationId == null) {
        correlationId = UUID.randomUUID().toString();
      }
      
      txtMessage.setJMSCorrelationID(correlationId);
      this.producer.send(txtMessage);
   }
   
   public void send(ProcessResponse response) throws Exception {
      String respXml = (new XStream()).toXML(response);
      LOGGER.debug("send-MessageRequest: " + respXml);
      send(response.getRequest().getId(),  respXml);
   }
   
   public ProcessResponse send_receive(ProcessRequest message) throws Exception {
      ProcessResponse rv = null;
      
      String requestXml = (new XStream()).toXML(message);
      LOGGER.debug("MessageRequest: " + requestXml);
      send(message.getId(),  requestXml);
      String responseMessage = responseQueue.poll(5000l, TimeUnit.MILLISECONDS);
      if (responseMessage == null) {
         LOGGER.error("Response not received for request: " + message.getId());
         rv = new ProcessResponse(message, false, "Response not received for request: " + message.getId(), null);
      }
      else {
         rv = (ProcessResponse) ((new XStream()).fromXML(responseMessage));
      }
      
      return rv;
   }
   
   public void connect() throws Exception {   
      if (isConnected()) {
         LOGGER.debug("bypassing JMS connection - reusing current connection");
         return;
      }
      
      try {
         ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(messageBrokerUrl);
         connection = connectionFactory.createConnection();
         connection.start();
         session = connection.createSession(transacted, ackMode);
         Destination adminQueue = session.createQueue(clientQueueName);
   
         //Setup a message producer to send message to the queue the server is consuming from
         this.producer = session.createProducer(adminQueue);
         this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
   
         //Create a temporary queue that this client will listen for responses on then create a consumer
         //that consumes message from this temporary queue...for a real application a client should reuse
         //the same temp queue for each message to the server...one temp queue per client
         tempDest = session.createTemporaryQueue();
         MessageConsumer responseConsumer = session.createConsumer(tempDest);
   
         //This class will handle the messages to the temp queue as well
         responseConsumer.setMessageListener(this);
         connected = true;
      }
      catch (Throwable t) {
         LOGGER.error("Error establishing connection to JMS service provider: " + t.getMessage(), t);
      }
   }
   
   public void stop() throws Exception {
      connected = false;
      
      try {
         connection.close();
         session.close();
      }
      catch (Throwable t) {
         // eat exceptions ...
      }
   }
}


