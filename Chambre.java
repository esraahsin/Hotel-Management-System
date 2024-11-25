import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Chambre {
    private Categorie categorie ;
    private int num_etage ;
    private int num_chambre ;
    private int capacite ;
    private List<Consommation> consommations;
    private int nb_lits_personne;
    private int nb_lits_deux_personnes ;
    private List<Reservation>  reservations ;
    public Chambre(Categorie categorie,int num_etage,int num_chambre,int capacite,int nb_lits_personne,int nb_lits_deux_personnes)
    {
        this.categorie=categorie;
        this.num_etage=num_etage;
        this.num_chambre=num_chambre;
        this.capacite=capacite;
        this.consommations=new ArrayList<Consommation>();
        this.nb_lits_personne=nb_lits_personne;
        this.nb_lits_deux_personnes=nb_lits_deux_personnes;
        this.reservations=new ArrayList<Reservation>();
    }
    public Categorie getCategorie()
    {
        return categorie;
    }
    public void setCategorie(Categorie new_c)
    {
        categorie=new_c;
    }
    public int getNumEtage()
    {
        return num_etage;
    }
    public void setNumEtage(int etage)
    {
        num_etage=etage ;
    }
    public int getCapacite()
    {
        return capacite ;
    }
    public void setCapacite(int capacite)
    {
        this.capacite=capacite;
    }
    public List<Consommation> getListConsommations()
    {
        return consommations;
    }
    public void setListConsommations(List<Consommation> new_liste)
    {
        consommations=new_liste;
    }
    public int getNb_lits_personnne()
    {
        return nb_lits_personne;
    }
    public void setNblits_personnes(int nb)
    {
        nb_lits_personne=nb;
    }
    public int getNblits_deux_personnne()
    {
        return nb_lits_deux_personnes;
    }
    public void setNblits_deux_personnes(int nb)
    {
        nb_lits_deux_personnes=nb;
    }
    public List<Reservation> getReservations()
    {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations)
    {
        this.reservations=reservations;
    }
    public int getNumChambre()
    {
        return num_chambre;
    }
    public void setNumChambre(int n)
    {
        num_chambre=n;
    }
    public boolean estDisponible(int periode,LocalDate date_debut)
    {
        for (Reservation res : reservations)
        {
            if (existeChambresEn(res,periode,date_debut))
            return false ;

        }
        return true;
    }
    public boolean existeChambresEn(Reservation reservation, int periode, LocalDate date) {
        LocalDate dateFinReservation = reservation.getDatedebut().plusDays(reservation.getPeriode());

    // Vérifie si la réservation couvre la période demandée
    if (date.isAfter(reservation.getDatedebut()) && date.plusDays(periode).isBefore(dateFinReservation)) {
        return true;
    }

    // Vérifie si la date de début de la réservation est pendant la période demandée
    if (date.isBefore(reservation.getDatedebut()) && date.plusDays(periode).isAfter(reservation.getDatedebut())) {
        return true;
    }

    // Vérifie si la date de fin de la réservation est pendant la période demandée
    if (date.isBefore(dateFinReservation) && date.plusDays(periode).isAfter(dateFinReservation)) {
        return true;
    }

    return false;
    }
    
    public void ajouterReservation(Reservation reservation)
    {
        reservations.add(reservation);
    }
    public void libererChambre(Reservation reservation) throws ReservationInexistante{
        if (existeconsommationnonpaye(reservation)==false)
        {reservations.remove(reservation);
        consommations.removeAll(consommations);
        System.out.println("Chambre est liberee !");
        }
        throw new ReservationInexistante("Consommation non paye");
    }
    public void addConsommation(Reservation reservation,Consommation consommation) throws ReservationInexistante
    {

    if (reservations.indexOf(reservation)>=0 && (!consommation.getDateConsommation().isBefore(reservation.getDatedebut()) && consommation.getDateConsommation().isBefore(reservation.getDatedebut().plusDays(reservation.getPeriode()))))
    { consommations.add(consommation); }
    }
    public double MontantConsommationTotalChambre(Reservation reservation)
    {
        double p=0;
        List<Consommation> l= this.getListConsommations(reservation.getDatedebut(),reservation.getPeriode());
        for (Consommation c : l)
        {
            p+=c.MontantConsommation();
        }
        return p;
    }
    public double MontantReservation(Reservation reservation)
    {
        return capacite*categorie.getPrix();
    }
    public boolean existeconsommationnonpaye(Reservation reservation)
    {
        for(Consommation consommation :consommations)
        {
            if(consommation.getPaye()==false)
                return true;

        }return false ;
    }
    public String toString()
    {
        return "Chambre numero :"+num_chambre+" Etage : "+num_etage+" Capacité :"+capacite;
    }
    public List<Consommation> getListConsommations(LocalDate date, int periode) {
        List<Consommation> l = new ArrayList<>();
        LocalDate periodeFin = date.plusDays(periode);
    
        for (Consommation c : consommations) {
            LocalDate dateConsommation = c.getDateConsommation();
    
            if (!dateConsommation.isBefore(date) && dateConsommation.isBefore(periodeFin)) {
                l.add(c);
            }
        }
    
        return l;
    }
}
