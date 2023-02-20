package hescha.creator;

import hescha.expectedapplication.model.Category;
import lombok.SneakyThrows;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

import static hescha.creator.ClassType.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        CodeGenerator codeGenerator = new CodeGenerator(velocityEngine);

        Class<Category> classForGenerating = Category.class;

        Set<Class> allClassesUsingClassLoader = findAllClassesUsingClassLoader(classForGenerating.getPackageName());
        System.out.println(allClassesUsingClassLoader);


        allClassesUsingClassLoader.stream()
                .filter(aClass -> !aClass.getName().contains("AbstractEntity"))
                .forEach(aClass -> {
                    try {
                        generateCode(codeGenerator, aClass);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
        }
        return null;
    }

    private static void generateCode(CodeGenerator codeGenerator, Class<Category> classForGenerating) throws IOException {
        String generatedRepository = codeGenerator.generate(REPOSITORY, classForGenerating);
        saveGeneratedCLass(REPOSITORY, classForGenerating, generatedRepository);

        String generatedService = codeGenerator.generate(SERVICE, classForGenerating);
        saveGeneratedCLass(SERVICE, classForGenerating, generatedService);

        String generatedController = codeGenerator.generate(CONTROLLER, classForGenerating);
        saveGeneratedCLass(CONTROLLER, classForGenerating, generatedController);

        String generateThymeleafListPage = codeGenerator.generate(THYMELEAF_ALL_PAGE, classForGenerating);
        saveGeneratedCLass(THYMELEAF_ALL_PAGE, classForGenerating, generateThymeleafListPage);

        String generateThymeleafSinglePage = codeGenerator.generate(THYMELEAF_SINGLE_PAGE, classForGenerating);
        saveGeneratedCLass(THYMELEAF_SINGLE_PAGE, classForGenerating, generateThymeleafSinglePage);

        String generateThymeleafEditPage = codeGenerator.generate(THYMELEAF_EDIT_PAGE, classForGenerating);
        saveGeneratedCLass(THYMELEAF_EDIT_PAGE, classForGenerating, generateThymeleafEditPage);
    }

    private static void saveGeneratedCLass(ClassType classType, Class<Category> classForGenerating, String generatedCode) throws IOException {
        String pathToFile = getPathToFile(classType, classForGenerating);
        FileWriter fw = new FileWriter(pathToFile);
        fw.append(generatedCode);
        fw.flush();
        fw.close();
    }

    private static String getPathToFile(ClassType classType, Class<Category> classForGenerating) {
        String pathToFile;
        if (classType.type.equals("java")) {
            pathToFile = "src/main/java/generated/" + classType.pathToFolderToSave
                    + "/" + classForGenerating.getSimpleName() + classType.getTemplateName() + "." + classType.getType();
        } else {
            pathToFile = "src/main/java/generated/" + classType.pathToFolderToSave
                    + "/" + classForGenerating.getSimpleName().toLowerCase() + classType.postfix + "." + classType.getType();
        }
        return pathToFile;
    }
}