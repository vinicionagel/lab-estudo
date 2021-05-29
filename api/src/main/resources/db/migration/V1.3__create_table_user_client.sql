CREATE TABLE oauth_client_details (
  client_id CHARACTER VARYING PRIMARY KEY,
  resource_ids CHARACTER VARYING,
  client_secret CHARACTER VARYING,
  scope CHARACTER VARYING,
  authorized_grant_types CHARACTER VARYING,
  web_server_redirect_uri CHARACTER VARYING,
  authorities CHARACTER VARYING,
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information CHARACTER VARYING,
  autoapprove CHARACTER VARYING
);

INSERT INTO oauth_client_details
(
  client_id,
  client_secret,
  scope,
  authorized_grant_types,
  web_server_redirect_uri,
  authorities,
  access_token_validity,
  refresh_token_validity,
  additional_information,
  autoapprove
) VALUES (
  'app',
  '$2a$10$NyCoRnNaCKDzgstGAdJc9unmFEp.i0iJLAlXB.1cFBwtRZQv5B9zm',
  'READ,WRITE',
  'password,refresh_token',
  null,
  null,
  3600,
  86400,
  null,
  true
);
