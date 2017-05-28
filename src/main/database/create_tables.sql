-- Sequence: public.piggybanks_id_seq

-- DROP SEQUENCE public.piggybanks_id_seq;

CREATE SEQUENCE public.piggybanks_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.piggybanks_id_seq
  OWNER TO ldyginzhuqjqyp;


-- Table: public.piggybanks

-- DROP TABLE public.piggybanks;

CREATE TABLE public.piggybanks
(
  id integer NOT NULL DEFAULT nextval('piggybanks_id_seq'::regclass),
  name text,
  short_description text,
  target numeric,
  current numeric,
  long_description text,
  picture_url text,
  CONSTRAINT piggybank_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.piggybanks
  OWNER TO ldyginzhuqjqyp;