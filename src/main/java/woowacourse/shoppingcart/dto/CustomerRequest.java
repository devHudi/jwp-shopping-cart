package woowacourse.shoppingcart.dto;

public class CustomerRequest {
    private String email;
    private String password;
    private String profileImageUrl;
    private String name;
    private String gender;
    private String birthDay;
    private String contact;
    private AddressRequest fullAddress;
    private boolean terms;

    public CustomerRequest() {
    }

    public CustomerRequest(String email, String password, String profileImageUrl, String name, String gender,
                           String birthDay, String contact, AddressRequest fullAddress, boolean terms) {
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.contact = contact;
        this.fullAddress = fullAddress;
        this.terms = terms;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getContact() {
        return contact;
    }

    public AddressRequest getFullAddress() {
        return fullAddress;
    }

    public boolean isTerms() {
        return terms;
    }

    @Override
    public String toString() {
        return "CustomerRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", contact='" + contact + '\'' +
                ", fullAddress=" + fullAddress +
                ", terms=" + terms +
                '}';
    }
}