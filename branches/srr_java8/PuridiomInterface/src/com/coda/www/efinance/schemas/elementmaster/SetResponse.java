/**
 * SetResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public class SetResponse  extends com.coda.www.efinance.schemas.common.MultiCompanyResponse  implements java.io.Serializable {
    /* The code for the
     *                                 company in which you attempted to
     * set
     *                                 some of the element master's
     *                             values. */
    private java.lang.String cmpCode;

    /* The level of the
     *                                 element master whose values you
     *                                 attempted to set. */
    private short level;

    /* The code for the
     *                                 element master whose values you
     *                                 attempted to set. */
    private java.lang.String code;

    /* The TimeStamp value
     *                                 for this element master in the
     *                             database. */
    private java.lang.Short timeStamp;

    /* The TimeStamp value
     *                                 for this element master in the
     *                                 oas_elmtaxes table. */
    private java.lang.Short taxesTimeStamp;

    public SetResponse() {
    }

    public SetResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String aborted,
           com.coda.www.efinance.schemas.common.Warning warning,
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           java.lang.Short timeStamp,
           java.lang.Short taxesTimeStamp) {
        super(
            status,
            transactioncoordinator,
            reason,
            aborted,
            warning);
        this.cmpCode = cmpCode;
        this.level = level;
        this.code = code;
        this.timeStamp = timeStamp;
        this.taxesTimeStamp = taxesTimeStamp;
    }


    /**
     * Gets the cmpCode value for this SetResponse.
     * 
     * @return cmpCode   * The code for the
     *                                 company in which you attempted to
     * set
     *                                 some of the element master's
     *                             values.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this SetResponse.
     * 
     * @param cmpCode   * The code for the
     *                                 company in which you attempted to
     * set
     *                                 some of the element master's
     *                             values.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this SetResponse.
     * 
     * @return level   * The level of the
     *                                 element master whose values you
     *                                 attempted to set.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this SetResponse.
     * 
     * @param level   * The level of the
     *                                 element master whose values you
     *                                 attempted to set.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this SetResponse.
     * 
     * @return code   * The code for the
     *                                 element master whose values you
     *                                 attempted to set.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this SetResponse.
     * 
     * @param code   * The code for the
     *                                 element master whose values you
     *                                 attempted to set.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the timeStamp value for this SetResponse.
     * 
     * @return timeStamp   * The TimeStamp value
     *                                 for this element master in the
     *                             database.
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this SetResponse.
     * 
     * @param timeStamp   * The TimeStamp value
     *                                 for this element master in the
     *                             database.
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the taxesTimeStamp value for this SetResponse.
     * 
     * @return taxesTimeStamp   * The TimeStamp value
     *                                 for this element master in the
     *                                 oas_elmtaxes table.
     */
    public java.lang.Short getTaxesTimeStamp() {
        return taxesTimeStamp;
    }


    /**
     * Sets the taxesTimeStamp value for this SetResponse.
     * 
     * @param taxesTimeStamp   * The TimeStamp value
     *                                 for this element master in the
     *                                 oas_elmtaxes table.
     */
    public void setTaxesTimeStamp(java.lang.Short taxesTimeStamp) {
        this.taxesTimeStamp = taxesTimeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetResponse)) return false;
        SetResponse other = (SetResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
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
        int _hashCode = super.hashCode();
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
        new org.apache.axis.description.TypeDesc(SetResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SetResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxesTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxesTimeStamp"));
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
