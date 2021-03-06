package org.isotc211.v2005.gmd;

import java.util.List;
import org.isotc211.v2005.gco.CodeListValue;


/**
 * POJO class for XML type MD_LegalConstraints_Type(@http://www.isotc211.org/2005/gmd).
 *
 * This is a complex type.
 */
public interface MDLegalConstraints extends MDConstraints
{
    
    
    /**
     * Gets the list of accessConstraints properties
     */
    public List<CodeListValue> getAccessConstraintsList();
    
    
    /**
     * Returns number of accessConstraints properties
     */
    public int getNumAccessConstraints();
    
    
    /**
     * Adds a new accessConstraints property
     */
    public void addAccessConstraints(CodeListValue accessConstraints);
    
    
    /**
     * Gets the list of useConstraints properties
     */
    public List<CodeListValue> getUseConstraintsList();
    
    
    /**
     * Returns number of useConstraints properties
     */
    public int getNumUseConstraints();
    
    
    /**
     * Adds a new useConstraints property
     */
    public void addUseConstraints(CodeListValue useConstraints);
    
    
    /**
     * Gets the list of otherConstraints properties
     */
    public List<String> getOtherConstraintsList();
    
    
    /**
     * Returns number of otherConstraints properties
     */
    public int getNumOtherConstraints();
    
    
    /**
     * Adds a new otherConstraints property
     */
    public void addOtherConstraints(String otherConstraints);
}
