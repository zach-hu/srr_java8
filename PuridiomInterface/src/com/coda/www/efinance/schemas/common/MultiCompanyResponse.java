/**
 * MultiCompanyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.common;

public class MultiCompanyResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.common.Warning warning;

    private java.lang.String aborted;  // attribute

    public MultiCompanyResponse() {
    }

    public MultiCompanyResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String aborted,
           com.coda.www.efinance.schemas.common.Warning warning) {
        super();
        this.aborted = aborted;
        this.warning = warning;
    }


    /**
     * Gets the warning value for this MultiCompanyResponse.
     *
     * @return warning
     */
    public com.coda.www.efinance.schemas.common.Warning getWarning() {
        return warning;
    }


    /**
     * Sets the warning value for this MultiCompanyResponse.
     *
     * @param warning
     */
    public void setWarning(com.coda.www.efinance.schemas.common.Warning warning) {
        this.warning = warning;
    }


    /**
     * Gets the aborted value for this MultiCompanyResponse.
     *
     * @return aborted
     */
    public java.lang.String getAborted() {
        return aborted;
    }


    /**
     * Sets the aborted value for this MultiCompanyResponse.
     *
     * @param aborted
     */
    public void setAborted(java.lang.String aborted) {
        this.aborted = aborted;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MultiCompanyResponse)) return false;
        MultiCompanyResponse other = (MultiCompanyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.warning==null && other.getWarning()==null) ||
             (this.warning!=null &&
              this.warning.equals(other.getWarning()))) &&
            ((this.aborted==null && other.getAborted()==null) ||
             (this.aborted!=null &&
              this.aborted.equals(other.getAborted())));
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
        if (getWarning() != null) {
            _hashCode += getWarning().hashCode();
        }
        if (getAborted() != null) {
            _hashCode += getAborted().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MultiCompanyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "MultiCompanyResponse"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("aborted");
        attrField.setXmlName(new javax.xml.namespace.QName("", "aborted"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Warning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Warning"));
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
