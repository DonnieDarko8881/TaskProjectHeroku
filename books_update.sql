DELIMITER $$

CREATE TRIGGER BOOKS_UPDATE AFTER UPDATE ON BOOKS
FOR EACH ROW
BEGIN
	INSERT INTO BOOK_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID,
		NEW_TITLE, NEW_PUBYEAR, NEW_BESTSELLER,
        OLD_TITLE, OLD_PUBYEAR, OLD_BESTSELLER) 
    VALUES(curtime(),"UPDATE", NEW.BOOK_ID, 
    NEW.TITLE, NEW.PUBYEAR, NEW.BESTSELLER,
	OLD.TITLE, OLD.PUBYEAR, OLD.BESTSELLER
    );
END $$
DELIMITER ;