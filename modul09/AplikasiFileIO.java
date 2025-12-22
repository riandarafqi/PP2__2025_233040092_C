/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040092.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    
public AplikasiFileIO() {
    super("Tutorial File IO & Exception Handling");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Inisialisasi Komponen (gak usah ribet)
    textArea = new JTextArea();
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    fileChooser = new JFileChooser();

    // Tombol-tombol
    JPanel buttonPanel = new JPanel();
    btnOpenText = new JButton("Buka Text");
    btnSaveText = new JButton("Simpan Text");
    btnSaveBinary = new JButton("Simpan Config (Binary)");
    btnLoadBinary = new JButton("Muat Config (Binary)");

    buttonPanel.add(btnOpenText);
    buttonPanel.add(btnSaveText);
    buttonPanel.add(btnSaveBinary);
    buttonPanel.add(btnLoadBinary);

    // Layout biar rapi
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Event handling, biar gak error
    btnOpenText.addActionListener(e -> bukaFileTeks());
    btnSaveText.addActionListener(e -> simpanFileTeks());
    btnSaveBinary.addActionListener(e -> simpanConfigBinary());
    btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
    muatLastNotes();
    setVisible(true);
    }


// Buka file teks, versi males ribet
private void bukaFileTeks() {
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            textArea.setText("");

            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            JOptionPane.showMessageDialog(this, "Sip, file kebuka.");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gak nemu filenya: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal baca file: " + ex.getMessage());
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

// Simpan teks, gaya males ribet
        private void simpanFileTeks() {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
               try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                   writer.write(textArea.getText());
                   JOptionPane.showMessageDialog(this, "Sip, file disimpan.");
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(this, "Yah, gagal simpan: " + ex.getMessage());
               }
           }
       }

// Simpan config ke binary, cuma ukuran font
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            JOptionPane.showMessageDialog(this, "Font (" + fontSize + ") disave ke config.bin");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal simpan bin: " + ex.getMessage());
        }
    }

// Load config dari binary, ubah font
        private void muatConfigBinary() {
            try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
                int fontSize = dis.readInt();
                textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
                JOptionPane.showMessageDialog(this, "Font diganti ke: " + fontSize);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Belum ada config.bin");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal baca bin: " + ex.getMessage());
            }
        }
        
        // Membaca last_notes.txt saat aplikasi dibuka
private void muatLastNotes() {
    File file = new File("last_notes.txt");
    if (!file.exists()) {
        return; // file tidak ada → diam saja
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        textArea.setText("");
        String line;
        while ((line = reader.readLine()) != null) {
            textArea.append(line + "\n");
        }
    } catch (IOException ex) {
        // diam saja, jangan error
    }
}

// Main, biar jalan
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new AplikasiFileIO().setVisible(true));
        }
}