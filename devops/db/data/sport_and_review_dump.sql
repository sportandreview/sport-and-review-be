--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_auto_evaluation; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_auto_evaluation (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    player_user_id bigint,
    phisical_level double precision,
    sport_id bigint,
    level double precision,
    competitive_level double precision,
    number_of_trainings double precision
);


--
-- Name: app_auto_evaluation_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_auto_evaluation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_auto_evaluation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_auto_evaluation_seq; Type: SEQUENCE; Schema: public; Owner: 
--

CREATE SEQUENCE public.app_auto_evaluation_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: app_club; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_club (
    id bigint NOT NULL,
    uuid character varying(255),
    name character varying(255),
    description character varying(255),
    phone character varying(255),
    city character varying(255),
    mail character varying(255),
    address character varying(255),
    club_logo character varying(255),
    booking_policy character varying(255),
    club_rating double precision,
    location character varying(255),
    owner_id bigint
);


--
-- Name: app_club_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_club ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_club_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_club_opening_day; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_club_opening_day (
    club_id bigint NOT NULL,
    opening_day_id bigint NOT NULL
);


--
-- Name: app_club_review; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_club_review (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    player_user_id bigint,
    club_id bigint,
    customer_services double precision,
    locker_room double precision,
    services double precision,
    note character varying(255),
    game_match_id bigint
);


--
-- Name: app_club_review_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_club_review ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_club_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_club_services; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_club_services (
    club_id bigint NOT NULL,
    services_id bigint NOT NULL
);


--
-- Name: app_favorite_clubs_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_favorite_clubs_player_user (
    player_user_id bigint NOT NULL,
    club_id bigint NOT NULL
);


--
-- Name: app_favorite_fields_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_favorite_fields_player_user (
    player_user_id bigint NOT NULL,
    field_id bigint NOT NULL
);


--
-- Name: app_favorite_highlights_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_favorite_highlights_player_user (
    highlight_id bigint NOT NULL,
    player_user_id bigint NOT NULL
);


--
-- Name: app_favorite_team_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_favorite_team_player_user (
    team_id bigint NOT NULL,
    player_user_id bigint NOT NULL
);


--
-- Name: app_field; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_field (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    name character varying(255),
    field_image character varying(255),
    hour_range_start timestamp(6) with time zone,
    hour_range_end timestamp(6) with time zone,
    price double precision,
    covered boolean,
    size double precision,
    rating double precision,
    description character varying(255),
    ground_type_id bigint,
    club_id bigint
);


--
-- Name: app_field_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_field ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_field_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_field_review; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_field_review (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    game_match_id bigint,
    field_id bigint,
    player_user_id bigint,
    value double precision,
    note character varying(255)
);


--
-- Name: app_field_review_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_field_review ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_field_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_filter; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_filter (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    filter_name character varying(255),
    club_id bigint,
    field_id bigint
);


--
-- Name: app_filter_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_filter ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_filter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_friends; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_friends (
    player_user_1_id bigint NOT NULL,
    player_user_2_id bigint NOT NULL
);


--
-- Name: app_game_level; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_game_level (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_game_level_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_game_level ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_game_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_game_match; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_game_match (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    state_id bigint,
    date_game timestamp(6) with time zone,
    game_level_id bigint,
    requested_date timestamp(6) with time zone,
    organizer_id bigint,
    field_id bigint,
    gender_team_id bigint,
    winning_team_id bigint
);


--
-- Name: app_game_match_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_game_match ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_game_match_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_game_match_team; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_game_match_team (
    game_match_id bigint NOT NULL,
    team_id bigint NOT NULL
);


--
-- Name: app_gender_type; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_gender_type (
    id bigint NOT NULL,
    uuid character varying(255),
    description character varying(255)
);


--
-- Name: app_gender_type_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_gender_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_gender_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_ground_type; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_ground_type (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_ground_type_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_ground_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_ground_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_highlight; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_highlight (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    video_url character varying(255),
    creation_date timestamp(6) with time zone,
    thumbnail character varying(255),
    game_match_id bigint,
    player_user_id bigint
);


--
-- Name: app_highlight_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_highlight ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_highlight_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_invitation; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_invitation (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    date timestamp(6) with time zone,
    game_match_id bigint,
    invitation_state_id bigint,
    player_user_id bigint
);


--
-- Name: app_invitation_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_invitation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_invitation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_invitation_state; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_invitation_state (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_invitation_state_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_invitation_state ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_invitation_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_join_request; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_join_request (
    invitation_id bigint NOT NULL,
    player_user_id bigint NOT NULL,
    join_request_state_id bigint,
    id bigint NOT NULL,
    uuid character varying(255)
);


--
-- Name: app_join_request_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

CREATE SEQUENCE public.app_join_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: app_join_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: 
--

ALTER SEQUENCE public.app_join_request_id_seq OWNED BY public.app_join_request.id;


--
-- Name: app_join_request_invitation_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_join_request ALTER COLUMN invitation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_join_request_invitation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_join_request_state; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_join_request_state (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_join_request_state_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_join_request_state ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_join_request_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_match_state; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_match_state (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_match_state_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_match_state ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.app_match_state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_opening_day; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_opening_day (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255),
    opening_time timestamp(6) with time zone,
    closing_time timestamp(6) with time zone
);


--
-- Name: app_opening_day_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_opening_day ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_opening_day_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_owner; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_owner (
    id bigint NOT NULL,
    uuid character varying(255),
    description character varying(255),
    partita_iva character varying(255)
);


--
-- Name: app_owner_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_owner ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_owner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_payment; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_payment (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    is_partial boolean,
    amount double precision,
    partial_amount double precision,
    transaction_code character varying(255),
    booking_policy character varying(255),
    player_user_id bigint,
    game_match_id bigint,
    payment_type_id bigint
);


--
-- Name: app_payment_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_payment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_payment_type; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_payment_type (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);


--
-- Name: app_payment_type_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_payment_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_payment_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_photogallery; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_photogallery (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    image character varying(255),
    club_id bigint
);


--
-- Name: app_photogallery_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_photogallery ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_photogallery_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_player_review; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_player_review (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    player_user_id bigint,
    made_by_player_user_id bigint,
    game_match_id bigint,
    sport_id bigint,
    physical_ability double precision,
    behavior double precision,
    tactical_ability double precision,
    technical_ability double precision
);




--
-- Name: app_player_review_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_player_review ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_player_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_player_user (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    mobile_enabled boolean,
    nickname character varying(255),
    technical_level double precision,
    tactical_level double precision,
    behavior double precision,
    technical_ability double precision,
    name character varying(255),
    surname character varying(255),
    mobile_phone character varying(255),
    email character varying(255),
    password character varying(255),
    birth_date timestamp(6) with time zone,
    profile_image character varying(255),
    cap character varying(255),
    city character varying(255),
    gender_type_id bigint,
    phone character varying(255),
    physical_level double precision,
    role character varying(255),
    CONSTRAINT app_player_user_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);




--
-- Name: app_player_user_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_player_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_player_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_review_from_host; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_review_from_host (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    player_user_id bigint,
    owner_id bigint,
    behavior double precision
);




--
-- Name: app_review_from_host_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_review_from_host ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_review_from_host_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_services; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_services (
    id bigint NOT NULL,
    uuid character varying(255),
    name character varying(255),
    icon character varying(255),
    price double precision,
    services_type_id bigint
);




--
-- Name: app_services_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_services ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_services_type; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_services_type (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);




--
-- Name: app_services_type_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_services_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_services_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_sport; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_sport (
    id bigint NOT NULL,
    max_player integer,
    suggested_service_id bigint,
    uuid character varying(255),
    name character varying(255)
);




--
-- Name: app_sport_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_sport ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_sport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_sport_point; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_sport_point (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    description character varying(255)
);




--
-- Name: app_sport_point_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_sport_point ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_sport_point_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_sport_sport_point; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_sport_sport_point (
    sport_id bigint NOT NULL,
    sport_point_id bigint NOT NULL
);




--
-- Name: app_team; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_team (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    name character varying(255)
);




--
-- Name: app_team_id_seq; Type: SEQUENCE; Schema: public; Owner: 
--

ALTER TABLE public.app_team ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.app_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: app_team_player_user; Type: TABLE; Schema: public; Owner: 
--

CREATE TABLE public.app_team_player_user (
    team_id bigint NOT NULL,
    player_user_id bigint NOT NULL
);


--
-- Name: app_join_request id; Type: DEFAULT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request ALTER COLUMN id SET DEFAULT nextval('public.app_join_request_id_seq'::regclass);


--
-- Data for Name: app_auto_evaluation; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_auto_evaluation (id, uuid, player_user_id, phisical_level, sport_id, level, competitive_level, number_of_trainings) FROM stdin;
\.


--
-- Data for Name: app_club; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_club (id, uuid, name, description, phone, city, mail, address, club_logo, booking_policy, club_rating, location, owner_id) FROM stdin;
\.


--
-- Data for Name: app_club_opening_day; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_club_opening_day (club_id, opening_day_id) FROM stdin;
\.


--
-- Data for Name: app_club_review; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_club_review (id, uuid, player_user_id, club_id, customer_services, locker_room, services, note, game_match_id) FROM stdin;
\.


--
-- Data for Name: app_club_services; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_club_services (club_id, services_id) FROM stdin;
\.


--
-- Data for Name: app_favorite_clubs_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_favorite_clubs_player_user (player_user_id, club_id) FROM stdin;
\.


--
-- Data for Name: app_favorite_fields_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_favorite_fields_player_user (player_user_id, field_id) FROM stdin;
\.


--
-- Data for Name: app_favorite_highlights_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_favorite_highlights_player_user (highlight_id, player_user_id) FROM stdin;
\.


--
-- Data for Name: app_favorite_team_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_favorite_team_player_user (team_id, player_user_id) FROM stdin;
\.


--
-- Data for Name: app_field; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_field (id, uuid, name, field_image, hour_range_start, hour_range_end, price, covered, size, rating, description, ground_type_id, club_id) FROM stdin;
\.


--
-- Data for Name: app_field_review; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_field_review (id, uuid, game_match_id, field_id, player_user_id, value, note) FROM stdin;
\.


--
-- Data for Name: app_filter; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_filter (id, uuid, filter_name, club_id, field_id) FROM stdin;
\.


--
-- Data for Name: app_friends; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_friends (player_user_1_id, player_user_2_id) FROM stdin;
\.


--
-- Data for Name: app_game_level; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_game_level (id, uuid, description) FROM stdin;
1	100cf7ab-fa13-48e5-a414-606628dfdb06	AMATEUR
2	1f5b79b9-213b-4942-9660-54de43b7ef72	SEMI-PROFESSIONAL
3	284ceb95-5b2d-43ca-ab83-427a1d65812e	PROFESSIONAL
\.


--
-- Data for Name: app_game_match; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_game_match (id, uuid, state_id, date_game, game_level_id, requested_date, organizer_id, field_id, gender_team_id, winning_team_id) FROM stdin;
\.


--
-- Data for Name: app_game_match_team; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_game_match_team (game_match_id, team_id) FROM stdin;
\.


--
-- Data for Name: app_gender_type; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_gender_type (id, uuid, description) FROM stdin;
1	6b8acbc6-defb-49fe-9c6c-ae20de7d12c4	MALE
2	cf9829b2-00f0-4c8c-9a2d-a7dc1590e1bd	FEMALE
3	eea5f0cc-0d4c-4119-8e75-c109596132ce	NOT SPECIFIED
\.


--
-- Data for Name: app_ground_type; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_ground_type (id, uuid, description) FROM stdin;
\.


--
-- Data for Name: app_highlight; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_highlight (id, uuid, video_url, creation_date, thumbnail, game_match_id, player_user_id) FROM stdin;
\.


--
-- Data for Name: app_invitation; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_invitation (id, uuid, date, game_match_id, invitation_state_id, player_user_id) FROM stdin;
\.


--
-- Data for Name: app_invitation_state; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_invitation_state (id, uuid, description) FROM stdin;
1	198e780c-47f8-4910-953e-d959ed4fca4a	ACCEPTED
2	43d4d9b6-2159-4c22-bd9b-190b641d40ab	NOT ACCEPTED
\.


--
-- Data for Name: app_join_request; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_join_request (invitation_id, player_user_id, join_request_state_id, id, uuid) FROM stdin;
\.


--
-- Data for Name: app_join_request_state; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_join_request_state (id, uuid, description) FROM stdin;
1	3d9a897c-9e71-4753-b6b7-ed749b12f5d7	ACCEPTED
2	b414bca2-cf68-4b97-b10d-3dce5b1d3dfc	NOT ACCEPTED
\.


--
-- Data for Name: app_match_state; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_match_state (id, uuid, description) FROM stdin;
1	56dee5a7-a739-4d20-a182-3e0c0764bc18	PENDING
2	9f70feda-768e-4bfa-ac71-1bb6bc741dcd	BOOKED
3	1b86a175-fade-46f7-b78a-ab2486ad52ec	DENIED
4	26011df6-ba24-43d0-a4ab-6e6cda7b0134	CREATED
5	f02240bc-ba1f-40be-9658-358e5fb852ba	FINISHED
\.


--
-- Data for Name: app_opening_day; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_opening_day (id, uuid, description, opening_time, closing_time) FROM stdin;
\.


--
-- Data for Name: app_owner; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_owner (id, uuid, description, partita_iva) FROM stdin;
\.


--
-- Data for Name: app_payment; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_payment (id, uuid, is_partial, amount, partial_amount, transaction_code, booking_policy, player_user_id, game_match_id, payment_type_id) FROM stdin;
\.


--
-- Data for Name: app_payment_type; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_payment_type (id, uuid, description) FROM stdin;
1	63776874-7846-4e4a-9985-09c8e7124961	SINGLE
2	612fcefc-0583-47d6-9cbb-232d3fef8107	PAYPAL
3	d2e352a9-2d75-4646-aaa2-e64dafb59f6a	LOCAL
4	c18a8004-af96-4d60-a327-b86a4b64b60b	CARD
\.


--
-- Data for Name: app_photogallery; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_photogallery (id, uuid, image, club_id) FROM stdin;
\.


--
-- Data for Name: app_player_review; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_player_review (id, uuid, player_user_id, made_by_player_user_id, game_match_id, sport_id, physical_ability, behavior, tactical_ability, technical_ability) FROM stdin;
\.


--
-- Data for Name: app_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_player_user (id, uuid, mobile_enabled, nickname, technical_level, tactical_level, behavior, technical_ability, name, surname, mobile_phone, email, password, birth_date, profile_image, cap, city, gender_type_id, phone, physical_level, role) FROM stdin;
\.


--
-- Data for Name: app_review_from_host; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_review_from_host (id, uuid, player_user_id, owner_id, behavior) FROM stdin;
\.


--
-- Data for Name: app_services; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_services (id, uuid, name, icon, price, services_type_id) FROM stdin;
\.


--
-- Data for Name: app_services_type; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_services_type (id, uuid, description) FROM stdin;
1	42e31e47-e22d-4af8-93da-0af432195258	FREE
2	b097d4e9-c5cf-471e-a8d2-68faf296ca37	SINGLE CHOICE
3	9fb7a189-6f12-4441-855b-ab42d41f622a	MULTIPLE
\.


--
-- Data for Name: app_sport; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_sport (id, max_player, suggested_service_id, uuid, name) FROM stdin;
\.


--
-- Data for Name: app_sport_point; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_sport_point (id, uuid, description) FROM stdin;
\.


--
-- Data for Name: app_sport_sport_point; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_sport_sport_point (sport_id, sport_point_id) FROM stdin;
\.


--
-- Data for Name: app_team; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_team (id, uuid, name) FROM stdin;
\.


--
-- Data for Name: app_team_player_user; Type: TABLE DATA; Schema: public; Owner: 
--

COPY public.app_team_player_user (team_id, player_user_id) FROM stdin;
\.


--
-- Name: app_auto_evaluation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_auto_evaluation_id_seq', 13, true);


--
-- Name: app_auto_evaluation_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_auto_evaluation_seq', 51, true);


--
-- Name: app_club_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_club_id_seq', 17, true);


--
-- Name: app_club_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_club_review_id_seq', 1, false);


--
-- Name: app_field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_field_id_seq', 13, true);


--
-- Name: app_field_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_field_review_id_seq', 1, false);


--
-- Name: app_filter_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_filter_id_seq', 1, false);


--
-- Name: app_game_level_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_game_level_id_seq', 1, false);


--
-- Name: app_game_match_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_game_match_id_seq', 6, true);


--
-- Name: app_gender_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_gender_type_id_seq', 1, false);


--
-- Name: app_ground_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_ground_type_id_seq', 5, true);


--
-- Name: app_highlight_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_highlight_id_seq', 2, true);


--
-- Name: app_invitation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_invitation_id_seq', 1, false);


--
-- Name: app_invitation_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_invitation_state_id_seq', 1, false);


--
-- Name: app_join_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_join_request_id_seq', 1, false);


--
-- Name: app_join_request_invitation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_join_request_invitation_id_seq', 1, false);


--
-- Name: app_join_request_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_join_request_state_id_seq', 1, false);


--
-- Name: app_match_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_match_state_id_seq', 4, true);


--
-- Name: app_opening_day_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_opening_day_id_seq', 1, false);


--
-- Name: app_owner_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_owner_id_seq', 2, true);


--
-- Name: app_payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_payment_id_seq', 1, false);


--
-- Name: app_payment_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_payment_type_id_seq', 1, false);


--
-- Name: app_photogallery_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_photogallery_id_seq', 1, false);


--
-- Name: app_player_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_player_review_id_seq', 1, false);


--
-- Name: app_player_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_player_user_id_seq', 24, true);


--
-- Name: app_review_from_host_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_review_from_host_id_seq', 1, false);


--
-- Name: app_services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_services_id_seq', 6, true);


--
-- Name: app_services_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_services_type_id_seq', 1, false);


--
-- Name: app_sport_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_sport_id_seq', 6, true);


--
-- Name: app_sport_point_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_sport_point_id_seq', 1, true);


--
-- Name: app_team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: 
--

SELECT pg_catalog.setval('public.app_team_id_seq', 2, true);


--
-- Name: app_auto_evaluation app_auto_evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_auto_evaluation
    ADD CONSTRAINT app_auto_evaluation_pkey PRIMARY KEY (id);


--
-- Name: app_club app_club_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club
    ADD CONSTRAINT app_club_pkey PRIMARY KEY (id);


--
-- Name: app_club_review app_club_review_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_review
    ADD CONSTRAINT app_club_review_pkey PRIMARY KEY (id);


--
-- Name: app_club_services app_club_services_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_services
    ADD CONSTRAINT app_club_services_pkey PRIMARY KEY (club_id, services_id);


--
-- Name: app_favorite_team_player_user app_favorite_team_player_user_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_team_player_user
    ADD CONSTRAINT app_favorite_team_player_user_pkey PRIMARY KEY (team_id, player_user_id);


--
-- Name: app_field app_field_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field
    ADD CONSTRAINT app_field_pkey PRIMARY KEY (id);


--
-- Name: app_field_review app_field_review_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field_review
    ADD CONSTRAINT app_field_review_pkey PRIMARY KEY (id);


--
-- Name: app_filter app_filter_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_filter
    ADD CONSTRAINT app_filter_pkey PRIMARY KEY (id);


--
-- Name: app_game_level app_game_level_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_level
    ADD CONSTRAINT app_game_level_pkey PRIMARY KEY (id);


--
-- Name: app_game_match app_game_match_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_pkey PRIMARY KEY (id);


--
-- Name: app_gender_type app_gender_type_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_gender_type
    ADD CONSTRAINT app_gender_type_pkey PRIMARY KEY (id);


--
-- Name: app_ground_type app_ground_type_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_ground_type
    ADD CONSTRAINT app_ground_type_pkey PRIMARY KEY (id);


--
-- Name: app_highlight app_highlight_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_highlight
    ADD CONSTRAINT app_highlight_pkey PRIMARY KEY (id);


--
-- Name: app_invitation app_invitation_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_invitation
    ADD CONSTRAINT app_invitation_pkey PRIMARY KEY (id);


--
-- Name: app_invitation_state app_invitation_state_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_invitation_state
    ADD CONSTRAINT app_invitation_state_pkey PRIMARY KEY (id);


--
-- Name: app_join_request_state app_join_request_state_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request_state
    ADD CONSTRAINT app_join_request_state_pkey PRIMARY KEY (id);


--
-- Name: app_match_state app_match_state_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_match_state
    ADD CONSTRAINT app_match_state_pkey PRIMARY KEY (id);


--
-- Name: app_opening_day app_opening_day_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_opening_day
    ADD CONSTRAINT app_opening_day_pkey PRIMARY KEY (id);


--
-- Name: app_owner app_owner_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_owner
    ADD CONSTRAINT app_owner_pkey PRIMARY KEY (id);


--
-- Name: app_payment app_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_payment
    ADD CONSTRAINT app_payment_pkey PRIMARY KEY (id);


--
-- Name: app_payment_type app_payment_type_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_payment_type
    ADD CONSTRAINT app_payment_type_pkey PRIMARY KEY (id);


--
-- Name: app_photogallery app_photogallery_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_photogallery
    ADD CONSTRAINT app_photogallery_pkey PRIMARY KEY (id);


--
-- Name: app_player_review app_player_review_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_review
    ADD CONSTRAINT app_player_review_pkey PRIMARY KEY (id);


--
-- Name: app_player_user app_player_user_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_user
    ADD CONSTRAINT app_player_user_pkey PRIMARY KEY (id);


--
-- Name: app_review_from_host app_review_from_host_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_review_from_host
    ADD CONSTRAINT app_review_from_host_pkey PRIMARY KEY (id);


--
-- Name: app_services app_services_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_services
    ADD CONSTRAINT app_services_pkey PRIMARY KEY (id);


--
-- Name: app_services_type app_services_type_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_services_type
    ADD CONSTRAINT app_services_type_pkey PRIMARY KEY (id);


--
-- Name: app_sport app_sport_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport
    ADD CONSTRAINT app_sport_pkey PRIMARY KEY (id);


--
-- Name: app_sport_point app_sport_point_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport_point
    ADD CONSTRAINT app_sport_point_pkey PRIMARY KEY (id);


--
-- Name: app_team app_team_pkey; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_team
    ADD CONSTRAINT app_team_pkey PRIMARY KEY (id);


--
-- Name: app_favorite_fields_player_user pk_app_app_favorite_fields_player_user; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_fields_player_user
    ADD CONSTRAINT pk_app_app_favorite_fields_player_user PRIMARY KEY (player_user_id, field_id);


--
-- Name: app_club_opening_day pk_app_club_opening_day; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_opening_day
    ADD CONSTRAINT pk_app_club_opening_day PRIMARY KEY (club_id, opening_day_id);


--
-- Name: app_favorite_clubs_player_user pk_app_favorite_clubs_player_user; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_clubs_player_user
    ADD CONSTRAINT pk_app_favorite_clubs_player_user PRIMARY KEY (player_user_id, club_id);


--
-- Name: app_favorite_highlights_player_user pk_app_favorite_highlights_player_user; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_highlights_player_user
    ADD CONSTRAINT pk_app_favorite_highlights_player_user PRIMARY KEY (highlight_id, player_user_id);


--
-- Name: app_friends pk_app_friends_player_user; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_friends
    ADD CONSTRAINT pk_app_friends_player_user PRIMARY KEY (player_user_1_id, player_user_2_id);


--
-- Name: app_game_match_team pk_app_game_match_team; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match_team
    ADD CONSTRAINT pk_app_game_match_team PRIMARY KEY (game_match_id, team_id);


--
-- Name: app_join_request pk_app_join_request; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request
    ADD CONSTRAINT pk_app_join_request PRIMARY KEY (invitation_id, player_user_id);


--
-- Name: app_sport_sport_point pk_app_sport_sport_point; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport_sport_point
    ADD CONSTRAINT pk_app_sport_sport_point PRIMARY KEY (sport_id, sport_point_id);


--
-- Name: app_team_player_user pk_app_team_player_user; Type: CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_team_player_user
    ADD CONSTRAINT pk_app_team_player_user PRIMARY KEY (team_id, player_user_id);


--
-- Name: app_auto_evaluation app_auto_evaluation_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_auto_evaluation
    ADD CONSTRAINT app_auto_evaluation_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_auto_evaluation app_auto_evaluation_sport_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_auto_evaluation
    ADD CONSTRAINT app_auto_evaluation_sport_id_fkey FOREIGN KEY (sport_id) REFERENCES public.app_sport(id);


--
-- Name: app_club_review app_club_review_club_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_review
    ADD CONSTRAINT app_club_review_club_id_fkey FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_club_review app_club_review_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_review
    ADD CONSTRAINT app_club_review_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_club_review app_club_review_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_review
    ADD CONSTRAINT app_club_review_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_club_services app_club_services_club_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_services
    ADD CONSTRAINT app_club_services_club_id_fkey FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_club_services app_club_services_services_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_services
    ADD CONSTRAINT app_club_services_services_id_fkey FOREIGN KEY (services_id) REFERENCES public.app_services(id);


--
-- Name: app_field app_field_ground_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field
    ADD CONSTRAINT app_field_ground_type_id_fkey FOREIGN KEY (ground_type_id) REFERENCES public.app_ground_type(id);


--
-- Name: app_field_review app_field_review_field_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field_review
    ADD CONSTRAINT app_field_review_field_id_fkey FOREIGN KEY (field_id) REFERENCES public.app_field(id);


--
-- Name: app_field_review app_field_review_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field_review
    ADD CONSTRAINT app_field_review_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_field_review app_field_review_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field_review
    ADD CONSTRAINT app_field_review_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_filter app_filter_club_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_filter
    ADD CONSTRAINT app_filter_club_id_fkey FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_filter app_filter_field_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_filter
    ADD CONSTRAINT app_filter_field_id_fkey FOREIGN KEY (field_id) REFERENCES public.app_field(id);


--
-- Name: app_game_match app_game_match_field_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_field_id_fkey FOREIGN KEY (field_id) REFERENCES public.app_field(id);


--
-- Name: app_game_match app_game_match_gender_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_gender_team_id_fkey FOREIGN KEY (gender_team_id) REFERENCES public.app_gender_type(id);


--
-- Name: app_game_match app_game_match_level_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_level_fkey FOREIGN KEY (game_level_id) REFERENCES public.app_game_level(id);


--
-- Name: app_game_match app_game_match_organizer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_organizer_id_fkey FOREIGN KEY (organizer_id) REFERENCES public.app_player_user(id);


--
-- Name: app_game_match app_game_match_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_state_fkey FOREIGN KEY (state_id) REFERENCES public.app_match_state(id);


--
-- Name: app_game_match app_game_match_winning_team_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match
    ADD CONSTRAINT app_game_match_winning_team_id_fkey FOREIGN KEY (winning_team_id) REFERENCES public.app_team(id);


--
-- Name: app_highlight app_highlight_created_by_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_highlight
    ADD CONSTRAINT app_highlight_created_by_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_highlight app_highlight_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_highlight
    ADD CONSTRAINT app_highlight_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_invitation app_invitation_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_invitation
    ADD CONSTRAINT app_invitation_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_invitation app_invitation_invitation_state_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_invitation
    ADD CONSTRAINT app_invitation_invitation_state_id_fkey FOREIGN KEY (invitation_state_id) REFERENCES public.app_invitation_state(id);


--
-- Name: app_invitation app_invitation_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_invitation
    ADD CONSTRAINT app_invitation_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_payment app_payment_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_payment
    ADD CONSTRAINT app_payment_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_payment app_payment_payed_by_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_payment
    ADD CONSTRAINT app_payment_payed_by_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_payment app_payment_payment_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_payment
    ADD CONSTRAINT app_payment_payment_type_id_fkey FOREIGN KEY (payment_type_id) REFERENCES public.app_payment_type(id);


--
-- Name: app_photogallery app_photogallery_club_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_photogallery
    ADD CONSTRAINT app_photogallery_club_id_fkey FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_player_review app_player_review_game_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_review
    ADD CONSTRAINT app_player_review_game_match_id_fkey FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_player_review app_player_review_made_by_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_review
    ADD CONSTRAINT app_player_review_made_by_id_fkey FOREIGN KEY (made_by_player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_player_review app_player_review_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_review
    ADD CONSTRAINT app_player_review_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_player_review app_player_review_sport_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_review
    ADD CONSTRAINT app_player_review_sport_id_fkey FOREIGN KEY (sport_id) REFERENCES public.app_sport(id);


--
-- Name: app_player_user app_player_user_gender_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_player_user
    ADD CONSTRAINT app_player_user_gender_type_id_fkey FOREIGN KEY (gender_type_id) REFERENCES public.app_gender_type(id);


--
-- Name: app_review_from_host app_review_from_host_owner_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_review_from_host
    ADD CONSTRAINT app_review_from_host_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES public.app_owner(id);


--
-- Name: app_review_from_host app_review_from_host_player_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_review_from_host
    ADD CONSTRAINT app_review_from_host_player_user_id_fkey FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_services app_services_services_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_services
    ADD CONSTRAINT app_services_services_type_id_fkey FOREIGN KEY (services_type_id) REFERENCES public.app_services_type(id);


--
-- Name: app_sport app_sport_suggested_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport
    ADD CONSTRAINT app_sport_suggested_service_id_fkey FOREIGN KEY (suggested_service_id) REFERENCES public.app_services(id);


--
-- Name: app_club_opening_day fk30sehmxvxowsbhh1lnwep3tqn; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_opening_day
    ADD CONSTRAINT fk30sehmxvxowsbhh1lnwep3tqn FOREIGN KEY (opening_day_id) REFERENCES public.app_opening_day(id);


--
-- Name: app_join_request fk4qsof9rcb0ruv34nihsdu57q; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request
    ADD CONSTRAINT fk4qsof9rcb0ruv34nihsdu57q FOREIGN KEY (invitation_id) REFERENCES public.app_invitation(id);


--
-- Name: app_favorite_team_player_user fk7he2rmey4w9xaikmxsm24vw9m; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_team_player_user
    ADD CONSTRAINT fk7he2rmey4w9xaikmxsm24vw9m FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_friends fk83rman0ucyw17fu062j6k0dqm; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_friends
    ADD CONSTRAINT fk83rman0ucyw17fu062j6k0dqm FOREIGN KEY (player_user_2_id) REFERENCES public.app_player_user(id);


--
-- Name: app_favorite_clubs_player_user fk83wcekq9gonw8plqbpr1g8g49; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_clubs_player_user
    ADD CONSTRAINT fk83wcekq9gonw8plqbpr1g8g49 FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_favorite_fields_player_user fk9bgcf8beanhwxd0v7ty36rfsr; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_fields_player_user
    ADD CONSTRAINT fk9bgcf8beanhwxd0v7ty36rfsr FOREIGN KEY (field_id) REFERENCES public.app_field(id);


--
-- Name: app_favorite_fields_player_user fkar6022n5d6hxsjy2y4lo8ocer; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_fields_player_user
    ADD CONSTRAINT fkar6022n5d6hxsjy2y4lo8ocer FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_team_player_user fkbskwv7njpb1aobykd0h4twnaa; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_team_player_user
    ADD CONSTRAINT fkbskwv7njpb1aobykd0h4twnaa FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_favorite_team_player_user fkcy17rpdta4v14exd9cld654qp; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_team_player_user
    ADD CONSTRAINT fkcy17rpdta4v14exd9cld654qp FOREIGN KEY (team_id) REFERENCES public.app_player_user(id);


--
-- Name: app_favorite_highlights_player_user fke8c0787nya2b36bh7fqjslw1n; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_highlights_player_user
    ADD CONSTRAINT fke8c0787nya2b36bh7fqjslw1n FOREIGN KEY (highlight_id) REFERENCES public.app_highlight(id);


--
-- Name: app_friends fkerr4hymaa8togqqnp79ckf2ax; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_friends
    ADD CONSTRAINT fkerr4hymaa8togqqnp79ckf2ax FOREIGN KEY (player_user_1_id) REFERENCES public.app_player_user(id);


--
-- Name: app_join_request fkf4ugwr04ggnq6pgx0j37ltox7; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request
    ADD CONSTRAINT fkf4ugwr04ggnq6pgx0j37ltox7 FOREIGN KEY (join_request_state_id) REFERENCES public.app_join_request_state(id);


--
-- Name: app_team_player_user fkh7epjsd01gt67ydsadoixj917; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_team_player_user
    ADD CONSTRAINT fkh7epjsd01gt67ydsadoixj917 FOREIGN KEY (team_id) REFERENCES public.app_team(id);


--
-- Name: app_club_opening_day fkk0ik5x8kqfj91497bnnflwtbl; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club_opening_day
    ADD CONSTRAINT fkk0ik5x8kqfj91497bnnflwtbl FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_join_request fkl5jr9evw4yg160j2vl6h1qnhg; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_join_request
    ADD CONSTRAINT fkl5jr9evw4yg160j2vl6h1qnhg FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_field fkletaxc42u0pv2nv06j81jtbgr; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_field
    ADD CONSTRAINT fkletaxc42u0pv2nv06j81jtbgr FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_game_match_team fknyspayx75fe8vxltnc2hybk8b; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match_team
    ADD CONSTRAINT fknyspayx75fe8vxltnc2hybk8b FOREIGN KEY (game_match_id) REFERENCES public.app_game_match(id);


--
-- Name: app_sport_sport_point fkodp2uxcldnbujmti61t1g40lu; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport_sport_point
    ADD CONSTRAINT fkodp2uxcldnbujmti61t1g40lu FOREIGN KEY (sport_point_id) REFERENCES public.app_sport_point(id);


--
-- Name: app_game_match_team fkot7psompwom3a17ixqqnb8na5; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_game_match_team
    ADD CONSTRAINT fkot7psompwom3a17ixqqnb8na5 FOREIGN KEY (team_id) REFERENCES public.app_team(id);


--
-- Name: app_sport_sport_point fkp0bkigbfne6aoqsa00g2jn3ts; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_sport_sport_point
    ADD CONSTRAINT fkp0bkigbfne6aoqsa00g2jn3ts FOREIGN KEY (sport_id) REFERENCES public.app_sport(id);


--
-- Name: app_favorite_highlights_player_user fkpeaig18pm3uhhmr00qk7bkgw9; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_highlights_player_user
    ADD CONSTRAINT fkpeaig18pm3uhhmr00qk7bkgw9 FOREIGN KEY (player_user_id) REFERENCES public.app_player_user(id);


--
-- Name: app_favorite_clubs_player_user fkt4fl4pdkjoryx5oc1u9ovgf3u; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_favorite_clubs_player_user
    ADD CONSTRAINT fkt4fl4pdkjoryx5oc1u9ovgf3u FOREIGN KEY (club_id) REFERENCES public.app_club(id);


--
-- Name: app_club owner_id; Type: FK CONSTRAINT; Schema: public; Owner: 
--

ALTER TABLE ONLY public.app_club
    ADD CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES public.app_owner(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

