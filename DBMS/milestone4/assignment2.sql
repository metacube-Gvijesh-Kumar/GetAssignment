-- Create a Stored procedure to retrieve average sales of each product in a month. Month and year will be input parameter to function.
-- DAY(LAST_DAY(monthname('2024-02-01'))) tried this method to find the days in current month but it is not working
DROP PROCEDURE IF EXISTS averageSales;   
DELIMITER //
CREATE PROCEDURE averageSales(IN month INT,IN year INT) 
BEGIN
SELECT oi.product,COUNT(oi.product)/30
FROM orderitem oi
JOIN orders od
ON od.ID=oi.orders
WHERE MONTH(od.Date)=month AND YEAR(od.date)=year
GROUP BY oi.product;
END //
DELIMITER ;
Call averageSales(2,2024);

-- Create a stored procedure to retrieve table having order detail with status for a given period.
-- Start date and end date will be input parameter.
-- Put validation on input dates like start date is less than end date. 
-- If start date is greater than end date take first date of month as start date.
DROP PROCEDURE IF EXISTS orderDetail;
DELIMITER //
CREATE PROCEDURE orderDetail(IN startM date,IN endM date) 
BEGIN

IF startM >endM
OR startM=null OR endM=null
THEN
SIGNAL SQLSTATE '22003'
SET MESSAGE_TEXT = 'invalid date range';
END IF;

SELECT oi.orders,oi.product,od.date,oi.status
FROM orderitem oi 
JOIN orders od
ON od.ID=oi.Orders
WHERE od.Date>startM and od.date<endM;
END //
DELIMITER ;
Call orderDetail('2024-01-01','2024-10-10');