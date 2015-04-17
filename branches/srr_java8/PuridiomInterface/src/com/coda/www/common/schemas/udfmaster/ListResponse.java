/**
 * ListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.udfmaster;

public class ListResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* A list of UDF master
     *                             items. */
    private com.coda.www.common.schemas.udfmaster.UDFMasterListItem[] UDFMasterItemList;

    public ListResponse() {
    }

    public ListResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.common.schemas.udfmaster.UDFMasterListItem[] UDFMasterItemList) {
        super();
        this.UDFMasterItemList = UDFMasterItemList;
    }


    /**
     * Gets the UDFMasterItemList value for this ListResponse.
     *
     * @return UDFMasterItemList   * A list of UDF master
     *                             items.
     */
    public com.coda.www.common.schemas.udfmaster.UDFMasterListItem[] getUDFMasterItemList() {
        return UDFMasterItemList;
    }


    /**
     * Sets the UDFMasterItemList value for this ListResponse.
     *
     * @param UDFMasterItemList   * A list of UDF master
     *                             items.
     */
    public void setUDFMasterItemList(com.coda.www.common.schemas.udfmaster.UDFMasterListItem[] UDFMasterItemList) {
        this.UDFMasterItemList = UDFMasterItemList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListResponse)) return false;
        ListResponse other = (ListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.UDFMasterItemList==null && other.getUDFMasterItemList()==null) ||
             (this.UDFMasterItemList!=null &&
              java.util.Arrays.equals(this.UDFMasterItemList, other.getUDFMasterItemList())));
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
        if (getUDFMasterItemList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUDFMasterItemList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUDFMasterItemList(), i);
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
        new org.apache.axis.description.TypeDesc(ListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UDFMasterItemList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterItemList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem"));
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
