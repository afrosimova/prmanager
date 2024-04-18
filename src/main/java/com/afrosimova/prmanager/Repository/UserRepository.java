package com.afrosimova.prmanager.Repository;

import com.afrosimova.prmanager.Entity.User;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select a from Agency a " +
//            "join Compania c on a.compania = c.id " +
//            "where lower(c.name) like lower(concat('%', :searchRecruiter, '%')) " +
//            "and lower(a.city) like lower(concat('%', :searchSpecialisation, '%'))")
//    List<Agency> search(@Param("searchRecruiter") String searchRecruiter, @Param("searchSpecialisation") String searchSpecialisation);

}