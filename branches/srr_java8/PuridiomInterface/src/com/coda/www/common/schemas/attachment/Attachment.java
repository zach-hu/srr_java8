/**
 * Attachment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.attachment;


/**
 * This element holds
 *                 details of attachments.
 */
public class Attachment  implements java.io.Serializable, org.apache.axis.encoding.SimpleType {
    private byte[] _value;

    private java.math.BigDecimal id;  // attribute

    private java.math.BigDecimal repositoryobjectid;  // attribute

    private java.lang.String ref;  // attribute

    private java.lang.String path;  // attribute

    private java.lang.String externalreference;  // attribute

    private java.util.Calendar filelastmodified;  // attribute

    private java.lang.String filename;  // attribute

    private java.lang.String user;  // attribute

    private java.util.Calendar added;  // attribute

    private java.lang.String mimetype;  // attribute

    private java.lang.String product;  // attribute

    private java.lang.String thumbnail;  // attribute

    private java.lang.String codametadata;  // attribute

    private java.lang.Boolean missing;  // attribute

    private short timestamp;  // attribute

    public Attachment() {
    }

    // Simple Types must have a String constructor
    public Attachment(byte[] _value) {
        this._value = _value;
    }
    public Attachment(java.lang.String _value) {
        this._value = org.apache.axis.encoding.Base64.decode(_value);
    }

    // Simple Types must have a toString for serializing the value
    public java.lang.String toString() {
        return _value == null ? null : org.apache.axis.encoding.Base64.encode(_value);
    }


    /**
     * Gets the _value value for this Attachment.
     * 
     * @return _value
     */
    public byte[] get_value() {
        return _value;
    }


    /**
     * Sets the _value value for this Attachment.
     * 
     * @param _value
     */
    public void set_value(byte[] _value) {
        this._value = _value;
    }


    /**
     * Gets the id value for this Attachment.
     * 
     * @return id
     */
    public java.math.BigDecimal getId() {
        return id;
    }


    /**
     * Sets the id value for this Attachment.
     * 
     * @param id
     */
    public void setId(java.math.BigDecimal id) {
        this.id = id;
    }


    /**
     * Gets the repositoryobjectid value for this Attachment.
     * 
     * @return repositoryobjectid
     */
    public java.math.BigDecimal getRepositoryobjectid() {
        return repositoryobjectid;
    }


    /**
     * Sets the repositoryobjectid value for this Attachment.
     * 
     * @param repositoryobjectid
     */
    public void setRepositoryobjectid(java.math.BigDecimal repositoryobjectid) {
        this.repositoryobjectid = repositoryobjectid;
    }


    /**
     * Gets the ref value for this Attachment.
     * 
     * @return ref
     */
    public java.lang.String getRef() {
        return ref;
    }


    /**
     * Sets the ref value for this Attachment.
     * 
     * @param ref
     */
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }


    /**
     * Gets the path value for this Attachment.
     * 
     * @return path
     */
    public java.lang.String getPath() {
        return path;
    }


    /**
     * Sets the path value for this Attachment.
     * 
     * @param path
     */
    public void setPath(java.lang.String path) {
        this.path = path;
    }


    /**
     * Gets the externalreference value for this Attachment.
     * 
     * @return externalreference
     */
    public java.lang.String getExternalreference() {
        return externalreference;
    }


    /**
     * Sets the externalreference value for this Attachment.
     * 
     * @param externalreference
     */
    public void setExternalreference(java.lang.String externalreference) {
        this.externalreference = externalreference;
    }


    /**
     * Gets the filelastmodified value for this Attachment.
     * 
     * @return filelastmodified
     */
    public java.util.Calendar getFilelastmodified() {
        return filelastmodified;
    }


    /**
     * Sets the filelastmodified value for this Attachment.
     * 
     * @param filelastmodified
     */
    public void setFilelastmodified(java.util.Calendar filelastmodified) {
        this.filelastmodified = filelastmodified;
    }


    /**
     * Gets the filename value for this Attachment.
     * 
     * @return filename
     */
    public java.lang.String getFilename() {
        return filename;
    }


    /**
     * Sets the filename value for this Attachment.
     * 
     * @param filename
     */
    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }


    /**
     * Gets the user value for this Attachment.
     * 
     * @return user
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this Attachment.
     * 
     * @param user
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the added value for this Attachment.
     * 
     * @return added
     */
    public java.util.Calendar getAdded() {
        return added;
    }


    /**
     * Sets the added value for this Attachment.
     * 
     * @param added
     */
    public void setAdded(java.util.Calendar added) {
        this.added = added;
    }


    /**
     * Gets the mimetype value for this Attachment.
     * 
     * @return mimetype
     */
    public java.lang.String getMimetype() {
        return mimetype;
    }


    /**
     * Sets the mimetype value for this Attachment.
     * 
     * @param mimetype
     */
    public void setMimetype(java.lang.String mimetype) {
        this.mimetype = mimetype;
    }


    /**
     * Gets the product value for this Attachment.
     * 
     * @return product
     */
    public java.lang.String getProduct() {
        return product;
    }


    /**
     * Sets the product value for this Attachment.
     * 
     * @param product
     */
    public void setProduct(java.lang.String product) {
        this.product = product;
    }


    /**
     * Gets the thumbnail value for this Attachment.
     * 
     * @return thumbnail
     */
    public java.lang.String getThumbnail() {
        return thumbnail;
    }


    /**
     * Sets the thumbnail value for this Attachment.
     * 
     * @param thumbnail
     */
    public void setThumbnail(java.lang.String thumbnail) {
        this.thumbnail = thumbnail;
    }


    /**
     * Gets the codametadata value for this Attachment.
     * 
     * @return codametadata
     */
    public java.lang.String getCodametadata() {
        return codametadata;
    }


    /**
     * Sets the codametadata value for this Attachment.
     * 
     * @param codametadata
     */
    public void setCodametadata(java.lang.String codametadata) {
        this.codametadata = codametadata;
    }


    /**
     * Gets the missing value for this Attachment.
     * 
     * @return missing
     */
    public java.lang.Boolean getMissing() {
        return missing;
    }


    /**
     * Sets the missing value for this Attachment.
     * 
     * @param missing
     */
    public void setMissing(java.lang.Boolean missing) {
        this.missing = missing;
    }


    /**
     * Gets the timestamp value for this Attachment.
     * 
     * @return timestamp
     */
    public short getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this Attachment.
     * 
     * @param timestamp
     */
    public void setTimestamp(short timestamp) {
        this.timestamp = timestamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Attachment)) return false;
        Attachment other = (Attachment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._value==null && other.get_value()==null) || 
             (this._value!=null &&
              java.util.Arrays.equals(this._value, other.get_value()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.repositoryobjectid==null && other.getRepositoryobjectid()==null) || 
             (this.repositoryobjectid!=null &&
              this.repositoryobjectid.equals(other.getRepositoryobjectid()))) &&
            ((this.ref==null && other.getRef()==null) || 
             (this.ref!=null &&
              this.ref.equals(other.getRef()))) &&
            ((this.path==null && other.getPath()==null) || 
             (this.path!=null &&
              this.path.equals(other.getPath()))) &&
            ((this.externalreference==null && other.getExternalreference()==null) || 
             (this.externalreference!=null &&
              this.externalreference.equals(other.getExternalreference()))) &&
            ((this.filelastmodified==null && other.getFilelastmodified()==null) || 
             (this.filelastmodified!=null &&
              this.filelastmodified.equals(other.getFilelastmodified()))) &&
            ((this.filename==null && other.getFilename()==null) || 
             (this.filename!=null &&
              this.filename.equals(other.getFilename()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.added==null && other.getAdded()==null) || 
             (this.added!=null &&
              this.added.equals(other.getAdded()))) &&
            ((this.mimetype==null && other.getMimetype()==null) || 
             (this.mimetype!=null &&
              this.mimetype.equals(other.getMimetype()))) &&
            ((this.product==null && other.getProduct()==null) || 
             (this.product!=null &&
              this.product.equals(other.getProduct()))) &&
            ((this.thumbnail==null && other.getThumbnail()==null) || 
             (this.thumbnail!=null &&
              this.thumbnail.equals(other.getThumbnail()))) &&
            ((this.codametadata==null && other.getCodametadata()==null) || 
             (this.codametadata!=null &&
              this.codametadata.equals(other.getCodametadata()))) &&
            ((this.missing==null && other.getMissing()==null) || 
             (this.missing!=null &&
              this.missing.equals(other.getMissing()))) &&
            this.timestamp == other.getTimestamp();
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
        if (get_value() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(get_value());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(get_value(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getRepositoryobjectid() != null) {
            _hashCode += getRepositoryobjectid().hashCode();
        }
        if (getRef() != null) {
            _hashCode += getRef().hashCode();
        }
        if (getPath() != null) {
            _hashCode += getPath().hashCode();
        }
        if (getExternalreference() != null) {
            _hashCode += getExternalreference().hashCode();
        }
        if (getFilelastmodified() != null) {
            _hashCode += getFilelastmodified().hashCode();
        }
        if (getFilename() != null) {
            _hashCode += getFilename().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getAdded() != null) {
            _hashCode += getAdded().hashCode();
        }
        if (getMimetype() != null) {
            _hashCode += getMimetype().hashCode();
        }
        if (getProduct() != null) {
            _hashCode += getProduct().hashCode();
        }
        if (getThumbnail() != null) {
            _hashCode += getThumbnail().hashCode();
        }
        if (getCodametadata() != null) {
            _hashCode += getCodametadata().hashCode();
        }
        if (getMissing() != null) {
            _hashCode += getMissing().hashCode();
        }
        _hashCode += getTimestamp();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Attachment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Attachment"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDbGuid"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("repositoryobjectid");
        attrField.setXmlName(new javax.xml.namespace.QName("", "repositoryobjectid"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDbGuid"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ref");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ref"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("path");
        attrField.setXmlName(new javax.xml.namespace.QName("", "path"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("externalreference");
        attrField.setXmlName(new javax.xml.namespace.QName("", "externalreference"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyDbQuotes"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("filelastmodified");
        attrField.setXmlName(new javax.xml.namespace.QName("", "filelastmodified"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePosixTime"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("filename");
        attrField.setXmlName(new javax.xml.namespace.QName("", "filename"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("user");
        attrField.setXmlName(new javax.xml.namespace.QName("", "user"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyUnlimited"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("added");
        attrField.setXmlName(new javax.xml.namespace.QName("", "added"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeHardTime"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("mimetype");
        attrField.setXmlName(new javax.xml.namespace.QName("", "mimetype"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("product");
        attrField.setXmlName(new javax.xml.namespace.QName("", "product"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("thumbnail");
        attrField.setXmlName(new javax.xml.namespace.QName("", "thumbnail"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyUnlimited"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("codametadata");
        attrField.setXmlName(new javax.xml.namespace.QName("", "codametadata"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeNameTextValues"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("missing");
        attrField.setXmlName(new javax.xml.namespace.QName("", "missing"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBoolean"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("timestamp");
        attrField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
          new  org.apache.axis.encoding.ser.SimpleSerializer(
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
          new  org.apache.axis.encoding.ser.SimpleDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
