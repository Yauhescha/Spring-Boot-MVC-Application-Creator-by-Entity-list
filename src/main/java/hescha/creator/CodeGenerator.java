package hescha.creator;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@RequiredArgsConstructor
public class CodeGenerator {
    private final VelocityEngine velocityEngine;

    public String generate(ClassType classType, Class<?> pojoClass) {
        VelocityContext context = new VelocityContext();
        Template t = velocityEngine.getTemplate(classType.pathToTemplate);
        context.put("class", pojoClass);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
