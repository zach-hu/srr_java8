/**
 * ListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class ListResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed. */
    private com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys filter;

    /* Contains extended key
     *                                 information for the listed element
     * masters. */
    private com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys;

    public ListResponse() {
    }

    public ListResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys filter,
           com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys) {
        super();
        this.filter = filter;
        this.keys = keys;
    }


    /**
     * Gets the filter value for this ListResponse.
     *
     * @return filter   * The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this ListResponse.
     *
     * @param filter   * The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed.
     */
    public void setFilter(com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys filter) {
        this.filter = filter;
    }


    /**
     * Gets the keys value for this ListResponse.
     *
     * @return keys   * Contains extended key
     *                                 information for the listed element
     * masters.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this ListResponse.
     *
     * @param keys   * Contains extended key
     *                                 information for the listed element
     * masters.
     */
    public void setKeys(com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[] keys) {
        this.keys = keys;
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
            ((this.filter==null && other.getFilter()==null) ||
             (this.filter!=null &&
              this.filter.equals(other.getFilter()))) &&
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
        int _hashCode = super.hashCode();
        if (getFilter() != null) {
            _hashCode += getFilter().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(ListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmReqFullKeys"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
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
