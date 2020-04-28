package com.bailey.projects.homecareplus.repository;

import com.bailey.projects.homecareplus.model.AppointmentType;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

public interface AppointmentTypeRepository extends CouchbasePagingAndSortingRepository<AppointmentType, String>
{

    @Query("SELECT type.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b UNNEST b.list AS type " +
            "WHERE b.type = 'appointment_types'")
    List<AppointmentType> getAllAppointmentTypes();

}
