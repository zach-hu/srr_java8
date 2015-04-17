/**
 * AddResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice;

public class AddResponse  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponseMultiCompany multiCompany;

    /* The code for the company in
     *                             which you attempted to create the input
     * template master. */
    private java.lang.String cmpCode;

    /* The code for the input
     *                             template master that you attempted to
     * create. */
    private java.lang.String code;

    /* The TimeStamp value for this
     *                             input template master in the database. */
    private short timeStamp;

    public AddResponse() {
    }

    public AddResponse(
           com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponseMultiCompany multiCompany,
           java.lang.String cmpCode,
           java.lang.String code,
           short timeStamp) {
           this.multiCompany = multiCompany;
           this.cmpCode = cmpCode;
           this.code = code;
           this.timeStamp = timeStamp;
    }


    /**
     * Gets the multiCompany value for this AddResponse.
     * 
     * @return multiCompany
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponseMultiCompany getMultiCompany() {
        return multiCompany;
    }


    /**
     * Sets the multiCompany value for this AddResponse.
     * 
     * @param multiCompany
     */
    public void setMultiCompany(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponseMultiCompany multiCompany) {
        this.multiCompany = multiCompany;
    }


    /**
     * Gets the cmpCode value for this AddResponse.
     * 
     * @return cmpCode   * The code for the company in
     *                             which you attempted to create the input
     * template master.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this AddResponse.
     * 
     * @param cmpCode   * The code for the company in
     *                             which you attempted to create the input
     * template master.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this AddResponse.
     * 
     * @return code   * The code for the input
     *                             template master that you attempted to
     * create.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this AddResponse.
     * 
     * @param code   * The code for the input
     *                             template master that you attempted to
     * create.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the timeStamp value for this AddResponse.
     * 
     * @return timeStamp   * The TimeStamp value for this
     *                             input template master in the database.
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this AddResponse.
     * 
     * @param timeStamp   * The TimeStamp value for this
     *                             input template master in the database.
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddResponse)) return false;
        AddResponse other = (AddResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.multiCompany==null && other.getMultiCompany()==null) || 
             (this.multiCompany!=null &&
              this.multiCompany.equals(other.getMultiCompany()))) &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            this.timeStamp == other.getTimeStamp();
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
        if (getMultiCompany() != null) {
            _hashCode += getMultiCompany().hashCode();
        }
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        _hashCode += getTimeStamp();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">AddResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multiCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "MultiCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">>AddResponse>MultiCompany"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
