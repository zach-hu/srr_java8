/**
 * HardenedDatesResults.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains the due date, value date and
 *                 discount dates returned in a
 *             GetHardenedDatesResponse.
 */
public class HardenedDatesResults  implements java.io.Serializable {
    private java.util.Calendar dueDate;

    /* The
     *                         hard date when the document will
     *                     clear. */
    private java.util.Calendar valueDate;

    /* The
     *                         hard date when the element's first discount
     * rate comes into effect. */
    private java.util.Calendar discountDate1;

    /* The hard date when the
     *                         element's second discount rate comes into
     *                     effect. */
    private java.util.Calendar discountDate2;

    /* The hard date when the
     *                         element's third discount rate comes into
     *                     effect. */
    private java.util.Calendar discountDate3;

    /* The hard date when the
     *                         element's fourth discount rate comes into
     *                     effect. */
    private java.util.Calendar discountDate4;

    /* The hard date when the
     *                         element's fifth discount rate comes into
     *                     effect. */
    private java.util.Calendar discountDate5;

    public HardenedDatesResults() {
    }

    public HardenedDatesResults(
           java.util.Calendar dueDate,
           java.util.Calendar valueDate,
           java.util.Calendar discountDate1,
           java.util.Calendar discountDate2,
           java.util.Calendar discountDate3,
           java.util.Calendar discountDate4,
           java.util.Calendar discountDate5) {
           this.dueDate = dueDate;
           this.valueDate = valueDate;
           this.discountDate1 = discountDate1;
           this.discountDate2 = discountDate2;
           this.discountDate3 = discountDate3;
           this.discountDate4 = discountDate4;
           this.discountDate5 = discountDate5;
    }


    /**
     * Gets the dueDate value for this HardenedDatesResults.
     * 
     * @return dueDate
     */
    public java.util.Calendar getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this HardenedDatesResults.
     * 
     * @param dueDate
     */
    public void setDueDate(java.util.Calendar dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the valueDate value for this HardenedDatesResults.
     * 
     * @return valueDate   * The
     *                         hard date when the document will
     *                     clear.
     */
    public java.util.Calendar getValueDate() {
        return valueDate;
    }


    /**
     * Sets the valueDate value for this HardenedDatesResults.
     * 
     * @param valueDate   * The
     *                         hard date when the document will
     *                     clear.
     */
    public void setValueDate(java.util.Calendar valueDate) {
        this.valueDate = valueDate;
    }


    /**
     * Gets the discountDate1 value for this HardenedDatesResults.
     * 
     * @return discountDate1   * The
     *                         hard date when the element's first discount
     * rate comes into effect.
     */
    public java.util.Calendar getDiscountDate1() {
        return discountDate1;
    }


    /**
     * Sets the discountDate1 value for this HardenedDatesResults.
     * 
     * @param discountDate1   * The
     *                         hard date when the element's first discount
     * rate comes into effect.
     */
    public void setDiscountDate1(java.util.Calendar discountDate1) {
        this.discountDate1 = discountDate1;
    }


    /**
     * Gets the discountDate2 value for this HardenedDatesResults.
     * 
     * @return discountDate2   * The hard date when the
     *                         element's second discount rate comes into
     *                     effect.
     */
    public java.util.Calendar getDiscountDate2() {
        return discountDate2;
    }


    /**
     * Sets the discountDate2 value for this HardenedDatesResults.
     * 
     * @param discountDate2   * The hard date when the
     *                         element's second discount rate comes into
     *                     effect.
     */
    public void setDiscountDate2(java.util.Calendar discountDate2) {
        this.discountDate2 = discountDate2;
    }


    /**
     * Gets the discountDate3 value for this HardenedDatesResults.
     * 
     * @return discountDate3   * The hard date when the
     *                         element's third discount rate comes into
     *                     effect.
     */
    public java.util.Calendar getDiscountDate3() {
        return discountDate3;
    }


    /**
     * Sets the discountDate3 value for this HardenedDatesResults.
     * 
     * @param discountDate3   * The hard date when the
     *                         element's third discount rate comes into
     *                     effect.
     */
    public void setDiscountDate3(java.util.Calendar discountDate3) {
        this.discountDate3 = discountDate3;
    }


    /**
     * Gets the discountDate4 value for this HardenedDatesResults.
     * 
     * @return discountDate4   * The hard date when the
     *                         element's fourth discount rate comes into
     *                     effect.
     */
    public java.util.Calendar getDiscountDate4() {
        return discountDate4;
    }


    /**
     * Sets the discountDate4 value for this HardenedDatesResults.
     * 
     * @param discountDate4   * The hard date when the
     *                         element's fourth discount rate comes into
     *                     effect.
     */
    public void setDiscountDate4(java.util.Calendar discountDate4) {
        this.discountDate4 = discountDate4;
    }


    /**
     * Gets the discountDate5 value for this HardenedDatesResults.
     * 
     * @return discountDate5   * The hard date when the
     *                         element's fifth discount rate comes into
     *                     effect.
     */
    public java.util.Calendar getDiscountDate5() {
        return discountDate5;
    }


    /**
     * Sets the discountDate5 value for this HardenedDatesResults.
     * 
     * @param discountDate5   * The hard date when the
     *                         element's fifth discount rate comes into
     *                     effect.
     */
    public void setDiscountDate5(java.util.Calendar discountDate5) {
        this.discountDate5 = discountDate5;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HardenedDatesResults)) return false;
        HardenedDatesResults other = (HardenedDatesResults) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            ((this.valueDate==null && other.getValueDate()==null) || 
             (this.valueDate!=null &&
              this.valueDate.equals(other.getValueDate()))) &&
            ((this.discountDate1==null && other.getDiscountDate1()==null) || 
             (this.discountDate1!=null &&
              this.discountDate1.equals(other.getDiscountDate1()))) &&
            ((this.discountDate2==null && other.getDiscountDate2()==null) || 
             (this.discountDate2!=null &&
              this.discountDate2.equals(other.getDiscountDate2()))) &&
            ((this.discountDate3==null && other.getDiscountDate3()==null) || 
             (this.discountDate3!=null &&
              this.discountDate3.equals(other.getDiscountDate3()))) &&
            ((this.discountDate4==null && other.getDiscountDate4()==null) || 
             (this.discountDate4!=null &&
              this.discountDate4.equals(other.getDiscountDate4()))) &&
            ((this.discountDate5==null && other.getDiscountDate5()==null) || 
             (this.discountDate5!=null &&
              this.discountDate5.equals(other.getDiscountDate5())));
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
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        if (getValueDate() != null) {
            _hashCode += getValueDate().hashCode();
        }
        if (getDiscountDate1() != null) {
            _hashCode += getDiscountDate1().hashCode();
        }
        if (getDiscountDate2() != null) {
            _hashCode += getDiscountDate2().hashCode();
        }
        if (getDiscountDate3() != null) {
            _hashCode += getDiscountDate3().hashCode();
        }
        if (getDiscountDate4() != null) {
            _hashCode += getDiscountDate4().hashCode();
        }
        if (getDiscountDate5() != null) {
            _hashCode += getDiscountDate5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HardenedDatesResults.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HardenedDatesResults"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ValueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountDate1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DiscountDate1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountDate2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DiscountDate2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountDate3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DiscountDate3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountDate4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DiscountDate4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountDate5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DiscountDate5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
