--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.account (
    id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    CONSTRAINT account_role_check CHECK (((role)::text = ANY (ARRAY[('ADMIN'::character varying)::text, ('MANAGER'::character varying)::text, ('EMPLOYEE'::character varying)::text])))
);


ALTER TABLE public.account OWNER TO "user";

--
-- Name: account_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.account_seq OWNER TO "user";

--
-- Name: agent_point; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.agent_point (
    id integer NOT NULL,
    address character varying(255) NOT NULL,
    approved_apps_count integer NOT NULL,
    card_issuance_days_passed integer NOT NULL,
    issued_cards_count integer NOT NULL,
    join_time character varying(255) NOT NULL,
    materials_delivered boolean NOT NULL,
    location_id integer NOT NULL,
    CONSTRAINT agent_point_join_time_check CHECK (((join_time)::text = ANY (ARRAY[('YESTERDAY'::character varying)::text, ('LONG_AGO'::character varying)::text])))
);


ALTER TABLE public.agent_point OWNER TO "user";

--
-- Name: agent_point_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.agent_point_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.agent_point_seq OWNER TO "user";

--
-- Name: employee; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.employee (
    id integer NOT NULL,
    grade character varying(255) NOT NULL,
    location_address character varying(255) NOT NULL,
    account_id integer NOT NULL,
    location_id integer NOT NULL,
    CONSTRAINT employee_grade_check CHECK (((grade)::text = ANY (ARRAY[('JUNIOR'::character varying)::text, ('MIDDLE'::character varying)::text, ('SENIOR'::character varying)::text])))
);


ALTER TABLE public.employee OWNER TO "user";

--
-- Name: employee_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.employee_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.employee_seq OWNER TO "user";

--
-- Name: location; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.location (
    id integer NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL
);


ALTER TABLE public.location OWNER TO "user";

--
-- Name: location_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.location_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.location_seq OWNER TO "user";

--
-- Name: task; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.task (
    id integer NOT NULL,
    creation_time date NOT NULL,
    distance_to double precision NOT NULL,
    getting_time double precision NOT NULL,
    is_archived boolean NOT NULL,
    order_number integer NOT NULL,
    start_time time(6) without time zone NOT NULL,
    type character varying(255) NOT NULL,
    agent_point_id integer NOT NULL,
    employee_id integer,
    CONSTRAINT task_type_check CHECK (((type)::text = ANY (ARRAY[('DEPARTURE'::character varying)::text, ('TUITION'::character varying)::text, ('DELIVERY'::character varying)::text])))
);


ALTER TABLE public.task OWNER TO "user";

--
-- Name: task_manual; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.task_manual (
    id integer NOT NULL,
    json_manual character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    CONSTRAINT task_manual_type_check CHECK (((type)::text = ANY (ARRAY[('DEPARTURE'::character varying)::text, ('TUITION'::character varying)::text, ('DELIVERY'::character varying)::text])))
);


ALTER TABLE public.task_manual OWNER TO "user";

--
-- Name: task_manual_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.task_manual_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.task_manual_seq OWNER TO "user";

--
-- Name: task_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.task_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.task_seq OWNER TO "user";

--
-- Name: task_status; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.task_status (
    id integer NOT NULL,
    comment character varying(255) NOT NULL,
    is_completed boolean NOT NULL,
    task_id integer
);


ALTER TABLE public.task_status OWNER TO "user";

--
-- Name: task_status_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.task_status_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.task_status_seq OWNER TO "user";

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.account (id, full_name, login, password_hash, role) FROM stdin;
102	Ivanov Ivan Ivanovich	admin	$2a$10$4Igde0Xjla76hdoypkS36ukVNkF87ozf1ug3/ukX4/fzOSBfYmqwW	ADMIN
104	Дерягин Никита Владимирович	employee1	$2a$10$Dh88A7KKrnoK6e/G75wniu8.6XHTQeqXKA5iizHKfEflsdz6NmM2K	EMPLOYEE
105	Петрошев Валерий Павлович	employee2	$2a$10$80tLq0Yt5XibhBbSS4HGEO4aXpQSF6xHEGcQgI013uiQcwKnVOqgi	EMPLOYEE
106	Евдокимов Давид Тихонович	employee3	$2a$10$3IDYJIb2IBzy8iDpPHPdfeWKLuRwypF5IWHR/Yo/QOI.Ao9hLMjJK	EMPLOYEE
107	Андреев Гордий Данилович	employee4	$2a$10$r5axLlwMs2.yGoPxQZE3D.5hCHLXzUsIL4IWxInRBbGHN7PT/m0o2	EMPLOYEE
108	Иванов Адам Федорович	employee5	$2a$10$/iIaYqKUeCgTo4E/Hk9NiOQQRv.cVpXAw8WZfJ7rX3rTSBM4SdKGK	EMPLOYEE
109	Бобылёв Ипполит Альбертович	employee6	$2a$10$KKq7eZ2i47.rjRsM6yGcIOoprQ3ipxcGBS.G8jGa4Gx0rRtzo4DV.	EMPLOYEE
110	Беляева Евгения Антоновна	employee7	$2a$10$pxNWDhvrqCeOsGnVdV46jOTcB6ZNkLkI/rbJ1SNrG1DY5uWefzXu.	EMPLOYEE
111	Николаев Азарий Платонович	employee8	$2a$10$7hJPARDoQ949nCVEXV8HuusQBsXMJD/NF2tZqdy6z5d06pto/1FTG	EMPLOYEE
\.


--
-- Data for Name: agent_point; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.agent_point (id, address, approved_apps_count, card_issuance_days_passed, issued_cards_count, join_time, materials_delivered, location_id) FROM stdin;
61	Россия, Краснодар, тер. Пашковский жилой массив, ул. Крылатая, д. 2	19	12	1	LONG_AGO	t	111
62	Россия, Краснодар, ул. Восточно-Кругликовская, д. 64/2	19	27	12	LONG_AGO	t	112
66	Россия, Краснодар, ст-ца. Елизаветинская, ул. Широкая, д. 260	29	15	15	LONG_AGO	t	116
72	Россия, Краснодар, ул. Уральская, д. 79/1	5	0	0	YESTERDAY	f	122
73	Россия, Краснодар, ул. им. Селезнева, д. 197/5	14	7	3	LONG_AGO	t	123
92	Россия, Краснодар, ул. Уральская, д. 79/1	32	23	21	LONG_AGO	t	142
95	Россия, Краснодар, ул. Красная, 149	10	9	7	LONG_AGO	t	145
99	Россия, Краснодар, ул. им. Володи Головатого, д. 313	65	6	12	LONG_AGO	t	149
59	Россия, Краснодар, Красноармейская, 126	38	0	23	LONG_AGO	t	152
60	Россия, Краснодар, Ленина, 37	14	0	0	LONG_AGO	f	153
63	Россия, Краснодар, Красных Партизан, 439	84	33	63	LONG_AGO	t	154
64	Россия, Краснодар, Таманская, 153 к. 3	15	2	1	LONG_AGO	t	155
65	Россия, Краснодар, Дзержинского, 165	19	0	0	LONG_AGO	t	156
67	Россия, Краснодар, Тургенева, 174	0	0	0	LONG_AGO	f	157
68	Россия, Краснодар, Ставропольская, 140	0	0	0	YESTERDAY	f	158
71	Россия, Краснодар, Уральская, 162	21	4	5	LONG_AGO	t	159
76	Россия, Краснодар, Атарбекова, 24	6	0	0	YESTERDAY	f	160
77	Россия, Краснодар, Героя Аверкиева, 8	18	6	6	LONG_AGO	t	161
81	Россия, Краснодар, Тургенева, 106	96	2	20	LONG_AGO	t	162
100	Россия, Краснодар, Красная, 145	20	3	4	LONG_AGO	t	163
101	Россия, Краснодар, Красная, 154	0	0	0	YESTERDAY	f	164
82	Россия, Краснодар, Красных Партизан, 117	0	0	0	YESTERDAY	f	165
83	Россия, Краснодар, Северная, 389	16	0	0	LONG_AGO	t	166
84	Россия, Краснодар, Уральская, 166/3	43	3	29	LONG_AGO	t	167
85	Россия, Краснодар, Северная, 524	13	3	4	LONG_AGO	t	168
87	Россия, Краснодар, Коммунаров, 258	45	16	30	LONG_AGO	t	169
88	Россия, Краснодар, Дзержинского, 101	19	1	4	LONG_AGO	t	170
89	Россия, Краснодар, Северная, 326	20	3	9	LONG_AGO	t	171
91	Россия, Краснодар, Красная, 176	82	76	72	LONG_AGO	t	172
93	Россия, Краснодар, Северная, 326	19	4	4	LONG_AGO	t	173
97	Россия, Краснодар, Дзержинского, 102	10	0	0	YESTERDAY	f	174
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.employee (id, grade, location_address, account_id, location_id) FROM stdin;
58	MIDDLE	Краснодар, Красных Партизан, 321	110	58
59	JUNIOR	Краснодар, Красных Партизан, 321	111	59
52	SENIOR	Краснодар, Красная, 139	104	175
53	MIDDLE	Краснодар, Красная, 139	105	176
54	JUNIOR	Краснодар, Красная, 139	106	177
55	SENIOR	Краснодар, Мачуги, 41	107	178
56	MIDDLE	Краснодар, Мачуги, 41	108	179
57	JUNIOR	Краснодар, Мачуги, 41	109	180
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.location (id, latitude, longitude) FROM stdin;
58	45.05375615	38.94197325660306
59	45.05375615	38.94197325660306
111	45.0113787	39.122462
112	45.06876395	39.02560522382217
116	45.049406250000004	38.803343999999996
122	45.0344911	39.052518
123	45.0165901	39.0541191
142	45.0344911	39.052518
145	45.048263250000005	38.97818755881007
149	45.0392504	38.9744738
152	45.0540287	38.78333182918455
153	45.02088465	39.08052255
154	45.0514211	38.9564249
155	45.023305050000005	39.01281960716355
156	45.0902859	38.97646804701084
157	45.0697697	38.970178925216445
158	45.01989315	39.003858446928824
159	45.0385151	39.09396181038221
160	45.0591141	38.9492965
161	45.060213399999995	39.029274322484525
162	45.052193349999996	38.95986561799339
163	45.046872449999995	38.97772153508214
164	45.037148	38.975632821148466
165	45.0634423	38.9191145
166	45.0375616	38.9935016
167	45.0390135	39.0955714
168	45.0360279	39.001249327204974
169	45.0436991	38.9814975
170	45.069402350000004	38.972340575108305
171	45.0406511	38.970659
172	45.04589	38.981377
173	45.0406511	38.970659
174	45.1037667	38.9840094
175	45.0449862	38.97658758574157
176	45.0449862	38.97658758574157
177	45.0449862	38.97658758574157
178	45.0128578	39.071748768897166
179	45.0128578	39.071748768897166
180	45.0128578	39.071748768897166
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.task (id, creation_time, distance_to, getting_time, is_archived, order_number, start_time, type, agent_point_id, employee_id) FROM stdin;
54	2023-11-11	0	0	f	0	13:04:00	DEPARTURE	59	54
55	2023-11-11	0	0	f	0	15:43:00	TUITION	60	55
56	2023-11-11	0	0	f	0	15:43:00	TUITION	61	56
57	2023-11-11	0	0	f	0	15:43:00	TUITION	62	57
\.


--
-- Data for Name: task_manual; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.task_manual (id, json_manual, type) FROM stdin;
52	{"priority":"LOW","performTime":2.0,"requiredEmployeeGrade":"MIDDLE","issuedCardsMinDaysCount1":5,"issuedCardsMinDaysCount2":3}	DEPARTURE
53	{"priority":"MIDDLE","performTime":2.0,"requiredEmployeeGrade":"SENIOR","approvedAppsPercentage":1.0,"issuedCardsCount":6}	TUITION
\.


--
-- Data for Name: task_status; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.task_status (id, comment, is_completed, task_id) FROM stdin;
\.


--
-- Name: account_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.account_seq', 151, true);


--
-- Name: agent_point_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.agent_point_seq', 101, true);


--
-- Name: employee_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.employee_seq', 101, true);


--
-- Name: location_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.location_seq', 201, true);


--
-- Name: task_manual_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.task_manual_seq', 101, true);


--
-- Name: task_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.task_seq', 101, true);


--
-- Name: task_status_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.task_status_seq', 1, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: agent_point agent_point_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.agent_point
    ADD CONSTRAINT agent_point_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- Name: task_manual task_manual_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task_manual
    ADD CONSTRAINT task_manual_pkey PRIMARY KEY (id);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: task_status task_status_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task_status
    ADD CONSTRAINT task_status_pkey PRIMARY KEY (id);


--
-- Name: account uk5vxwyorsr92jce3ore6h93k6q; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT uk5vxwyorsr92jce3ore6h93k6q UNIQUE (login);


--
-- Name: account uk_5vxwyorsr92jce3ore6h93k6q; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT uk_5vxwyorsr92jce3ore6h93k6q UNIQUE (login);


--
-- Name: task_status uk_bjov1hytns5rhf69jiit52fij; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task_status
    ADD CONSTRAINT uk_bjov1hytns5rhf69jiit52fij UNIQUE (task_id);


--
-- Name: employee uk_lsnx7na4u8ohrhoeag7un4wh3; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_lsnx7na4u8ohrhoeag7un4wh3 UNIQUE (account_id);


--
-- Name: agent_point uk_py7muqx0nj55h7jpn6ynw8fb1; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.agent_point
    ADD CONSTRAINT uk_py7muqx0nj55h7jpn6ynw8fb1 UNIQUE (location_id);


--
-- Name: employee uk_rgbq4c19lpmfh7r3ma0aligmj; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_rgbq4c19lpmfh7r3ma0aligmj UNIQUE (location_id);


--
-- Name: idx1c324phwp97cglkdjrjyai06; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX idx1c324phwp97cglkdjrjyai06 ON public.task USING btree (order_number);


--
-- Name: idx1s3ak7v78kdnj3xxrd1q5likq; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX idx1s3ak7v78kdnj3xxrd1q5likq ON public.task USING btree (employee_id);


--
-- Name: idxl62d9pql2gy0mmj4dmt2bl28q; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX idxl62d9pql2gy0mmj4dmt2bl28q ON public.task USING btree (agent_point_id);


--
-- Name: idxlsnx7na4u8ohrhoeag7un4wh3; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX idxlsnx7na4u8ohrhoeag7un4wh3 ON public.employee USING btree (account_id);


--
-- Name: agent_point fkbdj1oiunco1svqauoogkqel7o; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.agent_point
    ADD CONSTRAINT fkbdj1oiunco1svqauoogkqel7o FOREIGN KEY (location_id) REFERENCES public.location(id);


--
-- Name: employee fkcfg6ajo8oske94exynxpf7tf9; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkcfg6ajo8oske94exynxpf7tf9 FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- Name: task_status fkjlssdsbnsibpfg84qe9efv337; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task_status
    ADD CONSTRAINT fkjlssdsbnsibpfg84qe9efv337 FOREIGN KEY (task_id) REFERENCES public.task(id);


--
-- Name: task fkmeqi2abtbehx871tag4op3hag; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fkmeqi2abtbehx871tag4op3hag FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: employee fknfotji9xluv8o3y9gogq2hxiw; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fknfotji9xluv8o3y9gogq2hxiw FOREIGN KEY (location_id) REFERENCES public.location(id);


--
-- Name: task fkspbqvg5r4fnxihh0355cnvmea; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fkspbqvg5r4fnxihh0355cnvmea FOREIGN KEY (agent_point_id) REFERENCES public.agent_point(id);


--
-- PostgreSQL database dump complete
--

