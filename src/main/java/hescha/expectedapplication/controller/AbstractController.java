package hescha.expectedapplication.controller;

import hescha.expectedapplication.model.AbstractEntity;
import hescha.expectedapplication.service.CrudService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractController<CurrentClass extends AbstractEntity> {
    public final String STATUS = "STATUS";
    public final String CURRENT_ADDRESS;
    public final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    public final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    public final String REDIRECT_TO_ALL_ITEMS;
    private final CrudService<CurrentClass> service;

    public AbstractController(CrudService<CurrentClass> service,
                              String address) {
        this.service = service;

        String currentAddress = address.toLowerCase();
        this.CURRENT_ADDRESS = "/" + currentAddress;
        this.THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = currentAddress;
        this.THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
        this.REDIRECT_TO_ALL_ITEMS = "redirect:/" + CURRENT_ADDRESS;
    }

    @PostMapping
    public String create(@ModelAttribute CurrentClass entity,
                         RedirectAttributes ra) {
        try {
            AbstractEntity createdEntity = service.create(entity);
            return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
        } catch (Exception e) {
            ra.addFlashAttribute(STATUS, "Creating failed");
            e.printStackTrace();
        }
        return REDIRECT_TO_ALL_ITEMS;
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id,
                       Model model) {
        model.addAttribute("list", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute CurrentClass entity,
                         @PathVariable("id") Long id,
                         RedirectAttributes ra) {
        try {
            service.update(id, entity);
            ra.addFlashAttribute(STATUS, "Editing is successful");
            return REDIRECT_TO_ALL_ITEMS + "/" + id;
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(STATUS, "Editing failed");
        }

        return REDIRECT_TO_ALL_ITEMS;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(STATUS, "Removing is successful");
        } catch (Exception ex) {
            ra.addFlashAttribute(STATUS, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
