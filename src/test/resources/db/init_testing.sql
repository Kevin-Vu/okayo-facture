DROP SCHEMA PUBLIC CASCADE;
CREATE SCHEMA PUBLIC;


CREATE TABLE client
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    code_client text NOT NULL,
    adresse text NOT NULL,
    code_postal bigint NOT NULL
);

CREATE TABLE facture
(
    id bigint PRIMARY KEY NOT NULL,
    date_facturation timestamp WITHOUT TIME ZONE NOT NULL,
    date_echeance timestamp WITHOUT TIME ZONE NOT NULL,
    total_tva float NOT NULL,
    total_ht float NOT NULL,
    total_ttc float NOT NULL,
    fk_id_client integer REFERENCES client(id)
);

CREATE TABLE designation_catalogue
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    tva float NOT NULL
);

CREATE TABLE designation
(
    id bigint PRIMARY KEY NOT NULL,
    name text NOT NULL,
    tva float NOT NULL,
    prix_unit_ht float NOT NULL,
    quantite integer NOT NULL,
    reduction float,
    total_ht float NOT NULL,
    fk_id_facture integer REFERENCES facture(id)
);

CREATE SEQUENCE client_sequence START 1;
CREATE SEQUENCE facture_sequence START 1;
CREATE SEQUENCE designation_catalogue_sequence START 1;
CREATE SEQUENCE designation_sequence START 1;

-- INSERT DATA

INSERT INTO client(id,name,code_client,adresse,code_postal)
values
(1, 'Société Général', 'CU-018924-SG', '29 BD Haussmann', 75009),
(2, 'Dassault Systèmes', 'CU-642155-3DS', '10 rue Marcel Dassault', 78946),
(3, 'Renault', 'CU-854255-RNLT', '13 quai Alphonse Le Gallo', 92100)
;

ALTER sequence client_sequence restart WITH 4;

-- FAKE TTC
INSERT INTO facture(id, date_facturation, date_echeance, total_tva, total_ht, total_ttc, fk_id_client)
values
(1, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '100.00', '119.80', 1),
(2, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '299.00', '2545.99', 1),
(3, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '125.00', '169.42', 1),
(4, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '260.00', '645.06', 2),
(5, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '50.00', '119.50', 2),
(6, '2020-03-14 10:00:001', '2020-06-14 10:00:001', '19.80', '250.00', '25.89', 3)
;

ALTER sequence facture_sequence restart WITH 7;

INSERT INTO designation_catalogue(id, name, tva)
values
(1, 'Designation 1', '19.80'),
(2, 'Designation 2', '20.00'),
(3, 'Designation 3', '14.80'),
(4, 'Designation 4 new', '15.60')
;

ALTER sequence designation_catalogue_sequence restart WITH 5;

INSERT INTO designation(id, name, tva, prix_unit_ht, quantite, reduction, total_ht, fk_id_facture)
values
(1, 'Designation 1', '19.80', '100.00', 3, '0.00', '300.00', 1),
(2, 'Designation 2', '19.80', '100.00', 1, '50.00', '50.00', 1),
(3, 'Designation 1', '19.80', '100.00', 2, '60.00', '80.00', 2),
(4, 'Designation 3', '19.80', '100.00', 2, '60.00', '80.00', 2),
(5, 'Designation 4 old', '19.80', '50.00', 2, '0.00', '100.00', 2)
;

ALTER sequence designation_sequence restart WITH 6;

