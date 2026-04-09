package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.TechnicalService;
import com.yasinkucuker.arzum_iletisim.service.ProductService;
import com.yasinkucuker.arzum_iletisim.service.TechnicalServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/dashboard/technicalservice")
public class TechnicalServiceController {

    private final TechnicalServiceService service;
    private final ProductService productService;

    public TechnicalServiceController(TechnicalServiceService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", service.getAll());
        return "dashboard/technicalservice/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        TechnicalService ts = new TechnicalService();
        ts.setRequestDate(LocalDateTime.now()); // Varsayılan açılış tarihi bugün
        model.addAttribute("technicalService", ts);
        model.addAttribute("products", productService.getAll());
        return "dashboard/technicalservice/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("technicalService") TechnicalService ts) {
        service.save(ts);
        return "redirect:/dashboard/technicalservice";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("technicalService", service.getById(id));
        model.addAttribute("products", productService.getAll());
        return "dashboard/technicalservice/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("technicalService") TechnicalService ts) {
        service.update(id, ts);
        return "redirect:/dashboard/technicalservice";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/dashboard/technicalservice";
    }
}
