/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * @author Johnny Zapana
 *
 */
public class Description implements Serializable {
	private String lang;

	// private Map<String, String> content;
	private Map content = new HashMap();

	private int shortNameIndex = 0;

	private int contentIndex = 0;

	public Description() {
	}

	public Description(String lang, Map content) {
		this.lang = lang;
		this.content = content;
	}

	/**
	 * @return the content
	 */
	// public Map<String, String> getContent() {
	public Map getContent() {
		return content;
	}

	/**
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public Map putContent(String key, String content) {
		if (key.equals(Params.SHORT_NAME_ELEMENT)) {
			this.content.put(key + "_" + String.valueOf(++this.shortNameIndex),
					content);
		} else {
			this.content.put(key + "_" + String.valueOf(++this.contentIndex),
					content);
		}
		return this.content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	// public void setContent(Map<String, String> content) {
	public void setContent(Map content) {
		this.content = content;
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
	 * <!ELEMENT Description ( #PCDATA | ShortName )* > <!-- mixed: string and
	 * ShortName --> <!ATTLIST Description xml:lang %xmlLangCode; #REQUIRED >
	 *
	 * @param descriptionElement
	 * @param descriptionObject
	 */
	public static Element buildDescriptionElement(Description descriptionObject) {
		Element descriptionElement = new Element("Description");

		descriptionObject.setAttributes(descriptionElement, descriptionObject);

		descriptionObject.setContent(descriptionElement, descriptionObject);

		return descriptionElement;
	}

	private void setAttributes(Element descriptionElement,
			Description descriptionObject) {
		/*
		 * xml:lang %xmlLangCode; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("lang", descriptionObject.getLang(),
				Namespace.getNamespace("xml", "http://www.w3.org/1999/xhtml")));

		descriptionElement.setAttributes(attributes);
	}

	private void setContent(Element descriptionElement,
			Description descriptionObject) {
		/*
		 * ( #PCDATA | ShortName )*
		 */
		String xmlLabel;

		for (Iterator iter = descriptionObject.getContent().keySet().iterator(); iter
				.hasNext();) {
			xmlLabel = (String) iter.next();

			if (xmlLabel.indexOf(Params.SHORT_NAME_ELEMENT) > -1) {
				descriptionElement.addContent(new Element(
						Params.SHORT_NAME_ELEMENT)
						.setText((String) descriptionObject.getContent().get(
								xmlLabel)));
			} else {
				descriptionElement.addContent(new CDATA((String) descriptionObject
						.getContent().get(xmlLabel)));
			}
		}
	}
}
