-- Display Shopper’s information along with number of orders he/she placed during last 30 days.

SELECT u.UserName,u.Email,Count(od.User) orders_placed
FROM orders od
JOIN user u
ON u.UserName=od.user
WHERE od.date > DATE_SUB(now(), INTERVAL 700 DAY)
GROUP BY od.User;

-- Display the top 10 Shoppers who generated maximum number of revenue in last 30 days.

WITH orderDetail AS (
SELECT od.Id,od.date,op.total,od.User
FROM orders od
JOIN (
SELECT oi.orders,SUM(p.price*oi.quantity) total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
GROUP BY oi.orders 
) AS op
ON od.ID = op.orders
WHERE od.date > DATE_SUB(now(), INTERVAL 700 DAY)
)

SELECT u.UserName,sum(od.total) as orderValue
FROM user u
JOIN orderDetail od
ON od.user=u.UserName
GROUP BY od.User
ORDER BY orderValue DESC
LIMIT 10;

-- Display top 20 Products which are ordered most in last 60 days along with numbers.

SELECT Name,ID,count
FROM Product p
Join 
(SELECT oi.Product,count(oi.Product) count
FROM orderitem oi 
JOIN orders od
ON od.ID=oi.Orders
WHERE od.date > DATE_SUB(now(), INTERVAL 700 DAY)
GROUP BY oi.Product) q 
ON p.ID = q.product;

-- Display Monthly sales revenue of the StoreFront for last 6 months. It should display each month’s sale

WITH orderDetail AS (
SELECT od.Id,od.date,op.total total,od.User,month(od.date) month
FROM orders od
JOIN (
SELECT oi.orders,SUM(p.price*oi.quantity) total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
GROUP BY oi.orders 
) AS op
ON od.ID = op.orders
WHERE od.date > DATE_SUB(now(), INTERVAL 180 DAY)
)
SELECT month,sum(total) total_revenue
FROM orderDetail
GROUP by month;

-- Mark the products as Inactive which are not ordered in last 90 days.
ALTER TABLE product
ADD active boolean DEFAULT true NOT NULL;

-- Mark the products as Inactive which are not ordered in last 90 days.

SET SQL_SAFE_UPDATES = 0;
UPDATE product 
SET active=0
WHERE ID NOT IN (
SELECT Product 
FROM orderitem oi
JOIN orders od
ON od.id = oi.Orders
WHERE od.Date > DATE_SUB(now(), INTERVAL 90 DAY)
);
SET SQL_SAFE_UPDATES = 1;

-- Given a category search keyword, display all the Products present in this category/categories
-- skipped for now

-- Display top 10 Items which were cancelled most.

SELECT product,count(product) count
FROM orderItem
WHERE status='canceled'
group by product
order by count;

