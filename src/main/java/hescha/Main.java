package hescha;

import hescha.creator.ClassType;
import hescha.creator.CodeGenerator;
import hescha.expectedapplication.model.Category;
import lombok.SneakyThrows;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;

import static hescha.creator.ClassType.SERVICE;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        CodeGenerator repositoryGenerator = new CodeGenerator(velocityEngine);

        Class<Category> classForGenerating = Category.class;

//        String generatedRepository = repositoryGenerator.generate(REPOSITORY, classForGenerating);
//        saveGeneratedCLass(REPOSITORY, classForGenerating, generatedRepository);

        String generatedRepository = repositoryGenerator.generate(SERVICE, classForGenerating);
        saveGeneratedCLass(SERVICE, classForGenerating, generatedRepository);

    }

    private static void saveGeneratedCLass(ClassType classType, Class<Category> classForGenerating, String generatedCode) throws IOException {
        String pathToFile = getPathToFile(classType, classForGenerating);
        FileWriter fw = new FileWriter(pathToFile);
        fw.append(generatedCode);
        fw.flush();
        fw.close();
    }

    private static String getPathToFile(ClassType classType, Class<Category> classForGenerating) {
        String pathToFile = "src/main/java/generated/" + classType.getTemplateName().toLowerCase()
                + "/" + classForGenerating.getSimpleName() + classType.getTemplateName() + ".java";
        return pathToFile;
    }
}