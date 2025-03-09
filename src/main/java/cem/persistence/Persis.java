package cem.persistence;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import cem.model.Corredor;
import cem.model.Marxa;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cem.enums.Asistencia;
import cem.enums.Sexe;
import cem.model.Inscripcio;



public class Persis {
    private String nameCorredor = "corredors.txt";
    private String nameMarxa = "marxes.txt";
    private String nameInscripcio = "inscripcions.txt";
    private String nameFolder = "dades";
    private String pathFolder;
    private String pathFileCorredor;
    private String pathFileMarxa;
    private String pathFileInscripcio;

    public Persis() throws IOException {
        pathFolder = "." + File.separator + nameFolder;
        pathFileCorredor = pathFolder + File.separator + nameCorredor;
        pathFileMarxa = pathFolder + File.separator + nameMarxa;
        pathFileInscripcio = pathFolder + File.separator + nameInscripcio;
        File folder = new File(pathFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File corredor = new File(pathFileCorredor);
        if (!corredor.exists()) {
            corredor.createNewFile();

        }
        File marxa = new File(pathFileMarxa);
        if (!marxa.exists()) {
            marxa.createNewFile();

        }
        File inscripcio = new File(pathFileInscripcio);
        if (!inscripcio.exists()) {
            inscripcio.createNewFile();
        }
    }

    public void writerCorredorInFile(Corredor c) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileCorredor, true));
        writer.write(c.getNif() + "/" + c.getNom() + "/" + c.getCognoms() + "/" + c.getDataNaixement() + "/" + c.getSexe() + "/" + c.getPoblacio() +
                "/" + c.getNumTelefon() + "/" + c.getEmail() + "/" + c.getEntitat() + "/" + c.isFederat());
        writer.newLine();
        writer.close();
    }

    public void writerMarxaInFile(Marxa m) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileMarxa, true));
        writer.write(m.getEdicio() + "/" + m.getInscripcionsMarxa());
        writer.newLine();
        writer.close();
    }

    public void writerInscripcioInFile(Inscripcio i, int edicio) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileInscripcio, true));
        writer.write(edicio + "/" + i.getDorsal() + "/" + i.isModalitat() + "/" + i.getHoraSortida() + "/" + i.getHoraArribada() + "/" + i.getCorredor().getNif());
        writer.newLine();
        writer.close();
    }

    public ArrayList<Corredor> readCorredor() throws IOException {
        ArrayList<Corredor> corredors = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathFileCorredor));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");
            String nif = data[0];
            String nom = data[1];
            String cognom = data[2];
            LocalDate dataNaixement = LocalDate.parse(data[3]); // lo he mirado en internet
            Sexe sexe = Sexe.valueOf(data[4]); // lo he mirado en internet
            String poblacio = data[5];
            String telf = data[6];
            String email = data[7];
            String entitat = data[8];
            Boolean federat = Boolean.parseBoolean(data[9]);
            corredors.add(new Corredor(nif, nom, cognom, dataNaixement, sexe, poblacio, telf, email, entitat, federat));
        }
        return corredors;
    }
    public ArrayList<Marxa> readMarxa(ArrayList<Corredor> corredores) throws IOException {
        ArrayList<Marxa> marxas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathFileMarxa));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");
            int edicion = Integer.parseInt(data[0]);
            marxas.add(new Marxa(edicion));
        }
        reader.close();
        reader = new BufferedReader(new FileReader(pathFileInscripcio));
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");
            int edicio = Integer.parseInt(data[0]);
            int dorsal = Integer.parseInt(data[1]);
            boolean modalitat = Boolean.parseBoolean(data[2]);
            LocalDate horaSortida = LocalDate.parse(data[3]);
            LocalDate horaAcabada = LocalDate.parse(data[4]);
            Corredor corredor = buscarCorredor(corredores, data[3]);
            Marxa marxa = buscarMarxa(marxas, edicio);
            if (marxa != null && corredor != null) {
                marxa.addCorrInsc(new Inscripcio(dorsal, modalitat, corredor));
            }
        }
        reader.close();
        return marxas;
    }

    public static Marxa buscarMarxa(ArrayList<Marxa> marxas, int edicion) {
        for (Marxa m : marxas) {
            if (m.getEdicio() == edicion) {
                return m;
            }
        }
        return null;
    }

    public static Corredor buscarCorredor(ArrayList<Corredor> corredores, String nif) {
        for (Corredor c : corredores) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Inscripcio> readInscripcio() throws IOException {
        ArrayList<Inscripcio> inscripcions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathFileInscripcio));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");
            Marxa marxa = new Marxa(Integer.parseInt(data[0]));
            int dorsal = Integer.parseInt(data[1]);
            boolean modalitat = Boolean.parseBoolean(data[2]);
            Corredor corredor = new Corredor (data[3]);
            inscripcions.add(new Inscripcio(dorsal, modalitat, corredor));
        }
        return inscripcions;
    }


    public void ReescribirCorredor(ArrayList<Corredor> corredors) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileCorredor, false));
        for (Corredor c : corredors) {
            writerCorredorInFile(c);
        }
        writer.close();
    }
}

