-- Create a function to calculate number of orders in a month. Month and year will be input parameter to function.

DELIMITER //
CREATE FUNCTION numberOfOrders(month INT,year INT) RETURNS int DETERMINISTIC
BEGIN
 DECLARE orders INT;
	SELECT COUNT(ID) into orders
	FROM orders
	WHERE MONTH(date)=month AND YEAR(date)=year;
    RETURN orders;
END 
//
DELIMITER ;

SELECT numberOfOrders(2,2024);

-- Create a function to return month in a year having maximum orders. Year will be input parameter.

DELIMITER //
DROP FUNCTION IF EXISTS maxOrderMonth //
CREATE FUNCTION maxOrderMonth (year INT) RETURNS VARCHAR(100) DETERMINISTIC
BEGIN
 DECLARE MonthName VARCHAR(100) DEFAULT "";
	
    SELECT MONTHNAME(date) into MonthName
	FROM orders
	WHERE YEAR(date)=year
	GROUP BY MONTHNAME(date)
	ORDER BY count(MONTHNAME(date)) desc
	LIMIT 1;
    
    RETURN MonthName;
END 
//
DELIMITER ;

SELECT maxOrderMonth(2024);


