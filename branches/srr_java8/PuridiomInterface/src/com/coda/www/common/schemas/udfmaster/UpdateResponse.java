/**
 * UpdateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;

public class UpdateResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The details of a UDF
     *                             master. */
    private com.coda.www.common.schemas.udfmaster.UDFMaster UDFMaster;

    public UpdateResponse() {
    }

    public UpdateResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.common.schemas.udfmaster.UDFMaster UDFMaster) {
        super();
        this.UDFMaster = UDFMaster;
    }


    /**
     * Gets the UDFMaster value for this UpdateResponse.
     *
     * @return UDFMaster   * The details of a UDF
     *                             master.
     */
    public com.coda.www.common.schemas.udfmaster.UDFMaster getUDFMaster() {
        return UDFMaster;
    }


    /**
     * Sets the UDFMaster value for this UpdateResponse.
     *
     * @param UDFMaster   * The details of a UDF
     *                             master.
     */
    public void setUDFMaster(com.coda.www.common.schemas.udfmaster.UDFMaster UDFMaster) {
        this.UDFMaster = UDFMaster;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateResponse)) return false;
        UpdateResponse other = (UpdateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.UDFMaster==null && other.getUDFMaster()==null) ||
             (this.UDFMaster!=null &&
              this.UDFMaster.equals(other.getUDFMaster())));
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
        if (getUDFMaster() != null) {
            _hashCode += getUDFMaster().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UpdateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster"));
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
