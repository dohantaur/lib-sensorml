package net.opengis.sensorml.v20.impl;

import net.opengis.OgcPropertyList;
import net.opengis.sensorml.v20.CapabilityList;
import net.opengis.swe.v20.DataComponent;


/**
 * POJO class for XML type CapabilityListType(@http://www.opengis.net/sensorml/2.0).
 *
 * This is a complex type.
 */
public class CapabilityListImpl extends AbstractMetadataListImpl implements CapabilityList
{
    static final long serialVersionUID = 1L;
    protected OgcPropertyList<DataComponent> capabilityList = new OgcPropertyList<DataComponent>();
    
    
    public CapabilityListImpl()
    {
    }
    
    
    /**
     * Gets the list of capability properties
     */
    @Override
    public OgcPropertyList<DataComponent> getCapabilityList()
    {
        return capabilityList;
    }
    
    
    /**
     * Returns number of capability properties
     */
    @Override
    public int getNumCapabilitys()
    {
        if (capabilityList == null)
            return 0;
        return capabilityList.size();
    }
    
    
    /**
     * Gets the capability property with the given name
     */
    @Override
    public DataComponent getCapability(String name)
    {
        if (capabilityList == null)
            return null;
        return capabilityList.get(name);
    }
    
    
    /**
     * Adds a new capability property
     */
    @Override
    public void addCapability(String name, DataComponent capability)
    {
        this.capabilityList.add(name, capability);
    }
}
