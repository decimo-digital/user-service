package it.decimo.user_service.repository;

import it.decimo.user_service.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    /**
     * Restituisce tutti i merchant in cui l'owner è l'utente specificato
     *
     * @param ownerId id dell'utente
     * @return lista di merchant
     */
    List<Merchant> findAllByOwner(int ownerId);
}
