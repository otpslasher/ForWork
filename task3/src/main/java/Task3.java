import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Task3 {

    // Задание НЕ выполнено. Не смогла справиться с множественными вложениями.
    // Код ниже часть попыток сделать хоть что-то.

    public static void main(String[] args) {


        String testsPathName = args[0];
        String valuesPathName = args[1];
        String reportPathName = args[2];

        FileReader fileTests;
        Scanner valReader;
        try {
            fileTests = new FileReader(testsPathName);
            FileReader fileValues = new FileReader(valuesPathName);

            //read file val

            valReader = new Scanner(fileValues);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //makes string val
        String line;
        String jsonLineVal = "";
        while (valReader.hasNextLine()) {
            line = valReader.nextLine();
            jsonLineVal = jsonLineVal.concat(line).trim();
        }


        //read file test
        Scanner testReader;
        testReader = new Scanner(fileTests);
        //makes string test
        String jsonLineTest = "";
        while (testReader.hasNextLine()) {
            line = testReader.nextLine();
            jsonLineTest = jsonLineTest.concat(line).trim();
        }


        ObjectMapper objectMapper = new ObjectMapper();
        List<String> keysT = new ArrayList<>();
        List<String> keysV = new ArrayList<>();

        List<JsonNode> znachV = new ArrayList<>();
        List<JsonNode> znachT = new ArrayList<>();
        List<JsonNode> znachALL = new ArrayList<>();


        JsonNode jsonNodeV;
        try {
            jsonNodeV = objectMapper.readTree(jsonLineVal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNodeT;
        try {
            jsonNodeT = objectMapper.readTree(jsonLineTest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        getKeys(jsonNodeT, keysT);
        getKeys(jsonNodeV, keysV);
        System.out.println(keysT);
        System.out.println(keysV);
        getValues(jsonNodeT, znachT);
        getValues(jsonNodeV, znachV);
        System.out.println(znachT);
        System.out.println(znachV);
    }

    public static void getKeys(JsonNode jsonNode, List<String> keys) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                keys.add(field.getKey());
                getKeys(field.getValue(), keys);
            });
        } else if (jsonNode.isArray()) {
            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(node -> {
                getKeys(node, keys);
            });
        }
    }

    public static void getValues(JsonNode jsonNode, List<JsonNode> znach) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                znach.add(field.getValue());
                getValues(field.getValue(), znach);
            });
        } else if (jsonNode.isArray()) {
            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(node -> {
                getValues(node, znach);
            });
        }
    }
}
