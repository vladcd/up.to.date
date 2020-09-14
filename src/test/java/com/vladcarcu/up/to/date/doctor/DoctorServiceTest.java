package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.model.Popularity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    // TODO: TOP 10
    // TODO: TOP 9
    @Test
    public void findTopAndBottomPerformers_whenOk_thenVerifyCall() {
        Doctor topPerformer = mockDoctor(Popularity.STAR);
        Doctor regularPerformer = mockDoctor(Popularity.REGULAR);
        Doctor bottomPerformer = mockDoctor(Popularity.UNKNOWN);

        when(doctorRepository.findAll()).thenReturn(List.of(topPerformer, regularPerformer, bottomPerformer));

        List<Doctor> doctors = doctorService.findTopAndBottomPerformers();
        assertEquals(2, doctors.size());
    }

    private Doctor mockDoctor(Popularity popularity) {
        Doctor bottomPerformer = mock(Doctor.class);
        when(bottomPerformer.getPopularity()).thenReturn(popularity);
        return bottomPerformer;
    }
}
