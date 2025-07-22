package com.levelup.erp.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {


    @GetMapping("/view")
    public String showProductsPage() {
        return "product/viewProduct"; // loads templates/product/product.html
    }

}
