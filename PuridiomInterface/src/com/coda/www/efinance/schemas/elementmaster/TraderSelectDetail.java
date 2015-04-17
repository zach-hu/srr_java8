/**
 * TraderSelectDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * Contains details of a trader selected
 *                 by the TraderSelectFilter defined in the
 *             SelectTraderRequest.
 */
public class TraderSelectDetail  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.TraderKey key;

    /* The trader
     *                     name. */
    private java.lang.String name;

    /* The first address line for
     *                         the trader. */
    private java.lang.String address1;

    /* The second address line for
     *                         the trader. */
    private java.lang.String address2;

    /* The third address line for
     *                         the trader. */
    private java.lang.String address3;

    /* The fourth address line for
     *                         the trader. */
    private java.lang.String address4;

    /* The fifth address line for
     *                         the trader. */
    private java.lang.String address5;

    /* The sixth address line for
     *                         the trader. */
    private java.lang.String address6;

    /* The
     *                         date when the trader was last
     *                     used. */
    private java.util.Calendar lastUsed;

    /* The date when the trader was
     *                     created. */
    private java.util.Calendar created;

    public TraderSelectDetail() {
    }

    public TraderSelectDetail(
           com.coda.www.efinance.schemas.elementmaster.TraderKey key,
           java.lang.String name,
           java.lang.String address1,
           java.lang.String address2,
           java.lang.String address3,
           java.lang.String address4,
           java.lang.String address5,
           java.lang.String address6,
           java.util.Calendar lastUsed,
           java.util.Calendar created) {
           this.key = key;
           this.name = name;
           this.address1 = address1;
           this.address2 = address2;
           this.address3 = address3;
           this.address4 = address4;
           this.address5 = address5;
           this.address6 = address6;
           this.lastUsed = lastUsed;
           this.created = created;
    }


    /**
     * Gets the key value for this TraderSelectDetail.
     * 
     * @return key
     */
    public com.coda.www.efinance.schemas.elementmaster.TraderKey getKey() {
        return key;
    }


    /**
     * Sets the key value for this TraderSelectDetail.
     * 
     * @param key
     */
    public void setKey(com.coda.www.efinance.schemas.elementmaster.TraderKey key) {
        this.key = key;
    }


    /**
     * Gets the name value for this TraderSelectDetail.
     * 
     * @return name   * The trader
     *                     name.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this TraderSelectDetail.
     * 
     * @param name   * The trader
     *                     name.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the address1 value for this TraderSelectDetail.
     * 
     * @return address1   * The first address line for
     *                         the trader.
     */
    public java.lang.String getAddress1() {
        return address1;
    }


    /**
     * Sets the address1 value for this TraderSelectDetail.
     * 
     * @param address1   * The first address line for
     *                         the trader.
     */
    public void setAddress1(java.lang.String address1) {
        this.address1 = address1;
    }


    /**
     * Gets the address2 value for this TraderSelectDetail.
     * 
     * @return address2   * The second address line for
     *                         the trader.
     */
    public java.lang.String getAddress2() {
        return address2;
    }


    /**
     * Sets the address2 value for this TraderSelectDetail.
     * 
     * @param address2   * The second address line for
     *                         the trader.
     */
    public void setAddress2(java.lang.String address2) {
        this.address2 = address2;
    }


    /**
     * Gets the address3 value for this TraderSelectDetail.
     * 
     * @return address3   * The third address line for
     *                         the trader.
     */
    public java.lang.String getAddress3() {
        return address3;
    }


    /**
     * Sets the address3 value for this TraderSelectDetail.
     * 
     * @param address3   * The third address line for
     *                         the trader.
     */
    public void setAddress3(java.lang.String address3) {
        this.address3 = address3;
    }


    /**
     * Gets the address4 value for this TraderSelectDetail.
     * 
     * @return address4   * The fourth address line for
     *                         the trader.
     */
    public java.lang.String getAddress4() {
        return address4;
    }


    /**
     * Sets the address4 value for this TraderSelectDetail.
     * 
     * @param address4   * The fourth address line for
     *                         the trader.
     */
    public void setAddress4(java.lang.String address4) {
        this.address4 = address4;
    }


    /**
     * Gets the address5 value for this TraderSelectDetail.
     * 
     * @return address5   * The fifth address line for
     *                         the trader.
     */
    public java.lang.String getAddress5() {
        return address5;
    }


    /**
     * Sets the address5 value for this TraderSelectDetail.
     * 
     * @param address5   * The fifth address line for
     *                         the trader.
     */
    public void setAddress5(java.lang.String address5) {
        this.address5 = address5;
    }


    /**
     * Gets the address6 value for this TraderSelectDetail.
     * 
     * @return address6   * The sixth address line for
     *                         the trader.
     */
    public java.lang.String getAddress6() {
        return address6;
    }


    /**
     * Sets the address6 value for this TraderSelectDetail.
     * 
     * @param address6   * The sixth address line for
     *                         the trader.
     */
    public void setAddress6(java.lang.String address6) {
        this.address6 = address6;
    }


    /**
     * Gets the lastUsed value for this TraderSelectDetail.
     * 
     * @return lastUsed   * The
     *                         date when the trader was last
     *                     used.
     */
    public java.util.Calendar getLastUsed() {
        return lastUsed;
    }


    /**
     * Sets the lastUsed value for this TraderSelectDetail.
     * 
     * @param lastUsed   * The
     *                         date when the trader was last
     *                     used.
     */
    public void setLastUsed(java.util.Calendar lastUsed) {
        this.lastUsed = lastUsed;
    }


    /**
     * Gets the created value for this TraderSelectDetail.
     * 
     * @return created   * The date when the trader was
     *                     created.
     */
    public java.util.Calendar getCreated() {
        return created;
    }


    /**
     * Sets the created value for this TraderSelectDetail.
     * 
     * @param created   * The date when the trader was
     *                     created.
     */
    public void setCreated(java.util.Calendar created) {
        this.created = created;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TraderSelectDetail)) return false;
        TraderSelectDetail other = (TraderSelectDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.address1==null && other.getAddress1()==null) || 
             (this.address1!=null &&
              this.address1.equals(other.getAddress1()))) &&
            ((this.address2==null && other.getAddress2()==null) || 
             (this.address2!=null &&
              this.address2.equals(other.getAddress2()))) &&
            ((this.address3==null && other.getAddress3()==null) || 
             (this.address3!=null &&
              this.address3.equals(other.getAddress3()))) &&
            ((this.address4==null && other.getAddress4()==null) || 
             (this.address4!=null &&
              this.address4.equals(other.getAddress4()))) &&
            ((this.address5==null && other.getAddress5()==null) || 
             (this.address5!=null &&
              this.address5.equals(other.getAddress5()))) &&
            ((this.address6==null && other.getAddress6()==null) || 
             (this.address6!=null &&
              this.address6.equals(other.getAddress6()))) &&
            ((this.lastUsed==null && other.getLastUsed()==null) || 
             (this.lastUsed!=null &&
              this.lastUsed.equals(other.getLastUsed()))) &&
            ((this.created==null && other.getCreated()==null) || 
             (this.created!=null &&
              this.created.equals(other.getCreated())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getAddress1() != null) {
            _hashCode += getAddress1().hashCode();
        }
        if (getAddress2() != null) {
            _hashCode += getAddress2().hashCode();
        }
        if (getAddress3() != null) {
            _hashCode += getAddress3().hashCode();
        }
        if (getAddress4() != null) {
            _hashCode += getAddress4().hashCode();
        }
        if (getAddress5() != null) {
            _hashCode += getAddress5().hashCode();
        }
        if (getAddress6() != null) {
            _hashCode += getAddress6().hashCode();
        }
        if (getLastUsed() != null) {
            _hashCode += getLastUsed().hashCode();
        }
        if (getCreated() != null) {
            _hashCode += getCreated().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TraderSelectDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderKey"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Created"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
