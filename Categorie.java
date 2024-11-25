public class Categorie{
    private boolean equipementBain ;
    private boolean equipementDouche ;
    private boolean equipementTeleviseur ;
    private double prix ;
    public Categorie(boolean equipementBain,boolean equipementDouche,boolean equipementTeleviseur,double prix)
    {
        this.equipementBain=equipementBain;
        this.equipementDouche=equipementDouche;
        this.equipementTeleviseur=equipementTeleviseur;
        this.prix=prix;
    }
    public boolean getEquipementBain()
    {
        return equipementBain;
    }
    public void setEquipementBain(boolean new_eq)
    {
        equipementBain=new_eq;
    }
    public boolean getEquipementDouche()
    {
        return equipementDouche;
    }
    public void setEquipementDouche(boolean new_eq)
    {
        equipementDouche=new_eq;
    }
    public boolean getEquipementTeleviseur()
    {
        return equipementTeleviseur;
    }
    public void setEquipementTeleviseur(boolean new_eq)
    {
        equipementTeleviseur=new_eq;
    }
    public double getPrix()
    {
        return prix;
    }
    public void setPrix(double new_p)
    {
        prix=new_p;
    }
}
