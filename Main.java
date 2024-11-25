import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args)throws ReservationInexistante,ChambreNonDisponibleException{
    Adresse adresseHotel1 = new Adresse("rue1", "3", "nom_commune", "2832", "departement", "pays de hotel1");
    Categorie categorieSuite = new Categorie(true, true, true, 150.0);
    Categorie categorieStandard = new Categorie(true, false, true, 100.0);
    Categorie categorieEconomique = new Categorie(false, true, false, 80.0);
    Organisme organisme1 = new Organisme("Organisme1");
    
    Chambre ch4=new Chambre(categorieSuite, 1, 100001, 2, 1, 1);
    Chambre ch1=new Chambre(categorieStandard, 1, 100002, 2, 1, 1);
    Chambre ch2=new Chambre(categorieEconomique, 1, 100003, 2, 1, 1);
    Chambre ch3=new Chambre(categorieSuite, 1, 100004, 2, 1, 1);
    Hotel hotel1 = new Hotel("Hotel1", "4 etoile ", "8 am", "9pm", adresseHotel1);
    hotel1.ajouterChambre(ch1);
    hotel1.ajouterChambre(ch2);
    hotel1.ajouterChambre(ch3);
    hotel1.ajouterChambre(ch4);
    organisme1.addHotel(hotel1);
    List<Chambre> chambreAReserver1=new ArrayList<>();
    List<Chambre> chambreAReserver2=new ArrayList<>();
    List<Chambre> chambreAReserver3=new ArrayList<>();
    chambreAReserver1.add(ch1);
    chambreAReserver1.add(ch2);
    chambreAReserver2.add(ch1);
    chambreAReserver2.add(ch4);
    chambreAReserver3.add(ch3);


    Adresse adresseClient1 = new Adresse("Rue1", "123", "Ville1", "12345", "Dept1", "Pays1");
    Adresse adresseClient2 = new Adresse("Rue2", "456", "Ville2", "67890", "Dept2", "Pays2");
    Client client1 = new Client("Nom1", "Prenom1", adresseClient1, 123456789, "email1@example.com", "siteWeb1", "Actif");
    Client client2 = new Client("Nom2", "Prenom2", adresseClient2, 987654321, "email2@example.com", "Inactif");
    Client client3 = new Client("Nom3", "Prenom3", adresseClient1, 111222333, "email3@example.com", "siteWeb3", "Actif");
    Client client4 = new Client("Nom4", "Prenom4", adresseClient2, 444555666, "email4@example.com", "Inactif");
    Reservation reservationClient1 = new Reservation(client1, chambreAReserver1,LocalDate.of(2023, 2, 7), 3, null, 0);
    Reservation reservationClient2 = new Reservation(client2, chambreAReserver1,LocalDate.of(2023, 2, 1), 3, null, 0);
    Reservation reservationClient3 = new Reservation(client3, chambreAReserver3, LocalDate.of(2023, 1, 7), 4, null, 0);
    Reservation reservationClient4 = new Reservation(client4, chambreAReserver2, LocalDate.of(2023, 1, 7), 4, null, 0);

    client1.ClienteffectuerReservation(hotel1, reservationClient1);
    client2.ClienteffectuerReservation(hotel1, reservationClient2);
    client3.ClienteffectuerReservation(hotel1, reservationClient3);
    client4.ClienteffectuerReservation(hotel1, reservationClient4);
    Service service = new Service("Nom du service", 16, false);

    Consommation consommation = new Consommation(LocalDate.of(2023, 2, 7), service, "12:00", 2, client1);
    client1.VaConsommer(consommation,ch2,reservationClient1);
    client1.ajouterPetitDejeuner(5,ch2,LocalDate.of(2023, 2, 7),"12",reservationClient1);
    client2.ajouterPetitDejeuner(4,ch1,LocalDate.of(2023, 2, 1),"10",reservationClient2);
    client3.ajouterAppelTelephonique(10,ch3,LocalDate.of(2023, 1, 8),"19",reservationClient3);
    client4.ajouterAppelTelephonique(10,ch4,LocalDate.of(2023,1,7),"12",reservationClient4);
    reservationClient1.ImprimerFacture();
    reservationClient2.ImprimerFacture();
    reservationClient3.ImprimerFacture();
    reservationClient4.ImprimerFacture();
    reservationClient1.payerReservation(LocalDate.of(2023, 2, 7));
    reservationClient2.libererChambres(LocalDate.of(2023, 2, 1));        
    

}}