package notpatternslearning.multithread.blinov.tasks.port;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Container<T> {
    private T contents;
    private Port departure;
    private Port destination;
}
