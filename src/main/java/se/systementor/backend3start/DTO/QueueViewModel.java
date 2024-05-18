package se.systementor.backend3start.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class QueueViewModel {
    private UUID id;
    private String name;
    private String roomIdCSV;
    private int messagesToSend;
}
