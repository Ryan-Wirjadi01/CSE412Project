import java.sql.*;
import java.util.ArrayList;

//requires the postgresql library
public  class DatabaseConnection {

    //change Url, User, and Pass depending on what it is for your own postgresql server
    private static final String connectionUrl = "jdbc:postgresql://localhost:5432/foodapp";
    private static final String user = "postgres";
    private static final String pass = "123";

    //testing methods
    public static void main(String[] args) {
        ArrayList<String> res = getRestaurants("tempe");
        System.out.println("length: " + res.size());
        for (String str: res) {
            System.out.println(str);
        }

        ArrayList<String> food = getFoodList("domino's");
        System.out.println("length: " + food.size());
        for (String str: food) {
            System.out.println(str);
        }

        System.out.println("Price: " + getFoodPrice("domino's", "cheese pizza"));


    }


    public static ArrayList<String> getRestaurants(String city){
        Connection c;
        Statement stmt;
        ArrayList<String> restaurants = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(connectionUrl,user,pass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select name from restaurant where UPPER(location) LIKE UPPER('%" + city + "%');");

            while(rs.next()){
               String str = rs.getString("name");

                restaurants.add(str);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return restaurants;
    }

    public static ArrayList<String> getFoodList(String restaurant){
        restaurant = restaurant.replace('\'', '_');
        Connection c;
        Statement stmt;
        ArrayList<String> foods = new ArrayList<>();

        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(connectionUrl,user,pass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select CF.name from cook_food as CF, restaurant as R WHERE CF.rid = R.rid and UPPER(R.name) like UPPER('%" + restaurant +"%');");

            while(rs.next()){
                String str = rs.getString("name");

                foods.add(str);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return foods;
    }

    public static float getFoodPrice(String restaurant, String food){
        restaurant = restaurant.replace('\'', '_');
        Connection c;
        Statement stmt;
        float price = 0;

        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(connectionUrl,user,pass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select CF.price " +
                    "FROM cook_food as CF, restaurant as R " +
                    "WHERE CF.rid = R.rid and UPPER(CF.name) like UPPER('" + food + "') and UPPER(R.name) like UPPER('%" + restaurant +"%');");
            while(rs.next()) {
                price = rs.getFloat("price");
            }
            rs.close();
            stmt.close();
            c.close();

        } catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return price;
    }

    //public static

}
