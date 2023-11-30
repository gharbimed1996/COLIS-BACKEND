package com.example.gauth.service;

import com.example.gauth.entity.DemandeEntreprise;
import com.example.gauth.repository.DemandeEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeEntrepriseService {
    @Autowired
    private DemandeEntrepriseRepository demandeEntrepriseRepository;

   
    public DemandeEntreprise ajout (DemandeEntreprise demandeEntreprise){

        demandeEntreprise.getEntrepriseName();
        demandeEntreprise.getDescription();
        demandeEntreprise.getEmailPro();
        demandeEntreprise.getMatricule();
        return demandeEntrepriseRepository.save(demandeEntreprise);
    }
    public List<DemandeEntreprise> findAll(){
        return demandeEntrepriseRepository.findAll();}

    public void deleteById(Long id) {
        // Delete a DemandeEntreprise by its ID
        demandeEntrepriseRepository.deleteById(id);
    }

    public DemandeEntreprise findById(Long id) {
        return demandeEntrepriseRepository.findById(id).orElse(null);
    }

}
