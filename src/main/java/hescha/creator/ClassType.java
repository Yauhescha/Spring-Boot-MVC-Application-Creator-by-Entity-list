package hescha.creator;

import lombok.Getter;

@Getter
public enum ClassType {
    SERVICE("Service", "java"),
    REPOSITORY("Repository", "java"),
    CONTROLLER("Controller", "java"),
    THYMELEAF_ALL_PAGE("ViewAllPage", "html"),
    THYMELEAF_SINGLE_PAGE("ViewSinglePage", "html"),
    THYMELEAF_EDIT_PAGE("EditPage", "html");

    final String templateName;
    final String path;
    final String type;

    ClassType(String templateName, String type) {
        this.templateName = templateName;
        this.type = type;
        this.path = "src/main/resources/templates/" + templateName + "." + type + ".vm" ;
    }
}
