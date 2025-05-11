/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.persistence;

import cem.model.Inscripcio;
import cem.model.Participant;
import cem.model.Marxa;
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
            rs2 = st2.executeQuery("SELECT MAX(hora_sortida - hora_arribada) FROM inscripcio WHERE asistencia = 'ha abandonat' and edicio = " + edicio);
            int abandonat = 0;
            if (rs2.next()) {
                abandonat = rs2.getInt(1);
            }
            rs2 = st2.executeQuery("SELECT MAX(TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida)) FROM inscripcio WHERE asistencia = 'ha abandonat' AND edicio = " + edicio);
            int segons = 0;
            LocalTime rapid = null;
            if (rs2.next()) {
                segons = rs2.getInt(1);
                rapid = LocalTime.ofSecondOfDay(segons);
            }
            
            rs2 = st2.executeQuery("SELECT MIN(TIMESTAMPDIFF(SECOND, hora_arribada, hora_sortida)) FROM inscripcio WHERE asistencia = 'ha abandonat' AND edicio = " + edicio);
            int segons2 = 0;
            LocalTime lent = null;
            if (rs2.next()) {
                segons2 = rs2.getInt(1);
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
