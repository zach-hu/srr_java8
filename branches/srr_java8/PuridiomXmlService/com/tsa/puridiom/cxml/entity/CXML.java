/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny Zapana
 *
 */
public class CXML implements Serializable {

	private String version;

	private String payloadID;

	private Date timestamp;

	private String signatureVersion;

	private String lang;

	private Header header;

	private Request request;

	private Response response;

	private String comment;

	public CXML() {
		// this.version = "1.0.0.02";
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the payloadID
	 */
	public String getPayloadID() {
		return payloadID;
	}

	/**
	 * @param payloadID
	 *            the payloadID to set
	 */
	public void setPayloadID(String payloadID) {
		this.payloadID = payloadID;
	}

	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * @return the signatureVersion
	 */
	public String getSignatureVersion() {
		return signatureVersion;
	}

	/**
	 * @param signatureVersion
	 *            the signatureVersion to set
	 */
	public void setSignatureVersion(String signatureVersion) {
		this.signatureVersion = signatureVersion;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * <!ELEMENT cXML (((Header, (Message | Request)) | Response),
	 * ds:Signature?)> <!ATTLIST cXML version %string; "&cxml.version;"
	 * payloadID %string; #REQUIRED timestamp %datetime.tz; #REQUIRED
	 * signatureVersion %cxml.signatureVersions; #IMPLIED xml:lang %xmlLangCode;
	 * #IMPLIED >
	 *
	 * @param cXMLObject
	 */
	public static void buildCXMLDocument(CXML cXMLObject,
			String organizationId, String fileName) throws Exception {
		Element cxmlDocument = new Element("cXML");

		try {
			cXMLObject.setAttributes(cxmlDocument, cXMLObject);

			cXMLObject.setContent(cxmlDocument, cXMLObject);

			Document doc = new Document(cxmlDocument);

			FileOutputStream out = new FileOutputStream(DictionaryManager
					.getInstance("host", organizationId).getProperty(
							"reportsOut", "")
					+ "cxml\\" + fileName + ".xml");

			XMLOutputter serializer = new XMLOutputter("", true);

			serializer.setExpandEmptyElements(true);
			// serializer.setTextNormalize(true);
			serializer.setNewlines(true);
			serializer.setIndent(true);

			serializer.output(doc, System.out);

			serializer.output(doc, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			Log.error(cXMLObject, "CXMLBuilder could not build CXML document: "
					+ e.getMessage() + " with ID " + fileName);
			throw e;
		}
	}

	private void setAttributes(Element cxmlDocument, CXML cXMLObject) {
		/*
		 * version %string; "&cxml.version;" payloadID %string; #REQUIRED
		 * timestamp %datetime.tz; #REQUIRED signatureVersion
		 * %cxml.signatureVersions; #IMPLIED xml:lang %xmlLangCode; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		/* version Attribute have default value */

		attributes.add(new Attribute("payloadID", cXMLObject.getPayloadID()));

		attributes.add(new Attribute("timestamp", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SS").format(cXMLObject.getTimestamp())));

		if (cXMLObject.getSignatureVersion() != null) {
			attributes.add(new Attribute("signatureVersion", cXMLObject
					.getSignatureVersion()));
		}

		if (cXMLObject.getLang() != null) {
			attributes.add(new Attribute("lang", cXMLObject.getLang(),
					Namespace.getNamespace("xml",
							"http://www.w3.org/1999/xhtml")));
		}

		cxmlDocument.setAttributes(attributes);
	}

	private void setContent(Element cxmlDocument, CXML cXMLObject) {

		cxmlDocument.addContent(new Comment(this.getComment()));

		/*
		 * (((Header, (Message | Request)) | Response), ds:Signature?)
		 */
		if (cXMLObject.getResponse() != null) {
			cxmlDocument.addContent(new Response()
					.buildResponseElement(cXMLObject.getResponse()));
		} else {
			cxmlDocument.addContent(new Header().buildHeaderElement(cXMLObject
					.getHeader()));

			if (cXMLObject.getRequest() != null) {
				cxmlDocument.addContent(new Request()
						.buildRequestElement(cXMLObject.getRequest()));
			} else {
				// TODO Message
			}

		}

		// TODO ds:Signature?
	}

	public class Header implements Serializable {
		private FromTo from;

		private FromTo to;

		private Sender sender;

		/**
		 * @return the from
		 */
		public FromTo getFrom() {
			return from;
		}

		/**
		 * @param from
		 *            the from to set
		 */
		public void setFrom(FromTo from) {
			this.from = from;
		}

		/**
		 * @return the sender
		 */
		public Sender getSender() {
			return sender;
		}

		/**
		 * @param sender
		 *            the sender to set
		 */
		public void setSender(Sender sender) {
			this.sender = sender;
		}

		/**
		 * @return the to
		 */
		public FromTo getTo() {
			return to;
		}

		/**
		 * @param to
		 *            the to to set
		 */
		public void setTo(FromTo to) {
			this.to = to;
		}

		/**
		 * <!-- header --> <!ELEMENT Header (From, To, Sender, (Path,
		 * OriginalDocument)?)>
		 *
		 * @param headerObject
		 */
		public Element buildHeaderElement(Header headerObject) {
			Element headerElement = new Element("Header");

			headerObject.setContent(headerElement, headerObject);

			return headerElement;
		}

		private void setContent(Element headerElement, Header headerObject) {
			/*
			 * (From, To, Sender, (Path, OriginalDocument)?)
			 */
			headerElement.addContent(FromTo.buildFromToElement(
					Params.FROM_ELEMENT_NAME, headerObject.getFrom()));

			headerElement.addContent(FromTo.buildFromToElement(
					Params.TO_ELEMENT_NAME, headerObject.getTo()));

			headerElement.addContent(Sender.buildSenderElement(headerObject
					.getSender()));

			// TODO (Path, OriginalDocument)?
		}
	}

	public class Request implements Serializable {
		private Integer id;

		private String deploymentMode;

		private OrderRequest orderRequest;

		/**
		 * @return the deploymentMode
		 */
		public String getDeploymentMode() {
			return deploymentMode;
		}

		/**
		 * @param deploymentMode
		 *            the deploymentMode to set
		 */
		public void setDeploymentMode(String deploymentMode) {
			this.deploymentMode = deploymentMode;
		}

		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the orderRequest
		 */
		public OrderRequest getOrderRequest() {
			return orderRequest;
		}

		/**
		 * @param orderRequest
		 *            the orderRequest to set
		 */
		public void setOrderRequest(OrderRequest orderRequest) {
			this.orderRequest = orderRequest;
		}

		/**
		 * <!ENTITY % cxml.requests "(ProfileRequest | OrderRequest |
		 * MasterAgreementRequest| PunchOutSetupRequest | ProviderSetupRequest |
		 * StatusUpdateRequest | GetPendingRequest | SubscriptionListRequest |
		 * SubscriptionContentRequest | SupplierListRequest |
		 * SupplierDataRequest | CopyRequest | CatalogUploadRequest |
		 * AuthRequest | DataRequest)" > <!ELEMENT Request (%cxml.requests;)>
		 * <!ATTLIST Request deploymentMode (production | test) "production" Id
		 * ID #IMPLIED >
		 *
		 * @param requestObject
		 * @return
		 */
		public Element buildRequestElement(Request requestObject) {
			Element requestElement = new Element("Request");

			this.setAttributes(requestElement, requestObject);

			this.setContent(requestElement, requestObject);

			return requestElement;
		}

		private void setAttributes(Element requestElement, Request requestObject) {
			/*
			 * deploymentMode (production | test) "production" Id ID #IMPLIED
			 */
			// List<Attribute> attributes = new ArrayList<Attribute>();
			List attributes = new ArrayList();

			if (requestObject.getDeploymentMode() != null) {
				attributes.add(new Attribute("deploymentMode", requestObject
						.getDeploymentMode()));
			}

			if (requestObject.getId() != null) {
				attributes.add(new Attribute("Id", String.valueOf(requestObject
						.getId())));
			}

			requestElement.setAttributes(attributes);
		}

		private void setContent(Element requestElement, Request requestObject) {
			/*
			 * (%cxml.requests;)
			 */
			if (requestObject.getOrderRequest() != null) {
				requestElement.addContent(OrderRequest
						.buildOrderRequestElement(requestObject
								.getOrderRequest()));
			}
			// TODO else { .....
		}
	}

	public class Response implements Serializable {
		private Integer id;

		private Status status;

		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the status
		 */
		public Status getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setStatus(Status status) {
			this.status = status;
		}

		/**
		 * <!ENTITY % cxml.responses ",(ProfileResponse | PunchOutSetupResponse |
		 * ProviderSetupResponse | GetPendingResponse | SubscriptionListResponse |
		 * SubscriptionContentResponse | SupplierListResponse |
		 * SupplierDataResponse | AuthResponse | DataResponse)?" > <!ELEMENT
		 * Response (Status %cxml.responses;)> <!ATTLIST Response Id ID #IMPLIED >
		 *
		 * @param responseObject
		 * @return
		 */
		public Element buildResponseElement(Response responseObject) {
			Element responseElement = new Element("Response");

			this.setAttributes(responseElement, responseObject);

			this.setContent(responseElement, responseObject);

			return responseElement;
		}

		private void setAttributes(Element responseElement,
				Response responseObject) {
			/*
			 * Id ID #IMPLIED
			 */
			// List<Attribute> attributes = new ArrayList<Attribute>();
			List attributes = new ArrayList();

			if (responseObject.getId() != null) {
				attributes.add(new Attribute("Id", String
						.valueOf(responseObject.getId())));
			}

			responseElement.setAttributes(attributes);
		}

		private void setContent(Element responseElement, Response responseObject) {
			/*
			 * (Status %cxml.responses;)
			 */
			responseElement.addContent(Status.buildStatusElement(responseObject
					.getStatus()));
			// TODO if () {...} else if () { .....(%cxml.responses;)
		}

	}

}
