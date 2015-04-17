/**
 * ListPunchoutItemCodesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class ListPunchoutItemCodesResponse  implements java.io.Serializable {
    /* A filter that selects the
     *                             punchout item codes to be listed. */
    private com.coda.www.efinance.schemas.common.ReqKeys filter;

    /* If set to FALSE, it indicates
     *                             that the Procurement database, in which
     * the
     *                             punchout items are held, cannot be accessed.
     * If set to TRUE, the Procurement database has
     *                             been accessed; this means that if nothing
     * is
     *                             returned, the element does not contain
     * a
     *                             list of punchout items. */
    private boolean foundDb;

    /* Contains the keys for the
     *                             punchout item codes listed. */
    private com.coda.www.efinance.schemas.common.KeyDataElement[] keys;

    public ListPunchoutItemCodesResponse() {
    }

    public ListPunchoutItemCodesResponse(
           com.coda.www.efinance.schemas.common.ReqKeys filter,
           boolean foundDb,
           com.coda.www.efinance.schemas.common.KeyDataElement[] keys) {
           this.filter = filter;
           this.foundDb = foundDb;
           this.keys = keys;
    }


    /**
     * Gets the filter value for this ListPunchoutItemCodesResponse.
     * 
     * @return filter   * A filter that selects the
     *                             punchout item codes to be listed.
     */
    public com.coda.www.efinance.schemas.common.ReqKeys getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this ListPunchoutItemCodesResponse.
     * 
     * @param filter   * A filter that selects the
     *                             punchout item codes to be listed.
     */
    public void setFilter(com.coda.www.efinance.schemas.common.ReqKeys filter) {
        this.filter = filter;
    }


    /**
     * Gets the foundDb value for this ListPunchoutItemCodesResponse.
     * 
     * @return foundDb   * If set to FALSE, it indicates
     *                             that the Procurement database, in which
     * the
     *                             punchout items are held, cannot be accessed.
     * If set to TRUE, the Procurement database has
     *                             been accessed; this means that if nothing
     * is
     *                             returned, the element does not contain
     * a
     *                             list of punchout items.
     */
    public boolean isFoundDb() {
        return foundDb;
    }


    /**
     * Sets the foundDb value for this ListPunchoutItemCodesResponse.
     * 
     * @param foundDb   * If set to FALSE, it indicates
     *                             that the Procurement database, in which
     * the
     *                             punchout items are held, cannot be accessed.
     * If set to TRUE, the Procurement database has
     *                             been accessed; this means that if nothing
     * is
     *                             returned, the element does not contain
     * a
     *                             list of punchout items.
     */
    public void setFoundDb(boolean foundDb) {
        this.foundDb = foundDb;
    }


    /**
     * Gets the keys value for this ListPunchoutItemCodesResponse.
     * 
     * @return keys   * Contains the keys for the
     *                             punchout item codes listed.
     */
    public com.coda.www.efinance.schemas.common.KeyDataElement[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this ListPunchoutItemCodesResponse.
     * 
     * @param keys   * Contains the keys for the
     *                             punchout item codes listed.
     */
    public void setKeys(com.coda.www.efinance.schemas.common.KeyDataElement[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListPunchoutItemCodesResponse)) return false;
        ListPunchoutItemCodesResponse other = (ListPunchoutItemCodesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.filter==null && other.getFilter()==null) || 
             (this.filter!=null &&
              this.filter.equals(other.getFilter()))) &&
            this.foundDb == other.isFoundDb() &&
            ((this.keys==null && other.getKeys()==null) || 
             (this.keys!=null &&
              java.util.Arrays.equals(this.keys, other.getKeys())));
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
        if (getFilter() != null) {
            _hashCode += getFilter().hashCode();
        }
        _hashCode += (isFoundDb() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeys(), i);
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
        new org.apache.axis.description.TypeDesc(ListPunchoutItemCodesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">ListPunchoutItemCodesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeys"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundDb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "FoundDb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyDataElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key"));
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
