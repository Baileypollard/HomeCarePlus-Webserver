package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Employee;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "employee")
public interface EmployeeRepository extends CouchbasePagingAndSortingRepository<Employee, String>
{

}
