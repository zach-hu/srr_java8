/**
 * FinderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class FinderResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed. */
    private com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter finderFilter;

    /* Contains the key
     *                                 information for the listed element
     * masters. */
    private com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys;

    public FinderResponse() {
    }

    public FinderResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter finderFilter,
           com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys) {
        super();
        this.finderFilter = finderFilter;
        this.keys = keys;
    }


    /**
     * Gets the finderFilter value for this FinderResponse.
     *
     * @return finderFilter   * The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter getFinderFilter() {
        return finderFilter;
    }


    /**
     * Sets the finderFilter value for this FinderResponse.
     *
     * @param finderFilter   * The filter that was
     *                                 used to select the element masters
     * to be
     *                             listed.
     */
    public void setFinderFilter(com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter finderFilter) {
        this.finderFilter = finderFilter;
    }


    /**
     * Gets the keys value for this FinderResponse.
     *
     * @return keys   * Contains the key
     *                                 information for the listed element
     * masters.
     */
    public com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this FinderResponse.
     *
     * @param keys   * Contains the key
     *                                 information for the listed element
     * masters.
     */
    public void setKeys(com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FinderResponse)) return false;
        FinderResponse other = (FinderResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.finderFilter==null && other.getFinderFilter()==null) ||
             (this.finderFilter!=null &&
              this.finderFilter.equals(other.getFinderFilter()))) &&
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
        if (getFinderFilter() != null) {
            _hashCode += getFinderFilter().hashCode();
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
        new org.apache.axis.description.TypeDesc(FinderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FinderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finderFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FinderFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderFilter"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElementNamed"));
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
