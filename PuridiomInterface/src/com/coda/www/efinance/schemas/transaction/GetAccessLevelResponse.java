/**
 * GetAccessLevelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class GetAccessLevelResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* This element contains
     *                                 details of access level
     *                             parameters. */
    private com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams;

    /* The type of access
     *                             available. */
    private java.lang.String accessLevel;

    public GetAccessLevelResponse() {
    }

    public GetAccessLevelResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams,
           java.lang.String accessLevel) {
        super();
        this.accessLevelParams = accessLevelParams;
        this.accessLevel = accessLevel;
    }


    /**
     * Gets the accessLevelParams value for this GetAccessLevelResponse.
     *
     * @return accessLevelParams   * This element contains
     *                                 details of access level
     *                             parameters.
     */
    public com.coda.www.efinance.schemas.transaction.AccessLevelParams getAccessLevelParams() {
        return accessLevelParams;
    }


    /**
     * Sets the accessLevelParams value for this GetAccessLevelResponse.
     *
     * @param accessLevelParams   * This element contains
     *                                 details of access level
     *                             parameters.
     */
    public void setAccessLevelParams(com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams) {
        this.accessLevelParams = accessLevelParams;
    }


    /**
     * Gets the accessLevel value for this GetAccessLevelResponse.
     *
     * @return accessLevel   * The type of access
     *                             available.
     */
    public java.lang.String getAccessLevel() {
        return accessLevel;
    }


    /**
     * Sets the accessLevel value for this GetAccessLevelResponse.
     *
     * @param accessLevel   * The type of access
     *                             available.
     */
    public void setAccessLevel(java.lang.String accessLevel) {
        this.accessLevel = accessLevel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAccessLevelResponse)) return false;
        GetAccessLevelResponse other = (GetAccessLevelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.accessLevelParams==null && other.getAccessLevelParams()==null) ||
             (this.accessLevelParams!=null &&
              this.accessLevelParams.equals(other.getAccessLevelParams()))) &&
            ((this.accessLevel==null && other.getAccessLevel()==null) ||
             (this.accessLevel!=null &&
              this.accessLevel.equals(other.getAccessLevel())));
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
        if (getAccessLevelParams() != null) {
            _hashCode += getAccessLevelParams().hashCode();
        }
        if (getAccessLevel() != null) {
            _hashCode += getAccessLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAccessLevelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetAccessLevelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessLevelParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevelParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevelParams"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
