package woowacourse.shoppingcart.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderRequest {
    @NotNull
    private Long productId;
    @Min(0)
    private int quantity;

    public OrderRequest() {
    }

    public OrderRequest(final Long productId, final int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
