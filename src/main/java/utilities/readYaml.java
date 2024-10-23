package utilities;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class readYaml {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> loadConfig(String path){
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;
        try (InputStream inputStream = new FileInputStream(path)) {
            yamlMap = yaml.load(inputStream);
            Set<String> set = yamlMap.keySet();
            for (String key : set) {
                System.out.println(key + " " + yamlMap.get(key));
            }
            return yamlMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config from: " + path);
        }
    }
}

