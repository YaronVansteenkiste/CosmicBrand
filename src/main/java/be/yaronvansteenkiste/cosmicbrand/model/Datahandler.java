package be.yaronvansteenkiste.cosmicbrand.model;

import be.yaronvansteenkiste.cosmicbrand.repositories.DataRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;



@Entity
public class Datahandler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(length = 100000)
    private byte[] data;

    public Datahandler() {}

    public Datahandler(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
