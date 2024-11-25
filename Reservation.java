import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Reservation  {
    private Client clientResponsable ;
    private List<Chambre> chambresReservees;
    private Facture facture;
    private LocalDate datedebut;
    private int periode_jours ;
    private int numero;
    private static int numeroReservation=0 ;
    private boolean estpayee;

    public Reservation(Client clientResponsable,List<Chambre> chambresReservees,LocalDate datedebut,int periode_jours,String mode_paiment,double remise) throws ReservationInexistante
    {
        this.clientResponsable=clientResponsable;
        this.chambresReservees=chambresReservees;
        this.datedebut=datedebut;
        this.periode_jours=periode_jours;
        numeroReservation++;
        numero=numeroReservation;
        estpayee=false;
        facture= new Facture(this,mode_paiment,remise);
        
    }
    public int getNumReservation()
    {
        return numero;
    }
    public void ImprimerFacture() throws ReservationInexistante
    {
        facture.afficherFacture();
    }
    public void payerReservation(LocalDate datepai) throws ReservationInexistante{
        if (!(estpayee)) {
            facture.setDatePaiement(datepai) ;// Enregistrez la date actuelle comme date de paiement
            estpayee = true;
            System.out.println("La facture a été payée avec succès par  "+clientResponsable);
        } else {
            System.out.println("La facture a été  deja payée  par le client "+clientResponsable);
        }
    }
    public Client getResponsable()
    {
        return clientResponsable;
    }
    public void setResponsable(Client new_responsable)
    {
        clientResponsable=new_responsable;
    }
    public List<Chambre> getChambresReservees()
    {
        return chambresReservees ;
    }
    public void setListChambresReservees(List<Chambre> new_list)
    {
        chambresReservees=new_list ;
    }
    public LocalDate getDatedebut()
    {
        return datedebut;
    }
    public void setDatedebut(LocalDate new_date)
    {
        datedebut=new_date ;
    }
    public int getPeriode()
    {
        return periode_jours;
    }
    public void setPeriode(int new_periode)
    {
        periode_jours=new_periode;
    }
    public List<Categorie> getCategorie()
    {
        List<Categorie> listeCategories=new ArrayList<>();
        for (Chambre c : chambresReservees )
            {if (existecategorie(c.getCategorie())==false )
                {
                    listeCategories.add(c.getCategorie());
                }}
        return listeCategories ;
            
    }
    boolean existecategorie(Categorie categorie)
    {
        for (Chambre ch : chambresReservees)
        { if (ch.getCategorie().equals(categorie)) 
            return true ;
        
    }
    return false ;
    }
    public int getNombreChambresDemandees()
    {
        return chambresReservees.size();

    }
    public double  CalculerPrixChambres()
    {
        int prix=0;
        for (Chambre ch : chambresReservees)
        {
            prix+=ch.getCategorie().getPrix();
        }
        return prix;
    }
    public void addChambreAReserver(Chambre ch,int periode,LocalDate date) throws ChambreNonDisponibleException
    {
        if (ch.estDisponible(periode,date))
        chambresReservees.add(ch);
        else 
        throw new ChambreNonDisponibleException("Chambre n'est pas disponible pour reserver ");

    }
    public static int  getNumeroReservation()
    {
        return numeroReservation ;
    }
    public boolean getEstPayee()
    {
        return estpayee;
    }
    public void libererChambres(LocalDate dateAnnulation) throws ReservationInexistante {
        if (PasencoreArrive(dateAnnulation)) {
            for (Chambre ch : chambresReservees) {
                ch.getReservations().remove(this);
            }
            chambresReservees.clear();
            System.out.println("Réservation annulée");
        } else if (estpayee) {
            for (Chambre ch : chambresReservees) {
                
                ch.getReservations().remove(this);
            }
            System.out.println("Chambres libérées");
        } else {
            throw new ReservationInexistante("La réservation n'est pas encore payée, impossible de libérer les chambres");
        }
    }
    
    public boolean PasencoreArrive(LocalDate dateAnnulation)
    {
        return dateAnnulation.isBefore(datedebut) ;
    }
    public String toString()
    {
        return "Client responsable :"+clientResponsable.toString()+"  numero de reservation :"+numeroReservation+"date :  "+datedebut;
    }
    
}

