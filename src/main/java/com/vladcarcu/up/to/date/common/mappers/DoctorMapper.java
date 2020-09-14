package com.vladcarcu.up.to.date.common.mappers;

import com.vladcarcu.up.to.date.common.model.Popularity;
import com.vladcarcu.up.to.date.doctor.Doctor;
import com.vladcarcu.up.to.date.doctor.DoctorDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO toDTO(Doctor doctor);

    Doctor toDoctor(DoctorDTO doctorDTO);

    List<DoctorDTO> toDTOList(List<Doctor> doctors);

    // TODO: TOP 2
    default String toPopularityString(Popularity popularity) {
        return switch (popularity) {
            case UNKNOWN -> "Newcomer";
            case REGULAR -> capitalize(popularity.toString());
            case STAR -> "All Star";
        };
    }

    default Popularity toPopularity(String popularity) {
        return switch (popularity) {
            case "Newcomer":
                yield Popularity.UNKNOWN;
            case "Regular":
                yield Popularity.REGULAR;
            case "All Star":
                yield Popularity.STAR;
            default:
                yield null;
        };
    }

    // TODO: TOP 7
    private String capitalize(String input) {
        String lowercase = input.toLowerCase();
        return new StringBuilder()
                .append(lowercase.substring(0, 1).toUpperCase())
                .append(lowercase.substring(1))
                .toString();
    }

}
