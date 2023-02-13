package hescha.expectedapplication.controller;

import hescha.expectedapplication.model.Category;
import hescha.expectedapplication.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping(CategoryController.ADDRESS)
public class CategoryController {
    public static final String MANY = "categories";
    public static final String ONE = MANY + "-one";
    public static final String ADDRESS = "/" + MANY;
    public static final String STATUS = "STATUS";
    private final CategoryService service;


    @PostMapping
    public String create(@ModelAttribute Category entity, RedirectAttributes ra) {
        try {
            Category createdEntity = service.create(entity);
            return "redirect:" + ADDRESS + "/" + createdEntity.getId();
        } catch (Exception e) {
            ra.addFlashAttribute(STATUS, "Creating failed");
            e.printStackTrace();
        }
        return "redirect:" + ADDRESS;
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return MANY;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("list", service.read(id));
        return ONE;
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Category entity, @PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            service.update(id, entity);
            ra.addFlashAttribute(STATUS, "Editing is successful");
            return "redirect:" + ADDRESS + "/" + id;
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(STATUS, "Editing failed");
        }

        return "redirect:" + ADDRESS;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(STATUS, "Removing is successful");
        } catch (Exception ex) {
            ra.addFlashAttribute(STATUS, "Removing failed");
        }
        return "redirect:" + ADDRESS;
    }
}
