DROP PROCEDURE IF EXISTS UpdateBestsellers;

DELIMITER $$
CREATE PROCEDURE UpdateBestsellers()
BEGIN
    DECLARE numberOfLoans, days, bookId INT;
    DECLARE LoansPerMonth DECIMAL(5,2);
    DECLARE finished INT default(0);
    DECLARE ALL_BOOK CURSOR FOR SELECT BOOK_ID from BOOKS;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FINISHED =1;
    OPEN ALL_BOOK;
    While (finished =0) DO
		fetch ALL_BOOK INTO bookId;
        if(finished = 0) THEN
			SELECT COUNT(*) FROM RENTS WHERE BOOK_ID = bookId INTO numberOfLoans;
            SELECT DATEDIFF(CURDATE(), MIN(RENT_DATE)) + 1 from RENTS
			where BOOK_ID = bookId into DAYS;
            SET LoansPerMonth = numberOfLoans/days * 30;
            if LoansPerMonth>2 THEN
				UPDATE BOOKS SET BESTSELLER = true
				WHERE BOOK_ID = bookId; 
			else 
				UPDATE BOOKS SET BESTSELLER = false
				WHERE BOOK_ID = bookId; 
			end if;
			COMMIT;
		END IF;
	END WHILE;
    SELECT numberOfLoans, days, LoansPerMonth;
END $$
DELIMITER ;
call UpdateBestsellers();