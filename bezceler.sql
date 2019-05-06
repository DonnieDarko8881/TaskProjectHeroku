DROP PROCEDURE IF EXISTS UpdateBestsellers;

DELIMITER $$
CREATE PROCEDURE UpdateBestsellers()
BEGIN
    DECLARE numberOfLoans, days, bookId INT;
    DECLARE loansPerMonth DECIMAL(5,2);
    DECLARE finished INT default(0);
    DECLARE all_book CURSOR FOR SELECT BOOK_ID from BOOKS;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;
    OPEN all_book;
    WHILE (finished =0) DO
		fetch all_book INTO bookId;
        if(finished = 0) THEN
			SELECT COUNT(*) FROM RENTS WHERE BOOK_ID = bookId INTO numberOfLoans;
            SELECT DATEDIFF(CURDATE(), MIN(RENT_DATE)) + 1 from RENTS
			where BOOK_ID = bookId into DAYS;
            SET loansPerMonth = numberOfLoans/days * 30;
            IF loansPerMonth>2 THEN
				UPDATE BOOKS SET BESTSELLER = true
				WHERE BOOK_ID = bookId; 
			else 
				UPDATE BOOKS SET BESTSELLER = false
				WHERE BOOK_ID = bookId; 
			END IF;
			COMMIT;
		END IF;
	END WHILE;
    SELECT numberOfLoans, days, loansPerMonth;
END $$
DELIMITER ;
CALL UpdateBestsellers();
