package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.Sale;
import com.yasinkucuker.arzum_iletisim.service.CorporateCustomerService;
import com.yasinkucuker.arzum_iletisim.service.ProductService;
import com.yasinkucuker.arzum_iletisim.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/dashboard/sale")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;
    private final CorporateCustomerService customerService;

    public SaleController(SaleService saleService, ProductService productService, CorporateCustomerService customerService) {
        this.saleService = saleService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sales", saleService.getAll());
        return "dashboard/sale/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Sale sale = new Sale();
        sale.setSaleDate(LocalDateTime.now());
        model.addAttribute("sale", sale);
        model.addAttribute("products", productService.getAll());
        model.addAttribute("customers", customerService.getAll());
        return "dashboard/sale/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("sale") Sale sale) {
        saleService.save(sale);
        return "redirect:/dashboard/sale";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("sale", saleService.getById(id));
        model.addAttribute("products", productService.getAll());
        model.addAttribute("customers", customerService.getAll());
        return "dashboard/sale/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("sale") Sale sale) {
        saleService.update(id, sale);
        return "redirect:/dashboard/sale";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        saleService.delete(id);
        return "redirect:/dashboard/sale";
    }
}
