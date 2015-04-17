/**
 * AddRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class AddRequest  implements java.io.Serializable {
    private java.lang.String[] companies;

    private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequestAddOptions addOptions;

    /* Contains the information for
     *                             the element master you want to create. */
    private com.coda.www.efinance.schemas.elementmaster.Element element;

    public AddRequest() {
    }

    public AddRequest(
           java.lang.String[] companies,
           com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequestAddOptions addOptions,
           com.coda.www.efinance.schemas.elementmaster.Element element) {
           this.companies = companies;
           this.addOptions = addOptions;
           this.element = element;
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
     * Gets the addOptions value for this AddRequest.
     * 
     * @return addOptions
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequestAddOptions getAddOptions() {
        return addOptions;
    }


    /**
     * Sets the addOptions value for this AddRequest.
     * 
     * @param addOptions
     */
    public void setAddOptions(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequestAddOptions addOptions) {
        this.addOptions = addOptions;
    }


    /**
     * Gets the element value for this AddRequest.
     * 
     * @return element   * Contains the information for
     *                             the element master you want to create.
     */
    public com.coda.www.efinance.schemas.elementmaster.Element getElement() {
        return element;
    }


    /**
     * Sets the element value for this AddRequest.
     * 
     * @param element   * Contains the information for
     *                             the element master you want to create.
     */
    public void setElement(com.coda.www.efinance.schemas.elementmaster.Element element) {
        this.element = element;
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
            ((this.addOptions==null && other.getAddOptions()==null) || 
             (this.addOptions!=null &&
              this.addOptions.equals(other.getAddOptions()))) &&
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
        if (getAddOptions() != null) {
            _hashCode += getAddOptions().hashCode();
        }
        if (getElement() != null) {
            _hashCode += getElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">AddRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Companies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "AddOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>AddRequest>AddOptions"));
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
