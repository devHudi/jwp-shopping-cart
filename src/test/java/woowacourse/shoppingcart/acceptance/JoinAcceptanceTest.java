package woowacourse.shoppingcart.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowacourse.AcceptanceTest;

public class JoinAcceptanceTest extends AcceptanceTest {
    @Test
    @DisplayName("유저 회원가입 성공")
    void joinSuccess() {
        // given
        Map<String, Object> params = new HashMap<>();
        params.put("email", "hudi@gmail.com");
        params.put("password", "a1@12345");
        params.put("profileImageUrl", "http://gravatar.com/avatar/1?d=identicon");
        params.put("name", "조동현");
        params.put("gender", "male");
        params.put("birthDay", "1998-12-21");
        params.put("contact", "01074415409");
        params.put("fullAddress", Map.of("address", "서울특별시 강남구 선릉역", "detailAddress", "이디야 책상", "zoneCode", "12345"));
        params.put("terms", true);

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/customers")
                .then().log().all()
                .extract();

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(response.header("location")).isNotNull()
        );
    }
}