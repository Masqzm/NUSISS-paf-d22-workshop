package paf.day22.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
