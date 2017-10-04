create schema library;

create table MEMBERS (
    MemberID INT(4) UNSIGNED NOT NULL AUTO_INCREMENT,
	Fname VARCHAR(20),
    Minit CHAR(1),
    Lname VARCHAR(20),
    Address VARCHAR(50),
    PhoneNumber VARCHAR(10),
    Username VARCHAR(15),
    Password VARCHAR(15),
    Is_active BINARY NOT NULL, #0 for inactive, 1 for active
    PRIMARY KEY (MemberID)
);

#INSERT INTO MEMBERS VALUES ('Chloe', 'A', 'Kimble', 'Address', '1234567890', 'cakimble', 'password', 1);

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
    checkedout BINARY,
    PRIMARY KEY (ID),
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN)
);

create table AUTHORS (
    ISBN VARCHAR(13) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN),
    PRIMARY KEY (ISBN, AName)
);

create table CHECKOUT_LOG (
    BookID INT(10) UNSIGNED NOT NULL,
	  MemberID INT(4) UNSIGNED NOT NULL,
    Date_out DATE NOT NULL,
    Date_in DATE,
    FOREIGN KEY (BookID) REFERENCES BOOKS (ID),
	  FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID),
    PRIMARY KEY (BookID, MemberID, Date_out)
);

create table KEYWORDS (
    ISBN VARCHAR(13) NOT NULL,
    keyword VARCHAR(200),
    FOREIGN KEY (ISBN) REFERENCES BOOK_DETAILS (ISBN),
    PRIMARY KEY (ISBN, keyword)
);
	