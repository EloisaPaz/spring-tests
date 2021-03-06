package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(repository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        verify(repository).findAll();

        assertThat(foundVisits).hasSize(1);
    }

    @Test
    void findById() {
        Visit visit = new Visit();

        when(repository.findById(1L)).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        assertThat(foundVisit).isNotNull();

        verify(repository).findById(1L);
        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit();

        when(repository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = service.save(new Visit());

        assertThat(savedVisit).isNotNull();
        verify(repository, times(1)).save(any(Visit.class));

    }

    @Test
    void delete() {

        service.delete(new Visit());

        verify(repository, times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(repository, atLeastOnce()).deleteById(1L);
        verify(repository).deleteById(anyLong());
    }
}