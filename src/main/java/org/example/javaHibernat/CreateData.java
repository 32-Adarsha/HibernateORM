package org.example.javaHibernat;

import com.code.entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

public class CreateData {
    SessionFactory sessionFactory;
    public CreateData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

//        this.setUsers();
//        this.setCategoryAndProducts();
//        this.setOrderDetail();


        System.out.println("User Created Successfully");
    }


    private void setCategoryAndProducts() {
        //Category
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        Category category = new Category("Sports" , "Sports Item" , Collections.emptyList());
        currentSession.persist(category);
        //product
        BigDecimal totalAmount = new BigDecimal("100.0");
        Product product = new Product("Football" , totalAmount , 50 , category);
        currentSession.persist(product);

        currentSession.getTransaction().commit();
        currentSession.close();

    }
    private void setUsers(){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();

        //user1
        Users user = new Users("adarsha@gmail.com", "Adarsha" , getPassword("password") , Users.Role.ADMIN , Collections.emptyList());
        currentSession.persist(user);

        //user 2
        user = new Users("khadka@gmail.com", "Khadka" , getPassword("password2") , Users.Role.CUSTOMER , Collections.emptyList());
        currentSession.persist(user);

        currentSession.getTransaction().commit();

        currentSession.close();

    }
    private void setOrderDetail(){
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();
        // Getting User with id 1
        Users users = currentSession.get(Users.class, 3);


        // Getting Product wi id 3
        Product product = currentSession.get(Product.class, 3);

        // Order TotalAmount
        BigDecimal totalAmount = new BigDecimal("100.0");

        // Today Timestamp
        Date  currenDate = new Date();
        Timestamp orderDate = new Timestamp(currenDate.getTime());

        // Orders
        Orders orders = new Orders(orderDate ,totalAmount , users );
        currentSession.persist(orders);

        //OrderDetails
        OrderDetails orderDetails = new OrderDetails(5 , totalAmount , orders , product );
        currentSession.persist(orderDetails);
        OrderDetails orderDetails2 = new OrderDetails(10 , totalAmount , orders , product );
        currentSession.persist(orderDetails2);



        currentSession.getTransaction().commit();
        currentSession.close();
    }
    private String getPassword(String password) {
        // some hashing algorithm to hashing password
        return password;
    }
}
