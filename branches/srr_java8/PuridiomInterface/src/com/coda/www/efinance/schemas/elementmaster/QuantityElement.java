/**
 * QuantityElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Holds details of one of the
 *                 element's quantities.
 */
public class QuantityElement  implements java.io.Serializable {
    private java.lang.Boolean used;

    /* The title for this
     *                     quantity. */
    private java.lang.String title;

    /* Specifies whether this
     *                         quantity is mandatory. */
    private java.lang.Boolean mand;

    /* A balance master code for
     *                         this quantity. This field may be
     *                     blank. */
    private java.lang.String balCode;

    /* The number of decimal places
     *                         for this quantity when there is no balance
     * code. */
    private java.lang.Short decimals;

    public QuantityElement() {
    }

    public QuantityElement(
           java.lang.Boolean used,
           java.lang.String title,
           java.lang.Boolean mand,
           java.lang.String balCode,
           java.lang.Short decimals) {
           this.used = used;
           this.title = title;
           this.mand = mand;
           this.balCode = balCode;
           this.decimals = decimals;
    }


    /**
     * Gets the used value for this QuantityElement.
     * 
     * @return used
     */
    public java.lang.Boolean getUsed() {
        return used;
    }


    /**
     * Sets the used value for this QuantityElement.
     * 
     * @param used
     */
    public void setUsed(java.lang.Boolean used) {
        this.used = used;
    }


    /**
     * Gets the title value for this QuantityElement.
     * 
     * @return title   * The title for this
     *                     quantity.
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this QuantityElement.
     * 
     * @param title   * The title for this
     *                     quantity.
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the mand value for this QuantityElement.
     * 
     * @return mand   * Specifies whether this
     *                         quantity is mandatory.
     */
    public java.lang.Boolean getMand() {
        return mand;
    }


    /**
     * Sets the mand value for this QuantityElement.
     * 
     * @param mand   * Specifies whether this
     *                         quantity is mandatory.
     */
    public void setMand(java.lang.Boolean mand) {
        this.mand = mand;
    }


    /**
     * Gets the balCode value for this QuantityElement.
     * 
     * @return balCode   * A balance master code for
     *                         this quantity. This field may be
     *                     blank.
     */
    public java.lang.String getBalCode() {
        return balCode;
    }


    /**
     * Sets the balCode value for this QuantityElement.
     * 
     * @param balCode   * A balance master code for
     *                         this quantity. This field may be
     *                     blank.
     */
    public void setBalCode(java.lang.String balCode) {
        this.balCode = balCode;
    }


    /**
     * Gets the decimals value for this QuantityElement.
     * 
     * @return decimals   * The number of decimal places
     *                         for this quantity when there is no balance
     * code.
     */
    public java.lang.Short getDecimals() {
        return decimals;
    }


    /**
     * Sets the decimals value for this QuantityElement.
     * 
     * @param decimals   * The number of decimal places
     *                         for this quantity when there is no balance
     * code.
     */
    public void setDecimals(java.lang.Short decimals) {
        this.decimals = decimals;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QuantityElement)) return false;
        QuantityElement other = (QuantityElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.used==null && other.getUsed()==null) || 
             (this.used!=null &&
              this.used.equals(other.getUsed()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.mand==null && other.getMand()==null) || 
             (this.mand!=null &&
              this.mand.equals(other.getMand()))) &&
            ((this.balCode==null && other.getBalCode()==null) || 
             (this.balCode!=null &&
              this.balCode.equals(other.getBalCode()))) &&
            ((this.decimals==null && other.getDecimals()==null) || 
             (this.decimals!=null &&
              this.decimals.equals(other.getDecimals())));
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
        if (getUsed() != null) {
            _hashCode += getUsed().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getMand() != null) {
            _hashCode += getMand().hashCode();
        }
        if (getBalCode() != null) {
            _hashCode += getBalCode().hashCode();
        }
        if (getDecimals() != null) {
            _hashCode += getDecimals().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QuantityElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("used");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Used"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decimals");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Decimals"));
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
