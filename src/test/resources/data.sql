INSERT INTO user(id, is_admin, name) VALUES
  (1, TRUE, 'Bea'),
  (2, FALSE, 'Adel'),
  (3, FALSE, 'Andor');

INSERT INTO mentor(id, name, points) VALUES
  (1, 'Ikarasz', '100'),
  (2, 'Blanka', '100'),
  (3, 'Gabor', '100');

INSERT INTO review(id, text, is_anonym, rating, mentor_id, reviewer_id) VALUES
  (1, 'Like your face!', FALSE, 'PLUS', 1, 2),
  (2, 'Like your enthusiasm!', FALSE, 'PLUS', 2, 1),
  (3, 'Like your keyboard!', FALSE, 'PLUS', 3, 3);
