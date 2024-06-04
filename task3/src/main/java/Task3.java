import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task3 {

    // Задание НЕ выполнено. Не смогла справиться с множественными вложениями.
    // Код ниже часть попыток сделать хоть что-то.

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.println("Enter Tests path name: ");
        String testsPathName = input.nextLine();
        System.out.println("Enter Values path name: ");
        String valuesPathName = input.nextLine();

        File fileTests = new File(testsPathName);
        File fileValues = new File(valuesPathName);

        //read file val
        Scanner valReader = null;
        try {
            valReader = new Scanner(fileValues);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //makes string val
        String line = "";
        String jsonLineVal = "";
        while (valReader.hasNextLine()) {
            line = valReader.nextLine();
            jsonLineVal = jsonLineVal.concat(line).trim();
        }


        //read file test
        Scanner testReader = null;
        try {
            testReader = new Scanner(fileTests);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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


        JsonNode jsonNodeV = null;
        try {
            jsonNodeV = objectMapper.readTree(jsonLineVal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNodeT = null;
        try {
            jsonNodeT = objectMapper.readTree(jsonLineTest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        getKeys(jsonNodeT, keysT);
        getKeys(jsonNodeV, keysV);
        System.out.println(keysT);
        System.out.println(keysV);
        getValues(jsonNodeT,znachT);
        getValues(jsonNodeV,znachV);
        System.out.println(znachT);
        System.out.println(znachV);





    }

    public static void getKeys(JsonNode jsonNode, List<String> keys) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            fields.forEachRemaining(field -> {
                keys.add(field.getKey());
                getKeys((JsonNode) field.getValue(), keys);
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
                getValues((JsonNode) field.getValue(), znach);
            });
        } else if (jsonNode.isArray()) {
            ArrayNode arrayField = (ArrayNode) jsonNode;
            arrayField.forEach(node -> {
                getValues(node, znach);
            });
        }


    }
}