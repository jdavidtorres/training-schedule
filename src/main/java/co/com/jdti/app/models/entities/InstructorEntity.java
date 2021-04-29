package co.com.jdti.app.models.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "instructors")
@Data
@OnDelete(action = OnDeleteAction.CASCADE)
public class InstructorEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String lastname;

    @NotNull
    @Column(nullable = false)
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EventEntity> events;

    public InstructorEntity() {
        this.events = new ArrayList<>();
    }

    public InstructorEntity(String name, String lastname, Date birthday) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.events = new ArrayList<>();
    }

    public InstructorEntity(String id, String name, String lastname, Date birthday) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.events = new ArrayList<>();
    }
}
