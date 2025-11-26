/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040092.modul07;

/**
 *
 * @author Rianda Rafqi
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // [cite: 167, 168, 169, 170, 171]

public class ManajemenNilaiSiswaApp extends JFrame { // [cite: 39]

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane; // [cite: 47-53]

    public ManajemenNilaiSiswaApp() {
        // 1. Konfigurasi Frame Utama [cite: 152]
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 450); // Tinggi ditambah sedikit untuk tombol hapus
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. Inisialisasi Tabbed Pane [cite: 156, 157]
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel Tab 1 (Input) [cite: 158]
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel Tab 2 (Tabel) [cite: 159]
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        add(tabbedPane); // [cite: 160]
    }

    private JPanel createInputPanel() { // [cite: 58]
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10)); // Ubah row jadi 5 untuk tombol reset
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel("")); 
        panel.add(btnSimpan);
        
        // TUGAS 4: Tombol Reset 
        JButton btnReset = new JButton("Reset Form");
        panel.add(new JLabel("")); // Placeholder
        panel.add(btnReset);

        // Event Tombol Simpan
        btnSimpan.addActionListener(e -> prosesSimpan());
        
        // Event Tombol Reset
        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
        });

        return panel;
    }

    private JPanel createTablePanel() { // [cite: 88]
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Tabel
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // TUGAS 2: Tombol Hapus [cite: 227]
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBawah.add(btnHapus);
        panel.add(panelBawah, BorderLayout.SOUTH);

        // Event Tombol Hapus [cite: 228, 229]
        btnHapus.addActionListener(e -> {
            int selectedRow = tableData.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        return panel;
    }

    private void prosesSimpan() { // [cite: 102]
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // Validasi Nama Kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TUGAS 3: Validasi Nama Minimal 3 Karakter 
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal harus 3 karakter!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0-100!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TUGAS 1: Grade dengan Switch Case 
        String grade = "";
        int range = nilai / 10; // Membagi nilai untuk mendapatkan digit depan (contoh 85/10 = 8)
        
        switch (range) {
            case 10: 
            case 9:
            case 8:
                grade = "A"; // 80-100
                break;
            case 7:
                grade = "AB"; // 70-79
                break;
            case 6:
                grade = "B"; // 60-69
                break;
            case 5:
                grade = "BC"; // 50-59
                break;
            case 4:
                grade = "C"; // 40-49
                break;
            case 3:
                grade = "D"; // 30-39
                break;
            default:
                grade = "E"; // 0-29
                break;
        }

        // Masukkan ke Tabel
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // Reset Form
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1);
    }

    public static void main(String[] args) { // [cite: 162]
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}