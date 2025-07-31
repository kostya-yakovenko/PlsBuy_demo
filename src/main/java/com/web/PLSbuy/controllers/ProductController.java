package com.web.PLSbuy.controllers;

import com.web.PLSbuy.models.Product;
import com.web.PLSbuy.models.User;
import com.web.PLSbuy.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String products(
            @RequestParam(name = "searchWord", required = false) String searchWord,
            @RequestParam(name = "searchCity", required = false) String searchCity,
            Model model,
            Principal principal
    ) {
        model.addAttribute("products", productService.listProducts(searchWord, searchCity));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("searchCity", searchCity);
        return "products";
    }
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        User author = product.getUser();
        model.addAttribute("authorProduct", author);
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product, Principal principal) throws IOException {

        System.out.println("File1 empty: " + file1.isEmpty() + ", name: " + file1.getOriginalFilename());
        System.out.println("File2 empty: " + file2.isEmpty() + ", name: " + file2.getOriginalFilename());
        System.out.println("File3 empty: " + file3.isEmpty() + ", name: " + file3.getOriginalFilename());

        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
    @GetMapping("/my/products")
    public String userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }
}
