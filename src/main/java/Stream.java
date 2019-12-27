import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream {

    public static Map<String, Object> parseMapForFilter(Map<String, Object> map) {
        if (map == null) {
            return null;
        } else {
            map = map.entrySet().stream()
                    .filter((e) -> checkValue(e.getValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue
                    ));
        }
        return map;
    }

    private static boolean checkValue(Object object) {
        if (object instanceof String && "".equals(object)) {
            return false;
        }
        return object != null;
    }

    public static Map<String, Object> parseMapForFilterByOptional(Map<String, Object> map) {

        return Optional.ofNullable(map).map(
                (v) -> {
                    Map params = v.entrySet().stream()
                            .filter((e) -> checkValueByOptional(e.getValue()))
                            .collect(Collectors.toMap(
                                    (e) -> (String) e.getKey(),
                                    (e) -> e.getValue()
                            ));
                    return params;
                }
        ).orElse(null);
    }

    private static boolean checkValueByOptional(Object object) {
        return (Boolean) Optional.ofNullable(object)
                .filter((e) -> e instanceof String && e.equals("") ? false : true)
                .orElse(false);
    }

    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<>(16);
        Map<String,Object> params1 = new HashMap<>(16);

        params.put("a","");
        params.put("b",null);
        params.put("c","hi");

        params = parseMapForFilter(params);
        System.out.println(params);
        System.out.println(parseMapForFilter(null));

        params1.put("a","");
        params1.put("b",null);
        params1.put("c","hello");

        params1 = parseMapForFilterByOptional(params1);
        System.out.println(params1);
        System.out.println(parseMapForFilterByOptional(null));
    }
}
