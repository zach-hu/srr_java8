/**
 * Transaction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds a financial
 *             document.
 */
public class Transaction  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.transaction.Header header;

    /* This
     *                         element holds the document
     *                     lines. */
    private com.coda.www.efinance.schemas.transaction.Line[] lines;

    public Transaction() {
    }

    public Transaction(
           com.coda.www.efinance.schemas.transaction.Header header,
           com.coda.www.efinance.schemas.transaction.Line[] lines) {
           this.header = header;
           this.lines = lines;
    }


    /**
     * Gets the header value for this Transaction.
     * 
     * @return header
     */
    public com.coda.www.efinance.schemas.transaction.Header getHeader() {
        return header;
    }


    /**
     * Sets the header value for this Transaction.
     * 
     * @param header
     */
    public void setHeader(com.coda.www.efinance.schemas.transaction.Header header) {
        this.header = header;
    }


    /**
     * Gets the lines value for this Transaction.
     * 
     * @return lines   * This
     *                         element holds the document
     *                     lines.
     */
    public com.coda.www.efinance.schemas.transaction.Line[] getLines() {
        return lines;
    }


    /**
     * Sets the lines value for this Transaction.
     * 
     * @param lines   * This
     *                         element holds the document
     *                     lines.
     */
    public void setLines(com.coda.www.efinance.schemas.transaction.Line[] lines) {
        this.lines = lines;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Transaction)) return false;
        Transaction other = (Transaction) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.lines==null && other.getLines()==null) || 
             (this.lines!=null &&
              java.util.Arrays.equals(this.lines, other.getLines())));
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
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLines(), i);
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
        new org.apache.axis.description.TypeDesc(Transaction.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Header"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Lines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line"));
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
