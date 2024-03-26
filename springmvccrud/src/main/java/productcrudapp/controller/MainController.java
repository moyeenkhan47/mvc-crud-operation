package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;
import productcrudapp.user.User;
import productcrudapp.user.UserService;

@Controller
public class MainController {
	ProductDao productDao;

	public MainController(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Autowired
	private UserService userService;

	// registration
	@RequestMapping("/")
	public String showForm() {
		return "registrationform";
	}

	@PostMapping("processform")
	public String handleForm(@ModelAttribute("user") User user, Model model) {
		System.out.println(user);
		this.userService.createUser(user);

		return "login";
	}

	// login form
	@GetMapping("/loginForm")
	public String showLoginForm() {

		return "login";
	}

// user authentication
	@PostMapping("/loginuser")
	public String handleLoginForm(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {
		

		if (isValidCredentials(username, password)) {
			session.setAttribute("username", username);

			return "redirect:/index";
		} else {

			model.addAttribute("error", "Invalid username or password");
			return "redirect:/loginForm";
		}
	}

	private boolean isValidCredentials(String username, String password) {

		User user = userService.getUserByUsername(username);
		return user != null && user.getPassword().equals(password);
	}

	@PostMapping("/logoutForm")
	public String showLogoutForm(HttpSession session) {
		session.invalidate();
		return "registrationform";
	}

//index page
	@RequestMapping("/index")
	public String home(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username != null) {
			// Pass the username to the model for displaying in the welcome message
			model.addAttribute("username", username);
		}
		List<Product> products = this.productDao.getProducts();
		model.addAttribute("products", products);
		return "index";

	}

	// add product
	@RequestMapping("/add-product")
	public String addproduct() {
		return "add-product-form";
	}
	
// handle add product form

	@PostMapping("/handle-product")
	public RedirectView handleProduct(@ModelAttribute("product") Product product, HttpServletRequest request) {

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/index");

		this.productDao.createProduct(product);

		return redirectView;
	}

	@GetMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		this.productDao.deleteProduct(productId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/index");

		return redirectView;
	}

// update show
	@GetMapping("/updateSave/{pid}")
	public String updateForm(@PathVariable("pid") int pid, Model model) {
		Product product = this.productDao.getProduct(pid);
		model.addAttribute("product", product);
		System.out.println("in form");
		return "update-form";
	}

	// update handle

	@GetMapping(value = "/update-handle")
	public RedirectView updateHandle(@ModelAttribute("product") Product product, int id, String name,
			String description, Double price, HttpServletRequest request, Model model) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/index");
		this.productDao.updateData(id, name, description, price);
		model.addAttribute("product", product);
		System.out.println("in update handle");
		return redirectView;
	}

	// view handle
	@GetMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable("productId") int pid, Model model) {
		Product product = this.productDao.getProduct(pid);
		model.addAttribute("product", product);
		return "view";
	}

	// search handle
	@PostMapping("/searchProduct")
	public String searchProduct(HttpServletRequest request, Model model) {
		String searchKeyword = request.getParameter("searchKeyword");

		try {
			// Try parsing the input as an integer (product ID)
			Integer productId = Integer.parseInt(searchKeyword);
			Product productById = this.productDao.getProduct(productId);

			if (productById != null) {
				model.addAttribute("product", productById);
				return "search";
			} else {
				model.addAttribute("message", "No product found for the given ID.");
			}
		} catch (NumberFormatException e) {
			// Input is not a valid integer, try searching by name or price
			try {
				// Try parsing the input as a double (product price)
				double price = Double.parseDouble(searchKeyword);
				Product productsByPrice = this.productDao.getProductsByPrice(price);

				if (productsByPrice != null) {
					model.addAttribute("product", productsByPrice);
					return "search";
				} else {
					model.addAttribute("message", "No products found for the given price.");
				}
			} catch (NumberFormatException e2) {
				// Input is not a valid double, search by name
				Product productByName = this.productDao.getProductByName(searchKeyword);

				if (productByName != null) {
					model.addAttribute("product", productByName);
					return "search";
				} else {
					model.addAttribute("message", "No product found for the given name.");
				}
			}
		}

		return "search";
	}
}
