package paf.day22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paf.day22.model.Rsvp;
import paf.day22.service.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRestController {
    @Autowired
    private RsvpService rsvpSvc;

    // GET /api/rsvps 
    // Accept: application/json
    @GetMapping(path="/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rsvp>> getAllRsvps() {
        List<Rsvp> rsvps = rsvpSvc.getAllRsvps();

        return ResponseEntity.ok().body(rsvps);
    }

    // GET /api/rsvp?q=fred
    // Accept: application/json
    @GetMapping(path="/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rsvp> getRsvpByName(@RequestParam String q) {
        Rsvp rsvp = rsvpSvc.getRsvpByName(q);

        return ResponseEntity.ok().body(rsvp);
    }

    // GET /api/rsvps/count
    // Accept: application/json
    @GetMapping(path="/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getRsvpCount() {
        return ResponseEntity.status(201).body(rsvpSvc.getRsvpCount());
    }

    // POST /api/rsvp 
    // Accept: application/json
    // Content-Type: application/x-www-form-urlencoded 
    @PostMapping(path="/rsvp",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
                // For FORM_URLENCODED use ModelAttribute/MultiValueMap, for JSON use RequestBody 
    public ResponseEntity<Boolean> createRsvp(@ModelAttribute Rsvp rsvp) {
        boolean success = rsvpSvc.insertRsvp(rsvp);

        return ResponseEntity.status(201).body(success);
    }

    // PUT /api/rsvp/fred@gmail.com  
    // Accept: application/json 
    // Content-Type: application/x-www-form-urlencoded 
    @PutMapping(path="/rsvp/{email}",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public ResponseEntity<Boolean> updateRsvp(@PathVariable String email,
                                            @RequestParam MultiValueMap<String, String> form) {
        boolean success = rsvpSvc.updateRsvpByEmail(email, form);

        return ResponseEntity.status(201).body(success);
    }
}