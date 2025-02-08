package com.venky.Hrms.repository;

import com.venky.Hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceDAO extends JpaRepository<Attendance,Long> {

    Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    @Query("SELECT a FROM Attendance a WHERE " +
            "a.date BETWEEN :fromDate AND :toDate " +
            "ORDER BY " +
            ":searchValue :orderBy")
    List<Attendance> findAllAttendanceDetails(@Param("fromDate") LocalDate fromDate,
                                              @Param("toDate") LocalDate toDate,
                                              @Param("searchValue") String searchValue,
                                              @Param("orderBy") String orderBy);

}
