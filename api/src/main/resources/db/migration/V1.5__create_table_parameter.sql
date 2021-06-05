CREATE TABLE parameter (
    key CHARACTER VARYING NOT NULL,
    value CHARACTER VARYING NOT NULL,
    created TIMESTAMP,
    updated timestamp,
    CONSTRAINT "parameter_pk" PRIMARY KEY (key)
)
