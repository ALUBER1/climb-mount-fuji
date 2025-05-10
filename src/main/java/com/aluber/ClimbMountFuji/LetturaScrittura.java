package com.aluber.ClimbMountFuji;

import java.io.*;

class FileException extends Exception {
    private String matter = "";

    public FileException(String matter) {
        this.matter = matter;
    }

    public String getMatter() {
        return this.matter;
    }
}

enum Mode {
    READ,
    WRITE
}

/**
 * classe per la gestione sequenziale di un file di testo
 */
public class LetturaScrittura {

    private final Mode mode;
    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * costruttore di un oggetto FileReader/FileWriter sopra il file specificato
     * (in caso di scrittura il file se non esistente viene creato, se esistente
     * viene sovrascritto)
     *
     * @param filename percorso/nome del file
     * @param mode     R per sola lettura, W per sola scrittura
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public LetturaScrittura(String filename, char mode) throws IOException, IllegalArgumentException {
        switch (mode) {
            case 'R':
                this.mode = Mode.READ;
                reader = new BufferedReader(new FileReader(filename));
                break;
            case 'W':
                this.mode = Mode.WRITE;
                writer = new BufferedWriter(new FileWriter(filename));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * scrive una linea di testo nel file aperto in scrittura
     *
     * @param line linea di testo
     * @throws IOException
     * @throws FileException
     */
    public void toFile(String line) throws IOException, FileException {
        if (mode == Mode.READ) {
            throw new FileException("Read-only file!");
        }
        writer.write(line);
        writer.write("\r\n"); // nuova linea
    }

    /**
     * legge una linea di testo dal file aperto in lettura
     *
     * @return linea di testo
     * @throws IOException
     * @throws FileException
     */
    public String fromFile() throws IOException, FileException {
        if (mode == Mode.WRITE) {
            throw new FileException("Write-only file!");
        }
        String line = reader.readLine();
        if (line == null) {
            throw new FileException("End of file!");
        }
        return line;
    }

    /**
     * chiude il file aperto in scrittura o lettura
     *
     * @throws IOException
     */
    public void closeFile() throws IOException {
        if (mode == Mode.READ) {
            reader.close();
        } else // mode == Mode.WRITE
        {
            writer.close();
        }
    }

}