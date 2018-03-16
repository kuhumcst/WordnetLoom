
INSERT INTO users (id, email, firstname, lastname, password, role) VALUES (3, 'seaton@hum.ku.dk', 'Mitchell', '','XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=','ADMIN');
INSERT INTO users (id, email, firstname, lastname, password, role) VALUES (4, 'jms862@hum.ku.dk', 'Ida', '','XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=','USER');
INSERT INTO users (id, email, firstname, lastname, password, role) VALUES (5, 'saolsen@hum.ku.dk', 'Sussi', '','XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=','USER');
INSERT INTO users (id, email, firstname, lastname, password, role) VALUES (6, 'bspedersen@hum.ku.dk', 'Bolette', '','XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=','USER');

INSERT INTO users_settings (user_id, lexicon_marker, chosen_lexicons, show_tool_tips)
  SELECT
    u.id,
    TRUE,
    '1;2;',
    TRUE
  FROM users u 
  WHERE id >= 3;

UPDATE TABLE tracker_rev_info SET username = 'jms862@hum.ku.dk' WHERE username = 'admin@example.com';