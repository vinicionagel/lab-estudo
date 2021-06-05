CREATE TABLE self_register_user (
        id CHARACTER VARYING NOT NULL,
        name CHARACTER VARYING NOT NULL,
        email CHARACTER VARYING NOT NULL,
        pass CHARACTER VARYING NOT NULL,
        created TIMESTAMP,
        CONSTRAINT "self_register_pk" PRIMARY KEY (id)
);
