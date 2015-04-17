/**
 * CheckCodeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class CheckCodeResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* This element holds
     *                                 the result of the
     *                             validation. */
    private com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer answer;

    public CheckCodeResponse() {
    }

    public CheckCodeResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer answer) {
        super();
        this.answer = answer;
    }


    /**
     * Gets the answer value for this CheckCodeResponse.
     *
     * @return answer   * This element holds
     *                                 the result of the
     *                             validation.
     */
    public com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer getAnswer() {
        return answer;
    }


    /**
     * Sets the answer value for this CheckCodeResponse.
     *
     * @param answer   * This element holds
     *                                 the result of the
     *                             validation.
     */
    public void setAnswer(com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer answer) {
        this.answer = answer;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckCodeResponse)) return false;
        CheckCodeResponse other = (CheckCodeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.answer==null && other.getAnswer()==null) ||
             (this.answer!=null &&
              this.answer.equals(other.getAnswer())));
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
        if (getAnswer() != null) {
            _hashCode += getAnswer().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckCodeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CheckCodeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Answer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeAnswer"));
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
