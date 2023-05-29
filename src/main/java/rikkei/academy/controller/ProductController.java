package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Product;
import rikkei.academy.service.IProductService;




@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping(value = {"/", "/product"})
    public ModelAndView listProduct(@PageableDefault(sort = "name",size = 3) Pageable pageable){
        Page<Product> productPage = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("pageProduct",productPage);
        return modelAndView;
    }
    @GetMapping("/create/product")
    public ModelAndView showFormCreateProduct(){
        ModelAndView modelAndView = new ModelAndView("/product/add");
        modelAndView.addObject("createProductForm",new Product());
        return modelAndView;
    }
    @PostMapping("/create/product")
    public String createProduct(@Validated @ModelAttribute("createProductForm") Product product, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "/product/add";
        }
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("search") String search, Pageable pageable){
        Page<Product> productPage;
        if(!search.trim().equals("")){
            productPage = productService.findByNameProduct(search,pageable);
        } else {
            productPage = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("pageProduct",productPage);
        return modelAndView;
    }
}