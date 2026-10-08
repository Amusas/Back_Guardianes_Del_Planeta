import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GenerateTests {

    public static void main(String[] args) throws IOException {
        String baseDir = "c:/Users/feliren/Desktop/Nueva carpeta/BackGuardianesPlaneta";
        String srcJavaDir = baseDir + "/src/main/java";
        String testJavaDir = baseDir + "/src/test/java";

        try (Stream<Path> paths = Files.walk(Paths.get(srcJavaDir))) {
            paths.filter(Files::isRegularFile)
                 .forEach(path -> {
                     String fileName = path.getFileName().toString();
                     if (fileName.endsWith("PersistenceAdapter.java")) {
                         generateAdapterTest(path, srcJavaDir, testJavaDir);
                     } else if (fileName.endsWith("PersistenceMapper.java") || fileName.endsWith("RestMapper.java")) {
                         generateMapperTest(path, srcJavaDir, testJavaDir);
                     }
                 });
        }
        System.out.println("Tests generated.");
    }

    private static void generateAdapterTest(Path path, String srcJavaDir, String testJavaDir) {
        String[] pkgAndCls = getPackageAndClass(path, srcJavaDir);
        String pkg = pkgAndCls[0];
        String cls = pkgAndCls[1];

        String content = "package " + pkg + ";\n\n" +
                "import org.junit.jupiter.api.Test;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.boot.test.context.SpringBootTest;\n" +
                "import org.springframework.test.context.DynamicPropertyRegistry;\n" +
                "import org.springframework.test.context.DynamicPropertySource;\n" +
                "import org.testcontainers.containers.MySQLContainer;\n" +
                "import org.testcontainers.junit.jupiter.Container;\n" +
                "import org.testcontainers.junit.jupiter.Testcontainers;\n" +
                "import static org.junit.jupiter.api.Assertions.assertNotNull;\n\n" +
                "@SpringBootTest\n" +
                "@Testcontainers\n" +
                "public class " + cls + "Test {\n\n" +
                "    @Container\n" +
                "    static MySQLContainer<?> mysql = new MySQLContainer<>(\"mysql:8.0\")\n" +
                "            .withDatabaseName(\"testdb\")\n" +
                "            .withUsername(\"test\")\n" +
                "            .withPassword(\"test\");\n\n" +
                "    @DynamicPropertySource\n" +
                "    static void properties(DynamicPropertyRegistry registry) {\n" +
                "        registry.add(\"spring.datasource.url\", mysql::getJdbcUrl);\n" +
                "        registry.add(\"spring.datasource.username\", mysql::getUsername);\n" +
                "        registry.add(\"spring.datasource.password\", mysql::getPassword);\n" +
                "        registry.add(\"spring.jpa.hibernate.ddl-auto\", () -> \"create-drop\");\n" +
                "    }\n\n" +
                "    @Autowired\n" +
                "    private " + cls + " adapter;\n\n" +
                "    @Test\n" +
                "    void testContextLoads() {\n" +
                "        assertNotNull(adapter);\n" +
                "    }\n" +
                "}\n";

        writeFile(pkg, cls, content, testJavaDir);
    }

    private static void generateMapperTest(Path path, String srcJavaDir, String testJavaDir) {
        String[] pkgAndCls = getPackageAndClass(path, srcJavaDir);
        String pkg = pkgAndCls[0];
        String cls = pkgAndCls[1];

        String content = "package " + pkg + ";\n\n" +
                "import org.junit.jupiter.api.Test;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.boot.test.context.SpringBootTest;\n" +
                "import static org.junit.jupiter.api.Assertions.assertNotNull;\n\n" +
                "@SpringBootTest(classes = " + cls + "Impl.class)\n" +
                "public class " + cls + "Test {\n\n" +
                "    @Autowired\n" +
                "    private " + cls + " mapper;\n\n" +
                "    @Test\n" +
                "    void testContextLoads() {\n" +
                "        assertNotNull(mapper);\n" +
                "    }\n" +
                "}\n";

        writeFile(pkg, cls, content, testJavaDir);
    }

    private static void writeFile(String pkg, String cls, String content, String testJavaDir) {
        File dir = new File(testJavaDir + "/" + pkg.replace(".", "/"));
        dir.mkdirs();
        try (FileWriter fw = new FileWriter(new File(dir, cls + "Test.java"))) {
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] getPackageAndClass(Path path, String srcJavaDir) {
        String relPath = new File(srcJavaDir).toURI().relativize(path.toUri()).getPath();
        String[] parts = relPath.split("/");
        String cls = parts[parts.length - 1].replace(".java", "");
        StringBuilder pkg = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (i > 0) pkg.append(".");
            pkg.append(parts[i]);
        }
        return new String[]{pkg.toString(), cls};
    }
}
