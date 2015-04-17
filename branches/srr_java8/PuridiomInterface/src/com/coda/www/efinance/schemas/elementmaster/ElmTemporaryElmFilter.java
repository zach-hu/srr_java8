/**
 * ElmTemporaryElmFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * The temporary supplier
 *             filter.
 */
public class ElmTemporaryElmFilter  implements java.io.Serializable {
    private boolean selectAllTemporaryElmBanksAndAddresses;

    /* Specifies whether to use the
     *                         TemporaryID field. */
    private boolean useTemporaryID;

    /* Specifies whether to use the
     *                         TraderNameCode field. */
    private boolean useTraderNameCode;

    /* The
     *                         TemporaryID, which identifies a temporary
     *                     supplier. */
    private int temporaryID;

    /* The trader code, which
     *                         identifies a temporary
     *                     supplier. */
    private java.lang.String traderNameCode;

    public ElmTemporaryElmFilter() {
    }

    public ElmTemporaryElmFilter(
           boolean selectAllTemporaryElmBanksAndAddresses,
           boolean useTemporaryID,
           boolean useTraderNameCode,
           int temporaryID,
           java.lang.String traderNameCode) {
           this.selectAllTemporaryElmBanksAndAddresses = selectAllTemporaryElmBanksAndAddresses;
           this.useTemporaryID = useTemporaryID;
           this.useTraderNameCode = useTraderNameCode;
           this.temporaryID = temporaryID;
           this.traderNameCode = traderNameCode;
    }


    /**
     * Gets the selectAllTemporaryElmBanksAndAddresses value for this ElmTemporaryElmFilter.
     * 
     * @return selectAllTemporaryElmBanksAndAddresses
     */
    public boolean isSelectAllTemporaryElmBanksAndAddresses() {
        return selectAllTemporaryElmBanksAndAddresses;
    }


    /**
     * Sets the selectAllTemporaryElmBanksAndAddresses value for this ElmTemporaryElmFilter.
     * 
     * @param selectAllTemporaryElmBanksAndAddresses
     */
    public void setSelectAllTemporaryElmBanksAndAddresses(boolean selectAllTemporaryElmBanksAndAddresses) {
        this.selectAllTemporaryElmBanksAndAddresses = selectAllTemporaryElmBanksAndAddresses;
    }


    /**
     * Gets the useTemporaryID value for this ElmTemporaryElmFilter.
     * 
     * @return useTemporaryID   * Specifies whether to use the
     *                         TemporaryID field.
     */
    public boolean isUseTemporaryID() {
        return useTemporaryID;
    }


    /**
     * Sets the useTemporaryID value for this ElmTemporaryElmFilter.
     * 
     * @param useTemporaryID   * Specifies whether to use the
     *                         TemporaryID field.
     */
    public void setUseTemporaryID(boolean useTemporaryID) {
        this.useTemporaryID = useTemporaryID;
    }


    /**
     * Gets the useTraderNameCode value for this ElmTemporaryElmFilter.
     * 
     * @return useTraderNameCode   * Specifies whether to use the
     *                         TraderNameCode field.
     */
    public boolean isUseTraderNameCode() {
        return useTraderNameCode;
    }


    /**
     * Sets the useTraderNameCode value for this ElmTemporaryElmFilter.
     * 
     * @param useTraderNameCode   * Specifies whether to use the
     *                         TraderNameCode field.
     */
    public void setUseTraderNameCode(boolean useTraderNameCode) {
        this.useTraderNameCode = useTraderNameCode;
    }


    /**
     * Gets the temporaryID value for this ElmTemporaryElmFilter.
     * 
     * @return temporaryID   * The
     *                         TemporaryID, which identifies a temporary
     *                     supplier.
     */
    public int getTemporaryID() {
        return temporaryID;
    }


    /**
     * Sets the temporaryID value for this ElmTemporaryElmFilter.
     * 
     * @param temporaryID   * The
     *                         TemporaryID, which identifies a temporary
     *                     supplier.
     */
    public void setTemporaryID(int temporaryID) {
        this.temporaryID = temporaryID;
    }


    /**
     * Gets the traderNameCode value for this ElmTemporaryElmFilter.
     * 
     * @return traderNameCode   * The trader code, which
     *                         identifies a temporary
     *                     supplier.
     */
    public java.lang.String getTraderNameCode() {
        return traderNameCode;
    }


    /**
     * Sets the traderNameCode value for this ElmTemporaryElmFilter.
     * 
     * @param traderNameCode   * The trader code, which
     *                         identifies a temporary
     *                     supplier.
     */
    public void setTraderNameCode(java.lang.String traderNameCode) {
        this.traderNameCode = traderNameCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmTemporaryElmFilter)) return false;
        ElmTemporaryElmFilter other = (ElmTemporaryElmFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.selectAllTemporaryElmBanksAndAddresses == other.isSelectAllTemporaryElmBanksAndAddresses() &&
            this.useTemporaryID == other.isUseTemporaryID() &&
            this.useTraderNameCode == other.isUseTraderNameCode() &&
            this.temporaryID == other.getTemporaryID() &&
            ((this.traderNameCode==null && other.getTraderNameCode()==null) || 
             (this.traderNameCode!=null &&
              this.traderNameCode.equals(other.getTraderNameCode())));
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
        _hashCode += (isSelectAllTemporaryElmBanksAndAddresses() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUseTemporaryID() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUseTraderNameCode() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTemporaryID();
        if (getTraderNameCode() != null) {
            _hashCode += getTraderNameCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmTemporaryElmFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmTemporaryElmFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectAllTemporaryElmBanksAndAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SelectAllTemporaryElmBanksAndAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useTemporaryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UseTemporaryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useTraderNameCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UseTraderNameCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TemporaryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderNameCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderNameCode"));
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
