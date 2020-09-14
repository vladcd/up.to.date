package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.model.Popularity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Optional<Doctor> findById(Integer id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        // no matter what comes from the request, if this method is called, it needs to create a new Doctor
        doctor.setId(null);
        return doctorRepository.save(doctor);
    }

    public Popularity calculatePopularity(int numberOfAppointments, int numberOfSlots) {
        double ratio = numberOfSlots == 0 ? 0 : ((double) numberOfAppointments) / numberOfSlots;
        if (ratio == 0) {
            return Popularity.UNKNOWN;
        } else if (ratio < 0.5) {
            return Popularity.REGULAR;
        } else {
            return Popularity.STAR;
        }
    }

    public void updateAll(List<Doctor> doctors) {
        doctorRepository.saveAll(doctors);
    }

    // TODO: TOP 6
    public List<Doctor> findTopAndBottomPerformers() {
        return doctorRepository.findAll().stream()
                .collect(teeing(
                        filtering(Doctor::isStar, toList()),
                        filtering(Doctor::isUnknown, toList()),
                        (listOfPopular, listOfUnknown) -> {
                            listOfPopular.addAll(listOfUnknown);
                            return listOfPopular;
                        }
                ));
    }
}
