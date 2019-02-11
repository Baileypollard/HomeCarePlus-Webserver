package views;

import com.techprimers.security.securitydbexample.repository.AppointmentRepository;
import com.techprimers.security.securitydbexample.repository.ClientRepository;
import com.techprimers.security.securitydbexample.repository.EmployeeRepository;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateAppointmentWindow extends Window
{
    public CreateAppointmentWindow(EmployeeRepository employeeRepository, ClientRepository clientRepository, AppointmentRepository appointmentRepository)
    {
        super("New Appointment");
        VerticalLayout layout = new CreateAppointmentView(employeeRepository, clientRepository, appointmentRepository);
        layout.setSizeFull();
        setContent(layout);
        getContent().setSizeUndefined();
        center();
    }
}
