/**
 * Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.common;

public class Response  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.common.Reason reason;

    private com.coda.www.efinance.schemas.common.TypeResponseStatus status;  // attribute

    private java.lang.String transactioncoordinator;  // attribute

    public Response() {
    }

    public Response(
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator) {
           this.reason = reason;
           this.status = status;
           this.transactioncoordinator = transactioncoordinator;
    }


    /**
     * Gets the reason value for this Response.
     * 
     * @return reason
     */
    public com.coda.www.efinance.schemas.common.Reason getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this Response.
     * 
     * @param reason
     */
    public void setReason(com.coda.www.efinance.schemas.common.Reason reason) {
        this.reason = reason;
    }


    /**
     * Gets the status value for this Response.
     * 
     * @return status
     */
    public com.coda.www.efinance.schemas.common.TypeResponseStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Response.
     * 
     * @param status
     */
    public void setStatus(com.coda.www.efinance.schemas.common.TypeResponseStatus status) {
        this.status = status;
    }


    /**
     * Gets the transactioncoordinator value for this Response.
     * 
     * @return transactioncoordinator
     */
    public java.lang.String getTransactioncoordinator() {
        return transactioncoordinator;
    }


    /**
     * Sets the transactioncoordinator value for this Response.
     * 
     * @param transactioncoordinator
     */
    public void setTransactioncoordinator(java.lang.String transactioncoordinator) {
        this.transactioncoordinator = transactioncoordinator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Response)) return false;
        Response other = (Response) obj;
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
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.transactioncoordinator==null && other.getTransactioncoordinator()==null) || 
             (this.transactioncoordinator!=null &&
              this.transactioncoordinator.equals(other.getTransactioncoordinator())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getTransactioncoordinator() != null) {
            _hashCode += getTransactioncoordinator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Response"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("status");
        attrField.setXmlName(new javax.xml.namespace.QName("", "status"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeResponseStatus"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("transactioncoordinator");
        attrField.setXmlName(new javax.xml.namespace.QName("", "transactioncoordinator"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCorbaIor"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason"));
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
