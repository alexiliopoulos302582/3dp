package com.DPdexion.controller;


import com.DPdexion.entity.Product;
import com.DPdexion.repository.ProductRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/search")
public class searchProducts {

    private ProductRepository productRepository;

    public searchProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/crud")
    public String showEntities(@Param("keyword") String keyword,
                               @RequestParam(name = "sortOrder",
                                       required = false, defaultValue = "asc") String sortOrder,
                               Model model) {
        List<Product> products;

        if ("asc".equalsIgnoreCase(sortOrder)) {
            products = productRepository.findAllSortedAsc(keyword);
        } else {
            products = productRepository.findAllSortedDesc(keyword);
        }

        model.addAttribute("products", products);
        return "entities";
    }







//    working
//    @GetMapping("/crud")
//    public String showEntities(@Param("keyword") String keyword,
//                               Model model) {
//        List<Product> products = productRepository.findAll(keyword);
//
//        model.addAttribute("products", products);
//        return "entities";}
//

//    public List<Product> listAll(String keyword) {
//        if (keyword != null) {
//            return productRepository.findAll(keyword); }
//        return productRepository.findAll( );
//    }


//    @GetMapping("/crud")
//    public String showEntities(
//
//            Model model) {
//        List<Product> products = productRepository.findAll();
//        model.addAttribute("products", products);
//        return "entities";}


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
                Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("products", optionalProduct.get());
            return "update-form";
        } else {
                        return "product-not-found";
        }
    }

    @GetMapping
    public List<Product> getAllEntities() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/update-form")
    public String showAddProductForm(Model model) {
        model.addAttribute("newProduct", new Product());

        // Return the Thymeleaf template for adding a new product
        return "add-product-form";
    }

    @PostMapping("/save")
    public String saveNewProduct(@ModelAttribute Product newProduct) {
        // Use the productRepository to save the new product
        productRepository.save(newProduct);

        // Redirect to the page showing all entities after saving
        return "redirect:/api/search/crud";
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedproduct) {
        if (productRepository.existsById(id)) {
            updatedproduct.setId(id);
            return ResponseEntity.ok(productRepository.save(updatedproduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product updatedProduct) {

        productRepository.save(updatedProduct);

        // Redirect to the page showing all entities after the update
        return "redirect:/api/search/crud";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/delete/{id}")
    public  String deleteProduct(@PathVariable Long id ) {


            productRepository.deleteById(id);
            return "redirect:/api/search/crud";

    }




//        return "redirect:/api/search/crud";



//    @CrossOrigin(origins = "*")
//    @GetMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


}
