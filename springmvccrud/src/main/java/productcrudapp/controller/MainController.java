package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home(Model model) {
		List<Product> products = this.productDao.getProducts();
		model.addAttribute("products", products);

		return "index";
	}

	ProductDao productDao;

	public MainController(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	// show add product form
	@RequestMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add Product");

		return "add-product-form";
	}

// handle add product form
	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute("product") Product product, HttpServletRequest request) {

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");

		this.productDao.createProduct(product);
		/*
		 * if(product !=null) { System.out.println("success");
		 * List<Product>list=this.productDao.getProducts();
		 * model.addAttribute("listProuct", list); }
		 */
		return redirectView;
	}

	// delete handle
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		this.productDao.deleteProduct(productId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");

		return redirectView;
	}

	@RequestMapping(value = "/update/{productId}",method = RequestMethod.GET)
	public String updateForm(@PathVariable("productId") int pid, Model model) {
		Product product = this.productDao.getProduct(pid);
		model.addAttribute("product", product);
		return "update-form";
	}

}
