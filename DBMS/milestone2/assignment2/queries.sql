-- Display Id, Title, Category Title, Price of the products which are Active (considering stock >0) 
SELECT p.ID,p.Name,c.name category,price
FROM product p 
JOIN catergory c
ON p.Category = c.ID
WHERE stock>0
