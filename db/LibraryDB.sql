create schema library;

create table MEMBERS (
    MemberID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT,
    Fname VARCHAR(20),
    Minit CHAR(1),
    Lname VARCHAR(20),
    Address VARCHAR(50),
    PhoneNumber VARCHAR(10),
    Username VARCHAR(15) unique,
    Password VARCHAR(15),
    Fines decimal (5), 
    Is_active BINARY NOT NULL, #0 for inactive, 1 for activeFines
    PRIMARY KEY (MemberID)
);

# INSERT INTO MEMBERS VALUES ('Chloe', 'A', 'kimble', 0001, 'address', '123466789', 'cakimble', 'password', 1);

create table ASSOCIATES (
	  MemberID INT(4) UNSIGNED NOT NULL,
    Manager BINARY NOT NULL,  #0 for Ass., 1 for Manager
	  PRIMARY KEY (MemberID),
    FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID)
);

create table BOOK_DETAILS (
    ISBN VARCHAR(13) NOT NULL,
    Title VARCHAR(100),
    Year VARCHAR(4),
    PRIMARY KEY (ISBN)
);

create table BOOKS (
    ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    ISBN VARCHAR(13) NOT NULL,
    Checked_Out BINARY,
    Date_Out DATE,
    MemberID INT(4) UNSIGNED,
    PRIMARY KEY (ID),
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN),
    FOREIGN KEY (MemberID) REFERENCES MEMBERS(MemberID)
);

create table AUTHORS (
    ISBN VARCHAR(13) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN),
    PRIMARY KEY (ISBN, AName)
);

create table KEYWORDS (
    ISBN VARCHAR(13) NOT NULL,
    Keyword VARCHAR(200),
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN),
    PRIMARY KEY (ISBN, Keyword)
);
	