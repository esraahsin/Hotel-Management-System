public class Adresse{
    private String nom_rue,num_immeuble,nom_commune,code_postal,departement,pays;
    public Adresse(String nom_rue,String num_immeuble,String nom_commune,String code_postal,String departement,String pays)
    {
        this.nom_rue=nom_rue;
        this.num_immeuble=num_immeuble;
        this.nom_commune=nom_commune;
        this.code_postal=code_postal;
        this.departement=departement;
        this.pays=pays;
    }
    public String getNomRue()
    {
        return nom_rue;
    }
    public void setNomRue(String nom)
    {
        nom_rue=nom;
    }
    public String getNumImmeuble()
    {
        return num_immeuble;
    }
    public void setNumImmeuble(String num_immeuble)
    {
        this.num_immeuble=num_immeuble;
    }
    public String getNomCommune()
    {
        return nom_commune;
    }
    public void setNomCommune(String nom)
    {
        nom_commune=nom;
    }
    public String getCodePostal()
    {
        return code_postal;
    }
    public void setCodePostal(String code)
    {
        code_postal=code;
    }
    public String getDepartement()
    {
        return departement;
    }
    public void setDepartement(String nom)
    {
        departement=nom;
    }
    public String getPays()
    {
        return pays;
    }
    public void setPays(String nom)
    {
        pays=nom;
    }
    public String toString()
    {
        return "Adresse : Nom de rue : "+nom_rue+"  Num immeuble "+num_immeuble+"  Nom Commune  "+nom_commune+" Code postal "+code_postal+" Departement : "+departement+"Pays : "+pays ;
    }
}
