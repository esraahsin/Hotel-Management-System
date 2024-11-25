import java.time.LocalDate;
import java.util.Date;

public class Facture {
    private  int compteur=0;
    private int num_facture;

    private Reservation reservation ;
    private String mode_paiment ;
    private LocalDate date_paiement ;
    private double montant ;
    private double remise ;
    private boolean estpayee;

    public Facture(Reservation reservation,String mode_paiment,double remise)
    {
        this.reservation=reservation;
        this.mode_paiment=mode_paiment;
        this.remise=remise;
        compteur++;
        num_facture=reservation.getNumReservation();
    }
    public Facture(Reservation reservation,String mode_paiment,LocalDate date_paiement)
    {
        this.reservation=reservation;
        this.date_paiement=date_paiement;
        this.mode_paiment=mode_paiment;
        this.remise=0;
        compteur++;
        num_facture=reservation.getNumReservation();
    }
    
    public double MontantFacture()
    {
        double  p=0;
        for(Chambre ch : reservation.getChambresReservees())
        {
            p+=ch.MontantConsommationTotalChambre(reservation)+ch.MontantReservation(reservation);
        }
        if (remise==0)
        return p;
        return (p*remise/100);

    }
    public void afficherFacture() throws ReservationInexistante {
        boolean test=false;
        for (Reservation res : reservation.getResponsable().getReservationsClient())
        {
            if(res.equals(reservation))
                test=true;
        }
        if (test==false)
        {
           throw new ReservationInexistante("Erreur de facturation :Pas de Reservation pour le client :"+reservation.getResponsable().toString());
        }
        System.out.println("********************FACTURE********************");
        System.out.println("Reservation n°"+reservation.getNumReservation()+"  Client Responsable :"+reservation.getResponsable()+"\n");
        System.out.println("Mode de paiment: "+this.mode_paiment+"\n");
        for (Chambre ch : reservation.getChambresReservees()) {
            System.out.print("Chambre : " + ch.toString() + " Consommations : ");
            
            if (ch.getListConsommations(reservation.getDatedebut(),reservation.getPeriode()).size()> 0) {
                System.out.println(ch.getListConsommations(reservation.getDatedebut(),reservation.getPeriode()));
            } else {
                System.out.println("Aucune consommation disponible.");
            }
        }
        System.out.println("Montant à payer : " + this.MontantFacture()+"\n");
        System.out.println("**************************************************");
    }
    public LocalDate getDatePaiement()
    {
        return date_paiement;
    }
    public void setDatePaiement(LocalDate date)
    {
        date_paiement=date;
    }
    
}