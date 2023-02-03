package eu.ciechanowiec.movie2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
class MainTest {
    @Test
    void sampleTrueTest() {
        assertTrue(true);
    }
}
