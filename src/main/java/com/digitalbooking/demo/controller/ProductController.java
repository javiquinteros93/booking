package com.digitalbooking.demo.controller;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.dto.ProductDTO;
import com.digitalbooking.demo.model.dto.HomeProductDTO;
import com.digitalbooking.demo.model.dto.ReservationProductDTO;
import com.digitalbooking.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) throws BadRequestException {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> returnProduct(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<ProductDTO> returnProduct(@PathVariable String title) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(productService.findProductByTitle(title));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> productList() {
        return ResponseEntity.ok(productService.productList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editProduct(@RequestBody ProductDTO product) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.editProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/home")
    public ResponseEntity<List<HomeProductDTO>> randomProductList(@RequestParam(required = false) String category, @RequestParam(required = false) String city, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws BadRequestException, ResourceNotFoundException {
        if(category == null && city == null && startDate == null && endDate == null)
            return ResponseEntity.ok(productService.randomProductList());
        if(category != null && (city == null || Objects.equals(city, "null")) && startDate == null && endDate == null)
            return ResponseEntity.ok(productService.categoryProductList(category));
        if((category == null || category.equals("null")) && city != null && startDate == null && endDate == null)
            return ResponseEntity.ok(productService.cityProductList(city));
        if(category != null && city != null && startDate == null && endDate == null)
            return ResponseEntity.ok(productService.categoryCityProductList(category, city));
        if((category == null || category.equals("null")) && (city == null || Objects.equals(city, "null")) && startDate != null && endDate != null)
            return ResponseEntity.ok(productService.reservationProductList(startDate, endDate));
        if((category == null || category.equals("null")) && city != null && startDate != null && endDate != null)
            return ResponseEntity.ok(productService.reservationCityProductList(startDate, endDate, city));
        if(category != null && (city == null || Objects.equals(city, "null")) && startDate != null && endDate != null)
            return ResponseEntity.ok(productService.reservationCategoryProductList(startDate, endDate, category));
        if(category != null && city != null && startDate != null && endDate != null)
            return ResponseEntity.ok(productService.reservationCityCategoryProductList(startDate, endDate, city, category));
        return ResponseEntity.ok(productService.randomProductList());
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationProductDTO> reservationInfo(@PathVariable(value = "id") Long id) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(productService.reservationProduct(id));
    }


}

