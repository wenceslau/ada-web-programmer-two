package ada.web.springadvice.annotation;

import ada.web.springadvice.controller.BookController;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueISBNValidator implements ConstraintValidator<UniqueISBN, String> {


    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (isbn == null)
            return true;

        return BookController.bookList.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .isEmpty();
    }

}
