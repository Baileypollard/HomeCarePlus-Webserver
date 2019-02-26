package com.techprimers.security.securitydbexample.repository;

import com.couchbase.client.java.document.json.JsonObject;
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

    @Query("INSERT INTO #{#n1ql.bucket} (KEY, VALUE) VALUES ($1, $2)")
    Employee createNewEmployee(String id, JsonObject document);

    @Query("DELETE FROM #{#n1ql.bucket} where type = 'employee' AND employee_id = $1 RETURNING META().id")
    void removeEmployeeById(String id);

    @Query("UPDATE #{#n1ql.bucket} USE KEYS $1 SET first_name=$2, last_name=$3, address=$4, " +
            "gender=$5, phone_number=$6 RETURNING META().id")
    void save(String KEY, String firstName, String lastName, String address, String gender,
              String phoneNumber);
}
