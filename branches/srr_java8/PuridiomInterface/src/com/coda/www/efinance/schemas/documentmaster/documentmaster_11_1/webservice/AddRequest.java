/**
 * AddRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice;

public class AddRequest  implements java.io.Serializable {
    private java.lang.String[] companies;

    /* Contains the information for
     *                             the document master you want to create. */
    private com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster;

    public AddRequest() {
    }

    public AddRequest(
           java.lang.String[] companies,
           com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
           this.companies = companies;
           this.documentMaster = documentMaster;
    }


    /**
     * Gets the companies value for this AddRequest.
     * 
     * @return companies
     */
    public java.lang.String[] getCompanies() {
        return companies;
    }


    /**
     * Sets the companies value for this AddRequest.
     * 
     * @param companies
     */
    public void setCompanies(java.lang.String[] companies) {
        this.companies = companies;
    }


    /**
     * Gets the documentMaster value for this AddRequest.
     * 
     * @return documentMaster   * Contains the information for
     *                             the document master you want to create.
     */
    public com.coda.www.efinance.schemas.documentmaster.DocumentMaster getDocumentMaster() {
        return documentMaster;
    }


    /**
     * Sets the documentMaster value for this AddRequest.
     * 
     * @param documentMaster   * Contains the information for
     *                             the document master you want to create.
     */
    public void setDocumentMaster(com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddRequest)) return false;
        AddRequest other = (AddRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.companies==null && other.getCompanies()==null) || 
             (this.companies!=null &&
              java.util.Arrays.equals(this.companies, other.getCompanies()))) &&
            ((this.documentMaster==null && other.getDocumentMaster()==null) || 
             (this.documentMaster!=null &&
              this.documentMaster.equals(other.getDocumentMaster())));
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
        if (getCompanies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompanies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompanies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentMaster() != null) {
            _hashCode += getDocumentMaster().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", ">AddRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "Companies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "DocumentMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentMaster"));
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
