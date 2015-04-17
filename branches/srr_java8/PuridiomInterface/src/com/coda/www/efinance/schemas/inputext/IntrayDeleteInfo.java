/**
 * IntrayDeleteInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains the
 *                 reason why the specified document on the Intray is
 * being
 *             deleted.
 */
public class IntrayDeleteInfo  implements java.io.Serializable {
    private java.lang.String reason;

    /* Indicates that the user is
     *                         willing to delete Intercompany documents from
     * the Intray. */
    private java.lang.Boolean includeInterCompanyDocs;

    public IntrayDeleteInfo() {
    }

    public IntrayDeleteInfo(
           java.lang.String reason,
           java.lang.Boolean includeInterCompanyDocs) {
           this.reason = reason;
           this.includeInterCompanyDocs = includeInterCompanyDocs;
    }


    /**
     * Gets the reason value for this IntrayDeleteInfo.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this IntrayDeleteInfo.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }


    /**
     * Gets the includeInterCompanyDocs value for this IntrayDeleteInfo.
     * 
     * @return includeInterCompanyDocs   * Indicates that the user is
     *                         willing to delete Intercompany documents from
     * the Intray.
     */
    public java.lang.Boolean getIncludeInterCompanyDocs() {
        return includeInterCompanyDocs;
    }


    /**
     * Sets the includeInterCompanyDocs value for this IntrayDeleteInfo.
     * 
     * @param includeInterCompanyDocs   * Indicates that the user is
     *                         willing to delete Intercompany documents from
     * the Intray.
     */
    public void setIncludeInterCompanyDocs(java.lang.Boolean includeInterCompanyDocs) {
        this.includeInterCompanyDocs = includeInterCompanyDocs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayDeleteInfo)) return false;
        IntrayDeleteInfo other = (IntrayDeleteInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            ((this.includeInterCompanyDocs==null && other.getIncludeInterCompanyDocs()==null) || 
             (this.includeInterCompanyDocs!=null &&
              this.includeInterCompanyDocs.equals(other.getIncludeInterCompanyDocs())));
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
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        if (getIncludeInterCompanyDocs() != null) {
            _hashCode += getIncludeInterCompanyDocs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayDeleteInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayDeleteInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeInterCompanyDocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IncludeInterCompanyDocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
