package worker;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.sql.*;

import org.json.JSONObject;

class Worker {
  public static void main(String[] args) {
    try {
      Jedis redis = connectToRedis("redis");
      Connection dbConn = connectToDB("database", "3306", "signup");

      System.err.println("Watching user registration queue");

      while (true) {
        String userJSON = redis.blpop(0, "user").get(1);
        JSONObject userData = new JSONObject(userJSON);
        String userName = userData.getString("userName");
        Integer userId = userData.getInt("id");
        String firstName = userData.getString("firstName");
        String lastName = userData.getString("lastName");
        String password = userData.getString("password");
        String emailAddress = userData.getString("emailAddress");
        Long dateOfBirth = userData.getLong("dateOfBirth");


        System.err.printf("Processing user '%s' with id '%s'\n", userName, userId);
        addUser(dbConn, userId, dateOfBirth, emailAddress, firstName, lastName, password, userName);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  static void addUser(Connection dbConn, Integer userId, Long dateOfBirth, String emailAddress, String firstName, String lastName, String password, String userName) throws SQLException {
    PreparedStatement insert = dbConn.prepareStatement(
      "INSERT INTO user (id, dateOfBirth, emailAddress, firstName, lastName, password, userName) VALUES (?, ?, ? , ? , ? , ?, ?)");
    insert.setInt(1, userId);
    java.sql.Date dob = new java.sql.Date(dateOfBirth);
    insert.setDate(2, dob);
    insert.setString(3, emailAddress);
    insert.setString(4, firstName);
    insert.setString(5, lastName);
    insert.setString(6, password);
    insert.setString(7, userName);
    try {
      insert.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  static Jedis connectToRedis(String host) {
    Jedis conn = new Jedis(host);

    while (true) {
      try {
        conn.keys("*");
        break;
      } catch (JedisConnectionException e) {
        System.err.println("Waiting for redis");
        sleep(1000);
      }
    }

    System.err.println("Connected to redis");
    return conn;
  }

  static Connection connectToDB(String host, String port, String database) throws SQLException {
    Connection conn = null;

    try {

      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

      while (conn == null) {
        try {
          conn = DriverManager.getConnection(url, "gordon", "password");
        } catch (SQLException e) {
          System.err.println("Waiting for db");
          sleep(1000);
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.err.println("Connected to db");
    return conn;
  }

  static void sleep(long duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }
}
