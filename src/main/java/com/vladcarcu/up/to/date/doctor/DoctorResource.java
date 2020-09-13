package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.mappers.DoctorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorResource {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toDoctor(doctorDTO);
        return doctorMapper.toDTO(doctorService.createDoctor(doctor));
    }

    @GetMapping("/performers")
    public List<DoctorDTO> getTopAndBottomPerformers() {
        return doctorMapper.toDTOList(doctorService.findTopAndBottomPerformers());
    }

}
