INSERT INTO user(id, name, is_admin, email_address, img_url) VALUES
  (1, 'Bea', TRUE, 'bea@gmail.com', 'img.jpg'),
  (2, 'Adel', FALSE, 'adel@gmail.com', 'img.jpg'),
  (3, 'Andor',FALSE, 'andor@gmail.com', 'img.jpg');

INSERT INTO mentor(id, name, points, slack_alias, image) VALUES
  (1, 'Ikarasz', '100', 'Ika', 'image'),
  (2, 'Blanka', '100', 'Bla', 'image'),
  (3, 'Gabor', '100', 'Gab', 'image');

INSERT INTO review(id, text, is_anonym, rating, mentor_id, reviewer_id) VALUES
  (1, 'Like your face!', FALSE, 'PLUS', 1, 2),
  (2, 'Like your enthusiasm!', FALSE, 'PLUS', 2, 1),
  (3, 'Like your keyboard!', FALSE, 'PLUS', 3, 3);
