package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.EMPLOYEE_POSTS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler"})
@NamedQuery(name = EmployeePost.FIND_ALL_POSTS_BY_EMPLOYEE_ID,
        query = "SELECT e FROM EmployeePost e WHERE e.employee.id = ?1")
public class EmployeePost extends BaseEntity {

    public static final String FIND_ALL_POSTS_BY_EMPLOYEE_ID = "findAllPostsByEmployeeId";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.EMPLOYEE_ID)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.POST_ID)
    private Post post;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "is_active")
    private boolean isActive;

    @Override
    public String getUrl() {
        return employee.getUrl() + "/posts/" + getId();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmployeePost [");
        builder.append(super.toString()).append(", ");
        builder.append("employee=" + employee).append(", ");
        return builder.toString();
    }

}
