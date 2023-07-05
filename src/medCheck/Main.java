package medCheck;

import medCheck.service.serviceImpl.DepartmentServiceImpl;
import medCheck.service.serviceImpl.DoctorServiceImpl;
import medCheck.service.serviceImpl.HospitalServiceImpl;
import medCheck.service.serviceImpl.PatientServiceImpl;

public class Main {
    public static void main(String[] args) {
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(hospitalService);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(hospitalService);
        PatientServiceImpl patientService = new PatientServiceImpl(hospitalService);


    }
}
