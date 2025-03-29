package org.example.javaHibernat;

import com.code.entity.OrderDetails;
import com.code.entity.Orders;
import com.code.entity.Users;
import jakarta.persistence.criteria.From;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GetData {
    SessionFactory sessionFactory;
    public GetData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.getUserWithId(3);
        this.getOrderDetails();
    }


    private void getUserWithId(int id){
        Session currentsession = sessionFactory.openSession();
        currentsession.beginTransaction();
        Users user = currentsession.get(Users.class, id);
        System.out.println(user.toString());
        currentsession.getTransaction().commit();
        currentsession.close();
    }


    private void getOrderDetails(){
        Session currentsession = sessionFactory.openSession();
        currentsession.beginTransaction();

        // query that will select the order and fetch the user associated with that order
        // it will also fetch the orderDetails (as order can have multiple items)
        // will iterate over the orderDetails to find the each product.
        String hql = "select o from Orders o "
                    + "join fetch o.user u "
                    + "join fetch o.orderDetails od "
                    + "join fetch od.product p";
        List<Orders> orders = currentsession.createQuery(hql, Orders.class).list();
        currentsession.getTransaction().commit();


        for (Orders order : orders) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println("User: " + order.getUser().getUsername());


            for (OrderDetails orderDetail : order.getOrderDetails()) {
                System.out.println("  Product: " + orderDetail.getProduct().getName());
                System.out.println("  Quantity: " + orderDetail.getQuantity());
                System.out.println("  Price: " + orderDetail.getUnitPrice());
            }
        }

        currentsession.close();

    }


}
