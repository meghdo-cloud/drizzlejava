package cloud.meghdo.drizzlejava.controller;

import cloud.meghdo.drizzlejava.entity.tablea;
import cloud.meghdo.drizzlejava.service.tableaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tablea")
public class tableacontroller {

    private final tableaservice tableaservice;

    @Autowired
    public tableacontroller(tableaservice tableaservice) {
        this.tableaservice = tableaservice;
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody tablea tablea) {
        try {
            // Check if id and name are provided
            if (tablea.getId() == null || tablea.getName() == null || tablea.getName().isEmpty()) {
                return new ResponseEntity<>("Both 'id' and 'name' are required!", HttpStatus.BAD_REQUEST);
            }
            // Save or update the entity to the database
            tableaservice.saveOrUpdateTableA(tablea);
            return new ResponseEntity<>("Entry created/updated successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create/update entry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
