package com.techprimers.security.securitydbexample.repository;

import com.couchbase.client.java.document.json.JsonObject;
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

    @Query("INSERT INTO #{#n1ql.bucket} (KEY, VALUE) VALUES ($1, $2)")
    Client createClient(String key, JsonObject document);

    @Query("DELETE FROM #{#n1ql.bucket} AS b where b.type = 'client' AND b.client_id = $1 RETURNING META().id")
    void removeClient(String id);
}
