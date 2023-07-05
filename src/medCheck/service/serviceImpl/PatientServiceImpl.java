package medCheck.service.serviceImpl;

import medCheck.model.Hospital;
import medCheck.model.Patient;
import medCheck.service.GenericService;
import medCheck.service.PatientService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {
    private final HospitalServiceImpl hospitalService;

    public PatientServiceImpl(HospitalServiceImpl hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        hospital.getPatients().add(patient);
        return "Успешно добавлено";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital: hospitalService.getAllHospital()){
            hospital.getPatients().remove(getPatientById(id));
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        Patient patients = getPatientById(id);
        patients.setFirstName(patient.getFirstName());
        patients.setLastName(patient.getLastName());
        patients.setAge(patient.getAge());
        patients.setGender(patient.getGender());
        return "Успешно изменено";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        Hospital hospital = hospitalService.findHospitalById(id);
        hospital.getPatients().addAll(patients);
        return "Успешно добавлен";
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital: hospitalService.getAllHospital()){
//            for (Patient patient: hospital.getPatients()){
//                if (patient.getId().equals(id)){
//                    return patient;
//                }
//            }
            Optional<Patient> patient = hospital.getPatients().stream()
                    .filter(x -> x.getId().equals(id)).findFirst();
            return patient.orElse(null);
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {

        return null;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        for (Hospital hospital: hospitalService.getAllHospital()){
            if (ascOrDesc.equals("1")){
              return hospital.getPatients().stream().sorted(Comparator.comparing(Patient::getAge)).toList();
            } else if (ascOrDesc.equals("2")) {
                return hospital.getPatients().stream().sorted((o1, o2) -> o2.getAge()- o1.getAge()).toList();
            }
        }
        return null;
    }
}
