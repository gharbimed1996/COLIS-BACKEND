package com.example.gauth.controller;

import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.ClientDTO;
import com.example.gauth.service.AdminService;
import com.example.gauth.service.ClientService;
import io.phasetwo.client.openapi.model.UserRepresentation;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.RoleRepresentation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")

public class ClientControlleur {

    @Autowired
    private ClientService clientService;
    @Autowired
    private KeycklockConfig config;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ClientDTO.class);


        @PostMapping("/create")
        public String createclient(@RequestBody ClientDTO clientDTO) {

            return clientService.createKeycloakUser(clientDTO);
        }
        @PostMapping("/login")

        public ResponseEntity<AccessTokenResponse> login( @RequestBody ClientDTO loginRequest) {
            Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentialsClient(loginRequest).build();

            AccessTokenResponse accessTokenResponse = null;
            try {
                accessTokenResponse = keycloak.tokenManager().getAccessToken();
                return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
            } catch (BadRequestException ex) {
                LOG.warn("invalid account.", ex);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
            }

        }

    @GetMapping("/user/{id}/roles")
    public ResponseEntity<List<RoleRepresentation>> getUserRoles(@PathVariable String userId, @RequestParam String id) {
        List<RoleRepresentation> userRoles = clientService.getUserRoles(userId, id);
        if (userRoles != null) {
            return ResponseEntity.ok(userRoles);
        } else {
            // Handle the error or return an error response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable String userId) {
        clientService.deleteClient(userId);
        return ResponseEntity.ok("Client user deleted successfully");
    }


    }



