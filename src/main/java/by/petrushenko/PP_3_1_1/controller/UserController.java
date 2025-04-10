package by.petrushenko.PP_3_1_1.controller;

import by.petrushenko.PP_3_1_1.model.User;
import by.petrushenko.PP_3_1_1.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping()
    public String users(Model model) {

        model.addAttribute("people", userRepository.findAll());
        return "users/index";
    }

    @GetMapping("/user")
    public String user(@RequestParam(required = false) int id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);

        return "users/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam int id) {
        User user1 = userRepository.findById(id).orElse(null);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
