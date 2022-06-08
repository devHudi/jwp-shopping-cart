package woowacourse.shoppingcart.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.shoppingcart.dao.OrderDao;
import woowacourse.shoppingcart.dao.ProductDao;
import woowacourse.shoppingcart.domain.OrderDetail;
import woowacourse.shoppingcart.domain.Orders;
import woowacourse.shoppingcart.dto.request.OrderRequest;
import woowacourse.shoppingcart.dto.response.OrderProductResponse;
import woowacourse.shoppingcart.dto.response.OrderResponse;
import woowacourse.shoppingcart.dto.response.ProductResponse;
import woowacourse.shoppingcart.exception.InvalidOrderException;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderService(final OrderDao orderDao, final ProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    public Long addOrder(final List<OrderRequest> orderRequests, final Long customerId) {
        List<OrderDetail> orderDetails = orderRequests.stream()
                .map(orderRequest -> new OrderDetail(productDao.findById(orderRequest.getProductId()),
                        orderRequest.getQuantity())).collect(
                        Collectors.toList());

        Orders orders = new Orders(orderDetails, customerId);
        return orderDao.save(orders);
    }

    public OrderResponse findOrderById(final Long customerId, final Long orderId) {
        validateOrderIdByCustomerId(customerId, orderId);
        Orders orders = orderDao.findById(orderId);

        return convertOrderToResponse(orders);
    }

    private void validateOrderIdByCustomerId(final Long customerId, final Long orderId) {
        if (!orderDao.isValidOrderId(customerId, orderId)) {
            throw new InvalidOrderException("유저에게는 해당 order_id가 없습니다.");
        }
    }

    public List<OrderResponse> findOrdersByCustomerId(final Long customerId) {
        List<Orders> orders = orderDao.findAllByCustomerId(customerId);

        return orders.stream().map(this::convertOrderToResponse).collect(Collectors.toList());
    }

    private OrderResponse convertOrderToResponse(Orders orders) {
        List<OrderProductResponse> orderProductResponses = getOrderProductResponseByOrder(orders);
        return new OrderResponse(orderProductResponses);
    }

    private List<OrderProductResponse> getOrderProductResponseByOrder(Orders orders) {
        return orders.getOrderProducts().stream()
                .map(orderDetail -> new OrderProductResponse(new ProductResponse(orderDetail.getProduct()),
                        orderDetail.getQuantity())).collect(
                        Collectors.toList());
    }
}
