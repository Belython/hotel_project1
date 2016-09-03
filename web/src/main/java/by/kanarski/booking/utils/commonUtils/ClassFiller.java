package by.kanarski.booking.utils.commonUtils;

import by.kanarski.booking.constants.ResourcePath;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Дмитрий on 03.09.2016.
 */
public class ClassFiller {

    private static String charset = "UTF-8";

    public static void main(String[] args) {
        try {
            String classDir = "E:\\projects\\hotel_project1\\web\\src\\main\\java\\by\\kanarski\\booking\\constants\\PageParameterName.java";
            StringBuilder classSrc = getClassSrc(classDir);
            Map<String, String> fieldMap = getFiledMap(ResourcePath.TEXT_SOURCE);
            String filledClassSrc = createFilledClassNew(classSrc, fieldMap);
            writeInClassFile(classDir, filledClassSrc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getClassSrc(String classDir) throws IOException {
        File classFile = new File(classDir);
        StringBuilder classSrc = new StringBuilder();
        FileInputStream fis = new FileInputStream(classFile);
        InputStreamReader isr = new InputStreamReader(fis, charset);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        System.out.println(line);
        while (line != null) {
            classSrc.append(line + System.getProperty("line.separator"));
            line = br.readLine();
        }
        br.close();
        isr.close();
        fis.close();
        return classSrc;
    }

    private static Map<String, String> getFiledMap(String resurceBundlePath) {
        ResourceBundle bundle = ResourceBundle.getBundle(resurceBundlePath, new Locale("ru", "RU"));
        Set<String> keySet = bundle.keySet();
        String fieldRegExp = "\\w+";
        HashMap<String, String> fieldMap = new HashMap<>();
        Pattern pattern = Pattern.compile(fieldRegExp);
        for(String key: keySet) {
            Matcher matcher = pattern.matcher(key);
            matcher.find();
            String fieldName = matcher.group();
            matcher.find();
            String fieldValue = matcher.group();
            if (fieldMap.containsKey(fieldName)) {
                String fieldPartName = fieldMap.get(fieldName);
                fieldPartName = fieldPartName + ",\n        \"" + fieldValue + "\"";
                fieldMap.put(fieldName, fieldPartName);
            } else {
                fieldMap.put(fieldName,"        \"" + fieldValue + "\"");
            }
        }
        return fieldMap;
    }

//    private static String createFilledClass(StringBuilder classSrc, Map<String, String> fieldMap) {
//        Set<String> fieldSet = fieldMap.keySet();
//        String tClassSrc = classSrc.toString();
//        for (String field : fieldSet) {
//            String propRegexp = "public static final List<String> " + field.toUpperCase() + " = Arrays.asList\\(";
//            Pattern pattern = Pattern.compile(propRegexp);
//            Matcher matcher = pattern.matcher(tClassSrc);
//            if (matcher.find()) {
//                classSrc = classSrc.insert(matcher.end(), "\n" + fieldMap.get(field));
//            }
//            tClassSrc = classSrc.toString();
//        }
//        return tClassSrc;
//    }

    private static String createFilledClassNew(StringBuilder classSrc, Map<String, String> fieldMap) {
        Set<String> fieldSet = fieldMap.keySet();
        String tClassSrc = classSrc.toString();
        String startRegExp = "public class \\w+ \\{";
        Pattern pattern = Pattern.compile(startRegExp);
        Matcher matcher = pattern.matcher(tClassSrc);
        matcher.find();
        int startPosition = matcher.end();
        for (String field : fieldSet) {
            String classProperty = "\n\n    public static final List<String> " + field.toUpperCase() + " = Arrays.asList (\n" +
                    fieldMap.get(field) +
                    "\n    );";
            classSrc = classSrc.insert(startPosition, classProperty);
            tClassSrc = classSrc.toString();
        }
        return tClassSrc;
    }


    private static void writeInClassFile(String classDir, String classSrc) throws IOException{
        File classFile = new File(classDir);
        FileOutputStream fos = new FileOutputStream(classFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
        osw.write(classSrc);
        osw.flush();
        osw.close();
        fos.close();
    }
}
