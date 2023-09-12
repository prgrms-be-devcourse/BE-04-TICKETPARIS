INSERT INTO customer (username, password, name, email, birth_date, phone, address)
VALUES ('testCustomer1',
        '$2b$12$76taFAFPE9ydE0ZsuWkIZexWVjLBbEIRNc509/OLI5nM9d5r3fkRG',
        '구매자1',
        'testCustomer1@ticketparis.com',
        '1990-01-01',
        '010-1234-5678',
        '경기도 성남시 분당구 정자동 100-1');

INSERT INTO customer (username, password, name, email, birth_date, phone, address)
VALUES ('testCustomer2',
        '$3b$12$76taFAFPE9ydE0ZsuWkIZexWVjLBbEIRNc509/OLI5nM9d5r3fkRG',
        '구매자2',
        'testCustomer2@ticketparis.com',
        '1990-02-02',
        '010-4321-8765',
        '경기도 성남시 분당구 수내동 200-2');


INSERT INTO seller (username, password, name, registration_number, store_name, email, phone)
VALUES ('testSeller1',
        '$4b$12$76taFAFPE9ydE0ZsuWkIZexWVjLBbEIRNc509/OLI5nM9d5r3fkRG',
        '판매자1',
        '100-12-56789',
        '주식회사 티켓파리',
        'testSeller1@ticketparis.com',
        '010-4234-1674');


INSERT INTO hall (name, address, seats_count)
VALUES ('영산아트홀', '서울특별시 영등포구 여의공원로 101 (여의도동)', 598);

INSERT INTO hall (name, address, seats_count)
VALUES ('롤링홀', '서울특별시 마포구 어울마당로 35 (서교동) 지하 1층', 200);

INSERT INTO hall (name, address, seats_count)
VALUES ('유스퀘어 문화관', '광주광역시 서구 무진대로 904 (광천동)', 559);


INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('제4회 더 싱어즈 정기연주회',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224812_230829_130237.gif',
        '2023-09-11',
        '2023-09-11',
        '2시간',
        7,
        20000,
        '클래식/무용',
        '테스트 상세 설명1',
        1,
        1);

INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('테너 박성원과 함께하는 일콰트로의 네번째 행복한 여정',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224754_230828_165705.gif',
        '2023-09-14',
        '2023-09-14',
        '1시간 40분',
        7,
        50000,
        '클래식/무용',
        '테스트 상세 설명2',
        1,
        1);

INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('다브다 EP 발매 콘서트, Yonder',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224799_230829_122358.jpg',
        '2023-09-24',
        '2023-09-24',
        '1시간 40분',
        0,
        55000,
        '콘서트',
        '테스트 상세 설명3',
        1,
        2);

INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('우물 EP앨범 발매 기념 콘서트, 마른 정원',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224446_230823_123946.jpg',
        '2023-09-14',
        '2023-09-14',
        '2시간',
        0,
        44000,
        '콘서트',
        '테스트 상세 설명4',
        1,
        2);

INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('미친햄릿 [광주]',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224907_230831_102059.gif',
        '2023-09-22',
        '2023-09-23',
        '1시간 30분',
        13,
        35000,
        '연극',
        '테스트 상세 설명5',
        1,
        3);

INSERT INTO performance (title, poster_url, start_date, end_date, duration, age_rating, price, category, description,
                         seller_id, hall_id)
VALUES ('제14회 금호주니어콘서트, 강예서 플루트 독주회',
        'http://www.kopis.or.kr/upload/pfmPoster/PF_PF224644_230825_145427.jpg',
        '2023-08-24',
        '2023-08-24',
        '1시간',
        7,
        10000,
        '클래식/무용',
        '테스트 상세 설명6',
        1,
        3);


INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-11 19:30:00', 1, 595, 1); -- 더 싱어즈, 영산아트홀, 1회차, 1

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-11 22:00:00', 2, 594, 1); -- 더 싱어즈, 영산아트홀, 2회차, 2

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-14 19:30:00', 1, 594, 2); -- 테너 박성원, 영산아트홀, 3

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-24 17:00:00', 1, 199, 3); -- 다브다, 롤링홀, 4

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-14 20:00:00', 1, 199, 4); -- 우물, 롤링홀, 5

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-22 19:30:00', 1, 558, 5); -- 미친햄릿, 유스퀘어, 6

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-09-23 19:30:00', 1, 557, 5); -- 미친햄릿, 유스퀘어, 7

INSERT INTO schedule (start_datetime, sequence, seats_count, performance_id)
VALUES ('2023-08-24 19:30:00', 1, 557, 6); -- 강예서, 유스퀘어, 8


INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 1);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 2);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 2);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 3);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 4);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 5);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 6);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 7);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 1, 8);

INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 1);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 1);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 2);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 2);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 3);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 3);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 3);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 7);
INSERT INTO reservation (status, customer_id, schedule_id)
VALUES ('COMPLETED', 2, 8);