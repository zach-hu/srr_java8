/**
 * UpdateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class UpdateResponse  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponseMultiCompany multiCompany;

    /* The code for the company in
     *                             which you attempted to update the element
     * master. */
    private java.lang.String cmpCode;

    /* The level of the element
     *                             master that you attempted to update. */
    private short level;

    /* The code for the element
     *                             master that you attempted to update. */
    private java.lang.String code;

    /* The TimeStamp value for this
     *                             element master in the database. */
    private java.lang.Short timeStamp;

    /* The TimeStamp value for this
     *                             element master in the oas_elmtaxes table. */
    private java.lang.Short taxesTimeStamp;

    public UpdateResponse() {
    }

    public UpdateResponse(
           com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponseMultiCompany multiCompany,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           java.lang.Short timeStamp,
           java.lang.Short taxesTimeStamp) {
           this.multiCompany = multiCompany;
           this.cmpCode = cmpCode;
           this.level = level;
           this.code = code;
           this.timeStamp = timeStamp;
           this.taxesTimeStamp = taxesTimeStamp;
    }


    /**
     * Gets the multiCompany value for this UpdateResponse.
     * 
     * @return multiCompany
     */
    public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponseMultiCompany getMultiCompany() {
        return multiCompany;
    }


    /**
     * Sets the multiCompany value for this UpdateResponse.
     * 
     * @param multiCompany
     */
    public void setMultiCompany(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponseMultiCompany multiCompany) {
        this.multiCompany = multiCompany;
    }


    /**
     * Gets the cmpCode value for this UpdateResponse.
     * 
     * @return cmpCode   * The code for the company in
     *                             which you attempted to update the element
     * master.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this UpdateResponse.
     * 
     * @param cmpCode   * The code for the company in
     *                             which you attempted to update the element
     * master.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this UpdateResponse.
     * 
     * @return level   * The level of the element
     *                             master that you attempted to update.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this UpdateResponse.
     * 
     * @param level   * The level of the element
     *                             master that you attempted to update.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this UpdateResponse.
     * 
     * @return code   * The code for the element
     *                             master that you attempted to update.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this UpdateResponse.
     * 
     * @param code   * The code for the element
     *                             master that you attempted to update.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the timeStamp value for this UpdateResponse.
     * 
     * @return timeStamp   * The TimeStamp value for this
     *                             element master in the database.
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this UpdateResponse.
     * 
     * @param timeStamp   * The TimeStamp value for this
     *                             element master in the database.
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the taxesTimeStamp value for this UpdateResponse.
     * 
     * @return taxesTimeStamp   * The TimeStamp value for this
     *                             element master in the oas_elmtaxes table.
     */
    public java.lang.Short getTaxesTimeStamp() {
        return taxesTimeStamp;
    }


    /**
     * Sets the taxesTimeStamp value for this UpdateResponse.
     * 
     * @param taxesTimeStamp   * The TimeStamp value for this
     *                             element master in the oas_elmtaxes table.
     */
    public void setTaxesTimeStamp(java.lang.Short taxesTimeStamp) {
        this.taxesTimeStamp = taxesTimeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateResponse)) return false;
        UpdateResponse other = (UpdateResponse) obj;
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
            this.level == other.getLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.taxesTimeStamp==null && other.getTaxesTimeStamp()==null) || 
             (this.taxesTimeStamp!=null &&
              this.taxesTimeStamp.equals(other.getTaxesTimeStamp())));
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
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getTaxesTimeStamp() != null) {
            _hashCode += getTaxesTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">UpdateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multiCompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "MultiCompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", ">>UpdateResponse>MultiCompany"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxesTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster/elementmaster-6.0/webservice", "TaxesTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
