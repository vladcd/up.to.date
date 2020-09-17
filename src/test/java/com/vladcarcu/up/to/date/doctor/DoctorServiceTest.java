package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.model.Popularity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        Doctor topPerformer = mock(Doctor.class);
        when(topPerformer.getPopularity()).thenReturn(Popularity.STAR);
        Doctor regularPerformer = mock(Doctor.class);
        Doctor bottomPerformer = mock(Doctor.class);
        when(bottomPerformer.getPopularity()).thenReturn(Popularity.UNKNOWN);

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(topPerformer, regularPerformer, bottomPerformer));

        List<Doctor> doctors = doctorService.findTopAndBottomPerformers();
        assertEquals(2, doctors.size());
    }
}
