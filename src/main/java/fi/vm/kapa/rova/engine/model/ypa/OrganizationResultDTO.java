package fi.vm.kapa.rova.engine.model.ypa;

import java.util.SortedSet;
import java.util.TreeSet;

public class OrganizationResultDTO {
    private SortedSet<OrganizationResult> organizationResults = new TreeSet<>();
    private int total;

    public SortedSet<OrganizationResult> getOrganizationResults() {
        return organizationResults;
    }

    public void setOrganizationResults(SortedSet<OrganizationResult> organizationResults) {
        this.organizationResults = organizationResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
