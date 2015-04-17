/**
 * FetchResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class FetchResponse  implements java.io.Serializable {
    /* The code for the company from
     *                             which you attempted to fetch element information. */
    private java.lang.String cmpCode;

    /* The level of the element
     *                             whose information you attempted to fetch. */
    private short level;

    /* The code for the element
     *                             master whose information you attempted
     * to fetch. */
    private java.lang.String code;

    /* Contains the element
     *                             information fetched from the database. */
    private com.coda.www.efinance.schemas.elementmaster.Element element;

    public FetchResponse() {
    }

    public FetchResponse(
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           com.coda.www.efinance.schemas.elementmaster.Element element) {
           this.cmpCode = cmpCode;
           this.level = level;
           this.code = code;
           this.element = element;
    }


    /**
     * Gets the cmpCode value for this FetchResponse.
     * 
     * @return cmpCode   * The code for the company from
     *                             which you attempted to fetch element information.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this FetchResponse.
     * 
     * @param cmpCode   * The code for the company from
     *                             which you attempted to fetch element information.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this FetchResponse.
     * 
     * @return level   * The level of the element
     *                             whose information you attempted to fetch.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this FetchResponse.
     * 
     * @param level   * The level of the element
     *                             whose information you attempted to fetch.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this FetchResponse.
     * 
     * @return code   * The code for the element
     *                             master whose information you attempted
     * to fetch.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this FetchResponse.
     * 
     * @param code   * The code for the element
     *                             master whose information you attempted
     * to fetch.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the element value for this FetchResponse.
     * 
     * @return element   * Contains the element
     *                             information fetched from the database.
     */
    public com.coda.www.efinance.schemas.elementmaster.Element getElement() {
        return element;
    }


    /**
     * Sets the element value for this FetchResponse.
     * 
     * @param element   * Contains the element
     *                             information fetched from the database.
     */
    public void setElement(com.coda.www.efinance.schemas.elementmaster.Element element) {
        this.element = element;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchResponse)) return false;
        FetchResponse other = (FetchResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            this.level == other.getLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getElement() != null) {
            _hashCode += getElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FetchResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">FetchResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("element");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "Element"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element"));
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
