package medCheck.service.serviceImpl;

import medCheck.model.Department;
import medCheck.model.Hospital;
import medCheck.service.DepartmentService;
import medCheck.service.GenericService;

import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final HospitalServiceImpl hospitalService;

    public DepartmentServiceImpl(HospitalServiceImpl hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = hospitalService.findHospitalById(id);
        return hospital.getDepartments();
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Hospital hospital : hospitalService.getAllHospital()) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getDepartmentName().equalsIgnoreCase(name)) {
                        return department;
                    } else {
                        throw new RuntimeException("Не найдено");
                    }
                }
            }
        } catch (RuntimeException r) {
            System.out.println(r.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        hospital.getDepartments().add(department);
        return "Успешно добавлен";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : hospitalService.getAllHospital()) {
            hospital.getDepartments().removeIf(department -> department.getId().equals(id));
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        String str = "Не обновлено";
        for (Hospital hospital : hospitalService.getAllHospital()) {
            for (Department department1 : hospital.getDepartments()) {
                if (department1.getId().equals(id)) {
                    department1.setDepartmentName(department.getDepartmentName());
                    str = "Успешно изменено";
                }
            }
        }
        return str;
    }
}
