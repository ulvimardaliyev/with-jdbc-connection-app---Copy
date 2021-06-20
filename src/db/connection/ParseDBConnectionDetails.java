package db.connection;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ParseDBConnectionDetails {

    private String url;
    private String user;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void buildSQLConneectionProperty() {

        Properties properties = new Properties();
        try (FileInputStream fileInputStream =
                     new FileInputStream("C:\\Users\\m.ulvi\\IdeaProjects\\with-jdbc-connection-app---Copy\\src\\db.properties")) {
            properties.load(fileInputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        setUrl(properties.getProperty("url"));
        System.out.println(properties.getProperty("url"));
        setUser(properties.getProperty("user"));
        setPassword(properties.getProperty("password"));
    }
}
