package cloud.meghdo.drizzlejava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class tablea {

    @Id
    private Long id;

    private String name;

    // Constructors
    public tablea() {
    }

    public tablea(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
