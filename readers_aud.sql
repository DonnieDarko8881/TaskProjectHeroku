CREATE TABLE READERS_AUD (
	EVENT_ID int(11) not null auto_increment,
    EVENT_DATE datetime not null,
    EVENT_TYPE varchar(10) default null,
    READER_ID int(11) not null,
	OLD_FIRSTNAME varchar(100),
    NEW_FIRSTNAME varchar(100),
    OLD_LASTNAME varchar(100),
    NEW_LASTNAME varchar(100),
    OLD_PESELID varchar(11),
    NEW_PESELID varchar(11),
    OLD_VIP_LEVEL varchar(20),
    NEW_VIP_LEVEL varchar(20),
    PRIMARY KEY (`EVENT_ID`)
);