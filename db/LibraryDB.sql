create schema library;

create table MEMBERS (
	Fname VARCHAR(20),
    Minit CHAR(1),
    Lname VARCHAR(20),
    MemberID INT(4) NOT NULL,
    Address VARCHAR(50),
    PhoneNumber INT(10),
    Username CHAR(15),
    Password CHAR(15),
    PRIMARY KEY (MemberID)
);

create table EMPLOYEES (
	MemberID INT(4) NOT NULL,
    Type char (1) NOT NULL,  #associate or manager a or m
	PRIMARY KEY (MemberID),
    FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID)
);

create table BOOKS (
    ISBN INT(13) NOT NULL,
    Title VARCHAR(100),
    Year INT(4),
    Borrower INT(4), #will be the member ID of who has it
    Count INT(2), #how many total of this book is owned by library
    FOREIGN KEY (Borrower) REFERENCES MEMBERS (MemberID),
    PRIMARY KEY (ISBN)
);


create table AUTHORS (
    ISBN INT(13) NOT NULL,
    AName VARCHAR(50) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN, AName)
);

create table checkout_log (
    ISBN INT(13) NOT NULL,
	MemberID INT(4) NOT NULL,
    Date_out DATE,
    Date_in DATE,
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN),
	FOREIGN KEY (MemberID) REFERENCES MEMBERS (MemberID),
    PRIMARY KEY (ISBN, MemberID)
);

create table keywords (
    ISBN INT(13) NOT NULL,
    keyword VARCHAR(200),
    FOREIGN KEY (ISBN) REFERENCES BOOKS (ISBN),
    PRIMARY KEY (ISBN, keyword)
);
	