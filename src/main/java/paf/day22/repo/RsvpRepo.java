package paf.day22.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf.day22.SQLQueries;
import paf.day22.model.Rsvp;
import paf.day22.model.exception.ResourceNotFoundException;

@Repository
public class RsvpRepo {
    @Autowired
    private JdbcTemplate template;

    public List<Rsvp> getAllRsvps() {
        List<Rsvp> rsvps = new ArrayList<>();

        rsvps = template.query(SQLQueries.SQL_GET_ALL_RSVPS, BeanPropertyRowMapper.newInstance(Rsvp.class));

        if(rsvps.isEmpty()) 
            throw new ResourceNotFoundException("No records found in rsvp table");

        return rsvps;
    }

    public Rsvp getRsvpByName(String q) {
        try {
            String searchTerm = "%" + q + "%";  // add wildcards to front and back of search term
            
            return template.queryForObject(SQLQueries.SQL_GET_RSVP_BYNAME, BeanPropertyRowMapper.newInstance(Rsvp.class), searchTerm);
        } catch (DataAccessException ex) {
            throw new ResourceNotFoundException("No RSVP by search term [" + q + "] found");
        }
    }
}
