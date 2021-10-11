//package com.zeusas.cloud.repo.generator;
//
//import com.zeusas.util.QDate;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 自动创建service、impl、dao、controller
// *
// * @author zhaoyc
// * @since 16-Apr-21
// */
//public class AutoCreate {
//
//    /**
//     * 驱动
//     */
//    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//    /**
//     * 服务器地址
//     */
//    private static final String DB_URL = "jdbc:mysql://192.168.1.253:3306/dp_repo?useSSL=false";
//    /**
//     * 登录用户名
//     */
//    private static final String DB_UID = "repov5";
//    /**
//     * 登录密码
//     */
//    private static final String DB_PWD = "repov5";
//    /**
//     * 数据库操作
//     */
//    private static final String SQL = "SELECT * FROM ";
//    // todo: 自行修改对应的包名
//    /**
//     * 项目包名
//     */
//    private static final String PACKAGE = "com.zeusas.cloud.repo";
//    // todo: 自行修改对应的路径
//    /**
//     * 项目包名
//     */
//    private static final String PATH = "\\repo-lib\\src\\main\\java\\com\\zeusas\\cloud\\repo\\";
//    // todo: 自行修改对应的人名
//    /**
//     * 创建人
//     */
//    private static final String CREATOR = "zhaoyc";
//    // todo: 不会生成重复表名的文件,但可以自行添加需要忽略创建的表名
//    /**
//     * 忽略的表名,格式为大驼峰,比如:HelloWorld
//     */
//    private static final List<String> IGNORE = Arrays.asList("CoreDict",
//            "CoreIdgen",
//            "CounterAchievementHistory",
//            "CounterAchievementInTime",
//            "CounterNew",
//            "RepoTargetDay",
//            "RepoTargetLog",
//            "RepoTargetMonth",
//            "RepoUnit",
//            "RepoUnitGroup",
//            "RepoUnitGroupItem",
//            "RepoUnitOrg");
//
//    /**
//     * key为表名称, value为主键类型
//     */
//    static Map<String, String> TABLE_MAP = new HashMap<>();
//
//    public static void main(String[] args) throws Exception {
////         generateFromTable();
//        generateFromClazzList();
//    }
//
//    /**
//     * 根据类列表生成文件
//     *
//     * @author Wei Wei
//     * @since 14:28 2021/4/21
//     */
//    private static void generateFromClazzList() {
//        List<ClazzProperty> clazzList = new ArrayList<>();
//        clazzList.add(new ClazzProperty("Integer", "SysCounter"));
//        generateFromClazzList(clazzList);
//    }
//
//
//    /**
//     * 根据类列表生成文件
//     *
//     * @param clazzList 要生成的类列表
//     * @author Wei Wei
//     * @since 14:28 2021/4/21
//     */
//    private static void generateFromClazzList(List<ClazzProperty> clazzList) {
//        for (ClazzProperty clazzProperty : clazzList) {
//            createStart(clazzProperty.pkClazzName, clazzProperty.baseName);
//        }
//    }
//
//    /**
//     * 根据数据表生成
//     *
//     * @throws Exception Exception
//     * @author Wei Wei
//     * @since 14:26 2021/4/21
//     */
//    private static void generateFromTable() throws Exception {
//        AutoCreate auto = new AutoCreate();
//        // 获取所有数据表
//        List<String> list = auto.init();
//        // 忽略的表名
//        list.removeAll(IGNORE);
//        for (String table : list) {
//            createStart(table);
//        }
//    }
//
//    static class ClazzProperty {
//        /**
//         * 主键类的名称
//         */
//        private final String pkClazzName;
//        /**
//         * 基础名称 baseName + dao\service\serviceImpl 等为类名
//         */
//        private final String baseName;
//
//        public ClazzProperty(String pkClazzName, String baseName) {
//            this.pkClazzName = pkClazzName;
//            this.baseName = baseName;
//        }
//    }
//
//    /**
//     * 获取指定数据库中包含的表
//     *
//     * @return java.util.List<java.lang.String>
//     * @author zhaoyc
//     * @since 16-Apr-21
//     */
//    public List<String> init() throws Exception {
//        // 访问数据库 采用 JDBC方式
//        Class.forName(DB_DRIVER);
//        Connection con = DriverManager.getConnection(DB_URL, DB_UID, DB_PWD);
//        DatabaseMetaData md = con.getMetaData();
//        List<String> list = new ArrayList<>();
//        String[] types = {"TABLE"};
//        ResultSet rs = md.getTables(null, "%", "%", types);
//
//        HashMap<String, String> map = new HashMap<>();
//        while (rs.next()) {
//            String tableName = rs.getString("TABLE_NAME");
//            String tableSql = SQL + tableName + " limit 1";
//            ResultSetMetaData metaData= con.prepareStatement(tableSql).getMetaData();
//            int primaryKeyType = metaData.getColumnType(1);
//            String type = toClass(primaryKeyType);
//            tableName = toBigHump(tableName);
//            map.put(tableName, type);
//            list.add(tableName);
//        }
//        TABLE_MAP.putAll(map);
//        return list;
//    }
//
//    /**
//     * 把数据库类型转换为java类型
//     *
//     * @param type 数据库类型
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static String toClass(int type) {
//        String result = "";
//
//        switch (type) {
//            case Types.CHAR:
//            case Types.VARCHAR:
//            case Types.LONGVARCHAR:
//                result = "String";
//                break;
//
//            case Types.NUMERIC:
//            case Types.DECIMAL:
//                result = "BigDecimal";
//                break;
//
//            case Types.BIT:
//                result = "Boolean";
//                break;
//
//            case Types.TINYINT:
//                result = "Byte";
//                break;
//
//            case Types.SMALLINT:
//                result = "Short";
//                break;
//
//            case Types.INTEGER:
//                result = "Integer";
//                break;
//
//            case Types.BIGINT:
//                result = "Long";
//                break;
//
//            case Types.REAL:
//            case Types.FLOAT:
//                result = "Float";
//                break;
//
//            case Types.DOUBLE:
//                result = "Double";
//                break;
//
//            case Types.BINARY:
//            case Types.VARBINARY:
//            case Types.LONGVARBINARY:
//                result = "Byte[]";
//                break;
//
//            case Types.DATE:
//                result = "Date";
//                break;
//
//            case Types.TIME:
//                result = "Time";
//                break;
//
//            case Types.TIMESTAMP:
//                result = "Timestamp";
//                break;
//        }
//
//        return result;
//    }
//
//    /**
//     * 把输入字符串的首字母改成大写
//     *
//     * @param str 输入的字符串
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 16-Apr-21
//     */
//    private static String initCap(String str) {
//        char[] ch = str.toCharArray();
//        if (ch[0] >= 'a' && ch[0] <= 'z') {
//            ch[0] = (char) (ch[0] - 32);
//        }
//        return new String(ch);
//    }
//
//    /**
//     * 把输入字符串的首字母改成小写
//     *
//     * @param str 输入的字符串
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 16-Apr-21
//     */
//    private static String initLow(String str) {
//        char[] ch = str.toCharArray();
//        if (ch[0] >= 'A' && ch[0] <= 'Z') {
//            ch[0] = (char) (ch[0] + 32);
//        }
//        return new String(ch);
//    }
//
//    /**
//     * 首字母转换和下划线转换
//     *
//     * @param underlineString 下划线分隔的字符串
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 16-Apr-21
//     */
//    private static String toBigHump(String underlineString) {
//        String[] tables = underlineString.split("_");
//        StringBuilder sb = new StringBuilder();
//        for (String s : tables) {
//            sb.append(initCap(s));
//        }
//        return sb.toString();
//    }
//
//
//    /**
//     * 创建Dao
//     *
//     * @param pkClazzName 主键类型
//     * @param baseName    基础名称
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static String createDao(String pkClazzName, String baseName) {
//        return "package " + PACKAGE + ".dao;\n" +
//               "\n" +
//               "import " + PACKAGE + ".entity." + baseName + "Entity;\n" +
//               "import com.zeusas.cloud.core.dao.BasicJpaRepository;\n" +
//               "\n" +
//               "/**\n" +
//               " * TODO: 描述\n" +
//               " *\n" +
//               " * @author " + CREATOR + "\n" +
//               " * @since " + QDate.format(QDate.YYYY_MM_DD_HMS, System.currentTimeMillis()) + "\n" +
//               " */\n" +
//               "public interface " + baseName + "Dao extends BasicJpaRepository<" + baseName + "Entity, " + pkClazzName + "> {\n" +
//               "\n" +
//               "}";
//    }
//
//
//    /**
//     * 创建Service
//     *
//     * @param pkClazzName 主键类型
//     * @param baseName    基础名称
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static String createService(String pkClazzName, String baseName) {
//        return "package " + PACKAGE + ".service;\n" +
//               "\n" +
//               "import " + PACKAGE + ".entity." + baseName + "Entity;\n" +
//               "import com.zeusas.cloud.core.service.IService;\n" +
//               "\n" +
//               "/**\n" +
//               " * TODO: 描述\n" +
//               " *\n" +
//               " * @author " + CREATOR + "\n" +
//               " * @since " + QDate.format(QDate.YYYY_MM_DD_HMS, System.currentTimeMillis()) + "\n" +
//               " */\n" +
//               "public interface " + baseName + "Service extends IService<" + baseName + "Entity, " +
//               pkClazzName + "> {\n" +
//               "\n" +
//               "}";
//    }
//
//
//    /**
//     * 创建ServiceImpl
//     *
//     * @param baseName 表名称
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static String createServiceImpl(String pkClazzName, String baseName) {
//        return "package " + PACKAGE + ".service.impl;\n" +
//               "\n" +
//               "import com.zeusas.cloud.core.service.AbstractJpaService;\n" +
//               "import " + PACKAGE + ".dao." + baseName + "Dao;\n" +
//               "import " + PACKAGE + ".entity." + baseName + "Entity;\n" +
//               "import " + PACKAGE + ".service." + baseName + "Service;\n" +
//               "import org.springframework.beans.factory.annotation.Autowired;\n" +
//               "import org.springframework.stereotype.Service;\n" +
//               "\n" +
//               "/**\n" +
//               " * TODO: 描述\n" +
//               " *\n" +
//               " * @author " + CREATOR + "\n" +
//               " * @since " + QDate.format(QDate.YYYY_MM_DD_HMS, System.currentTimeMillis()) + "\n" +
//               " */\n" +
//               "@Service\n" +
//               "public class " + baseName + "ServiceImpl extends AbstractJpaService<" + baseName + "Entity, "
//               + pkClazzName + ">\n" +
//               "        implements " + baseName + "Service {\n" +
//               "\n" +
//               "    private final " + baseName + "Dao " + initLow(baseName) + "Dao;\n" +
//               "\n" +
//               "    @Autowired\n" +
//               "    public " + baseName + "ServiceImpl(" + baseName + "Dao " + initLow(baseName) + "Dao) {\n" +
//               "        this." + initLow(baseName) + "Dao = " + initLow(baseName) + "Dao;\n" +
//               "    }\n" +
//               "\n" +
//               "    @Override\n" +
//               "    protected " + baseName + "Dao getDao() {\n" +
//               "        return " + initLow(baseName) + "Dao;\n" +
//               "    }\n" +
//               "\n" +
//               "}\n";
//    }
//
//    /**
//     * 创建Controller
//     *
//     * @param tableName 表名称
//     * @return java.lang.String
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static String createController(String tableName) {
//        return "package " + PACKAGE + ".controller;\n" +
//               "\n" +
//               "import " + PACKAGE + ".entity." + tableName + "Entity;\n" +
//               "import " + PACKAGE + ".service." + tableName + "Service;\n" +
//               "import org.springframework.beans.factory.annotation.Autowired;\n" +
//               "import org.springframework.web.bind.annotation.RequestMapping;\n" +
//               "import org.springframework.web.bind.annotation.ResponseBody;\n" +
//               "\n" +
//               "/**\n" +
//               " * TODO: 描述\n" +
//               " *\n" +
//               " * @author " + CREATOR + "\n" +
//               " * @since " + QDate.format(QDate.YYYY_MM_DD_HMS, System.currentTimeMillis()) + "\n" +
//               " */\n" +
//               "@Controller\n" +
//               "@RequestMapping(\"/" + initLow(tableName) + "\")\n" +
//               "public class " + tableName + "Controller {\n" +
//               "\n" +
//               "    private static final Logger logger = LoggerFactory.getLogger(" + tableName + "Controller.class);" +
//               "\n" +
//               "\n" +
//               "    @Autowired\n" +
//               "    private " + tableName + "Service " + initLow(tableName) + "Service;\n" +
//               "\n" +
//               "}\n";
//    }
//
//    /**
//     * 开始创建Dao\Service\ServiceImpl\Controller文件
//     *
//     * @param tableName 数据库表名称
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static void createStart(String tableName) {
//        String pkClazzName = TABLE_MAP.get(tableName);
//        createStart(pkClazzName, tableName);
//    }
//
//    /**
//     * 开始创建Dao\Service\ServiceImpl\Controller文件
//     *
//     * @param pkClazzName 主键类型
//     * @param baseName    基础名称
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    private static void createStart(String pkClazzName, String baseName) {
//        //获取当前项目的路径
//        String url = System.getProperty("user.dir");
//        url += PATH;
//        //创建Dao
//        createFile(new File(url + "dao\\" + baseName + "Dao.java"), createDao(pkClazzName, baseName));
//        //创建Service
//        createFile(new File(url + "service\\" + baseName + "Service.java"), createService(pkClazzName, baseName));
//        //创建ServiceImpl
//        createFile(new File(url + "service\\impl\\" + baseName + "ServiceImpl.java"), createServiceImpl(pkClazzName, baseName));
//        //创建Controller
////        createFile(new File(url + "controller\\" + tableName + "Controller.java"), createController(tableName));
//    }
//
//    /**
//     * 创建文件
//     *
//     * @param file    创建的文件
//     * @param context 文件里面的内容
//     * @author zhaoyc
//     * @since 17-Apr-21
//     */
//    static void createFile(File file, String context) {
//        try {
//            // 如果不存在文件
//            if (!file.exists()) {
//                //获取文件
//                File parent = file.getParentFile();
//                //如果是目录
//                if (parent != null) {
//                    //创建目录
//                    parent.mkdirs();
//                }
//
//                //创建文件
//                file.createNewFile();
//                FileWriter fileWriter;
//                try {
//                    fileWriter = new FileWriter(file);
//                    fileWriter.write(context);
//                    fileWriter.flush();
//                    fileWriter.close();
//                } catch (IOException ignored) {
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("创建文件失败:" + e.getMessage());
//        }
//    }
//}
