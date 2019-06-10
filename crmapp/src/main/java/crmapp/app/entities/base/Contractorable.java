package crmapp.app.entities.base;

public interface Contractorable<T extends AbstractCompany> {

    void setContractor(T contractor);

}
