import java.time.LocalDate;
public class Consommation{
    private LocalDate date_consommation ;
    private Service service ;
    private String heure ;
    private double  quantite ;
    private Client responsable ;
    private double montantTotalConsommation;
    private boolean paye;
    public Consommation(LocalDate date_consommation,Service service,String heure,int quantite,Client responsable)
    {
        this.date_consommation=date_consommation;
        this.heure=heure;
        this.service=service;
        this.quantite=quantite;
        this.responsable=responsable;
        montantTotalConsommation=0;
        paye=false;
    }
    public LocalDate getDateConsommation()
    {
        return date_consommation;
    }
    public void setDateConsommation(LocalDate date)
    {
        date_consommation=date;
    }
    public Service getService()
    {
        return service;
    }
    public void setService(Service service)
    {
        this.service=service;
    }
    public String getHeureConsommation()
    {
        return heure;
    }
    public void setHeureConsommation(String heure)
    {
        this.heure=heure;
    }
    public double getQuantite()
    {
        return quantite;
    }
    public void setQuantite(double quantite)
    {
        this.quantite=quantite;
    }
    public double getMontantTotalConsommation()
    {
        return montantTotalConsommation;
    }
    public void setMontantTotalConsommation(double m)
    {
        this.montantTotalConsommation=m;
    }
    public Client getClientResponsable()
    {
        return responsable;
    }
    public void setClientResponsable(Client res)
    {
        responsable=res;
    }
    public double MontantConsommation()
    {
        if (service.getEstTarifforfaitaire())
            return service.PrixService();
        return service.PrixService()*quantite;
    }
    public  void getArticleMiniBar(double prix)
    {
        System.out.println("Client prend un article de mini bar ");
        montantTotalConsommation+=prix;
        
    }
    public void getAppelTeleph(String destination,double prix)
    {
        System.out.println("Appel dont la destination est "+destination);
        montantTotalConsommation+=prix;

    }
    public void getPetitdej(Categorie categorie)
    {
        System.out.println("Petit dej de categorie "+categorie.toString());
        montantTotalConsommation+=categorie.getPrix();

    }
    public boolean getPaye()
    {
        return paye;
    }
    public void setPaye(boolean p)
    {
        paye=p;
    }
    public String toString()
    {
        return "Service  :"+service.toString()+"  quantite "+quantite+"  Date de commande :"+date_consommation;}
    
}
