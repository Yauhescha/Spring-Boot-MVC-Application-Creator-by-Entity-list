package hescha.creator;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@RequiredArgsConstructor
public class RepositoryGenerator {
    private static final String TEMPLATE_NAME = "Repository";
    private static final String TEMPLATE_PATH = "src/main/resources/templates/" + TEMPLATE_NAME + ".java.vm";
    private final VelocityEngine velocityEngine;

    public String generateRepositoryForClass(Class<?> pojoClass) {
        VelocityContext context = new VelocityContext();
        Template t = velocityEngine.getTemplate(TEMPLATE_PATH);
        context.put("class", pojoClass);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
