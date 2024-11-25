import java.util.ArrayList;
import java.util.List;
class HotelRemoveException extends Exception{
    HotelRemoveException(String message)
    {
        super(message);
    }
}
public class Organisme {
    public String NomOrganisme;
    public List<Hotel> hotels ;
    public Organisme(String nomOrganisme)
    {
        this.NomOrganisme=nomOrganisme;
        this.hotels=new ArrayList<>();

    }
    public void addHotel(Hotel hotel)
    {
        hotels.add(hotel);
    }
    public void supprimerHotel(Hotel hotel) throws HotelRemoveException
    {
        if (existeHotel(hotel))
        {
            hotels.remove(hotel);
        }
        else {
            throw new HotelRemoveException("Hotel n existe pas dans la liste de cette organisme !");
        }


    }
    public boolean  existeHotel(Hotel hotel)
    {
        if (hotels.indexOf(hotel)!=0)
            return true;
        return false ;
    }
    public String toString()
    {
        return " Nom :"+NomOrganisme+" Listes d Hotels :"+hotels;

    }
}