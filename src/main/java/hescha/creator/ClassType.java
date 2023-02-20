package hescha.creator;

import lombok.Getter;

@Getter
public enum ClassType {
    SERVICE("Service", "service", "java", ""),
    REPOSITORY("Repository", "repository", "java", ""),
    CONTROLLER("Controller", "controller", "java", ""),
    THYMELEAF_ALL_PAGE("ViewAllPage", "templates", "html", ""),
    THYMELEAF_SINGLE_PAGE("ViewSinglePage", "templates", "html", "-one"),
    THYMELEAF_EDIT_PAGE("EditPage", "templates", "html", "-edit");

    final String templateName;
    final String pathToFolderToSave;
    final String pathToTemplate;

    final String type;
    final String postfix;

    ClassType(String templateName, String pathToFolderToSave, String type, String postfix) {
        this.templateName = templateName;
        this.pathToFolderToSave = pathToFolderToSave;
        this.type = type;
        this.postfix = postfix;
        this.pathToTemplate = "src/main/resources/templates/" + templateName + "." + type + ".vm" ;
    }
}
