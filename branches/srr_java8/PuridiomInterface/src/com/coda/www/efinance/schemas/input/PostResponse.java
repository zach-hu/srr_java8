/**
 * PostResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.input;

public class PostResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains the extended
     *                                 key information for the transaction
     * you
     *                             posted. */
    private com.coda.www.efinance.schemas.transaction.TxnKey key;

    /* The document number
     *                                 you may have supplied (applies to
     * Batch
     *                             Load). */
    private java.lang.String suppliedNumber;

    private java.lang.String postto;  // attribute

    public PostResponse() {
    }

    public PostResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String postto,
           com.coda.www.efinance.schemas.transaction.TxnKey key,
           java.lang.String suppliedNumber) {
        super();
        this.postto = postto;
        this.key = key;
        this.suppliedNumber = suppliedNumber;
    }


    /**
     * Gets the key value for this PostResponse.
     *
     * @return key   * Contains the extended
     *                                 key information for the transaction
     * you
     *                             posted.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this PostResponse.
     *
     * @param key   * Contains the extended
     *                                 key information for the transaction
     * you
     *                             posted.
     */
    public void setKey(com.coda.www.efinance.schemas.transaction.TxnKey key) {
        this.key = key;
    }


    /**
     * Gets the suppliedNumber value for this PostResponse.
     *
     * @return suppliedNumber   * The document number
     *                                 you may have supplied (applies to
     * Batch
     *                             Load).
     */
    public java.lang.String getSuppliedNumber() {
        return suppliedNumber;
    }


    /**
     * Sets the suppliedNumber value for this PostResponse.
     *
     * @param suppliedNumber   * The document number
     *                                 you may have supplied (applies to
     * Batch
     *                             Load).
     */
    public void setSuppliedNumber(java.lang.String suppliedNumber) {
        this.suppliedNumber = suppliedNumber;
    }


    /**
     * Gets the postto value for this PostResponse.
     *
     * @return postto
     */
    public java.lang.String getPostto() {
        return postto;
    }


    /**
     * Sets the postto value for this PostResponse.
     *
     * @param postto
     */
    public void setPostto(java.lang.String postto) {
        this.postto = postto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostResponse)) return false;
        PostResponse other = (PostResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.key==null && other.getKey()==null) ||
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.suppliedNumber==null && other.getSuppliedNumber()==null) ||
             (this.suppliedNumber!=null &&
              this.suppliedNumber.equals(other.getSuppliedNumber()))) &&
            ((this.postto==null && other.getPostto()==null) ||
             (this.postto!=null &&
              this.postto.equals(other.getPostto())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getSuppliedNumber() != null) {
            _hashCode += getSuppliedNumber().hashCode();
        }
        if (getPostto() != null) {
            _hashCode += getPostto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input", "PostResponse"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("postto");
        attrField.setXmlName(new javax.xml.namespace.QName("", "postto"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocPost"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suppliedNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input", "SuppliedNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
