package com.example.asap_log_deasafio;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Cliente;
import com.example.asap_log_deasafio.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(value = "Clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Consulta todas os clientes.")
    @GetMapping("")
    public ResponseEntity<List<Cliente>> getAll() throws Exception {
        List<Cliente> items = clienteService.consultarClientes();
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @ApiOperation(value = "Consulta um cliente pelo Id.")
    @GetMapping("{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") String id) throws Exception {
        Optional<Cliente> existingItemOptional = clienteService.consultarClienteId(id);
        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Insere um cliente novo.")
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente item) throws Exception {
        Cliente savedItem = clienteService.salvarCliente(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um cliente existente.")
    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) throws Exception {
        Cliente savedItem = this.clienteService.atualizarCliente(cliente);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove um cliente.")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) throws Exception {
        clienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
