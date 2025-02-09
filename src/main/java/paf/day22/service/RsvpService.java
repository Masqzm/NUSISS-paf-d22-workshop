package paf.day22.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day22.model.Rsvp;
import paf.day22.repo.RsvpRepo;

@Service
public class RsvpService {
    @Autowired
    private RsvpRepo rsvpRepo;

    public List<Rsvp> getAllRsvps() {
        return rsvpRepo.getAllRsvps();
    }

    public Rsvp getRsvpByName(String q) {
        return rsvpRepo.getRsvpByName(q);
    }
    
}
