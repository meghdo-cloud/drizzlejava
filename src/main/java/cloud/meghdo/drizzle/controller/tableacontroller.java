package cloud.meghdo.drizzle.controller;

import cloud.meghdo.drizzle.entity.tablea;
import cloud.meghdo.drizzle.service.tableaservice;
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

    // Endpoint to create or update an entry
    @PostMapping
    public ResponseEntity<String> createOrUpdateEntry(@RequestParam Long id, @RequestParam String name) {
        try {
            tablea updatedTableA = tableaservice.saveOrUpdateTableA(id, name);
            return new ResponseEntity<>("Entry saved/updated successfully with ID: " + updatedTableA.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create or update entry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

