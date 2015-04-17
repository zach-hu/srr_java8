/**
 * DefinedLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This element holds details of one
 *                 defined line.
 */
public class DefinedLine  implements java.io.Serializable {
    private short lineNumber;

    /* Specifies that the current
     *                         document line is to be included in the document
     * and posted to its account, even when it has zero
     *                         value in all currencies. */
    private boolean postIfZeroValue;

    /* The
     *                         index for the next defined line
     *                     item. */
    private short nextDefLineIndex;

    /* The original line number of
     *                         this line in the input template. This number
     * is
     *                         used by the system when lines are added to,
     * or
     *                         rearranged on, the input
     *                     template. */
    private short origLineNumber;

    /* This
     *                         element holds details of the fields on this
     * defined line. */
    private com.coda.www.efinance.schemas.inputtemplate.LineItem[] items;

    public DefinedLine() {
    }

    public DefinedLine(
           short lineNumber,
           boolean postIfZeroValue,
           short nextDefLineIndex,
           short origLineNumber,
           com.coda.www.efinance.schemas.inputtemplate.LineItem[] items) {
           this.lineNumber = lineNumber;
           this.postIfZeroValue = postIfZeroValue;
           this.nextDefLineIndex = nextDefLineIndex;
           this.origLineNumber = origLineNumber;
           this.items = items;
    }


    /**
     * Gets the lineNumber value for this DefinedLine.
     * 
     * @return lineNumber
     */
    public short getLineNumber() {
        return lineNumber;
    }


    /**
     * Sets the lineNumber value for this DefinedLine.
     * 
     * @param lineNumber
     */
    public void setLineNumber(short lineNumber) {
        this.lineNumber = lineNumber;
    }


    /**
     * Gets the postIfZeroValue value for this DefinedLine.
     * 
     * @return postIfZeroValue   * Specifies that the current
     *                         document line is to be included in the document
     * and posted to its account, even when it has zero
     *                         value in all currencies.
     */
    public boolean isPostIfZeroValue() {
        return postIfZeroValue;
    }


    /**
     * Sets the postIfZeroValue value for this DefinedLine.
     * 
     * @param postIfZeroValue   * Specifies that the current
     *                         document line is to be included in the document
     * and posted to its account, even when it has zero
     *                         value in all currencies.
     */
    public void setPostIfZeroValue(boolean postIfZeroValue) {
        this.postIfZeroValue = postIfZeroValue;
    }


    /**
     * Gets the nextDefLineIndex value for this DefinedLine.
     * 
     * @return nextDefLineIndex   * The
     *                         index for the next defined line
     *                     item.
     */
    public short getNextDefLineIndex() {
        return nextDefLineIndex;
    }


    /**
     * Sets the nextDefLineIndex value for this DefinedLine.
     * 
     * @param nextDefLineIndex   * The
     *                         index for the next defined line
     *                     item.
     */
    public void setNextDefLineIndex(short nextDefLineIndex) {
        this.nextDefLineIndex = nextDefLineIndex;
    }


    /**
     * Gets the origLineNumber value for this DefinedLine.
     * 
     * @return origLineNumber   * The original line number of
     *                         this line in the input template. This number
     * is
     *                         used by the system when lines are added to,
     * or
     *                         rearranged on, the input
     *                     template.
     */
    public short getOrigLineNumber() {
        return origLineNumber;
    }


    /**
     * Sets the origLineNumber value for this DefinedLine.
     * 
     * @param origLineNumber   * The original line number of
     *                         this line in the input template. This number
     * is
     *                         used by the system when lines are added to,
     * or
     *                         rearranged on, the input
     *                     template.
     */
    public void setOrigLineNumber(short origLineNumber) {
        this.origLineNumber = origLineNumber;
    }


    /**
     * Gets the items value for this DefinedLine.
     * 
     * @return items   * This
     *                         element holds details of the fields on this
     * defined line.
     */
    public com.coda.www.efinance.schemas.inputtemplate.LineItem[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this DefinedLine.
     * 
     * @param items   * This
     *                         element holds details of the fields on this
     * defined line.
     */
    public void setItems(com.coda.www.efinance.schemas.inputtemplate.LineItem[] items) {
        this.items = items;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefinedLine)) return false;
        DefinedLine other = (DefinedLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.lineNumber == other.getLineNumber() &&
            this.postIfZeroValue == other.isPostIfZeroValue() &&
            this.nextDefLineIndex == other.getNextDefLineIndex() &&
            this.origLineNumber == other.getOrigLineNumber() &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getLineNumber();
        _hashCode += (isPostIfZeroValue() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getNextDefLineIndex();
        _hashCode += getOrigLineNumber();
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefinedLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postIfZeroValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "PostIfZeroValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextDefLineIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextDefLineIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origLineNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "OrigLineNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
