/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * @author Johnny Zapana
 *
 */
public class Comments implements Serializable {

	private String lang;

	private List content = new ArrayList();

	/**
	 * @return the content
	 */
	public List getContent() {
		return content;
	}

	/**
	 *
	 * @param content
	 * @return
	 */
	public List addContent(Object content) {
		this.content.add(content);

		return this.content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(List content) {
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
	 * <!ELEMENT Comments ( #PCDATA | Attachment )* > <!-- mixed: string and
	 * opt. Attachment list --> <!ATTLIST Comments xml:lang %xmlLangCode;
	 * #IMPLIED >
	 *
	 * @param commentsObject
	 * @return
	 */
	public static Element buildCommentsElement(Comments commentsObject) {
		Element commentsElement = new Element("Comments");

		commentsObject.setAttributes(commentsElement, commentsObject);

		commentsObject.setContent(commentsElement, commentsObject);

		return commentsElement;
	}

	private void setAttributes(Element commentsElement, Comments commentsObject) {
		/*
		 * xml:lang %xmlLangCode; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (commentsObject.getLang() != null) {
			attributes.add(new Attribute("lang", commentsObject.getLang(),
					Namespace.getNamespace("xml",
							"http://www.w3.org/1999/xhtml")));
		}

		commentsElement.setAttributes(attributes);
	}

	private void setContent(Element commentsElement, Comments commentsObject) {
		/*
		 * ( #PCDATA | Attachment )*
		 */

		Object content;
		Element attachmentElement;

		for (Iterator iterator = commentsObject.getContent().iterator(); iterator
				.hasNext();) {
			content = iterator.next();

			if (content instanceof URL) {
				attachmentElement = new Element(Params.ATTACHMENT_ELEMENT);

				attachmentElement
						.addContent(URL.buildURlElement((URL) content));

				commentsElement.addContent(attachmentElement);

			} else {
				commentsElement.addContent(content.toString());
			}
		}
	}

}
