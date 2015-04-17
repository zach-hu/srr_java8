/**
 * AccountServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class AccountServiceLocator extends org.apache.axis.client.Service implements com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountService {

    public AccountServiceLocator() {
    }


    public AccountServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccountServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccountServicePort
    private java.lang.String AccountServicePort_address = "http://codaweb.int.thompsontractor.com/D43PRD/services/finance/transaction/account-11.2";

    public java.lang.String getAccountServicePortAddress() {
        return AccountServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccountServicePortWSDDServiceName = "AccountServicePort";

    public java.lang.String getAccountServicePortWSDDServiceName() {
        return AccountServicePortWSDDServiceName;
    }

    public void setAccountServicePortWSDDServiceName(java.lang.String name) {
        AccountServicePortWSDDServiceName = name;
    }

    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes getAccountServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccountServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccountServicePort(endpoint);
    }

    public com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes getAccountServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub(portAddress, this);
            _stub.setPortName(getAccountServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccountServicePortEndpointAddress(java.lang.String address) {
        AccountServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServicePortTypes.class.isAssignableFrom(serviceEndpointInterface)) {
                com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub _stub = new com.coda.www.efinance.schemas.transaction.account_11_2.webservice.AccountServiceSOAPBindingStub(new java.net.URL(AccountServicePort_address), this);
                _stub.setPortName(getAccountServicePortWSDDServiceName());
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
        if ("AccountServicePort".equals(inputPortName)) {
            return getAccountServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "AccountService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "AccountServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccountServicePort".equals(portName)) {
            setAccountServicePortEndpointAddress(address);
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
