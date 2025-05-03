package com.example.client.controller;

import com.example.client.dto.ClientDTO;
import com.example.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "test on customer", description = "documentation APIs on customer ")
public class ClientController {

    private  final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    @Operation(summary = "create client", description = "save client in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client saved successful"),
            @ApiResponse(responseCode = "404", description = "client already exist"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.createClient(dto));
    }

    @GetMapping()
    @Operation(summary = "get client", description = "get all clients in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " get all clients successful"),
            @ApiResponse(responseCode = "404", description = "client not found"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<List<ClientDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.getAllClient());
    }

    @PutMapping("/update/{clientId}")
    @Operation(summary = "update client", description = "update client wo exist in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client updated successful"),
            @ApiResponse(responseCode = "404", description = "client already exist"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<ClientDTO> update(@PathVariable int clientId, @RequestBody ClientDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.clientService.updateClient(clientId,dto));
    }

    @DeleteMapping("/delete/{clientId}")
    @Operation(summary = "delete client", description = " delete client who exist in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client deleted successful"),
            @ApiResponse(responseCode = "404", description = "client already exist"),
            @ApiResponse(responseCode = "500", description = " error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int clientId){
        boolean exist = this.clientService.deleteClient(clientId);
        if (exist){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
