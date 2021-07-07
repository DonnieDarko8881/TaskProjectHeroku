CREATE TABLE BOOK_AUD (
	EVENT_ID int(11) not null auto_increment,
    EVENT_DATE datetime not null,
    EVENT_TYPE varchar(10) default null,
    BOOK_ID int(11) not null,
    OLD_TITLE varchar(100),
    NEW_TITLE varchar(100),
    OLD_PUBYEAR int(4),
    NEW_PUBYEAR int(4),
    OLD_BESTSELLER boolean,
    NEW_BESTSELLER boolean,
    PRIMARY KEY (`EVENT_ID`)
);
