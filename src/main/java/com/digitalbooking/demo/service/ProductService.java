package com.digitalbooking.demo.service;

import com.digitalbooking.demo.exceptions.BadRequestException;
import com.digitalbooking.demo.exceptions.ResourceNotFoundException;
import com.digitalbooking.demo.model.*;
import com.digitalbooking.demo.model.Product;
import com.digitalbooking.demo.model.dto.*;
import com.digitalbooking.demo.model.dto.ProductDTO;
import com.digitalbooking.demo.repository.CategoryRepository;
import com.digitalbooking.demo.repository.CityRepository;
import com.digitalbooking.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ObjectMapper mapper;

    public ProductDTO addProduct(ProductDTO product) throws BadRequestException {
        if(product == null)
            throw new BadRequestException("Didn't get a product to save");
        productRepository.save(mapper.convertValue(product,Product.class));
        return product;
    }

    public ProductDTO findProductById(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new ResourceNotFoundException("Can't find product with id: " + id);
        return mapper.convertValue(product,ProductDTO.class);
    }

    public ProductDTO findProductByTitle(String title) throws BadRequestException, ResourceNotFoundException {
        if(title==null)
            throw new BadRequestException("Title can't be null");
        List<Product> product = productRepository.findByProductTitle(title);
        if(product.isEmpty())
            throw new ResourceNotFoundException("Can't find product titled: " + title);
        return mapper.convertValue(product.get(0),ProductDTO.class);
    }

    public List<ProductDTO> productList() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productsDTOS = new ArrayList<>();
        for (Product c:
                products) {
            ProductDTO cdto = mapper.convertValue(c,ProductDTO.class);
            productsDTOS.add(cdto);
        }
        return productsDTOS;
    }

    public String editProduct(ProductDTO product) throws ResourceNotFoundException {
        List<Product> cat = productRepository.findByProductTitle(product.getTitle());
        if(cat.isEmpty())
            throw new ResourceNotFoundException("Can't find any product named: " + product.getTitle());
        if(cat.size() > 1)
            throw new ResourceNotFoundException("There are too many products with that name");
        productRepository.save(cat.get(0));
        return "Product: " + product.getTitle() + " has been updated.";
    }

    public String deleteProduct(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        if (productRepository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Can't find product with id: " + id);
        productRepository.deleteById(id);
        return "Product with id: " + id + " has been updated.";
    }

    public ReservationProductDTO reservationProduct(Long id) throws BadRequestException, ResourceNotFoundException {
        if(id==null)
            throw new BadRequestException("Id can't be null");
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new ResourceNotFoundException("Can't find product with id: " + id);
        return mapper.convertValue(product,ReservationProductDTO.class);
    }

    public List<HomeProductDTO> randomProductList(){
        List<Product> products = productRepository.findAll();
        Collections.shuffle(products);
        List<HomeProductDTO> productsDTOS = new ArrayList<>();
        for (Product c:
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productsDTOS.add(cdto);
        }
        return productsDTOS.subList(0,8);
    }

    public List<HomeProductDTO> cityProductList(String name){
        List<City> city = cityRepository.findByCityName(name);
        List<Product> products = productRepository.findByCity(city.get(0));
        List<HomeProductDTO> productsDTOS = new ArrayList<>();
        for (Product c:
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productsDTOS.add(cdto);
        }
        return productsDTOS;
    }

    public List<HomeProductDTO> categoryProductList(String title) throws BadRequestException {
        List<Category> category = categoryRepository.findByCategoryTitle(title);
        if(category.size() == 0)
            throw new BadRequestException("Can't find category: " + title);
        List<Product> products = productRepository.findByCategory(category.get(0));
        List<HomeProductDTO> productsDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productsDTOS.add(cdto);
        }
        return productsDTOS;
    }

    public List<HomeProductDTO> categoryCityProductList(String title, String name) throws BadRequestException, ResourceNotFoundException {
        List<Category> category = categoryRepository.findByCategoryTitle(title);
        if(category.size() == 0)
            throw new BadRequestException("Can't find category: " + title);
        List<Product> products = productRepository.findByCategory(category.get(0)).stream().filter(product -> product.getCity().getName().equals(name)).collect(Collectors.toList());
        if(products.size() == 0)
            throw new ResourceNotFoundException("There are no products with category: " + title + " in: " + name);
        List<HomeProductDTO> productsDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productsDTOS.add(cdto);
        }
        return productsDTOS;
    }

    public List<HomeProductDTO> reservationProductList(Date startDate, Date endDate){
        List<Product> products = productRepository.findByDate(startDate, endDate);
        List<HomeProductDTO> productDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productDTOS.add(cdto);
        }
        return productDTOS;
    }

    public List<HomeProductDTO> reservationCategoryProductList(Date startDate, Date endDate, String title) throws ResourceNotFoundException {
        List<Product> products = productRepository.findByDate(startDate, endDate).stream().filter(product -> product.getCategory().getTitle().equals(title)).collect(Collectors.toList());
        if(products.size() == 0)
            throw new ResourceNotFoundException("There are no : " + title + " available for those dates.");
        List<HomeProductDTO> productDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productDTOS.add(cdto);
        }
        return productDTOS;
    }

    public List<HomeProductDTO> reservationCityProductList(Date startDate, Date endDate, String city) throws ResourceNotFoundException {
        List<Product> products = productRepository.findByReservationDateAndCity(startDate, endDate, city);
        if(products.size() == 0)
            throw new ResourceNotFoundException("There are no products available in: " + city + " for those dates.");
        List<HomeProductDTO> productDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productDTOS.add(cdto);
        }
        return productDTOS;
    }

    public List<HomeProductDTO> reservationCityCategoryProductList(Date startDate, Date endDate, String city, String title) throws ResourceNotFoundException {
        List<Product> products = productRepository.findByReservationDateAndCity(startDate, endDate, city).stream().filter(product -> product.getCategory().getTitle().equals(title)).collect(Collectors.toList());
        if(products.size() == 0)
            throw new ResourceNotFoundException("There are no : " + title + " available in: " + city + " for those dates.");
        List<HomeProductDTO> productDTOS = new ArrayList<>();
        for (Product c :
                products) {
            HomeProductDTO cdto = mapper.convertValue(c, HomeProductDTO.class);
            productDTOS.add(cdto);
        }
        return productDTOS;
    }

}



