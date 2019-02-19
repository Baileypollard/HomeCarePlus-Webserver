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

    @Query("INSERT INTO #{#n1ql.bucket} (KEY, VALUE) VALUES ($1, {'first_name':$2, 'last_name':$3, 'address':$4" +
            ", 'phone_number':$5, 'gender':$6, 'type':'client', 'client_id':$1, 'additional_information':$7})")
    Client createClient(String clientID, String firstName, String lastName, String address, String phoneNumber,
                        String gender, String additionalInfo);

    @Query("DELETE FROM #{#n1ql.bucket} AS b where b.type = 'client' AND b.client_id = $1 RETURNING META().id")
    void removeClient(String id);
}
