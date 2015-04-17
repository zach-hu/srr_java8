/*
 * XmlFile.java
 *
 * Created on January 30, 2002, 3:36 PM
 */

package com.tsagate.foundation.utility;

/**
 *
 * @author  renzo
 * @version
 */

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.*;
import java.util.*;

public class XmlFile
{
    private String  errorString = "";
    private String  is_xmlPath = "";
    private Document document;
    private Element root;
    private File xmlFile;
    private String fileName = "";

    public String sClassName = "XmlFile";
    /** Creates new XmlFile */
    public XmlFile()
    {

    }

    public XmlFile(String file)
    {
        fileName = file;
        document = getXMLFile(file);
        if (document == null) {
            Log.error(this, "ERROR: " + file + " does not exist.");
        } else {
            root = document.getRootElement();
        }
    }
    
    public XmlFile(String file, String organizationId)
    {
        fileName = file;
        document = getXMLFile(file, organizationId);
        if (document == null) {
            Log.error(this, "ERROR: " + file + " does not exist.");
        } else {
            root = document.getRootElement();
        }
    }

    /**
     *  getErrorString Method
     *  @return String
     */
    public String getErrorString()
    {
        return errorString;
    }

    /**
     *  setErrorString Method
     *  @param newValue String new value of errorString variable
     */
    public void setErrorString(String newValue)
    {
        errorString = newValue;
    }

    /**
     *  getXMLFile Method
     *  @param filename String name of file
     *  @return Document
     */
    public Document getXMLFile(String filename)
    {
        return this.getXMLFile(filename, "");
    }
    
    /**
     *  getXMLFile Method
     *  @param filename String name of file
     *  @return Document
     */
    public Document getXMLFile(String filename, String organizationId)
    {
        //create a saxBuilder to read the xmlFile
        //DOMBuilder builder = new DOMBuilder(false);
        //system.out.println("xmlfile filename: " + filename);
        //File file = new File(filename);
        File file = Utility.getOidFile(filename, organizationId);

        this.xmlFile = file;

        Document jdomDoc = null;
        if (file.exists())
        {
            SAXBuilder builder = new SAXBuilder();
            try
            {
                //DOMBuilder builder = new DOMBuilder(false);
                jdomDoc = builder.build(file);
            }
            catch (JDOMException e)
            {
                Log.error(this, "XmlFile.getXmlFile() -- JDOMException: " + e.toString() );
            }
        }
        else
        {
            //system.out.println("file: " + filename + " does not exists." );
            //system.out.println("" + file.getAbsolutePath());
//			LogFile.write("file: " + filename + " does not exists." );
//        	LogFile.write("" + file.getAbsolutePath());
        }
    return jdomDoc;
    }

    /**
     *  getDomFile Method
     *  @param file String name of file
     *  @return Document
     */
    public Document getDomFile(String file)
    {
        Document xmlFile = null;
        return xmlFile;
    }

    public void setElement(Element element, java.lang.String text)
    {
        element.setText(text);
    }
    /**
     * return an element right down the root
     * will return an Element if exists, or a new one
     *
     */
    public Element getRootChild(java.lang.String elementName)
    {
         Element element = root.getChild(elementName);
         if (element != null)
         {
             return element;
         }
         else
         {
             Log.warn(this, "Element: " + elementName + " was not found");
             return new Element("element");
         }
    }
    /*
     * looks for the last element of the array
     * returns it as an JDom.Element or null if it didn't find it
     */

    public Element findElement(String as_name[])
    {
        Element element = root;

        for(int i= 0; i < as_name.length; i++)
        {

            element = element.getChild(as_name[i]);
        }
       return element;
    }
    /*
     *  returns the text of the last element of the array
     */
    public String getText(String as_elementName[])
    {
        return getText(findElement(as_elementName));
    }

    /*
     *  Returns a list of all the childs of the last element in the array
     */
    public List getElementList(String as_elementName[])
    {
        Element e = findElement(as_elementName);
        List	list = new ArrayList();

        if (e != null)
        {
            list = e.getChildren();
        }
        return list;
    }


    /*
     * returns the text of the especified Element
     */
    public String getText(Element aE)
    {
        return aE.getTextTrim() ;
    }

    public String[] getAttributes(java.lang.String as_elementName)
    {
        int i = 0;
        Element element = root.getChild(as_elementName);
        List attributes = element.getAttributes();
        Iterator it = attributes.iterator();
        String temp[] = new String[attributes.size()];
        while(it.hasNext())
        {
            temp[i] = ((Attribute)it.next()).getValue();

            i++;
        }
        return temp;
    }

    public Element getChildElement(Element as_parent, String as_child)
    {
        Element element = as_parent.getChild(as_child);
        if ( element == null ) {
            return new Element("element");
        }

        return element;
    }

    public void output(FileOutputStream fos) throws IOException
    {
        XMLOutputter outputter = new XMLOutputter("\t", true);
        outputter.setExpandEmptyElements(true);

        outputter.output(document, fos);
    }
    public void output()
    {
        try
        {
            if(this.xmlFile.isFile())
            {
                FileOutputStream fos = new FileOutputStream(this.xmlFile);
                this.output(fos);
            }
            System.out.println("file: " + this.xmlFile.getPath());
        }
        catch(IOException ie)
        {
            Log.error(this, ie.toString());
            Log.error(this, "Error outputting file: " + this.xmlFile.getName());
        }
    }

    public String getText(String as_childName, Element eName)
    {
          return eName.getChildText(as_childName);
    }

    /**
     * Method getRootChildren
     * Returns a List of the Root's Children.
     * @param string
     * @return List
     */
    public List getRootChildren(String string)
    {
         List list = root.getChildren(string);

         return list;
    }
    public Element getRoot()
    {
        return root;
    }
    public void setRoot(Element root)
    {
        this.root = root;
    }
}
