CREATE DATABASE rsvp;

USE rsvp;

CREATE USER 'fred' @'%'		# % is a wild card for any host
identified BY 'qweasd';		# password

GRANT ALL PRIVILEGES ON rsvp.* 
TO 'fred' @'%';

flush PRIVILEGES;

CREATE TABLE rsvp (
	name char(64) NOT NULL,
	email char(64) NOT NULL,
	phone char(32) NOT NULL,
	confirmation_date date,
	comments text,
	CONSTRAINT pk_email PRIMARY KEY (email)
);

INSERT INTO	rsvp (name, email, phone, confirmation_date, comments) VALUES ('Fred', 'fred@gmail.com', '12345678', '2025-02-01', 'Hello world!');

SELECT * FROM rsvp;

SELECT * FROM rsvp WHERE name LIKE '%fred%';
SELECT * FROM rsvp WHERE email = 'fred@gmail.com';

UPDATE rsvp SET 
	name = 'Newname',
	phone = '99998888',
	confirmation_date = '2025-02-09',
	comments = 'Edited'
WHERE email = 'test@g.com';


SELECT count(*) FROM rsvp;