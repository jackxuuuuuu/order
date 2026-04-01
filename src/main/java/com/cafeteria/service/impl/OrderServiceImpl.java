package com.cafeteria.service.impl;

import com.cafeteria.dao.impl.DishDaoImpl;
import com.cafeteria.dao.impl.OrderDaoImpl;
import com.cafeteria.dao.impl.OrderDetailDaoImpl;
import com.cafeteria.dao.impl.ShoppingCartDaoImpl;
import com.cafeteria.entity.Dish;
import com.cafeteria.entity.Order;
import com.cafeteria.entity.OrderDetail;
import com.cafeteria.entity.ShoppingCart;
import com.cafeteria.service.OrderService;
import com.cafeteria.util.CommonUtils;
import com.cafeteria.util.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单服务实现类
 * Order service implementation
 */
public class OrderServiceImpl implements OrderService {

    private final OrderDaoImpl orderDao;
    private final OrderDetailDaoImpl orderDetailDao;
    private final ShoppingCartDaoImpl shoppingCartDao;
    private final DishDaoImpl dishDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
        this.orderDetailDao = new OrderDetailDaoImpl();
        this.shoppingCartDao = new ShoppingCartDaoImpl();
        this.dishDao = new DishDaoImpl();
    }

    public OrderServiceImpl(OrderDaoImpl orderDao, OrderDetailDaoImpl orderDetailDao,
                            ShoppingCartDaoImpl shoppingCartDao, DishDaoImpl dishDao) {
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
        this.shoppingCartDao = shoppingCartDao;
        this.dishDao = dishDao;
    }

    @Override
    public Order createOrder(Long userId, String paymentMethod, String remark) {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("支付方式不能为空");
        }

        // 查询用户购物车
        List<ShoppingCart> cartItems = shoppingCartDao.findByUserId(userId);
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("购物车为空");
        }

        // 计算订单总金额并创建订单详情
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (ShoppingCart cartItem : cartItems) {
            Dish dish = dishDao.findById(cartItem.getDishId());
            if (dish == null) {
                throw new IllegalArgumentException("菜品不存在: " + cartItem.getDishName());
            }
            if (dish.getStatus() == null || dish.getStatus() != 1) {
                throw new IllegalArgumentException("菜品未上架: " + cartItem.getDishName());
            }
            if (dish.getStock() == null || dish.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("菜品库存不足: " + cartItem.getDishName());
            }

            // 计算小计
            BigDecimal itemAmount = dish.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            totalAmount = totalAmount.add(itemAmount);

            // 创建订单详情（临时，稍后设置orderId）
            OrderDetail detail = new OrderDetail();
            detail.setDishId(dish.getId());
            detail.setDishName(dish.getName());
            detail.setQuantity(cartItem.getQuantity());
            detail.setPrice(dish.getPrice());
            detail.setAmount(itemAmount);
            orderDetails.add(detail);
        }

        // 创建订单
        String orderNo = CommonUtils.generateOrderNo();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setPaymentMethod(paymentMethod);
        order.setRemark(remark);
        order.setStatus(OrderStatus.PENDING_PAYMENT.getCode());

        int result = orderDao.insert(order);
        if (result <= 0) {
            throw new RuntimeException("创建订单失败");
        }

        // 保存订单详情
        for (OrderDetail detail : orderDetails) {
            detail.setOrderId(order.getId());
            orderDetailDao.insert(detail);

            // 扣减库存
            Dish dish = dishDao.findById(detail.getDishId());
            int newStock = dish.getStock() - detail.getQuantity();
            dishDao.updateStock(dish.getId(), newStock);
        }

        // 清空购物车
        shoppingCartDao.deleteByUserId(userId);

        return order;
    }

    @Override
    public boolean payOrder(Long orderId) {
        if (orderId == null) {
            return false;
        }

        Order order = orderDao.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("订单不存在");
        }

        // 只有待支付状态的订单才能支付
        if (!OrderStatus.PENDING_PAYMENT.getCode().equals(order.getStatus())) {
            throw new IllegalArgumentException("订单状态不正确，无法支付");
        }

        return orderDao.updateStatus(orderId, OrderStatus.PAID.getCode()) > 0;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        if (orderId == null) {
            return false;
        }

        Order order = orderDao.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("订单不存在");
        }

        // 只有待支付和已支付状态的订单才能取消
        if (!OrderStatus.PENDING_PAYMENT.getCode().equals(order.getStatus())
                && !OrderStatus.PAID.getCode().equals(order.getStatus())) {
            throw new IllegalArgumentException("订单状态不正确，无法取消");
        }

        // 恢复库存
        List<OrderDetail> details = orderDetailDao.findByOrderId(orderId);
        for (OrderDetail detail : details) {
            Dish dish = dishDao.findById(detail.getDishId());
            if (dish != null) {
                int newStock = dish.getStock() + detail.getQuantity();
                dishDao.updateStock(dish.getId(), newStock);
            }
        }

        return orderDao.updateStatus(orderId, OrderStatus.CANCELLED.getCode()) > 0;
    }

    @Override
    public boolean completeOrder(Long orderId) {
        if (orderId == null) {
            return false;
        }

        Order order = orderDao.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("订单不存在");
        }

        // 只有制作中状态的订单才能完成
        if (!OrderStatus.PREPARING.getCode().equals(order.getStatus())) {
            throw new IllegalArgumentException("订单状态不正确，无法完成");
        }

        return orderDao.updateStatus(orderId, OrderStatus.COMPLETED.getCode()) > 0;
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer status) {
        if (orderId == null || status == null) {
            return false;
        }

        Order order = orderDao.findById(orderId);
        if (order == null) {
            return false;
        }

        order.setStatus(status);
        order.setUpdateTime(new Date());
        return orderDao.update(order) > 0;
    }

    @Override
    public Order getOrderById(Long id) {
        if (id == null) {
            return null;
        }
        return orderDao.findById(id);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        if (orderNo == null || orderNo.trim().isEmpty()) {
            return null;
        }
        return orderDao.findByOrderNo(orderNo);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        if (userId == null) {
            return List.of();
        }
        return orderDao.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> getOrdersByStatus(Integer status) {
        if (status == null) {
            return List.of();
        }
        return orderDao.findByStatus(status);
    }
}
