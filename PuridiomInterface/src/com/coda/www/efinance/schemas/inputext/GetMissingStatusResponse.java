/**
 * GetMissingStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class GetMissingStatusResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The code of the
     *                                 company in which the document
     *                             exists. */
    private java.lang.String cmpCode;

    /* The code of the
     *                                 document master. */
    private java.lang.String docCode;

    /* The document
     *                             number. */
    private java.lang.String docNum;

    /* Indicates whether the
     *                                 document is a missing document
     *                             number. */
    private boolean isMissing;

    public GetMissingStatusResponse() {
    }

    public GetMissingStatusResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String cmpCode,
           java.lang.String docCode,
           java.lang.String docNum,
           boolean isMissing) {
        super();
        this.cmpCode = cmpCode;
        this.docCode = docCode;
        this.docNum = docNum;
        this.isMissing = isMissing;
    }


    /**
     * Gets the cmpCode value for this GetMissingStatusResponse.
     *
     * @return cmpCode   * The code of the
     *                                 company in which the document
     *                             exists.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this GetMissingStatusResponse.
     *
     * @param cmpCode   * The code of the
     *                                 company in which the document
     *                             exists.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the docCode value for this GetMissingStatusResponse.
     *
     * @return docCode   * The code of the
     *                                 document master.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this GetMissingStatusResponse.
     *
     * @param docCode   * The code of the
     *                                 document master.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the docNum value for this GetMissingStatusResponse.
     *
     * @return docNum   * The document
     *                             number.
     */
    public java.lang.String getDocNum() {
        return docNum;
    }


    /**
     * Sets the docNum value for this GetMissingStatusResponse.
     *
     * @param docNum   * The document
     *                             number.
     */
    public void setDocNum(java.lang.String docNum) {
        this.docNum = docNum;
    }


    /**
     * Gets the isMissing value for this GetMissingStatusResponse.
     *
     * @return isMissing   * Indicates whether the
     *                                 document is a missing document
     *                             number.
     */
    public boolean isIsMissing() {
        return isMissing;
    }


    /**
     * Sets the isMissing value for this GetMissingStatusResponse.
     *
     * @param isMissing   * Indicates whether the
     *                                 document is a missing document
     *                             number.
     */
    public void setIsMissing(boolean isMissing) {
        this.isMissing = isMissing;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMissingStatusResponse)) return false;
        GetMissingStatusResponse other = (GetMissingStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.cmpCode==null && other.getCmpCode()==null) ||
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.docCode==null && other.getDocCode()==null) ||
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode()))) &&
            ((this.docNum==null && other.getDocNum()==null) ||
             (this.docNum!=null &&
              this.docNum.equals(other.getDocNum()))) &&
            this.isMissing == other.isIsMissing();
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        if (getDocNum() != null) {
            _hashCode += getDocNum().hashCode();
        }
        _hashCode += (isIsMissing() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMissingStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetMissingStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isMissing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IsMissing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
