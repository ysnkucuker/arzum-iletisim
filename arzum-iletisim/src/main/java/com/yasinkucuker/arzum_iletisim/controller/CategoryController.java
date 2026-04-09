package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.Category;
import com.yasinkucuker.arzum_iletisim.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Listeleme
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "dashboard/category/list";
    }

    // Ekleme Sayfası
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "dashboard/category/add";
    }

    // Kaydetme İşlemi
    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/dashboard/category";
    }

    // Düzenleme Sayfası
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "dashboard/category/edit";
    }

    // Güncelleme İşlemi
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("category") Category category) {
        categoryService.update(id, category);
        return "redirect:/dashboard/category";
    }

    // Silme İşlemi
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/dashboard/category";
    }
}
