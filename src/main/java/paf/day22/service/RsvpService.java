package paf.day22.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import paf.day22.model.Rsvp;
import paf.day22.model.exception.ResourceNotFoundException;
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

    public boolean insertRsvp(Rsvp rsvp) {
        try {
            rsvpRepo.getRsvpByEmail(rsvp.getEmail());
        } catch(DataAccessException ex) {
            // insert new rsvp since it doesn't exist
            return rsvpRepo.insertRsvp(rsvp);
        }        
        
        // update rsvp since it exists
        return rsvpRepo.insertRsvpByEmail(rsvp);
    }

    public boolean updateRsvpByEmail(String email, MultiValueMap<String, String> form) {
        try {
            rsvpRepo.getRsvpByEmail(email);

            return rsvpRepo.updateRsvpByEmail(email, form);
        } catch(DataAccessException ex) {
            throw new ResourceNotFoundException("No RSVP with email [" + email + "] found");
        }  
    }

    public Integer getRsvpCount() {
        return rsvpRepo.getRsvpCount();
    }
}
