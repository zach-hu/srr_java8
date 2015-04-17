/**
 * ListAllTraderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class ListAllTraderResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The filter sent with
     *                                 the ListAllTraderRequest corresponding
     * to this response. */
    private com.coda.www.efinance.schemas.elementmaster.AllCmpTraderFilter filter;

    /* The list of trader
     *                                 codes returned by the
     *                                 ListAllTraderRequest corresponding
     * to
     *                                 this response. */
    private java.lang.String[] codes;

    public ListAllTraderResponse() {
    }

    public ListAllTraderResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementmaster.AllCmpTraderFilter filter,
           java.lang.String[] codes) {
        super();
        this.filter = filter;
        this.codes = codes;
    }


    /**
     * Gets the filter value for this ListAllTraderResponse.
     *
     * @return filter   * The filter sent with
     *                                 the ListAllTraderRequest corresponding
     * to this response.
     */
    public com.coda.www.efinance.schemas.elementmaster.AllCmpTraderFilter getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this ListAllTraderResponse.
     *
     * @param filter   * The filter sent with
     *                                 the ListAllTraderRequest corresponding
     * to this response.
     */
    public void setFilter(com.coda.www.efinance.schemas.elementmaster.AllCmpTraderFilter filter) {
        this.filter = filter;
    }


    /**
     * Gets the codes value for this ListAllTraderResponse.
     *
     * @return codes   * The list of trader
     *                                 codes returned by the
     *                                 ListAllTraderRequest corresponding
     * to
     *                                 this response.
     */
    public java.lang.String[] getCodes() {
        return codes;
    }


    /**
     * Sets the codes value for this ListAllTraderResponse.
     *
     * @param codes   * The list of trader
     *                                 codes returned by the
     *                                 ListAllTraderRequest corresponding
     * to
     *                                 this response.
     */
    public void setCodes(java.lang.String[] codes) {
        this.codes = codes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAllTraderResponse)) return false;
        ListAllTraderResponse other = (ListAllTraderResponse) obj;
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
            ((this.codes==null && other.getCodes()==null) ||
             (this.codes!=null &&
              java.util.Arrays.equals(this.codes, other.getCodes())));
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
        if (getCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCodes(), i);
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
        new org.apache.axis.description.TypeDesc(ListAllTraderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListAllTraderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllCmpTraderFilter"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Codes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCode"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
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
