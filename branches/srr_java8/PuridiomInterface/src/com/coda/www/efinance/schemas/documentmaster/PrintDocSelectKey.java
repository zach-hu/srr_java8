/**
 * PrintDocSelectKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 the document masters you are
 *             printing.
 */
public class PrintDocSelectKey  extends com.coda.www.efinance.schemas.documentmaster.DocSelectKey  implements java.io.Serializable {
    private java.util.Calendar lastModifiedFrom;

    /* Specifies you want to
     *                                 search for documents which were modified
     * on or before this
     *                             date. */
    private java.util.Calendar lastModifiedTo;

    public PrintDocSelectKey() {
    }

    public PrintDocSelectKey(
           int maxKeys,
           com.coda.www.efinance.schemas.common.Key key,
           java.lang.String shortName,
           java.lang.Boolean onlyIntercompany,
           java.lang.Boolean onlyCancelling,
           java.lang.Boolean enableMasterSecurityFilter,
           java.lang.Boolean excludeReservedDocs,
           java.lang.Boolean onlySalesInvoiceReservedDocs,
           java.util.Calendar lastModifiedFrom,
           java.util.Calendar lastModifiedTo) {
        super(
            maxKeys,
            key,
            shortName,
            onlyIntercompany,
            onlyCancelling,
            enableMasterSecurityFilter,
            excludeReservedDocs,
            onlySalesInvoiceReservedDocs);
        this.lastModifiedFrom = lastModifiedFrom;
        this.lastModifiedTo = lastModifiedTo;
    }


    /**
     * Gets the lastModifiedFrom value for this PrintDocSelectKey.
     * 
     * @return lastModifiedFrom
     */
    public java.util.Calendar getLastModifiedFrom() {
        return lastModifiedFrom;
    }


    /**
     * Sets the lastModifiedFrom value for this PrintDocSelectKey.
     * 
     * @param lastModifiedFrom
     */
    public void setLastModifiedFrom(java.util.Calendar lastModifiedFrom) {
        this.lastModifiedFrom = lastModifiedFrom;
    }


    /**
     * Gets the lastModifiedTo value for this PrintDocSelectKey.
     * 
     * @return lastModifiedTo   * Specifies you want to
     *                                 search for documents which were modified
     * on or before this
     *                             date.
     */
    public java.util.Calendar getLastModifiedTo() {
        return lastModifiedTo;
    }


    /**
     * Sets the lastModifiedTo value for this PrintDocSelectKey.
     * 
     * @param lastModifiedTo   * Specifies you want to
     *                                 search for documents which were modified
     * on or before this
     *                             date.
     */
    public void setLastModifiedTo(java.util.Calendar lastModifiedTo) {
        this.lastModifiedTo = lastModifiedTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrintDocSelectKey)) return false;
        PrintDocSelectKey other = (PrintDocSelectKey) obj;
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
        new org.apache.axis.description.TypeDesc(PrintDocSelectKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PrintDocSelectKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "LastModifiedFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "LastModifiedTo"));
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
