package paf.day22.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import paf.day22.SQLQueries;
import paf.day22.model.Rsvp;
import paf.day22.model.exception.InsertionErrorException;
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

	public Rsvp getRsvpByEmail(String email) throws DataAccessException {
        try {
            return template.queryForObject(SQLQueries.SQL_GET_RSVP_BYEMAIL, 
                                            BeanPropertyRowMapper.newInstance(Rsvp.class), 
                                            email);
        } catch (DataAccessException ex) {
            throw ex;
        }
	}

    public boolean insertRsvp(Rsvp rsvp) {
        try {
            return template.update(SQLQueries.SQL_INSERT_RSVP, rsvp.getName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmation_date(), rsvp.getComments()) > 0; 
        } catch (DataAccessException ex) {
            throw new InsertionErrorException("RSVP insertion error", ex.getCause());
        }
    }
    // To insert and overwrite if rsvp exists
    public boolean insertRsvpByEmail(Rsvp rsvp) {
        try {
            return template.update(SQLQueries.SQL_UPDATEALL_RSVP_BYEMAIL, 
                                rsvp.getName(), 
                                rsvp.getPhone(), 
                                rsvp.getConfirmation_date(), 
                                rsvp.getComments(),
                                rsvp.getEmail()) > 0; 
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("RSVP update error - form contains illegal arguments");
        }
    }

    /* Updating all possible fields:
     * UPDATE rsvp SET 
            name = ?,
            phone = ?,
            confirmation_date = ?,
            comments = ? 
        WHERE email = ?
    */
    public boolean updateRsvpByEmail(String email, MultiValueMap<String, String> updateForm) {
        if(updateForm.isEmpty())
            throw new IllegalArgumentException("RSVP update error - form is empty");
        
        if(updateForm.containsKey("email"))
            throw new IllegalArgumentException("RSVP update error - not allowed to change email");
    
        StringBuilder sql = new StringBuilder("UPDATE rsvp SET ");
        List<Object> paramValues = new ArrayList<>();

        // Build SQL dynamically
        for(String key : updateForm.keySet()) {
            sql.append(key).append(" = ?, ");
            paramValues.add(updateForm.getFirst(key));
        }

        // Remove last comma and space
        sql.setLength(sql.length() - 2);

        // Add filter
        sql.append(" WHERE email = ?");
        paramValues.add(email);
        
        try {
            return template.update(sql.toString(), paramValues.toArray()) > 0;
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("RSVP update error - form contains illegal arguments");
        }
    }

    public Integer getRsvpCount() {
        return template.queryForObject(SQLQueries.SQL_GET_RSVP_COUNT, Integer.class);
    }
}