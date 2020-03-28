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