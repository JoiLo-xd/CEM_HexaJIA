/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.persistence;

import cem.model.Inscripcio;
import cem.model.Participant;
import cem.model.Marxa;
import cem.model.TO.InscripcionsRanking;
import cem.model.TO.ParticipantEditionTO;
import cem.model.TO.StatsMarxesTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author ivang
 */
public class cemDAO {

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cem";
        String user = "user1";
        String pass = "Asdqwe123";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }
    
    public void setTimeInscripcio(int Dorsal, int edicio, LocalTime valor_imp)throws SQLException{
        //por poner
    }

    public ArrayList<ParticipantEditionTO> getMarxes() throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT edicio FROM marxa");
        ArrayList<ParticipantEditionTO> marxes = new ArrayList<>();
        while (rs.next()) {
            int edicio = rs.getInt("edicio");
            Statement st2 = c.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM inscripcio WHERE edicio = " + edicio);
            int numParticipants = 0;
            if (rs2.next()) {
                numParticipants = rs2.getInt(1);
            }
            rs2.close();
            st2.close();
            marxes.add(new ParticipantEditionTO(edicio, numParticipants));
        }
        rs.close();
        st.close();
        desconectar(c);
        return marxes;
    }
    
    public ArrayList<InscripcionsRanking> getInscripcions(int edicio, int opcio) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ArrayList<InscripcionsRanking> inscripcions = new ArrayList<>();
        switch(opcio) {
            case 0:
                ResultSet rs0 = st.executeQuery("SELECT p.nom, p.cognom, p.nif FROM inscripcio as i join participant as p on p.nif = i.nif where edicio = " + edicio + " order by TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida)");
                while (rs0.next()) {
                    String nom = rs0.getString(1);
                    nom = nom.concat(" " + rs0.getString(2));
                    String nif = rs0.getString(3);
                    Statement st2 = c.createStatement();
                    ResultSet rs2 = st2.executeQuery("SELECT TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida), asistencia FROM inscripcio WHERE edicio = " + edicio + " and nif = '" + nif + "'");
                    String temps = "--:--:--";
                    String assistencia = "";
                    if (rs2.next()) {
                        if (rs2.getTimestamp(1) != null) {
                            temps = rs2.getTimestamp(1).toString();
                        }
                        assistencia = rs2.getString(2);
                    }
                    rs2.close();
                    st2.close();
                    inscripcions.add(new InscripcionsRanking(nom, temps, assistencia));
                    
                }rs0.close();
                break;
            case 1:
                ResultSet rs1 = st.executeQuery("SELECT p.nom, p.cognom, p.nif, i.asistencia FROM inscripcio as i join participant as p on p.nif = i.nif where edicio = " + edicio + " and i.asistencia = 'No ha vingut'");
                while (rs1.next()) {
                    String nom = rs1.getString(1);
                    nom = nom.concat(" " + rs1.getString(2));
                    String nif = rs1.getString(3);
                    String temps = "--:--:--";
                    String assistencia = rs1.getString(4);
                    inscripcions.add(new InscripcionsRanking(nom, temps, assistencia));
                    
                }rs1.close();
                break;
            case 2:
                ResultSet rs2 = st.executeQuery("SELECT p.nom, p.cognom, p.nif, i.asistencia FROM inscripcio as i join participant as p on p.nif = i.nif where i.edicio = " + edicio + " and i.asistencia = 'Ha abandonat'");
                while (rs2.next()) {
                    String nom = rs2.getString(1);
                    nom = nom.concat(" " + rs2.getString(2));
                    String nif = rs2.getString(3);
                    String temps = "--:--:--";
                    String assistencia = rs2.getString(4);
                    inscripcions.add(new InscripcionsRanking(nom, temps, assistencia));
                    
                }rs2.close();
                break;
            case 3:
                ResultSet rs3 = st.executeQuery("SELECT p.nom, p.cognom, p.nif FROM inscripcio as i join participant as p on p.nif = i.nif where edicio = " + edicio + " order by TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida) desc");
                while (rs3.next()) {
                    String nom = rs3.getString(1);
                    nom = nom.concat(" " + rs3.getString(2));
                    String nif = rs3.getString(3);
                    Statement st32 = c.createStatement();
                    ResultSet rs32 = st32.executeQuery("SELECT TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida), asistencia FROM inscripcio WHERE edicio = " + edicio + " and nif = '" + nif + "'");
                    String temps = "--:--:--";
                    String assistencia = "";
                    if (rs32.next()) {
                        if (rs32.getTimestamp(1) != null) {
                            temps = rs32.getTimestamp(1).toString();
                        }
                        assistencia = rs32.getString(2);
                    }
                    rs32.close();
                    st32.close();
                    inscripcions.add(new InscripcionsRanking(nom, temps, assistencia));
                    
                }rs3.close();
                break;
        }
        st.close();
        desconectar(c);
        return inscripcions;
    }
    
    public ArrayList<StatsMarxesTO> getStatsMarxes() throws SQLException {
        //hacer codigo bien
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT edicio FROM marxa");
        ArrayList<StatsMarxesTO> statsMarxes = new ArrayList<>();
        while (rs.next()) {
            int edicio = rs.getInt("edicio");
            Statement st2 = c.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM inscripcio WHERE edicio = " + edicio);
            int inscrits = 0;
            if (rs2.next()) {
                inscrits = rs2.getInt(1);
            }
            rs2 = st2.executeQuery("SELECT COUNT(*) FROM inscripcio WHERE hora_sortida is not null and edicio = " + edicio);
            int enCursa = 0;
            if (rs2.next()) {
                enCursa = rs2.getInt(1);
            }
            rs2 = st2.executeQuery("SELECT COUNT(*) FROM inscripcio WHERE hora_arribada is not null and edicio = " + edicio);
            int arribats = 0;
            if (rs2.next()) {
                arribats = rs2.getInt(1);
            }
            rs2 = st2.executeQuery("SELECT COUNT(*) FROM inscripcio WHERE hora_arribada is null and hora_sortida is null and edicio = " + edicio);
            int absents = 0;
            if (rs2.next()) {
                absents = rs2.getInt(1);
            }
            rs2 = st2.executeQuery("SELECT MAX(hora_sortida - hora_arribada), MAX(TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida)), MIN(TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida)) FROM inscripcio WHERE asistencia = 'ha abandonat' and edicio = " + edicio);
            int abandonat = 0;
            int segons = 0;
            int segons2 = 0;
            LocalTime rapid = null;
            LocalTime lent = null;
            if (rs2.next()) {
                abandonat = rs2.getInt(1);
                segons = rs2.getInt(2);
                rapid = LocalTime.ofSecondOfDay(segons);
                segons2 = rs2.getInt(3);
                lent = LocalTime.ofSecondOfDay(segons2);
            }
            rs2.close();
            st2.close();
            statsMarxes.add(new StatsMarxesTO(edicio, inscrits, enCursa, arribats, absents, abandonat, rapid, lent));
        }
        rs.close();
        st.close();
        desconectar(c);
        return statsMarxes;
    }

    public void insertMarxa(Marxa marxa) throws SQLException {
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into marxa values (?);");
        ps.setInt(1, marxa.getEdicio());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

    public boolean existMarxa(Marxa marxa) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select * from marxa where edicio = '" + marxa.getEdicio() + "';");
        boolean existe = rs.next();
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    public void insertParticipant(Participant corredor) throws SQLException {
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into participant values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setString(1, corredor.getNif());
        ps.setString(2, corredor.getNom());
        ps.setString(3, corredor.getCognoms());
        ps.setDate(4, Date.valueOf(corredor.getDataNaixement()));
        ps.setBoolean(5, corredor.isSexe());
        ps.setString(6, corredor.getPoblacio());
        ps.setString(7, corredor.getNumTelefon());
        ps.setString(8, corredor.getEmail());
        if (!corredor.isFederat()) {
            ps.setNull(9, java.sql.Types.VARCHAR);
            ps.setNull(10, java.sql.Types.VARCHAR);
        } else {
            ps.setBoolean(9, corredor.isFederat());
            ps.setString(10, corredor.getEntitat());
        }
        if (corredor.getObservacions() == null) {
            ps.setNull(11, java.sql.Types.VARCHAR);
        } else {
            ps.setString(8, corredor.getObservacions());
        }
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

    public Participant getParticipant(String dni) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select nif, nom,cognom,naixement,sexe,poblacio,num_telf,gmail,federat,entitat from participant where nif = '" + dni + "';");
        //Patata incoming
        rs.next();
        return new Participant(rs.getString(1), rs.getString(2), rs.getString(3),
                rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getString(6),
                rs.getString(7), rs.getString(8), rs.getString(10), rs.getBoolean(9));

    }

    public Inscripcio getInscripcio(String dni, String edicio) throws SQLException {
        Connection c = conectar();
        String query = ("SELECT nif, edicio FROM inscripcio WHERE nif = ? AND edicio = ?");
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, dni);
        ps.setInt(2, Integer.parseInt(edicio));
        ResultSet rs = ps.executeQuery();

        Inscripcio inscripcio = null;
        if (rs.next()) {
            inscripcio = new Inscripcio(rs.getString(1), rs.getInt(2));
        }
        rs.close();
        ps.close();
        desconectar(c);
        return inscripcio;
    }

    public boolean existParticipant(Participant corredor) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select * from participant where nif = '" + corredor.getNif() + "';");
        boolean existe = rs.next();
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    public void insertInscripcio(Inscripcio inscripcio) throws SQLException {
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement(" INSERT INTO inscripcio (dorsal, modalitat, asistencia, nif, edicio) VALUES (?, ?, ?, ?, ?);");
        ps.setInt(1, inscripcio.getDorsal());
        ps.setBoolean(2, inscripcio.isModalitat());
        ps.setString(3, inscripcio.getAsistencia());
        ps.setString(4, inscripcio.getDni());
        ps.setInt(5, inscripcio.getEdicio());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

    public boolean existInscripcio(Inscripcio inscricpio) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select * from inscripcio where nif = '" + inscricpio.getDorsal() + "';");
        boolean existe = rs.next();
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    public boolean existParticipantinInscripcio(String dni, int edicio) throws SQLException {
        Connection c = conectar();
        String query = ("SELECT * FROM inscripcio WHERE nif = ? AND edicio = ?");
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, dni);
        ps.setInt(2, edicio);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        desconectar(c);
        return existe;
    }

    public boolean existParticipantforDNI(String dni) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select * from participant where nif = '" + dni + "';");
        boolean existe = rs.next();
        rs.close();
        st.close();
        desconectar(c);
        return existe;
    }

    public void modifiParticipant(Participant pa) throws SQLException {
        Connection c = conectar();
        String query = "UPDATE participant set nom = ?, cognom = ?, sexe = ?, poblacio = ?, num_telf = ?, gmail = ?, federat = ?, entitat = ? where nif = '" + pa.getNif() + "';";

        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, pa.getNom());
        ps.setString(2, pa.getCognoms());
        ps.setBoolean(3, pa.isSexe());
        ps.setString(4, pa.getPoblacio());
        ps.setString(5, pa.getNumTelefon());
        ps.setString(6, pa.getEmail());
        ps.setBoolean(7, pa.isFederat());
        ps.setString(8, pa.getEntitat());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }
    
    public void modifiInscripcio(Inscripcio in) throws SQLException {
        Connection c = conectar();
        String query = "UPDATE inscripcio set nif = ?, modalitat = ?, dorsal = ? where nif = '" + in.getDni()+ "';";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, in.getDni());
        ps.setBoolean(2, in.isModalitat());
        ps.setInt(3, in.getDorsal());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

    public boolean existDorsal(int dorsal, int edicio) throws SQLException {
        Connection c = conectar();
        String query = "SELECT * FROM inscripcio WHERE dorsal = ? AND edicio = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, dorsal);
        ps.setInt(2, edicio);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        desconectar(c);
        return existe;
    }

}
