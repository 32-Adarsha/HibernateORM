package org.example.javaHibernat;

import com.code.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory =  new Configuration().configure("hibernate.cfg.xml")
               .addAnnotatedClass(Category.class)
               .addAnnotatedClass(OrderDetails.class)
               .addAnnotatedClass(Orders.class)
               .addAnnotatedClass(Product.class)
               .addAnnotatedClass(Users.class)
       .buildSessionFactory();

        // please perform the operation sequentially

        //new CreateData(sessionFactory);
        //new GetData(sessionFactory);
        //new UpdateData(sessionFactory);
        //new DeleteData(sessionFactory);

    }
}
