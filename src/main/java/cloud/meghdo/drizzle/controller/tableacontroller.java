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

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody tablea tablea) {
        try {
            tableaservice.savetablea(tablea);
            return new ResponseEntity<>("Entry created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create entry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
