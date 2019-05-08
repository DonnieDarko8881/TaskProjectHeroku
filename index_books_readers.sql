CREATE INDEX index_books_title ON BOOKS (TITLE);
EXPLAIN SELECT * FROM READERS WHERE firstname = "John";
CREATE INDEX index_readers_firstname_lastname ON READERS (FIRSTNAME, LASTNAME);
CREATE INDEX index_readers_lastname ON READERS (LASTNAME);
EXPLAIN SELECT * FROM READERS WHERE firstname = "John";