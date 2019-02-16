package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Client;
import com.techprimers.security.securitydbexample.model.Employee;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends CouchbasePagingAndSortingRepository<Client, String>
{
    @Query("SELECT b.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b " +
            "WHERE b.type = 'client'")
    List<Client> findAll();

    @Query("SELECT b.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b " +
            "WHERE b.type = 'client'")
    Client createClient(String id, String first_name, String last_name, String address, String gender,
                        String phoneNumber);
}
