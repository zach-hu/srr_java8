/**
 * GetMissingDocNumbersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class GetMissingDocNumbersResponse  implements java.io.Serializable {
    /* The code of the country in
     *                             which you attempted to search for missing
     * document numbers. */
    private java.lang.String cmpCode;

    /* The code of the document
     *                             master you attempted to search for missing
     * document numbers. */
    private java.lang.String docCode;

    /* The missing document number
     *                             you attempted to search for. */
    private java.lang.String docNum;

    /* The year/period you attempted
     *                             to search for missing document numbers. */
    private java.lang.String period;

    /* The reason why the document
     *                             number was not used. */
    private java.lang.String description;

    /* Contains the missing document
     *                             numbers retrieved by the GetMissingDocNumbersRequest. */
    private com.coda.www.efinance.schemas.inputext.MissingDocNumber[] missingDocNumbers;

    public GetMissingDocNumbersResponse() {
    }

    public GetMissingDocNumbersResponse(
           java.lang.String cmpCode,
           java.lang.String docCode,
           java.lang.String docNum,
           java.lang.String period,
           java.lang.String description,
           com.coda.www.efinance.schemas.inputext.MissingDocNumber[] missingDocNumbers) {
           this.cmpCode = cmpCode;
           this.docCode = docCode;
           this.docNum = docNum;
           this.period = period;
           this.description = description;
           this.missingDocNumbers = missingDocNumbers;
    }


    /**
     * Gets the cmpCode value for this GetMissingDocNumbersResponse.
     * 
     * @return cmpCode   * The code of the country in
     *                             which you attempted to search for missing
     * document numbers.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this GetMissingDocNumbersResponse.
     * 
     * @param cmpCode   * The code of the country in
     *                             which you attempted to search for missing
     * document numbers.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the docCode value for this GetMissingDocNumbersResponse.
     * 
     * @return docCode   * The code of the document
     *                             master you attempted to search for missing
     * document numbers.
     */
    public java.lang.String getDocCode() {
        return docCode;
    }


    /**
     * Sets the docCode value for this GetMissingDocNumbersResponse.
     * 
     * @param docCode   * The code of the document
     *                             master you attempted to search for missing
     * document numbers.
     */
    public void setDocCode(java.lang.String docCode) {
        this.docCode = docCode;
    }


    /**
     * Gets the docNum value for this GetMissingDocNumbersResponse.
     * 
     * @return docNum   * The missing document number
     *                             you attempted to search for.
     */
    public java.lang.String getDocNum() {
        return docNum;
    }


    /**
     * Sets the docNum value for this GetMissingDocNumbersResponse.
     * 
     * @param docNum   * The missing document number
     *                             you attempted to search for.
     */
    public void setDocNum(java.lang.String docNum) {
        this.docNum = docNum;
    }


    /**
     * Gets the period value for this GetMissingDocNumbersResponse.
     * 
     * @return period   * The year/period you attempted
     *                             to search for missing document numbers.
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this GetMissingDocNumbersResponse.
     * 
     * @param period   * The year/period you attempted
     *                             to search for missing document numbers.
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the description value for this GetMissingDocNumbersResponse.
     * 
     * @return description   * The reason why the document
     *                             number was not used.
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this GetMissingDocNumbersResponse.
     * 
     * @param description   * The reason why the document
     *                             number was not used.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the missingDocNumbers value for this GetMissingDocNumbersResponse.
     * 
     * @return missingDocNumbers   * Contains the missing document
     *                             numbers retrieved by the GetMissingDocNumbersRequest.
     */
    public com.coda.www.efinance.schemas.inputext.MissingDocNumber[] getMissingDocNumbers() {
        return missingDocNumbers;
    }


    /**
     * Sets the missingDocNumbers value for this GetMissingDocNumbersResponse.
     * 
     * @param missingDocNumbers   * Contains the missing document
     *                             numbers retrieved by the GetMissingDocNumbersRequest.
     */
    public void setMissingDocNumbers(com.coda.www.efinance.schemas.inputext.MissingDocNumber[] missingDocNumbers) {
        this.missingDocNumbers = missingDocNumbers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMissingDocNumbersResponse)) return false;
        GetMissingDocNumbersResponse other = (GetMissingDocNumbersResponse) obj;
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
            ((this.docCode==null && other.getDocCode()==null) || 
             (this.docCode!=null &&
              this.docCode.equals(other.getDocCode()))) &&
            ((this.docNum==null && other.getDocNum()==null) || 
             (this.docNum!=null &&
              this.docNum.equals(other.getDocNum()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.missingDocNumbers==null && other.getMissingDocNumbers()==null) || 
             (this.missingDocNumbers!=null &&
              java.util.Arrays.equals(this.missingDocNumbers, other.getMissingDocNumbers())));
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
        if (getDocCode() != null) {
            _hashCode += getDocCode().hashCode();
        }
        if (getDocNum() != null) {
            _hashCode += getDocNum().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getMissingDocNumbers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMissingDocNumbers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMissingDocNumbers(), i);
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
        new org.apache.axis.description.TypeDesc(GetMissingDocNumbersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingDocNumbersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "DocCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "DocNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingDocNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "MissingDocNumbers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumber"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumber"));
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
