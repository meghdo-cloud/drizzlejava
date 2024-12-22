package cloud.meghdo.drizzlejava.service;

import cloud.meghdo.drizzlejava.entity.tablea;
import cloud.meghdo.drizzlejava.repository.tablearepository;
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
    public tablea saveOrUpdateTableA(tablea tableA) {
        // Try to find the entity by id
        tablea existingTableA = tablearepository.findById(tableA.getId())
                .orElse(tableA); // Create new if not found
        existingTableA.setName(tableA.getName()); // Update the name if the entity exists
        return tablearepository.save(existingTableA); // Save or update the entity
    }
}