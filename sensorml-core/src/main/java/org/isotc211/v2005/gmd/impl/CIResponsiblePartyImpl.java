package org.isotc211.v2005.gmd.impl;

import net.opengis.OgcProperty;
import net.opengis.OgcPropertyImpl;
import org.isotc211.v2005.gco.CodeListValue;
import org.isotc211.v2005.gco.impl.AbstractObjectImpl;
import org.isotc211.v2005.gmd.CIContact;
import org.isotc211.v2005.gmd.CIResponsibleParty;


/**
 * POJO class for XML type CI_ResponsibleParty_Type(@http://www.isotc211.org/2005/gmd).
 *
 * This is a complex type.
 */
public class CIResponsiblePartyImpl extends AbstractObjectImpl implements CIResponsibleParty
{
    static final long serialVersionUID = 1L;
    protected String individualName;
    protected String organisationName;
    protected String positionName;
    protected OgcProperty<CIContact> contactInfo;
    protected CodeListValue role;
    
    
    public CIResponsiblePartyImpl()
    {
    }
    
    
    /**
     * Gets the individualName property
     */
    @Override
    public String getIndividualName()
    {
        return individualName;
    }
    
    
    /**
     * Checks if individualName is set
     */
    @Override
    public boolean isSetIndividualName()
    {
        return (individualName != null);
    }
    
    
    /**
     * Sets the individualName property
     */
    @Override
    public void setIndividualName(String individualName)
    {
        this.individualName = individualName;
    }
    
    
    /**
     * Gets the organisationName property
     */
    @Override
    public String getOrganisationName()
    {
        return organisationName;
    }
    
    
    /**
     * Checks if organisationName is set
     */
    @Override
    public boolean isSetOrganisationName()
    {
        return (organisationName != null);
    }
    
    
    /**
     * Sets the organisationName property
     */
    @Override
    public void setOrganisationName(String organisationName)
    {
        this.organisationName = organisationName;
    }
    
    
    /**
     * Gets the positionName property
     */
    @Override
    public String getPositionName()
    {
        return positionName;
    }
    
    
    /**
     * Checks if positionName is set
     */
    @Override
    public boolean isSetPositionName()
    {
        return (positionName != null);
    }
    
    
    /**
     * Sets the positionName property
     */
    @Override
    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }
    
    
    /**
     * Gets the contactInfo property
     */
    @Override
    public CIContact getContactInfo()
    {
        if (contactInfo == null)
            contactInfo = new OgcPropertyImpl<CIContact>(new CIContactImpl());
        return contactInfo.getValue();
    }
    
    
    /**
     * Gets extra info (name, xlink, etc.) carried by the contactInfo property
     */
    @Override
    public OgcProperty<CIContact> getContactInfoProperty()
    {
        if (contactInfo == null)
            contactInfo = new OgcPropertyImpl<CIContact>();
        return contactInfo;
    }
    
    
    /**
     * Checks if contactInfo is set
     */
    @Override
    public boolean isSetContactInfo()
    {
        return (contactInfo != null && contactInfo.getValue() != null);
    }
    
    
    /**
     * Sets the contactInfo property
     */
    @Override
    public void setContactInfo(CIContact contactInfo)
    {
        if (this.contactInfo == null)
            this.contactInfo = new OgcPropertyImpl<CIContact>();
        this.contactInfo.setValue(contactInfo);
    }
    
    
    /**
     * Gets the role property
     */
    @Override
    public CodeListValue getRole()
    {
        return role;
    }
    
    
    /**
     * Sets the role property
     */
    @Override
    public void setRole(CodeListValue role)
    {
        this.role = role;
    }
}
