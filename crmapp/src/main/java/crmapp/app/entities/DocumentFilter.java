package crmapp.app.entities;

import java.util.List;

public class DocumentFilter {

    private List<Integer> docStatuses;
    private List<Integer> docTypes;
    private List<Integer> clients;
    private String sortType;
    private String sortField;

    public List<Integer> getDocStatuses() {
        return docStatuses;
    }

    public void setDocStatuses(List<Integer> docStatuses) {
        this.docStatuses = docStatuses;
    }

    public List<Integer> getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(List<Integer> docTypes) {
        this.docTypes = docTypes;
    }

    public List<Integer> getClients() {
        return clients;
    }

    public void setClients(List<Integer> clients) {
        this.clients = clients;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}
