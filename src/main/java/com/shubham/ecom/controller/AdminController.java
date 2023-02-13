package com.shubham.ecom.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shubham.ecom.dto.ProductDTO;
import com.shubham.ecom.model.Category;
import com.shubham.ecom.model.Product;
import com.shubham.ecom.service.CategoryService;
import com.shubham.ecom.service.ProductService;

@Controller
public class AdminController {
	
//	Creating class level variable for product directory location to save image in static
//	System.getProperty("user.dir", ...) will give you path till project main folder = C:\Users\Shubham\eclipse-workspace\major
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
//	=================== Category Section ==========================
	
//	getting admin page
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
//	Read category
//	getting category page and showing list of category from database
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
//	getting add category page with list of data
//	we cant send it blank because we have requested object with name if 'category' in categoriesAdd.html so sending blank Category object
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
//	Creating/Saving category
//	after submitting form with post method we will be saving that data in database and redirected to categories page
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
//	Delete Category
//	getmapping for deleting category and redirecting to admin/categories
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
//	Update Category
//	1. get mapping to redirect to categoriesAdd.html
//	2. will send id and category name with getmapping and checking in database that id and category name and send to categoriesAdd page with prefilled form 
	@GetMapping("/admin/categories/update/{id}")
//	taking 
	public String updateCat(@PathVariable int id, Model model) {
//		sending id, model to categoryService to find value
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
//	3. after redirecting with prefilled form with id and category name we can update category name and it will work normal like create/save category because JPA features
		}else {
			return "404";
		}
	}
	
//	================= Product Section =======================
	
//	For getting page of all products from database
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "products";
	}
	
//	Getting page of addProduct
	@GetMapping("/admin/products/add")
	public String getProductsAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
//	post method for when we click on select image in addProduct page
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
//			MultipartFile is used for handling img, pdf, videos and all types of file
								 @RequestParam("productImage")MultipartFile file,
//          we are using imgName so when updating we can select image location in server before updating image path
								 @RequestParam("imgName")String imgName) throws IOException{
		
//		getting all value from add product form and sending to product service for saving in database
//		first we have to get individual product details from productDTO
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
//		have to select specific category id from category list options
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		
//	=========================== Storing and handling image =========================
//		Storing image into src/main/resources => static => productImages and also in database with the help of MultipartFile
		String imageUUID;
//		checking if isempty is not true
		if(!file.isEmpty()) {
//			storing file name in variable if its available
			imageUUID = file.getOriginalFilename();
			
//			storing image path in variable if its available
			
//			Path = An object that may be used to locate a file in a file system. It willtypically represent a system dependent file path.
//			it takes two argument first file path and second is file name
//			uploadDir is created in class level at start of AdminController class at top 👆
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
//			Files is used for storing file with Byte as array for storing it
			Files.write(fileNameAndPath, file.getBytes());
		} else {
//			if image name is not given/ image is not selected then
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
	
//	For mapping delete product
	@GetMapping("admin/products/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/update/{id}")
	public String updateProductGet(@PathVariable int id, Model model) {
		
//		here we got product id from getmapping for updating a product
//		finding product value and saving in Optional with help of productService
		Product product = productService.getProductById(id).get();
		
//		saving product value in productDTO for sending with Model.addAtribute
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight()); 
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
//		sending value of productDTO to productsAdd page as well as category values
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
		return "productsAdd";
	}
}
