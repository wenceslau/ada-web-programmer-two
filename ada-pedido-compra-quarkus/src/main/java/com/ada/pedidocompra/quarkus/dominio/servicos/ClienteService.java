package com.ada.pedidocompra.quarkus.dominio.servicos;

import com.ada.pedidocompra.quarkus.infraestrutura.configuracao.exceptions.NegocioException;
//import com.ada.pedidocompra.infraestrutura.configuracao.seguranca.PasswordUtils;
import com.ada.pedidocompra.quarkus.infraestrutura.configuracao.seguranca.PasswordUtils;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreateClienteRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.UpdateClienteRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.ClienteCredential;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.ClienteResponse;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.IdResponse;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.UsuarioRepository;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.enums.TipoUsuarioEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    private final UsuarioRepository usuarioRepository;

    public ClienteService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public IdResponse create(CreateClienteRequest createClienteRequest) {

        var usuario = createClienteRequest.toUsuario(TipoUsuarioEnum.ADMIN);
        usuario.setSenha((usuario.getSenha()));
        usuario.setSenha(PasswordUtils.encode(usuario.getSenha()));

        usuarioRepository.persist(usuario);

        return new IdResponse(usuario.getId());
    }

    @Transactional
    public void deleteById(Long clienteId) {
        usuarioRepository.deleteById(clienteId);
    }

    @Transactional
    public void update(Long clienteId, UpdateClienteRequest updateClienteRequest) {

        var clienteDb = usuarioRepository.findByIdOptional(clienteId);
        if (clienteDb.isEmpty()) {
            throw new NegocioException("Cliente não encontrado!!!");
        }

        clienteDb.get().setNome(updateClienteRequest.nome());

        usuarioRepository.persist(clienteDb.get());
    }

    public List<ClienteResponse> findAll() {

        var clienteList = usuarioRepository.findAll();

        return clienteList
                .stream()
                .map(ClienteResponse::from)// Converte cada cliente para ClienteResponse
                .toList(); // Converte o Stream para uma lista

    }

    public ClienteResponse findById(Long id) {

        var clienteDb = usuarioRepository.findByIdOptional(id);
        if (clienteDb.isEmpty()) {
            throw new NegocioException("Cliente não encontrado!!!");
        }

        return ClienteResponse.from(clienteDb.get());
    }

    public ClienteCredential findByEmail(String email) {

        var clienteDb = usuarioRepository.findByEmail(email);
        if (clienteDb.isEmpty()) {
            throw new NegocioException("Cliente não encontrado!!!");
        }

        return ClienteCredential.from(clienteDb.get());
    }
}
