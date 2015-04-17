/**
 * Currency.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class Currency  implements java.io.Serializable {
    /* Specifies whether the
     *                         document currency is entered by the user during
     * Input, a fixed, protected value, or a modifiable
     *                     value. */
    private java.lang.String rule;

    /* The
     *                         default document currency. */
    private java.lang.String _default;

    /* Specifies whether users can
     *                         apply a change to the exchange rate to the
     * whole
     *                         document or just to a document line, subject
     * to
     *                         the settings on their capability master, or
     * whether this ability is controlled by a setting
     *                         elsewhere (on the currency master or company
     * master). */
    private java.lang.String rateControl;

    public Currency() {
    }

    public Currency(
           java.lang.String rule,
           java.lang.String _default,
           java.lang.String rateControl) {
           this.rule = rule;
           this._default = _default;
           this.rateControl = rateControl;
    }


    /**
     * Gets the rule value for this Currency.
     * 
     * @return rule   * Specifies whether the
     *                         document currency is entered by the user during
     * Input, a fixed, protected value, or a modifiable
     *                     value.
     */
    public java.lang.String getRule() {
        return rule;
    }


    /**
     * Sets the rule value for this Currency.
     * 
     * @param rule   * Specifies whether the
     *                         document currency is entered by the user during
     * Input, a fixed, protected value, or a modifiable
     *                     value.
     */
    public void setRule(java.lang.String rule) {
        this.rule = rule;
    }


    /**
     * Gets the _default value for this Currency.
     * 
     * @return _default   * The
     *                         default document currency.
     */
    public java.lang.String get_default() {
        return _default;
    }


    /**
     * Sets the _default value for this Currency.
     * 
     * @param _default   * The
     *                         default document currency.
     */
    public void set_default(java.lang.String _default) {
        this._default = _default;
    }


    /**
     * Gets the rateControl value for this Currency.
     * 
     * @return rateControl   * Specifies whether users can
     *                         apply a change to the exchange rate to the
     * whole
     *                         document or just to a document line, subject
     * to
     *                         the settings on their capability master, or
     * whether this ability is controlled by a setting
     *                         elsewhere (on the currency master or company
     * master).
     */
    public java.lang.String getRateControl() {
        return rateControl;
    }


    /**
     * Sets the rateControl value for this Currency.
     * 
     * @param rateControl   * Specifies whether users can
     *                         apply a change to the exchange rate to the
     * whole
     *                         document or just to a document line, subject
     * to
     *                         the settings on their capability master, or
     * whether this ability is controlled by a setting
     *                         elsewhere (on the currency master or company
     * master).
     */
    public void setRateControl(java.lang.String rateControl) {
        this.rateControl = rateControl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Currency)) return false;
        Currency other = (Currency) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rule==null && other.getRule()==null) || 
             (this.rule!=null &&
              this.rule.equals(other.getRule()))) &&
            ((this._default==null && other.get_default()==null) || 
             (this._default!=null &&
              this._default.equals(other.get_default()))) &&
            ((this.rateControl==null && other.getRateControl()==null) || 
             (this.rateControl!=null &&
              this.rateControl.equals(other.getRateControl())));
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
        if (getRule() != null) {
            _hashCode += getRule().hashCode();
        }
        if (get_default() != null) {
            _hashCode += get_default().hashCode();
        }
        if (getRateControl() != null) {
            _hashCode += getRateControl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Currency.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Currency"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Rule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_default");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Default"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rateControl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "RateControl"));
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
