package com.vladcarcu.up.to.date.common.mappers;

import com.vladcarcu.up.to.date.common.model.Popularity;
import com.vladcarcu.up.to.date.doctor.Doctor;
import com.vladcarcu.up.to.date.doctor.DoctorDTO;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO toDTO(Doctor doctor);

    Doctor toDoctor(DoctorDTO doctorDTO);

    List<DoctorDTO> toDTOList(List<Doctor> doctors);

    // TODO: TOP 2
    default String toPopularityString(Popularity popularity) {
        switch (popularity) {
            case UNKNOWN:
                return "Newcomer";
            case REGULAR:
                return capitalize(popularity.toString());
            case STAR:
                return "All Star";
        }
        return Strings.EMPTY;
    }

    default Popularity toPopularity(String popularity) {
        switch (popularity) {
            case "Newcomer":
                return Popularity.UNKNOWN;
            case "Regular":
                return Popularity.REGULAR;
            case "All Star":
                return Popularity.STAR;
        }
        return null;
    }

    // TODO: TOP 7
    default String capitalize(String input) {
        String lowercase = input.toLowerCase();
        return new StringBuilder()
                .append(lowercase.substring(0, 1).toUpperCase())
                .append(lowercase.substring(1))
                .toString();
    }

}
