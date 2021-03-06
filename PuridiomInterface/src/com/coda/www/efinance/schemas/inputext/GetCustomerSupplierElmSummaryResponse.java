/**
 * GetCustomerSupplierElmSummaryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class GetCustomerSupplierElmSummaryResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains results
     *                                 information for the
     *                                 GetCustomerSupplierElmSummary request
     * corresponding to this
     *                             response. */
    private com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine[] results;

    public GetCustomerSupplierElmSummaryResponse() {
    }

    public GetCustomerSupplierElmSummaryResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine[] results) {
        super();
        this.results = results;
    }


    /**
     * Gets the results value for this GetCustomerSupplierElmSummaryResponse.
     *
     * @return results   * Contains results
     *                                 information for the
     *                                 GetCustomerSupplierElmSummary request
     * corresponding to this
     *                             response.
     */
    public com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine[] getResults() {
        return results;
    }


    /**
     * Sets the results value for this GetCustomerSupplierElmSummaryResponse.
     *
     * @param results   * Contains results
     *                                 information for the
     *                                 GetCustomerSupplierElmSummary request
     * corresponding to this
     *                             response.
     */
    public void setResults(com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine[] results) {
        this.results = results;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCustomerSupplierElmSummaryResponse)) return false;
        GetCustomerSupplierElmSummaryResponse other = (GetCustomerSupplierElmSummaryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.results==null && other.getResults()==null) ||
             (this.results!=null &&
              java.util.Arrays.equals(this.results, other.getResults())));
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
        if (getResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResults(), i);
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
        new org.apache.axis.description.TypeDesc(GetCustomerSupplierElmSummaryResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetCustomerSupplierElmSummaryResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("results");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Results"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CustomerSupplierElmSummaryLine"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Line"));
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
