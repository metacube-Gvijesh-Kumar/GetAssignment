-- Display Recent 50 Orders placed (Id, Order Date, Order Total).

-- the below query works quantity based only and forms basis for the actual query
-- SELECT od.Id,date,SUM(Quantity)
-- FROM orders od
-- JOIN orderItem oi
-- ON od.ID = oi.orders
-- GROUP BY od.Id
-- ORDER BY date
-- LIMIT 50;

-- instead of doing the join directly on the order item first we are make order item to 
-- have a join with the product so that we can calculate the total for each orderitem group by order 
-- finally do a natural join btn the order and order item and we are done
-- I verified it through the totalling

SELECT od.Id,date,total
FROM orders od
JOIN (
SELECT orders,SUM(p.price*oi.quantity) total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
GROUP BY orders 
) AS op
ON od.ID = op.orders
ORDER BY date
LIMIT 50;


-- Display 10 most expensive Orders.
-- utilize the previous methods again and get the result this limit 10 with order by total

SELECT od.Id,date,total
FROM orders od
JOIN (
SELECT orders,SUM(p.price*oi.quantity) total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
GROUP BY orders 
) AS op
ON od.ID = op.orders
ORDER BY total desc
LIMIT 10;

-- Display all the Orders which are placed more than 10 days old and one or more items from those orders are still not shipped.
SELECT distinct id,date
FROM orders od
JOIN orderitem oi  
ON oi.orders = od.id
WHERE oi.Status='on the way' and od.date < DATE_SUB(now(), INTERVAL 10 DAY);

-- Display list of shoppers which haven't ordered anything since last month.
-- first find the user who have ordered within the 30 days and then filter out from all
-- username using the in operator

SELECT UserName 
FROM user
WHERE UserName NOT IN (
SELECT u.UserName
FROM user u
JOIN orders od
ON od.User=u.UserName
WHERE od.date> DATE_SUB(now(), INTERVAL 30 DAY));

-- Display list of shopper along with orders placed by them in last 15 days.

SELECT u.UserName,od.ID,od.date 
FROM user u
LEFT JOIN orders od 
ON od.user=u.UserName
WHERE od.date> DATE_SUB(now(), INTERVAL 30 DAY)
ORDER BY UserName ;

-- Display list of order items which are in “shipped” state for particular Order Id 

SELECT oi.Orders orderId,oi.Product productId ,oi.status
FROM orderitem oi
WHERE Orders=13 AND status='shipped';

-- Display list of order items along with order placed date which fall between Rs 20 to Rs 50 price.

SELECT der.orders,der.product,der.total,date FROM 
( SELECT orders,Product,p.price*oi.quantity total
FROM orderitem oi
JOIN Product p
ON oi.product= p.id
WHERE p.price*oi.quantity>=4000 AND p.price*oi.quantity<=25000 ) as der
JOIN orders
on orders.id = der.orders
