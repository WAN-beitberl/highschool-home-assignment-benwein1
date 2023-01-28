
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createHighschoolFriendship {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/talbar";
        String user = "root";
        String password = "Benben9611!";

        try (Connection con = DriverManager.getConnection(url, user, password);
             BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\highschool_friendships.csv"))) {

            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if(first) {
                    first = false;
                    continue;
                }
                String[] values = line.split(",");
                // Insert the data into the table
                String sql = "INSERT INTO highschool_friendships (id, friend_id, other_friend_id)"
                        + " VALUES (?,?, ?)";
                try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    if(values.length > 1 && !values[1].isEmpty())
                        stmt.setInt(1, Integer.parseInt(values[1]));
                    else
                    	continue;
                    if(values.length > 2 && !values[2].isEmpty())
                        stmt.setInt(2, Integer.parseInt(values[2]));
                    else
                    	continue;
                    if(values.length > 3 && !values[3].isEmpty())
                        stmt.setInt(3, Integer.parseInt(values[3]));
                    else
                    	continue;
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}