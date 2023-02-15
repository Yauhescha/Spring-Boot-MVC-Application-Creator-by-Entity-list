package hescha.creator;

import lombok.Getter;

@Getter
public enum ClassType {
    SERVICE("Service"),
    REPOSITORY("Repository"),
    CONTROLLER("Controller");

    final String templateName;
    final String path;

    ClassType(String templateName) {
        this.templateName = templateName;
        this.path = "src/main/resources/templates/" + templateName + ".java.vm";
    }
}
