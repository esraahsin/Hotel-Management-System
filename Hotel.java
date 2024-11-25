import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 
public class Hotel {
    public String nom_Hotel ;
    public String  categorie ;
    public String heureOuverture ;
    public String heureFermeture ;
    public Adresse adresse ;
    private List<Chambre> chambres;
    public Hotel(String nom_Hotel,String categorie,String heureOuveture , String heureFermeture,Adresse adresse)
    {
        chambres= new ArrayList<Chambre>();
        this.nom_Hotel=nom_Hotel ;
        this.categorie=categorie ;
        this.heureFermeture=heureFermeture;
        this.heureOuverture=heureOuveture;
        this.adresse=adresse ;
    }
    public List<Chambre> getListChambres()
    {
        return chambres ;
    }
    public void setListChambres(List<Chambre> new_list)
    {
        chambres=new_list ;
    }
    public void ajouterChambre(Chambre ch)
    {
        if (chambres.size()==0 || chambres.indexOf(ch)!=-1)
        chambres.add(ch) ;
        else {
            System.out.println("La chambre "+ch+" existe déja dans l'hotel");
        }
    }
    public void supprimerChambre(Chambre ch)
    {
        if (chambres.indexOf(ch)!=-1)
        chambres.remove(ch);
        else{
            System.out.println("La chambre "+ch+" n'existe pas dans l'hotel");
        }

    }
    public void  effectuerReservation(Reservation reservation) throws ChambreNonDisponibleException,ReservationInexistante
    {
        if(chambreDisponible(reservation))
        {
            for (Chambre  ch: reservation.getChambresReservees())
            {
                ch.getReservations().add(reservation);
            }
            reservation.getResponsable().getReservationsClient().add(reservation);
            System.out.println("Reservation est effectue avec succees");
        }
        else { 
            //exception
            throw new ChambreNonDisponibleException("Désolé, aucune chambre disponible pour la réservation demandée:"+reservation.toString());
        }

    }

    
    public boolean chambreDisponible(Reservation reservation) {
    
        for (Chambre chambre : reservation.getChambresReservees()) {
            if (!chambreEstDisponible(chambre, reservation.getPeriode(), reservation.getDatedebut())) {
                return false;
            }
        }
    
        return true;
    }
    
    private boolean chambreEstDisponible(Chambre chambre, int periode, LocalDate dateDebut) {
        return chambre.estDisponible(periode, dateDebut);
    }
    
    
    
    
    public boolean existe(Categorie ca,List<Categorie> chc)
    {
        for (Categorie c : chc)
        {
            if (c.equals(ca))
                return true ;
            
        }return false ;
    }
    public boolean equal (Categorie categorie,List<Categorie> categories)
        {
            for (Categorie i : categories)
            {
                    if (i.equals(categorie))
                     return true ;
            }
            return false ;
        }
    public String toString()
    {
        return "Nom Hotel :"+nom_Hotel+"   Categorie :"+categorie+"  Temps de travail :"+heureOuverture+" - "+heureFermeture+"  Adresse :"+adresse;
    }
}
