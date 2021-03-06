/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.BusinessLayer.Repositories;


import App.DataLayer.Models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cesaineam
 */

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

    @Query("SELECT u FROM UserModel u WHERE u.userMail = :userMail")
    UserModel findByuserMail( String userMail );
}
