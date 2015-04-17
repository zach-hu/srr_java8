/**
 * ItmReqKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * Contains key information about an
 *                 input template master. The information includes the
 * company within which the input template exists and the
 *                 code for the linked document master.
 */
public class ItmReqKey  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The input template master
     *                     code. */
    private java.lang.String code;

    /* The code for the linked
     *                         document master. */
    private java.lang.String docCode;

    public ItmReqKey() {
    }

    public ItmReqKey(
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String docCode) {
           this.cmpCode = cmpCode;
           this.code = code;
           this.docCode = docCode;
    }


    /**
     * Gets the cmpCode value for this ItmReqKey.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this ItmReqKey.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this ItmReqKey.
     * 
     * @return code   * The input template master
     *                     code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ItmReqKey.
     * 
     * @param code   * The input template master
     *                     code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the docCode value for this ItmReqKey.
     * 
     * @return docCode   * The code for the linked
     *                         document master.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this ItmReqKey.
     * 
     * @param docCode   * The code for the linked
     *                         document master.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItmReqKey)) return false;
        ItmReqKey other = (ItmReqKey) obj;
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
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode())));
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
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ItmReqKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "itmReqKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
