package woowacourse.shoppingcart.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("이미 존재하는 이메일입니다.");
    }
}
