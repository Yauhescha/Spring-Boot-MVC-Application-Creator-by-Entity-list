package hescha;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("src/main/resources/templates/Repository.java.vm");
        VelocityContext context = new VelocityContext();
        context.put("class", main.java.hescha.expectedapplication.model.Category.class);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        FileWriter fw = new FileWriter("generated/"+System.currentTimeMillis() + ".java");
        fw.append(writer.toString());
        fw.flush();
        fw.close();
    }
}