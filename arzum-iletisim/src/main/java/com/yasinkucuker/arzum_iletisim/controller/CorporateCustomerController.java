package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.CorporateCustomer;
import com.yasinkucuker.arzum_iletisim.service.CorporateCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/corporatecustomer")
public class CorporateCustomerController {

    private final CorporateCustomerService customerService;

    public CorporateCustomerController(CorporateCustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "dashboard/corporatecustomer/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new CorporateCustomer());
        return "dashboard/corporatecustomer/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") CorporateCustomer customer) {
        customerService.save(customer);
        return "redirect:/dashboard/corporatecustomer";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "dashboard/corporatecustomer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("customer") CorporateCustomer customer) {
        customerService.update(id, customer);
        return "redirect:/dashboard/corporatecustomer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/dashboard/corporatecustomer";
    }
}