package medCheck.service.serviceImpl;

import medCheck.model.Doctor;
import medCheck.model.Hospital;
import medCheck.service.DoctorService;
import medCheck.service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {

    private final HospitalServiceImpl hospitalService;

    public DoctorServiceImpl(HospitalServiceImpl hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital: hospitalService.getAllHospital()){
            for (Doctor doctor: hospital.getDoctors()){
                if (doctor.getId().equals(id)){
                    return  doctor;
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {

        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        Hospital hospital = hospitalService.findHospitalById(id);
        return hospital.getDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {

        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        hospital.getDoctors().add(doctor);
        return "Успешно добалено";
    }

    @Override
    public void removeById(Long id) {
      findDoctorById(id);
      for (Hospital hospital: hospitalService.getAllHospital()){
          hospital.getDoctors().remove(findDoctorById(id));
      }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        Doctor doctor1 = findDoctorById(id);
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setGender(doctor.getGender());
//        doctor1.setExperienceYear();
        return null;
    }
}
