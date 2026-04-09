package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.Product;
import com.yasinkucuker.arzum_iletisim.service.CategoryService;
import com.yasinkucuker.arzum_iletisim.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/dashboard/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAll());
        return "dashboard/product/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "dashboard/product/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product,
                       @RequestParam("imageFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            product.setImage(file.getBytes());
        }
        productService.save(product);
        return "redirect:/dashboard/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getById(id); // Burada ürün var mı kontrol et
        if (product == null) {
            return "redirect:/dashboard/product"; // Veya hata sayfasına yönlendir
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll()); // Burası kritik!
        return "dashboard/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("product") Product product,
                         @RequestParam("imageFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            product.setImage(file.getBytes());
        } else {
            // Eğer yeni resim seçilmediyse eskisini koru
            product.setImage(productService.getById(id).getImage());
        }
        productService.update(id, product);
        return "redirect:/dashboard/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/dashboard/product";
    }

    // Byte dizisini HTML'de göstermek için yardımcı metod
    @GetMapping("/display/{id}")
    @ResponseBody
    public byte[] displayImage(@PathVariable Long id) {
        Product product = productService.getById(id);
        return product.getImage();
    }
}
