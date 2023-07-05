package medCheck.dataBase;


import medCheck.model.Hospital;


import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Hospital> hospitals = new ArrayList<>();
    public List<Hospital> getHospitals() {
        return hospitals;
    }
    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

}
