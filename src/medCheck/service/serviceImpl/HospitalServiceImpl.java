package medCheck.service.serviceImpl;

import medCheck.dataBase.DataBase;
import medCheck.model.Hospital;
import medCheck.model.Patient;
import medCheck.service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private final DataBase dataBase = new DataBase();

    public DataBase getDataBase() {
        return dataBase;
    }

    @Override
    public String addHospital(Hospital hospital) {
        dataBase.getHospitals().add(hospital);
        return "Успешно добавлен";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            for (Hospital hospital : dataBase.getHospitals()) {
                if (hospital.getId().equals(id)) {
                    return hospital;
                } else {
                    throw new RuntimeException("Больница не найдено");
                }
            }
        } catch (RuntimeException r) {
            System.out.println(r.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return dataBase.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospital = findHospitalById(id);
        return hospital.getPatients();
    }

    @Override
    public String deleteHospitalById(Long id) {
        dataBase.getHospitals().remove(findHospitalById(id));
        return "Успешно удалено";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospital = new HashMap<>();
        for (Hospital hospital1 : dataBase.getHospitals()) {
            if (hospital1.getAddress().equalsIgnoreCase(address)) {
                hospital.put(hospital1.getAddress(), hospital1);
            }
        }
        return hospital;
    }
}
