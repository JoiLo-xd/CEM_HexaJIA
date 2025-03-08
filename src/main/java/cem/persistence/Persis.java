package cem.persistence;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import cem.model.Corredor;
import cem.model.Marxa;
import java.time.LocalDate;
import cem.enums.Sexe;



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
        writer.write(c.getNif() + "/" + c.getNom() + "/" + c.getCognoms() + "/" + c.getDataNaixement() + "/" + c.getPoblacio() + "/" + c.getSexe() +
                "/" + c.getNumTelefon() + "/" + c.getEmail() + "/" + c.getEntitat() + c.isFederat());
        writer.newLine();
        writer.close();
    }

    public void writerMarxaInFile(Marxa m) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileMarxa, true));
        writer.write(m.getEdicio() + "/" + m.getInscripcionsMarxa());
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
    public ArrayList<Marxa> readMarxa() throws IOException {
        ArrayList<Marxa> marxas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathFileMarxa));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");
            int edicion = Integer.parseInt(data[0]);
            marxas.add(new Marxa(edicion));
            // no se tengo que añadir ahi e larrayList de inscripciones
        }
        return marxas;
    }

}

