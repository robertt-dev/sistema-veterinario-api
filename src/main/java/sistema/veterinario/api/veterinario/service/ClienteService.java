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
import sistema.veterinario.api.veterinario.model.dto.ClienteDTO;
import sistema.veterinario.api.veterinario.model.entity.Cliente;
import sistema.veterinario.api.veterinario.model.enums.SituacaoEnum;
import sistema.veterinario.api.veterinario.model.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public void cadastrarCliente(@Valid ClienteDTO clienteDTO) {
    this.verificacaoCadastroCliente(clienteDTO);

    clienteDTO.setSituacaoEnum(SituacaoEnum.ATIV);
    
    clienteRepository.save(new Cliente(clienteDTO));
  }

  private void verificacaoCadastroCliente(ClienteDTO clienteDTO) {
    if (StringUtils.isBlank(clienteDTO.getNome())) {
      throw new VeterinarioException("O nome do cliente é obrigatorio!");
    }
    
    if (!clienteDTO.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
      throw new VeterinarioException("O cpf do cliente é obrigatorio e deve conter 11 digitos!");
    }

    if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
      throw new VeterinarioException("Este CPF já exite!");
    }

    if (StringUtils.isBlank(clienteDTO.getTelefone())) {
      throw new VeterinarioException("O telefone é Obrigatorio!");
    }

    if (!clienteDTO.getTelefone().matches("\\d{11}")) {
      throw new VeterinarioException("Numero de telefone invalido!");
    }

    if (clienteRepository.existsByTelefone(clienteDTO.getTelefone())) {
      throw new VeterinarioException("Este Telefone já exite!");
    }

    if (StringUtils.isBlank(clienteDTO.getEmail())) {
      throw new VeterinarioException("O Email é obrigatorio!");
    }

    if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
      throw new VeterinarioException("Este Email já existe!");
    }
    
  }

  public Page<ClienteDTO> listarCliente(Pageable listar) {
    return clienteRepository
    .findAllBySituacaoEnum(SituacaoEnum.ATIV, listar)
    .map(ClienteDTO::new);
  }

  public void atualizarCliente(Long id, ClienteDTO clienteDTO) {
    if (clienteRepository.existsByCpfAndIdNot(clienteDTO.getCpf(), id)) {
      throw new VeterinarioException("Este CPF já existe!");
    }

    if (clienteRepository.existsByTelefoneAndIdNot(clienteDTO.getTelefone(), id)) {
      throw new VeterinarioException("Este telefone já existe!");
    }

    if (clienteRepository.existsByEmailAndIdNot(clienteDTO.getEmail(), id)) {
      throw new VeterinarioException("Este email já existe!");
    }

    Optional<Cliente> clienteOptionalId = clienteRepository.findById(id);

    if (clienteOptionalId.isPresent()) {
      Cliente cliente = clienteOptionalId.get();
      cliente.setNome(clienteDTO.getNome());
      cliente.setCpf(clienteDTO.getCpf());
      cliente.setTelefone(clienteDTO.getTelefone());
      cliente.setEmail(clienteDTO.getEmail());
    }
  }

  public void deletarCliente(Long id) {
    Optional<Cliente> clienteOptionalId = clienteRepository.findById(id);

    if (clienteOptionalId.isPresent()) {
      Cliente cliente = clienteOptionalId.get();
      cliente.setSituacaoEnum(SituacaoEnum.INAT);
      clienteRepository.save(cliente);
    } else {
      throw new VeterinarioException("Este cliente não existe!");
    }
  }
  
}
