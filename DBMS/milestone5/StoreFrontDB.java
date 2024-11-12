package dbms.milestone3;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class order{
    public int Id;
    public String date;
    public long total;
    
    public order(int Id,String date,long total) {
        this.Id= Id;
        this.date=date;
        this.total=total;
    }
}

class Image{
    public String url;
    public int productId;
    public Image(String url,int productId) {
        this.url=url;
        this.productId=productId;
    }
}

class Category{
    public String name;
    public int id;
    public int childCount;
    public Category(String name,int id,int childCount) {
        this.name=name;
        this.id=id;
        this.childCount=childCount;
    }
}

public class StoreFrontDB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/storefront";
    static final String USER = "root";
    static final String PASS = "root";
    static Connection conn;
    
    public StoreFrontDB() {
            try{
               conn = DriverManager.getConnection(DB_URL, USER, PASS);
             } catch (SQLException e) {
                e.printStackTrace();
             }
    }
    
    public List<order> fetchOrders(Integer userId){
        
        String fetchOrderQ = "SELECT od.Id,date AS order_date,total AS order_total\r\n"
                + "FROM orders od\r\n"
                + "JOIN (\r\n"
                + "SELECT orders,SUM(p.price*oi.quantity) total\r\n"
                + "FROM orderitem oi\r\n"
                + "JOIN Product p\r\n"
                + "ON oi.product= p.id\r\n"
                + "WHERE oi.Status='shipped'\r\n"
                + "GROUP BY orders \r\n"
                + ") AS op\r\n"
                + "ON od.ID = op.orders\r\n"
                + "WHERE od.Id NOT IN (\r\n"
                + "SELECT DISTINCT od.Id \r\n"
                + "FROM orders od \r\n"
                + "JOIN orderitem oi \r\n"
                + "ON oi.orders=od.Id\r\n"
                + "WHERE oi.status not in ('shipped')\r\n"
                + ")  AND od.User = "+userId+"\r\n"
                + "ORDER BY order_date desc;";
        
        List<order> orders = new ArrayList<order>();
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(fetchOrderQ);
            
            while (rs.next()) {
                
                order od = new order(rs.getInt("Id"),rs.getString("order_date"),rs.getLong("order_total"));
                orders.add(od);

            }
            
            
        }
        catch (SQLException e) {
            e.printStackTrace();
         }
        
        return orders;
    }
   
    public void batchInsertImages (List<Image> images){
        
        String updateImagesQ = "INSERT INTO `Image` VALUES\r\n";
     
        try {
            Statement stmt = conn.createStatement();
            for(Image i:images) {
            stmt.executeQuery(updateImagesQ + "(null,'"+i.url+"',"+i.productId+");");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
         }
        
    }
    
    public int deleteUnorderedProducts (){
        
        String fetchProductIdsQ = "SELECT ID \r\n"
                + "FROM product\r\n"
                + "WHERE ID NOT IN (\r\n"
                + "SELECT oi.product\r\n"
                + "FROM orderitem oi\r\n"
                + "JOIN orders od\r\n"
                + "ON od.Id=oi.orders\r\n"
                + "WHERE od.Date>DATE_SUB(now(), INTERVAL 365 DAY)\r\n"
                + ");";
        
        String deleteProductIds = "DELETE FROM Product WHERE Id=";
        
        int deleted=0;
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(fetchProductIdsQ);
            
            while (rs.next()) {
                String productId = rs.getString("ID");
                stmt.execute(deleteProductIds+productId+";");
                deleted++;
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace();
         }
        return deleted;
    }
    
    public List<Category> topCategoryWithChildCount (){
        
        List<Category> categories = new ArrayList<Category>();
        
        String fetchTopCategory= "SELECT p.ID,p.Name,count(p.Id) as childCount  \r\n"
                + "From catergory p\r\n"
                + "Join Catergory c\r\n"
                + "on c.parentId = p.Id\r\n"
                + "where p.ParentId Is null\r\n"
                + "group by p.Id";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(fetchTopCategory);
            Category  c = new Category(rs.getString("Name"),rs.getInt("ID"),rs.getInt("childCount"));
            categories.add(c);
        }
        catch (SQLException e) {
            e.printStackTrace();
         }
        
        return categories;
        
    }
    
    public static void main(String[] args)  {
        
     
    }

}
