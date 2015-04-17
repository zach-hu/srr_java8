/**
 * PurgeTraderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class PurgeTraderResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The title of a log
     *                                 file in which details of the purge
     * trader session are
     *                             recorded. */
    private java.lang.String logTitle;

    /* The filter sent with
     *                                 the PurgeTraderRequest corresponding
     * to
     *                                 this response. */
    private com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter;

    public PurgeTraderResponse() {
    }

    public PurgeTraderResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String logTitle,
           com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter) {
        super();
        this.logTitle = logTitle;
        this.filter = filter;
    }


    /**
     * Gets the logTitle value for this PurgeTraderResponse.
     *
     * @return logTitle   * The title of a log
     *                                 file in which details of the purge
     * trader session are
     *                             recorded.
     */
    public java.lang.String getLogTitle() {
        return logTitle;
    }


    /**
     * Sets the logTitle value for this PurgeTraderResponse.
     *
     * @param logTitle   * The title of a log
     *                                 file in which details of the purge
     * trader session are
     *                             recorded.
     */
    public void setLogTitle(java.lang.String logTitle) {
        this.logTitle = logTitle;
    }


    /**
     * Gets the filter value for this PurgeTraderResponse.
     *
     * @return filter   * The filter sent with
     *                                 the PurgeTraderRequest corresponding
     * to
     *                                 this response.
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this PurgeTraderResponse.
     *
     * @param filter   * The filter sent with
     *                                 the PurgeTraderRequest corresponding
     * to
     *                                 this response.
     */
    public void setFilter(com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter filter) {
        this.filter = filter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PurgeTraderResponse)) return false;
        PurgeTraderResponse other = (PurgeTraderResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.logTitle==null && other.getLogTitle()==null) ||
             (this.logTitle!=null &&
              this.logTitle.equals(other.getLogTitle()))) &&
            ((this.filter==null && other.getFilter()==null) ||
             (this.filter!=null &&
              this.filter.equals(other.getFilter())));
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
        if (getLogTitle() != null) {
            _hashCode += getLogTitle().hashCode();
        }
        if (getFilter() != null) {
            _hashCode += getFilter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PurgeTraderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PurgeTraderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LogTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectFilter"));
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
