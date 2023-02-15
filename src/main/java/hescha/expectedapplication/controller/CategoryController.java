package hescha.expectedapplication.controller;

import hescha.expectedapplication.model.Category;
import hescha.expectedapplication.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CategoryController.CURRENT_ADDRESS)
public class CategoryController extends AbstractController<Category> {
    public static final String CURRENT_ADDRESS = "/" + "categories";
    private final CategoryService service;

    public CategoryController(CategoryService service, String address) {
        super(service, address);
        this.service = service;
    }
}
