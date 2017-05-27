CREATE SEQUENCE public.events_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 6
  CACHE 1;

CREATE TABLE public.events
(
  eventname character varying,
  city character varying,
  dance character varying NOT NULL,
  id integer NOT NULL DEFAULT nextval('events_id_seq'::regclass),
  price double precision,
  date date,
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
