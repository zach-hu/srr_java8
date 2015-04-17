/**
 * PO_DATA_ARRAY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iseries.wsbeans.getworkordervalidation.xsd;

public class PO_DATA_ARRAY  implements java.io.Serializable {
    private java.lang.String _AUTREQ_O;

    private java.lang.String _CUNO_O;

    private java.lang.String _TRNTYP_O;

    private java.lang.String _WOOPNO_O;

    private java.lang.String _WOSGNO_O;

    public PO_DATA_ARRAY() {
    }

    public PO_DATA_ARRAY(
           java.lang.String _AUTREQ_O,
           java.lang.String _CUNO_O,
           java.lang.String _TRNTYP_O,
           java.lang.String _WOOPNO_O,
           java.lang.String _WOSGNO_O) {
           this._AUTREQ_O = _AUTREQ_O;
           this._CUNO_O = _CUNO_O;
           this._TRNTYP_O = _TRNTYP_O;
           this._WOOPNO_O = _WOOPNO_O;
           this._WOSGNO_O = _WOSGNO_O;
    }


    /**
     * Gets the _AUTREQ_O value for this PO_DATA_ARRAY.
     * 
     * @return _AUTREQ_O
     */
    public java.lang.String get_AUTREQ_O() {
        return _AUTREQ_O;
    }


    /**
     * Sets the _AUTREQ_O value for this PO_DATA_ARRAY.
     * 
     * @param _AUTREQ_O
     */
    public void set_AUTREQ_O(java.lang.String _AUTREQ_O) {
        this._AUTREQ_O = _AUTREQ_O;
    }


    /**
     * Gets the _CUNO_O value for this PO_DATA_ARRAY.
     * 
     * @return _CUNO_O
     */
    public java.lang.String get_CUNO_O() {
        return _CUNO_O;
    }


    /**
     * Sets the _CUNO_O value for this PO_DATA_ARRAY.
     * 
     * @param _CUNO_O
     */
    public void set_CUNO_O(java.lang.String _CUNO_O) {
        this._CUNO_O = _CUNO_O;
    }


    /**
     * Gets the _TRNTYP_O value for this PO_DATA_ARRAY.
     * 
     * @return _TRNTYP_O
     */
    public java.lang.String get_TRNTYP_O() {
        return _TRNTYP_O;
    }


    /**
     * Sets the _TRNTYP_O value for this PO_DATA_ARRAY.
     * 
     * @param _TRNTYP_O
     */
    public void set_TRNTYP_O(java.lang.String _TRNTYP_O) {
        this._TRNTYP_O = _TRNTYP_O;
    }


    /**
     * Gets the _WOOPNO_O value for this PO_DATA_ARRAY.
     * 
     * @return _WOOPNO_O
     */
    public java.lang.String get_WOOPNO_O() {
        return _WOOPNO_O;
    }


    /**
     * Sets the _WOOPNO_O value for this PO_DATA_ARRAY.
     * 
     * @param _WOOPNO_O
     */
    public void set_WOOPNO_O(java.lang.String _WOOPNO_O) {
        this._WOOPNO_O = _WOOPNO_O;
    }


    /**
     * Gets the _WOSGNO_O value for this PO_DATA_ARRAY.
     * 
     * @return _WOSGNO_O
     */
    public java.lang.String get_WOSGNO_O() {
        return _WOSGNO_O;
    }


    /**
     * Sets the _WOSGNO_O value for this PO_DATA_ARRAY.
     * 
     * @param _WOSGNO_O
     */
    public void set_WOSGNO_O(java.lang.String _WOSGNO_O) {
        this._WOSGNO_O = _WOSGNO_O;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PO_DATA_ARRAY)) return false;
        PO_DATA_ARRAY other = (PO_DATA_ARRAY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._AUTREQ_O==null && other.get_AUTREQ_O()==null) || 
             (this._AUTREQ_O!=null &&
              this._AUTREQ_O.equals(other.get_AUTREQ_O()))) &&
            ((this._CUNO_O==null && other.get_CUNO_O()==null) || 
             (this._CUNO_O!=null &&
              this._CUNO_O.equals(other.get_CUNO_O()))) &&
            ((this._TRNTYP_O==null && other.get_TRNTYP_O()==null) || 
             (this._TRNTYP_O!=null &&
              this._TRNTYP_O.equals(other.get_TRNTYP_O()))) &&
            ((this._WOOPNO_O==null && other.get_WOOPNO_O()==null) || 
             (this._WOOPNO_O!=null &&
              this._WOOPNO_O.equals(other.get_WOOPNO_O()))) &&
            ((this._WOSGNO_O==null && other.get_WOSGNO_O()==null) || 
             (this._WOSGNO_O!=null &&
              this._WOSGNO_O.equals(other.get_WOSGNO_O())));
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
        if (get_AUTREQ_O() != null) {
            _hashCode += get_AUTREQ_O().hashCode();
        }
        if (get_CUNO_O() != null) {
            _hashCode += get_CUNO_O().hashCode();
        }
        if (get_TRNTYP_O() != null) {
            _hashCode += get_TRNTYP_O().hashCode();
        }
        if (get_WOOPNO_O() != null) {
            _hashCode += get_WOOPNO_O().hashCode();
        }
        if (get_WOSGNO_O() != null) {
            _hashCode += get_WOSGNO_O().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PO_DATA_ARRAY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "PO_DATA_ARRAY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_AUTREQ_O");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_AUTREQ_O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_CUNO_O");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_CUNO_O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_TRNTYP_O");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_TRNTYP_O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_WOOPNO_O");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_WOOPNO_O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_WOSGNO_O");
        elemField.setXmlName(new javax.xml.namespace.QName("http://getworkordervalidation.wsbeans.iseries/xsd", "_WOSGNO_O"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
