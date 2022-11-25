
package com.restapi.springbootrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.springbootrestapi.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	List<Employee> findByName(String name);

	// SELECT *FROM TABLE where name="anshu" and location="australiya"

	List<Employee> findByNameAndLocation(String name, String location);

	// SELECT *FROM TABLE WHERE name LIKE "%ram%"

	List<Employee> findByNameContaining(String keyword, Sort sort);

	// SELECT *from table_employee1 WHERE name="rock" OR location="UK";

	@Query("FROM Employee WHERE name = :name OR location = :location")
	List<Employee> getEmployeesByNameAndLocation(String name, String location);

	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name = :name")
	Integer deleteEmployeeByName(String name);

}
