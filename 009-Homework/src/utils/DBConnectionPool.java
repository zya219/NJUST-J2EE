package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionPool {
    private static volatile DBConnectionPool dbConnection;
    private ComboPooledDataSource cpds;

    private DBConnectionPool() {
        try {
            String driverClassName;
            String url;
            String username;
            String password;
            Properties properties = new Properties();
            InputStream is = DBConnectionPool.class.getClassLoader().getResourceAsStream("/db.properties");
            if (is != null) {
                properties.load(is);
                System.out.println("here");
                driverClassName = properties.getProperty("driver");
                url = properties.getProperty("url");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                
            } else {
                driverClassName = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost:3306/009_jdbc?useUnicode=true&characterEncoding=UTF-8";
                username = "root";
                password = "Zya20040219";
            }

            this.cpds = new ComboPooledDataSource();
            this.cpds.setDriverClass(driverClassName);
            this.cpds.setJdbcUrl(url);
            this.cpds.setUser(username);
            this.cpds.setPassword(password);
            this.cpds.setInitialPoolSize(3);
            this.cpds.setMaxPoolSize(10);
            this.cpds.setAcquireIncrement(1);
            this.cpds.setIdleConnectionTestPeriod(60);
            this.cpds.setMaxIdleTime(3000);
            this.cpds.setTestConnectionOnCheckout(true);
            this.cpds.setTestConnectionOnCheckin(true);
            this.cpds.setAcquireRetryAttempts(30);
            this.cpds.setAcquireRetryDelay(1000);
            this.cpds.setBreakAfterAcquireFailure(true);
        } catch (IOException | PropertyVetoException var7) {
            var7.printStackTrace();
        }

    }

    public static DBConnectionPool getInstance() {
        if (dbConnection == null) {
            synchronized(DBConnectionPool.class) {
                if (dbConnection == null) {
                    dbConnection = new DBConnectionPool();
                }
            }
        }

        return dbConnection;
    }

    public final synchronized Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

    protected void finalize() throws Throwable {
        DataSources.destroy(this.cpds);
        super.finalize();
    }
}
