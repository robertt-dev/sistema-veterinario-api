package sistema.veterinario.api.veterinario.service;

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
    
    if (StringUtils.isBlank(tutorDTO.getCpf())) {
      throw new VeterinarioException("O cpf do tutor é obrigatorio e deve conter 11 digitos!");
    }

    if (tutorRepository.existsByCpf(tutorDTO.getCpf())) {
      throw new VeterinarioException("Este CPF já exite!");
    }

    if (StringUtils.isBlank(tutorDTO.getTelefone())) {
      throw new VeterinarioException("O telefone é Obrigatorio e deve conter no mínimo 11 digitos!");
    }

    if (StringUtils.isBlank(tutorDTO.getEmail())) {
      throw new VeterinarioException("Digite o Email!");
    }
    
  }

  public Page<TutorDTO> listarTutor(Pageable listar) {
    return tutorRepository
    .findAllBySituacaoEnum(SituacaoEnum.ATIV, listar)
    .map(TutorDTO::new);
  }
  
}
