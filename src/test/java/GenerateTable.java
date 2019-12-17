
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GenerateTable {

    private Connection conn = null;
    private final Statement stmt = null;
    private final String url = "jdbc:postgresql://192.168.0.86:5432/crm";
    private final String user = "postgres";
    private final String password = "postgres";

    @Before
    public void setUp() throws Exception {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected!!!");
    }

    @Test
    public void GenTable() {
        FileInputStream input = null;
        StringBuilder sb = new StringBuilder();
        try {
            String filePath = "C:\\Users\\zhaoyc\\Desktop\\新建文件夹";
            File crm  = new File(filePath);
            File[] fileList = crm.listFiles();
            System.out.println(fileList);
            for (File f : fileList) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {

    }
}
