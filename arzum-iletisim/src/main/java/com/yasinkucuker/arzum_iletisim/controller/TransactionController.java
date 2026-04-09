package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.Transaction;
import com.yasinkucuker.arzum_iletisim.service.CorporateCustomerService;
import com.yasinkucuker.arzum_iletisim.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/dashboard/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final CorporateCustomerService customerService;

    public TransactionController(TransactionService transactionService, CorporateCustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactions", transactionService.getAll());
        return "dashboard/transaction/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Transaction t = new Transaction();
        t.setTransactionDate(LocalDateTime.now());
        model.addAttribute("transaction", t);
        model.addAttribute("customers", customerService.getAll());
        return "dashboard/transaction/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("transaction") Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/dashboard/transaction";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("transaction", transactionService.getById(id));
        model.addAttribute("customers", customerService.getAll());
        return "dashboard/transaction/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("transaction") Transaction transaction) {
        transactionService.update(id, transaction);
        return "redirect:/dashboard/transaction";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionService.delete(id);
        return "redirect:/dashboard/transaction";
    }
}
