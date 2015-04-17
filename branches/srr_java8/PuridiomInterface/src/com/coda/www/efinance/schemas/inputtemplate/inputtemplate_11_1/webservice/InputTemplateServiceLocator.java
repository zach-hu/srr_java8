/**
 * InputTemplateServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice;

public class InputTemplateServiceLocator extends org.apache.axis.client.Service implements com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateService {

    public InputTemplateServiceLocator() {
    }


    public InputTemplateServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InputTemplateServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InputTemplateServicePort
    private java.lang.String InputTemplateServicePort_address = "http://codaweb.int.thompsontractor.com/D43PRD/services/finance/inputtemplate/inputtemplate-11.1";

    public java.lang.String getInputTemplateServicePortAddress() {
        return InputTemplateServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InputTemplateServicePortWSDDServiceName = "InputTemplateServicePort";

    public java.lang.String getInputTemplateServicePortWSDDServiceName() {
        return InputTemplateServicePortWSDDServiceName;
    }

    public void setInputTemplateServicePortWSDDServiceName(java.lang.String name) {
        InputTemplateServicePortWSDDServiceName = name;
    }

    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes getInputTemplateServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InputTemplateServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInputTemplateServicePort(endpoint);
    }

    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes getInputTemplateServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServiceSOAPBindingStub(portAddress, this);
            _stub.setPortName(getInputTemplateServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInputTemplateServicePortEndpointAddress(java.lang.String address) {
        InputTemplateServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes.class.isAssignableFrom(serviceEndpointInterface)) {
                com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServiceSOAPBindingStub(new java.net.URL(InputTemplateServicePort_address), this);
                _stub.setPortName(getInputTemplateServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InputTemplateServicePort".equals(inputPortName)) {
            return getInputTemplateServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "InputTemplateService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "InputTemplateServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InputTemplateServicePort".equals(portName)) {
            setInputTemplateServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
