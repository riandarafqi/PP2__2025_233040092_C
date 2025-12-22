/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040092.modul08.controller;

import id.ac.unpas.pp2_c_233040092.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040092.modul08.view.PersegiPanjangView;

public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        this.view.getBtnHitung().addActionListener(e -> hitungLuas());
    }

    public void hitungLuas() {
        try {
            double panjang = Double.parseDouble(view.getTxtPanjang().getText());
            double lebar = Double.parseDouble(view.getTxtLebar().getText());

            model.setPanjang(panjang);
            model.setLebar(lebar);

            double hasil = model.hitungLuas();
            view.getLblHasil().setText(String.valueOf(hasil));

        } catch (Exception ex) {
            view.getLblHasil().setText("Error");
        }
    }
}


