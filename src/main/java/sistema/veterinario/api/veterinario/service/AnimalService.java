package sistema.veterinario.api.veterinario.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import sistema.veterinario.api.veterinario.exception.VeterinarioException;
import sistema.veterinario.api.veterinario.model.dto.AnimalDTO;
import sistema.veterinario.api.veterinario.model.entity.Animal;
import sistema.veterinario.api.veterinario.model.entity.Cliente;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;
import sistema.veterinario.api.veterinario.model.repository.AnimalRepository;
import sistema.veterinario.api.veterinario.model.repository.ClienteRepository;

@Service
@Transactional
public class AnimalService {

  @Autowired
  private AnimalRepository animalRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  public Page<AnimalDTO> listarAnimal(Pageable lista) {
    return animalRepository
        .findAllBySituacaoEnum(SituacaoEnum.ATIV, lista)
        .map(AnimalDTO::new);
  }

  public void cadastroAnimal(@Valid AnimalDTO animalDTO) {

    Cliente cliente = clienteRepository.findById(animalDTO.getCliente().getId())
        .orElseThrow(() -> new VeterinarioException("Tutor informado não existe!"));

    animalDTO.setCliente(cliente);
    this.verificacaoCadastroAnimal(animalDTO);

    animalDTO.setSituacaoEnum(SituacaoEnum.ATIV);
    animalRepository.save(new Animal(animalDTO));
  }

  public void verificacaoCadastroAnimal(AnimalDTO animalDTO) {
    if (StringUtils.isBlank(animalDTO.getNome())) {
      throw new VeterinarioException("O nome do animal é obrigatorio!");
    }

    if (StringUtils.isBlank(animalDTO.getEspecie())) {
      throw new VeterinarioException("A especie do animal é obrigatorio!");
    }

    if (StringUtils.isBlank(animalDTO.getRaca())) {
      throw new VeterinarioException("A raça do animal é obrigatorio!");
    }

    if (StringUtils.isBlank(animalDTO.getCorPelo())) {
      throw new VeterinarioException("A cor do pelo do animal é obrigatorio!");
    }

    if (StringUtils.isBlank(animalDTO.getSexo())) {
      throw new VeterinarioException("O sexo do animal é obrigatorio!");
    }

    if (animalDTO.getTempAnimalEnum() == null) {
      throw new VeterinarioException("O temperamento do animal é obrigatorio!");
    }

    if (animalDTO.getDeOndeEnum() == null) {
      throw new VeterinarioException("Informe se o animal é interno ou externo!");
    }

    if (animalDTO.getCliente() == null) {
      throw new VeterinarioException("É obrigatorio informar o tutor!");
    }
  }

  public void deletaAnimal(Long id) {
    Optional<Animal> animalOptional = animalRepository.findById(id);

    if (animalOptional.isPresent()) {
      Animal animal = animalOptional.get();
      animal.setSituacaoEnum(SituacaoEnum.INAT);
      this.animalRepository.save(animal);
    } else {
      throw new VeterinarioException("Esse animal não existe!");
    }
  }

  public void atualizarAnimal(Long id, AnimalDTO animalDTO) {

    Optional<Animal> animalOptionalId = animalRepository.findById(id);

    if (animalOptionalId.isPresent()) {
      Animal animal = animalOptionalId.get();
      animal.setNome(animalDTO.getNome());
      animal.setEspecie(animalDTO.getEspecie());
      animal.setRaca(animalDTO.getRaca());
      animal.setCorPelo(animalDTO.getCorPelo());
      animal.setSexo(animalDTO.getSexo());
      animal.setIdadeAnimal(animalDTO.getIdadeAnimal());
      animal.setTempAnimalEnum(animalDTO.getTempAnimalEnum());
      animal.setDeOndeEnum(animalDTO.getDeOndeEnum());
      animal.setCliente(animalDTO.getCliente());
    }
  }

}
