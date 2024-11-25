public class Service{
    public String nom;
    public double prix;
    private boolean tarifForfaitaire;
    public Service(String nom,double prix,boolean tarifForfaitaire)
    {
        this.nom=nom;
        this.prix=prix;
        this.tarifForfaitaire=tarifForfaitaire;
    }
    public boolean  getEstTarifforfaitaire()
    {
        return tarifForfaitaire ;
    }
    public void setEstTarifForfaitaire(boolean f)
    {
        tarifForfaitaire=f;
    }
    public double PrixService()
    {
        return prix;

    }
    public String toString()
    {
        return nom+" prix :"+prix ;
    }
    

}
