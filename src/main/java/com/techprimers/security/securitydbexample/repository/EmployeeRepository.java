package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Employee;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "employee")
public interface EmployeeRepository extends CouchbasePagingAndSortingRepository<Employee, String>
{
    @Query("SELECT b.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b " +
            "WHERE b.type = 'employee'")
    List<Employee> findAll();


    @Query("INSERT INTO #{#n1ql.bucket} (KEY, VALUE) VALUES ($1, {'first_name':$2, 'last_name':$3, 'address':$4" +
            ", 'phone_number':$5, 'gender':$6, 'type':'employee', 'employee_id':$1})")
    Employee createNewEmployee(String id, String firstName, String lastName, String address, String phoneNumber,
                               String gender);

    @Query("DELETE FROM #{#n1ql.bucket} where type = 'employee' AND employee_id = $1 RETURNING META().id")
    void removeEmployeeById(String id);
}
