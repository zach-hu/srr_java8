/**
 * DocumentWideData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains details that
 *                 apply to all lines in the document.
 */
public class DocumentWideData  implements java.io.Serializable {
    private java.util.Calendar dueDate;

    /* The
     *                         date when the document will
     *                     clear. */
    private java.util.Calendar valueDate;

    /* The
     *                         value of External Reference
     *                     1. */
    private java.lang.String extRef1;

    /* The
     *                         value of External Reference
     *                     2. */
    private java.lang.String extRef2;

    /* The
     *                         value of External Reference
     *                     3. */
    private java.lang.String extRef3;

    /* The
     *                         value of External Reference
     *                     4. */
    private java.lang.String extRef4;

    /* The
     *                         value of External Reference
     *                     5. */
    private java.lang.String extRef5;

    /* The
     *                         value of External Reference
     *                     6. */
    private java.lang.String extRef6;

    public DocumentWideData() {
    }

    public DocumentWideData(
           java.util.Calendar dueDate,
           java.util.Calendar valueDate,
           java.lang.String extRef1,
           java.lang.String extRef2,
           java.lang.String extRef3,
           java.lang.String extRef4,
           java.lang.String extRef5,
           java.lang.String extRef6) {
           this.dueDate = dueDate;
           this.valueDate = valueDate;
           this.extRef1 = extRef1;
           this.extRef2 = extRef2;
           this.extRef3 = extRef3;
           this.extRef4 = extRef4;
           this.extRef5 = extRef5;
           this.extRef6 = extRef6;
    }


    /**
     * Gets the dueDate value for this DocumentWideData.
     * 
     * @return dueDate
     */
    public java.util.Calendar getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this DocumentWideData.
     * 
     * @param dueDate
     */
    public void setDueDate(java.util.Calendar dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the valueDate value for this DocumentWideData.
     * 
     * @return valueDate   * The
     *                         date when the document will
     *                     clear.
     */
    public java.util.Calendar getValueDate() {
        return valueDate;
    }


    /**
     * Sets the valueDate value for this DocumentWideData.
     * 
     * @param valueDate   * The
     *                         date when the document will
     *                     clear.
     */
    public void setValueDate(java.util.Calendar valueDate) {
        this.valueDate = valueDate;
    }


    /**
     * Gets the extRef1 value for this DocumentWideData.
     * 
     * @return extRef1   * The
     *                         value of External Reference
     *                     1.
     */
    public java.lang.String getExtRef1() {
        return extRef1;
    }


    /**
     * Sets the extRef1 value for this DocumentWideData.
     * 
     * @param extRef1   * The
     *                         value of External Reference
     *                     1.
     */
    public void setExtRef1(java.lang.String extRef1) {
        this.extRef1 = extRef1;
    }


    /**
     * Gets the extRef2 value for this DocumentWideData.
     * 
     * @return extRef2   * The
     *                         value of External Reference
     *                     2.
     */
    public java.lang.String getExtRef2() {
        return extRef2;
    }


    /**
     * Sets the extRef2 value for this DocumentWideData.
     * 
     * @param extRef2   * The
     *                         value of External Reference
     *                     2.
     */
    public void setExtRef2(java.lang.String extRef2) {
        this.extRef2 = extRef2;
    }


    /**
     * Gets the extRef3 value for this DocumentWideData.
     * 
     * @return extRef3   * The
     *                         value of External Reference
     *                     3.
     */
    public java.lang.String getExtRef3() {
        return extRef3;
    }


    /**
     * Sets the extRef3 value for this DocumentWideData.
     * 
     * @param extRef3   * The
     *                         value of External Reference
     *                     3.
     */
    public void setExtRef3(java.lang.String extRef3) {
        this.extRef3 = extRef3;
    }


    /**
     * Gets the extRef4 value for this DocumentWideData.
     * 
     * @return extRef4   * The
     *                         value of External Reference
     *                     4.
     */
    public java.lang.String getExtRef4() {
        return extRef4;
    }


    /**
     * Sets the extRef4 value for this DocumentWideData.
     * 
     * @param extRef4   * The
     *                         value of External Reference
     *                     4.
     */
    public void setExtRef4(java.lang.String extRef4) {
        this.extRef4 = extRef4;
    }


    /**
     * Gets the extRef5 value for this DocumentWideData.
     * 
     * @return extRef5   * The
     *                         value of External Reference
     *                     5.
     */
    public java.lang.String getExtRef5() {
        return extRef5;
    }


    /**
     * Sets the extRef5 value for this DocumentWideData.
     * 
     * @param extRef5   * The
     *                         value of External Reference
     *                     5.
     */
    public void setExtRef5(java.lang.String extRef5) {
        this.extRef5 = extRef5;
    }


    /**
     * Gets the extRef6 value for this DocumentWideData.
     * 
     * @return extRef6   * The
     *                         value of External Reference
     *                     6.
     */
    public java.lang.String getExtRef6() {
        return extRef6;
    }


    /**
     * Sets the extRef6 value for this DocumentWideData.
     * 
     * @param extRef6   * The
     *                         value of External Reference
     *                     6.
     */
    public void setExtRef6(java.lang.String extRef6) {
        this.extRef6 = extRef6;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentWideData)) return false;
        DocumentWideData other = (DocumentWideData) obj;
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
            ((this.extRef1==null && other.getExtRef1()==null) || 
             (this.extRef1!=null &&
              this.extRef1.equals(other.getExtRef1()))) &&
            ((this.extRef2==null && other.getExtRef2()==null) || 
             (this.extRef2!=null &&
              this.extRef2.equals(other.getExtRef2()))) &&
            ((this.extRef3==null && other.getExtRef3()==null) || 
             (this.extRef3!=null &&
              this.extRef3.equals(other.getExtRef3()))) &&
            ((this.extRef4==null && other.getExtRef4()==null) || 
             (this.extRef4!=null &&
              this.extRef4.equals(other.getExtRef4()))) &&
            ((this.extRef5==null && other.getExtRef5()==null) || 
             (this.extRef5!=null &&
              this.extRef5.equals(other.getExtRef5()))) &&
            ((this.extRef6==null && other.getExtRef6()==null) || 
             (this.extRef6!=null &&
              this.extRef6.equals(other.getExtRef6())));
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
        if (getExtRef1() != null) {
            _hashCode += getExtRef1().hashCode();
        }
        if (getExtRef2() != null) {
            _hashCode += getExtRef2().hashCode();
        }
        if (getExtRef3() != null) {
            _hashCode += getExtRef3().hashCode();
        }
        if (getExtRef4() != null) {
            _hashCode += getExtRef4().hashCode();
        }
        if (getExtRef5() != null) {
            _hashCode += getExtRef5().hashCode();
        }
        if (getExtRef6() != null) {
            _hashCode += getExtRef6().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentWideData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData"));
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
        elemField.setFieldName("extRef1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtRef6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
