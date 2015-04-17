/**
 * Tax.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds tax information for the
 *                 specified trader.
 */
public class Tax  implements java.io.Serializable {
    private java.lang.String VAT;

    /* The
     *                         code of the 1099 tax master that applies to
     * the
     *                         specified trader. */
    private java.lang.String ten99Code;

    /* The Federal tax ID of the
     *                         specified trader. The format of a Federal
     * tax ID
     *                         is NN-NNNNNNN, where N is any digit. Typically,
     * a Federal tax ID is used by suppliers who have
     *                     employees. */
    private java.lang.String federalTax;

    /* The
     *                         Social security number of the specified trader.
     * The format of a Social security number is
     *                         NNN-NN-NNNN, where N is any digit. Typically,
     * a
     *                         Social security number is used by suppliers
     * who
     *                         do not have employees. */
    private java.lang.String socialSecurity;

    /* Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent. */
    private java.lang.Boolean secondTIN;

    public Tax() {
    }

    public Tax(
           java.lang.String VAT,
           java.lang.String ten99Code,
           java.lang.String federalTax,
           java.lang.String socialSecurity,
           java.lang.Boolean secondTIN) {
           this.VAT = VAT;
           this.ten99Code = ten99Code;
           this.federalTax = federalTax;
           this.socialSecurity = socialSecurity;
           this.secondTIN = secondTIN;
    }


    /**
     * Gets the VAT value for this Tax.
     * 
     * @return VAT
     */
    public java.lang.String getVAT() {
        return VAT;
    }


    /**
     * Sets the VAT value for this Tax.
     * 
     * @param VAT
     */
    public void setVAT(java.lang.String VAT) {
        this.VAT = VAT;
    }


    /**
     * Gets the ten99Code value for this Tax.
     * 
     * @return ten99Code   * The
     *                         code of the 1099 tax master that applies to
     * the
     *                         specified trader.
     */
    public java.lang.String getTen99Code() {
        return ten99Code;
    }


    /**
     * Sets the ten99Code value for this Tax.
     * 
     * @param ten99Code   * The
     *                         code of the 1099 tax master that applies to
     * the
     *                         specified trader.
     */
    public void setTen99Code(java.lang.String ten99Code) {
        this.ten99Code = ten99Code;
    }


    /**
     * Gets the federalTax value for this Tax.
     * 
     * @return federalTax   * The Federal tax ID of the
     *                         specified trader. The format of a Federal
     * tax ID
     *                         is NN-NNNNNNN, where N is any digit. Typically,
     * a Federal tax ID is used by suppliers who have
     *                     employees.
     */
    public java.lang.String getFederalTax() {
        return federalTax;
    }


    /**
     * Sets the federalTax value for this Tax.
     * 
     * @param federalTax   * The Federal tax ID of the
     *                         specified trader. The format of a Federal
     * tax ID
     *                         is NN-NNNNNNN, where N is any digit. Typically,
     * a Federal tax ID is used by suppliers who have
     *                     employees.
     */
    public void setFederalTax(java.lang.String federalTax) {
        this.federalTax = federalTax;
    }


    /**
     * Gets the socialSecurity value for this Tax.
     * 
     * @return socialSecurity   * The
     *                         Social security number of the specified trader.
     * The format of a Social security number is
     *                         NNN-NN-NNNN, where N is any digit. Typically,
     * a
     *                         Social security number is used by suppliers
     * who
     *                         do not have employees.
     */
    public java.lang.String getSocialSecurity() {
        return socialSecurity;
    }


    /**
     * Sets the socialSecurity value for this Tax.
     * 
     * @param socialSecurity   * The
     *                         Social security number of the specified trader.
     * The format of a Social security number is
     *                         NNN-NN-NNNN, where N is any digit. Typically,
     * a
     *                         Social security number is used by suppliers
     * who
     *                         do not have employees.
     */
    public void setSocialSecurity(java.lang.String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }


    /**
     * Gets the secondTIN value for this Tax.
     * 
     * @return secondTIN   * Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent.
     */
    public java.lang.Boolean getSecondTIN() {
        return secondTIN;
    }


    /**
     * Sets the secondTIN value for this Tax.
     * 
     * @param secondTIN   * Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent.
     */
    public void setSecondTIN(java.lang.Boolean secondTIN) {
        this.secondTIN = secondTIN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tax)) return false;
        Tax other = (Tax) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.VAT==null && other.getVAT()==null) || 
             (this.VAT!=null &&
              this.VAT.equals(other.getVAT()))) &&
            ((this.ten99Code==null && other.getTen99Code()==null) || 
             (this.ten99Code!=null &&
              this.ten99Code.equals(other.getTen99Code()))) &&
            ((this.federalTax==null && other.getFederalTax()==null) || 
             (this.federalTax!=null &&
              this.federalTax.equals(other.getFederalTax()))) &&
            ((this.socialSecurity==null && other.getSocialSecurity()==null) || 
             (this.socialSecurity!=null &&
              this.socialSecurity.equals(other.getSocialSecurity()))) &&
            ((this.secondTIN==null && other.getSecondTIN()==null) || 
             (this.secondTIN!=null &&
              this.secondTIN.equals(other.getSecondTIN())));
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
        if (getVAT() != null) {
            _hashCode += getVAT().hashCode();
        }
        if (getTen99Code() != null) {
            _hashCode += getTen99Code().hashCode();
        }
        if (getFederalTax() != null) {
            _hashCode += getFederalTax().hashCode();
        }
        if (getSocialSecurity() != null) {
            _hashCode += getSocialSecurity().hashCode();
        }
        if (getSecondTIN() != null) {
            _hashCode += getSecondTIN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tax.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tax"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VAT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten99Code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Ten99Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("federalTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FederalTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("socialSecurity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SocialSecurity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SecondTIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
