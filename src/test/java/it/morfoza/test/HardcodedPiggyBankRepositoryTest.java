package it.morfoza.test;

import it.morfoza.HardcodedPiggyRepository;
import it.morfoza.PiggyBank;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HardcodedPiggyBankRepositoryTest {

    @Test
    public void shouldDisplayMatchingEvents() {

        // Given
        HardcodedPiggyRepository sut = new HardcodedPiggyRepository();

        // When
        List<PiggyBank> piggyBanks = sut.getByCity("jazz");

        // Then
        assertThat(piggyBanks).hasSize(1);
        PiggyBank piggyBank1 = piggyBanks.get(0);
        assertThat(piggyBank1.getName()).isEqualTo("Jazz");
    }

    @Test
    public void shouldReturnEmptyList() {
        // Given
        HardcodedPiggyRepository sut = new HardcodedPiggyRepository();

        // When
        List<PiggyBank> result = sut.getByCity("tralalala");

        // Then
        assertThat(result).hasSize(0);
    }

    @Test
    public void shouldDisplayTwoMatchingEvents() {

        // Given
        HardcodedPiggyRepository sut = new HardcodedPiggyRepository();

        // When
        List<PiggyBank> piggyBanks = sut.getByCity("salsa");

        // Then
        assertThat(piggyBanks).hasSize(2);
    }

}
