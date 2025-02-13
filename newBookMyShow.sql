--------------------------------------------------------
POSTGRES SQL DUMBS TO CREATE BOOKMYSHOW TABLES USING BELOW 8 QUERIES
--------------------------------------------------------

-- Sequence for users ID
CREATE SEQUENCE public.users_user_id_seq
	AS INTEGER
	START WITH 101
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
-- Users Table
CREATE TABLE public.users (
	user_id INTEGER NOT NULL PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	user_email VARCHAR(255) NOT NULL,
	user_password VARCHAR(255) NOT NULL,
	user_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	user_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for user_id
ALTER TABLE ONLY public.users ALTER COLUMN user_id 
SET DEFAULT nextval('public.users_user_id_seq'::regclass);

----------------------------------------------------------------------------------

-- Sequence for movies ID
CREATE SEQUENCE public.movies_movie_id_seq
	AS INTEGER
	START WITH 201
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
-- movies Table
CREATE TABLE public.movies (
	movie_id INTEGER NOT NULL PRIMARY KEY,
	movie_name VARCHAR(255) NOT NULL,
	movie_language VARCHAR(255) NOT NULL,
	movie_genre VARCHAR(255) NOT NULL,
	movie_duration INTERVAL NOT NULL,
	movie_releasedate DATE NOT NULL,
	movie_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	movie_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for movie_id
ALTER TABLE ONLY public.movies ALTER COLUMN movie_id
SET DEFAULT nextval('public.movies_movie_id_seq'::regclass);

----------------------------------------------------------------------------------
-- Sequence for theaters ID
CREATE SEQUENCE public.theaters_theater_id_seq
	AS INTEGER
	START WITH 301
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
-- theaters Table
CREATE TABLE public.theaters (
	theater_id INTEGER NOT NULL PRIMARY KEY,
	theater_name VARCHAR(255) NOT NULL,
	theater_address VARCHAR(255) NOT NULL,
	theater_city VARCHAR(255) NOT NULL,
	theater_totalseats INTEGER NOT NULL,
	theater_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	theater_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for theater_id
ALTER TABLE ONLY public.theaters ALTER COLUMN theater_id
SET DEFAULT nextval('public.theaters_theater_id_seq'::regclass);

----------------------------------------------------------------------------------
-- Sequence for theaterseats ID
CREATE SEQUENCE public.theaterseats_theaterseat_id_seq
	AS INTEGER
	START WITH 401
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
-- theaterseats Table
CREATE TABLE public.theaterseats (
	theaterseat_id INTEGER NOT NULL PRIMARY KEY,
	theaterseat_row CHAR(1) NOT NULL,
	theaterseat_noofseats INTEGER NOT NULL, 
	theaterseat_theater_id INTEGER NOT NULL,
	theaterseat_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	theaterseat_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for theaterseat_id
ALTER TABLE ONLY public.theaterseats ALTER COLUMN theaterseat_id
SET DEFAULT nextval('public.theaterseats_theaterseat_id_seq'::regclass);

-- Foreign key constraint for theaterseat_id
ALTER TABLE ONLY public.theaterseats ADD CONSTRAINT theaterseat_theater_id_fkey 
FOREIGN KEY (theaterseat_theater_id) REFERENCES public.theaters(theater_id);
----------------------------------------------------------------------------------

-- Sequence for shows ID
CREATE SEQUENCE public.shows_show_id_seq
    AS INTEGER
    START WITH 501
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Enum for shows
CREATE TYPE public.status AS ENUM ('0', '1', '2'); -- (0-Available,1-HOUSEFULL,2-FREEZE) -- use this enum as default to show 0/1
--updating enum for shows
--ALTER TYPE public.status ADD VALUE '2';

-- shows Table
CREATE TABLE public.shows (
    show_id INTEGER NOT NULL PRIMARY KEY,
    show_date DATE NOT NULL DEFAULT CURRENT_DATE,
    show_starttime TIME NOT NULL,
    show_endtime TIME NOT NULL,
    show_theater_id INTEGER NOT NULL,
    show_movie_id INTEGER NOT NULL,
    show_status public.status NOT NULL DEFAULT '0',
    show_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    show_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for show_id
ALTER TABLE ONLY public.shows ALTER COLUMN show_id
SET DEFAULT nextval('public.shows_show_id_seq'::regclass);

-- Foreign key constraint for theater_id
ALTER TABLE ONLY public.shows ADD CONSTRAINT show_theater_id_fkey
FOREIGN KEY (show_theater_id) REFERENCES public.theaters(theater_id);

-- Foreign key constraint for movie_id
ALTER TABLE ONLY public.shows ADD CONSTRAINT show_movie_id_fkey
FOREIGN KEY (show_movie_id) REFERENCES public.movies(movie_id);

----------------------------------------------------------------------------------

-- Sequence for showseats ID
CREATE SEQUENCE public.showseats_showseat_id_seq
    AS INTEGER
    START WITH 601
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Enum for showseats
----CREATE TYPE public.showseatstatus AS ENUM ('0', '1'); -- (0-Available,1-booked)

-- showseats Table
CREATE TABLE public.showseats (
    showseat_id INTEGER NOT NULL PRIMARY KEY,
    seatstatus_show_id INTEGER NOT NULL,
    showseat_row CHAR(1) NOT NULL,
    showseat_seatno INTEGER NOT NULL,
    showseat_status public.status NOT NULL DEFAULT '0',
    showseat_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    showseat_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for showseat_id
ALTER TABLE ONLY public.showseats ALTER COLUMN showseat_id
SET DEFAULT nextval('public.showseats_showseat_id_seq'::regclass);

-- Foreign key constraint for Show_id
ALTER TABLE ONLY public.showseats ADD CONSTRAINT seatstatus_show_id_fkey
FOREIGN KEY (seatstatus_show_id) REFERENCES public.shows(show_id);
----------------------------------------------------------------------------------

-- Sequence for Payment ID
CREATE SEQUENCE public.payments_payment_id_seq
    AS INTEGER
    START WITH 701
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Enum for PaymentType
--CREATE TYPE public.paymenttype AS ENUM ('(1)CREDIT', '(2)UPI', '(3)CASH');

-- Enum for PaymentStatus
--CREATE TYPE public.paymentstatus AS ENUM ('(1)SUCCESS', '(2)FAILURE');

-- Payment Table
CREATE TABLE public.payments (
    payment_id INTEGER NOT NULL PRIMARY KEY,
    payment_type INTEGER NOT NULL,
    payment_totalamount DECIMAL(10, 2) NOT NULL,
    payment_status INTEGER NOT NULL,
    payment_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    payment_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for payment_id
ALTER TABLE ONLY public.payments ALTER COLUMN payment_id
SET DEFAULT nextval('public.payments_payment_id_seq'::regclass);

----------------------------------------------------------------------------------

-- Sequence for booking ID
CREATE SEQUENCE public.bookings_booking_id_seq
    AS INTEGER
    START WITH 801
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- bookings Table
CREATE TABLE public.bookings (
    booking_id INTEGER NOT NULL PRIMARY KEY,
    booking_user_id INTEGER NOT NULL,
    booking_show_id INTEGER NOT NULL,
    booking_totalseats INTEGER NOT NULL,
    booking_listofseats TEXT NOT NULL,
    booking_payment_id INTEGER NOT NULL,
    booking_createdon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    booking_updatedon TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Default value for booking_id
ALTER TABLE ONLY public.bookings ALTER COLUMN booking_id
SET DEFAULT nextval('public.bookings_booking_id_seq'::regclass);

-- Foreign key constraint for Show_id
ALTER TABLE ONLY public.bookings ADD CONSTRAINT bookings_show_id_fkey
    FOREIGN KEY (booking_show_id) REFERENCES public.shows(show_id);

-- Foreign key constraint for payment_id
ALTER TABLE ONLY public.bookings ADD CONSTRAINT bookings_payment_id_fkey
    FOREIGN KEY (booking_payment_id) REFERENCES public.payments(payment_id);

-- Foreign key constraint for payment_id
ALTER TABLE ONLY public.bookings ADD CONSTRAINT bookings_user_id_fkey
    FOREIGN KEY (booking_user_id) REFERENCES public.users(user_id);
----------------------------------------------------------------------------------
-----------------------------TO CREATE INDEX----------------------------------

CREATE INDEX idx_bookings_booking_id ON bookings(booking_id);
CREATE INDEX idx_bookings_user_id ON bookings(booking_user_id);
CREATE INDEX idx_bookings_payment_id ON bookings(booking_payment_id);
CREATE INDEX idx_bookings_show_id ON bookings(booking_show_id);

------------------------------------------------------------------------------
--------------------------------------------------------------------------
POSTGRES SQL DUMBS TO SELECT FROM BOOKMYSHOW TABLES USING BELOW 8 QUERIES
--------------------------------------------------------------------------

SELECT * FROM public.users;
SELECT * FROM public.movies;
SELECT * FROM public.theaters;
SELECT * FROM public.theaterseats;
SELECT * FROM public.shows;
SELECT * FROM public.showseats;
SELECT * FROM public.bookings;
SELECT * FROM public.payments;

------------------------------------------------------------------------------
-----------------------------TO CREATE INDEX----------------------------------

CREATE INDEX idx_bookings_booking_id ON bookings(booking_id);
CREATE INDEX idx_bookings_user_id ON bookings(user_id);
CREATE INDEX idx_bookings_payment_id ON bookings(payment_id);
CREATE INDEX idx_bookings_show_id ON bookings(show_id);

------------------------------------------------------------------------------

--------------------------------------------------------------------------------
POSTGRES SQL DUMBS TO INSERT DATA INTO BOOKMYSHOW TABLES USING BELOW 8 QUERIES
--------------------------------------------------------------------------------

---insert users table data
SELECT * FROM public.users;

INSERT INTO public.users (user_name, user_email, user_password) VALUES ('Mounika Dummu', 'monu.d@example.com', 'password');
INSERT INTO public.users (user_name, user_email, user_password) VALUES ('Venu Dummu', 'venu.d@example.com', 'password');
INSERT INTO public.users (user_name, user_email, user_password) VALUES ('Akhila Gorakala', 'akhi.g@example.com', 'password');
INSERT INTO public.users (user_name, user_email, user_password) VALUES ('Anil Gorakala', 'anil.g@example.com', 'password');

---insert movies table data
SELECT * FROM public.movies;

INSERT INTO public.movies (movie_name, movie_language, movie_genre, movie_duration, movie_releasedate)
VALUES ('Pushpa 2: The Rule', 'Telugu,Bengali,Malayalam,Hindi,Kannada,Tamil', 'Action,Thriller', '3 hours 20 minutes', '2024-12-05');
INSERT INTO public.movies (movie_name, movie_language, movie_genre, movie_duration, movie_releasedate)
VALUES ('Yeh Jawaani Hai Deewani', 'Hindi', 'Comedy,Drama,Musical,Romantic', '2 hours 41 minutes', '2013-05-31');
INSERT INTO public.movies (movie_name, movie_language, movie_genre, movie_duration, movie_releasedate)
VALUES ('The Wild Robot', 'English,Hindi', 'Adventure,Animation,Family', '1 hours 41 minutes', '2024-10-18');
INSERT INTO public.movies (movie_name, movie_language, movie_genre, movie_duration, movie_releasedate)
VALUES ('Pushpa: The Rise - Part 01', 'Telugu,Bengali,Malayalam,Hindi,Kannada,Tamil', 'Action,Thriller', '2 hours 59 minutes', '2021-12-17');

---insert theaters table data
SELECT * FROM public.theaters;

INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('AMB Cinemas: Gachibowli',
	'Sarath City Capital Mall, Forest Dept Colony, Kondapur, Gachibowli - Miyapur Road, White Field Road, 
	Opposite Mahindra Showroom, Hyderabad, Telangana 500084, India', 'Hyderabad', 15);
INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('INOX: GVK One, Banjara Hills','4th Floor, GVK One Mall, Road No.1, Banjara Hills,
	Opposite Hyatt Place, Hyderabad, Telangana 500034, India', 'Hyderabad', 10);
INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('INOX: CMR Central, Maddilapalem','Survey No. 67, 3rd Floor, CMR Central, Maddilapalem, 
	Dwaraka Nagar, Vizag, Andhra Pradesh 530013, India', 'Vizag', 12);
INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('INOX: Varun Beach, Beach Road','Survey No.120 and 121, Varun Beach, 3rd Floor, R K Beach Road, Maharani Pet, Vizag, 
	Andhra Pradesh 530003, India', 'Vizag', 10);
INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('STBL Cine World, Sheelanagar: Vizag','No.6-175/20, Sheela Nagar, Beside Ayyapa Swamy Temple, Vizag, 
	Andhra Pradesh 530012, India', 'Vizag', 11);
INSERT INTO public.theaters(theater_name, theater_address, theater_city, theater_totalseats)
	VALUES ('PVR: VR Chennai, Anna Nagar','3rd Floor, VR Mall, Metro Zone, No 44, Pillaiyar Koil Street, Jawaharlal Nehru Road,
	Anna Nagar, Chennai, Tamil Nadu 600040, India', 'Chennai', 10);
	
--- insert into theaterseats table data
SELECT * FROM public.theaterseats where theaterseat_theater_id = 301;

INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',2,301),('B',4,301),('C',4,301),('D',5,301); --15 seats
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',1,302),('B',2,302),('C',3,302),('D',4,302); --10 seats
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',4,303),('B',4,303),('C',4,303); --12 seats
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',5,304),('B',5,304); --10 seats
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',6,305),('B',3,305),('C',2,305); --11 seats
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('A',2,306),('B',2,306),('C',2,306),('D',2,306),('E',2,306); --10seats

--- insert into shows table data
SELECT * FROM public.shows WHERE show_theater_id = 302;

INSERT INTO public.shows(show_date, show_starttime, show_endtime, show_theater_id, show_movie_id)
	VALUES ('2025-01-07','10:00:00' ,'13:30:00', 302,202);
INSERT INTO public.shows(show_date, show_starttime, show_endtime, show_theater_id, show_movie_id)
	VALUES ('2025-01-07','14:00:00' ,'17:30:00', 302,202);
INSERT INTO public.shows(show_date, show_starttime, show_endtime, show_theater_id, show_movie_id)
	VALUES ('2025-01-07','21:50:00' ,'01:10:00', 302,202);
INSERT INTO public.shows(show_date, show_starttime, show_endtime, show_theater_id, show_movie_id)
	VALUES ('2025-01-08','18:10:00' ,'21:30:00', 302,202);
INSERT INTO public.shows(show_date, show_starttime, show_endtime, show_theater_id, show_movie_id)
	VALUES ('2025-01-09','12:55:00' ,'03:00:00', 302,202);

--- insert into showseats table data
SELECT * FROM public.showseats ORDER BY 2,3,4;

INSERT INTO public.showseats(seatstatus_show_id, showseat_row, showseat_seatno, showseat_status)
	VALUES (501, 'A', 1, '0'),(501, 'A', 2, '0'),(501, 'B', 3, '0'),(501, 'B', 4, '1'),(501, 'B', 5, '1'),
	(501, 'B', 6, '1'),(501, 'C', 7, '1'),(501, 'C', 8, '0'),(501, 'C', 9, '0'),(501, 'C', 10, '1'),
	(501, 'D', 11, '1'),(501, 'D', 12, '1'),(501, 'D', 13, '1'),(501, 'D', 14, '1'),(501, 'D', 15, '1'); 
	--301 theater --15seats(A2,B4,C4,D5)

	INSERT INTO public.showseats(showseat_show_id, showseat_row, showseat_seatno, showseat_status)
VALUES 
    -- A row: A1 to A14
    --(501, 'A', 1, '0'), (501, 'A', 2, '0')
	(501, 'A', 3, '0'), (501, 'A', 4, '0'), 
    (501, 'A', 5, '0'), (501, 'A', 6, '0'), (501, 'A', 7, '0'), (501, 'A', 8, '0'),
    (501, 'A', 9, '0'), (501, 'A', 10, '0'), (501, 'A', 11, '0'), (501, 'A', 12, '0'),
    (501, 'A', 13, '0'), (501, 'A', 14, '0'),
    
    -- B row: B1 to B18
    --(501, 'B', 1, '0'), (501, 'B', 2, '0'), (501, 'B', 3, '0'), (501, 'B', 4, '1'),
    (501, 'B', 5, '1'), (501, 'B', 6, '1'), (501, 'B', 7, '0'), (501, 'B', 8, '0'),
    (501, 'B', 9, '0'), (501, 'B', 10, '0'), (501, 'B', 11, '0'), (501, 'B', 12, '0'),
    (501, 'B', 13, '0'), (501, 'B', 14, '0'), (501, 'B', 15, '0'), (501, 'B', 16, '0'),
    (501, 'B', 17, '0'), (501, 'B', 18, '0'),

    -- C row: C1 to C18
    --(501, 'C', 1, '0'), (501, 'C', 2, '0'), (501, 'C', 3, '0'), (501, 'C', 4, '0'),
    (501, 'C', 5, '0'), (501, 'C', 6, '0'), (501, 'C', 7, '1'), (501, 'C', 8, '0'),
    (501, 'C', 9, '0'), (501, 'C', 10, '1'), (501, 'C', 11, '0'), (501, 'C', 12, '0'),
    (501, 'C', 13, '0'), (501, 'C', 14, '0'), (501, 'C', 15, '0'), (501, 'C', 16, '0'),
    (501, 'C', 17, '0'), (501, 'C', 18, '0'),

    -- D row: D1 to D18
   -- (501, 'D', 1, '0'), (501, 'D', 2, '0'), (501, 'D', 3, '0'), (501, 'D', 4, '0'),
    --(501, 'D', 5, '0'),
	(501, 'D', 6, '0'), (501, 'D', 7, '0'), (501, 'D', 8, '0'),
    (501, 'D', 9, '0'), (501, 'D', 10, '0'), (501, 'D', 11, '1'), (501, 'D', 12, '1'),
    (501, 'D', 13, '1'), (501, 'D', 14, '1'), (501, 'D', 15, '1'), (501, 'D', 16, '0'),
    (501, 'D', 17, '0'), (501, 'D', 18, '0'),

    -- E row: E1 to E21
    (501, 'E', 1, '0'), (501, 'E', 2, '0'), (501, 'E', 3, '0'), (501, 'E', 4, '0'),
    (501, 'E', 5, '0'), (501, 'E', 6, '0'), (501, 'E', 7, '0'), (501, 'E', 8, '0'),
    (501, 'E', 9, '0'), (501, 'E', 10, '0'), (501, 'E', 11, '0'), (501, 'E', 12, '0'),
    (501, 'E', 13, '0'), (501, 'E', 14, '0'), (501, 'E', 15, '0'), (501, 'E', 16, '0'),
    (501, 'E', 17, '0'), (501, 'E', 18, '0'), (501, 'E', 19, '0'), (501, 'E', 20, '0'),
    (501, 'E', 21, '0'),

	(501, 'F', 1, '0'), (501, 'F', 2, '0'), (501, 'F', 3, '0'), (501, 'F', 4, '0'), 
    (501, 'F', 5, '0'), (501, 'F', 6, '0'), (501, 'F', 7, '1'), (501, 'F', 8, '0'),
    (501, 'F', 9, '0'), (501, 'F', 10, '1'), (501, 'F', 11, '1'), (501, 'F', 12, '1'),
    (501, 'F', 13, '1'), (501, 'F', 14, '1'), (501, 'F', 15, '1'), (501, 'F', 16, '1'),
    (501, 'F', 17, '1'), (501, 'F', 18, '0'), (501, 'F', 19, '0'), (501, 'F', 20, '0'),
    (501, 'F', 21, '0'),

    -- Row G (G1 to G21)
    (501, 'G', 1, '0'), (501, 'G', 2, '0'), (501, 'G', 3, '0'), (501, 'G', 4, '0'), 
    (501, 'G', 5, '0'), (501, 'G', 6, '0'), (501, 'G', 7, '1'), (501, 'G', 8, '0'),
    (501, 'G', 9, '0'), (501, 'G', 10, '1'), (501, 'G', 11, '1'), (501, 'G', 12, '1'),
    (501, 'G', 13, '1'), (501, 'G', 14, '1'), (501, 'G', 15, '1'), (501, 'G', 16, '1'),
    (501, 'G', 17, '1'), (501, 'G', 18, '0'), (501, 'G', 19, '0'), (501, 'G', 20, '0'),
    (501, 'G', 21, '0'),

    -- Row H (H1 to H21)
    (501, 'H', 1, '0'), (501, 'H', 2, '0'), (501, 'H', 3, '0'), (501, 'H', 4, '0'), 
    (501, 'H', 5, '0'), (501, 'H', 6, '0'), (501, 'H', 7, '1'), (501, 'H', 8, '0'),
    (501, 'H', 9, '0'), (501, 'H', 10, '1'), (501, 'H', 11, '1'), (501, 'H', 12, '1'),
    (501, 'H', 13, '1'), (501, 'H', 14, '1'), (501, 'H', 15, '1'), (501, 'H', 16, '1'),
    (501, 'H', 17, '1'), (501, 'H', 18, '0'), (501, 'H', 19, '0'), (501, 'H', 20, '0'),
    (501, 'H', 21, '0');
	
    -- I row: I1 to I10
    (501, 'I', 1, '0'), (501, 'I', 2, '0'), (501, 'I', 3, '0'), (501, 'I', 4, '0'),
    (501, 'I', 5, '0'), (501, 'I', 6, '0'), (501, 'I', 7, '0'), (501, 'I', 8, '0'),
    (501, 'I', 9, '0'), (501, 'I', 10, '0');
 --162 seats

INSERT INTO public.showseats (showseat_show_id, showseat_row, showseat_seatno, showseat_status) 
VALUES 
    -- Row E (1-21)
	(505, 'A', generate_series(2, 14), '0'),
	(505, 'B', generate_series(2, 21), '0'),
	(505, 'C', generate_series(2, 21), '0'),
	(505, 'D', generate_series(4, 21), '0'),
	(505, 'E', generate_series(1, 21), '0'),
    (505, 'F', generate_series(1, 21), '0'),
    -- Row G to P (1-18)
    (505, 'G', generate_series(1, 18), '0'),
    (505, 'H', generate_series(1, 18), '0'),
    (505, 'I', generate_series(1, 18), '0'),
    (505, 'J', generate_series(1, 18), '0'),
    (505, 'K', generate_series(1, 18), '0'),
    (505, 'L', generate_series(1, 18), '0'),
    (505, 'M', generate_series(1, 18), '0'),
    (505, 'N', generate_series(1, 18), '0'),
    (505, 'O', generate_series(1, 18), '0'),
    (505, 'P', generate_series(1, 18), '0');

INSERT INTO public.showseats (showseat_show_id, showseat_row, showseat_seatno, showseat_status)
--SELECT 505, 'A', generate_series(3, 21), '0'
--UNION ALL
SELECT 505, 'B', generate_series(3, 21), '0'
UNION ALL
SELECT 505, 'C', generate_series(3, 21), '0'
UNION ALL
SELECT 505, 'D', generate_series(5, 21), '0'
UNION ALL
SELECT 505, 'E', generate_series(1, 21), '0'
UNION ALL
SELECT 505, 'F', generate_series(1, 21), '0'
UNION ALL
SELECT 505, 'G', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'H', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'I', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'J', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'K', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'L', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'M', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'N', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'O', generate_series(1, 18), '0'
UNION ALL
SELECT 505, 'P', generate_series(1, 11), '0';

-- 299 seats (302 theater)

INSERT INTO public.showseats(seatstatus_show_id, showseat_row, showseat_seatno, showseat_status)
	VALUES (505, 'A', 1, '0'),(505, 'B', 2, '1'),(505, 'B', 3, '1'),
	(505, 'B', 4, '1'),(505, 'C', 5, '1'),(505, 'C', 6, '1'),
	(505, 'D', 7, '1'),(505, 'D', 8, '1'),(505, 'D', 9, '1'),(505, 'D', 10, '1'); 
	--302 theater --10seats(A1,B2,C3,D4)
INSERT INTO public.showseats(seatstatus_show_id, showseat_row, showseat_seatno, showseat_status)
	VALUES (503, 'A', 1, '0'),(503, 'A', 2, '1'),(503, 'B', 3, '1'),(503, 'B', 4, '1'),(503, 'B', 5, '0'),
	(503, 'B', 6, '1'),(503, 'C', 7, '1'),(503, 'C', 8, '0'),(503, 'C', 9, '0'),(503, 'C', 10, '0'),
	(503, 'D', 11, '1'),(503, 'D', 12, '1'),(503, 'D', 13, '1'),(503, 'D', 14, '1'),(503, 'D', 15, '1'); 
	--301 theater --15seats(A2,B4,C4,D5)
INSERT INTO public.showseats(showseat_show_id, showseat_row, showseat_seatno, showseat_status)
	VALUES (502, 'A', 1, '2'),(502, 'A', 2, '0'),(502, 'B', 3, '0'),(502, 'B', 4, '2'),(502, 'B', 5, '2'),
	(502, 'B', 6, '1'),(502, 'C', 7, '0'),(502, 'C', 8, '0'),(502, 'C', 9, '0'),(502, 'C', 10, '1'),
	(502, 'D', 11, '1'),(502, 'D', 12, '1'),(502, 'D', 13, '1'),(502, 'D', 14, '1'),(502, 'D', 15, '0'); 
	--301 theater --15seats(A2,B4,C4,D5)

----------------
--UPDATE QUERY
----------------
UPDATE showseats SET seatstatus_show_id = 505 WHERE seatstatus_show_id = 503
ALTER TABLE showseats ADD COLUMN showseat_ticketcost INTEGER;
UPDATE showseats SET showseat_ticketcost = 10;
UPDATE showseats SET showseat_status = 0
SELECT DISTINCT theater_city from theaters;
DELETE FROM bookings;
DELETE FROM payments;

SELECT * FROM public.theaters;
UPDATE theaters SET theater_totalseats = 299 WHERE theater_id = 302
SELECT * FROM public.theaterseats where theaterseat_theater_id = 302
UPDATE theaterseats SET theaterseat_noofseats = 11 WHERE theaterseat_id = 438

SELECT * FROM public.showseats WHERE showseat_show_id = 521 ORDER BY 2,3,4 ;

---removes duplicate
DELETE FROM public.showseats
WHERE ctid NOT IN (
    SELECT MIN(ctid) FROM public.showseats GROUP BY showseat_show_id,showseat_row,
	showseat_seatno);
	
INSERT INTO public.theaterseats(theaterseat_row, theaterseat_noofseats, theaterseat_theater_id)
	VALUES ('E',21,302),('F',21,302),('G',18,302),('H',18,302),('I',18,302),('J',18,302),('K',18,302),
	('L',18,302),('M',18,302),('N',18,302),('O',18,302),('P',18,302);

SELECT * FROM public.shows WHERE show_theater_id = 302;	

---add duplicate entry 
INSERT INTO public.showseats(showseat_show_id, showseat_row, showseat_seatno, showseat_status)
SELECT 522, showseat_row, showseat_seatno, showseat_status
FROM public.showseats
WHERE showseat_show_id = 505;

-----
 1  movie
 2 theaters 
 15, 10 theaterseats
 2,2 shows 
(15,15), (10,10) showseats

 1  movie
 3 theaters 
 6,8,10 theaterseats
 1,2,1 shows 
(6),(8,8), (10) showseats

--------------
joins query
--------------
select ss1_0.showseat_id,ss1_0.showseat_show_id,ss1_0.showseat_row,ss1_0.showseat_seatno,ss1_0.showseat_status from shows se1_0 join theaters t1_0 on t1_0.theater_id=se1_0.show_theater_id join movies m1_0 on m1_0.movie_id=se1_0.show_movie_id join showseats ss1_0 on se1_0.show_id=ss1_0.showseat_show_id 
where se1_0.show_id= 503 and ss1_0.showseat_status = '2'
order by ss1_0.showseat_row,ss1_0.showseat_seatno,6

SELECT 
    m.movie_id AS movieId,
    m.movie_name AS movieName,
    t.theater_id AS theaterId,
    t.theater_name AS theaterName,
    s.show_id AS showId,
    s.show_starttime AS showStarttime,
    s.show_date AS showDate
FROM shows s JOIN theaters  t ON s.show_theater_id = t.theater_id
JOIN movies m ON s.show_movie_id = m.movie_id
WHERE s.show_id =502
ORDER BY s.show_date, s.show_starttime;

