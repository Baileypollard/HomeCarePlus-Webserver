package com.techprimers.security.securitydbexample.repository;


import com.techprimers.security.securitydbexample.model.Appointment;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "appointment")
public interface AppointmentRepository extends CouchbasePagingAndSortingRepository<Appointment, String>
{
    @Query("SELECT b.*, appointment.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b UNNEST b.schedule AS appointment " +
            "WHERE b.type = 'appointment'")
    List<Appointment> findAll();

    @Query("SELECT b.*, appointment.*, META(b).id as _ID, META(b).cas as _CAS FROM #{#n1ql.bucket} AS b UNNEST b.schedule AS appointment " +
            "WHERE b.type = 'appointment' AND b.employee_id = $1")
    List<Appointment> findAllForEmployee(String employeeId);

    @Query("UPDATE #{#n1ql.bucket} SET schedule = ARRAY v FOR v IN schedule WHEN v.appointment_id != $1 END WHERE type='appointment' " +
            "AND ANY v IN schedule SATISFIES v.appointment_id = $1 END RETURNING META().id as docid")
    void removeAppointmentByAppointmentId(String appointmentId);

    @Query("MERGE INTO #{#n1ql.bucket} AS d USING [1] AS o ON KEY $1 " +
            "WHEN MATCHED THEN UPDATE SET d.schedule = ARRAY_APPEND(d.schedule, {'first_name':$4, 'last_name':$5, 'address':$6, " +
            "'start_time':$7, 'end_time':$8, 'appointment_id':$9, 'gender':$10, 'status':$11}) " +
            "WHEN NOT MATCHED THEN INSERT {'type':'appointment', 'employee_id':$2, 'date':$3, " +
            "'schedule': [ { 'appointment_id':$9, 'first_name':$4, 'last_name':$5, 'address':$6, 'gender':$10, 'status':$11, " +
            "'start_time':$7, 'end_time':$8, 'punched_in_time':'', 'punched_out_time':'' } ] }")
    Appointment createAppointment(String KEY, String employeeId, String date, String firstName, String lastName, String address,
                           String startTime, String endTime, String appointmentId, String gender, String status);
}