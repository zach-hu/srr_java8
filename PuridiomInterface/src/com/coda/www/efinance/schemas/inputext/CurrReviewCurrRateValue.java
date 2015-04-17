/**
 * CurrReviewCurrRateValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains details about a
 *                 currency that applies to all lines in the
 *             document.
 */
public class CurrReviewCurrRateValue  extends com.coda.www.efinance.schemas.common.CurrRateValue  implements java.io.Serializable {
    private java.lang.String[] which;

    public CurrReviewCurrRateValue() {
    }

    public CurrReviewCurrRateValue(
           java.lang.String currCode,
           java.math.BigDecimal value,
           java.math.BigDecimal rate,
           java.lang.String[] which) {
        super(
            currCode,
            value,
            rate);
        this.which = which;
    }


    /**
     * Gets the which value for this CurrReviewCurrRateValue.
     * 
     * @return which
     */
    public java.lang.String[] getWhich() {
        return which;
    }


    /**
     * Sets the which value for this CurrReviewCurrRateValue.
     * 
     * @param which
     */
    public void setWhich(java.lang.String[] which) {
        this.which = which;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CurrReviewCurrRateValue)) return false;
        CurrReviewCurrRateValue other = (CurrReviewCurrRateValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.which==null && other.getWhich()==null) || 
             (this.which!=null &&
              java.util.Arrays.equals(this.which, other.getWhich())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CurrReviewCurrRateValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("which");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Which"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWhichCur"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Curr"));
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
