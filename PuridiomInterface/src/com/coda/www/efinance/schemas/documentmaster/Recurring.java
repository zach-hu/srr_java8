/**
 * Recurring.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds recurring document
 *             settings.
 */
public class Recurring  implements java.io.Serializable {
    private int number;

    /* Specifies that the user can
     *                         change the number of documents to be posted
     * during Input. */
    private boolean numberModifiable;

    /* The
     *                         default interval between recurring
     *                     documents. */
    private short phasing;

    /* The default interval between
     *                         recurring documents. */
    private java.lang.String phasingRule;

    /* Specifies that the user can
     *                         change the phasing parameters during
     *                     Input. */
    private boolean phasingModifiable;

    /* The date rule, which
     *                         specifies how the dates of recurring documents
     * are calculated. */
    private java.lang.String dateRule;

    /* Specifies that the user can
     *                         change the date rule during
     *                     Input. */
    private boolean dateRuleModifiable;

    /* The amount rule, which
     *                         specifies whether the amount on the original
     * document is divided equally between all the
     *                         recurring documents, or whether the value
     * of
     *                         each recurring document is equal to this
     *                         original amount. */
    private java.lang.String amountRule;

    /* Specifies that the user can
     *                         change the amount rule during
     *                     Input. */
    private boolean amountRuleModifiable;

    public Recurring() {
    }

    public Recurring(
           int number,
           boolean numberModifiable,
           short phasing,
           java.lang.String phasingRule,
           boolean phasingModifiable,
           java.lang.String dateRule,
           boolean dateRuleModifiable,
           java.lang.String amountRule,
           boolean amountRuleModifiable) {
           this.number = number;
           this.numberModifiable = numberModifiable;
           this.phasing = phasing;
           this.phasingRule = phasingRule;
           this.phasingModifiable = phasingModifiable;
           this.dateRule = dateRule;
           this.dateRuleModifiable = dateRuleModifiable;
           this.amountRule = amountRule;
           this.amountRuleModifiable = amountRuleModifiable;
    }


    /**
     * Gets the number value for this Recurring.
     * 
     * @return number
     */
    public int getNumber() {
        return number;
    }


    /**
     * Sets the number value for this Recurring.
     * 
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * Gets the numberModifiable value for this Recurring.
     * 
     * @return numberModifiable   * Specifies that the user can
     *                         change the number of documents to be posted
     * during Input.
     */
    public boolean isNumberModifiable() {
        return numberModifiable;
    }


    /**
     * Sets the numberModifiable value for this Recurring.
     * 
     * @param numberModifiable   * Specifies that the user can
     *                         change the number of documents to be posted
     * during Input.
     */
    public void setNumberModifiable(boolean numberModifiable) {
        this.numberModifiable = numberModifiable;
    }


    /**
     * Gets the phasing value for this Recurring.
     * 
     * @return phasing   * The
     *                         default interval between recurring
     *                     documents.
     */
    public short getPhasing() {
        return phasing;
    }


    /**
     * Sets the phasing value for this Recurring.
     * 
     * @param phasing   * The
     *                         default interval between recurring
     *                     documents.
     */
    public void setPhasing(short phasing) {
        this.phasing = phasing;
    }


    /**
     * Gets the phasingRule value for this Recurring.
     * 
     * @return phasingRule   * The default interval between
     *                         recurring documents.
     */
    public java.lang.String getPhasingRule() {
        return phasingRule;
    }


    /**
     * Sets the phasingRule value for this Recurring.
     * 
     * @param phasingRule   * The default interval between
     *                         recurring documents.
     */
    public void setPhasingRule(java.lang.String phasingRule) {
        this.phasingRule = phasingRule;
    }


    /**
     * Gets the phasingModifiable value for this Recurring.
     * 
     * @return phasingModifiable   * Specifies that the user can
     *                         change the phasing parameters during
     *                     Input.
     */
    public boolean isPhasingModifiable() {
        return phasingModifiable;
    }


    /**
     * Sets the phasingModifiable value for this Recurring.
     * 
     * @param phasingModifiable   * Specifies that the user can
     *                         change the phasing parameters during
     *                     Input.
     */
    public void setPhasingModifiable(boolean phasingModifiable) {
        this.phasingModifiable = phasingModifiable;
    }


    /**
     * Gets the dateRule value for this Recurring.
     * 
     * @return dateRule   * The date rule, which
     *                         specifies how the dates of recurring documents
     * are calculated.
     */
    public java.lang.String getDateRule() {
        return dateRule;
    }


    /**
     * Sets the dateRule value for this Recurring.
     * 
     * @param dateRule   * The date rule, which
     *                         specifies how the dates of recurring documents
     * are calculated.
     */
    public void setDateRule(java.lang.String dateRule) {
        this.dateRule = dateRule;
    }


    /**
     * Gets the dateRuleModifiable value for this Recurring.
     * 
     * @return dateRuleModifiable   * Specifies that the user can
     *                         change the date rule during
     *                     Input.
     */
    public boolean isDateRuleModifiable() {
        return dateRuleModifiable;
    }


    /**
     * Sets the dateRuleModifiable value for this Recurring.
     * 
     * @param dateRuleModifiable   * Specifies that the user can
     *                         change the date rule during
     *                     Input.
     */
    public void setDateRuleModifiable(boolean dateRuleModifiable) {
        this.dateRuleModifiable = dateRuleModifiable;
    }


    /**
     * Gets the amountRule value for this Recurring.
     * 
     * @return amountRule   * The amount rule, which
     *                         specifies whether the amount on the original
     * document is divided equally between all the
     *                         recurring documents, or whether the value
     * of
     *                         each recurring document is equal to this
     *                         original amount.
     */
    public java.lang.String getAmountRule() {
        return amountRule;
    }


    /**
     * Sets the amountRule value for this Recurring.
     * 
     * @param amountRule   * The amount rule, which
     *                         specifies whether the amount on the original
     * document is divided equally between all the
     *                         recurring documents, or whether the value
     * of
     *                         each recurring document is equal to this
     *                         original amount.
     */
    public void setAmountRule(java.lang.String amountRule) {
        this.amountRule = amountRule;
    }


    /**
     * Gets the amountRuleModifiable value for this Recurring.
     * 
     * @return amountRuleModifiable   * Specifies that the user can
     *                         change the amount rule during
     *                     Input.
     */
    public boolean isAmountRuleModifiable() {
        return amountRuleModifiable;
    }


    /**
     * Sets the amountRuleModifiable value for this Recurring.
     * 
     * @param amountRuleModifiable   * Specifies that the user can
     *                         change the amount rule during
     *                     Input.
     */
    public void setAmountRuleModifiable(boolean amountRuleModifiable) {
        this.amountRuleModifiable = amountRuleModifiable;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Recurring)) return false;
        Recurring other = (Recurring) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.number == other.getNumber() &&
            this.numberModifiable == other.isNumberModifiable() &&
            this.phasing == other.getPhasing() &&
            ((this.phasingRule==null && other.getPhasingRule()==null) || 
             (this.phasingRule!=null &&
              this.phasingRule.equals(other.getPhasingRule()))) &&
            this.phasingModifiable == other.isPhasingModifiable() &&
            ((this.dateRule==null && other.getDateRule()==null) || 
             (this.dateRule!=null &&
              this.dateRule.equals(other.getDateRule()))) &&
            this.dateRuleModifiable == other.isDateRuleModifiable() &&
            ((this.amountRule==null && other.getAmountRule()==null) || 
             (this.amountRule!=null &&
              this.amountRule.equals(other.getAmountRule()))) &&
            this.amountRuleModifiable == other.isAmountRuleModifiable();
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
        _hashCode += getNumber();
        _hashCode += (isNumberModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getPhasing();
        if (getPhasingRule() != null) {
            _hashCode += getPhasingRule().hashCode();
        }
        _hashCode += (isPhasingModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDateRule() != null) {
            _hashCode += getDateRule().hashCode();
        }
        _hashCode += (isDateRuleModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAmountRule() != null) {
            _hashCode += getAmountRule().hashCode();
        }
        _hashCode += (isAmountRuleModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Recurring.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Recurring"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberModifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "NumberModifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phasing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Phasing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phasingRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PhasingRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phasingModifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PhasingModifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DateRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateRuleModifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DateRuleModifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AmountRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountRuleModifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AmountRuleModifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
