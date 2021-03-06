package org.isotc211.v2005.gmd.impl;

import org.isotc211.v2005.gco.CodeListValue;
import org.isotc211.v2005.gco.impl.AbstractObjectImpl;
import org.isotc211.v2005.gmd.CIOnlineResource;


/**
 * POJO class for XML type CI_OnlineResource_Type(@http://www.isotc211.org/2005/gmd).
 *
 * This is a complex type.
 */
public class CIOnlineResourceImpl extends AbstractObjectImpl implements CIOnlineResource
{
    static final long serialVersionUID = 1L;
    protected String linkage = "";
    protected String protocol;
    protected String applicationProfile;
    protected String name;
    protected String description;
    protected CodeListValue function;
    
    
    public CIOnlineResourceImpl()
    {
    }
    
    
    /**
     * Gets the linkage property
     */
    @Override
    public String getLinkage()
    {
        return linkage;
    }
    
    
    /**
     * Sets the linkage property
     */
    @Override
    public void setLinkage(String linkage)
    {
        this.linkage = linkage;
    }
    
    
    /**
     * Gets the protocol property
     */
    @Override
    public String getProtocol()
    {
        return protocol;
    }
    
    
    /**
     * Checks if protocol is set
     */
    @Override
    public boolean isSetProtocol()
    {
        return (protocol != null);
    }
    
    
    /**
     * Sets the protocol property
     */
    @Override
    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }
    
    
    /**
     * Gets the applicationProfile property
     */
    @Override
    public String getApplicationProfile()
    {
        return applicationProfile;
    }
    
    
    /**
     * Checks if applicationProfile is set
     */
    @Override
    public boolean isSetApplicationProfile()
    {
        return (applicationProfile != null);
    }
    
    
    /**
     * Sets the applicationProfile property
     */
    @Override
    public void setApplicationProfile(String applicationProfile)
    {
        this.applicationProfile = applicationProfile;
    }
    
    
    /**
     * Gets the name property
     */
    @Override
    public String getName()
    {
        return name;
    }
    
    
    /**
     * Checks if name is set
     */
    @Override
    public boolean isSetName()
    {
        return (name != null);
    }
    
    
    /**
     * Sets the name property
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    /**
     * Gets the description property
     */
    @Override
    public String getDescription()
    {
        return description;
    }
    
    
    /**
     * Checks if description is set
     */
    @Override
    public boolean isSetDescription()
    {
        return (description != null);
    }
    
    
    /**
     * Sets the description property
     */
    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
    /**
     * Gets the function property
     */
    @Override
    public CodeListValue getFunction()
    {
        return function;
    }
    
    
    /**
     * Checks if function is set
     */
    @Override
    public boolean isSetFunction()
    {
        return (function != null);
    }
    
    
    /**
     * Sets the function property
     */
    @Override
    public void setFunction(CodeListValue function)
    {
        this.function = function;
    }
}
