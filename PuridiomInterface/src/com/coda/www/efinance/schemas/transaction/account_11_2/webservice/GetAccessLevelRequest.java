/**
 * GetAccessLevelRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class GetAccessLevelRequest  implements java.io.Serializable {
    /* This element contains details
     *                             of access level parameters. */
    private com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams;

    public GetAccessLevelRequest() {
    }

    public GetAccessLevelRequest(
           com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams) {
           this.accessLevelParams = accessLevelParams;
    }


    /**
     * Gets the accessLevelParams value for this GetAccessLevelRequest.
     * 
     * @return accessLevelParams   * This element contains details
     *                             of access level parameters.
     */
    public com.coda.www.efinance.schemas.transaction.AccessLevelParams getAccessLevelParams() {
        return accessLevelParams;
    }


    /**
     * Sets the accessLevelParams value for this GetAccessLevelRequest.
     * 
     * @param accessLevelParams   * This element contains details
     *                             of access level parameters.
     */
    public void setAccessLevelParams(com.coda.www.efinance.schemas.transaction.AccessLevelParams accessLevelParams) {
        this.accessLevelParams = accessLevelParams;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAccessLevelRequest)) return false;
        GetAccessLevelRequest other = (GetAccessLevelRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accessLevelParams==null && other.getAccessLevelParams()==null) || 
             (this.accessLevelParams!=null &&
              this.accessLevelParams.equals(other.getAccessLevelParams())));
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
        if (getAccessLevelParams() != null) {
            _hashCode += getAccessLevelParams().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAccessLevelRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetAccessLevelRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessLevelParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "AccessLevelParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevelParams"));
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
