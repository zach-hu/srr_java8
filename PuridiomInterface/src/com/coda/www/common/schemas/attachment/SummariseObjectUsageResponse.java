/**
 * SummariseObjectUsageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.attachment;

public class SummariseObjectUsageResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* A summary of the
     *                                 object usage. */
    private com.coda.www.common.schemas.attachment.ObjectUsageSummary[] summary;

    public SummariseObjectUsageResponse() {
    }

    public SummariseObjectUsageResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.common.schemas.attachment.ObjectUsageSummary[] summary) {
        super();
        this.summary = summary;
    }


    /**
     * Gets the summary value for this SummariseObjectUsageResponse.
     *
     * @return summary   * A summary of the
     *                                 object usage.
     */
    public com.coda.www.common.schemas.attachment.ObjectUsageSummary[] getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this SummariseObjectUsageResponse.
     *
     * @param summary   * A summary of the
     *                                 object usage.
     */
    public void setSummary(com.coda.www.common.schemas.attachment.ObjectUsageSummary[] summary) {
        this.summary = summary;
    }

    public com.coda.www.common.schemas.attachment.ObjectUsageSummary getSummary(int i) {
        return this.summary[i];
    }

    public void setSummary(int i, com.coda.www.common.schemas.attachment.ObjectUsageSummary _value) {
        this.summary[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SummariseObjectUsageResponse)) return false;
        SummariseObjectUsageResponse other = (SummariseObjectUsageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.summary==null && other.getSummary()==null) ||
             (this.summary!=null &&
              java.util.Arrays.equals(this.summary, other.getSummary())));
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
        if (getSummary() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSummary());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSummary(), i);
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
        new org.apache.axis.description.TypeDesc(SummariseObjectUsageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "SummariseObjectUsageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ObjectUsageSummary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
