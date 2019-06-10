package crmapp.app.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public abstract class TypeBaseEntity extends BaseEntity {

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "title", length = 30)
    private String title;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("code=" + code).append(", ")
                .append("title=" + title).append(", ")
                .toString();
    }

}
