/**
 * GetResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice;

public class GetResponse  implements java.io.Serializable {
    /* The code for the company from
     *                             which you attempted to retrieve the
     *                             specified document master. */
    private java.lang.String cmpCode;

    /* The code for the document
     *                             master that you attempted to retrieve. */
    private java.lang.String code;

    /* Contains the document master
     *                             you have retrieved from the database. */
    private com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster;

    public GetResponse() {
    }

    public GetResponse(
           java.lang.String cmpCode,
           java.lang.String code,
           com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
           this.cmpCode = cmpCode;
           this.code = code;
           this.documentMaster = documentMaster;
    }


    /**
     * Gets the cmpCode value for this GetResponse.
     * 
     * @return cmpCode   * The code for the company from
     *                             which you attempted to retrieve the
     *                             specified document master.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this GetResponse.
     * 
     * @param cmpCode   * The code for the company from
     *                             which you attempted to retrieve the
     *                             specified document master.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this GetResponse.
     * 
     * @return code   * The code for the document
     *                             master that you attempted to retrieve.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this GetResponse.
     * 
     * @param code   * The code for the document
     *                             master that you attempted to retrieve.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the documentMaster value for this GetResponse.
     * 
     * @return documentMaster   * Contains the document master
     *                             you have retrieved from the database.
     */
    public com.coda.www.efinance.schemas.documentmaster.DocumentMaster getDocumentMaster() {
        return documentMaster;
    }


    /**
     * Sets the documentMaster value for this GetResponse.
     * 
     * @param documentMaster   * Contains the document master
     *                             you have retrieved from the database.
     */
    public void setDocumentMaster(com.coda.www.efinance.schemas.documentmaster.DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResponse)) return false;
        GetResponse other = (GetResponse) obj;
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
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getDocumentMaster() != null) {
            _hashCode += getDocumentMaster().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", ">GetResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "DocumentMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentMaster"));
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
