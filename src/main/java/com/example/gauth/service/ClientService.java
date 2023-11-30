package com.example.gauth.service;

import com.example.gauth.config.KeycklockConfig;
import com.example.gauth.entity.ClientDTO;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static com.example.gauth.config.KeycklockConfig.keycloak;

@Service
public class ClientService {
    @Autowired
    KeycklockConfig keycklockConfig;

    private Keycloak keycloak;




    public ClientService(KeycklockConfig keycklockConfig) {
        this.keycklockConfig = keycklockConfig;
    }

    @PostConstruct
    public void init() {
        this.keycloak = keycklockConfig.getInstance();
    }

    public String createKeycloakUser(ClientDTO user) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();
        CredentialRepresentation pass = new CredentialRepresentation();
        pass.setType(CredentialRepresentation.PASSWORD);
        pass.setValue(user.getPassword());
        pass.setTemporary(false);
        UserRepresentation client = new UserRepresentation();
        client.setUsername(user.getUsername());
        client.setEmail(user.getEmail());
        client.setEnabled(true);
        client.setCredentials(Arrays.asList(pass));
        Response response = usersResource.create(client);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());
        String orgId = user.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        String roleName = "Client";
        RoleRepresentation roleid = keycloak.realm("Auth").roles().get(roleName).toRepresentation();
        List<RoleRepresentation> roles = Arrays.asList(roleid);
        keycloak.realm("Auth").users().get(userId).roles().realmLevel().add(roles);
        return userId;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public void deleteClient(String id) {
        UserResource userResource = keycklockConfig.getInstance().realm(keycklockConfig.getREALM()).users().get(id);
        userResource.remove();
    }


    public List<RoleRepresentation> getUserRoles(String userId, String id) {
        UserResource userResource = keycloak.realm("Auth").users().get(userId);
        Response response = (Response) userResource.roles().clientLevel(id).listAll();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<RoleRepresentation>>() {});
        } else {
            // Handle the error or throw an exception
            return null;
        }
    }

}

