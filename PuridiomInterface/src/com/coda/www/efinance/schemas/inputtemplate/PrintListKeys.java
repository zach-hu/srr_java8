/**
 * PrintListKeys.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * Contains key information for input
 *                 template masters that are returned by a
 *             ListRequest.
 */
public class PrintListKeys  extends com.coda.www.efinance.schemas.inputtemplate.ItmReqKeys  implements java.io.Serializable {
    private java.util.Calendar lastModifiedFrom;

    /* The end date of a
     *                                 date range for selecting input template
     * masters that were last modified within
     *                                 this date range. */
    private java.util.Calendar lastModifiedTo;

    public PrintListKeys() {
    }

    public PrintListKeys(
           int maxKeys,
           com.coda.www.efinance.schemas.inputtemplate.ItmReqKey key,
           java.util.Calendar lastModifiedFrom,
           java.util.Calendar lastModifiedTo) {
        super(
            maxKeys,
            key);
        this.lastModifiedFrom = lastModifiedFrom;
        this.lastModifiedTo = lastModifiedTo;
    }


    /**
     * Gets the lastModifiedFrom value for this PrintListKeys.
     * 
     * @return lastModifiedFrom
     */
    public java.util.Calendar getLastModifiedFrom() {
        return lastModifiedFrom;
    }


    /**
     * Sets the lastModifiedFrom value for this PrintListKeys.
     * 
     * @param lastModifiedFrom
     */
    public void setLastModifiedFrom(java.util.Calendar lastModifiedFrom) {
        this.lastModifiedFrom = lastModifiedFrom;
    }


    /**
     * Gets the lastModifiedTo value for this PrintListKeys.
     * 
     * @return lastModifiedTo   * The end date of a
     *                                 date range for selecting input template
     * masters that were last modified within
     *                                 this date range.
     */
    public java.util.Calendar getLastModifiedTo() {
        return lastModifiedTo;
    }


    /**
     * Sets the lastModifiedTo value for this PrintListKeys.
     * 
     * @param lastModifiedTo   * The end date of a
     *                                 date range for selecting input template
     * masters that were last modified within
     *                                 this date range.
     */
    public void setLastModifiedTo(java.util.Calendar lastModifiedTo) {
        this.lastModifiedTo = lastModifiedTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrintListKeys)) return false;
        PrintListKeys other = (PrintListKeys) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lastModifiedFrom==null && other.getLastModifiedFrom()==null) || 
             (this.lastModifiedFrom!=null &&
              this.lastModifiedFrom.equals(other.getLastModifiedFrom()))) &&
            ((this.lastModifiedTo==null && other.getLastModifiedTo()==null) || 
             (this.lastModifiedTo!=null &&
              this.lastModifiedTo.equals(other.getLastModifiedTo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getLastModifiedFrom() != null) {
            _hashCode += getLastModifiedFrom().hashCode();
        }
        if (getLastModifiedTo() != null) {
            _hashCode += getLastModifiedTo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrintListKeys.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "PrintListKeys"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LastModifiedFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LastModifiedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
