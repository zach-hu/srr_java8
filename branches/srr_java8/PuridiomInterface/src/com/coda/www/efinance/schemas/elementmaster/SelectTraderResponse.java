/**
 * SelectTraderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class SelectTraderResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The filter sent with
     *                                 the SelectTraderRequest corresponding
     * to
     *                                 this response. */
    private com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter;

    /* Contains details of
     *                                 the traders selected by the
     *                                 SelectTraderRequest corresponding
     * to
     *                                 this response. */
    private com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail[] details;

    public SelectTraderResponse() {
    }

    public SelectTraderResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter,
           com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail[] details) {
        super();
        this.filter = filter;
        this.details = details;
    }


    /**
     * Gets the filter value for this SelectTraderResponse.
     *
     * @return filter   * The filter sent with
     *                                 the SelectTraderRequest corresponding
     * to
     *                                 this response.
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this SelectTraderResponse.
     *
     * @param filter   * The filter sent with
     *                                 the SelectTraderRequest corresponding
     * to
     *                                 this response.
     */
    public void setFilter(com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter) {
        this.filter = filter;
    }


    /**
     * Gets the details value for this SelectTraderResponse.
     *
     * @return details   * Contains details of
     *                                 the traders selected by the
     *                                 SelectTraderRequest corresponding
     * to
     *                                 this response.
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this SelectTraderResponse.
     *
     * @param details   * Contains details of
     *                                 the traders selected by the
     *                                 SelectTraderRequest corresponding
     * to
     *                                 this response.
     */
    public void setDetails(com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail[] details) {
        this.details = details;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectTraderResponse)) return false;
        SelectTraderResponse other = (SelectTraderResponse) obj;
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
            ((this.details==null && other.getDetails()==null) ||
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails())));
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
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetails(), i);
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
        new org.apache.axis.description.TypeDesc(SelectTraderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SelectTraderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectFilter"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Detail"));
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
