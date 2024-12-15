package cloud.meghdo.drizzle.service;

import cloud.meghdo.drizzle.entity.tablea;
import cloud.meghdo.drizzle.repository.tablearepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tableaservice {

    private final tablearepository tablearepository;

    @Autowired
    public tableaservice(tablearepository tablearepository) {
        this.tablearepository = tablearepository;
    }

    // Method to save or update the tablea entity
    public tablea saveOrUpdateTableA(Long id, String name) {
        // Try to find the entity by id
        tablea tableA = tablearepository.findById(id).orElse(new tablea(id, name)); // Create new if not found
        tableA.setId(id);  // Ensure the id is set
        tableA.setName(name);  // Set the name value
        return tablearepository.save(tableA); // Save or update the entity
    }
}

