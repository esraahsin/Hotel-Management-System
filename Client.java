import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Client {
    private String nom ;
    private String prenom ;
    private Adresse adresse ;
    private int num_teleph;
    private String adresse_mail;
    private String site_web ;
    private List<Reservation> reservations;
    private String etat;
    private List<LocalDate> datesConsommation;
    public Client(String nom,String prenom,Adresse adresse,int num_teleph,String adresse_mail,String site_web,String etat)
    {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.num_teleph=num_teleph;
        this.adresse_mail=adresse_mail;
        this.site_web=site_web ;
        this.etat=etat ;
        reservations=new ArrayList<Reservation>() ;
        datesConsommation=new ArrayList<LocalDate>();
    }
    public Client(String nom,String prenom,Adresse adresse,int num_teleph,String adresse_mail,String etat)
    {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.num_teleph=num_teleph;
        this.adresse_mail=adresse_mail;
        this.etat=etat ;
        reservations=new ArrayList<Reservation>() ;
        datesConsommation=new ArrayList<LocalDate>();
    }
    public Client(String nom,String prenom,Adresse adresse,int num_teleph,String etat)
    {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.num_teleph=num_teleph;
        this.etat=etat ;
        reservations=new ArrayList<Reservation>() ;
        datesConsommation=new ArrayList<LocalDate>();
    }
    public Client(String nom,String prenom,Adresse adresse,String etat)
    {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.etat=etat ;
        reservations=new ArrayList<Reservation>() ;
        datesConsommation=new ArrayList<LocalDate>();
    }
    public String  getNomClient()
    {
        return nom;
    }
    public void setNomClient(String new_nom)
    {
        nom=new_nom;
    }
    public String getPrenomClient()
    {
        return prenom;
    }
    public void setPrenomClient(String new_prenom)
    {
        prenom=new_prenom;
    }
    public Adresse getAdresseClient()
    {
        return adresse;
    }
    public void setAdresseClient(Adresse adresse)
    {
        this.adresse=adresse ;   
    }
    public int getNumteleph()
    {
        return num_teleph;
    }
    public void setNumteleph(int num)
    {
        num_teleph=num ;
    }
    public String getAdresseMail()
    {
        return adresse_mail ;
    }
    public void setAdresseMail(String adr)
    {
        adresse_mail=adr ;
    }
    public String getSite_web()
     {
        return site_web ;
     }
     public void setSite_web(String site)
     {
        site_web=site ;
     }
     public List<Reservation> getReservationsClient()
     {
        return reservations ;
     }
     public void setReservationsClient(List<Reservation> liste)
     {
        reservations=liste ;
     }
     public String getEtat()
     {
        return etat ;
     }
     public void setEtat(String etat)
     {
        this.etat=etat ;
     }
     public List<LocalDate> getDatesConsommation()
     {
        return datesConsommation;
     }
     public void setDatesConsommation(List<LocalDate> liste)
     {
        datesConsommation=liste;
     }
     public void annulerReservation(Reservation reservation,LocalDate dateAnnulation) throws ReservationInexistante,ChambreNonDisponibleException
     {
        if (reservations.indexOf(reservation)==-1)
        throw new ReservationInexistante("Client n'a pas cette reservation");
        else 
        {reservations.remove(reservation);
        for (Chambre ch :reservation.getChambresReservees())
        {
            ch.getReservations().remove(reservation);
        }
        System.out.println("Reservation "+reservation.toString()+" est annulee!");
        reservation.libererChambres(dateAnnulation);
        }

     }
     public void ClienteffectuerReservation(Hotel hotel,Reservation reservation) throws ReservationInexistante,ChambreNonDisponibleException
     {
        if (!hotel.chambreDisponible(reservation))
            throw new ChambreNonDisponibleException("Désolé, aucune chambre disponible pour la réservation demandée:"+reservation.toString());
        if (hotel.chambreDisponible(reservation))
        {
            reservations.add(reservation);
            for (Chambre  ch: reservation.getChambresReservees())
            {
                ch.getReservations().add(reservation);
            }
            reservations.add(reservation);
            System.out.println("Reservation est effectue avec succees");

        
        }
        

        for( Chambre ch : reservation.getChambresReservees())
        {
            ch.getReservations().add(reservation);
        }
        hotel.effectuerReservation(reservation);
     }
    public void VaConsommer(Consommation consommation,Chambre ch,Reservation reservation) throws ReservationInexistante
    {
        boolean t=false;
        for (Reservation res :ch.getReservations())
        {
            if(res.equals(reservation))
            t=true;
        }
        if (!(reservations.contains(reservation)) || t==false)
        throw new ReservationInexistante("Reservation n existe pas pour ce client");
        ch.addConsommation(reservation,consommation);

    }
    public void ajouterPetitDejeuner(int quantite, Chambre chambre,LocalDate date,String  heure ,Reservation reservation) throws ReservationInexistante {
        boolean t=false;
        for (Reservation res :chambre.getReservations())
        {
            if(res.equals(reservation))
            t=true;
        }
        if (!(reservations.contains(reservation)) || t==false)
        throw new ReservationInexistante("Reservation n existe pas pour ce client");
        if (reservation.getChambresReservees().isEmpty()) {
            throw new ReservationInexistante("Impossible d'ajouter des consommations.");}
            Service petitDejeuner = new Service("Petit-déjeuner", 10.0, false); // Exemple de service petit-déjeuner
        Consommation consommation = new Consommation(date,petitDejeuner,heure, quantite, reservation.getResponsable());

        this.VaConsommer(consommation,chambre,reservation);
    }

    public void ajouterMiniBar(int quantite, Chambre chambre,LocalDate date,String  heure ,Reservation reservation) throws ReservationInexistante{
        boolean t=false;
        if (reservation.getChambresReservees().isEmpty()) {
            throw new ReservationInexistante("Impossible d'ajouter des consommations.");}
        for (Reservation res :chambre.getReservations())
        {
            if(res.equals(reservation))
            t=true;
        }
        if (!(reservations.contains(reservation)) && t==false)
        throw new ReservationInexistante("Reservation n existe pas pour ce client");
        Service miniBar = new Service("Mini-bar", 5.0, false); // Exemple de service minibar
        Consommation consommation = new Consommation(date,miniBar,heure, quantite, reservation.getResponsable());

        this.VaConsommer(consommation,chambre,reservation);
        consommation.getArticleMiniBar(5);
    }

    public void ajouterAppelTelephonique(int quantite, Chambre chambre,LocalDate date,String  heure ,Reservation reservation) throws ReservationInexistante{
        boolean t=false;
        if (reservation.getChambresReservees().isEmpty()) {
            throw new ReservationInexistante("Impossible d'ajouter des consommations.");}
        for (Reservation res :chambre.getReservations())
        {
            if(res.equals(reservation))
            t=true;
        }
        if (!(reservations.contains(reservation)) || t==false)        throw new ReservationInexistante("Reservation n existe pas pour ce client");
        double tarifParMinute = 0.1; // Exemple de tarif par minute pour les appels téléphoniques
        Service appelTelephonique = new Service("Appel téléphonique", tarifParMinute, false);
        Consommation consommation = new Consommation(date,appelTelephonique, heure,quantite, reservation.getResponsable());
                this.VaConsommer(consommation,chambre,reservation);

    }
    public String toString()
    {
        return "client :"+nom+" "+prenom+" ";
    }

}
