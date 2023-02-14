package hescha;

import hescha.creator.RepositoryGenerator;
import hescha.expectedapplication.model.Category;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Class<Category> classForGenerating = Category.class;
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        RepositoryGenerator repositoryGenerator = new RepositoryGenerator(velocityEngine);
        String generatedRepository = repositoryGenerator.generateRepositoryForClass(classForGenerating);

        FileWriter fw = new FileWriter("generated/repository/" + classForGenerating.getSimpleName() + "Repository" + ".java");
        fw.append(generatedRepository);
        fw.flush();
        fw.close();
    }
}