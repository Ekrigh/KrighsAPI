INSERT INTO addresses (street, postalcode, city)
VALUES ('kattgatan1', '11111', 'Iggesund'),
       ('kattgatan2', '22222', 'Iggesund'),
       ('kattgatan3', '33333', 'Iggesund'),
       ('kattgatan4', '44444', 'Iggesund');

INSERT INTO members (first_name, last_name, address_id, email, phone, date_of_birth)
VALUES ('Jerry', 'Persson', 1, 'jerry.persson@cat.se', '0701234567', '1985-01-15'),
       ('Tyra', 'Hermansson', 2, 'tyra.hermansson@cat.se', '0702345678', '1990-03-22'),
       ('Tiger', 'Nordström', 3, 'tiger.nordstrom@cat.se', '0703456789', '1988-06-10'),
       ('Jens', 'Wigell', 4, 'jens.wigell@cat.se', '0704567890', '1992-07-17');

INSERT INTO members (first_name, last_name, address_id, email, date_of_birth)
VALUES ('Tiffanny', 'Wigell', 4, 'Tiffanny.wigell@cat.se', '1992-12-05');
