package sistema.veterinario.api.veterinario.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sistema.veterinario.api.veterinario.model.dto.AnimalDTO;
import sistema.veterinario.api.veterinario.model.enums.DeOndeAnimalEnum;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;
import sistema.veterinario.api.veterinario.model.enums.TemperamentoAnimalEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private String corPelo;
    private String sexo;
    private Integer idadeAnimal;
    
    @Enumerated(EnumType.STRING)
    TemperamentoAnimalEnum tempAnimalEnum;

    @Enumerated(EnumType.STRING)
    DeOndeAnimalEnum deOndeEnum;

    @Enumerated(EnumType.STRING)
    SituacaoEnum situacaoEnum;

    @ManyToOne
    private Cliente cliente;

    public Animal(AnimalDTO animalDTO) {
        this.nome = animalDTO.getNome();
        this.especie = animalDTO.getEspecie();
        this.raca = animalDTO.getRaca();
        this.corPelo = animalDTO.getCorPelo();
        this.sexo = animalDTO.getSexo();
        this.idadeAnimal = animalDTO.getIdadeAnimal();
        this.tempAnimalEnum = animalDTO.getTempAnimalEnum();
        this.deOndeEnum = animalDTO.getDeOndeEnum();
        this.situacaoEnum = animalDTO.getSituacaoEnum();
        this.cliente = animalDTO.getCliente();
    }
}
