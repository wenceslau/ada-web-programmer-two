CREATE TABLE books
(
    id     BIGINT       NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn   VARCHAR(13)  NOT NULL,
    title  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE books  ADD CONSTRAINT books_isbn_unique UNIQUE (isbn);

CREATE SEQUENCE books_seq START WITH 1 INCREMENT BY 50;
