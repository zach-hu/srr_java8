/**
 * FindResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;

public class FindResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* A list of UDF
     *                             masters. */
    private com.coda.www.common.schemas.udfmaster.UDFMaster[] UDFMasterList;

    public FindResponse() {
    }

    public FindResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.common.schemas.udfmaster.UDFMaster[] UDFMasterList) {
        super();
        this.UDFMasterList = UDFMasterList;
    }


    /**
     * Gets the UDFMasterList value for this FindResponse.
     *
     * @return UDFMasterList   * A list of UDF
     *                             masters.
     */
    public com.coda.www.common.schemas.udfmaster.UDFMaster[] getUDFMasterList() {
        return UDFMasterList;
    }


    /**
     * Sets the UDFMasterList value for this FindResponse.
     *
     * @param UDFMasterList   * A list of UDF
     *                             masters.
     */
    public void setUDFMasterList(com.coda.www.common.schemas.udfmaster.UDFMaster[] UDFMasterList) {
        this.UDFMasterList = UDFMasterList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FindResponse)) return false;
        FindResponse other = (FindResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.UDFMasterList==null && other.getUDFMasterList()==null) ||
             (this.UDFMasterList!=null &&
              java.util.Arrays.equals(this.UDFMasterList, other.getUDFMasterList())));
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
        if (getUDFMasterList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFMasterList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFMasterList(), i);
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
        new org.apache.axis.description.TypeDesc(FindResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "FindResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFMasterList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster"));
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
