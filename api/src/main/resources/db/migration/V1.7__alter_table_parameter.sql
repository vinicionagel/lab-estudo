ALTER TABLE parameter
    ADD COLUMN description CHARACTER VARYING NOT NULL;

INSERT INTO parameter(key, value, created, description)
    VALUES ('accountConfirmationPeriod', '12', now(), 'Period which is valid to confirm account creation (12 hours)');

