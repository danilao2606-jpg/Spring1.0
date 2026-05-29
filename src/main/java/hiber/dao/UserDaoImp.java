package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void getOwnerCar(String model, int series) {
      String hql = "FROM User user WHERE user.car.model = :model AND user.car.series = :series";
      List<User> userList = sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
      for (User user: userList) {
         System.out.println("Id : " + user.getId());
         System.out.println("First Name : " + user.getFirstName());
         System.out.println("Last Name : " + user.getLastName());
         System.out.println("Email : " + user.getEmail());
         System.out.println("Car Model : " + user.getCarModel());
         System.out.println("Car Series : " + user.getCarSeries());
         System.out.println();
      }
   }
}
