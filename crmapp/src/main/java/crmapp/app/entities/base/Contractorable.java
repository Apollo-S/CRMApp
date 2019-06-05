package crmapp.app.entities.base;

import crmapp.app.entities.AbstractCompany;

public interface Contractorable<T extends AbstractCompany> {

    void setContractor(T contractor);

}
