/**
 * CurrencyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains information about a currency
 *                 edited by a CalcCurrRequest.
 */
public class CurrencyInfo  extends com.coda.www.efinance.schemas.transaction.CurrencyInfo  implements java.io.Serializable {
    private short scaling;

    /* The rate of exchange
     *                                 between the currency and its parent
     * currency or the home currency, whichever
     *                             applies. */
    private java.math.BigDecimal rate;

    /* The multiply or
     *                                 divide rule for the
     *                             currency. */
    private java.lang.String multDivRule;

    public CurrencyInfo() {
    }

    public CurrencyInfo(
           java.lang.String code,
           java.lang.String[] which,
           java.math.BigDecimal value,
           boolean validRate,
           java.lang.String linkType,
           java.lang.String parentCode,
           short decimalPlaces,
           short scaling,
           java.math.BigDecimal rate,
           java.lang.String multDivRule) {
        super(
            code,
            which,
            value,
            validRate,
            linkType,
            parentCode,
            decimalPlaces);
        this.scaling = scaling;
        this.rate = rate;
        this.multDivRule = multDivRule;
    }


    /**
     * Gets the scaling value for this CurrencyInfo.
     * 
     * @return scaling
     */
    public short getScaling() {
        return scaling;
    }


    /**
     * Sets the scaling value for this CurrencyInfo.
     * 
     * @param scaling
     */
    public void setScaling(short scaling) {
        this.scaling = scaling;
    }


    /**
     * Gets the rate value for this CurrencyInfo.
     * 
     * @return rate   * The rate of exchange
     *                                 between the currency and its parent
     * currency or the home currency, whichever
     *                             applies.
     */
    public java.math.BigDecimal getRate() {
        return rate;
    }


    /**
     * Sets the rate value for this CurrencyInfo.
     * 
     * @param rate   * The rate of exchange
     *                                 between the currency and its parent
     * currency or the home currency, whichever
     *                             applies.
     */
    public void setRate(java.math.BigDecimal rate) {
        this.rate = rate;
    }


    /**
     * Gets the multDivRule value for this CurrencyInfo.
     * 
     * @return multDivRule   * The multiply or
     *                                 divide rule for the
     *                             currency.
     */
    public java.lang.String getMultDivRule() {
        return multDivRule;
    }


    /**
     * Sets the multDivRule value for this CurrencyInfo.
     * 
     * @param multDivRule   * The multiply or
     *                                 divide rule for the
     *                             currency.
     */
    public void setMultDivRule(java.lang.String multDivRule) {
        this.multDivRule = multDivRule;
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
        _equals = super.equals(obj) && 
            this.scaling == other.getScaling() &&
            ((this.rate==null && other.getRate()==null) || 
             (this.rate!=null &&
              this.rate.equals(other.getRate()))) &&
            ((this.multDivRule==null && other.getMultDivRule()==null) || 
             (this.multDivRule!=null &&
              this.multDivRule.equals(other.getMultDivRule())));
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
        _hashCode += getScaling();
        if (getRate() != null) {
            _hashCode += getRate().hashCode();
        }
        if (getMultDivRule() != null) {
            _hashCode += getMultDivRule().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CurrencyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrencyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scaling");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Scaling"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multDivRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MultDivRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
