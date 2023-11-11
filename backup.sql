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
    CONSTRAINT account_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'MANAGER'::character varying, 'EMPLOYEE'::character varying])::text[])))
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
    CONSTRAINT agent_point_join_time_check CHECK (((join_time)::text = ANY ((ARRAY['YESTERDAY'::character varying, 'LONG_AGO'::character varying])::text[])))
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
    CONSTRAINT employee_grade_check CHECK (((grade)::text = ANY ((ARRAY['JUNIOR'::character varying, 'MIDDLE'::character varying, 'SENIOR'::character varying])::text[])))
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
    CONSTRAINT task_type_check CHECK (((type)::text = ANY ((ARRAY['DEPARTURE'::character varying, 'TUITION'::character varying, 'DELIVERY'::character varying])::text[])))
);


ALTER TABLE public.task OWNER TO "user";

--
-- Name: task_manual; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.task_manual (
    id integer NOT NULL,
    json_manual character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    CONSTRAINT task_manual_type_check CHECK (((type)::text = ANY ((ARRAY['DEPARTURE'::character varying, 'TUITION'::character varying, 'DELIVERY'::character varying])::text[])))
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
54	ул. им. Дзержинского, д. 100	9	3	1	LONG_AGO	t	104
57	Россия, Краснодар, Максима Горького, 128	15	3	3	LONG_AGO	t	107
58	Россия, Краснодар, Дзержинского, 100	9	3	1	LONG_AGO	t	108
59	Россия, Краснодар, ул. Красноармейская, д. 126	38	0	23	LONG_AGO	t	109
60	Россия, Краснодар, х. Ленина, п/о. 37	14	0	0	LONG_AGO	f	110
61	Россия, Краснодар, тер. Пашковский жилой массив, ул. Крылатая, д. 2	19	12	1	LONG_AGO	t	111
62	Россия, Краснодар, ул. Восточно-Кругликовская, д. 64/2	19	27	12	LONG_AGO	t	112
63	Россия, Краснодар, ул. Красных Партизан, д. 439	84	33	63	LONG_AGO	t	113
64	Россия, Краснодар, ул. Таманская, д. 153 к. 3, кв. 2	15	2	1	LONG_AGO	t	114
65	Россия, Краснодар, ул. им. Дзержинского, д. 165	19	0	0	LONG_AGO	t	115
66	Россия, Краснодар, ст-ца. Елизаветинская, ул. Широкая, д. 260	29	15	15	LONG_AGO	t	116
67	Россия, Краснодар, ул. им. Тургенева, д. 174, 1 этаж	0	0	0	LONG_AGO	f	117
68	Россия, Краснодар, ул. Ставропольская, д. 140	0	0	0	YESTERDAY	f	118
69	Россия, Краснодар, ст-ца. Елизаветинская, ул. Широкая, д. 260	29	15	15	LONG_AGO	t	119
70	Россия, Краснодар, ул. им. Тургенева, д. 174, 1 этаж	0	0	0	YESTERDAY	f	120
71	Россия, Краснодар, ул. Уральская, д. 162	21	4	5	LONG_AGO	t	121
72	Россия, Краснодар, ул. Уральская, д. 79/1	5	0	0	YESTERDAY	f	122
73	Россия, Краснодар, ул. им. Селезнева, д. 197/5	14	7	3	LONG_AGO	t	123
74	Россия, Краснодар, ул. Зиповская, д. 1	32	6	9	LONG_AGO	t	124
75	Россия, Краснодар, ул. им. 40-летия Победы, д. 20/1	35	4	15	LONG_AGO	t	125
76	Россия, Краснодар, ул. им. Атарбекова, д. 24	6	0	0	YESTERDAY	f	126
77	Россия, Краснодар, ул. им. Героя Аверкиева А.А., д. 8	18	6	6	LONG_AGO	t	127
80	Россия, Краснодар, ул. Героя Аверкиева, д. 8/1 к. мая, кв. 268	15	0	5	LONG_AGO	t	130
81	Россия, Краснодар, ул. им. Тургенева, д. 106	96	2	20	LONG_AGO	t	131
82	Россия, Краснодар, ул. Красных Партизан, д. 117	0	0	0	YESTERDAY	f	132
83	Россия, Краснодар, ул. Северная, д. 389	16	0	0	LONG_AGO	t	133
84	Россия, Краснодар, ул. Уральская, д. 166/3	43	3	29	LONG_AGO	t	134
85	Россия, Краснодар, ул. Северная, д. 524	13	3	4	LONG_AGO	t	135
86	Россия, Краснодар, ул. им. Кирилла Россинского, д. 61/1	19	6	5	LONG_AGO	t	136
87	Россия, Краснодар, ул. Коммунаров, д. 258	45	16	30	LONG_AGO	t	137
88	Россия, Краснодар, ул. им. Дзержинского, д. 101	19	1	4	LONG_AGO	t	138
89	Россия, Краснодар, ул. Северная, д. 326	20	3	9	LONG_AGO	t	139
90	Россия, Краснодар, ул. им. 40-летия Победы, д. 34	19	0	0	YESTERDAY	f	140
91	Россия, Краснодар, ул. Красная, д. 176	82	76	72	LONG_AGO	t	141
92	Россия, Краснодар, ул. Уральская, д. 79/1	32	23	21	LONG_AGO	t	142
93	Россия, Краснодар, ул. Северная, д. 326	19	4	4	LONG_AGO	t	143
95	Россия, Краснодар, ул. Красная, 149	10	9	7	LONG_AGO	t	145
96	Россия, Краснодар, п. Березовый, ул. Целиноградская, д. 6/1	13	0	0	YESTERDAY	f	146
97	Россия, Краснодар, ул. им. Дзержинского, д. 102	10	0	0	YESTERDAY	f	147
99	Россия, Краснодар, ул. им. Володи Головатого, д. 313	65	6	12	LONG_AGO	t	149
100	Россия, Краснодар, ул. Красная, д. 145	20	3	4	LONG_AGO	t	150
101	Россия, Краснодар, ул. Красная, д. 154	0	0	0	YESTERDAY	f	151
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.employee (id, grade, location_address, account_id, location_id) FROM stdin;
52	SENIOR	Краснодар, Красная, д. 139	104	52
53	MIDDLE	Краснодар, Красная, д. 139	105	53
54	JUNIOR	Краснодар, Красная, д. 139	106	54
55	SENIOR	Краснодар, В.Н. Мачуги, 41	107	55
56	MIDDLE	Краснодар, В.Н. Мачуги, 41	108	56
57	JUNIOR	Краснодар, В.Н. Мачуги, 41	109	57
58	MIDDLE	Краснодар, Красных Партизан, 321	110	58
59	JUNIOR	Краснодар, Красных Партизан, 321	111	59
\.


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.location (id, latitude, longitude) FROM stdin;
52	46.324912499999996	39.394934373618696
53	46.324912499999996	39.394934373618696
54	46.324912499999996	39.394934373618696
55	45.0145989	39.0624348
56	45.0145989	39.0624348
57	45.0145989	39.0624348
58	45.05375615	38.94197325660306
59	45.05375615	38.94197325660306
104	45.100081200000005	38.98668624016295
107	46.06770615	40.88417631344247
108	45.1000749	38.9850469
109	50.7773334	41.991432758936824
110	45.019141700000006	39.19819505
111	45.0113787	39.122462
112	45.06876395	39.02560522382217
113	45.106294	37.7040804
114	45.023305050000005	39.01281960716355
115	45.097528	38.9789193
116	45.049406250000004	38.803343999999996
117	45.0697697	38.970178925216445
118	45.0689905	39.1745253
119	45.049406250000004	38.803343999999996
120	45.0697697	38.970178925216445
121	45.037536599999996	39.09171515
122	45.0344911	39.052518
123	45.0165901	39.0541191
124	45.0623622	39.0014389
125	45.0550029	39.03754311794077
126	45.0591141	38.9492965
127	45.0613184	39.0303057
130	45.0498195	39.03398
131	45.052193349999996	38.95986561799339
132	45.106294	37.7040804
133	45.0375616	38.9935016
134	45.0407382	39.0960127
135	45.0360279	39.001249327204974
136	45.0883405	39.0206946
137	45.1437868	39.0427882
138	45.069402350000004	38.972340575108305
139	45.7642483	40.2497954
140	45.0974377	38.954668
141	45.0459461	38.9786546
142	45.0344911	39.052518
143	45.7642483	40.2497954
145	45.048263250000005	38.97818755881007
146	45.1553312	39.0005727
147	45.097528	38.9789193
149	45.0392504	38.9744738
150	45.2473503	38.0932369
151	45.23668885	39.2131694
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.task (id, creation_time, distance_to, getting_time, is_archived, order_number, start_time, type, agent_point_id, employee_id) FROM stdin;
52	2023-11-11	0	0	f	0	13:04:00	DEPARTURE	57	52
53	2023-11-11	0	0	f	0	13:04:00	DEPARTURE	58	53
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

SELECT pg_catalog.setval('public.location_seq', 151, true);


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

