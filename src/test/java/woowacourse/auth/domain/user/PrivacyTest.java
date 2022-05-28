package woowacourse.auth.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrivacyTest {
    @DisplayName("이름, 성별, 생년월일, 전화번호, 주소지 정보를 전달받아 생성된다.")
    @Test
    void of() {
        // given
        String name = "조동현";
        String gender = "male";
        String birthDay = "1998-12-21";
        String contact = "01011111111";
        String address = "서울특별시 강남구 선릉역";
        String detailAddress = "이디야 1층";
        String zoneCode = "12345";

        // when
        Privacy actual = Privacy.of(name, gender, birthDay, contact, address, detailAddress, zoneCode);

        // then
        assertThat(actual).isNotNull();
    }
}
