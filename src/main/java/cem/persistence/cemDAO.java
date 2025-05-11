/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cem.persistence;

import cem.model.Inscripcio;
import cem.model.Participant;
import cem.model.Marxa;
import cem.model.TO.ParticipantEditionTO;
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
    
    public Participant getParticipant(String dni) throws SQLException{
        Connection c = conectar();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select nif, nom,cognom,naixement,sexe,poblacio,num_telf,gmail,federat,entitat from participant where nif = '" + dni + "';");
        //Patata incoming
        rs.next();
        return new Participant(rs.getString(1),rs.getString(2),rs.getString(3),
                rs.getDate(4).toLocalDate(),rs.getBoolean(5),rs.getString(6),
                rs.getString(7),rs.getString(8),rs.getString(10),rs.getBoolean(9));
        
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
        String query = "SELECT * FROM inscripcio WHERE nif = ? AND edicio = ?";
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
    
    public void modifiParticipant(Participant pa) throws SQLException{
        Connection c = conectar();
        String query = "UPDATE participant set nom = ?, cognom = ?, sexe = ?, poblacio = ?, num_telf = ?, gmail = ?, federat = ?, entitat = ? where nif = '" + pa.getNif() + "';";
        
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, pa.getNom());
        ps.setString(2, pa.getCognoms());
        ps.setBoolean(3, pa.isSexe());
        ps.setString(4, pa.getPoblacio());
        ps.setString(5, pa.getNumTelefon());
        ps.setString(6,pa.getEmail());
        ps.setBoolean(7, pa.isFederat());
        ps.setString(8,pa.getEntitat());
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }

}
