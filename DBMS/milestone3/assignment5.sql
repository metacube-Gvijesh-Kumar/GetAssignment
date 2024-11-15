-- Create a view displaying the order information 
-- (Id, Title, Price, Shopper’s name, Email, Orderdate, Status) 
-- with latest ordered items should be displayed first for last 60 days.


CREATE OR REPLACE VIEW  orderWithUserDetail AS
WITH orderDetail AS (
SELECT od.Id,od.User,od.date,price,product,status
FROM orders od
JOIN (
SELECT orders,p.price*oi.quantity price,product,status
FROM orderitem oi
JOIN Product p
ON oi.product= p.id 
) AS op
ON od.ID = op.orders
WHERE od.date > DATE_SUB(now(), INTERVAL 60 DAY)
ORDER BY od.date)

SELECT ID,User,u.email,date,price,product,status
FROM orderDetail od 
JOIN user u
ON u.UserName=od.User;

SELECT * FROM orderWithUserDetail;

-- Use the above view to display the Products(Items) which are in ‘shipped’ state.
-- as there is no order in shipped state we find the order with the canceled state
SELECT * FROM orderWithUserDetail WHERE status='canceled';

-- Use the above view to display the top 5 most selling products.
SELECT product,count(Product) count
FROM orderWithUserDetail
GROUP BY  product
ORDER BY count 
LIMIT 5;
