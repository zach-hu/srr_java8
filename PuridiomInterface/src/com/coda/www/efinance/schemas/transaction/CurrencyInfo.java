/**
 * CurrencyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class CurrencyInfo  implements java.io.Serializable {
    private java.lang.String code;

    private java.lang.String[] which;

    private java.math.BigDecimal value;

    private boolean validRate;

    private java.lang.String linkType;

    private java.lang.String parentCode;

    private short decimalPlaces;

    public CurrencyInfo() {
    }

    public CurrencyInfo(
           java.lang.String code,
           java.lang.String[] which,
           java.math.BigDecimal value,
           boolean validRate,
           java.lang.String linkType,
           java.lang.String parentCode,
           short decimalPlaces) {
           this.code = code;
           this.which = which;
           this.value = value;
           this.validRate = validRate;
           this.linkType = linkType;
           this.parentCode = parentCode;
           this.decimalPlaces = decimalPlaces;
    }


    /**
     * Gets the code value for this CurrencyInfo.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this CurrencyInfo.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the which value for this CurrencyInfo.
     * 
     * @return which
     */
    public java.lang.String[] getWhich() {
        return which;
    }


    /**
     * Sets the which value for this CurrencyInfo.
     * 
     * @param which
     */
    public void setWhich(java.lang.String[] which) {
        this.which = which;
    }


    /**
     * Gets the value value for this CurrencyInfo.
     * 
     * @return value
     */
    public java.math.BigDecimal getValue() {
        return value;
    }


    /**
     * Sets the value value for this CurrencyInfo.
     * 
     * @param value
     */
    public void setValue(java.math.BigDecimal value) {
        this.value = value;
    }


    /**
     * Gets the validRate value for this CurrencyInfo.
     * 
     * @return validRate
     */
    public boolean isValidRate() {
        return validRate;
    }


    /**
     * Sets the validRate value for this CurrencyInfo.
     * 
     * @param validRate
     */
    public void setValidRate(boolean validRate) {
        this.validRate = validRate;
    }


    /**
     * Gets the linkType value for this CurrencyInfo.
     * 
     * @return linkType
     */
    public java.lang.String getLinkType() {
        return linkType;
    }


    /**
     * Sets the linkType value for this CurrencyInfo.
     * 
     * @param linkType
     */
    public void setLinkType(java.lang.String linkType) {
        this.linkType = linkType;
    }


    /**
     * Gets the parentCode value for this CurrencyInfo.
     * 
     * @return parentCode
     */
    public java.lang.String getParentCode() {
        return parentCode;
    }


    /**
     * Sets the parentCode value for this CurrencyInfo.
     * 
     * @param parentCode
     */
    public void setParentCode(java.lang.String parentCode) {
        this.parentCode = parentCode;
    }


    /**
     * Gets the decimalPlaces value for this CurrencyInfo.
     * 
     * @return decimalPlaces
     */
    public short getDecimalPlaces() {
        return decimalPlaces;
    }


    /**
     * Sets the decimalPlaces value for this CurrencyInfo.
     * 
     * @param decimalPlaces
     */
    public void setDecimalPlaces(short decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CurrencyInfo)) return false;
        CurrencyInfo other = (CurrencyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.which==null && other.getWhich()==null) || 
             (this.which!=null &&
              java.util.Arrays.equals(this.which, other.getWhich()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            this.validRate == other.isValidRate() &&
            ((this.linkType==null && other.getLinkType()==null) || 
             (this.linkType!=null &&
              this.linkType.equals(other.getLinkType()))) &&
            ((this.parentCode==null && other.getParentCode()==null) || 
             (this.parentCode!=null &&
              this.parentCode.equals(other.getParentCode()))) &&
            this.decimalPlaces == other.getDecimalPlaces();
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getWhich() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWhich());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWhich(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        _hashCode += (isValidRate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLinkType() != null) {
            _hashCode += getLinkType().hashCode();
        }
        if (getParentCode() != null) {
            _hashCode += getParentCode().hashCode();
        }
        _hashCode += getDecimalPlaces();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CurrencyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CurrencyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("which");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Which"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWhichCur"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Curr"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ValidRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LinkType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decimalPlaces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DecimalPlaces"));
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
