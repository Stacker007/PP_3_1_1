package by.petrushenko.PP_3_1_1.util;

import by.petrushenko.PP_3_1_1.model.User;
import by.petrushenko.PP_3_1_1.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!userRepository.findUserByEmail(user.getEmail()).isEmpty()) {
            errors.rejectValue("email", null, "This email address is already in use");
        }

    }
}
