INSERT INTO user(id, is_admin, name) VALUES
  (1, TRUE, 'Bea'),
  (2, FALSE, 'Adel'),
  (3, FALSE, 'Andor');

INSERT INTO mentor(id, name, points) VALUES
  (1, 'Ikarasz', '100'),
  (2, 'Blanka', '100'),
  (3, 'Gabor', '100');

INSERT INTO review(id, is_anonym, text, mentor_id, reviewer_id) VALUES
  (1, FALSE, 'Like your face!', 1, 2),
  (2, FALSE, 'Like your enthusiasm!', 2, 1),
  (3, FALSE, 'Like your keyboard!', 3, 3);
