/**
 * RefreshTotalsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class RefreshTotalsResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains the document
     *                                 totals in document, home and dual
     *                             currencies. */
    private com.coda.www.efinance.schemas.inputext.DocTotals totals;

    public RefreshTotalsResponse() {
    }

    public RefreshTotalsResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.inputext.DocTotals totals) {
        super();
        this.totals = totals;
    }


    /**
     * Gets the totals value for this RefreshTotalsResponse.
     *
     * @return totals   * Contains the document
     *                                 totals in document, home and dual
     *                             currencies.
     */
    public com.coda.www.efinance.schemas.inputext.DocTotals getTotals() {
        return totals;
    }


    /**
     * Sets the totals value for this RefreshTotalsResponse.
     *
     * @param totals   * Contains the document
     *                                 totals in document, home and dual
     *                             currencies.
     */
    public void setTotals(com.coda.www.efinance.schemas.inputext.DocTotals totals) {
        this.totals = totals;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RefreshTotalsResponse)) return false;
        RefreshTotalsResponse other = (RefreshTotalsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.totals==null && other.getTotals()==null) ||
             (this.totals!=null &&
              this.totals.equals(other.getTotals())));
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
        if (getTotals() != null) {
            _hashCode += getTotals().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RefreshTotalsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RefreshTotalsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totals");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Totals"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocTotals"));
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
