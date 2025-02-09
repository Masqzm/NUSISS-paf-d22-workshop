package paf.day22;

public class SQLQueries {
    public static final String SQL_GET_ALL_RSVPS = "SELECT * FROM rsvp";

    public static final String SQL_GET_RSVP_BYNAME = "SELECT * FROM rsvp WHERE name LIKE ?";
    public static final String SQL_GET_RSVP_BYEMAIL = "SELECT * FROM rsvp WHERE email = ?";

    public static final String SQL_GET_RSVP_COUNT = "SELECT count(*) FROM rsvp";

    public static final String SQL_INSERT_RSVP = "INSERT INTO rsvp (name, email, phone, confirmation_date, comments) VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATEALL_RSVP_BYEMAIL = """
        UPDATE rsvp SET 
            name = ?,
            phone = ?,
            confirmation_date = ?,
            comments = ? 
        WHERE email = ?
    """;
}
