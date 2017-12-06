INSERT INTO BOOKS VALUES ('0446605239', 'The Notebook', 2004);

UPDATE MEMBERS 
SET Fines = Fines + 0.05
WHERE MemberID IN (SELECT CheckedOutMemberID
FROM Copies 
WHERE CurDate()-14 > Copies.DateOut)