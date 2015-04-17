/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Path implements Serializable {
	// private List<Node> nodes;
	private List nodes = new ArrayList();

	/**
	 * @return the nodes
	 */
	// public List<Node> getNodes() {
	public List getNodes() {
		return nodes;
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	public List addNode(Node node) {
		this.nodes.add(node);

		return this.nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	// public void setNodes(List<Node> nodes) {
	public void setNodes(List nodes) {
		this.nodes = nodes;
	}

	/**
	 * <!ELEMENT Path (Node+)>
	 */
	public static Element buildPathElement(Path pathObject) {
		Element pathElement = new Element("Path");

		pathObject.setContent(pathElement, pathObject);

		return pathElement;
	}

	private void setContent(Element pathElement, Path pathObject) {
		/*
		 * (Node+)
		 */
		Iterator iterator = pathObject.getNodes().iterator();

		do {
			pathElement.addContent(Node
					.buildNodeElement((Node) iterator.next()));
		} while (iterator.hasNext());
	}

}
