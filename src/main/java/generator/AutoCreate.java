package generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动创建dao、service、impl、controller, 不会重复创建
 *
 * @author zhaoyc
 * @since 16-Apr-21
 */
public class AutoCreate {

    /** 驱动 */
    static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    /** 服务器地址 */
    static String DB_URL = "jdbc:mysql://192.168.1.253:3306/dp_repo?useSSL=false";
    /** 登录用户名 */
    static String DB_UID = "repov5";
    /** 登录密码 */
    static String DB_PWD = "repov5";
    /** 项目包名 */
    static String PACKAGE = "com.zeusas.cloud.buos.info";
    /** 创建人 */
    static String CREATOR = "zhaoyc";

    /** key为表名称, value为主键类型 */
    static Map<String, String> TABLE_MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {
        AutoCreate auto = new AutoCreate();
        //获取所有数据表
        List<String> list = auto.getTableList();
        for (String table : list) {
            createStart(table);
        }
    }

    /**
     * 获取指定数据库中包含的表
     *
     * @return java.util.List<java.lang.String>
     * @author zhaoyc
     * @since 16-Apr-21
     */
    public List<String> getTableList() throws Exception {
        // 访问数据库 采用 JDBC方式
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
        DatabaseMetaData md = con.getMetaData();
        List<String> list = new ArrayList<>();
        String[] types ={"TABLE"};
        ResultSet rs = md.getTables(null, "%", "%", types);

        HashMap<String, String> map = new HashMap<>();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            ResultSet primaryKeyResultSet = md.getPrimaryKeys(null, null, tableName);
            tableName = toTableName(tableName);
            while(primaryKeyResultSet.next()){
                int primaryKeyType = primaryKeyResultSet.getMetaData().getColumnType(1);
                String type = toClass(primaryKeyType);
                map.put(tableName, type);
            }
            list.add(tableName);
        }
        TABLE_MAP.putAll(map);
        return list;
    }

    /**
     * 把数据库类型转换为java类型
     *
     * @param type 数据库类型
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static String toClass(int type) {
        String result = "";

        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                result = "String";
                break;

            case Types.NUMERIC:
            case Types.DECIMAL:
                result = "BigDecimal";
                break;

            case Types.BIT:
                result = "Boolean";
                break;

            case Types.TINYINT:
                result = "Byte";
                break;

            case Types.SMALLINT:
                result = "Short";
                break;

            case Types.INTEGER:
                result = "Integer";
                break;

            case Types.BIGINT:
                result = "Long";
                break;

            case Types.REAL:
            case Types.FLOAT:
                result = "Float";
                break;

            case Types.DOUBLE:
                result = "Double";
                break;

            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                result = "Byte[]";
                break;

            case Types.DATE:
                result = "Date";
                break;

            case Types.TIME:
                result = "Time";
                break;

            case Types.TIMESTAMP:
                result = "Timestamp";
                break;
        }

        return result;
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str 输入的字符串
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 16-Apr-21
     */
    private static String initCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 把输入字符串的首字母改成小写
     *
     * @param str 输入的字符串
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 16-Apr-21
     */
    private static String initLow(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    /**
     * 首字母转换和下划线转换
     *
     * @param tableName 表名称
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 16-Apr-21
     */
    private static String toTableName(String tableName) {
        String[] tables = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : tables) {
            sb.append(initCap(s));
        }
        tableName = sb.toString();
        return tableName;
    }

    /**
     * 创建Dao
     *
     * @param tableName 表名称
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static String createDao(String tableName) {
        String type = TABLE_MAP.get(tableName);
        return "package " + PACKAGE + ".dao;\n" +
               "\n" +
               "import " + PACKAGE + ".entity." + tableName + ";\n" +
               "import com.zeusas.cloud.core.dao.BasicJpaRepository;\n" +
               "\n" +
               "/**\n" +
               " * TODO: 描述\n" +
               " *\n" +
               " * @author " + CREATOR + "\n" +
               " * @since TODO: 创建时间\n" +
               " */\n" +
               "public interface " + tableName + "Dao extends BasicJpaRepository<" + tableName + ", " + type + "> {\n" +
               "\n" +
               "}";
    }

    /**
     * 创建Service
     *
     * @param tableName 表名称
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static String createService(String tableName) {
        return "package " + PACKAGE + ".service;\n" +
               "\n" +
               "import " + PACKAGE + ".entity." + tableName + ";\n" +
               "import com.zeusas.cloud.core.service.IService;\n" +
               "\n" +
               "/**\n" +
               " * TODO: 描述\n" +
               " *\n" +
               " * @author " + CREATOR + "\n" +
               " * @since TODO: 创建时间\n" +
               " */\n" +
               "public interface " + tableName + "Service extends IService<" + tableName + ", " +
               TABLE_MAP.get(tableName) + "> {\n" +
               "\n" +
               "}";
    }

    /**
     * 创建ServiceImpl
     *
     * @param tableName 表名称
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static String createServiceImpl(String tableName) {
        return "package " + PACKAGE + ".impl;\n" +
               "\n" +
               "import " + PACKAGE + ".dao." + tableName + "Dao;\n" +
               "import " + PACKAGE + ".entity." + tableName + ";\n" +
               "import " + PACKAGE + ".service." + tableName + "Service;\n" +
               "import org.springframework.beans.factory.annotation.Autowired;\n" +
               "import org.springframework.stereotype.Service;\n" +
               "\n" +
               "/**\n" +
               " * TODO: 描述\n" +
               " *\n" +
               " * @author " + CREATOR + "\n" +
               " * @since TODO: 创建时间\n" +
               " */\n" +
               "@Service\n" +
               "public class " + tableName + "ServiceImpl implements " + tableName + "Service {\n" +
               "\n" +
               "    @Autowired\n" +
               "    private " + tableName + "Dao " + initLow(tableName) + "Dao;\n" +
               "\n" +
               "    @Override\n" +
               "    protected " + tableName + "Dao getDao() {\n" +
               "        return " + initLow(tableName) + "Dao;\n" +
               "    }\n" +
               "\n" +
               "}\n";
    }

    /**
     * 创建Controller
     *
     * @param tableName 表名称
     *
     * @return java.lang.String
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static String createController(String tableName) {
        return "package " + PACKAGE + ".controller;\n" +
               "\n" +
               "import " + PACKAGE + ".entity." + tableName + ";\n" +
               "import " + PACKAGE + ".service." + tableName + "Service;\n" +
               "import org.springframework.beans.factory.annotation.Autowired;\n" +
               "import org.springframework.web.bind.annotation.RequestMapping;\n" +
               "import org.springframework.web.bind.annotation.ResponseBody;\n" +
               "\n" +
               "/**\n" +
               " * TODO: 描述\n" +
               " *\n" +
               " * @author " + CREATOR + "\n" +
               " * @since TODO: 创建时间\n" +
               " */\n" +
               "@Controller\n" +
               "@RequestMapping(\"/" + initLow(tableName) + "\")\n" +
               "public class " + tableName + "Controller {\n" +
               "\n" +
               "    private static final Logger logger = LoggerFactory.getLogger(" + tableName + "Controller.class);" +
               "\n" +
               "\n" +
               "    @Autowired\n" +
               "    private " + tableName + "Service " + initLow(tableName) + "Service;\n" +
               "\n" +
               "}\n";
    }

    /**
     * 开始创建Dao\Service\ServiceImpl\Controller文件
     *
     * @param tableName 数据库表名称
     *
     * @author zhaoyc
     * @since 17-Apr-21
     */
    private static void createStart(String tableName) {
        //获取当前项目的路径
        String url = System.getProperty("user.dir");
        url += "\\src\\main\\java\\com\\zeusas\\cloud\\buos\\info\\";
        //创建Dao
        createFile(new File(url + "dao\\" + tableName + "Dao.java"), createDao(tableName));
        //创建Service
        createFile(new File(url + "service\\" + tableName + "Service.java"), createService(tableName));
        //创建ServiceImpl
        createFile(new File(url + "service\\impl\\" + tableName + "ServiceImpl.java"), createServiceImpl(tableName));
        //创建Controller
        createFile(new File(url + "controller\\" + tableName + "Controller.java"), createController(tableName));
    }

    /**
     * 创建文件
     *
     * @param file    创建的文件
     * @param context 文件里面的内容
     *
     * @author zhaoyc
     * @since 17-Apr-21
     */
    static void createFile(File file, String context) {
        try {
            // 如果不存在文件
            if (!file.exists()) {
                //获取文件
                File parent = file.getParentFile();
                //如果是目录
                if (parent != null) {
                    //创建目录
                    parent.mkdirs();
                }

                //创建文件
                file.createNewFile();
                FileWriter fileWriter;
                try {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(context);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("创建文件失败:" + e.getMessage());
        }
    }
}