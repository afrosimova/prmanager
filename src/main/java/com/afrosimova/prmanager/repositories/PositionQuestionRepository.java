//package com.afrosimova.prmanager.repositories;
//
//import com.afrosimova.prmanager.entities.PositionQuestion;
//import com.afrosimova.prmanager.entities.User;
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface PositionQuestionRepository extends JpaRepository<PositionQuestion, Long> {
//
//    @Query("select u from POSITION_QUESTION u " +
//            "join Question i on i.questionId = u.positionQuestionId " +
//            "join EMPLOYEE e on u.positionQuestionId = e.position.positionId " +
//            "join EMPLOYEE_SURVEY es on e = es.employee " +
//
//            "where u.questionOrder = :questionOrder")
//    List<PositionQuestion> findPositionQuestionBy(@Param("questionOrder") int questionOrder);
//}