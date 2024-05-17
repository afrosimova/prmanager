package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.Employee;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select u from EMPLOYEE u " +
            "join POSITION i on u.position = i " +
            "where i.positionId = :positionId"
            //employeeId=
    )
    List<Employee> findEmployee(@Param("positionId") long positionId);
}
