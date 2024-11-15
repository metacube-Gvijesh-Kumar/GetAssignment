-- Consider a form where providing a Zip Code populates associated City and State. 
-- Create appropriate tables and relationships for the same and write a SQL
-- query for that returns a Resultset containing Zip Code, City Names and
-- States ordered by State Name and City Name.


CREATE TABLE PinCity(
	PinCode int UNSIGNED NOT NULL,
    City varchar(100) NOT NULL,
    PRIMARY KEY (PinCode,City),
    FOREIGN KEY (City) REFERENCES Address(city) ON DELETE CASCADE,
    FOREIGN KEY(PinCode) REFERENCES Address(pincode) ON DELETE CASCADE
);

CREATE TABLE PinState(
	PinCode int UNSIGNED NOT NULL,
    State varchar(100) NOT NULL,
    PRIMARY KEY (PinCode,State),
    FOREIGN KEY (State) REFERENCES Address(State) ON DELETE CASCADE,
    FOREIGN KEY(PinCode) REFERENCES Address(pincode) ON DELETE CASCADE
);

SELECT state,city,pincode 
FROM PinState ps
JOIN
(SELECT city,pincode 
FROM PinCity
WHERE pinCode='343522') AS pc
ON pc.pincode=ps.pincode
WHERE pinCode='343522'
ORDER By state,city;
