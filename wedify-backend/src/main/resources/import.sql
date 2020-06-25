# OAuth2 Clients password = secret
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 authorities, access_token_validity, refresh_token_validity)
VALUES ('wedify-web', '$2a$12$o3dmbF3ElqPL1ApJ.9R/Qu7cVBMyV8pn80.HPFPdKO/jerqGJiXZe', 'all', 'password,refresh_token',
        'ROLE_CLIENT', 2000, 2592000);

# User Type
INSERT INTO user_type(id, type)
VALUES (1, 'COMPANY'),
       (2, 'CUSTOMER'),
       (3, 'ADMIN');

# User - Password = pass
INSERT INTO user(id, email, password, user_type)
VALUES (1, 'eetteam@gmail.com', '$2a$12$Uv6GCKwJk7SaEPpy/h/dM.Qf4/BE5OTLc.31cPYrPc/Sl/LhUO1GO', 1),
       (2, 'buiksloterkerk@gmail.com', '$2a$12$Uv6GCKwJk7SaEPpy/h/dM.Qf4/BE5OTLc.31cPYrPc/Sl/LhUO1GO', 1),
       (3, 'remcozaalverhuur@gmail.com', '$2a$12$Uv6GCKwJk7SaEPpy/h/dM.Qf4/BE5OTLc.31cPYrPc/Sl/LhUO1GO', 1),
       (4, 'vandervalk@gmail.com', '$2a$12$Uv6GCKwJk7SaEPpy/h/dM.Qf4/BE5OTLc.31cPYrPc/Sl/LhUO1GO', 1);

# Company
INSERT INTO company(id, city, house_number, kvk, name, phone_number, postal_code, street, website)
VALUES (1, 'Amsterdam', '20', '123456789', 'The Eet Company', '0201234567', '1011BE', 'Archenkande',
        'www.the-eet-team.nl'),
       (2, 'Amsterdam', '40', '3453454574', 'De Buiksloterkerk', '0201234667', '1034VZ', 'Buiksloterkerkerdijk',
        'www.buiksloterkerk.nl'),
       (3, 'Utrecht', '60', '234546111', 'Remco Zaalverhuur', '0201823127', '1034VZ', 'Heuvelrug',
        'www.remco-zaalverhuur.nl'),
       (4, 'Amsterdam', '20', '12345678', 'Van der Valk Hotel', '0204435678', '1190PL', 'Valkstraat',
        'www.vandervalk.nl');

# Facility Type
INSERT INTO facility_type(id, type)
VALUES (1, 'VENUE');

# Facility
INSERT INTO facility(id, description, max_guests, min_guests, name, pricing_information, company, facility_type)
VALUES (1, 'Zoekt u een originele trouwlocatie met een unieke sfeer, stijl en grandeur onder de rook van Amsterdam, die voor
           uw gasten goed bereikbaar is, waar u gratis kunt parkeren, en die niet gebonden is aan welke cateraar of merk drank
           dan ook, informeer dan bij ons voor deze perfecte locatie.', 500, 10, 'Buiksloterkerk',
        'Neem contact met ons op voor een offerte op maat.', 2, 1),
       (2, 'Korte beschrijving 1.', null, null, 'Landgoed', null, 1, 1),
       (3, 'Korte beschrijving 2.', 900, 500, 'Mooie toren', 'Onze prijsinformatie.', 3, 1),
       (4, 'Korte beschrijving 3.', 700, 75, 'Gras', 'Onze prijsinformatie.', 2, 1),
       (5, 'Welkom bij van der valk Almere', 500, 100, 'Van der Valk Hotel Almere', 'Op aanvraag', 4, 1),
       (6, 'Welkom bij van der valk Amsterdam', 700, 100, 'Van der Valk Hotel Amsterdam', 'Op aanvraag', 4, 1);

# Venue
INSERT INTO venue(id, city, house_number, postal_code, street)
VALUES (1, 'Amsterdam', '40', '1034VZ', 'Buiksloterkerkerdijk'),
       (2, 'Amsterdam', '20', '1234TP', 'Sloterdijk'),
       (3, 'Utrecht', '10', '1584PO', 'Grote Weg'),
       (4, 'Amsterdam', '60', '1034VZ', 'GroteKerkerDijk'),
       (5, 'Almere', '90', '1190PT', 'HogeStraat'),
       (6, 'Amsterdam', '20A', '1100TY', 'Amsterdamseweg');

# Venue Room
INSERT INTO venue_room(id, max_guests, min_guests, name, square_meter, venue)
VALUES (1, 100, 10, 'Kerkzaal', null, 1),
       (2, null, null, 'Karkasi', null, 2),
       (3, 200, 20, 'Plazoo', 300, 3),
       (4, 300, 60, 'Kleine Zaal', 100, 4),
       (5, 250, 50, 'Grote Zaal', 500, 4),
       (6, 100, 20, 'Sydney', 200, 5),
       (7, 200, 40, 'Athene', 250, 5),
       (8, 300, 50, 'Berlin', 400, 5),
       (9, 800, 200, 'Mega Zaal', 1000, 6),
       (10, 50, 10, 'Kleine Zaal', 100, 6),
       (11, 300, 50, 'Grote Zaal', 400, 6);
