package com.tsa.puridiom.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PuridiomEntityResolver implements EntityResolver {
	public static PuridiomEntityResolver getInstance() {
		return instance;
	}
	
	static PuridiomEntityResolver instance = new PuridiomEntityResolver();
	
	private PuridiomEntityResolver() {
		super();
	}
	
	private static String cXMLDTDURL = "http://xml.cXML.org/schemas/cXML/1.1.010/cXML.dtd";
	
	public InputSource resolveEntity(String publicID, String systemID) throws SAXException {
		InputSource result = null;
		
		if (cXMLDTDURL.equals(publicID) || cXMLDTDURL.equals(systemID)) {
			result = new InputSource(this.getClass().getResourceAsStream("/cXML.dtd"));
		}
		
		return result;
	}
	
	public static void main(String [] args) throws Exception {
		PuridiomEntityResolver resolver = getInstance();
		
		InputSource source = resolver.resolveEntity(cXMLDTDURL, null);
		
		System.out.println(source.getClass().getResource("/cXML.dtd"));
		
		if (source != null) System.out.println("source is not null");
		
		resolver.resolveEntity(null, null);
	}
}
