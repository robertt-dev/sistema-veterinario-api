package sistema.veterinario.api.veterinario.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import sistema.veterinario.api.veterinario.exception.VeterinarioException;
import sistema.veterinario.api.veterinario.model.dto.TutorDTO;
import sistema.veterinario.api.veterinario.model.entity.Tutor;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;
import sistema.veterinario.api.veterinario.model.repository.TutorRepository;

@Service
@Transactional
public class TutorService {

  @Autowired
  private TutorRepository tutorRepository;

  public void cadastrarTutor(@Valid TutorDTO tutorDTO) {
    this.verificacaoCadastroTutor(tutorDTO);

    tutorDTO.setSituacaoEnum(SituacaoEnum.ATIV);
    
    tutorRepository.save(new Tutor(tutorDTO));
  }

  private void verificacaoCadastroTutor(TutorDTO tutorDTO) {
    if (StringUtils.isBlank(tutorDTO.getNome())) {
      throw new VeterinarioException("O nome do tutor é obrigatorio!");
    }
    
    if (!tutorDTO.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
      throw new VeterinarioException("O cpf do tutor é obrigatorio e deve conter 11 digitos!");
    }

    if (tutorRepository.existsByCpf(tutorDTO.getCpf())) {
      throw new VeterinarioException("Este CPF já exite!");
    }

    if (StringUtils.isBlank(tutorDTO.getTelefone())) {
      throw new VeterinarioException("O telefone é Obrigatorio!");
    }

    if (!tutorDTO.getTelefone().matches("\\d{11}")) {
      throw new VeterinarioException("Numero de telefone invalido!");
    }

    if (tutorRepository.existsByTelefone(tutorDTO.getTelefone())) {
      throw new VeterinarioException("Este Telefone já exite!");
    }

    if (StringUtils.isBlank(tutorDTO.getEmail())) {
      throw new VeterinarioException("O Email é obrigatorio!");
    }

    if (tutorRepository.existsByEmail(tutorDTO.getEmail())) {
      throw new VeterinarioException("Este Email já existe!");
    }
    
  }

  public Page<TutorDTO> listarTutor(Pageable listar) {
    return tutorRepository
    .findAllBySituacaoEnum(SituacaoEnum.ATIV, listar)
    .map(TutorDTO::new);
  }

  public void atualizarTutor(Long id, TutorDTO tutorDTO) {
    if (tutorRepository.existsByCpfAndIdNot(tutorDTO.getCpf(), id)) {
      throw new VeterinarioException("Este CPF já existe!");
    }

    if (tutorRepository.existsByTelefoneAndIdNot(tutorDTO.getTelefone(), id)) {
      throw new VeterinarioException("Este telefone já existe!");
    }

    if (tutorRepository.existsByEmailAndIdNot(tutorDTO.getEmail(), id)) {
      throw new VeterinarioException("Este email já existe!");
    }

    Optional<Tutor> tutorOptionalId = tutorRepository.findById(id);

    if (tutorOptionalId.isPresent()) {
      Tutor tutor = tutorOptionalId.get();
      tutor.setNome(tutorDTO.getNome());
      tutor.setCpf(tutorDTO.getCpf());
      tutor.setTelefone(tutorDTO.getTelefone());
      tutor.setEmail(tutorDTO.getEmail());
    }
  }

  public void deletarTutor(Long id) {
    Optional<Tutor> tutorOptionalId = tutorRepository.findById(id);

    if (tutorOptionalId.isPresent()) {
      Tutor tutor = tutorOptionalId.get();
      tutor.setSituacaoEnum(SituacaoEnum.INAT);
      tutorRepository.save(tutor);
    } else {
      throw new VeterinarioException("Este tutor não existe!");
    }
  }
  
}
