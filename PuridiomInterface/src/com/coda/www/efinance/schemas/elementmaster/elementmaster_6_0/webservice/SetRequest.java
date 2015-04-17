/**
 * SetRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class SetRequest  implements java.io.Serializable {
    private java.lang.String[] companies;

    private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequestSetOptions setOptions;

    /* Identifies the element master
     *                             you want to update and the new values
     * for
     *                             the fields you want to set. Include optional
     * elements only for those fields you want to
     *                             set. Lists, if specified, are replaced
     * in
     *                             their entirety. */
    private com.coda.www.efinance.schemas.elementmaster.Element element;

    public SetRequest() {
    }

    public SetRequest(
           java.lang.String[] companies,
           com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequestSetOptions setOptions,
           com.coda.www.efinance.schemas.elementmaster.Element element) {
           this.companies = companies;
           this.setOptions = setOptions;
           this.element = element;
    }


    /**
     * Gets the companies value for this SetRequest.
     * 
     * @return companies
     */
    public java.lang.String[] getCompanies() {
        return companies;
    }


    /**
     * Sets the companies value for this SetRequest.
     * 
     * @param companies
     */
    public void setCompanies(java.lang.String[] companies) {
        this.companies = companies;
    }


    /**
     * Gets the setOptions value for this SetRequest.
     * 
     * @return setOptions
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequestSetOptions getSetOptions() {
        return setOptions;
    }


    /**
     * Sets the setOptions value for this SetRequest.
     * 
     * @param setOptions
     */
    public void setSetOptions(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequestSetOptions setOptions) {
        this.setOptions = setOptions;
    }


    /**
     * Gets the element value for this SetRequest.
     * 
     * @return element   * Identifies the element master
     *                             you want to update and the new values
     * for
     *                             the fields you want to set. Include optional
     * elements only for those fields you want to
     *                             set. Lists, if specified, are replaced
     * in
     *                             their entirety.
     */
    public com.coda.www.efinance.schemas.elementmaster.Element getElement() {
        return element;
    }


    /**
     * Sets the element value for this SetRequest.
     * 
     * @param element   * Identifies the element master
     *                             you want to update and the new values
     * for
     *                             the fields you want to set. Include optional
     * elements only for those fields you want to
     *                             set. Lists, if specified, are replaced
     * in
     *                             their entirety.
     */
    public void setElement(com.coda.www.efinance.schemas.elementmaster.Element element) {
        this.element = element;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetRequest)) return false;
        SetRequest other = (SetRequest) obj;
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
            ((this.setOptions==null && other.getSetOptions()==null) || 
             (this.setOptions!=null &&
              this.setOptions.equals(other.getSetOptions()))) &&
            ((this.element==null && other.getElement()==null) || 
             (this.element!=null &&
              this.element.equals(other.getElement())));
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
        if (getSetOptions() != null) {
            _hashCode += getSetOptions().hashCode();
        }
        if (getElement() != null) {
            _hashCode += getElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">SetRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Companies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "SetOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>SetRequest>SetOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("element");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Element"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element"));
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
