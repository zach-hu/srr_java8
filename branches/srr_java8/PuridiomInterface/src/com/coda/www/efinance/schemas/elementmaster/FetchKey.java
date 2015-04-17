/**
 * FetchKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This is an element key used when
 *                 fetching element information from the database. You
 * specify the element and the information that you want to
 *             retrieve.
 */
public class FetchKey  extends com.coda.www.efinance.schemas.elementmaster.ElmFullKey  implements java.io.Serializable {
    private java.lang.String[] vocabList;

    public FetchKey() {
    }

    public FetchKey(
           java.lang.String cmpCode,
           short level,
           java.lang.String code,
           java.lang.String[] vocabList) {
        super(
            cmpCode,
            level,
            code);
        this.vocabList = vocabList;
    }


    /**
     * Gets the vocabList value for this FetchKey.
     * 
     * @return vocabList
     */
    public java.lang.String[] getVocabList() {
        return vocabList;
    }


    /**
     * Sets the vocabList value for this FetchKey.
     * 
     * @param vocabList
     */
    public void setVocabList(java.lang.String[] vocabList) {
        this.vocabList = vocabList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchKey)) return false;
        FetchKey other = (FetchKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.vocabList==null && other.getVocabList()==null) || 
             (this.vocabList!=null &&
              java.util.Arrays.equals(this.vocabList, other.getVocabList())));
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
        if (getVocabList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVocabList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVocabList(), i);
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
        new org.apache.axis.description.TypeDesc(FetchKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FetchKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtElmVocabEnum"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabID"));
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
