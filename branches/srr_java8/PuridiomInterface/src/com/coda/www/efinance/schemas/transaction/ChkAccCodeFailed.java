/**
 * ChkAccCodeFailed.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * Holds details of why the account code
 *                 failed the validation. Applies only when the account
 * code is invalid.
 */
public class ChkAccCodeFailed  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.common.Reason reason;

    /* The element level of the
     *                         leftmost element which is not
     *                     valid. */
    private short level;

    /* The starting character
     *                         position in the account code of the leftmost
     * element which is not valid. */
    private int startIndex;

    /* The
     *                         ending character position in the account code
     * of
     *                         the leftmost element which is not
     *                     valid. */
    private int endIndex;

    public ChkAccCodeFailed() {
    }

    public ChkAccCodeFailed(
           com.coda.www.efinance.schemas.common.Reason reason,
           short level,
           int startIndex,
           int endIndex) {
           this.reason = reason;
           this.level = level;
           this.startIndex = startIndex;
           this.endIndex = endIndex;
    }


    /**
     * Gets the reason value for this ChkAccCodeFailed.
     * 
     * @return reason
     */
    public com.coda.www.efinance.schemas.common.Reason getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this ChkAccCodeFailed.
     * 
     * @param reason
     */
    public void setReason(com.coda.www.efinance.schemas.common.Reason reason) {
        this.reason = reason;
    }


    /**
     * Gets the level value for this ChkAccCodeFailed.
     * 
     * @return level   * The element level of the
     *                         leftmost element which is not
     *                     valid.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ChkAccCodeFailed.
     * 
     * @param level   * The element level of the
     *                         leftmost element which is not
     *                     valid.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the startIndex value for this ChkAccCodeFailed.
     * 
     * @return startIndex   * The starting character
     *                         position in the account code of the leftmost
     * element which is not valid.
     */
    public int getStartIndex() {
        return startIndex;
    }


    /**
     * Sets the startIndex value for this ChkAccCodeFailed.
     * 
     * @param startIndex   * The starting character
     *                         position in the account code of the leftmost
     * element which is not valid.
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }


    /**
     * Gets the endIndex value for this ChkAccCodeFailed.
     * 
     * @return endIndex   * The
     *                         ending character position in the account code
     * of
     *                         the leftmost element which is not
     *                     valid.
     */
    public int getEndIndex() {
        return endIndex;
    }


    /**
     * Sets the endIndex value for this ChkAccCodeFailed.
     * 
     * @param endIndex   * The
     *                         ending character position in the account code
     * of
     *                         the leftmost element which is not
     *                     valid.
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChkAccCodeFailed)) return false;
        ChkAccCodeFailed other = (ChkAccCodeFailed) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            this.level == other.getLevel() &&
            this.startIndex == other.getStartIndex() &&
            this.endIndex == other.getEndIndex();
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
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        _hashCode += getLevel();
        _hashCode += getStartIndex();
        _hashCode += getEndIndex();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChkAccCodeFailed.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeFailed"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "StartIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "EndIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
