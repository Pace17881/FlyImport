package beans;

import dtos.*;
import exceptions.RowSetExists;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import tables.*;

@Stateless
public class MoveDB implements MoveDBLocal
{

    @PersistenceContext(unitName = "cdtUnit")
    private EntityManager em;

    private Fluggesellschaft rmFluggesellschaft;
    private Flughafen rmFlughafen;
    private Fluglinie rmFluglinie;
    private Flugzeug rmFlugzeug;
    private Passagier rmPassagier;

    @Override
    public Report_DTO persistData(ArrayList<FI_DTO> list)
    {
        ArrayList<FI_DTO> goodList = new ArrayList<>();
        ArrayList<FI_DTO> doppList = new ArrayList<>();
        ArrayList<FI_DTO> errList = new ArrayList<>();

        for (FI_DTO elem : list)
        {
            FI_Buchung buchung = elem.getFI_B();
            FI_Fluggesellschaft flGesell = elem.getFI_FG();
            FI_Fluglinie linie = elem.getFI_FL();
            FI_Flugzeug fZeug = elem.getFI_FZ();
            FI_Passagier pass = elem.getFI_P();

            try
            {
                persistLand(linie, pass);

                persistFluggesellschaft(flGesell);

                persistFlugzeug(fZeug);

                persistFlughafen(linie);

                persistPassagier(pass);

                persistFluglinie(linie, fZeug, flGesell);

                persistBuchung(buchung, pass, linie);

                goodList.add(elem);
            }
            catch (RowSetExists rse)
            {
                System.out.println(rse.getMessage());
                doppList.add(elem);
                removeRowSet();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                errList.add(elem);
                removeRowSet();
            }
        }
        return new Report_DTO(errList, doppList, goodList);
    }

    private void persistLand(FI_Fluglinie linie, FI_Passagier pass) throws Exception
    {
        String[] countries =
        {
            linie.getStartLand(),
            linie.getZielLand(),
            pass.getLand()
        };
        String jpql = "Select l from Land l "
                + "where lower(l.landName) = :land";

        for (String country : countries)
        {
            Query q;
            List<Land> l;

            q = em.createQuery(jpql);
            q.setParameter("land", country.toLowerCase());
            l = q.getResultList();

            if (l.isEmpty())
                em.persist(new Land(country.intern()));
        }
    }

    private void persistFluggesellschaft(FI_Fluggesellschaft fi_flGesell) throws Exception
    {
        Fluggesellschaft flGesell;
        flGesell = em.find(
                Fluggesellschaft.class,
                fi_flGesell.getGesellschaftID());

        if (flGesell == null)
        {
            flGesell = new Fluggesellschaft(
                    fi_flGesell.getGesellschaftID(),
                    fi_flGesell.getGesellschaftName());

            rmFluggesellschaft = flGesell;
            em.persist(flGesell);
        }
    }

    private void persistFlugzeug(FI_Flugzeug fi_fZeug) throws Exception
    {
        Flugzeug fZeug = em.find(Flugzeug.class, fi_fZeug.getTyp());
        if (fZeug == null)
        {
            fZeug = new Flugzeug(
                    fi_fZeug.getTyp(),
                    fi_fZeug.getHersteller(),
                    fi_fZeug.getAnzahlSitze());

            rmFlugzeug = fZeug;
            em.persist(fZeug);
        }
    }

    private void persistPassagier(FI_Passagier fi_pass) throws Exception
    {
        Passagier pass = em.find(Passagier.class, fi_pass.getPassagierNR());

        if (pass == null)
        {
            pass = new Passagier(
                    fi_pass.getPassagierNR(),
                    fi_pass.getName(),
                    fi_pass.getOrt(),
                    getLandIdByName(fi_pass.getLand()));

            String anrede = fi_pass.getAnrede(),
                    plz = fi_pass.getPlz(),
                    strasse = fi_pass.getStrasse();

            if (anrede.length() > 0)
                pass.setAnrede(anrede);

            if (plz.length() > 0)
                pass.setPlz(plz);

            if (strasse.length() > 0)
                pass.setStrasse(strasse);

            rmPassagier = pass;
            em.persist(pass);
        }
    }

    private void persistFlughafen(FI_Fluglinie fi_fLinie) throws Exception
    {

        Flughafen fHafen = em.find(Flughafen.class, fi_fLinie.getStartFlughafenID());
        if (fHafen == null)
        {
            fHafen = new Flughafen(
                    fi_fLinie.getStartFlughafenID(),
                    fi_fLinie.getStartStadt());
            fHafen.setLandId(getLandIdByName(fi_fLinie.getStartLand()));

            em.persist(fHafen);
        }

        fHafen = em.find(Flughafen.class, fi_fLinie.getZielFlughafenID());
        if (fHafen == null)
        {
            fHafen = new Flughafen(
                    fi_fLinie.getZielFlughafenID(),
                    fi_fLinie.getZielStadt());
            fHafen.setLandId(getLandIdByName(fi_fLinie.getZielLand()));

            em.persist(fHafen);
        }
    }

    private void persistFluglinie(
            FI_Fluglinie fi_fLinie,
            FI_Flugzeug fi_fZeug,
            FI_Fluggesellschaft fi_flGesell
    ) throws Exception
    {

        FlugliniePK flugLPK = new FlugliniePK(
                fi_fLinie.getLinie(),
                fi_fLinie.getAbflugDatum());
        Fluglinie fLinie = em.find(Fluglinie.class, flugLPK);

        if (fLinie == null)
        {
            fLinie = new Fluglinie(
                    flugLPK,
                    fi_fLinie.getDauer(),
                    fi_fLinie.getPreis(),
                    fi_fLinie.getSitzeBelegt());

            Fluggesellschaft fGesell;
            fGesell = new Fluggesellschaft(fi_flGesell.getGesellschaftID());
            fLinie.setGesellschaftId(fGesell);

            Flugzeug fZeug = new Flugzeug(fi_fZeug.getTyp());
            fLinie.setTyp(fZeug);

            Flughafen fHafen = new Flughafen(fi_fLinie.getStartFlughafenID());
            fLinie.setStFhId(fHafen);

            fHafen = new Flughafen(fi_fLinie.getZielFlughafenID());
            fLinie.setZiFhId(fHafen);

            rmFluglinie = fLinie;
            em.persist(fLinie);
        }

    }

    private void persistBuchung(
            FI_Buchung fi_buchung,
            FI_Passagier fi_pass,
            FI_Fluglinie fi_flLinie
    ) throws RowSetExists
    {
        BuchungPK buchungPK = new BuchungPK(
                fi_pass.getPassagierNR(),
                fi_flLinie.getLinie(),
                fi_flLinie.getAbflugDatum(),
                fi_buchung.getBuchungNR());
        Buchung buchung = em.find(Buchung.class, buchungPK);

        if (buchung == null)
        {
            buchung = new Buchung(buchungPK, fi_buchung.getBuchungDatum());
            em.persist(buchung);
        }
        else
            throw new RowSetExists("Buchung doppelt: " + buchung);
    }

    private Land getLandIdByName(String landName)
    {
        String jpql = "Select l from Land l "
                + "where lower(l.landName) = :land";
        Query q = em.createQuery(jpql);

        q.setParameter("land", landName.toLowerCase());
        Land land = (Land) q.getSingleResult();
        return land;
    }

    private void removeRowSet()
    {
        if (rmFluglinie != null)
            em.remove(rmFluglinie);
        if (rmPassagier != null)
            em.remove(rmPassagier);
        if (rmFlughafen != null)
            em.remove(rmFlughafen);
        if (rmFlugzeug != null)
            em.remove(rmFlugzeug);
        if (rmFluggesellschaft != null)
            em.remove(rmFluggesellschaft);
    }
}
