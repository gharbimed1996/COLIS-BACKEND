package com.example.gauth.service;

import com.example.gauth.config.KeycklockConfig;

import com.example.gauth.entity.EntrepriseDTO;
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

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
@Service
public class EntrepriseService {

    @Autowired
    KeycklockConfig keycklockConfig;




    public EntrepriseService(KeycklockConfig keycklockConfig) {
        this.keycklockConfig = keycklockConfig;
    }

    public String addentrOrg(EntrepriseDTO entrepriseDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();
        CredentialRepresentation pass = new CredentialRepresentation();
        pass.setType(CredentialRepresentation.PASSWORD);
        pass.setValue(entrepriseDTO.getPassword());
        pass.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(entrepriseDTO.getUsername());
        user.setEmail(entrepriseDTO.getEmail());
        user.setEnabled(true);
        user.setCredentials(Arrays.asList(pass));
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());
        String orgId = entrepriseDTO.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        String roleName = "Entreprise";
        RoleRepresentation roleid = keycloak.realm("Auth").roles().get(roleName).toRepresentation();
        List<RoleRepresentation> roles = Arrays.asList(roleid);
        keycloak.realm("Auth").users().get(userId).roles().realmLevel().add(roles);
        return userId;
    }

    public String updateEntreprise(String userId, EntrepriseDTO updatedEntrepriseDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();

        // Fetch the user by userId
        UserResource userResource = usersResource.get(userId);

        if (userResource == null) {
            // User does not exist, create a new user
            CredentialRepresentation pass = new CredentialRepresentation();
            pass.setType(CredentialRepresentation.PASSWORD);
            pass.setValue(updatedEntrepriseDTO.getPassword());
            pass.setTemporary(false);

            UserRepresentation newUser = new UserRepresentation();
            newUser.setUsername(updatedEntrepriseDTO.getUsername());
            newUser.setEmail(updatedEntrepriseDTO.getEmail());
            newUser.setEnabled(updatedEntrepriseDTO.isEnabled());
            newUser.setCredentials(Arrays.asList(pass));

            Response response = usersResource.create(newUser);
            return CreatedResponseUtil.getCreatedId(response);
        } else {
            // User exists, update the user
            UserRepresentation existingUser = userResource.toRepresentation();

            // Update the user's information
            existingUser.setUsername(updatedEntrepriseDTO.getUsername());
            existingUser.setEmail(updatedEntrepriseDTO.getEmail());
            existingUser.setEnabled(updatedEntrepriseDTO.isEnabled());

            // Update the user's credentials if needed
            if (updatedEntrepriseDTO.getPassword() != null) {
                CredentialRepresentation updatedCredentials = new CredentialRepresentation();
                updatedCredentials.setType(CredentialRepresentation.PASSWORD);
                updatedCredentials.setValue(updatedEntrepriseDTO.getPassword());
                updatedCredentials.setTemporary(false);
                existingUser.setCredentials(Arrays.asList(updatedCredentials));
            }

            // Attempt to update the user
            userResource.update(existingUser);

            // Check if the user update was successful by fetching the user again
            UserRepresentation updatedUser = usersResource.get(userId).toRepresentation();

            if (!updatedUser.getUsername().equals(existingUser.getUsername()) ||
                    !updatedUser.getEmail().equals(existingUser.getEmail()) ||
                    updatedUser.isEnabled() != existingUser.isEnabled()) {
                // User update failed
                throw new RuntimeException("User update failed");
            }

            return userId;
        }
    }

    public void deleteEntreprise(String id) {
        UserResource userResource = keycklockConfig.getInstance().realm(keycklockConfig.getREALM()).users().get(id);
        userResource.remove();
    }




}
