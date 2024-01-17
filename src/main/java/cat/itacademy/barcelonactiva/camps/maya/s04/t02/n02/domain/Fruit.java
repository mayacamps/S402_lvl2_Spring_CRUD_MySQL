package cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "fruits", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Fruit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "cannot be null.")
    private String name;
    @NotNull(message = "cannot be null.")
    @Min(value = 0, message =  "cannot be negative.")
    private Integer quantKilos;

    public Fruit(){}
    public Fruit(String name, int quantKilos) {
        this.name = name;
        this.quantKilos = quantKilos;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantKilos() {
        return quantKilos;
    }

    public void setQuantKilos(int quantKilos) {
        this.quantKilos = quantKilos;
    }

    @Override
    public String toString(){
        return "Fruit:\n\t[id=" + id + ", \n\tname=" + name + ", \n\tquantity(kg)=" + quantKilos + "]\n";
    }
}
